package question2;

import java.util.Iterator;

import question1.Contributeur;
import question1.Cotisant;
import question1.GroupeDeContributeurs;
import question1.Visiteur;

/**
 * Le patron visiteur permet de parcourir une structure composite et d’exécuter
 * une opération particulière en fonction du nœud visité.
 * 
 * Le visiteur CompositeValide permet de valider une instance du composite. Le
 * solde de chaque contributeur doit être supérieur ou égal à un nombre transmis
 * en paramètre et il n’existe pas de groupe n’ayant pas de contributeurs.
 * 
 * @author benoit
 *
 */
public class CompositeValide implements Visiteur<Boolean> {

	private int valeur;

	public CompositeValide(int valeur) {

		this.valeur = valeur;
	}

	public CompositeValide() {
		this(0);
	}

	public Boolean visite(Contributeur c) {

		// contributeur valide si son solde est supérieur à la valeur passée en paramètre constructeur
		if (c.solde() >= valeur) {
			return true;

		} else {
			return false;

		}
	}

	public Boolean visite(GroupeDeContributeurs g) {
		boolean res = false;

		Iterator<Cotisant> it = g.iterator();

		while (it.hasNext()) {

			Cotisant cot = it.next();
			// execute de nouveau visite(GroupeDeContributeur) ou visite(Contributeur)
			res = cot.accepter(this);

			if (res == false) {

				break;
			}

		}
		// verifie qu'un groupe de contributeurs n'est pas vide
		if (g.getChildren().size() == 0) {

			res = false;
		}

		return res;
	}
}