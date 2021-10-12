package question1;

 

public class Mushrooms extends PizzaDecorator{
	public Mushrooms(Pizza p){
	  super(p);
	}

	public String getDescription(){
	  return pizza.getDescription() + ", mushrooms";
	}
	 
  public double cost(){
    return pizza.cost() + 1.0;
  }
}
