package question2;

import question1.CommandeI;
import question1.EvenementI;
/**
 * @author Votre nom, prénom    // a completer
 */
public class DecorateurDate extends CommandeDecorateur<EvenementI>{

    public DecorateurDate(CommandeI<EvenementI> commande){
                super(commande);
    }

    public DecorateurDate(){}

    public boolean executer(EvenementI evenement){
        System.out.print(evenement.getTheme() + ", date: ");
        System.out.println(evenement.getParametre("date").toString());
        return super.executer(evenement);
        
        
    }

    public String toString(){
    	return "Date" + "(" +  super.toString() + ")";
    }
}
