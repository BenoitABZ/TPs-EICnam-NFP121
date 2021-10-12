package question2;

import question1.*;
import tp6.question1.*;
import tp6.question2.*;
import tp6.question3.*;

import org.jdom.*;
import org.jdom.output.*;
import org.jdom.input.*;

import java.io.*;

public class TestXML2AST extends junit.framework.TestCase {

	private String nomDuFichier;

	protected void setUp() {
		nomDuFichier = "test_tp9q2_XML2AST.xml";
	}

	protected void tearDown() {
		// try{new File(nomDuFichier).delete();}catch(Exception e){}
	}

	public void test_XML2AST() {

		try {

			SerialiseDeserialiseAST_XML.serialAst2xml(new AST_Fact(10),	nomDuFichier);
			Element astXML = SerialiseDeserialiseAST_XML.deserialXml(nomDuFichier);

			Contexte m = new Memoire();
			Instruction inst = XML2AST.xmlInst2ast(m, astXML);

			m.ecrire("n", 5);
			VisiteurExpression<Integer> ve = new VisiteurEvaluation(m);
			VisiteurExpressionBooleenne<Boolean> vb = new VisiteurBoolEvaluation(ve);
			VisiteurInstruction<Contexte> vi = new VisiteurInstEvaluation(ve, vb);

			VisiteurExpression<String> ves = new VisiteurInfixe(m);
			VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToString(ves);
			VisiteurInstruction<String> vs = new VisiteurInstToString(ves, vbs);
			inst.accepter(vi);

			assertTrue(m + inst.accepter(vs) + " ne donne pas le résultat attendu ...",	m.lire("fact") == fact(5));

		} catch (Exception e) {
			fail("exception inattendue !!! : " + e.getMessage());
		}
	}

	private static int fact(int n) {
		if (n == 0)
			return 1;
		else
			return n * fact(n - 1);
	}
	
}
