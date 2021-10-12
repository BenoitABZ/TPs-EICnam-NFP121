package question1;

import java.util.*;

public class TestsCotisant extends junit.framework.TestCase {

	public void testEnonce() {
		try {
			Cotisant c = new Contributeur("a", -100);
			fail("la somme allou�e lors de la cr�ation ne peut �tre n�gative ???");
		} catch (Exception e) {
		}

		Contributeur c = new Contributeur("a", 100);
		assertTrue(" solde erron� ??? ", c.solde() == 100);
		final int val = 30;
		try {
			c.debit(val);
		} catch (Exception e) {
			fail(" aucune exception n'est attendue ici !");
		}
		assertTrue(" d�bit inop�rant ??? ", c.solde() == 100 - val);
		c.credit(val);
		assertTrue(" cr�dit inop�rant??? ", c.solde() == 100);

		try {
			c.debit(300);
			fail("SoldeDebiteurException est attendue");
		} catch (Exception e) {
			assertTrue(" SoldeDebiteurException est attendue ???", e instanceof SoldeDebiteurException);
		}

		GroupeDeContributeurs g = new GroupeDeContributeurs("g");
		g.ajouter(new Contributeur("a", 100));
		g.ajouter(new Contributeur("b", 50));
		g.ajouter(new Contributeur("c", 150));
		assertEquals(" nombre de Contributeurs ??? ", 3, g.nombreDeCotisants());

		assertEquals(" solde erron� ??? ", 300, g.solde());
		assertEquals(" solde erron� ??? ", 30, val);
		try {
			g.debit(10);
		} catch (Exception e) {
			fail();
		}
		assertEquals(" d�bit inop�rant ??? ", 300 - (g.nombreDeCotisants() * 10), g.solde());
		assertEquals(" solde erron� ??? ", 270, g.solde());

		try {
			g.debit(60);
			fail("SoldeDebiteurException est attendue");
		} catch (Exception e) {
			assertTrue(" SoldeDebiteurException est attendue ???", e instanceof SoldeDebiteurException);
		}
		assertEquals(" solde erron� ??? ", 210, g.solde());
		// ici le solde du groupe (g) est de 210, ce qui est une erreur de d�bit d�un
		// groupe,
		// le contributeur (a) a �t� d�bit� de 60
		// et l�op�ration a �chou�e pour (b)!
		// une transaction lors d�un d�bit sera demand�e en question3

		g = new GroupeDeContributeurs("g");
		g.ajouter(new Contributeur("g_a", 100));
		g.ajouter(new Contributeur("g_b", 200));
		g.ajouter(new Contributeur("g_c", 300));
		GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
		g1.ajouter(new Contributeur("g1_a1", 100));
		g1.ajouter(new Contributeur("g1_b1", 200));
		g.ajouter(g1);

		Iterator<Cotisant> it = g.iterator();
		assertTrue(it.next() instanceof Contributeur);
		assertTrue(it.next() instanceof Contributeur);
		assertTrue(it.next() instanceof Contributeur);
		assertTrue(it.next() instanceof GroupeDeContributeurs);
		assertTrue(it.next() instanceof Contributeur);
		assertTrue(it.next() instanceof Contributeur);

		try {
			it.next();
		} catch (Exception ex) {
			assertTrue(ex instanceof NoSuchElementException);
		}
	}

	public void testValeurNegative() {
		try {
			try {
				Cotisant c = new Contributeur("a", -100);
				fail("la somme allou�e lors de la cr�ation ne peut �tre n�gative ???");
			} catch (Exception e) {
			}
			Cotisant c = new Contributeur("a", 100);
			try {
				c.debit(-300);
				fail("la somme d�bit�e ne peut �tre n�gative ???");
			} catch (Exception e) {
			}
			try {
				c.credit(-300);
				fail("la somme cr�dit�e ne peut �tre n�gative ???");
			} catch (Exception e) {
			}
		} catch (Exception e) {
			fail("exception inattendue !!! " + e.getMessage());
		}
	}

