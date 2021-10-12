package exemple_kindergarten.specification;

// import pl.koziarz.specificationexample.abstracts.specification.AbstractSpecification;
// import pl.koziarz.specificationexample.abstracts.specification.Specification;
// import pl.koziarz.specificationexample.domain.entity.Child;
// import pl.koziarz.specificationexample.domain.entity.Toy;
import exemple_kindergarten.entity.Child;
import exemple_kindergarten.entity.Toy;
import question1.SpecificationI;
//public class ChildLikesSpecifiedToySpecification extends AbstractSpecification<Child> {
public class ChildLikesSpecifiedToySpecification implements SpecificationI<Child>{
	private SpecificationI<Toy> specification;
	
	public ChildLikesSpecifiedToySpecification(SpecificationI<Toy> specification) {
		super();
		this.specification = specification;
	}
	public ChildLikesSpecifiedToySpecification(){}
	public void setSpecification(SpecificationI<Toy> specification) {
		this.specification = specification;
	}

	@Override
	public boolean isSatisfiedBy(Child c) {
		for( Toy t : c.getFavouriteToys()) {
			if( specification.isSatisfiedBy(t) )
				return true;
		}
		return false;
	}

}
