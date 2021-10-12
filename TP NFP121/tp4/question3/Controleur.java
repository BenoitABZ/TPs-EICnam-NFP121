package question3;

import question3.tp3.PileI;

import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * MVC Le Contrôleur assure la synchronisation entre la vue et le modèle.
 * 
 * Le Contrôleur gère les évènements issus des boutons +, -, *, /,[].
 * 
 * Le contrôleur gère localement les écouteur(Listener) des boutons de l'IHM,
 * notons que la gestion des boutons utilise une architecture MVC.
 * 
 * @author benoit
 * @version 0.0.1
 */
public class Controleur extends JPanel {

	private JButton push, add, sub, mul, div, clear;
	private PileModele<Integer> pile;
	private JTextField donnee;

	public Controleur(PileModele<Integer> pile) {
		super();
		this.pile = pile;
		this.donnee = new JTextField(8);

		this.push = new JButton("push");
		this.add = new JButton("+");
		this.sub = new JButton("-");
		this.mul = new JButton("*");
		this.div = new JButton("/");
		this.clear = new JButton("[]");

		// Désactivation des boutons de calcul dans le constructeur. Manière élégante de
		// garantir que le fonctionnement de la pile ne sera pas altéré si invocation
		// d'un opérateur sur une unique opérande

		add.setEnabled(false);
		sub.setEnabled(false);
		mul.setEnabled(false);
		div.setEnabled(false);
		clear.setEnabled(false);

		setLayout(new GridLayout(2, 1));
		add(donnee);

		donnee.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

			}

		});
		JPanel boutons = new JPanel();
		boutons.setLayout(new FlowLayout());
		boutons.add(push);

		// Les components Swing sont eux meme implementé selon l'architecture MVC. Ici,
		// création d'un écouteur anonyme sur le bouton push et implementant la méthode
		// actionPerformed().

		push.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					pile.empiler(operande());

				} catch (NumberFormatException e1) {

					// Not a Number est sans effet...

				} catch (PilePleineException e1) {

				}

				// Apres la commande push, il existe au moins un élément sur la pile donc
				// réactivation du bouton clear.

				if (pile.taille() >= 1) {

					clear.setEnabled(true);

				}

				// Il faut au moins 2 opérandes pour effectuer un calcul.

				if (pile.taille() >= 2) {

					add.setEnabled(true);
					sub.setEnabled(true);
					mul.setEnabled(true);
					div.setEnabled(true);

				}

			}

		});
		boutons.add(add);

		add.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int resultat = 0;

				try {

					int resultat1 = pile.depiler();

					int resultat2 = pile.depiler();

					resultat = resultat2 + resultat1;

					try {

						pile.empiler(resultat);

					} catch (PilePleineException e1) {

						e1.printStackTrace();
					}

				} catch (PileVideException e1) {

				}

				// Bien evidemment, les boutons sont de nouveaux désactivé si la pile possède
				// moins de 2 éléments.

				if (pile.taille() < 2) {

					add.setEnabled(false);
					sub.setEnabled(false);
					mul.setEnabled(false);
					div.setEnabled(false);

				}

			}

		});
		boutons.add(sub);

		sub.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int resultat = 0;

				try {

					int resultat1 = pile.depiler();

					int resultat2 = pile.depiler();

					resultat = resultat2 - resultat1;

					try {

						pile.empiler(resultat);

					} catch (PilePleineException e1) {

						e1.printStackTrace();
					}

				} catch (PileVideException e1) {

				}

				if (pile.taille() < 2) {

					add.setEnabled(false);
					sub.setEnabled(false);
					mul.setEnabled(false);
					div.setEnabled(false);

				}

			}

		});
		boutons.add(mul);

		mul.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int resultat = 0;

				try {

					int resultat1 = pile.depiler();

					int resultat2 = pile.depiler();

					resultat = resultat2 * resultat1;

					try {

						pile.empiler(resultat);

					} catch (PilePleineException e1) {

						e1.printStackTrace();
					}

				} catch (PileVideException e1) {

				}

				if (pile.taille() < 2) {

					add.setEnabled(false);
					sub.setEnabled(false);
					mul.setEnabled(false);
					div.setEnabled(false);

				}

			}

		});
		boutons.add(div);
		div.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int resultat = 0;

				int resultat1 = 0;

				int resultat2 = 0;

				try {

					resultat1 = pile.depiler();

					resultat2 = pile.depiler();

					resultat = resultat2 / resultat1;

					try {
						pile.empiler(resultat);

					} catch (PilePleineException e1) {

						e1.printStackTrace();
					}
					// Si division par zero, on empile empile de nouveaux les données dépilées.
				} catch (ArithmeticException a) {

					try {
						pile.empiler(resultat2);

						pile.empiler(resultat1);

					} catch (PilePleineException e1) {

						e1.printStackTrace();
					}

				} catch (PileVideException e1) {

				}

				if (pile.taille() < 2) {

					add.setEnabled(false);
					sub.setEnabled(false);
					mul.setEnabled(false);
					div.setEnabled(false);

				}
			}
		});
		boutons.add(clear);
		clear.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean test = true;

				// On boucle jusqu'a ce que pile.depiler() soit null.

				while (test == true) {

					try {

						pile.depiler();

					} catch (PileVideException e1) {

						test = false;

					}
				}

				// On désactive de nouveaux l'ensemble des boutons.

				add.setEnabled(false);
				sub.setEnabled(false);
				mul.setEnabled(false);
				div.setEnabled(false);
				clear.setEnabled(false);

			}

		});
		add(boutons);
		boutons.setBackground(Color.red);
		actualiserInterface();
	}

	// Actualiser l'interface équivaut à ce que cette dernière se redéssine.
	public void actualiserInterface() {
		this.repaint();
	}

	private Integer operande() throws NumberFormatException {
		return Integer.parseInt(donnee.getText());
	}

}
