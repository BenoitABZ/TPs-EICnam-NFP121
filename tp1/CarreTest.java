
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class CarreTest extends TestCase {

	private int longueur;
	private PolygoneRegulier p;
	private Carre c;

	@Before
	public void setUp() // throws java.lang.Exception
	{
		longueur = 7;
		p = new PolygoneRegulier(longueur, 4);
		c = new Carre(longueur);
	}

	public void testPerimetre() {

		assertEquals("le perimetre devrait valoir 28", 28, p.perimetre());

		try {

			c = (Carre) p;
			fail("Exception?");

		} catch (Exception e) {

			assertEquals("le perimetre devrait valoir 28", 28, p.perimetre());

		}
		
		try {
			
			assertTrue(p instanceof PolygoneRegulier);
			
			p = c;
			
			assertTrue(p instanceof Carre);
			
		}catch(Exception e){
			
			fail("pas normal");
		}
	}

	@After
	public void tearDown() // throws java.lang.Exception
	{

	}
}
