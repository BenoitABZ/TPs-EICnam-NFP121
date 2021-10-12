package question1;

public class TRUE<E> implements SpecificationI<E>{
    
 public boolean isSatisfiedBy(E e){
     return true;
    }
    
   public String toString(){
       return "TRUE";
    }

}
