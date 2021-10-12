package question1;

import tp6.question1.*;
import tp6.question2.*;
import tp6.question3.*;

/**
 * Décrivez votre classe AST_Mult1 ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class AST_Mult1 extends AST_Progr implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1772035944513268696L;
	/*
	 * 
	 * modèle "java Style" dont l'AST "WhileL style" est à construire ci dessous a =
	 * n1 ; b = n2 ; produit = 0 ; while (b > 0) { produit = produit + a ; b=b-1; }
	 * afficher(produit);
	 * 
	 */
	private Contexte m = new Memoire();

	private Variable produit = null;
	private Variable b = null;
	private Constante a = null;
	private Constante zero = null;
	private Constante un = null;
	private Instruction ast = null;

	public AST_Mult1(int n1, int n2) {
		m.ecrire("a", n1);
		m.ecrire("b", n2);

		produit = new Variable(m, "produit");
		b = new Variable(m, "n2", n2);
		a = new Constante(n1);
		zero = new Constante(0);
		un = new Constante(1);

		ast = new TantQue(new Sup(b, zero), new Sequence(new Affectation(produit, new Addition(produit, a)),
				new Affectation(b, new Soustraction(b, un))));
	}

	public Contexte getMem() {
		return m;
	}

	public Instruction getAST() {
		return ast;
	}

}
