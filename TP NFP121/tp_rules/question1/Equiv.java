package question1;


public class Equiv<E> extends Binary<E>{

    public Equiv(SpecificationI<E> spec1, SpecificationI<E> spec2){
        super(spec1,spec2);
    }

    public Equiv(){}

    public boolean isSatisfiedBy(E e){
        // p ==> q et q ==>p alors p <==> q 
        return new And<E>(new Implies<E>(spec1,spec2), new Implies<E>(spec2,spec1)).isSatisfiedBy(e);
    }

    public String toString(){
        return "(" + spec1 + ") <==> (" + spec2 +")";
    }
}