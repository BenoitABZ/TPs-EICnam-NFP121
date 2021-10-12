package question1;

 


public class SlicedBlackOlives extends PizzaDecorator{
  public SlicedBlackOlives(Pizza p){
    super(p);
  }
  
	public String getDescription(){
	  return pizza.getDescription() + ", Sliced Black Olives";
	}
	 
  public double cost(){
    return pizza.cost() + .10;
  }
}
