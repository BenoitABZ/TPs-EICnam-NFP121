package exemple_alcool;



public class Bar{
	private String nom;
	private String lieu;

	
	public Bar(String nom, String lieu) {
		this.nom = nom;
		this.lieu = lieu;
	}

	public Bar() {
	}
	
	public String getLieu() {
		return lieu;
	}

	
	public String getNom() {
		return nom;
	}
	
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Bar [nom=" + nom + ", lieu=" + lieu + "]";
	}
	
}
