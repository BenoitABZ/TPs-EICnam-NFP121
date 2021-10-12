package question1;

import java.util.*;

/**
 * @author Votre nom, prénom // a completer
 * @see <a href=
 *      "https://docs.oracle.com/javase/7/docs/api/java/util/Comparator.html">Comparator</a>
 */
public class Commandes<E> extends CommandeA<E> {

	List<CommandeI<E>> list = new ArrayList<>();

	public Commandes(String nom) {
		super(nom);
	}

	/**
	 * Patron Strategie
	 * 
	 * Affectation éventuelle de la relation d'ordre. Un seul appel à cette méthode
	 * est permis, tout autre appel est sans effet.
	 * 
	 * @param comparator la relation d'ordre transmise
	 * @throws NullPointerException si comparator == null
	 */
	public void setComparator(Comparator<CommandeI<E>> comparator) {
		Collections.sort(list, comparator);
	}

	/**
	 * Ajout d'une commande à la liste.
	 * 
	 * @param commande la commande à ajouter
	 * @throws NullPointerException si commande == null
	 */
	public void addCommande(CommandeI<E> commande) {

		list.add(commande);
	}

	/**
	 * Ajout d'une table de commande à la liste.
	 * 
	 * @param commande la commande à ajouter
	 */
	public void addCommandes(CommandeI<E>[] commandes) {
		
		for(CommandeI<E> commande : commandes) {
			
			if (commande == null) {
				
				throw new NullPointerException();
			}
		}

		List<CommandeI<E>> arrayToList = Arrays.asList(commandes);
		list.addAll(arrayToList);

	}

	/**
	 * Ajout d'une table de commande à la liste. Syntaxe de type "Mutateur" pour
	 * femtoContainer/NFP121
	 * 
	 * @param commandes les commandes à ajouter
	 */
	public void setCommandes(CommandeI<E>[] commandes) {
		addCommandes(commandes);
	}

	@Override
	public boolean executer(E e) {
		boolean res = true;

		for (CommandeI<E> commande : this.list) {

			res = commande.executer(e);

		}

		return res;
	}
}
