package exemple_instruction.instructions;
import question1.SpecificationI;

public class While<E,M> extends Instruction<E,M>{
 private static boolean T = false;//true;
 
  protected SpecificationI<E> specification;
  protected Instruction<E,M>  instruction;

  public void setSpecification(SpecificationI<E> specification){
      this.specification = specification;
  }
  public void setInstruction(Instruction<E,M> instruction){
      this.instruction = instruction;
  }
  
  public void setTrace(boolean trace){
      T = T || trace;
  }
    
  @Override
  public M execute(E e,M memory)throws Exception{
      assert specification!=null && instruction!=null;
      if(T)System.out.println("While:condition.isSatisfied(memory): " + specification.isSatisfiedBy(e));
      while (specification.isSatisfiedBy(e)){
         memory = instruction.execute(e,memory);
         if(T)System.out.println("While:memory: " + memory);
         if(T)System.out.println("While:condition.isSatisfied(memory): " + specification.isSatisfiedBy(e));
         
      }
      return memory;
    }
    

}
