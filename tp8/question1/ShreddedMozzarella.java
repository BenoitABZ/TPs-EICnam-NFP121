package question1;

 



public class ShreddedMozzarella extends PizzaDecorator{
  public ShreddedMozzarella(Pizza p){
    super(p);
  }
  
	public String getDescription(){
	  return pizza.getDescription() + ", Shredded Mozzarella";
	}
	 
  public double cost(){
    return pizza.cost() + .70;
  }
}
