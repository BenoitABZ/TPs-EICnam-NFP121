package exemple_kindergarten.specification;

// import pl.koziarz.specificationexample.abstracts.specification.AbstractSpecification;
// import pl.koziarz.specificationexample.domain.entity.Child;
import exemple_kindergarten.entity.Child;
import question1.SpecificationI;
// public class ChildAgeBetweenInclSpecification extends AbstractSpecification<Child> {
public class ChildAgeBetweenInclSpecification implements SpecificationI<Child> {
	private int min;
	private int max;
	
	public ChildAgeBetweenInclSpecification( int min, int max ) {
		this.min=min;
		this.max=max;
	}
	public ChildAgeBetweenInclSpecification(){}
	public void setMin(int min){
	    this.min=min;
	   }
	public void setMax(int max){
	    this.max=max;
	   }
	@Override
	public boolean isSatisfiedBy(Child t) {
		return t.getAge()>=min && t.getAge()<=max;
	}
	
	
	
}
