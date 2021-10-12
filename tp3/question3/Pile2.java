package question3;

import question1.PilePleineException;
import question1.PileVideException;
import question2.PileI;

import java.util.Iterator;
import java.util.Stack;

public class Pile2<T> implements question3.PileI<T>{
    /** par d�l�gation : utilisation de la class Stack */
    private Stack<T> stk;
    /** la capacit� de la pile */
    private int capacite;

    /** Cr�ation d'une pile.
     * @param taille la "taille maximale" de la pile, doit �tre > 0
     */
    public Pile2(int taille){
    	if (taille < 0)
			taille = PileI.CAPACITE_PAR_DEFAUT;
		this.capacite = taille;
		this.stk = new Stack<T>();
    }

    public Pile2(){
    	this(PileI.CAPACITE_PAR_DEFAUT );
    }

    @Override
    public void empiler(T o) throws PilePleineException{
    	if (estPleine())
			throw new PilePleineException();

		this.stk.push(o);
    }

    @Override
    public T depiler() throws PileVideException{
    	if (estVide())
			throw new PileVideException();

		return this.stk.pop();
    }

    @Override
    public T sommet() throws PileVideException{
    	return this.stk.peek();
    }

	@Override
	public int capacite() {
		return this.capacite;
		
	}

	@Override
	public int taille() {
		return this.stk.size();
	}

	@Override
	public boolean estVide() {
		return this.stk.empty();
	}

	@Override
	public boolean estPleine() {
		return this.stk.size() >= capacite;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Pile2) {
			@SuppressWarnings("unchecked")
			Pile2<T> p = (Pile2<T>) o;

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
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
public String toString() {
		
		if (estVide()) {
			
			return "[]";
		}
		
		String s = "[";

		String s1 = null;

		Iterator<T> iter = this.stk.iterator();

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

    // recopier ici toutes les autres m�thodes
    // qui ne sont pas modifi�es en fonction
    // du type des �l�ments de la pile

} // Pile2