package question2;

import java.util.*;
import question1.*;

/**
 * Quelques test supplémentaires à ceux déja présent.
 * 
 * Tests basiques des visiteurs SansDoublon, DebitMaximal et CompositeValide dans testACompleter().
 * 
 * @author benoit
 *
 */
public class TestsDesVisiteurs extends junit.framework.TestCase {

	public void testACompleter() {
		try {
			GroupeDeContributeurs g = new GroupeDeContributeurs("g");
			g.ajouter(new Contributeur("g_a", 50));
			g.ajouter(new Contributeur("g_b", 200));
			g.ajouter(new Contributeur("g_c", 300));
			assertFalse(" Ce composite n'est valide, revoyez CompositeValide !!!",
					g.accepter(new CompositeValide(100)));
		} catch (Exception e) {
			fail("exception inattendue !!! " + e.getMessage());
		}
		;

		try {
			GroupeDeContributeurs g = new GroupeDeContributeurs("g");
			g.ajouter(new Contributeur("g_a", 50));
			g.ajouter(new Contributeur("g_b", 200));
			g.ajouter(new Contributeur("g_c", 300));
			assertTrue(" Ce composite est valide, revoyez SansDoublon !!!", g.accepter(new SansDoublon()));
		} catch (Exception e) {
			fail("exception inattendue !!! " + e.getMessage());
		}
		;

		try {
			GroupeDeContributeurs g = new GroupeDeContributeurs("g");
			g.ajouter(new Contributeur("g_b", 200));
			g.ajouter(new Contributeur("g_c", 300));
			GroupeDeContributeurs g1 = new GroupeDeContributeurs("g2");
			g1.ajouter(new Contributeur("g_a", 50));
			g.ajouter(g1);
			assertEquals(new Integer(50), g.accepter(new DebitMaximal()));
		} catch (Exception e) {
			fail("exception inattendue !!! " + e.getMessage());
		}
		;
	}

	public void testTroisContributeurs() {
		try {
			GroupeDeContributeurs g = new GroupeDeContributeurs("g");
			g.ajouter(new Contributeur("g_a", 100));
			g.ajouter(new Contributeur("g_b", 200));
			g.ajouter(new Contributeur("g_c", 300));
			assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));
		} catch (Exception e) {
			fail("exception inattendue !!! " + e.getMessage());
		}
	}

	public void testCompositeValide() {
		try {
			GroupeDeContributeurs g = new GroupeDeContributeurs("g");
			assertFalse(" Ce composite n'est pas valide, revoyez CompositeValide !!!",
					g.accepter(new CompositeValide()));

			GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
			g.ajouter(g1);
			assertFalse(" Ce composite n'est pas valide, revoyez CompositeValide !!!",
					g.accepter(new CompositeValide()));

			g1.ajouter(new Contributeur("c", 100));
			assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));

		} catch (Exception e) {
			fail("exception inattendue !!! " + e.getMessage());
		}
	}

	public void testTroisContributeursUnGroupe() {
		try {
			GroupeDeContributeurs g = new GroupeDeContributeurs("g");
			g.ajouter(new Contributeur("g_a", 100));
			g.ajouter(new Contributeur("g_b", 200));
			g.ajouter(new Contributeur("g_c", 300));
			assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));
			assertEquals(" Revoyez D�bitMaximal !!!", new Integer(100), g.accepter(new DebitMaximal()));
			GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
			g.ajouter(g1);
			assertFalse(" Ce composite n'est pas valide, revoyez CompositeValide !!!",
					g1.accepter(new CompositeValide()));
		} catch (Exception e) {
			fail("exception inattendue !!! " + e.getMessage());
		}
	}

	public void testUnContributeurUnGroupeAvecLeMemeNom() {
		try {
			GroupeDeContributeurs g = new GroupeDeContributeurs("g_a");
			g.ajouter(new Contributeur("g_a", 100));
			g.ajouter(new Contributeur("g_b", 200));
			g.ajouter(new Contributeur("g_c", 300));
			g.ajouter(new Contributeur("g_d", 80));
			assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));
			assertFalse(" Ce composite a au moins un doublon, revoyez SansDoublon !!!", g.accepter(new SansDoublon()));
		} catch (Exception e) {
			fail("exception inattendue !!! " + e.getMessage());
		}
	}

	public void testQuatreContributeursUnGroupe() {
		try {
			GroupeDeContributeurs g = new GroupeDeContributeurs("g");
			g.ajouter(new Contributeur("ga", 100));
			g.ajouter(new Contributeur("gb", 200));
			GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
			g1.ajouter(new Contributeur("g1a", 20));
			g.ajouter(g1);
			g.ajouter(new Contributeur("gc", 300));
			assertEquals("Le nombre de cotisants est-il erroné ???", 4, g.nombreDeCotisants());
			assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide(20)));
			assertTrue(" Ce composite n'a pas de doublon, revoyez SansDoublon !!!", g.accepter(new SansDoublon()));
			assertEquals(" Revoyez DébitMaximal !!!", new Integer(20), g.accepter(new DebitMaximal()));
		} catch (Exception e) {
			fail("exception inattendue !!! " + e.getMessage());
		}
	}

}