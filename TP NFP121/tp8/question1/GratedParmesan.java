package question1;

 


public class GratedParmesan extends PizzaDecorator{
  public GratedParmesan(Pizza p){
    super(p);
  }
  
	public String getDescription(){
	  return pizza.getDescription() + ", Grated Parmesan";
	}
	 
  public double cost(){
    return pizza.cost() + .60;
  }
}