	public void testIteratorSurUnGroupeDeGroupe() {
		GroupeDeContributeurs g = new GroupeDeContributeurs("g");
		g.ajouter(new Contributeur("g_a", 100));
		g.ajouter(new Contributeur("g_b", 200));
		g.ajouter(new Contributeur("g_c", 300));
		GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
		g1.ajouter(new Contributeur("g1_a1", 100));
		g1.ajouter(new Contributeur("g1_b1", 200));
		g.ajouter(g1);

		Iterator<Cotisant> it = g.iterator();
		assertTrue(" est-ce le bon Cotisant ?, un Contributeur est attendu !", it.next() instanceof Contributeur);
		assertTrue(" est-ce le bon Cotisant ?, un Contributeur est attendu !", it.next() instanceof Contributeur);
		assertTrue(" est-ce le bon Cotisant ?, un Contributeur est attendu !", it.next() instanceof Contributeur);
		assertTrue(" est-ce le bon Cotisant ?, un GroupeDeContributeurs est attendu !",
				it.next() instanceof GroupeDeContributeurs);
		assertTrue(" est-ce le bon Cotisant ?, un Contributeur est attendu !", it.next() instanceof Contributeur);
		assertTrue(" est-ce le bon Cotisant ?, un Contributeur est attendu !", it.next() instanceof Contributeur);

		try {
			it.next();
		} catch (Exception ex) {
			assertTrue(" NoSuchElementException est attendu ??? ", ex instanceof NoSuchElementException);
		}

	}

	public void testUnContributeur() {
		Contributeur c = new Contributeur("a", 100);
		assertTrue(" solde erron� ??? ", c.solde() == 100);
		final int val = 30;
		try {
			c.debit(val);
			assertTrue(" d�bit inop�rant ??? ", c.solde() == 100 - val);
			c.credit(val);
			assertTrue(" cr�dit inop�rant??? ", c.solde() == 100);

			try {
				c.debit(300);
				fail("le solde ne peut �tre n�gatif ???");
			} catch (Exception e) {
				assertTrue(" SoldeDebiteurException est attendue ???", e instanceof SoldeDebiteurException);
			}

			try {
				c.debit(val);
			} catch (Exception e) {
				fail(" le solde ne peut �tre nul ??? ");
			}
		} catch (Exception e) {
			fail("exception inattendue !!! " + e.getMessage());
		}
	}

	public void testUnGroupeDeContributeurs() {
		try {
			GroupeDeContributeurs c = new GroupeDeContributeurs("c");
			c.ajouter(new Contributeur("a", 100));
			c.ajouter(new Contributeur("b", 100));
			c.ajouter(new Contributeur("c", 100));

			assertTrue(" nombre de Contributeurs ??? ", c.nombreDeCotisants() == 3);

			assertTrue(" solde erron� ??? ", c.solde() == c.nombreDeCotisants() * 100);
			final int val = 30;

			c.debit(val);
			assertTrue(" d�bit inop�rant ??? ",
					c.solde() == (c.nombreDeCotisants() * 100) - (c.nombreDeCotisants() * val));
			c.credit(val);
			assertTrue(" cr�dit inop�rant??? ", c.solde() == c.nombreDeCotisants() * 100);

		} catch (Exception e) {
			fail("exception inattendue !!! " + e.getMessage());
		}
	}

	public void testPlusieursGroupesDeContributeurs() {
		try {
			GroupeDeContributeurs g0 = new GroupeDeContributeurs("g0");
			g0.ajouter(new Contributeur("a0", 100));
			assertTrue(" nombre de Contributeurs ??? ", g0.nombreDeCotisants() == 1);

			GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
			Cotisant a1 = new Contributeur("a1", 100);
			g1.ajouter(a1);
			g1.ajouter(new Contributeur("b1", 100));
			assertTrue(" nombre de Contributeurs ??? ", g1.nombreDeCotisants() == 2);
			GroupeDeContributeurs g2 = new GroupeDeContributeurs("g2");
			g2.ajouter(new Contributeur("a", 100));
			g2.ajouter(new Contributeur("b", 100));
			g2.ajouter(new Contributeur("c", 100));

			assertTrue(" nombre de Contributeurs ??? ", g2.nombreDeCotisants() == 3);
			assertTrue(" nombre de Contributeurs ??? ", g0.nombreDeCotisants() == 1);
			g2.ajouter(g0);
			assertTrue(" nombre de Contributeurs ??? ", g2.nombreDeCotisants() == 4);

			assertTrue(" nombre de Contributeurs ??? ", g1.nombreDeCotisants() == 2);
			g2.ajouter(g1);
			assertTrue(" nombre de Contributeurs ??? ", g2.nombreDeCotisants() == 6);

			assertTrue(" solde erron� ??? ", g2.solde() == g2.nombreDeCotisants() * 100);
			final int val = 30;

			g2.debit(val);
			assertTrue(" d�bit inop�rant ??? ",
					g2.solde() == (g2.nombreDeCotisants() * 100) - (g2.nombreDeCotisants() * val));
			g2.credit(val);
			assertTrue(" cr�dit inop�rant??? ", g2.solde() == g2.nombreDeCotisants() * 100);

		} catch (Exception e) {
			fail("exception inattendue !!! " + e.getMessage());
		}
	}

