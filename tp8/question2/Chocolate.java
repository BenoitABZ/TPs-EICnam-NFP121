package question2;

/**
 * implémentation de la boisson concrète Chocolate 
 * 
 * hérite de Beverage --> tout est Beverage
 * 
 * extrait de http://www.oreilly.com/catalog/hfdesignpat/
 */
public class Chocolate extends Beverage {
	public Chocolate() {
		//modification de l'attribut "description" dans le constructeur de tous les Beverage concrets
		description = "Chocolate";
	}
    
	//Override du prix conformement à la superclasse == 2.10
	@Override
	public double cost() {
		return 2.10;
	}

}
