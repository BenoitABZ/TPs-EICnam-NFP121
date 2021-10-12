package question2;
/**
 * Beverage est le Component du pattern Décorateur
 * 
 * elle définit attributs et méthodes dont toutes classes (components concrets ou décorateurs) hériteront
 * 
 * extrait de http://www.oreilly.com/catalog/hfdesignpat/
 */
public abstract class Beverage {
	String description = "Unknown Beverage";
  
	public String getDescription() {
		return description;
	}
 
	public abstract double cost();
	
		public String toString(){
		  //implémentation de la méthode toString() conformement au cahier des charges
		  //arrondit aux centièmes
		  return getDescription() + " $" + Math.round(cost() * 100.0) / 100.0;
		}
	

}