	public void testGetParentSurUnGroupe() {
		try {
			GroupeDeContributeurs g0 = new GroupeDeContributeurs("g0");
			Cotisant a0 = new Contributeur("a0", 100);
			g0.ajouter(a0);
			assertTrue(" getParent en �chec ??? ", g0.getParent() == null);
			assertTrue(" getParent en �chec ??? ", a0.getParent() != null);
			assertTrue(" getParent  en �chec ??? ", a0.getParent().equals(g0));

			GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
			Cotisant a1 = new Contributeur("a1", 100);
			g1.ajouter(a1);
			g1.ajouter(new Contributeur("b1", 100));
			assertTrue(" getParent en �chec ??? ", a1.getParent() != null);
			assertTrue(" parent incorrect ??? ", a1.getParent().nom().equals(g1.nom()));
			GroupeDeContributeurs g2 = new GroupeDeContributeurs("g2");
			Cotisant b = new Contributeur("b", 100);
			g2.ajouter(new Contributeur("a", 100));
			g2.ajouter(b);
			g2.ajouter(new Contributeur("c", 100));

			assertTrue(" getParent en �chec ??? ", g2.getParent() == null);
			assertTrue(" getParent en �chec ??? ", b.getParent() != null);
			assertTrue(" parent incorrect ??? ", b.getParent().nom().equals(g2.nom()));
			// g2.ajouter(g0);
			// assertTrue(" parent incorrect ??? ", g0.getParent().nom().equals(g2.nom()));

		} catch (Exception e) {
			fail("exception inattendue !!! " + e.getMessage());
		}
	}

	public void testUnGroupeAvecUnSoldeDebiteurDuPremierContributeur() {
		try {
			Contributeur a = new Contributeur("a", 100);
			Contributeur b = new Contributeur("b", 200);
			Contributeur c = new Contributeur("c", 300);

			GroupeDeContributeurs g = new GroupeDeContributeurs("g");
			g.ajouter(a);
			g.ajouter(b);
			g.ajouter(c);

			assertTrue(" nombre de Contributeurs ??? ", g.nombreDeCotisants() == 3);
			assertTrue(" solde erron� ??? ", g.solde() == 600);

			try {
				g.debit(120);
			} catch (Exception e) {
				assertTrue(" SoldeDebiteurException est attendue ???", e instanceof SoldeDebiteurException);
			}

			// pour ce test, les comptes des Contributeurs sont intacts
			assertTrue(" � la question3 seulement l'atomicit� est souhait�e ", a.solde() == 100);
			assertTrue(" � la question3 seulement l'atomicit� est souhait�e ", b.solde() == 200);
			assertTrue(" � la question3 seulement l'atomicit� est souhait�e ", c.solde() == 300);
			assertTrue(" solde erron� ??? ", g.solde() == 600);

		} catch (Exception e) {
			fail("exception inattendue !!! " + e.getMessage());
		}
	}

	public void testUnGroupeAvecUnSoldeDebiteurDuDernierContributeur() {
		try {
			Contributeur a = new Contributeur("a", 300);
			Contributeur b = new Contributeur("b", 200);
			Contributeur c = new Contributeur("c", 100);

			GroupeDeContributeurs g = new GroupeDeContributeurs("g");
			g.ajouter(a);
			g.ajouter(b);
			g.ajouter(c);

			assertTrue(" nombre de Contributeurs ??? ", g.nombreDeCotisants() == 3);
			assertTrue(" solde erron� ??? ", g.solde() == 600);

			try {
				g.debit(120);
			} catch (Exception e) {
				assertTrue(" SoldeDebiteurException est attendue ???", e instanceof SoldeDebiteurException);
			}
			// System.out.println(g);
			// pour ce test, les comptes des Contributeurs b & c ont �t� d�bit�s, et le
			// d�bit a �chou�
			assertTrue(" � la question3 seulement l'atomicit� est souhait�e ", a.solde() == 180);
			assertTrue(" � la question3 seulement l'atomicit� est souhait�e ", b.solde() == 80);
			assertTrue(" � la question3 seulement l'atomicit� est souhait�e ", c.solde() == 100);
			assertTrue(" solde erron� ??? ", g.solde() == (180 + 80 + 100));

		} catch (Exception e) {
			fail("exception inattendue !!! " + e.getMessage());
		}
	}

