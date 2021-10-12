package question1;

import java.util.*;

/**
 * 
 * La classe Ensemble hérite des méthodes de AbstractSet. Cette dernière hérite
 * d'AbstractCollection et implémente Set.
 * 
 * AbstractSet ne redéfinit aucune méthode de sa classe parent mais implémente
 * equals() et hashcode().
 * 
 * L'interface Set est le contrat permettant de garantir l'absence de doublon
 * (ensemble au sens mathématique du terme même si fini...). La méthode add()
 * doit etre implémenté en vertu de la contrainte.
 * 
 * contains(), addAll(), retainAll()... tel qu'implémenté dans
 * AbstractCollection correspond aux spécifications de Set.
 * 
 * Ceci est permis grace aux patrons Iterator et Template method (les classes
 * abstraites utilisent des méthodes implémentées par les classes enfants
 * (iterator() et add()).
 * 
 * @author benoit
 *
 * @param <T>
 */
public class Ensemble<T> extends AbstractSet<T> implements Cloneable {

	protected java.util.Vector<T> table = new java.util.Vector<T>();

	public int size() {
		return table.size();
	}

	public Iterator<T> iterator() {
		return table.iterator();
	}

	public boolean add(T t) {

		  if( ! this.contains(t) )
	            return table.add(t);
	        return false;
	}

	public Ensemble<T> union(Ensemble<? extends T> e) {

		Ensemble<T> eCopy = new Ensemble<T>();

		eCopy.addAll(this);

		eCopy.addAll(e);

		return eCopy;

	}

	public Ensemble<T> inter(Ensemble<? extends T> e) {

		Ensemble<T> eCopy = new Ensemble<T>();

		eCopy.addAll(this);

		eCopy.retainAll(e);

		return eCopy;
	}

	public Ensemble<T> diff(Ensemble<? extends T> e) {

		Ensemble<T> eCopy = new Ensemble<T>();

		eCopy.addAll(this);

		eCopy.removeAll(e);

		return eCopy;
	}

	Ensemble<T> diffSym(Ensemble<? extends T> e) {

		Ensemble<T> eCopy1 = union(e);

		Ensemble<T> eCopy2 = inter(e);

		Ensemble<T> eCopy = (eCopy1).diff(eCopy2);

		return eCopy;
	}

	@Override
	public Object clone() {

		Ensemble<T> eCopy = new Ensemble<T>();

		eCopy.addAll(this);

		return eCopy;

	}

}
