package exemple_kindergarten.specification;

// import pl.koziarz.specificationexample.abstracts.specification.AbstractSpecification;
// import pl.koziarz.specificationexample.domain.entity.Toy;
import exemple_kindergarten.entity.Toy;
import question1.SpecificationI;
//public class ToyWeightBetweenSpecification extends AbstractSpecification<Toy> {
public class ToyWeightBetweenSpecification implements SpecificationI<Toy>{	
	private int min;
	private int max;
	
	public ToyWeightBetweenSpecification(int min, int max) {
		super();
		this.min = min;
		this.max = max;
	}
	
	public ToyWeightBetweenSpecification(){}
	public void setMin(int min){
	    this.min=min;
	   }
	public void setMax(int max){
	    this.max=max;
	   }
	
	public boolean isSatisfiedBy(Toy t) {
		return ( t.getWeight() > min && t.getWeight() < max );
	}

}
