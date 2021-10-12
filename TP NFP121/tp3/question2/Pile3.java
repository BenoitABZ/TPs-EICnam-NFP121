package question2;

import java.util.Iterator;
import java.util.Vector;

import question1.PilePleineException;
import question1.PileVideException;

/**
 * D�crivez votre classe PileVector ici.
 * 
 * @author (votre nom)
 * @version (un num�ro de version ou une date)
 */
public class Pile3 implements PileI {

	private Vector<Object> v;

	public Pile3() {
		this(PileI.CAPACITE_PAR_DEFAUT );
	}

	public Pile3(int taille) {
		if (taille < 0)
			taille = PileI.CAPACITE_PAR_DEFAUT;
		this.v = new Vector<Object>(taille);
	}

	public void empiler(Object o) throws PilePleineException {
		if (estPleine())
			throw new PilePleineException();

		this.v.add(o);		
		;
	}

	public Object depiler() throws PileVideException {
		if (estVide())
			throw new PileVideException();
		
		Object o = this.sommet();

		this.v.remove(this.v.lastElement());
		
		return o;
	}

	public Object sommet() throws PileVideException {
		return this.v.lastElement();
	}

	public int taille() {
		return this.v.size();
	}

	public int capacite() {
		return this.v.capacity();
	}

	public boolean estVide() {
		return this.v.isEmpty();
	}

	public boolean estPleine() {
		return this.v.capacity() <= this.v.size();
	}

	public String toString() {
		
		if (estVide()) {
			
			return "[]";
		}
		
	String s = "[";
		
		String s1=null;
		
		Iterator<Object> iter = this.v.iterator();

		int count = 0;

		while (iter.hasNext()) {

			Object o = iter.next();

			if (count == 0) {

				 s1 = o.toString();
			} else {

				s1 = o + ", " + s1 ;
			}
			
			count++;
		}
		return s + s1 +"]";
	}

	public boolean equals(Object o) {
		if (o instanceof Pile3) {
			Pile3 p = (Pile3) o;
			
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

}
