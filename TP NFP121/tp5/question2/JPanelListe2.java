package question2;

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class JPanelListe2 extends JPanel implements ActionListener, ItemListener {

	private JPanel cmd = new JPanel();
	private JLabel afficheur = new JLabel();
	private JTextField saisie = new JTextField();

	private JPanel panelBoutons = new JPanel();
	private JButton boutonRechercher = new JButton("rechercher");
	private JButton boutonRetirer = new JButton("retirer");

	private CheckboxGroup mode = new CheckboxGroup();
	private Checkbox ordreCroissant = new Checkbox("croissant", mode, false);
	private Checkbox ordreDecroissant = new Checkbox("d�croissant", mode, false);

	private JButton boutonOccurrences = new JButton("occurrence");

	private JButton boutonAnnuler = new JButton("annuler");

	private TextArea texte = new TextArea();

	private List<String> liste;
	private Map<String, Integer> occurrences;
	// Ajout de la stack
	private Stack<Memento> stack;

	public JPanelListe2(List<String> liste, Map<String, Integer> occurrences) {
		this.liste = liste;
		this.occurrences = occurrences;
		this.stack = new Stack<Memento>();

		cmd.setLayout(new GridLayout(3, 1));

		cmd.add(afficheur);
		cmd.add(saisie);

		panelBoutons.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelBoutons.add(boutonRechercher);
		panelBoutons.add(boutonRetirer);
		panelBoutons.add(new JLabel("tri du texte :"));
		panelBoutons.add(ordreCroissant);
		panelBoutons.add(ordreDecroissant);
		panelBoutons.add(boutonOccurrences);
		panelBoutons.add(boutonAnnuler);
		cmd.add(panelBoutons);

		if (liste != null && occurrences != null) {
			afficheur.setText(liste.getClass().getName() + " et " + occurrences.getClass().getName());
			texte.setText(liste.toString());
		} else {
			texte.setText("la classe Chapitre2CoreJava semble incompl�te");
		}

		setLayout(new BorderLayout());

		add(cmd, "North");
		add(texte, "Center");

		// Ajout des observateurs implémentant les classes ActionListener (boutons) et
		// ItemListener (checkBox).
		boutonAnnuler.addActionListener(this);
		boutonRechercher.addActionListener(this);
		boutonOccurrences.addActionListener(this);
		boutonRetirer.addActionListener(this);
		saisie.addActionListener(this);
		ordreCroissant.addItemListener(this);
		ordreDecroissant.addItemListener(this);

	}

	// Classe interne implémentant la méthode compare() de l'interface
	// Comparator<String>.
	private class ReverseComparator implements Comparator<String> {

		// Inversion des arguments pour avoir un ordre alphabétique descendant.
		@Override
		public int compare(String arg0, String arg1) {

			return arg1.compareTo(arg0);

		}

	}

	private class NormalComparator implements Comparator<String> {

		// Inversion des arguments pour avoir un ordre alphabétique descendant.
		@Override
		public int compare(String arg0, String arg1) {

			return arg0.compareTo(arg1);

		}

	}

	// Implémentation du patron Memento afin de sauvegarder l'état de la liste et de
	// la map.
	// Originator = this.
	public Memento saveToMemento() {

		HashMap<String, Integer> mapCopy = new HashMap<>();
		mapCopy.putAll(occurrences);

		List<String> listCopy = new LinkedList<String>();
		listCopy.addAll(liste);

		return new Memento(mapCopy, listCopy);
	}

	public void restoreFromMemento(Memento memento) {
		this.liste = memento.getList();
		this.occurrences = memento.getMap();
	}

	// La classe Memento responsable de la sauvegarde!
	public class Memento {
		private final Map<String, Integer> map;
		private final List<String> list;

		public Memento(Map<String, Integer> map, List<String> list) {
			this.map = map;
			this.list = list;
		}

		public Map<String, Integer> getMap() {
			return map;
		}

		public List<String> getList() {
			return list;
		}
	}

	public void actionPerformed(ActionEvent ae) {

		try {
			boolean res = false;
			if (ae.getSource() == boutonRechercher || ae.getSource() == saisie) {
				res = liste.contains(saisie.getText());
				Integer occur = occurrences.get(saisie.getText());
				afficheur.setText("résultat de la recherche de : " + saisie.getText() + " -->  " + res);
			} else if (ae.getSource() == boutonRetirer) {

				res = retirerDeLaListeTousLesElementsCommencantPar(saisie.getText());
				afficheur.setText("résultat du retrait de tous les éléments commençant par -->  " + saisie.getText()
						+ " : " + res);
			} else if (ae.getSource() == boutonOccurrences) {
				Integer occur = occurrences.get(saisie.getText());
				if (occur != null)
					afficheur.setText(" -->  " + occur + " occurrence(s)");
				else
					afficheur.setText(" -->  ??? ");

				// Prise en compte du cas où le boutonAnnuler a généré l'évènement.
			} else if (ae.getSource() == boutonAnnuler) {

				// On restore le dernier état de nos objets occurrences et liste.
				if (!stack.empty()) {
					Memento memento = stack.pop();

					restoreFromMemento(memento);
				}
			}
			texte.setText(liste.toString());

		} catch (Exception e) {
			afficheur.setText(e.toString());
		}
	}

	public void itemStateChanged(ItemEvent ie) {

		if (ie.getSource() == ordreCroissant) {

			// On sauvegarde l'etat actuel des objets occurrences et liste à chaque fois
			// qu'un observateur est invoqué.

			stack.push(saveToMemento());

			// Trie par l'ordre naturel des éléments contenu dans la liste -- chaine de
			// caractère = ordre alphabétique
			Collections.sort(liste, new NormalComparator());

		}

		else if (ie.getSource() == ordreDecroissant) {

			stack.push(saveToMemento());

			// La méthode sort() prend ici en second argument, un objet de type
			// Comparator<T> (implémentation ci-dessus).
			Collections.sort(liste, new ReverseComparator());

		}

		texte.setText(liste.toString());

	}

	private boolean retirerDeLaListeTousLesElementsCommencantPar(String prefixe) {

		if (prefixe == null) {

			return false;
		}

		boolean resultat = false;

		Iterator<String> iter = liste.iterator();

		int count = 0;

		while (iter.hasNext()) {

			String s = iter.next();

			// Si un Item est préfixé par la chaine de caractère en paramètre.
			if (s.startsWith(prefixe)) {

				if (count == 0) {

					stack.push(saveToMemento());
				}

				count++;

				iter.remove();

				// Décrémenente value si la clé est présente - expression lamda.
				occurrences.computeIfPresent(s, (k, v) -> v - 1);

				if (occurrences.get(s) == 0) {

					resultat = true;
				}
			}
		}

		return resultat;
	}

}