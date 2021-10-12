package question1;

import tp6.question1.*;
import tp6.question2.*;
import tp6.question3.*;

import java.io.*;

public class TestFactoriel extends junit.framework.TestCase {

	private static int fact(int n) {
		if (n == 0)
			return 1;
		else
			return n * fact(n - 1);
	}

	public void testFactoriel() {
		try {

			AST_Fact p = new AST_Fact(10);

			Contexte m = p.getMem(); // récupération du contexte

			VisiteurExpression<Integer> ve = new VisiteurEvaluation(m);
			VisiteurExpressionBooleenne<Boolean> vb = new VisiteurBoolEvaluation(ve);
			VisiteurInstruction<Contexte> vi = new VisiteurInstEvaluation(ve, vb);

			VisiteurExpression<String> ves = new VisiteurInfixe(m);
			VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToString(ves);
			VisiteurInstruction<String> vs = new VisiteurInstToString(ves, vbs);

			Instruction ast = p.getAST(); // récupération de l'AST

			ast.accepter(vi); // évaluation proprement dite de l'AST

			assertTrue((String) ast.accepter(vs) + " ne donne pas le résultat attendu ...", m.lire("fact") == fact(10));

		} catch (Exception e) {
			fail("exception inattendue !!! : " + e.getMessage());
		}
	}
	
	public void testMult2() {
		try {

			AST_Mult2 p = new AST_Mult2(4,2);

			Contexte m = p.getMem(); // récupération du contexte

			VisiteurExpression<Integer> ve = new VisiteurEvaluation(m);
			VisiteurExpressionBooleenne<Boolean> vb = new VisiteurBoolEvaluation(ve);
			VisiteurInstruction<Contexte> vi = new VisiteurInstEvaluation(ve, vb);

			VisiteurExpression<String> ves = new VisiteurInfixe(m);
			VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToString(ves);
			VisiteurInstruction<String> vs = new VisiteurInstToString(ves, vbs);

			Instruction ast = p.getAST(); // récupération de l'AST

			ast.accepter(vi); // évaluation proprement dite de l'AST

			assertTrue((String) ast.accepter(vs) + " ne donne pas le résultat attendu ...", m.lire("m2") == 0);

		} catch (Exception e) {
			fail("exception inattendue !!! : " + e.getMessage());
		}
	}

}
