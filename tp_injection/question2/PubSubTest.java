package question2;

import java.util.List;

import container.ApplicationContext;

public class PubSubTest extends junit.framework.TestCase{
	
	public void testAvecInjection() throws Exception {
		ApplicationContext ctx = container.Factory.createApplicationContext("./question2/PubSubConfig.txt");		
		Serveur serveur = ctx.getBean("serveur");
		
		serveur.broadcast();
		
		List<ClientRecepteur> recepteurs = serveur.getRecepteurs();
		
		ClientRecepteur recepteur = recepteurs.get(0);

		assertEquals("un message à transmettre", recepteur.getMessage().getPayload());

	}

}
