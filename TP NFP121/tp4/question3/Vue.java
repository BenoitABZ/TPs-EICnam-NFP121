package question3;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.Observable;
import java.util.Observer;

/**
 * MVC : la Vue représente l'interface utilisateur. Elle correspond à l'affichage de l'état de la pile. 
 * 
 * Dans le cadre du pattern Observateur/Observé, elle implementera donc l'interface Observer.
 * 
 * @author benoit
 * @version 0.0.1
 */
public class Vue extends JPanel implements Observer {

	private JLabel etatPile;
	private PileModele<Integer> pile;

	public Vue(PileModele<Integer> pile) {
		super();
		this.pile = pile;
		this.etatPile = new JLabel("entrez des nombres entiers");
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(etatPile);
		setBackground(Color.green);
		
	    //Dans le constructeur, déclaration auprés de la pile de sa qualité d'observateur.
		
		pile.addObserver(this);
	}

	   // La vue reflète l'état de la pile.
	@Override
	public void update(Observable obs, Object arg) {
		etatPile.setText(pile.toString()); // ou obs.toString()

	}

}
