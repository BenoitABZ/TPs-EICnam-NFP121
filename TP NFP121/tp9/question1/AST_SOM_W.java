package question1;

import tp6.question1.*;
import tp6.question2.*;
import tp6.question3.*;

/**
 * Décrivez votre classe AST_SOM_W ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class AST_SOM_W extends AST_Progr implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1654157304969328857L;

	/*
	 * modèle "java Style" dont l'AST "WhileL style" est à construire ci dessous
	 * n=100; som = 0 ; while (n> 0) { som = som+n ; n = n - 1 ; } afficher(som);
	 */

	private Contexte m = new Memoire();

	private Variable som = null;
	private Variable nConst = null;
	private Constante zero = null;
	private Constante un = null;
	private Instruction ast = null;

	public AST_SOM_W(int n) {
		m.ecrire("n", n);

		som = new Variable(m, "som");
		nConst = new Variable(m, "nconst", n);
		zero = new Constante(0);
		un = new Constante(1);

		ast = new TantQue(new Sup(nConst, zero), new Sequence(new Affectation(som, new Addition(som, nConst)),
				new Affectation(nConst, new Soustraction(nConst, un))));

	}

	public Contexte getMem() {
		return m;
	}

	public Instruction getAST() {
		return ast;
	}

}
