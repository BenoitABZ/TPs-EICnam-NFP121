package question2;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Iterator;
import java.util.Stack;

public class Pile2 implements PileI {
	/** par d�l�gation : utilisation de la class Stack */
	private Stack<Object> stk;

	/** la capacit� de la pile */
	private int capacite;

	/**
	 * Cr�ation d'une pile.
	 * 
	 * @param taille la taille de la pile, la taille doit �tre > 0
	 */
	public Pile2(int taille) {
		if (taille < 0)
			taille = PileI.CAPACITE_PAR_DEFAUT;
		this.capacite = taille;
		this.stk = new Stack<Object>();

	}

	// constructeur fourni
	public Pile2() {
		this(PileI.CAPACITE_PAR_DEFAUT);
	}

	public void empiler(Object o) throws PilePleineException {
		if (estPleine())
			throw new PilePleineException();

		this.stk.push(o);
	}

	public Object depiler() throws PileVideException {
		if (estVide())
			throw new PileVideException();

		return this.stk.pop();
	}

	public Object sommet() throws PileVideException {
		return this.stk.peek();
	}

	/**
	 * Effectue un test de l'�tat de la pile.
	 * 
	 * @return vrai si la pile est vide, faux autrement
	 */
	public boolean estVide() {
		return this.stk.empty();
	}

	/**
	 * Effectue un test de l'�tat de la pile.
	 * 
	 * @return vrai si la pile est pleine, faux autrement
	 */
	public boolean estPleine() {
		return this.stk.size() >= capacite;
	}

	/**
	 * Retourne une repr�sentation en String d'une pile, contenant la repr�sentation
	 * en String de chaque �l�ment.
	 * 
	 * @return une repr�sentation en String d'une pile
	 */
	public String toString() {
		
		if (estVide()) {
			
			return "[]";
		}
		
		String s = "[";

		String s1 = null;

		Iterator<Object> iter = this.stk.iterator();

		int count = 0;

		while (iter.hasNext()) {

			Object o = iter.next();

			if (count == 0) {

				s1 = o.toString();
			} else {

				s1 = o + ", " + s1;
			}

			count++;
		}
		return s + s1 + "]";
	}

	public boolean equals(Object o) {
		if (o instanceof Pile2) {
			Pile2 p = (Pile2) o;

			try {
				while(this.taille()>0 && p.taille()>0) {
								
					if (!p.depiler().equals(this.depiler())) {

						return false;
					}
				}
				
				return this.capacite() == p.capacite() && this.taille() == p.taille() && this.hashCode() == p.hashCode() && this.toString().equals(p.toString()); 
				
			} catch (PileVideException e) {

				return this.capacite() == p.capacite() && this.taille() == p.taille() && this.hashCode() == p.hashCode() && this.toString().equals(p.toString()); 

			}
		} else
			return false;
	}

	public int hashCode() {
		return toString().hashCode();
	}

	/**
	 * Retourne le nombre d'�l�ment d'une pile.
	 * 
	 * @return le nombre d'�l�ment
	 */
	public int taille() {
		return this.stk.size();
	}

	/**
	 * Retourne la capacit� de cette pile.
	 * 
	 * @return le nombre d'�l�ment
	 */
	public int capacite() {
		return this.capacite;
	}

} // Pile2.java
