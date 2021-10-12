package exemple_instruction.instructions;

import question2.CommandI;

public abstract class Instruction<E,M> implements CommandI<E,M>{

  public <T> T accept(Visitor<T> visitor){
      return visitor.visit(this);
  }
  
}
