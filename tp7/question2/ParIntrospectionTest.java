package question2;

import java.util.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class ParIntrospectionTest extends junit.framework.TestCase{
  private static class Entier extends Observable{
    private int x;
    
    public Entier(int x){ this.x = x;}
    
    public void initialiser(int valeur){
      this.x = valeur;
      setChanged();
      notifyObservers();
    }
    public String toString(){ return Integer.toString(x);}
  }
 

  private Observable obs = null;
  
	public void test_lierObservableEtObserver(){
	  try{
	    Entier e = new Entier(1);
	    ParIntrospection.lierObservableEtObserver( e, new Observer(){
	                                   public void update(Observable o, Object arg){
	                                     ParIntrospectionTest.this.obs = o;
	                                   }
	                                 }
	                               );
	    e.initialiser(3);
	    assertNotNull(" notification absente ???", obs);
	    assertEquals(" pas le bon Observable ???", e.toString(), obs.toString());
	    assertEquals(" pas le bon Entier ???", "3", obs.toString());
	  }catch(Exception e){
	    fail(" exception !!! " + e.getMessage());
	  }
	}
	
	public void test_lierJButtonEtActionListener() throws Exception{
	  try{
	    JButton jb = new JButton();
	    ActionListener obs = new ActionListener(){
	                                   public void actionPerformed(ActionEvent ae){
	                                   }
	                                 };
	    question2.ParIntrospection.lierSourceEtListener(jb, obs);
	    
	    assertTrue(" l'observateur n'est pas ajout� ???", jb.getActionListeners().length==1);
	    boolean present = jb.getActionListeners()[0].equals(obs);
      assertTrue(" l'observateur n'est pas le bon, �ch�c lors de l'appel de \"getActionListeners\"", present);

	  }catch(Exception e){
	    fail(" lier JButton et ActionListener l�ve une exception ???" + e.getMessage()); 
	  }
	}
	
	// � compl�ter
	
}
