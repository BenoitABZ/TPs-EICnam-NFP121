package exemple_instruction.instructions;


public class MacroInstruction<E,M> extends Instruction<E,M>{
  private static boolean T = false;//true;
  protected Instruction<E,M>[]  instructions;

  public void setInstructions(Instruction<E,M>[] instructions){
      this.instructions = instructions;
  }
  
  public void setTrace(boolean trace){
      T = T || trace;
  }
  
  public M execute(E e,M memory)throws Exception{
    for(Instruction<E,M> instruction : instructions){
        memory = instruction.execute(e,memory);
        if(T)System.out.println("MacroInstruction:memoire: " + memory);
    }
    return memory;
    }
    
}