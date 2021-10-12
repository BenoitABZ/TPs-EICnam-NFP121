package question4;

import question1.*;
import question3.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.List;
import java.util.Scanner;
import java.io.*;

public class IHM extends JFrame {

    private JTextArea      resultat = new JTextArea("", 7,60);
    private JTextField     message = new JTextField(" un message...",24);
    private JButton        envoi = new JButton("envoyer");
    private JTextField     destinataire = new JTextField("a",4);
    private JButton        relève = new JButton("relever");
    private MailBox        mb;
    private PersistentMode persistent;

    public IHM() {
        this.setTitle("IHM de \"test\" class question3.MailBox");
        this.mb = new MailBox();
        //this.persistent = new XMLMode();
        this.persistent = new SerializationMode();
        
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(resultat, BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("message:"));
        panel.add(message);
        panel.add(envoi);
        panel.add(new JLabel("destinataire:"));
        panel.add(destinataire);
        panel.add(relève);
        container.add(panel, BorderLayout.SOUTH);
        
        this.envoi.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                  StringMessage sm = new StringMessage("IHM");
                  sm.setContent(message.getText());
                  try{
                    mb.add(destinataire.getText());
                  }catch(Exception e){}
                  try{
                    mb.post(sm,destinataire.getText());
                    persistent.writeMailBox(mb,"mailbox_temp.xml");
                    mb = persistent.readMailBox("mailbox_temp.xml");
                    resultat.setText(readFile("mailbox_temp.xml"));
                  }catch(Exception e){
                    resultat.setText(e.getMessage());
                  }
                  IHM.this.pack();
                }            
            });
            
          this.relève.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                  List<IMessage> list = null;
                  try{
                    list = mb.load(destinataire.getText());
                    persistent.writeMailBox(mb,"mailbox_temp.xml");
                    mb = persistent.readMailBox("mailbox_temp.xml");
                    resultat.setText(list.toString() + "\n" + readFile("mailbox_temp.xml"));
                  }catch(Exception e){
                    resultat.setText(e.getMessage());
                  }
                  IHM.this.pack();
                }
                            
            });
            
        this.pack();
        this.setVisible(true);
    }

    public static void main() {
        new IHM();    
    }
   
   // extrait de http://stackoverflow.com/questions/326390/how-to-create-a-java-string-from-the-contents-of-a-file
   private static String readFile(String fileName) throws Exception{
     return new Scanner( new File(fileName) ).useDelimiter("\\A").next();
   }

}