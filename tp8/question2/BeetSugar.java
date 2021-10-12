package question2;

/**
 * implémentation du décorateur concret BeetSugar
 * 
 * herite de CondimentDecorator, le décorateur abstrait, héritant lui meme de Beverage
 * 
 * extrait de http://www.oreilly.com/catalog/hfdesignpat/
 */
public class BeetSugar extends CondimentDecorator {

	//le décorateur hérite de Beverage mais prend également en paramètre Bevrage --> tout est Beverage
	public BeetSugar(Beverage beverage) {
		//affectation du beverage à décoré
		super(beverage);

	}

	@Override
	public String getDescription() {
		//super.getDescription = beverage.getDescription --> aggrégation des descriptions
		return super.getDescription() + ", Beet Sugar";
	}

	@Override
	public double cost() {
		//super.getCost = beverage.getCost--> aggrégation des couts
		return .10 + super.cost();
	}

}