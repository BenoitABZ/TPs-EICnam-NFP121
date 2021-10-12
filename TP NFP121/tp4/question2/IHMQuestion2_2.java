package question2;

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;

/**
 * Ajout de Listeners (Observateurs) de type MouseListener aux boutons A, B et C (Observes). Les 3 listeners (JMouseListener implementant l'interface MouseListener) auront pour noms Jm0X. 
 * 
 * Le contenu sera transmis aux Listeners pour mise a jour de ce dernier en cas de survole sur les boutons. MouseListener est plus adapté aux évènements liés à la souris.
 * 
 * @author benoit
 *
 */

public class IHMQuestion2_2 extends JFrame {

    private JButton boutonA = new JButton("A");
    private JButton boutonB = new JButton("B");
    private JButton boutonC = new JButton("C");

    private TextArea contenu = new TextArea(30, 80);

 
    public IHMQuestion2_2() {
        super("IHM Question2_2");
        JPanel enHaut = new JPanel();
        enHaut.add(boutonA);
        enHaut.add(boutonB);
        enHaut.add(boutonC);
        setLayout(new BorderLayout(5, 5));
        add("North", enHaut);
        add("Center", contenu); 
        setLocation(150,150);pack();show();
        enHaut.setBackground(Color.magenta);
        
        //...
        
        //Dans le constructeur
        
        boutonA.addActionListener(new JButtonObserver("jb01", contenu));
        boutonA.addActionListener(new JButtonObserver("jb02", contenu)); 
        boutonA.addActionListener(new JButtonObserver("jb03", contenu));
        boutonB.addActionListener(new JButtonObserver("jb01", contenu));
        boutonB.addActionListener(new JButtonObserver("jb02", contenu));
        boutonC.addActionListener(new JButtonObserver("jb01", contenu));
       
        boutonA.addMouseListener(new JMouseObserver("jm01", contenu));
        boutonB.addMouseListener(new JMouseObserver("jm02", contenu));
        boutonC.addMouseListener(new JMouseObserver("jm01", contenu));
        
    }
    
     public static void main(String[] args){
        new IHMQuestion2_1();
        new IHMQuestion2_2();
    }


}
