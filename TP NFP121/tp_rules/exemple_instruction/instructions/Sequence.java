package exemple_instruction.instructions;

public class Sequence<E,M> extends Instruction<E,M>{
    private static  boolean T = false;//true;
    protected Instruction<E,M>  instruction1;
    protected Instruction<E,M>  instruction2;

    public void setInstruction1(Instruction<E,M> instruction1){
        this.instruction1 = instruction1;
    }

    public void setInstruction2(Instruction<E,M> instruction2){
        this.instruction2 = instruction2;
    }

    public void setTrace(boolean trace){
        T = T || trace;
    }

    public M execute(E e,M memory)throws Exception{
        M memory1 = instruction1.execute(e,memory);
        if(T)System.out.println("Sequence:memoire1: " + memory1);
        M memory2 = instruction2.execute(e,memory1);
        if(T)System.out.println("Sequence:memoire2: " + memory2);
        return memory2;
    }

}