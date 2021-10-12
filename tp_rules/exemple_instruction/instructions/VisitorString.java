package exemple_instruction.instructions;



public class VisitorString extends Visitor<String>{
    private static final int TAB=2;
    
    private int tab;

    private String tab(int tab){
        this.tab = tab;
        String s = new String();
        for(int i=0;i<tab;i++){
            s = s +" ";
        }
        return s;
    }
    
    public <E,M> String visit(Sequence<E,M> sequence){
        return sequence.instruction1.accept(this) + "\n" +
               tab(tab) + sequence.instruction2.accept(this) ;
    }
    
    public <E,M> String visit(IfThen<E,M> ifThen){
        return "if(" + ifThen.specification.toString() + "){\n" +
               tab(tab+TAB) + ifThen.instruction.accept(this) + "\n"+
               tab(tab-TAB) + "}";
    }
    public <E,M> String visit(While<E,M> whil){
        return "while(" + whil.specification.toString() + "){\n" +
               tab(tab+TAB) + whil.instruction.accept(this) + "\n"+
               tab(tab-TAB) + "}";
    }
    
    public <E,M> String visit(MacroInstruction<E,M> macro){
        String str = "[";
        for(Instruction<E,M> instruction : macro.instructions){
            str = str + instruction.accept(this);
        }
        return str+"]";
    }
    
    public <E,M> String visit(Debug<E,M> debug){
        return "debug: " + debug;
    }
    
    // public <M> String visit(Not<M> not){
        // return "not(" + not.condition.accept(this) + ")";
    // }
    // public <M> String visit(And<M> and){
        // return "and(" + and.condition1.accept(this) + "," +
                        // and.condition2.accept(this) + ")";
    // }
    // public <M> String visit(Or<M> or){
        // return "or(" + or.condition1.accept(this) + "," +
                       // or.condition2.accept(this) + ")";
    // }    
    
}
