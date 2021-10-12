package question1;

public class TestsACompleter extends junit.framework.TestCase {
	private Memoire m;
	private Variable i, j, k, p;
	private VisiteurExpression<Integer> ve;
	private VisiteurExpression<String> vp, vi;

	public void setUp() {
		m = new Memoire();
		i = new Variable(m, "i", 3);
		j = new Variable(m, "j", 5);
		k = new Variable(m, "k", 6);
		p = new Variable(m, "p", 8);
		ve = new VisiteurEvaluation(m);
		vi = new VisiteurInfixe(m);
		vp = new VisiteurPostfixe(m);
		assertNotNull(i);
		assertNotNull(j);
		assertNotNull(ve);
		assertNotNull(vp);
		assertNotNull(vi);
	}

	public void testUneAddition() {
		Expression expr = new Addition(new Constante(3), new Constante(2));
		assertEquals(" 3+2 != 5 ?, curieux ", 5, expr.accepter(ve).intValue());
	}

	public void testUneVariable() {
		try {
			assertEquals(m + "lecture mémoire ?, i==3 ?", 3, m.lire("i").intValue());
			assertEquals(m + "lecture mémoire ?, j==5 ?", 5, m.lire("j").intValue());
			assertEquals(m + "écriture mémoire ?, k==6 ?", 6, m.lire("k").intValue());
			assertEquals(m + "écriture mémoire ?, p==8 ?", 8, m.lire("p").intValue());
			m.ecrire("z", 2);
			assertEquals(m + "écriture mémoire ?, z==2 ?", 2, m.lire("z").intValue());
		} catch (Exception e) {
			fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
		}
	}

	public void testMultiplication() {
		try {
			assertEquals(m + " i * i != 9 ?", 9, new Multiplication(i, i).accepter(ve).intValue());
		} catch (StackOverflowError e) {
			fail(" StackOverflowError, appel récursif sans fin de " + m + " i * i ???");
		} catch (Exception e) {
			fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
		}
	}

	public void testSoustraction() {
		try {
			assertEquals(m + " j - i != 2 ?", 2, new Soustraction(j, i).accepter(ve).intValue());
		} catch (StackOverflowError e) {
			fail(" StackOverflowError, appel récursif sans fin de " + m + " j - i ???");
		} catch (Exception e) {
			fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
		}
	}

	public void testDivision() {
		try {
			assertEquals(m.lire("j"), new Integer(5));
			assertEquals(m.lire("i"), new Integer(3));
			assertEquals(m + " i*i/i != 3 ???", 3, new Division(new Multiplication(i, i), i).accepter(ve).intValue());
		} catch (StackOverflowError e) {
			fail(" StackOverflowError, appel récursif sans fin de " + m + " i*i/i ???");
		} catch (Exception e) {
			fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
		}
		try {
			new Division(i, new Constante(0)).accepter(ve);
			fail("division par zéro ? possible ");
		} catch (ArithmeticException ae) {
		} catch (Exception e) {
			fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
		}
	}

	public void testVisiteurInfixe() {
		try {
			Expression expr = new Multiplication(new Constante(5), new Constante(6));
			assertEquals("(5 * 6)", expr.accepter(vi));
			expr = new Addition(expr, new Constante(5));
			assertEquals("((5 * 6) + 5)", expr.accepter(vi));
			assertEquals("{i=3, j=5}", m.toString());
			expr = new Soustraction(expr, expr);
			assertEquals("(((5 * 6) + 5) - ((5 * 6) + 5))", expr.accepter(vi));
		} catch (Exception e) {
			fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
		}
	}

	public void testVisiteurInfixe2() {
		try {
			Expression expr = new Multiplication(new Addition(new Constante(5), new Constante(5)), new Constante(6));
			assertEquals("((5 + 5) * 6)", expr.accepter(vi));
			expr = new Division(expr, new Constante(5));
			assertEquals("((5 + 5) * 6) + 5)", expr.accepter(vi));
			assertEquals("{i=3, j=5, k=6, p=8}", m.toString());
			expr = new Soustraction(expr, expr);
			assertEquals("(((5 * 6) + 5) - ((5 * 6) + 5))", expr.accepter(vi));
		} catch (Exception e) {
			fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
		}
	}

	public void testVisiteurPostfixe() {
		try {
			Expression expr = new Division(new Constante(6), new Constante(2));
			assertEquals("(6,2)/", expr.accepter(vp));
			expr = new Soustraction(expr, new Constante(2));
			assertEquals("((6,2)/,2)-", expr.accepter(vp));
			assertEquals("{i=3, j=5, k=6, p=8}", m.toString());
			expr = new Addition(expr, expr);
			assertEquals("(((6,2)/,2)-,((6,2)/,2)-)+", expr.accepter(vp));
		} catch (Exception e) {
			fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
		}
	}

	public void testVisiteurPostfixe2() {
		try {
			Expression expr = new Multiplication(k, p);
			assertEquals("(6,8)*", expr.accepter(vp));
			expr = new Soustraction(expr, new Constante(2));
			assertEquals("((6,8)*,2)-", expr.accepter(vp));
			assertEquals("{i=3, j=5, k=6, p=8}", m.toString());
			expr = new Addition(expr, expr);
			assertEquals("(((6,8)*,2)-,((6,8)*,2)-)+", expr.accepter(vp));
		} catch (Exception e) {
			fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
		}
	}

}
