package question2;

import question1.CommandeI;
import question1.EvenementI;
/**
 * @author Votre nom, prénom    // a completer
 */
public class DecorateurHumidite extends CommandeDecorateur<EvenementI>{
 
    public DecorateurHumidite(CommandeI<EvenementI> commande){
    	super(commande);
    }
    
    public DecorateurHumidite(){}

   public boolean executer(EvenementI evenement){
     System.out.print(evenement.getTheme() + ", humidite: ");
     System.out.println(evenement.getParametre("humidite").toString());
     
     if((Integer)evenement.getParametre("humidite") > 75) {

        return false;
     }else {
    	 
    	 return super.executer(evenement);
     }
   }
   
    public String toString(){
    	return "Humidite" + "(" +  super.toString() + ")";
    }
}