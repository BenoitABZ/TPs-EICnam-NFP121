package question1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * Gestion par introspection des m�thodes de la classe java.lang.Math,<br>
 * Seules sont conserv�es :les m�thodes retournant un double et d'arit� 1 ou 2
 * <p>
 * Note : Emploi du Pattern Singleton pour cette table, accessible uniquement en
 * lecture par des accesseurs
 * <p>
 * La convention de nommage est la suivante : le "Nom" de la fonction suivi de
 * "(double)" exemple : "sqrt(double)" ou le "Nom" de la fonction suivi de
 * "(double, double)" exemple : "IEEEremainder(double, double)"
 */

final public class TableMethodesJavaLangMath {
	/** Singleton */
	//utilisation du patron singleton pour la generation d'une instance unique de TableMethodesJavaLangMath - stateless
	private static TableMethodesJavaLangMath instanceUnique = null;

	public static TableMethodesJavaLangMath getInstance() {
		synchronized (TableMethodesJavaLangMath.class) {
			if (instanceUnique == null) {
				instanceUnique = new TableMethodesJavaLangMath();
			}
			return instanceUnique;
		}
	}

	private TableMethodesJavaLangMath() {
	}
	// fin du Singleton

	/**
	 * @param nomDeLaM�thode Nom de la fonction + "(double)" ou "(double, double)"
	 * @return true si la fonction est pr�sente
	 */
	public boolean cetteMethodeEstPresente(String nomDeLaMethode) {

		//utilisation de containkey() sur la map afin de verifier la presence de la cle, representant le nom de la methode(String) 
		if (tableDesMethodes.containsKey(nomDeLaMethode)) {

			return true;
		} else {

			return false;

		}
	}

	/**
	 * @param nomDeLaM�thode Nom de la fonction + "(double)" ou "(double, double)"
	 * @return true si la fonction est binaire, d'arit� 2
	 * @throws NoSuchElementException si la m�thode demand�e n'existe pas
	 */
	public boolean cetteMethodeAttendDeuxParametres(String nomDeLaMethode) {
        
		//on teste si la methode existe
		if (cetteMethodeEstPresente(nomDeLaMethode)) {
            
			//renvoie la methode (type Method) de la map
			Method method = tableDesMethodes.get(nomDeLaMethode);
            
			//si la methode a 2 parametres (double, double)
			if (method.getParameterCount() == 2) {

				return true;

			} else {

				return false;
			}
		} else {
            
			//sinon on throw une NSEE
			throw new NoSuchElementException();
		}
	}

	/**
	 * @param nomDeLaM�thode Nom de la fonction + "(double)" ou "(double, double)"
	 * @return true si la fonction est unaire, d'arit� 1
	 * @throws NoSuchElementException si la m�thode demand�e n'existe pas
	 */
	public boolean cetteMethodeAttendUnSeulParametre(String nomDeLaMethode) {

		if (cetteMethodeEstPresente(nomDeLaMethode)) {

			Method method = tableDesMethodes.get(nomDeLaMethode);
			
			//si la methode a 1 parametre (double)
			if (method.getParameterCount() == 1) {

				return true;

			} else {

				return false;
			}
		} else {

			throw new NoSuchElementException();
		}
	}

