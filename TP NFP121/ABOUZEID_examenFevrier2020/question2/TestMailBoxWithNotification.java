package question2;

import question1.*;

import java.util.List;
import java.util.ArrayList;

public class TestMailBoxWithNotification extends junit.framework.TestCase{
 
  public class TestMailBoxListener implements MailBoxListener{
    private List<String> notifications = new ArrayList<>();

    public void onReceive(String msg) throws Exception{
      notifications.add(msg);
    }
    public List<String> getNotifications(){
      return notifications;
    }
  }
  
  public void testScenario1() throws Exception{
    IMailBoxNotification mb = new MailBoxNotification();
    TestMailBoxListener listenerA = new TestMailBoxListener();
    mb.add("a");
    mb.addMailBoxListener("a", listenerA);  // un destinataire : a
    String msg = new String("src_mail_1"); // une source : src
    mb.send(msg,"a"); 
    assertEquals("src_mail_1",listenerA.getNotifications().get(0));

  }
  
  public void testScenario2()  throws Exception{
    IMailBoxNotification mb = new MailBoxNotification();
    mb.add("a");mb.add("b");mb.add("c");mb.add("d");
    TestMailBoxListener listenerA = new TestMailBoxListener();
    mb.addMailBoxListener("a", listenerA);  // un destinataire : a
    TestMailBoxListener listenerB = new TestMailBoxListener();
    mb.addMailBoxListener("b", listenerB);  // un destinataire : b    
    TestMailBoxListener listenerC1 = new TestMailBoxListener();
    mb.addMailBoxListener("c", listenerC1);  // un destinataire : c   
    TestMailBoxListener listenerC2 = new TestMailBoxListener();
    mb.addMailBoxListener("c", listenerC2);  // le meme c avec un 2ème "écouteur"  
    
    String msg = new String("src_mail_1"); 
    mb.send(msg,new String[]{"a","b","c"});
    
    assertEquals("src_mail_1",listenerA.getNotifications().get(0));
    assertEquals("src_mail_1",listenerB.getNotifications().get(0));
    assertEquals("src_mail_1",listenerC1.getNotifications().get(0));
    assertEquals("src_mail_1",listenerC2.getNotifications().get(0));

  }

  
}