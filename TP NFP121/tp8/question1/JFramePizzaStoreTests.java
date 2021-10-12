package question1;

import java.awt.Robot;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.Random;

import java.applet.*;
import java.beans.*;
import java.net.*;
import java.io.*;

public class JFramePizzaStoreTests extends junit.framework.TestCase{
    private static Random random= new Random();
    private JFrame f;
    private Robot robot;

    /**
     * Met en place les engagements.
     *
     * Méthode appelée avant chaque appel de méthode de test.
     */
    protected void setUp() throws java.lang.Exception{
   
        f = new PizzaStore();
        f.pack();
        f.setVisible(true);
        while(!(f.isShowing())){}
        f.setAlwaysOnTop(true);
        f.setLocation(100, 100);
        robot = new Robot();
        robot.delay(120);

    }

    /**
     * Supprime les engagements
     *
     * Méthode appelée après chaque appel de méthode de test.
     */
    protected void tearDown(){ // throws java.lang.Exception
        f.dispose();
    }

    public void test_PizzaStore_PizzaSolo() throws Exception{
        Container panel = f.getContentPane();
        Component[] components = panel.getComponents();
        assertEquals(components.length, 1);

        // la bonne IHM
        assertTrue(components[0] instanceof JPanel);  
        Component[] subComponents = ((JPanel)components[0]).getComponents();

        assertTrue(subComponents[0] instanceof JPanel); // // panelGlobal
        assertTrue(subComponents[1] instanceof JPanel); // // panelMake

        Component[] subsubComponents = ((JPanel)subComponents[0]).getComponents();
        assertTrue(subsubComponents[0] instanceof JLabel); // afficheur
        assertTrue(subsubComponents[1] instanceof JPanel); // panelBoutons
        assertTrue(subsubComponents[2] instanceof JPanel); // panelDecorations

        Component[] subsubsubComponents = ((JPanel)subsubComponents[1]).getComponents();
        assertTrue(subsubsubComponents[0] instanceof JButton);// solo
        assertTrue(subsubsubComponents[1] instanceof JButton);// classic
        assertTrue(subsubsubComponents[2] instanceof JButton);// crust

        click(subsubsubComponents[0]);
        verificationDuResultat(subsubComponents[0]," pizza solo 3 euros ? ","3 &#8364;</FONT></HTML>"); // 3 €
    }

    public void test_PizzaStore_PizzaSoloHamHam() throws Exception{
        Container panel = f.getContentPane();
        Component[] components = panel.getComponents();
        assertEquals(components.length, 1);

        // la bonne IHM
        assertTrue(components[0] instanceof JPanel);  
        Component[] subComponents = ((JPanel)components[0]).getComponents();

        assertTrue(subComponents[0] instanceof JPanel); // // panelGlobal
        assertTrue(subComponents[1] instanceof JPanel); // // panelMake

        Component[] subsubComponents = ((JPanel)subComponents[0]).getComponents();
        assertTrue(subsubComponents[0] instanceof JLabel); // afficheur
        assertTrue(subsubComponents[1] instanceof JPanel); // panelBoutons
        assertTrue(subsubComponents[2] instanceof JPanel); // panelDecorations

        Component[] subsubsubComponents = ((JPanel)subsubComponents[1]).getComponents();
        assertTrue(subsubsubComponents[0] instanceof JButton);// solo
        assertTrue(subsubsubComponents[1] instanceof JButton);// classic
        assertTrue(subsubsubComponents[2] instanceof JButton);// crust

        click(subsubsubComponents[0]);
        verificationDuResultat(subsubComponents[0]," pizza solo 3 euros ? ", "3 &#8364;</FONT></HTML>"); // 3 €

        Component[] decorations = ((JPanel)subsubComponents[2]).getComponents();
        click(decorations[2]); // Ham
        verificationDuResultat(subsubComponents[0]," solo + Ham ??? ","4,5 &#8364;</FONT></HTML>"); // 4,5 €
        click(decorations[2]); // Ham
        click(decorations[2]); // Ham
        verificationDuResultat(subsubComponents[0]," solo + Ham + Ham ??? ","6 &#8364;</FONT></HTML>"); // 6 €

    }

