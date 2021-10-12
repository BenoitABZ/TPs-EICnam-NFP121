
/**
 * D�crivez votre classe Carre ici.
 *
 * @author (votre nom)
 * @version (un num�ro de version ou une date)
 */
public class Carre extends PolygoneRegulier
{

	public Carre(int longueurDuCote) {
		super(longueurDuCote,4);
		
	}
	
	@Override
	public int surface() {
		
		return getLongueurDuCote()*getLongueurDuCote();
	}
	
	@Override
	public void setNombreDeCotes(int nombreDeCote) {
		
		this.setNombreDeCotes(4);
	}
	
}
