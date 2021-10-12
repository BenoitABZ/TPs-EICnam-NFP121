package question1;

 

public class Garlic extends Sauce{
  public Garlic(Pizza pizza){
		super(pizza);
	}

	public String getDescription(){
	  return pizza.getDescription() + ", garlic sauce";
	}
	 
  public double cost(){
    return pizza.cost() + .77;
  }
}
