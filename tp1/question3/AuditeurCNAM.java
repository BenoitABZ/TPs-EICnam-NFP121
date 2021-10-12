package question3;

/**
 * NFP121 TpIntroduction, usage de BlueJ et du "Submitter".
 * 
 * @version septembre 2009
 * @author � compl�ter
 * @see java.lang.String, java.lang.Math
 */
public class AuditeurCNAM {
	/** l'attribut nom de chaque auditeur. */
	private String nom;
	/** l'attribut prenom de chaque auditeur. */
	private String prenom;
	/** l'attribut matricule de chaque auditeur. */
	private String matricule;

	/**
	 * "Cr�ation", le constructeur d'un auditeur avec son nom, son pr�nom et son
	 * matricule.
	 * 
	 * @param nom       le nom de l'auditeur
	 * @param prenom    son pr�nom
	 * @param matricule sur la carte d'inscription, pr�s de la photo
	 */
	public AuditeurCNAM(String nom, String prenom, String matricule) {
		this.nom = nom;
		this.prenom = prenom;
		this.matricule = matricule;
	}

	/**
	 * le login au Cnam : 6 premi�res lettres du nom suivies de la premi�re lettre
	 * du pr�nom s�par�es de '_' . le login retourn� est en minuscules, le trait
	 * d'union, ou sp�ciaux <i>(pour unix)</i> sont remplac�s par des '_' pas de
	 * caract�res accentu�s pour le login voir les classes pr�d�fines,
	 * java.lang.String : les m�thodes replaceAll, toLowerCase et substring
	 * java.lang.Math : la m�thode min<br>
	 * <b>BlueJ : Menu Aide</b>
	 * 
	 * @return le login du Cnam simplifi�, sans les adaptations dues aux
	 *         homonymes...
	 */
	public String login() {

		int min = Math.min(6, nom.length());

		String sixFirstCharNom = nom.substring(0, min);

		char firstCharPrenom = prenom.charAt(0);

		String login = (sixFirstCharNom + "_" + firstCharPrenom);

		login = login.replaceAll("�", "e").replaceAll(" ", "_").replaceAll("-", "_").replaceAll("\"", "_")
				.replaceAll("�", "e").toLowerCase();

		System.out.println(login);

		return login;
	}

	/**
	 * Lecture du nom de l'auditeur.
	 * 
	 * @return son nom
	 */
	public String nom() {
		return nom;
	}

	/**
	 * Lecture du pr�nom de l'auditeur.
	 * 
	 * @return son pr�nom
	 */
	public String prenom() {
		return prenom;
	}

	/**
	 * Lecture du matricule de l'auditeur.
	 * 
	 * @return son matricule
	 */
	public String matricule() {
		return matricule;
	}

	/**
	 * m�thode toString ( m�thode red�finie de la classe Object).
	 * 
	 * @return la concat�nation du nom, du pr�nom et du login, selon cette syntaxe
	 *         <code>nom() + " " + prenom() +  " login : " + login()</code>
	 */
	@Override
	public String toString() {
		return nom() + " " + prenom() + " login : " + login();
	}

}
