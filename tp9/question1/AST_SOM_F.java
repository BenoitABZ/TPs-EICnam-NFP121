package question1;

import tp6.question1.*;
import tp6.question2.*;
import tp6.question3.*;

/**
 * Décrivez votre classe AST_SOM_F ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class AST_SOM_F extends AST_Progr implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1630953729007622731L;
	/*
	 * n=100; som = 0 ; for(i=0 ; i<n ; i++){ (n> 0) { som := som+i ; }
	 * afficher(som);
	 */
	private Contexte m = new Memoire();

	private Variable som = null;
	private Variable i = null;
	private Constante nConst = null;
	private Constante zero = null;
	private Constante un = null;
	private Instruction ast = null;

	public AST_SOM_F(int n) {
		m.ecrire("n", n);

		som = new Variable(m, "som");
		i = new Variable(m, "i");
		nConst = new Constante(n);
		zero = new Constante(0);
		un = new Constante(1);

		ast = new Pour(new Affectation(i, zero), new Inf(i, nConst), new Affectation(som, new Addition(som, i)),
				new Affectation(i, new Addition(i, un)));

	}

	public Contexte getMem() {
		return m;
	}

	public Instruction getAST() {
		return ast;
	}

}
