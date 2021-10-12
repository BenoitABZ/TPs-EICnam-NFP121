package question1;

import tp6.question1.*;
import tp6.question2.*;
import tp6.question3.*;

/**
 * Décrivez votre classe AST_Aff ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class AST_Aff extends AST_Progr implements java.io.Serializable {

	/*
	 * "WhileL style" : x:=5 ; y:=7+x;
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 6360345671799160662L;

	private Contexte m = new Memoire();

	private Variable x = null;
	private Variable y = null;
	private Constante cinq = null;
	private Constante sept = null;
	private Instruction ast = null;

	public AST_Aff() {

		x = new Variable(m, "x");
		y = new Variable(m, "y");
		cinq = new Constante(5);
		sept = new Constante(7);
		
		ast = new Sequence(new Affectation(x, cinq), new Affectation(y, new Addition(sept, x)));

	}

	public Contexte getMem() {
		return m;
	}

	public Instruction getAST() {
		return ast;
	}

}
