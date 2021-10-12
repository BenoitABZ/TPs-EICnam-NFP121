package exemple_instruction.instructions;

import java.lang.reflect.Method;


public abstract class Visitor<T>{

    public <E,M> T visit(Instruction<E,M> instruction){
      return execute(instruction);
    }
    // cf. NFP121 cours 7 diapositive 50
    private T execute(Object object){
      Class<?> cl = this.getClass();
      while(cl != Object.class){
          try{
            Method m = cl.getDeclaredMethod("visit",object.getClass());
            //System.out.println("visit(" + instruction.getClass().getSimpleName());
            return (T)m.invoke(this, object);
          }catch(Exception e){
              cl = cl.getSuperclass();
          }
      }
      return null;
    }
    
  
}
