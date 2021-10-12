package question2;

import question1.PilePleineException;
import question1.PileVideException;

public class Pile4 implements PileI, Cloneable {
	/** la liste des Maillons/Elements */
	private Maillon stk;
	/** la capacit� de la pile */
	private int capacite;
	/** le nombre */
	private int nombre;

	/**
	 * Classe interne "statique" contenant chaque �l�ment de la chaine c'est une
	 * proposition, vous pouvez l'ignorer !
	 */
	private static class Maillon implements Cloneable {
		private Object element;
		private Maillon suivant;

		public Maillon(Object element, Maillon suivant) {
			this.element = element;
			this.suivant = suivant;
		}

		public Maillon suivant() {
			return this.suivant;
		}

		public Object element() {
			return this.element;
		}

		public Object clone() throws CloneNotSupportedException {
			Maillon m = (Maillon) super.clone();
			m.element = element;
			return m;
		}
	}

	/**
	 * Cr�ation d'une pile.
	 * 
	 * @param taille la taille de la pile, la taille doit �tre > 0
	 */
	public Pile4(int taille) {
		if (taille <= 0)
			taille = PileI.CAPACITE_PAR_DEFAUT;
		this.stk = null;
		this.capacite = taille;
		this.nombre = 0;
	}

	public Pile4() {
		this(PileI.CAPACITE_PAR_DEFAUT);
	}

	public void empiler(Object o) throws PilePleineException {
		if (estPleine())
			throw new PilePleineException();

		if (estVide()) {

			this.stk = new Maillon(o, null);

			nombre++;
		} else {

			try {

				this.stk = new Maillon(o, (Maillon) this.stk.clone());

			} catch (CloneNotSupportedException e) {

				e.printStackTrace();
			}

			nombre++;

		}
	}

	public Object depiler() throws PileVideException {
		if (estVide())
			throw new PileVideException();

		Object current = this.stk.element();
		
		if (this.taille()==1) {
			
			this.stk = new Maillon(null, null);
			
			nombre--;
			
			return current;
						
		}

		Maillon suivant = this.stk.suivant();

		this.stk = new Maillon(suivant.element(), suivant.suivant());
			
		nombre--;

		return current;

	}

	public Object sommet() throws PileVideException {
		if (estVide())
			throw new PileVideException();

		return this.stk.element();
	}

	/**
	 * Effectue un test de l'�tat de la pile.
	 * 
	 * @return vrai si la pile est vide, faux autrement
	 */
	public boolean estVide() {
		return this.nombre == 0; // � compl�ter
	}

	/**
	 * Effectue un test de l'�tat de la pile.
	 * 
	 * @return vrai si la pile est pleine, faux autrement
	 */
	public boolean estPleine() {
		return this.nombre >= capacite; // � compl�ter
	}

	/**
	 * Retourne une repr�sentation en String d'une pile, contenant la repr�sentation
	 * en String de chaque �l�ment.
	 * 
	 * @return une repr�sentation en String d'une pile
	 */
	public String toString() {

		if (!estVide()) {

			String s = "[";

			Maillon m = this.stk;

			s = s + m.element();

			while (m.suivant() != null) {

				m = m.suivant();

				s = s + ", " + m.element();

			}
			return s + "]";

		} else {

			return "[]";
		}
	}

	public int capacite() {
		return this.capacite;
	}

	public boolean equals(Object o) {
		if (o instanceof Pile4) {
			Pile4 p = (Pile4) o;

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

	// fonction fournie
	public int hashCode() {
		return toString().hashCode();
	}

	public int taille() {
		return this.nombre;
	}
}