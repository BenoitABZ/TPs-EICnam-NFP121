package question1;

 
public class DeuxPizzas extends junit.framework.TestCase{
	private question1.PizzaSolo solo;

	public void setUp(){
		solo = new question1.PizzaSolo();
	}
	
 	public void testPizzaClassicWithHamAndMushrooms(){
 	  Pizza pizza = new Classic();
 	  pizza = new Ham(pizza);
    pizza = new Mushrooms(pizza);
	  assertEquals(7.5,pizza.cost(),0.1);
	}
	
	public void testPizzaSoloWithFreshMozarellaParmesan(){
	  assertEquals(3.8,new Parmesan(new FreshMozarella(new PizzaSolo())).cost(),0.1);
	}
	
}
