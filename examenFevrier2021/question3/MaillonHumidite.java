package question3;

import question1.CommandeI;
import question1.EvenementI;

/**
 * @author Votre nom, prénom // a completer
 */
public class MaillonHumidite extends Maillon<EvenementI> {

	public MaillonHumidite(Maillon<EvenementI> maillon) {
		super(maillon);
	}

	public MaillonHumidite() {
		super();
	}

	public boolean executer(EvenementI evenement) {
		if (evenement.getParametre("humidite") != null) {
			System.out.print(evenement.getTheme() + ", humidite: ");
			System.out.println(evenement.getParametre("humidite").toString());

			if ((Integer) evenement.getParametre("humidite") >= 75) {

				return false;
			}

		}

		return super.executer(evenement);
	}

	public String toString() {
		return "Humidite" + "(" +  super.toString() + ")";
	}
}