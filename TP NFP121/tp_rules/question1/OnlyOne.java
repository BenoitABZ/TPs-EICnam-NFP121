package question1;

import java.util.Iterator;

public class OnlyOne<E> extends CompositeSpecification<E> {

	public boolean isSatisfiedBy(E t) {
		boolean bool = true;

		int numberOfTrue = 0;

		Iterator<SpecificationI<E>> it = this.specifications.iterator();
		while (it.hasNext()) {

			bool = it.next().isSatisfiedBy(t);

			if (bool == true) {

				numberOfTrue++;

			}

		}

		if (numberOfTrue == 1) {

			bool = true;

			return bool;

		} else {

			bool = false;

		}

		return bool;
	}

	public String toString() {
		return "onlyOne" + super.toString();
	}
}