    public void test_PizzaStore_PizzaClassicClamsClams() throws Exception{
        Container panel = f.getContentPane();
        Component[] components = panel.getComponents();
        assertEquals(components.length, 1);

        // la bonne IHM
        assertTrue(components[0] instanceof JPanel);  
        Component[] subComponents = ((JPanel)components[0]).getComponents();

        assertTrue(subComponents[0] instanceof JPanel); // // panelGlobal
        assertTrue(subComponents[1] instanceof JPanel); // // panelMake

        Component[] subsubComponents = ((JPanel)subComponents[0]).getComponents();
        assertTrue(subsubComponents[0] instanceof JLabel); // afficheur
        assertTrue(subsubComponents[1] instanceof JPanel); // panelBoutons
        assertTrue(subsubComponents[2] instanceof JPanel); // panelDecorations

        Component[] subsubsubComponents = ((JPanel)subsubComponents[1]).getComponents();
        assertTrue(subsubsubComponents[0] instanceof JButton);// solo
        assertTrue(subsubsubComponents[1] instanceof JButton);// classic
        assertTrue(subsubsubComponents[2] instanceof JButton);// crust

        click(subsubsubComponents[1]);
        verificationDuResultat(subsubComponents[0]," pizza classic 5 euros ? ", "5 &#8364;</FONT></HTML>"); // 5 €

        Component[] decorations = ((JPanel)subsubComponents[2]).getComponents();
        click(decorations[5]); // Clams
        verificationDuResultat(subsubComponents[0]," classic + Clams ??? ","8,3 &#8364;</FONT></HTML>"); // 8,3 €
        click(decorations[5]); // Clams
        click(decorations[5]); // Clams
        verificationDuResultat(subsubComponents[0]," classic + Clams(once) ??? ","8,3 &#8364;</FONT></HTML>"); // 6 €

    }

    public void test_PizzaStore_PizzaCrustTomatoMozarella() throws Exception{
        Container panel = f.getContentPane();
        Component[] components = panel.getComponents();
        assertEquals(components.length, 1);

        // la bonne IHM
        assertTrue(components[0] instanceof JPanel);  
        Component[] subComponents = ((JPanel)components[0]).getComponents();

        assertTrue(subComponents[0] instanceof JPanel); // // panelGlobal
        assertTrue(subComponents[1] instanceof JPanel); // // panelMake

        Component[] subsubComponents = ((JPanel)subComponents[0]).getComponents();
        assertTrue(subsubComponents[0] instanceof JLabel); // afficheur
        assertTrue(subsubComponents[1] instanceof JPanel); // panelBoutons
        assertTrue(subsubComponents[2] instanceof JPanel); // panelDecorations

        Component[] subsubsubComponents = ((JPanel)subsubComponents[1]).getComponents();
        assertTrue(subsubsubComponents[0] instanceof JButton);// solo
        assertTrue(subsubsubComponents[1] instanceof JButton);// classic
        assertTrue(subsubsubComponents[2] instanceof JButton);// crust

        click(subsubsubComponents[2]);
        verificationDuResultat(subsubComponents[0]," pizza crust 7 euros ? ", "7 &#8364;</FONT></HTML>"); // 5 €

        Component[] decorations = ((JPanel)subsubComponents[2]).getComponents();
        click(decorations[3]); // tomato
        verificationDuResultat(subsubComponents[0]," crust + tomato ??? ","7,7 &#8364;</FONT></HTML>"); // 8,3 €
        click(decorations[1]); // Ham
        verificationDuResultat(subsubComponents[0]," crust + tomato + fresh mozarella ??? ","8,2 &#8364;</FONT></HTML>"); // 6 €

    }

    private void click(Component bouton) throws Exception{
        Point location = bouton.getLocationOnScreen();
        mouseMoveAndClick(location.x+2,location.y+2);
        robot.delay(100);
    }

    private void verificationDuResultat(Component label, String message, String resultatAttendu){
        JLabel res = (JLabel) label;
        String resultatAvecUnPoint = resultatAttendu.replaceAll(",",".");
        assertTrue(message, res.getText().toUpperCase().endsWith(resultatAttendu) || res.getText().toUpperCase().endsWith(resultatAvecUnPoint));
    }

    public void mouseMoveAndClick(int x, int y){
        robot.mouseMove( x,y);
        robot.delay(100);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(100);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(100);
    }//end mouseMoveAndClick

  
  
}
