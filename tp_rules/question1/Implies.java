package question1;

public class Implies<E> extends Binary<E>{

    public Implies(SpecificationI<E> spec1, SpecificationI<E> spec2){
        super(spec1,spec2);
    }

    public Implies(){}

    public boolean isSatisfiedBy(E e){
        // p ==> q <==> not(p) or q
        return new Or<E>(new Not<E>(spec1),spec2).isSatisfiedBy(e);
    }

    public String toString(){
        return "(" + spec1 + ") ==> (" + spec2 +")";
    }
}