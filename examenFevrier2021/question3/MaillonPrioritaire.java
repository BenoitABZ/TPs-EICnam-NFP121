package question3;

import question1.CommandeI;
import question1.EvenementI;
/**
 * @author Votre nom, prénom    // a completer
 */
public class MaillonPrioritaire extends Maillon<EvenementI>{
	
    private int priorite;

    public MaillonPrioritaire(){
               super();
    }

    public MaillonPrioritaire(int priorite,Maillon<EvenementI> successeur){
    	super(successeur);
        this.priorite = priorite;
    }

    public void setPriorite(int priorite){
        this.priorite = priorite;
    }

    public boolean executer(EvenementI evenement){
    	if (evenement.getPriorite() >= this.priorite) {

			return false;
		}else {
			
			return super.executer(evenement);
		}
    }

    public String toString(){
    	return "Prioritaire" + "(" +  super.toString() + ")";
    }
}
