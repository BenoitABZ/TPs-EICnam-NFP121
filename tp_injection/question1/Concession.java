package question1;

import java.util.List;

public class Concession {

	private List<Voiture> voitures;
	
	private String name;

	public List<Voiture> getVoitures() {
		return voitures;
	}
	
	public void setAutomobile(Voiture voiture) {
		voitures.add(voiture);
	}

	public void setVoitures(List<Voiture> voitures) {
		this.voitures = voitures;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
