package question1;

import java.util.List;

import container.ApplicationContext;

public class ConcessionTest extends junit.framework.TestCase {

	public void testAvecInjection() throws Exception {
		ApplicationContext ctx = container.Factory.createApplicationContext("./question1/ConcessionConfig.txt");
		Concession concession = ctx.getBean("concession");

		List<Voiture> voitures = concession.getVoitures();

		Voiture voiture = voitures.get(0);
		assertEquals("mercedes", voiture.getMarque());

	}
}
