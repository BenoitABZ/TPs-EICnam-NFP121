package question3;

import java.util.Observable;

import question3.tp3.PileI;
import question3.tp3.PileVideException;
import question3.tp3.PilePleineException;

/**
 * MVC : le Modèle est une pile (classe PileModele<T>). Il previent tous ses observateurs en cas de changement d'état
 * 
 * Dans le cadre du pattern Observateur/Observé, il héritera donc de la classe Observable.
 * 
 * Les fonctionalités modifiant la pile (empiler() et depiler()) devront notifier la vue systématiquement via la méthode notifyObserver().
 * 
 * @author benoit
 * @version 0.0.1
 */

public class PileModele<T> extends Observable implements PileI<T> {

	private PileI<T> pile;

	public PileModele(PileI<T> pile) {
		this.pile = pile;
	}

	public void empiler(T o) throws PilePleineException {
		pile.empiler(o);
		setChanged();
		notifyObservers(o);
	}

	public T depiler() throws PileVideException {
		T t = pile.depiler();
		setChanged();
		notifyObservers();
		
		return t;

	}

	public T sommet() throws PileVideException {
		return pile.sommet();
	}

	public int taille() {
		return pile.taille();
	}

	public int capacite() {
		return pile.capacite();
	}

	public boolean estVide() {
		return pile.estVide();
	}

	public boolean estPleine() {
		return pile.estPleine();
	}

	public String toString() {
		return pile.toString();
	}
}

/**
 * notez qu'un message d'alerte subsiste � la compilation (unsafe ...) d� �
 * l'emploi de notifyObservers, incontournable et sans cons�quence ici
 */
