package question2;

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;



/**
 * Ajout de Listeners (Observateurs) de type ActionListener aux boutons A, B et C (Observes). Les 3 listeners (JbuttonListener implementant l'interface ActionListener) auront pour noms Jb0X. 
 * 
 * Le contenu sera transmis aux Listeners pour mise a jour de ce dernier en cas de clique sur les boutons.
 * 
 * @author benoit
 *
 */
public class IHMQuestion2_1 extends JFrame {

    private JButton boutonA = new JButton("A");
    private JButton boutonB = new JButton("B");
    private JButton boutonC = new JButton("C");

    private TextArea contenu = new TextArea(30, 80);

 
    public IHMQuestion2_1() {
        super("IHM Question2_1");
        JPanel enHaut = new JPanel();
        enHaut.add(boutonA);
        enHaut.add(boutonB);
        enHaut.add(boutonC);
        setLayout(new BorderLayout(5, 5));
        add("North", enHaut);
        add("Center", contenu); 
        enHaut.setBackground(Color.blue);
        setLocation(100,100);
        pack();show();

        boutonA.addActionListener(new JButtonObserver("jb01", contenu));
        boutonA.addActionListener(new JButtonObserver("jb02", contenu)); 
        boutonA.addActionListener(new JButtonObserver("jb03", contenu));
        boutonB.addActionListener(new JButtonObserver("jb01", contenu));
        boutonB.addActionListener(new JButtonObserver("jb02", contenu));
        boutonC.addActionListener(new JButtonObserver("jb01", contenu));
      
    }
    
    public static void main(String[] args){
        new IHMQuestion2_1();
    }

}
