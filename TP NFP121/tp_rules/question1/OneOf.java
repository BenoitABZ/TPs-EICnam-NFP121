package question1;

import java.util.Iterator;

public class OneOf<E> extends CompositeSpecification<E>{
  
    public boolean isSatisfiedBy(E e){
		boolean bool = false;

		Iterator<SpecificationI<E>> it = this.specifications.iterator();
		while (it.hasNext()) {

			bool = it.next().isSatisfiedBy(e);

			if (bool == true) {

				return bool;
			}

		}

		return bool;
	}
    
    
    public String toString(){
        return "oneOf" + super.toString();
    }
}