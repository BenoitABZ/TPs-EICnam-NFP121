package question3;
import question2.CommandI;
public class Print implements CommandI<Integer,Integer>{
    
    public Integer execute(Integer max,Integer i){
        System.out.println("print: " + "max=" + max + ", i="+ i); 
        return i;
    }

    public String toString(){
        return "print";
    }

    public Print(){}
}