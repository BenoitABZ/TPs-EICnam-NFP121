package question3;

import question1.*;
import java.util.*;
import java.io.*;


public class TestMailBoxPersistent extends junit.framework.TestCase{
 

  public void testSauvegarde() throws Exception{
    if(new File("./mailbox.ser").delete()) 
       System.out.println("mailbox.ser supprimé");
      
    IMailBox mb = new MailBoxPersistent("mailbox.ser",new SerializationMode());
    mb.add("a");mb.add("b");mb.add("c");mb.add("d"); // 4 destinataires : a,b,c,d
    mb.send("msg_1",new String[]{"a","b","c"});
    for(int i=2; i<4; i++){
      mb.send("msg_"+i,new String[]{"a","b","c","d"});
    }
    List<String> list = mb.read("a"); // lecture des messages pour "a"
    assertEquals(3,list.size());
    list = mb.read("a");
    assertEquals(0,list.size());
  }

  public void testRestitution1() throws Exception{
    IMailBox mb = new MailBoxPersistent("mailbox.ser",new SerializationMode());
    List<String> list = mb.read("a");
    assertEquals(0,list.size());
    list = mb.read("b"); // lecture des messages pour "b"
    assertEquals(3,list.size());
    list = mb.read("d"); // lecture des messages pour "d"
    assertEquals(2,list.size());
    mb.send("msg_4",new String[]{"a","b","d"});
  }
  
  public void testRestitution2() throws Exception{
    IMailBox mb = new MailBoxPersistent("mailbox.ser",new SerializationMode());
    List<String> list = mb.read("a");
    assertEquals(1,list.size());
    list = mb.read("b"); // lecture des messages pour "b"
    assertEquals(1,list.size());
    list = mb.read("d"); // lecture des messages pour "d"
    assertEquals(1,list.size());
  }
 
}
