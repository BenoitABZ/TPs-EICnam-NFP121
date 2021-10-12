package question2;

public class ChocolateTest extends junit.framework.TestCase {
	private question2.Chocolate choco;

	public void setUp() {
		choco = new question2.Chocolate();
	}

	public void testChocolate() {
		assertEquals(2.1, choco.cost(), 0.1);
	}

	public void testChocolateWhip() {
		question2.Beverage chocoSimpleWhip = new question2.Whip(choco);

		assertEquals(2.2, chocoSimpleWhip.cost(), 0.01);
	}

	public void testChocolateWhipWhip() {
		question2.Beverage chocoDoubleWhip = new question2.Whip(new question2.Whip(choco));

		assertEquals(2.3, chocoDoubleWhip.cost(), 0.01);
	}

	public void testChocolateWhipSoyMocha() {
		question2.Beverage chocoWhipSoyMocha = new question2.Mocha(new question2.Soy(new question2.Whip(choco)));

		assertEquals(2.55, chocoWhipSoyMocha.cost(), 0.01);
	}
	
	//ajout de ce test qui décore un déca avec du lait
	public void testDecaMilk() {
		Beverage decaMilk = new Milk(new Decaf());
		
		System.out.println(decaMilk.toString());

		assertEquals(1.15, decaMilk.cost(), 0.01);
	}

}
