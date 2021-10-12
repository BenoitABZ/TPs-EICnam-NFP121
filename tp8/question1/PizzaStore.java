package question1;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * IHM Permettant la mise en oeuvre du pattern Décorateur pour l'élaboration d'une pizza
 * 
 * @author benoit
 *
 */
public class PizzaStore extends JFrame {

	public PizzaStore() {
		super("IHMPizza");
		add(new IHMPizza());
		pack();
		show();
	}

	public static void main(String[] args) {
		new PizzaStore();
	}

	private class IHMPizza extends JPanel {
		private JLabel afficheur = new JLabel(
				" please, first choose your pizza, then the constituents but once clams...");

		private JButton boutonSolo = new JButton("");
		private JButton boutonClassic = new JButton("");
		private JButton boutonCrust = new JButton("");

		private Checkbox parmesan = new Checkbox("parmesan", null, true);
		private Checkbox mozarella = new Checkbox("mozarella", null, false);
		private Checkbox ham = new Checkbox("ham", null, false);
		private Checkbox tomato = new Checkbox("tomato", null, false);
		private Checkbox mushrooms = new Checkbox("mushrooms", null, false);
		private Checkbox clams = new Checkbox("clams", null, false);

		private DecimalFormat df = new DecimalFormat("##.##");
		private JButton boutonMake = new JButton("");

		private Pizza pizza;

		public IHMPizza() {
			JPanel panelGlobal = new JPanel();
			JPanel panelBoutons = new JPanel();
			JPanel panelDecorations = new JPanel();
			JPanel panelMake = new JPanel();

			panelGlobal.setLayout(new GridLayout(3, 1));
			afficheur.setEnabled(false);
			panelGlobal.add(afficheur);

			panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER));
			panelBoutons.add(boutonSolo);
			panelBoutons.add(boutonClassic);
			panelBoutons.add(boutonCrust);

			panelDecorations.setLayout(new GridLayout(1, 6));
			panelDecorations.add(parmesan);
			panelDecorations.add(mozarella);
			panelDecorations.add(ham);
			panelDecorations.add(tomato);
			panelDecorations.add(mushrooms);
			panelDecorations.add(clams);

			panelGlobal.add(panelBoutons);
			panelGlobal.add(panelDecorations);

			setLayout(new BorderLayout());
			add(panelGlobal, "North");

			panelMake.setLayout(new FlowLayout(FlowLayout.CENTER));
			panelMake.add(boutonMake);
			add(panelMake, "Center");
            
			//Les pizzas sont de 3 types (Classic, PizzaSolo ou GenerousCrust) --> concreteComponent
			
			//ajout d'ActionListener sur chacun des 3 boutons permettant la génération d'une pizza en fonction du type et override de la methode actionPerformed()
			
			boutonSolo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					//generation de la pizza solo
					pizza = new PizzaSolo();
					//permet de désactiver la génération d'autres types de pizza
					validerLesDecorations();
				}
			});

			boutonClassic.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					pizza = new Classic();
					validerLesDecorations();
				}
			});

			boutonCrust.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					pizza = new GenerousCrust();
					validerLesDecorations();
				}
			});
			
			//Les pizzas sont décorées par des ingrédients avant d'être confectionnées (MAKING Pizza) et le prix dépend de cette composition.
			
			//ajout d'ItemListener sur chacune des checkbox permettant l'ajout d'ingrédients à une pizza quelque soit le type et override de la méthode itemSetChanged()

			parmesan.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent ie) {
					//si la checkbox est coché
					if (ie.getStateChange() == ItemEvent.SELECTED) {
						//ajout de l'ingredient à la pizza et modification de la description + prix
						pizza = new Parmesan(pizza);
					} else
						//une fois coché, on ne peut revenir en arrière
						//decocher et recocher sont sans effet
						parmesan.setState(true);
					//update de l'afficheur
					afficherLaPizzaEtSonCout();
				}
			});
			mozarella.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent ie) {
					if (ie.getStateChange() == ItemEvent.SELECTED) {
						pizza = new FreshMozarella(pizza);
					} else
						mozarella.setState(true);
					afficherLaPizzaEtSonCout();
				}
			});
			ham.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent ie) {
					if (ie.getStateChange() == ItemEvent.SELECTED) {
						pizza = new Ham(pizza);
					} else
						ham.setState(true);
					afficherLaPizzaEtSonCout();
				}
			});
			tomato.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent ie) {
					if (ie.getStateChange() == ItemEvent.SELECTED) {
						pizza = new Tomato(pizza);
					} else
						tomato.setState(true);
					afficherLaPizzaEtSonCout();
				}
			});
			mushrooms.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent ie) {
					if (ie.getStateChange() == ItemEvent.SELECTED) {
						pizza = new Mushrooms(pizza);
					} else
						tomato.setState(true);
					afficherLaPizzaEtSonCout();
				}
			});
			clams.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent ie) {
					if (ie.getStateChange() == ItemEvent.SELECTED) {
						pizza = new Clams(pizza);
					} else
						clams.setState(true);
					afficherLaPizzaEtSonCout();
				}
			});

			boutonMake.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					invaliderLesDecorations();
					afficheur.setText("<html><FONT Color=red size=5>MAKING of the " + afficheur.getText() + "</html>");
				}
			});

			invaliderLesDecorations();
		}

		private void afficherLaPizzaEtSonCout() {
			afficheur.setText("<html><FONT Color=blue size=4>" + pizza.getDescription()
					+ "</FONT><FONT Color=red size=4> cost: " + df.format(pizza.cost()) + " &#8364;</Font></html>");
		}

		private void invaliderLesDecorations() {
			boutonMake.setEnabled(false);
			boutonMake.setText("<html><font size=3 color=green>compose your pizza ...</font></html>");
			boutonSolo.setEnabled(true);
			boutonSolo.setText("<html><font size=3 color=red>solo</font></html>");
			boutonClassic.setEnabled(true);
			boutonClassic.setText("<html><font size=3 color=red>classic</font></html>");
			boutonCrust.setEnabled(true);
			boutonCrust.setText("<html><font size=3 color=red>crust</font></html>");
			parmesan.setState(false);
			mozarella.setState(false);
			ham.setState(false);
			tomato.setState(false);
			mushrooms.setState(false);
			clams.setState(false);
			parmesan.setEnabled(false);
			mozarella.setEnabled(false);
			ham.setEnabled(false);
			tomato.setEnabled(false);
			mushrooms.setEnabled(false);
			clams.setEnabled(false);
		}

		private void validerLesDecorations() {
			boutonMake.setEnabled(true);
			boutonMake.setText("<html><font size=3 color=red>make the pizza !</font></html>");
			boutonSolo.setEnabled(false);
			boutonSolo.setText("<html><font size=3 color=green>solo</font></html>");
			boutonClassic.setEnabled(false);
			boutonClassic.setText("<html><font size=3 color=green>classic</font></html>");
			boutonCrust.setEnabled(false);
			boutonCrust.setText("<html><font size=3 color=green>crust</font></html>");
			parmesan.setEnabled(true);
			mozarella.setEnabled(true);
			ham.setEnabled(true);
			tomato.setEnabled(true);
			mushrooms.setEnabled(true);
			clams.setEnabled(true);
			afficherLaPizzaEtSonCout();
		}

	}
}
