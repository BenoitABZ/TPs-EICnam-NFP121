package question3;

import question1.*;
import java.util.*;

/**
 * L'opération de débit sur un groupe de contributeurs en question 1 engendre
 * dans certains cas une erreur: il suffit que l'un des contributeurs ne possède
 * pas la somme à débiter pour qu'une exception soit levée, ainsi les sommes
 * débitées de certains contributeurs ne sont pas restituées.
 * 
 * L'opération de débit sur un groupe de contributeurs doit être atomique et
 * effectuée au sein d'une transaction, la classe abstraite AbstractTransaction
 * reflète cette notion et utilise le patron Template Method. Les opérations
 * beginTransaction, endTransaction et rollbackTransaction sont laissées à la
 * responsabilité des sous classes.
 * 
 * Le patron memento permet la mise en oeuvre des méthodes beginTransaction()
 * --> le gardien push sur sa pile de memento(sauvegarde) et rollback() --> le
 * gardien pop de sapile de memento (restitution de l'état antérieur).
 * 
 * Le memento utilise le patron visiteur: visiteurs pour la sauvegarde et pour
 * la restitution du composite
 * 
 * @author benoit
 *
 */
public class Memento {

	// création d'une pile au niveau global pour stocker(sauvegarde d'un composite)/destocker(restitution d'un composite) les Cotisant
	private Stack<Cotisant> cCopie;

	public Memento(Cotisant c) {

		cCopie = new Stack<>();
		// ce visiteur permet de stocker dans la stack l'ensemble des élément d'un composite
		c.accepter(new VisitorComposite());

	}

	public void setState(Cotisant c) {
		// ce visiteur permet d'affecter les soldes préalablement sauvegardé
		c.accepter(new VisitorRestit());
	}

	// classe interne VisitorComposite avec accés à la stack
	private class VisitorComposite implements Visiteur<Cotisant> {

		@Override
		public Cotisant visite(Contributeur c) {
			Contributeur contributeur = new Contributeur(c.nom(), c.solde());
			// stockage des contributeurs uniquement
			// utilisation de la méthode add() et non push() pour synchronisation avec pop()
			cCopie.add(contributeur);

			return contributeur;
		}

		@Override
		public Cotisant visite(GroupeDeContributeurs g) {
			GroupeDeContributeurs groupe = new GroupeDeContributeurs(g.nom());

			for (Cotisant c : g.getChildren()) {

				c.accepter(this);

			}
			return groupe;
		}

	}

	// classe interne VisitorRestit avec accés à la stack
	private class VisitorRestit implements Visiteur<Cotisant> {

		@Override
		public Cotisant visite(Contributeur c) {

			// utilisation de la méthode élégante affecterSolde()
			c.affecterSolde(cCopie.pop().solde());
			return c;
		}

		@Override
		public Cotisant visite(GroupeDeContributeurs g) {

			for (Cotisant c : g.getChildren()) {

				c.accepter(this);

			}
			return g;
		}

	}

}