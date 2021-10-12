package question1;

 

public class Ham extends PizzaDecorator{
	public Ham(Pizza p){
	  super(p);
	}

	public String getDescription(){
	  return pizza.getDescription() + ", ham";
	}
	 
  public double cost(){
    return pizza.cost() + 1.50;
  }
}
