package exemple_instruction.instructions;

public class Debug<E,M> extends Instruction<E,M>{
    private static boolean T = true; //false; 

    private E e;
    private M memory;

    public void setTrace(boolean trace){
        T = T || trace;
    }

    public M execute(E e,M memory){
        this.e = e;
        this.memory = memory;
        if(T)System.out.println("Debug:<entity,memory>: <" + e +"," + memory + ">");
        return memory;
    }

    public String toString(){
        return "Debug:<entity,memory>: <" + e +"," + memory + ">";
    }
}
