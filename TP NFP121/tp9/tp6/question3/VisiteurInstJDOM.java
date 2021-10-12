package tp6.question3;

import org.jdom.Element;

import tp6.question1.Contexte;
import tp6.question1.VisiteurExpression;
import tp6.question2.VisiteurExpressionBooleenne;

/**
 * Visiteur d'instruction, chaque classe concrète possède une implémentation de
 * la visite
 * 
 */
public class VisiteurInstJDOM extends VisiteurInstruction<Element> {

	private VisiteurExpression<Element> vi;
	private VisiteurExpressionBooleenne<Element> vb;

	/**
	 * Création d'un visiteur d'instructions
	 * 
	 * @param vi le visiteur d'expressions arithmétiques
	 * @param vb le visiteur d'expression booléennes
	 */
	public VisiteurInstJDOM(VisiteurExpression<Element> vi, VisiteurExpressionBooleenne<Element> vb) {
		this.vi = vi;
		this.vb = vb;
	}

	/**
	 * obtention du contexte,
	 * 
	 * @return le contexte ici de vi(le visiteur d'expression)
	 */
	public Contexte contexte() {
		return this.vi.contexte();
	}

	@Override
	public Element visite(Affectation aff) {
		Element elt = new Element("Affectation");
		elt.addContent(aff.v().accepter(vi));
		elt.addContent(aff.exp().accepter(vi));

		return elt;
	}

	@Override
	public Element visite(Sequence seq) {
		Element elt = new Element("Sequence");
		elt.addContent(seq.i1().accepter(this));
		elt.addContent(seq.i2().accepter(this));

		return elt;
	}

	@Override
	public Element visite(Selection sel) {
		Element elt = new Element("Selection");
		elt.addContent(sel.cond().accepter(vb));
		elt.addContent(sel.i1().accepter(this));
		elt.addContent(sel.i2().accepter(this));

		return elt;
	}

	@Override
	public Element visite(TantQue tq) {
		Element elt = new Element("TantQue");
		elt.addContent(tq.cond().accepter(vb));
		elt.addContent(tq.i1().accepter(this));

		return elt;
	}

	@Override
	public Element visite(Pour pour) {
		Element elt = new Element("Pour");
		elt.addContent(pour.init().accepter(this));
		elt.addContent(pour.cond().accepter(vb));
		elt.addContent(pour.i1().accepter(this));
		elt.addContent(pour.inc().accepter(this));

		return elt;
	}

	@Override
	public Element visite(Afficher a) {
		Element elt = new Element("Afficher");
		elt.addContent(a.exp().accepter(vi));

		return elt;
	}

	@Override
	public Element visite(Assertion a) {
		Element elt = new Element("Afficher");
		elt.addContent(a.cond().accepter(vb));

		return elt;
	}

}
