package question2;

import question1.CommandeI;
import question1.EvenementI;
/**
 * @author Votre nom, prénom    // a completer
 */
public class DecorateurPrecipitation extends CommandeDecorateur<EvenementI>{

    public DecorateurPrecipitation(CommandeI<EvenementI> commande){
               super(commande);
    }

    public DecorateurPrecipitation(){}

    public boolean executer(EvenementI evenement){
        System.out.print(evenement.getTheme() + ", precipitation: ");
        System.out.println(evenement.getParametre("precipitation").toString());
        return super.executer(evenement);
    }

    public String toString(){
        return "Precipitation" + "(" +  super.toString() + ")";
    }
}