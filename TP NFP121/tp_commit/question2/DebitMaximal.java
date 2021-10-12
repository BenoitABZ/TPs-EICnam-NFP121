package question2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import question1.Contributeur;
import question1.Cotisant;
import question1.GroupeDeContributeurs;
import question1.Visiteur;

/**
 * Le visiteur DebitMaximal permet d’obtenir la valeur maximale que l’on peut
 * débiter d’un cotisant (un contributeur isolé ou un groupe de contributeurs).
 * 
 * Alternative au transactionel car permet de prevenir tout débit qui
 * impacterait négativement un solde dans un groupe de contributeur avec
 * l'utilisation d'une simple condition.
 * 
 * 
 * @author benoit
 *
 */
public class DebitMaximal implements Visiteur<Integer> {

	public Integer visite(Contributeur c) {
		return c.solde();
	}

	/**
	 * Ajout de manière récursive du min solde de chaque contributeur dans chaque groupe de contributeur
	 * 
	 * @return Integer le plus petit solde du groupe
	 */
	public Integer visite(GroupeDeContributeurs g) {
		int res = 0;

		List<Integer> list = new ArrayList<>();

		for (Cotisant c : g.getChildren()) {

			list.add(c.accepter(this));

			Collections.sort(list);

		}

		res = list.get(0);

		return res;
	}

}