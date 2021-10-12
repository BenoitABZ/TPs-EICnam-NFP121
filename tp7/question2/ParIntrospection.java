package question2;

import java.lang.reflect.Method;
import java.util.NoSuchElementException;

public class ParIntrospection {

	/**
	 * Cette methode permet de relier par introspection, un observable et un observateur
	 * 
	 * Appel par introspection de la methode observable.addObserver(observer)
	 *
	 * @param observable une instance de la classe java.util.Observable ou l'une de ses derivees
	 * @param observer une implementation de l'interface java.util.Observer
	 * @throws NoSuchElementException en cas d'erreur
	 */
	public static void lierObservableEtObserver(Object observable, Object observer) throws Exception {

		try {

			Class<?> observableClass = observable.getClass();

			Class<?> observerClass = observer.getClass();
			
            //recuperation du descripteur de class de l'interface de l'observer (Observer)
			Class<?> interfaceObserver = observerClass.getInterfaces()[0];
            
			//car l'interface est le parametre de la methode addObserver
			Method addObserver = observableClass.getMethod("addObserver", interfaceObserver);

            //liaison de observable/observateur
			addObserver.invoke(observable, observer);

		} catch (Exception e) {

			throw new NoSuchElementException();
		}
	}

	/**
	 * Cette methode permet de delier par introspection, un observable et un
	 * observateur
	 * <p>
	 * Appel par introspection de la methode observable.deleteObserver(observer)
	 * 
	 * @param observable une instance de la classe java.util.Observable ou l'une de
	 *                   ses derivees
	 * @param observer   une implementation de l'interface java.util.Observer
	 * @throws NoSuchElementException en cas d'erreur
	 */
	public static void delierObservableEtObserver(Object observable, Object observer) throws Exception {

		try {

			Class<?> observableClass = observable.getClass();

			Class<?> observerClass = observer.getClass();

			Class<?> interfaceObserver = observerClass.getInterfaces()[0];

			Method deleteObserver = observableClass.getMethod("deleteObserver", interfaceObserver);

			deleteObserver.invoke(observable, observer);

		} catch (Exception e) {

			throw new NoSuchElementException();
		}
	}

	/**
	 * Cette methode permet de relier par introspection, une source et un "listener"
	 * <p>
	 * Appel par introspection de la methode source.addXXXXListener(listener)
	 * 
	 * @param source est une instance
	 * @param listener une implementation d'une interface XXXXListener
	 * @throws NoSuchElementException en cas d'erreur
	 */
	public static void lierSourceEtListener(Object source, Object listener) throws Exception {

		try {

			Class<?> sourceClass = source.getClass();

			Class<?> listenerClass = listener.getClass();

			Class<?> interfaceListener = listenerClass.getInterfaces()[0];
            //construction de la chaine de caractere representant la methode d'ajout de l'observateur en fonction de l'interface implementee
			StringBuilder stringBuff = new StringBuilder("add");

			String methodName = interfaceListener.getName();
            
			//decoupage en tableau pour avoir le dernier element du package
			String[] methodNameTab = methodName.split("\\.");

			methodName = methodNameTab[methodNameTab.length - 1];
            
			//constitution de la methode adequate
			methodName = stringBuff.append(methodName).toString();

			Method addXXXXListener = sourceClass.getMethod(methodName, interfaceListener);

			//liaison du source/listener
			addXXXXListener.invoke(source, listener);

		} catch (Exception e) {

			throw new NoSuchElementException();
		}
	}

	/**
	 * Cette methode permet de delier par introspection, une source et un "listener"
	 * 
	 * Appel par introspection de la methode source.removeXXXXListener(listener)
	 * 
	 * @param source est une instance
	 * @param listener une implementation d'une interface XXXXListener
	 * @throws NoSuchElementException en cas d'erreur
	 */
	public static void delierSourceEtListener(Object source, Object listener) throws Exception {

		try {

			Class<?> sourceClass = source.getClass();

			Class<?> listenerClass = listener.getClass();

			Class<?> interfaceListener = listenerClass.getInterfaces()[0];

			StringBuilder stringBuff = new StringBuilder("remove");

			String methodName = interfaceListener.getName();

			String[] methodNameTab = methodName.split("\\.");

			methodName = methodNameTab[methodNameTab.length - 1];

			methodName = stringBuff.append(methodName).toString();

			Method removeXXXXListener = sourceClass.getMethod(methodName, interfaceListener);

			removeXXXXListener.invoke(source, listener);

		} catch (Exception e) {

			throw new NoSuchElementException();
		}
	}
}
