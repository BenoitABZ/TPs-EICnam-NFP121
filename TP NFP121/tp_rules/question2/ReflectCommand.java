package question2;

import java.lang.reflect.*;

import question1.SpecificationI;


public class ReflectCommand<E,R> extends Command<E,R>  {
    protected CommandI<E,R>  instanceCommand=null;

    public void setCommand(String command){
    	try {

			Class<?> classSpecification = Class.forName(command);

			try {

				instanceCommand = (CommandI<E,R>) classSpecification.newInstance();

			} catch (InstantiationException e) {

				e.printStackTrace();

			} catch (IllegalAccessException e) {

				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}
    

    
    public R execute(E entity, R result)throws Exception{
        Method mCommand=null;
        try{
            if(instanceCommand!=null)
            	mCommand = instanceCommand.getClass().getDeclaredMethod("execute", entity.getClass(), result.getClass());

            return (R) mCommand.invoke(instanceCommand, entity, result);
            

        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }

    }
}
