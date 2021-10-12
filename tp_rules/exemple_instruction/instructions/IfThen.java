package exemple_instruction.instructions;

import question1.SpecificationI;

public class IfThen<E,M> extends Instruction<E,M>{
 private static  boolean T = false;//true;
  protected SpecificationI<E> specification;
  protected Instruction<E,M>    instruction;

  
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
      if(T)System.out.println("condition.isSatisfied(memory): " + specification.isSatisfiedBy(e));
      if (specification.isSatisfiedBy(e)){
         M m1 = instruction.execute(e,memory);
         if(T)System.out.println("memoire après: " + m1);
         return m1;
      }else
         return memory;
    }
    

}
