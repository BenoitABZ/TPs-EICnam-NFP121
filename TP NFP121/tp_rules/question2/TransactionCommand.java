package question2;

import java.util.Stack;
import java.io.*;

public class TransactionCommand<E, R extends java.io.Serializable> extends Command<E,R>{
    private static final boolean T = false;
    
    private Stack<byte[]> stk; 

    public TransactionCommand(){
        this.stk = new Stack<byte[]>();
    }

    @Override
    public synchronized R execute(E entity, R result)throws Exception{
        try{
            if(T)System.out.println("TransactionCommand:execute="+result);
            beginTransaction(result); 
            R res = getCommand().execute(entity,result);
            endTransaction();
            return res;
        }catch(Exception e){
            return rollBack();
        }
    }

    protected void beginTransaction(Serializable result){
        if(result!=null){
            if(T)System.out.println("beginTransaction:result="+result);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try{
                ObjectOutput out = new ObjectOutputStream(bos);
                out.writeObject(result);
                stk.push(bos.toByteArray());
                bos.close();
                out.close();
            }catch(Exception e){
                //throw new RuntimeException("sauvegarde: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    protected void endTransaction( ){
        if(T)System.out.println("endTransaction");
        if(!stk.isEmpty()){
            byte[] content = stk.pop();
        }
    }

    private R rollBack(){
        R result = null;
        try{
            if(!stk.isEmpty()){
                byte[] content = stk.pop();
                ByteArrayInputStream bis = new ByteArrayInputStream(content);
                ObjectInput in = new ObjectInputStream(bis);
                result = (R) in.readObject();
                bis.close();
                in.close();
                if(T)System.out.println("rollBack:result="+result);
            }
        }catch(Exception e){
            //throw new RuntimeException("restitution: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


}
