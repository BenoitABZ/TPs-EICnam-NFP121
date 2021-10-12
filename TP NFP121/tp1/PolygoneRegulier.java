
/**
 * D�crivez votre classe PolygoneRegulier ici.
 *
 * @author (votre nom)
 * @version (un num�ro de version ou une date)
 */
public class PolygoneRegulier {

	private int longueurDuCote;
	private int nombreDeCotes;

	public PolygoneRegulier(int longueurDuCote, int nombreDeCotes) {
		super();
		this.longueurDuCote = longueurDuCote;
		this.nombreDeCotes = nombreDeCotes;
	}

	public int getLongueurDuCote() {
		return longueurDuCote;
	}

	public void setLongueurDuCote(int longueurDuCote) {
		this.longueurDuCote = longueurDuCote;
	}

	public int getNombreDeCotes() {
		return nombreDeCotes;
	}

	public void setNombreDeCotes(int nombreDeCotes) {
		this.nombreDeCotes = nombreDeCotes;
	}

	public int perimetre() {

		return longueurDuCote * nombreDeCotes;
	}

	public int surface() {

		return (int) (1 / 4.0 * nombreDeCotes * Math.pow(longueurDuCote, 2.0) * cotg(Math.PI / nombreDeCotes));
	}

	private static double cotg(double x) {

		return Math.cos(x) / Math.sin(x);
	}
}
