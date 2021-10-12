package question3;

import java.util.Scanner;

// La liste des inscrits est obtenue via un service en ligne de la DSI
// les Champs fournis sont 	Nom	Prenom	Email 
//    un fichier au format CSV est téléchargeable
//
public class Auditeur {
	// Les champs, en version simplifiée
	private int id;
	private String nom;
	private String prenom;
	private String email;

	public Auditeur() {
	}

	public Auditeur(String nom, String prenom, String email) {
		this();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	public int getId() {
		return this.id;
	}

	public String getNom() {
		return this.nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public String getEmail() {
		return this.email;
	}

	private void setNom(String nom) {
		this.nom = nom;
	}

	private void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean equals(Object o) {
		Auditeur a = (Auditeur) o;
		return nom.equals(a.nom) && prenom.equals(a.prenom); // && e_mail ?
	}

	public int hashCode() {
		return nom.hashCode() + prenom.hashCode();
	}

	public static Auditeur parseAuditeur(String s) throws Exception {
		Auditeur a = new Auditeur();
		Scanner scanner = new Scanner(s);
		scanner.useDelimiter(";");

		String id = scanner.next();
		int idInt = Integer.parseInt(id);

		a.setId(idInt);
		a.setNom(scanner.next());
		a.setPrenom(scanner.next());
		a.setEmail(scanner.next());
		return a;
	}

	public String toString() {
		return Integer.toString(id) + ";" + getNom() + ";" + getPrenom() + ";" + getEmail();
	}

}
