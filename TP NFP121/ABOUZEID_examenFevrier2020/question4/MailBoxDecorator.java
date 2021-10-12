package question4;

import java.util.*;
import question2.MailBoxListener;
import question3.PersistentMode;

public abstract class MailBoxDecorator implements IMailBox{
    private IMailBox mb;

    public MailBoxDecorator(IMailBox mb){
       this.mb=mb;
    }
    public MailBoxDecorator(){}
    public void setMailBox(IMailBox mb){
        this.mb=mb;
    }
    public void add(String dest) throws Exception{
        mb.add(dest);
    }

    public void send(String message, String dest) throws Exception{
        mb.send(message, dest);
    }

    public void send(String message, String[] dest) throws Exception{
        mb.send(message, dest);
    }
    
    public List<String> read(String dest) throws Exception{
        return mb.read(dest);
    }

 
    public Iterator<String> iterator(){
        return mb.iterator();
    }

    public List<String> getAllMessages(String dest) throws Exception{
        return mb.getAllMessages(dest);
    }
    
    public List<String> getAllUnreadMessages(String dest) throws Exception{
        return mb.getAllUnreadMessages(dest);
    }
    
    //public void addMailBoxListener(String destinataire, MailBoxListener listener) throws Exception{
    
    //}

 
}