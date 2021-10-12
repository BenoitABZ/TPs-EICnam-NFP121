package question2;

import question1.IProgr;
import tp6.question1.Contexte;
import tp6.question1.VisiteurExpression;
import tp6.question1.VisiteurJDOM;
import tp6.question2.VisiteurBoolJDOM;
import tp6.question2.VisiteurExpressionBooleenne;
import tp6.question3.VisiteurInstJDOM;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Décrivez votre classe SerialiseDeserialiseAST_XML ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class SerialiseDeserialiseAST_XML {

	public static void serialAst2xml(IProgr p, String nomDuFichier) throws FileNotFoundException, IOException {
		
		Contexte contexte = p.getMem();
		
		VisiteurJDOM vi = new VisiteurJDOM(contexte);
		
		Element element = new Element("programme");
		
		Document document = new Document(element.addContent(p.getAST().accepter(new VisiteurInstJDOM(vi, new VisiteurBoolJDOM(vi)))));
			
		XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
		
		out.output(document, new FileOutputStream(new File(nomDuFichier)));
	}

	public static Element deserialXml(String nomDuFichier) throws Exception {
		
		SAXBuilder sax = new SAXBuilder();
		
		Document document = sax.build(new File(nomDuFichier));
		
		Element instruction = document.getRootElement();
		
		System.out.println(instruction.getName());
		
		return instruction;

	}
	
}