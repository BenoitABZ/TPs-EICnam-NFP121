package question2;

import java.util.HashSet;
import java.util.Set;

import question1.Contributeur;
import question1.Cotisant;
import question1.GroupeDeContributeurs;
import question1.Visiteur;

/**
 * La classe SansDoublon vérifie que les noms des groupes et des contributeurs
 * sont différents.
 * 
 * @author benoit
 *
 */
public class SansDoublon implements Visiteur<Boolean> {

	int nombre = 0;

	// création d'un set au niveau global (du visiteur)
	// un set n'accepte pas les doublons
	Set<String> set = new HashSet<>();

	public Boolean visite(Contributeur c) {
		boolean res = false;

		// utilisation du type de retour (boolean) de la méthode add()
		res = set.add(c.nom());

		return res;
	}

	public Boolean visite(GroupeDeContributeurs g) {
		boolean res = false;

		// sans doublon si un groupe de contributeur est vide
		if (g.getChildren().isEmpty()) {

			res = true;

			return res;

		}
		// ajout du groupe de contributeur dans le set
		res = set.add(g.nom());

		if (res == false) {

			return res;
		}

		for (Cotisant c : g.getChildren()) {
			// appel récursif à ce niveau...
			res = c.accepter(this);

			if (res == false) {

				return res;
			}

		}
		return res;
	}

}