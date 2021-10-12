package question1;

 

public class Clams extends PizzaDecorator{
  public Clams(Pizza p){
    super(p);
  }
  
	public String getDescription(){
	  return pizza.getDescription() + ", clams";
	}
	 
  public double cost(){
    return pizza.cost() + 3.30;
  }
}
