package question1;

 

public class Pepperoni extends PizzaDecorator{
  public Pepperoni(Pizza p){
    super(p);
  }
  
	public String getDescription(){
	  return pizza.getDescription() + ", pepperoni";
	}
	 
  public double cost(){
    return pizza.cost() + .20;
  }
}
