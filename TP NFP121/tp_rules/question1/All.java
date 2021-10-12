package question1;

import java.util.Iterator;

public class All<E> extends CompositeSpecification<E> {

	public boolean isSatisfiedBy(E e) {

		boolean bool = true;

		Iterator<SpecificationI<E>> it = this.specifications.iterator();
		while (it.hasNext()) {

			bool = bool && it.next().isSatisfiedBy(e);

			if (bool == false) {

				return bool;
			}

		}

		return bool;
	}

	public String toString() {
		return "all" + super.toString();
	}
}