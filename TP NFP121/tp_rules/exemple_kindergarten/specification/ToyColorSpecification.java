package exemple_kindergarten.specification;

// import pl.koziarz.specificationexample.abstracts.specification.AbstractSpecification;
// import pl.koziarz.specificationexample.domain.entity.Toy;
import exemple_kindergarten.entity.Toy;
import question1.SpecificationI;
// public class ToyColorSpecification extends AbstractSpecification<Toy> {
public class ToyColorSpecification implements SpecificationI<Toy> {
    private String color;

    public ToyColorSpecification(String color) {
        super();
        this.color = color;
    }

    public ToyColorSpecification(){}

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfiedBy(Toy t) {
        return t.getColor().equals(color);
    }

}