	public void testUnGroupeAvecTousLesSoldesDebiteurs() {
		try {
			GroupeDeContributeurs c = new GroupeDeContributeurs("c");
			c.ajouter(new Contributeur("a", 100));
			c.ajouter(new Contributeur("b", 200));
			c.ajouter(new Contributeur("c", 300));

			assertTrue(" nombre de Contributeurs ??? ", c.nombreDeCotisants() == 3);

			assertTrue(" solde erron� ??? ", c.solde() == 600);
			final int val = 30;

			c.debit(val);
			assertTrue(" d�bit inop�rant ??? ", c.solde() == 600 - (c.nombreDeCotisants() * val));
			c.credit(val);
			assertTrue(" cr�dit inop�rant??? ", c.solde() == 600);

		} catch (Exception e) {
			fail("exception inattendue !!! " + e.getMessage());
		}
	}

	public void testVisiteurSurUnGroupe() {
		try {
			GroupeDeContributeurs g = new GroupeDeContributeurs("g");
			g.ajouter(new Contributeur("a", 100));
			g.ajouter(new Contributeur("b", 200));
			g.ajouter(new Contributeur("c", 300));

			Iterator<Cotisant> it = g.iterator();
			assertTrue(" est-ce le bon Contributeur ???", it.next().nom().equals("a"));
			assertTrue(" est-ce le bon Contributeur ???", it.next().nom().equals("b"));
			assertTrue(" est-ce le bon Contributeur ???", it.next().nom().equals("c"));
			// System.out.println(g.accepte(new ToStringVisiteur()));
			assertTrue(" visite non conforme ? : ", g.accepter(new VisiteurToString()).equals(
					"<Groupe g : (<Contributeur : (a, 100)><Contributeur : (b, 200)><Contributeur : (c, 300)>>"));

		} catch (Exception e) {
			fail("exception inattendue !!! " + e.getMessage());
		}
	}

	private List<Cotisant> enListe(Cotisant c) {
		List<Cotisant> liste = new ArrayList<Cotisant>();

		if (c instanceof GroupeDeContributeurs) {
			GroupeDeContributeurs g = (GroupeDeContributeurs) c;
			for (Cotisant co : g.getChildren()) {
				liste.addAll(enListe(co));
			}
		} else {
			liste.add(c);
		}
		return liste;
	}

