package question1;

 

public class Parmesan extends PizzaDecorator{

  public Parmesan(Pizza p){
    super(p);
  }
  
	public String getDescription(){
	  return pizza.getDescription() + ", parmesan";
	}
	 
  public double cost(){
    return pizza.cost() + .30;
  }

}
