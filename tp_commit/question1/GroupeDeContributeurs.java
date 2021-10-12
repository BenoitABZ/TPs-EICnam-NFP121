package question1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Represente le compte d'un groupe de contributeur
 * 
 * Equivalent "Composite" du pattern composite
 * 
 * Implémente l'interface Iterable<Cotisant> --> construction d'un itérateur
 * personnalisé qui parcours Leaf et Composite
 * 
 * @author benoit
 *
 */
public class GroupeDeContributeurs extends Cotisant implements Iterable<Cotisant> {
	private List<Cotisant> liste;

	/**
	 * Instantation de la liste
	 * 
	 * La contrainte one to many est caractérisé par une liste
	 * 
	 * @param nomDuGroupe
	 */
	public GroupeDeContributeurs(String nomDuGroupe) {
		super(nomDuGroupe);
		liste = new ArrayList<>();
	}

	/**
	 * Ajout d'un cotisant dans le Composite
	 * 
	 * Les éléments de ce groupe ont pour parent this
	 * 
	 * @param cotisant
	 */
	public void ajouter(Cotisant cotisant) {

		cotisant.setParent(this);

		liste.add(cotisant);
	}

	/**
	 * Utilisantion de l'iterateur personnalisé pour parcouris le composite en
	 * profondeur
	 *
	 * nombre s'incrémente à chaque découverte d'un Contributeur ou récursivité si Composite
	 */

	public int nombreDeCotisants() {

		int nombre = 0;

		for (Cotisant cot : liste) {

			nombre += cot.nombreDeCotisants();

		}

		return nombre;

	}

	/**
	 * Utilisation de VisiteurToString pour la visite en profondeur
	 *
	 * @return String 
	 * Retourne le return de la méthode visit() relatif à chaque Cotisant
	 *
	 */
	public String toString() {

		String str = new String();

		str = this.accepter(new VisiteurToString());

		return str;
	}

	/**
	 * @return List<Cotisant> Retourne la liste de tous les Cotisant du Composite
	 * 
	 */
	public List<Cotisant> getChildren() {

		return this.liste;

	}

	/**
	 * Débite l'ensemble des contributeurs de ce groupe
	 * 
	 * @param somme
	 */
	public void debit(int somme) throws SoldeDebiteurException {

		if (somme < 0)
			throw new RuntimeException("nombre négatif !!!");

		List<Cotisant> children = this.getChildren();

		for (Cotisant child : children) {


				child.debit(somme);

			

		}
	}

	/**
	 * Crédite l'ensemble des contributeurs de ce groupe
	 * 
	 * @param somme
	 */
	public void credit(int somme) {
		List<Cotisant> children = this.getChildren();

		for (Cotisant child : children) {

			child.credit(somme);

		}
	}

	/**
	 * Calcul le solde l'ensemble des contributeurs de ce groupe
	 * 
	 * @param somme
	 */
	public int solde() {

		int solde = 0;

		List<Cotisant> children = this.getChildren();

		for (Cotisant child : children) {

			solde += child.solde();

		}

		return solde;
	}

	// méthodes fournies

	public Iterator<Cotisant> iterator() {
		return new GroupeIterator(liste.iterator());
	}

	private class GroupeIterator implements Iterator<Cotisant> {
		private Stack<Iterator<Cotisant>> stk;

		public GroupeIterator(Iterator<Cotisant> iterator) {
			this.stk = new Stack<Iterator<Cotisant>>();
			this.stk.push(iterator);
		}

		public boolean hasNext() {
			if (stk.empty()) {
				return false;
			} else {
				Iterator<Cotisant> iterator = stk.peek();
				if (!iterator.hasNext()) {
					stk.pop();
					return hasNext();
				} else {
					return true;
				}
			}
		}

		public Cotisant next() {
			if (hasNext()) {
				Iterator<Cotisant> iterator = stk.peek();
				Cotisant cpt = iterator.next();
				if (cpt instanceof GroupeDeContributeurs) {
					GroupeDeContributeurs gr = (GroupeDeContributeurs) cpt;
					stk.push(gr.liste.iterator());
				}
				return cpt;
			} else {
				throw new NoSuchElementException();
			}
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public <T> T accepter(Visiteur<T> visiteur) {
		return visiteur.visite(this);
	}

}
