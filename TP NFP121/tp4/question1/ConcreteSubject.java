package question1;

import java.util.Observable;
import java.util.ArrayList;

/**
 * Implementation de la classe ConcreteSubject. 
 * 
 * Cette derniere herite de la classe Observable du package java.util et herite notamment de la methode notifyObservers(Object arg) qui permet de notifier les observateurs enregistrés de toute modification dans la liste. .
 * 
 * @author benoit
 * @version 0.0.1
 */
public class ConcreteSubject extends Observable {

	/** ConcreteSubject est compos� d'une liste list */
	private ArrayList<String> list;

	public ConcreteSubject() {
		list = new ArrayList<String>();
	}

	public void insert(String name) {
		list.add(name);
		setChanged();
		notifyObservers(name);
	}

	public String toString() {
		return list.toString();
	}

}
