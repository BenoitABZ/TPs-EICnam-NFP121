package question2;

import question1.*;

import java.io.*;

public class Tests_serialAst2xml extends junit.framework.TestCase {

	public void testSimple() {
		String nomDuFichier = "test_tp9q2_Ast2xml" + ".xml";
		try {
			SerialiseDeserialiseAST_XML.serialAst2xml(new AST_Fact(10),	nomDuFichier);

			File file = new File(nomDuFichier);
			BufferedReader in = new BufferedReader(new FileReader(file));
			try {
				in.readLine(); // <?xml version="1.0" encoding="UTF-8"?>
				assertTrue(" <programme> est attendu ? ", in.readLine().trim().endsWith("<programme>"));
				assertTrue(" <Sequence> est attendu ? ", in.readLine().trim().endsWith("<Sequence>"));
				assertTrue(" mauvaise balise ? ", in.readLine().trim().endsWith("<Affectation>"));
				// etc ...
			} catch (Exception e) {
				throw e;
			} finally {
				in.close();
				// new File(nomDuFichier).delete();
			}
		} catch (Exception e) {
			fail("exception inattendue !!! : " + e.getMessage());
		}
	}

}
