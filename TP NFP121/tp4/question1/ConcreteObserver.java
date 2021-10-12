package question1;

import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

/**
 * Implementation de la classe ConcreteObserver. 
 * 
 * Cette derniere implememente la classe Observer du package java.util, notamment la methode update().
 * 
 * @author benoit
 * @version 0.0.1
 */
public class ConcreteObserver implements Observer {

	private Stack<Observable> senders;
	private Stack<Object> arguments;

	public ConcreteObserver() {
		senders = new Stack<Observable>();
		arguments = new Stack<Object>();
	}

	/**
	 * impl�mentation de la seule m�thode de l'interface java.util.Observer �
	 * chaque ex�cution de cette m�thode, celle-ci se contente d'empiler les
	 * param�tres transmis observable et arg respectivement dans 2 piles senders
	 * et arguments
	 */
	public void update(Observable observable, Object arg) {
		senders.push(observable);
		arguments.push(arg);
	}

	public Stack<Observable> senders() {
		return senders;
	}

	public Stack<Object> arguments() {
		return arguments;
	}
	
}
