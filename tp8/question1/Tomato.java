package question1;

 


public class Tomato extends Sauce{

  public Tomato(Pizza pizza){
		super(pizza);
	}

	public String getDescription(){
	  return pizza.getDescription() + ", tomato sauce";
	}
	 
  public double cost(){
    return pizza.cost() + .70;
  }
}
