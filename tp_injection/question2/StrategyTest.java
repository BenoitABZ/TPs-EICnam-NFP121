package question2;

import container.ApplicationContext;

public class StrategyTest extends junit.framework.TestCase {

	public void testAvecInjection() throws Exception {
		ApplicationContext ctx = container.Factory.createApplicationContext("./question2/StrategyConfig.txt");
		Etudiant etudiant = ctx.getBean("etudiantStudieux");

		assertEquals(20, etudiant.getResultat());

	}

}
