package exemple_kindergarten.specification;

// import pl.koziarz.specificationexample.abstracts.specification.AbstractSpecification;
// import pl.koziarz.specificationexample.abstracts.specification.Specification;
// import pl.koziarz.specificationexample.domain.entity.Toy;
// import pl.koziarz.specificationexample.domain.entity.ToyType;
import exemple_kindergarten.entity.Toy;
import exemple_kindergarten.entity.ToyType;
import question1.SpecificationI;
//public class ToyTypeNameSpecification extends AbstractSpecification<ToyType> {
public class ToyTypeNameSpecification implements SpecificationI<ToyType>{
    private String name;

    public ToyTypeNameSpecification(String name) {
        super();
        this.name = name;
    }

    public ToyTypeNameSpecification(){}

    public void setName(String name) {
        this.name=name;
    }

    @Override
    public boolean isSatisfiedBy(ToyType t) {
        return t.getName().equals(name);
    }

}
