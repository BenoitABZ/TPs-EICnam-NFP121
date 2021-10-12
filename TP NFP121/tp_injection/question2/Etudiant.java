package question2;

/**
 * Exemple du patron strategy avec un étudiant qui dispose de plusieurs strategies pour reviser ses examens
 * 
 * @author benoit
 *
 */
public class Etudiant {

	public String nom;

	public int resultat;

	public void setStrategie(Strategy strategie) {

		this.resultat = strategie.reviser();

	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getResultat() {
		return resultat;
	}

	public void setResultat(int resultat) {
		this.resultat = resultat;
	}

}
