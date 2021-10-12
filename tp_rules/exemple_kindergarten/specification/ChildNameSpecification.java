package exemple_kindergarten.specification;

// import pl.koziarz.specificationexample.abstracts.specification.AbstractSpecification;
// import pl.koziarz.specificationexample.domain.entity.Child;
import exemple_kindergarten.entity.Child;
import question1.SpecificationI;
//public class ChildNameSpecification extends AbstractSpecification<Child> {
public class ChildNameSpecification implements SpecificationI<Child> {
    private String name;

    public ChildNameSpecification(String name) {
        this.name=name;
    }

    public ChildNameSpecification(){}

    public void setName(String name) {
        this.name=name;
    }

    @Override
    public boolean isSatisfiedBy(Child t) {
        return t.getName().equals(name);
    }

}
