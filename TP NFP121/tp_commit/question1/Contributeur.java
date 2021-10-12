package question1;

/**
 * Represente le compte d'un contributeur
 * 
 * Equivalent "Leaf" du pattern Composite
 * 
 * @author benoit
 *
 */
public class Contributeur extends Cotisant {

	private int solde;

	/**
	 * Constructeur de Contributeur
	 * 
	 * @param nom   nom du contributeur
	 * 
	 * @param somme 
	 * somme initiale affectée au compte du contributeur
	 * somme<0
	 * 
	 * @throws RuntimeException
	 */
	public Contributeur(String nom, int somme) {
		super(nom);
		if (somme >= 0) {
			this.solde = somme;
		} else {
			this.solde = 0;

			throw new RuntimeException("nombre négatif !!!");

		}
	}

	public int solde() {
		return this.solde;
	}

	public int nombreDeCotisants() {
		return 1;
	}

	/**
	 * Décrémente le solde de la somme passé en paramètre
	 * 
	 * @param int somme 
	 * somme>0
	 * 
	 * @throws SoldeDebiteurException 
	 * quand solde < 0 et annulation de l'opération
	 */
	public void debit(int somme) throws SoldeDebiteurException {

		if (somme < 0)
			throw new RuntimeException("nombre négatif !!!");

		int sommeSave = this.solde;
		this.solde -= somme;

		if (solde < 0) {

			solde = sommeSave;

			throw new SoldeDebiteurException("solde négatif !!!");
		}

	}

	/**
	 * Incrémente le solde de la somme passé en paramètre
	 * 
	 * @param int somme somme>0
	 * 
	 */
	public void credit(int somme) {

		if (somme < 0)
			throw new RuntimeException("nombre négatif !!!");

		this.solde += somme;
	}

	/**
	 * throws RuntimeException new RuntimeException("nombre négatif !!!");
	 */
	public void affecterSolde(int somme) {

		// if (somme < 0)
		// throw new RuntimeException("nombre négatif !!!");

		try {
			debit(solde());
			credit(somme);// mode élégant ...
		} catch (SoldeDebiteurException sde) {
			// exception peu probable
			this.solde = somme; // mode efficace ...
		}
	}

	public <T> T accepter(Visiteur<T> visiteur) {
		return visiteur.visite(this);
	}

	public String toString() {
		return "<Contributeur : " + nom + "," + solde + ">";
	}

}
