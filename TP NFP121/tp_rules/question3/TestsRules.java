package question3;

import question1.*;
import question2.*;

import java.lang.reflect.Method;
import java.util.*;

public class TestsRules extends junit.framework.TestCase {

	public void testSimpleAvecDesEntiers() throws Exception {
		SpecificationI<Integer> inf = new EstInferieur(4);
		MacroCommand<Integer, Integer> inc2 = new MacroCommand<>();
		inc2.add(new Inc(1)).add(new Inc(1));

		RuleI<Integer, Integer> rule = new Rule<>(inf, inc2);
		System.out.println("rule:" + rule);

		Integer res = 0, x = 3;

		res = rule.execute(x, res); // [res/0,x/3]if inf(x,4) res = plus2.execute(res);
		assertEquals(new Integer(2), res); // [res/2]

		x = x + 2;
		Integer res1 = rule.execute(x, res); // [x/5]if inf(x,4) res1 = plus2.execute(res);
		assertEquals(new Integer(2), res1); // [res/2,res1/2]

		Rules<Integer, Integer> cluster1 = new Rules();
		cluster1.add(rule).add(rule).add(rule);

		Integer res2 = cluster1.execute(2, 0);
		assertEquals(new Integer(6), res2);

		Rule<Integer, Integer> print = new Rule<Integer, Integer>();
		print.setSpecification(new TRUE());
		print.setCommand(new Print());
		Rules<Integer, Integer> cluster2 = new Rules();
		cluster2.add(rule).add(print).add(rule).add(print);
		cluster1.add(cluster2);
		res2 = cluster1.execute(2, 0);
		assertEquals(new Integer(10), res2);

	}

	public void testRuleReflection() throws Exception {

		ReflectSpecification<Integer> inf = new ReflectSpecification<>();
		inf.setSpecification("question3.EstInferieur");

		ReflectCommand<Integer, Integer> inc = new ReflectCommand<>();
		inc.setCommand("question3.Inc");

		Rule<Integer, Integer> rule = new Rule<>();
		rule.setCommand(inc);
		rule.setSpecification(inf);

		EstInferieur ei = (EstInferieur) inf.getSpecification();

		ei.setValeur(4);

		Integer i = rule.execute(0, 5);

		assertEquals(new Integer(6), i);

	}

	public void testRuleReflectionWithRuleReflect() throws Exception {

		ReflectRule<Integer, Integer> rule = new ReflectRule<>();
		rule.setCommand("question3.Inc");
		rule.setSpecification("question3.EstInferieur");

		Integer i = rule.execute(0, 5);

		assertEquals(new Integer(5), i);

	}

	public void testRuleAuditeur() throws Exception {
		// // si l'auditeur a NFP120 et NFP121 alors il obtient AISL
		// // si l'auditeur a RSX116 ou RSX117 alors il obtient IRSM
		SpecificationI<Auditeur> aisl = new And<>(new EstEgal("NFP120"), new EstEgal("NFP121"));
		SpecificationI<Auditeur> irsm = new And<>(new EstEgal("RSX116"), new EstEgal("RSX117"));

		Command<Auditeur, String> affecterDiplomeIrsm = new DiplomeCommandIrsm();

		Command<Auditeur, String> affecterDiplomeAisl = new DiplomeCommandAisl();

		Rule<Auditeur, String> ruleAisl = new Rule<>();

		ruleAisl.setCommand(affecterDiplomeAisl);

		ruleAisl.setSpecification(aisl);

		Rule<Auditeur, String> ruleIrsm = new Rule<>();

		ruleIrsm.setCommand(affecterDiplomeIrsm);

		ruleIrsm.setSpecification(irsm);

		Rules<Auditeur, String> rulesDiplome = new Rules<>();

		rulesDiplome.add(ruleAisl);

		rulesDiplome.add(ruleIrsm);

		Auditeur auditeur = new Auditeur("moi");

		auditeur.ajouterUE("NFP120");

		auditeur.ajouterUE("NFP121");

		String result = "";

		result = rulesDiplome.execute(auditeur, result);

		assertEquals("AISL", result);

	}

// voir avec la jurisprudence
// ici http://deptinfo.cnam.fr/new/spip.php?pdoc13437

	public static class Auditeur {
		private String nom;
		private Set<String> ue;
		private Set<String> diplomes;

		public Auditeur(String nom) {
			this.nom = nom;
			this.ue = new TreeSet<>();
			this.diplomes = new TreeSet<>();
		}

		public Auditeur ajouterUE(String ue) {
			this.ue.add(ue);
			return this;
		}

		public Set<String> getUE() {
			return this.ue;
		}
	}

	public static class EstEgal implements SpecificationI<Auditeur> {
		private String valeur;

		public EstEgal(String valeur) {
			this.valeur = valeur;
		}

		public boolean isSatisfiedBy(Auditeur a) {
			for (String ue : a.getUE()) {

				if (ue.equals(valeur)) {

					return true;

				}
			}

			return false;
		}

		public String interpreter() {
			return "";
		}
	}

	public static class DiplomeCommandIrsm extends Command<Auditeur, String> {

		@Override
		public String execute(Auditeur a, String result) throws Exception {

			result = "IRSM";

			return result;

		}
	}

	public static class DiplomeCommandAisl extends Command<Auditeur, String> {

		@Override
		public String execute(Auditeur a, String result) throws Exception {

			result = "AISL";

			return result;

		}

	}

}
