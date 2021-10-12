package question2;

public class SkipCommand implements CommandI<Object,Object>{

    public Object execute(Object entity, Object result)throws Exception{
        return result;
    }

    public String toString(){
        return this.getClass().getSimpleName();
    }
}