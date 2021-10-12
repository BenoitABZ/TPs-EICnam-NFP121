package question2;

/**
 * Classe-test Pile4Test.
 * 
 * @author (votre nom)
 * @version (un num�ro de version ou une date)
 * 
 *          Les classes-test sont document�es ici :
 *          http://junit.sourceforge.net/javadoc/junit/framework/TestCase.html
 *          et sont bas�es sur le document � 2002 Robert A. Ballance intitul�
 *          �JUnit: Unit Testing Framework�.
 * 
 *          Les objets Test (et TestSuite) sont associ�s aux classes � tester
 *          par la simple relation yyyTest (e.g. qu'un Test de la classe
 *          Name.java se nommera NameTest.java); les deux se retrouvent dans le
 *          m�me paquetage. Les "engagements" (anglais : "fixture") forment un
 *          ensemble de conditions qui sont vraies pour chaque m�thode Test �
 *          ex�cuter. Il peut y avoir plus d'une m�thode Test dans une classe
 *          Test; leur ensemble forme un objet TestSuite. BlueJ d�couvrira
 *          automatiquement (par introspection) les m�thodes Test de votre
 *          classe Test et g�n�rera la TestSuite cons�quente. Chaque appel d'une
 *          m�thode Test sera pr�c�d� d'un appel de setUp(), qui r�alise les
 *          engagements, et suivi d'un appel � tearDown(), qui les d�truit.
 */
public class Pile4Test extends junit.framework.TestCase {
	// D�finissez ici les variables d'instance n�cessaires � vos engagements;
	// Vous pouvez �galement les saisir automatiquement du pr�sentoir
	// � l'aide du menu contextuel "Pr�sentoir --> Engagements".
	// Notez cependant que ce dernier ne peut saisir les objets primitifs
	// du pr�sentoir (les objets sans constructeur, comme int, float, etc.).

	PileI pi;
	
	/**
	 * Constructeur de la classe-test Pile4Test
	 */
	public Pile4Test() {
	}

	/**
	 * Met en place les engagements.
	 * 
	 * M�thode appel�e avant chaque appel de m�thode de test.
	 */
	protected void setUp() // throws java.lang.Exception
	{
		// Initialisez ici vos engagements


       pi = new question2.Pile4(PileI.CAPACITE_PAR_DEFAUT);
    }


    public void test_empiler()  throws Exception {

        pi.empiler("b");
        pi.empiler("a");
        pi.empiler("c");
        pi.empiler("d");
        pi.empiler("e");
        assertEquals("[e, d, c, a, b]",pi.toString());
    }
    
    public void test_depiler()  throws Exception {

        pi.empiler("b");
        pi.empiler("a");
        pi.empiler("c");
        pi.empiler("d");
        pi.empiler("e");
        pi.depiler();
        assertEquals("[d, c, a, b]",pi.toString());
    }
    
    public void test_sommet() throws Exception {
        PileI p = new question2.Pile4(3);
        assertEquals(true, p.estVide());

        p.empiler(new Integer(3));
        assertEquals(" sommet ?? ", new Integer(3), p.sommet());
        assertEquals(1, p.taille());
        assertEquals(" depiler ?? ", new Integer(3), p.depiler());
        assertEquals(0, p.taille());
    }
    
    public void test_estVide()  throws Exception {

        assertTrue(pi.estVide());
    }
    
    public void test_estPleine()  throws Exception {

    	pi = new question2.Pile4(1);
    	
    	pi.empiler("a");
    	
        assertTrue(pi.estPleine());
    }
    
    public void test_equals() throws Exception {
    	
    	PileI pe = new question2.Pile4(PileI.CAPACITE_PAR_DEFAUT);

        pi.empiler(3);
        pi.empiler(2);
        pi.empiler(1);

        pe.empiler(3);
        pe.empiler(2);
        pe.empiler(1);

        assertTrue("�galit� de deux piles ? ", pi.equals(pe));
        assertTrue("�galit� de deux piles ? ", pi.equals(pe));
        assertTrue("�galit� de deux piles ? ", pi.equals(pe));

        pe.empiler(1);
        assertFalse("�galit� de deux piles ? ", pi.equals(pe));

    }
    
    public void test_toString() throws Exception {
        PileI pile1 = new question2.Pile4(3);
        assertEquals("toString incorrect ? ", "[]", pile1.toString());
        pile1.empiler(4);
        assertEquals("toString incorrect ? ", "[4]", pile1.toString());
        pile1.empiler(5);
        assertEquals("toString incorrect ? ", "[5, 4]", pile1.toString());
        pile1.empiler(3);
        assertEquals("toString incorrect ? ", "[3, 5, 4]", pile1.toString());

    }
    
  public void test_Pile_equals_contenu_different() throws Exception {
    	
    	PileI pe = new question2.Pile4(PileI.CAPACITE_PAR_DEFAUT);

        pi.empiler(3);
        pi.empiler(2);
        pi.empiler(1);

        pe.empiler("3");
        pe.empiler("2");
        pe.empiler("1");

        assertFalse("�galit� de deux piles ? ", pi.equals(pe));
        assertFalse("�galit� de deux piles ? ", pi.equals(pe));
        assertTrue("�galit� de deux piles ? ", pi.equals(pi));

    }
      
	/**
	 * Supprime les engagements
	 * 
	 * M�thode appel�e apr�s chaque appel de m�thode de test.
	 */
	protected void tearDown() // throws java.lang.Exception
	{
		// Lib�rez ici les ressources engag�es par setUp()
	}

}
