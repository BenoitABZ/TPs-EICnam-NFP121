package tp6.question2;

import java.io.*;
import org.jdom.Element;

import tp6.question1.Addition;
import tp6.question1.Constante;
import tp6.question1.Division;
import tp6.question1.Multiplication;
import tp6.question1.Soustraction;
import tp6.question1.Variable;
import tp6.question1.VisiteurExpression;

public class VisiteurBoolJDOM extends VisiteurExpressionBooleenne<Element> {

	private VisiteurExpression<Element> ve;

	public VisiteurBoolJDOM(VisiteurExpression<Element> ve) {
		this.ve = ve;
	}

	@Override
	public Element visite(Vrai v) {
		Element elt = new Element("Vrai");
		elt.addContent("true");

		return elt;
	}

	@Override
	public Element visite(Faux f) {
		Element elt = new Element("Faux");
		elt.addContent("faux");

		return elt;
	}

	@Override
	public Element visite(Non n) {
		Element elt = new Element("Non");
		elt.addContent(n.bop().accepter(this));

		return elt;

	}

	@Override
	public Element visite(Ou ou) {
		Element elt = new Element("Ou");
		elt.addContent(ou.bop1().accepter(this));
		elt.addContent(ou.bop2().accepter(this));

		return elt;
	}

	@Override
	public Element visite(Et et) {
		Element elt = new Element("Et");
		elt.addContent(et.bop1().accepter(this));
		elt.addContent(et.bop2().accepter(this));

		return elt;
	}

	@Override
	public Element visite(Sup sup) {

		Element elt = new Element("Sup");
		elt.addContent(sup.op1().accepter(ve));
		elt.addContent(sup.op2().accepter(ve));

		return elt;
	}

	@Override
	public Element visite(Egal eg) {
		Element elt = new Element("Egal");
		elt.addContent(eg.op1().accepter(ve));
		elt.addContent(eg.op2().accepter(ve));

		return elt;
	}

	@Override
	public Element visite(Inf inf) {
		Element elt = new Element("Inf");
		elt.addContent(inf.op1().accepter(ve));
		elt.addContent(inf.op2().accepter(ve));

		return elt;
	}

}
