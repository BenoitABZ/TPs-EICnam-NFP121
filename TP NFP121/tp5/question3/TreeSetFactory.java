package question3;

import java.util.Set;

import java.util.TreeSet;

//La méthode create() de l'interface renvoie un TreeSet.
public class TreeSetFactory<T extends Comparable<T>> implements Factory<Set<T>>{

	@Override
	public Set<T> create() {
		
		return new TreeSet<T>();
	}

}
