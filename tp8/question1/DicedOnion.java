package question1;

 


public class DicedOnion extends PizzaDecorator{
	public DicedOnion(Pizza p){
	  super(p);
	}

	public String getDescription(){
	  return pizza.getDescription() + ", Diced Onion";
	}
	 
  public double cost(){
    return pizza.cost() + 0.40;
  }
}
