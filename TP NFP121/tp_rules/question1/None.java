package question1;

import java.util.*;

public class None<E> extends CompositeSpecification<E>{
    @Override
    public boolean isSatisfiedBy(E e){
    	
    	boolean bool = false;
    	
    	List<Boolean> bools = new ArrayList<>();

		Iterator<SpecificationI<E>> it = this.specifications.iterator();
		while (it.hasNext()) {
			
			if(it instanceof TRUE || it instanceof FALSE) {

			bool = it.next().isSatisfiedBy(e);

		    bools.add(bool);
			}
						
		}
			
			if(bools.contains(true)) {
				
				return false;
			}else {
				
				return true;
			}
			
		}   
    
    public String toString(){
        return "none" + super.toString();
    }
}