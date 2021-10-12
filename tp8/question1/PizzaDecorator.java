package question1;

 

public abstract class PizzaDecorator implements Pizza{
	protected Pizza pizza;

	public PizzaDecorator(Pizza pizza){
		this.pizza = pizza;
	}

  public abstract String getDescription();
  public abstract double cost();
  
}
