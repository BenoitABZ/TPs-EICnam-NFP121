package question1;

import tp6.question1.*;
import tp6.question2.*;
import tp6.question3.*;

import java.io.*;

/**
 * Décrivez votre classe abstraite AST_Progr ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public abstract class AST_Progr implements IProgr, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8502645631038412326L;
	protected Instruction ast;
	protected Contexte m;
	protected Variable n;
	
}
