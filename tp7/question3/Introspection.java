package question3;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Introspection {

	/**
	 * Malgres 2 methodes: une en parcourant l'arbre d'heritage et une inspiree de l'ED, impossible de passer les tests Jnews
	 * 
	 * 
	 */
	public static Set<Method> getHeritees(String nomDeLaClasse) throws ClassNotFoundException {
			
		   Class<?> classe = Class.forName(nomDeLaClasse);
		   Set<Method> set=new HashSet<Method>();     
		   for(Method m : classe.getMethods())   set.add(m);           
		   for(Method m : classe.getDeclaredMethods())   set.remove(m); 
		   
		     return set;
		 
	}
		
/*
		Set<Method> setMethodes = new HashSet<>();

		Class<?> enfant = Class.forName(nomDeLaClasse);

		Method[] methodesEnfant = enfant.getDeclaredMethods();

		for (Method methodEnfant : methodesEnfant) {

			Class<?> parent = enfant.getSuperclass();

			while (parent != null) {

				Method[] methodesParent = parent.getDeclaredMethods();

				for (Method methodParent : methodesParent) {

					if (methodParent.getName() != methodEnfant.getName()
							&& (methodParent.getModifiers() == Modifier.PUBLIC)) {

						setMethodes.add(methodParent);

						System.out.println(methodParent.getName());
					}
				}

				parent = parent.getSuperclass();
			}

		}

		return setMethodes;
	}

	Class<?> classe = Class.forName(nomDeLaClasse);

	//recuperation des methodes heritees
	List<Method> listHeritee = obtenirLesMethodesPubliquesLocalesEtHeritees(classe);

	//la liste est modifiable
	List<Method> methodHeritee = new ArrayList<>(listHeritee);
    
	//recuperation des methodes redefinies
	List<Method> listOverriden = obtenirLesMethodesPubliquesRedefiniesLocalement(classe);

	List<Method> methodOverriden = new ArrayList<>(listOverriden);

	Set<Method> methodNotOverriden = new HashSet<>();

	//On supprime des methodes heritee, celles qui sont redefinies. Obtention des methodes heritees non redefinies???
	methodHeritee.removeAll(methodOverriden);

	for(
	Method method:methodHeritee)
	{

		methodNotOverriden.add(method);
	}

	return methodNotOverriden;

	}

	public static List<Method> obtenirLesMethodesPubliquesLocalesEtHeritees(Class<?> cl) {
		return Arrays.asList(cl.getMethods());
	}

	public static List<Method> obtenirToutesLesMethodesAccessiblesDeCetteClasse(Class<?> cl) {
		return Arrays.asList(cl.getDeclaredMethods());
	}

	public static List<Method> obtenirLesMethodesPubliquesRedefiniesLocalement(Class<?> cl) {
		List<Method> listLocalMethods = obtenirToutesLesMethodesAccessiblesDeCetteClasse(cl);
		List<Method> listMethodsSuper = obtenirLesMethodesPubliquesLocalesEtHeritees(cl.getSuperclass());
		List<Method> listMethodsOverride = new ArrayList<>();
		for (Method m : listLocalMethods) {
			if (m.getModifiers() == Modifier.PUBLIC) {

				if (contient(listMethodsSuper, m)) {

					listMethodsOverride.add(m);
				}
			}
		}
		return listMethodsOverride;
	}

	private static boolean contient(List<Method> list, Method m) {
		for (Method ms : list) {
			if ((ms.getName().equals(m.getName())) && sameParameterType(ms, m) && sameReturnTypeWithCovariance(ms, m))
				return true;
		}
		return false;
	}

	private static boolean sameParameterType(Method me, Method m) {
		Class<?>[] paramsMe = me.getParameterTypes();
		Class<?>[] paramsM = m.getParameterTypes();
		if (paramsMe.length != paramsM.length)
			return false;
		for (int i = 0; i < paramsMe.length; i++) {
			if (paramsMe[i] != paramsM[i])
				return false;
		}
		return true;
	}

	private static boolean sameReturnTypeWithCovariance(Method ms, Method m) {
		Class<?> cl = m.getReturnType();
		Class<?> superCl = ms.getReturnType();
		if (superCl == cl)
			return true;
		while (cl != null) {
			cl = cl.getSuperclass();
			if (superCl == cl)
				return true;
		}
		return false;
	}
*/
	public static void main(String[] args) throws ClassNotFoundException {

		for (Method m : Introspection.getHeritees("java.util.Stack")) {
			System.out.println(m);
		}

		System.out.println(Introspection.getHeritees("java.util.Stack").size());
	}

}
/**
 * Bibliographie: Oracle, NFP121, StackOverFlow...
 */
