package question3;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JSlider;

public class Vue2 extends JPanel implements Observer {
	  
	  private JSlider jauge;
	  private PileModele<Integer> pile;

	  public Vue2(PileModele<Integer> pile) {
	    super();
	    this.pile = pile;
	    this.jauge = new JSlider(JSlider.HORIZONTAL, 0, pile.capacite(), 0);
	    this.jauge.setValue(0);
	    setLayout(new FlowLayout(FlowLayout.CENTER));
	    this.jauge.setEnabled(false);
	    add(this.jauge);
	    setBackground(Color.magenta);
	    pile.addObserver(this);
	  }
	  
	  @Override
	  public void update(Observable obs, Object arg) {
	    jauge.setValue(pile.taille());
	  }


	}