	/**
	 * Obtention de la liste ordonn�e des m�thodes
	 * 
	 * @return la liste tri�e des fonctions issues de java.lang.Math
	 */
	public String[] listeDesMethodes() {
         
		//recuperation du descripteur de classe de java.lang.Maths
		Class<?> classMaths = Math.class;

		List<String> tabMethods = new ArrayList<>();

		//iteration sur l'ensemble des methodes de la classe Math
		for (Method method : classMaths.getMethods()) {
             
			//prise en compte de ce que j'estime etre une difference de version du JDK entre mon poste et le serveur Jnews
			if (!method.getName().contains("fma")) {

				//recuperation du type des parametres de la methode
				Class<?>[] parametres = method.getParameterTypes();
				
                //verification de la pluralité des parametres, que ces derniers soient du type "double" et que le type de retour soit "double" egalement
				if (method.getParameterCount() > 0 && method.getReturnType().getName() == "double") {

					if (parametres[0].getName() == "double") {

						if (method.getParameterCount() == 1) {

							String methodName = method.getName() + "(";

							methodName += parametres[0].getName() + ")";

							tabMethods.add(methodName);

							System.out.println(methodName);

						} else {

							if (parametres[0].getName() == "double" && parametres[1].getName() == "double") {

								String methodName = method.getName() + "(";

								methodName += parametres[0].getName() + ", " + parametres[1].getName() + ")";

								tabMethods.add(methodName);

								System.out.println(methodName);
							}
						}
					}
				}
			}
		}

        //trie du tableau
		Collections.sort(tabMethods);

		//fonctionnel pour conversion en tableau
		String[] strings = tabMethods.stream().toArray(String[]::new);

		return strings;
	}

	/**
	 * Invocation d'une m�thode de la table
	 * 
	 * @param nomDeLaM�thode Nom de la fonction + "(double)"
	 * @param arg1           l'op�rande
	 * @return un r�sultat
	 * @throws NoSuchElementException si la m�thode demand�e n'existe pas ou une
	 *                                exception lev�e par la fonction appel�e
	 */
	public double invoquer(String nomDeLaMethode, double arg1) throws Exception {

		double res;

		try {

			Method fonction = tableDesMethodes.get(nomDeLaMethode);
            
			//cast en (double)
			//la methode invoke prend "null" en parametre car methode static
			res = (double) fonction.invoke(null, arg1);

		} catch (Exception e) {

			throw new NoSuchElementException();
		}

		return res;
	}

	/**
	 * Invocation d'une m�thode de la table
	 * 
	 * @param nomDeLaM�thode Nom de la fonction + "(double, double)"
	 * @param arg1           l'op�rande
	 * @return un r�sultat
	 * @throws NoSuchElementException si la m�thode demand�e n'existe pas ou une
	 *                                exception lev�e par la fonction appel�e
	 */
	public double invoquer(String nomDeLaMethode, double arg1, double arg2) throws Exception {

		double res;

		try {

			Method fonction = tableDesMethodes.get(nomDeLaMethode);

			Object[] args = new Object[] { arg1, arg2 };

			res = (double) fonction.invoke(null, args);

		} catch (Exception e) {

			throw new NoSuchElementException();
		}

		return res;
	}

	/**
	 * Le dictionnaire contient la liste des m�thodes disponibles. un choix de
	 * dictionnaire pourrait �tre pour la Cl� une String soit le Nom de la fonction
	 * + "(double)" ou "(double, double)".<br>
	 * et en Valeur = la Method correspondante. ou tout autre choix
	 */
	private static Map<String, Method> tableDesMethodes = new HashMap<>();

	/** bloc statique d'intialisation de la table des m�thodes */
	static {
         
		//methode naive, on aurait pu se servir de listeDesMethodes()
		Class<?> classMaths = Math.class;

		for (Method method : classMaths.getMethods()) {

			if (!method.getName().contains("fma")) {

				Class<?>[] parametres = method.getParameterTypes();

				if (method.getParameterCount() > 0 && method.getReturnType().getName() == "double") {

					if (parametres[0].getName() == "double") {

						if (method.getParameterCount() == 1) {

							String methodName = method.getName() + "(";

							methodName += parametres[0].getName() + ")";
                              
							//utilisation de la methode put pour inserer la cle/valeur
							tableDesMethodes.put(methodName, method);

						} else {

							if (parametres[0].getName() == "double" && parametres[1].getName() == "double") {

								String methodName = method.getName() + "(";

								methodName += parametres[0].getName() + ", " + parametres[1].getName() + ")";

								tableDesMethodes.put(methodName, method);
							}

						}

					}

				}

			}

		}
		
	}

}
