package question1;

import java.util.List;
import java.util.ArrayList;

public class TestMailBox extends junit.framework.TestCase{

  
  public void testScenario1() throws Exception{
    IMailBox mb = new MailBox();
    mb.add("a");mb.add("b");mb.add("c");mb.add("d"); // 4 destinataires : a,b,c,d
 
    String msg1 = new String("src1_msg1"); // source : src1, message msg1 
    mb.send(msg1,"a");
    List<String> list = mb.read("a");
    assertEquals(1,list.size());
    assertEquals("src1_msg1",list.get(0));
    list = mb.read("a");
    assertEquals(0,list.size());
    
    String msg2 = new String("src2_mail_2");
    mb.send(msg2,"a");
    list = mb.read("a");
    assertEquals(1,list.size());
    assertEquals("src2_mail_2",list.get(0));

    String msg3 = new String("src3_mail_3");
    mb.send(msg3,new String[]{"a","b","c","d"});
    
    String msg4 = new String("src4_mail_4");
    mb.send(msg4,new String[]{"a","b","c"});
    list = mb.read("b");
    assertEquals(2,list.size());
    assertEquals("src3_mail_3",list.get(0));
    assertEquals("src4_mail_4",list.get(1));
    
    list = mb.getAllMessages("a");
    assertEquals(4,list.size());  // 4 messages reçus en tout
    list = mb.getAllUnreadMessages("a");
    assertEquals(2,list.size());  // 2< messages lus
  }
  
  public void testScenario2_Cas_Exception() throws Exception{
    IMailBox mb = new MailBox();
    mb.add("a");mb.add("b");mb.add("c");mb.add("d"); // 4 destinataires : a,b,c,d
    String msg1 = new String("src1_mail_1"); // une source : src1
    try{
      mb.send(msg1,"x");
      fail("une exception destinataire inconnu est attendue !");
    }catch(Exception e){
      try{
        mb.send(msg1,new String[]{"a","b","x","c"});
        fail(" x est inconnu,  une exception doit etre levée, a,b et c sont bien inscrits");
      }catch(Exception e1){
        assertEquals("src1_mail_1",mb.read("a").get(0));
        assertEquals("src1_mail_1",mb.read("b").get(0));
        assertEquals("src1_mail_1",mb.read("c").get(0));
      }
    }
  }

  public void testScenario3_Equals() throws Exception{
    MailBox mb = new MailBox();
    mb.add("a");mb.add("b");mb.add("c");mb.add("d"); // 4 destinataires : a,b,c,d
    String msg1 = new String("src1_msg1"); // source : src1, message msg1 
    mb.send(msg1,"a");
    List<String> list = mb.read("a");
    list = mb.read("a");

    IMailBox mbBis = new MailBox(mb);
    assertEquals(mb,mbBis);

  }
 
}

