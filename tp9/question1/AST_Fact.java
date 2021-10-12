package question1;

import tp6.question1.*;
import tp6.question2.*;
import tp6.question3.*;

/**
 * Décrivez votre classe AST_Fact ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class AST_Fact extends AST_Progr implements java.io.Serializable {

	/*
	 * construire l'AST du programme "WhileL style" est à construire ci dessous
	 * xxx:=n ; fact := 1 ; while (~(xxx=0) ) do fact := fact * xxx ; xxx:= xxx-1;
	 * ftp; afficher(fact); remarque : on calcule ici fact(n) i.e. n est une donnée
	 * fournie au moment de l'exécution, d'où le constructeur suivant
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -6054707130695159699L;

	private Contexte m = new Memoire();

	private Variable dec = null;
	private Variable fact = null;
	private Constante zero = null;
	private Constante un = null;
	private Instruction ast = null;

	public AST_Fact(int n) {

		m.ecrire("n", n);

		int i = m.lire("n");
		
		dec = new Variable(m, "dec", i);
		fact = new Variable(m, "fact", 1);
		zero = new Constante(0);
		un = new Constante(1);
		
		ast = new TantQue(new Non(new Egal(dec, zero)), new Sequence(
				new Affectation(fact, new Multiplication(fact, dec)), new Affectation(dec, new Soustraction(dec, un))));

	}

	public Contexte getMem() {
		return m;
	}

	public Instruction getAST() {
		return ast;
	}

}
