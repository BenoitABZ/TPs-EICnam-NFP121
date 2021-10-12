package question2;

import question1.PilePleineException;
import question1.PileVideException;

/**
 * A remplacer en partie par votre classe Pile de la question 1.
 * 
 * @author (votre nom)
 * @version (un num�ro de version ou une date)
 */
public class Pile implements PileI {

	private Object[] zone;
	private int ptr;

	public Pile(int taille) {
		if (taille < 0)
			taille = PileI.CAPACITE_PAR_DEFAUT;
		this.zone = new Object[taille];
		this.ptr = 0;
	}

	public Pile() {
		this(PileI.CAPACITE_PAR_DEFAUT);
	}

	public void empiler(Object i) throws PilePleineException {
		if (estPleine())
			throw new PilePleineException();

		this.zone[this.ptr] = i;

		this.ptr++;

	}

	public Object depiler() throws PileVideException {
		if (estVide())
			throw new PileVideException();

		this.ptr--;

		return zone[ptr];

	}

	public boolean estVide() {
		return ptr == 0;
	}

	public boolean estPleine() {
		return ptr >= zone.length;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("[");
		for (int i = ptr - 1; i >= 0; i--) {
			sb.append(zone[i].toString());
			if (i > 0)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}

	public boolean equals(Object o) {
		if (o instanceof Pile) {
			Pile p = (Pile) o;

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

	public Object sommet() throws PileVideException {
		return this.zone[ptr - 1];
	}

	public int capacite() {
		return this.zone.length;
	}

	public int taille() {
		return this.ptr;
	}

	public int hashCode() {
		return toString().hashCode();
	}

}