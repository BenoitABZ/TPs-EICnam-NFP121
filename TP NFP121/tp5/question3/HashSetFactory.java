package question3;

import java.util.HashSet;
import java.util.Set;

//La méthode create() de l'interface renvoie un HashSet.
public class HashSetFactory<T> implements Factory<Set<T>> {

	@Override
	public Set<T> create() {

		return new HashSet<T>();
	}

}