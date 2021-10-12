package question3;

import question1.CommandeI;
import question1.EvenementI;
/**
 * @author Votre nom, prénom    // a completer
 */
public class MaillonPrecipitation extends Maillon<EvenementI>{

    public MaillonPrecipitation(Maillon<EvenementI> maillon){
    	super(maillon);
    }

    public MaillonPrecipitation(){
    	super();
    }

    public boolean executer(EvenementI evenement){
        System.out.print(evenement.getTheme() + ", precipitation: ");
        System.out.println(evenement.getParametre("precipitation").toString());
        return super.executer(evenement);
    }
    
        public String toString(){
        return "Precipitation" + "(" +  super.toString() + ")";
    }
}