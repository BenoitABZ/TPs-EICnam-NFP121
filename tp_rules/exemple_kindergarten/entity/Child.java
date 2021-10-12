package exemple_kindergarten.entity;

import java.util.HashSet;
import java.util.Set;

// ajout pour femtoContainer
import java.util.Arrays;

public class Child {
	private String name;
	private int age;
	private Set<Toy> favouriteToys = new HashSet<>();
	
	public Child(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Child() {
	}
	
	public int getAge() {
		return age;
	}
	
	public Set<Toy> getFavouriteToys() {
		return favouriteToys;
	}
	
	public String getName() {
		return name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	//public void setFavouriteToys(Set<Toy> favouriteToys) {
	//	this.favouriteToys = favouriteToys;
	//}
	
	// ajout pour femtoContainer
	public void setFavouriteToys(Toy[] favouriteToys) {
		this.favouriteToys = new HashSet(Arrays.asList(favouriteToys));
	}
	// fin ajout pour femtoContainer
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Child [name=" + name + ", age=" + age + ", favouriteToys=" + favouriteToys + "]";
	}
	
}