	public void testIteratorSurUnGroupeDeGroupes() {
		try {
			GroupeDeContributeurs g = new GroupeDeContributeurs("g");
			g.ajouter(new Contributeur("g_a", 100));
			g.ajouter(new Contributeur("g_b", 200));
			g.ajouter(new Contributeur("g_c", 300));
			GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
			g1.ajouter(new Contributeur("g1_a", 100));
			g1.ajouter(new Contributeur("g1_b", 200));
			GroupeDeContributeurs g11 = new GroupeDeContributeurs("g11");
			g11.ajouter(new Contributeur("g11_a", 100));
			g1.ajouter(g11);
			GroupeDeContributeurs g2 = new GroupeDeContributeurs("g2");
			g2.ajouter(new Contributeur("g2_a", 100));
			g2.ajouter(new Contributeur("g2_b", 200));
			g2.ajouter(new Contributeur("g2_c", 100));
			g2.ajouter(new Contributeur("g2_d", 200));

			g.ajouter(g1);
			g.ajouter(g2);

			Iterator<Cotisant> it = g.iterator();
			assertTrue(" est-ce le bon Cotisant ?, un Contributeur est attendu !", it.next() instanceof Contributeur);
			assertTrue(" est-ce le bon Cotisant ?, un Contributeur est attendu !", it.next() instanceof Contributeur);
			assertTrue(" est-ce le bon Cotisant ?, un Contributeur est attendu !", it.next() instanceof Contributeur);
			assertTrue(" est-ce le bon Cotisant ?, un GroupeDeContributeurs est attendu !",
					it.next() instanceof GroupeDeContributeurs); // g1
			assertTrue(" est-ce le bon Cotisant ?, un Contributeur est attendu !", it.next() instanceof Contributeur);
			assertTrue(" est-ce le bon Cotisant ?, un Contributeur est attendu !", it.next() instanceof Contributeur);
			assertTrue(" est-ce le bon Cotisant ?, un GroupeDeContributeurs est attendu !",
					it.next() instanceof GroupeDeContributeurs); // g11
			assertTrue(" est-ce le bon Cotisant ?, un Contributeur est attendu !", it.next() instanceof Contributeur);
			assertTrue(" est-ce le bon Cotisant ?, un GroupeDeContributeurs est attendu !",
					it.next() instanceof GroupeDeContributeurs); // g2
			assertTrue(" est-ce le bon Cotisant ?, un Contributeur est attendu !", it.next() instanceof Contributeur);
			assertTrue(" est-ce le bon Cotisant ?, un Contributeur est attendu !", it.next() instanceof Contributeur);
			assertTrue(" est-ce le bon Cotisant ?, un Contributeur est attendu !", it.next() instanceof Contributeur);
			assertTrue(" est-ce le bon Cotisant ?, un Contributeur est attendu !", it.next() instanceof Contributeur);

			try {
				it.next();
			} catch (Exception ex) {
				assertTrue(" NoSuchElementException est attendu ??? ", ex instanceof NoSuchElementException);
			}
		} catch (Exception e) {
			fail("exception inattendue !!! " + e.getMessage());
		}
	}

	public void testGetChildrenSurUnGroupeDeGroupes() {
		try {
			GroupeDeContributeurs g = new GroupeDeContributeurs("g");
			g.ajouter(new Contributeur("g_a", 100));
			g.ajouter(new Contributeur("g_b", 200));
			g.ajouter(new Contributeur("g_c", 300));
			GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
			g1.ajouter(new Contributeur("g1_a", 100));
			g1.ajouter(new Contributeur("g1_b", 200));
			GroupeDeContributeurs g11 = new GroupeDeContributeurs("g11");
			g11.ajouter(new Contributeur("g11_a", 100));
			g1.ajouter(g11);
			GroupeDeContributeurs g2 = new GroupeDeContributeurs("g2");
			g2.ajouter(new Contributeur("g2_a", 100));
			g2.ajouter(new Contributeur("g2_b", 200));
			g2.ajouter(new Contributeur("g2_c", 100));
			g2.ajouter(new Contributeur("g2_d", 200));

			g.ajouter(g1);
			g.ajouter(g2);

			Iterator<Cotisant> it = g.getChildren().iterator();
			assertTrue(" est-ce le bon Cotisant ?, un Contributeur est attendu !", it.next() instanceof Contributeur); // g_a
			assertTrue(" est-ce le bon Cotisant ?, un Contributeur est attendu !", it.next() instanceof Contributeur); // g_b
			assertTrue(" est-ce le bon Cotisant ?, un Contributeur est attendu !", it.next() instanceof Contributeur); // g_c
			assertTrue(" est-ce le bon Cotisant ?, un GroupeDeContributeurs est attendu !",
					it.next() instanceof GroupeDeContributeurs); // g1
			assertTrue(" est-ce le bon Cotisant ?, un GroupeDeContributeurs est attendu !",
					it.next() instanceof GroupeDeContributeurs); // g2
			try {
				it.next();
			} catch (Exception ex) {
				assertTrue(" NoSuchElementException est attendu ??? ", ex instanceof NoSuchElementException);
			}
		} catch (Exception e) {
			fail("exception inattendue !!! " + e.getMessage());
		}

	}

	public void testDebitMaximalSurUnGroupeDeGroupe() throws SoldeDebiteurException {

		GroupeDeContributeurs g = new GroupeDeContributeurs("g");
		Contributeur a = new Contributeur("g_a", 120);
		g.ajouter(a);
		g.ajouter(new Contributeur("g_b", 200));
		g.ajouter(new Contributeur("g_c", 300));
		GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
		g1.ajouter(new Contributeur("g1_a1", 130));
		g1.ajouter(new Contributeur("g1_b1", 200));
		g.ajouter(g1);
		g.debit(120);

		assertEquals(" Le débit est-il inopérant ?? ", 0, a.solde());

	}
}
