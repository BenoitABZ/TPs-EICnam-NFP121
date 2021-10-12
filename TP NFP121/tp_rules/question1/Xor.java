package question1;


public class Xor<E> extends Binary<E>{
 
    public Xor(SpecificationI<E> spec1, SpecificationI<E> spec2){
        super(spec1,spec2);
    }

    public Xor(){}
    
    public boolean isSatisfiedBy(E e){
    	return spec1.isSatisfiedBy(e) ^ spec2.isSatisfiedBy(e);
    }

     public String toString(){
        return "(" + spec1 + ") xor (" + spec2 +")";
    }
}