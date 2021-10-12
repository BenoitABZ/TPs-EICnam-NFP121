package question3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XmlRule<E, R> extends DecoratorRule<E, R> {

	private Rule<E, R> rule;

	public XmlRule(RuleI<E, R> rule) {
		super(rule);
	}

	public R execute(E e, R r) throws Exception {
		Element rootElement = makeDocument(e, r);
		serializeToXml(rootElement);
		return super.execute(e, r);
	}

	private Element makeDocument(E e, R r) {

		Element rulesElement = new Element("rules");

		Element ruleElement = new Element("rule");
		ruleElement.setText(rule.getClass().getName());

		Element spec = new Element("specification");
		spec.setText(rule.getSpecification().getClass().getName());
		spec.setAttribute("isSatisfiedBy", e.getClass().getName());

		Element com = new Element("command");
		com.setText(rule.getCommand().getClass().getName());
		com.setAttribute("result", r.getClass().getName());

		rulesElement.addContent(ruleElement);
		ruleElement.addContent(spec);
		ruleElement.addContent(com);

		return rulesElement;
	}

	private void serializeToXml(Element rootElement) throws IOException, Exception {

		Document document = new Document(rootElement);

		XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());

		out.output(document, new FileOutputStream(new File("XMLRULE.xml")));

	};
}
