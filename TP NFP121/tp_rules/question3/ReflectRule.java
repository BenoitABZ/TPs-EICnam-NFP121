package question3;

import question1.ReflectSpecification;
import question2.ReflectCommand;

public class ReflectRule<E,R> extends Rule<E,R>  {
    protected ReflectSpecification<E>  instanceSpecification=null;
    protected ReflectCommand<E,R>  instanceCommand=null;

    public void setSpecification(String specification){
        try{
            instanceSpecification = new ReflectSpecification<E>();          
            instanceSpecification.setSpecification(specification);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setCommand(String command){
        try{
        	 instanceCommand = new ReflectCommand<E,R>();          
             instanceCommand.setCommand(command);
         }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public R execute(E e,R r) throws Exception {
       
    	if(instanceSpecification.isSatisfiedBy(e)) {
    		
    		r = instanceCommand.execute(e,r);
    		
    	}
        
        return r;
    }

   
}
