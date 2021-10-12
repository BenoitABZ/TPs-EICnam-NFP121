package tp6.question1;

import java.io.*;

import org.jdom.Element;

public class VisiteurJDOM extends VisiteurParDefaut<Element> {

	private Contexte c;

	public VisiteurJDOM(Contexte c) {
		this.c = c;
	}

	public Contexte contexte() {
		return c;
	}

	@Override
	public Element visite(Constante c) {
		Element elt = new Element("Constante");
		elt.addContent(Integer.toString(c.valeur()));

		return elt;
	}

	@Override
	public Element visite(Variable v) {
		Element elt = new Element("Constante");
		elt.addContent(v.nom());

		return elt;
	}

	@Override
	public Element visite(Division d) {
		Element elt = new Element("Division");
		elt.addContent(d.op1().accepter(this));
		elt.addContent(d.op2().accepter(this));

		return elt;
	}

	@Override
	public Element visite(Addition a) {
		Element elt = new Element("Addition");
		elt.addContent(a.op1().accepter(this));
		elt.addContent(a.op2().accepter(this));

		return elt;
	}

	@Override
	public Element visite(Multiplication m) {
		Element elt = new Element("Multiplication");
		elt.addContent(m.op1().accepter(this));
		elt.addContent(m.op2().accepter(this));

		return elt;
	}

	@Override
	public Element visite(Soustraction s) {
		Element elt = new Element("Soustraction");
		elt.addContent(s.op1().accepter(this));
		elt.addContent(s.op2().accepter(this));

		return elt;
	}

}
