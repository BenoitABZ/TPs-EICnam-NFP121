package question1;

 

public class RedPepper extends PizzaDecorator{
  public RedPepper(Pizza p){
    super(p);
  }
  
	public String getDescription(){
	  return pizza.getDescription() + ", Red Pepper";
	}
	 
  public double cost(){
    return pizza.cost() + .30;
  }
}
