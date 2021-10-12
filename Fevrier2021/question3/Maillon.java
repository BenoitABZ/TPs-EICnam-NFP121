package question3;

import question1.CommandeI;

/**
 * Le patron chaine de responsabilité permet un meilleur découplage!
 * 
 * @author Votre nom, prénom // a completer
 */
public abstract class Maillon<E> implements CommandeI<E> {

	private Maillon<E> successeur;

	public Maillon(Maillon<E> successeur) {
		this.successeur = successeur;
	}

	public Maillon() {

		this.successeur = null;
	}

	public void setSuccesseur(Maillon<E> successeur) {
		this.successeur = successeur;
	}

	public Maillon<E> getSuccesseur() {
		return successeur;
	}

	public boolean executer(E e) {
		if (successeur != null) {
			return successeur.executer(e);
		} else
			return false;
	}

	public String toString() {
		return successeur.toString();
	}
}
