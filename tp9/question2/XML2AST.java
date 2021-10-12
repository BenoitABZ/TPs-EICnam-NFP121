package question2;

import tp6.question1.Contexte;

import tp6.question3.Instruction;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

/**
 * Décrivez votre classe XML2AST ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class XML2AST {

	static Class parent = null;

	static Class enfant = null;

	public static Instruction xmlInst2ast(Contexte m, Element element) throws Exception {
		
		parent = Class.forName(element.getName());
		
		List<Class> parameterTypes = new ArrayList<>();
		
		for (Object o : element.getChildren()) {
			
			Element elt = (Element)o;
			
			enfant = Class.forName(elt.getName());
			
			parameterTypes.add(enfant);
			
			if (elt.getName() == "Variable" || elt.getName() == "Constante" ) {
				
				Variable v = new variable()
			}
			
			
		}
		
		Class[] array = (Class[]) parameterTypes.toArray();
		
		Constructor constructor = parent.getDeclaredConstructor(array);
		
		Instruction instruction = (Instruction) constructor.newInstance(array);
		
		
	
	}

}
