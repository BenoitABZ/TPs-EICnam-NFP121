package postliminaire;


import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.NoSuchElementException;

import question1.Contexte;

public class Code implements Iterable<String>{
  private List<String> memory;
  private Contexte ctxt;
  private String nomDeClasse;
  private int limitStack;
  private int limitLocals;
  
  public Code( String nomDeClasse, Contexte ctxt){
    this.memory = new ArrayList<String>();
    this.ctxt = ctxt;
    this.nomDeClasse = nomDeClasse;
    enteteJasmin();
  }
  
  public int varIndex(String name){
    int vars = 1; //  0 est réservé
    for(String var : ctxt){
      if (var.equals(name)) return vars;   
      vars++;
    }
    
    throw new NoSuchElementException();
  }
  
 
  public void add(String inst){
    memory.add(inst);
  }
  
  public void add(int inst){
    memory.add(Integer.toString(inst));
  }
  
  public void clear(){
    memory.clear();
    enteteJasmin();  
  }
  
  public void addLabel(int label){
    memory.add("#_" + label + ":");
  }
  
  public int currentPosition(){
    return memory.size();
  }
  
  public void setLabel(int index, int label){
     memory.set(index,"#_" + label);
  }
    
  public void setByte(int index, int byte1){
    memory.set(index, Integer.toString(byte1));
  }
  
  public void setTwoBytes(int index, int byte1, int byte2){
    memory.set(index,Integer.toString(byte1));
    memory.set(index+1,Integer.toString(byte2));
  }
  
  public void setWord(int index, int word){
    memory.set(index,Integer.toString(word>>8));
    memory.set(index+1,Integer.toString(word & 0xFF));
  }
  
  public void setLimitStack(int limit){
    memory.set(limitStack,".limit stack " + limit);
  }
  
  public void setLimitLocals(int limit){
    memory.set(limitLocals,".limit locals " + limit);
  }
  
  public String get(int index){
    return memory.get(index);
  }
  
  public String nomDeClasse(){
    return this.nomDeClasse;
  }
  
  public String toString(){
    int index = 0;
    String str = new String();
    Iterator<String> it = iterator();
    while( it.hasNext()){
      String inst = it.next();
      if(inst.startsWith("#_") || inst.startsWith(".")){ // une étiquette ou une directive
        str += inst;
      }else{
//       str+= index + ":\t" + inst;
        str+= "\t" + inst;
        Integer operands=opr.get(inst);
        if(operands != null){
          for(int i = 0; i < operands;i++){
            str += " " + it.next();
          }
         index += operands;
        }
      }
        index++;
        str += "\n";
    }
    
    str += "\treturn\n";
    str += ".end method\n\n";
    
    return str;
  }
  
  public Iterator<String> iterator(){
    return memory.iterator();
  }

  public String jasminFormat(){
    return memory.toString();
  }
  
  
  private void enteteJasmin(){
    add("; whileL et ses visiteurs en jasmin ...");
    add(".class public " + this.nomDeClasse);
    add(".super java/lang/Object");
    add(".method public static main([Ljava/lang/String;)V");
    limitStack = currentPosition();
    add(".limit stack X ???");
    limitLocals = currentPosition();
    add(".limit locals X ???");
    contexte();
  }
  
  private void contexte(){
    int vars = 1; // 0 est réservé
    for(String name : ctxt){
      add("ldc"); add(ctxt.lire(name)); 
      add("istore"); add(varIndex(name));
      vars++;
    }
    this.setLimitLocals(vars);
  }
  
  
  private static final Map<String,Integer> opr;
  static{
    opr = new TreeMap<String,Integer>();
//     opr.put("ifne",2);
//     opr.put("goto",2);
//     opr.put("if_icmple",2);
//     opr.put("if_icmpne",2);
//     opr.put("if_icmpge",2);
  
    opr.put("ifne",1);
    opr.put("ifeq",1);
    opr.put("goto",1);
    opr.put("getstatic",1);
    opr.put("invokevirtual",1);
    opr.put("invokestatic",1);
        
    opr.put("new",1);
    opr.put("invokespecial",1);
    opr.put("if_icmple",1);
    opr.put("if_icmpne",1);
    opr.put("if_icmpge",1);
    opr.put("if_icmpeq",1);
    opr.put("ldc",1);
    
    opr.put("sipush",1); 
    opr.put("bipush",1);
    opr.put("ipush",1); 
    opr.put("istore",1);
    opr.put("iload",1);
    // les absents 0
  }
}
