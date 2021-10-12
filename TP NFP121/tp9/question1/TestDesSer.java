package question1;

import tp6.question1.*;
import tp6.question2.*;
import tp6.question3.*;

public class TestDesSer extends junit.framework.TestCase {

	public void testSimple() {
		try {
			question1.JAVASerialiseDeserialise.serialjava(new AST_Aff(), "test.ser");

			question1.AST_Aff p = (question1.AST_Aff) question1.JAVASerialiseDeserialise.deserialjava("test.ser");
			assertNotNull(p);

			Contexte m = new Memoire();
			VisiteurExpression<Integer> ve = new VisiteurEvaluation(m);
			VisiteurExpressionBooleenne<Boolean> vb = new VisiteurBoolEvaluation(ve);
			VisiteurInstruction<Contexte> vi = new VisiteurInstEvaluation(ve, vb);

			Instruction ast = p.getAST(); // récupération de l'AST
			ast.accepter(vi); // évaluation proprement dite de l'AST
			assertTrue(" ce n'est pas le résultat attendu ...", m.lire("y") == 12);
		} catch (Exception e) {
			fail("exception inattendue : " + e.getMessage());
		}
	}
	
}
