package question1;


public class FALSE<E> implements SpecificationI<E>{
    
    public boolean isSatisfiedBy(E e){
        return false;
    }

   public String toString(){
       return "FALSE";
    }
}
