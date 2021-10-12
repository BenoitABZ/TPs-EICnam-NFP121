package question3;

import question1.CommandeI;
import question1.EvenementI;
/**
 * @author Votre nom, prénom    // a completer
 */
public class MaillonDate extends Maillon<EvenementI>{
 
    public MaillonDate(){
		super();
    }
    public MaillonDate(Maillon<EvenementI> successeur){
        super(successeur);
    }

   public boolean executer(EvenementI evenement){
     System.out.print(evenement.getTheme() + ", date: ");
     System.out.println(evenement.getParametre("date").toString());
     return super.executer(evenement);
   }
   
    public String toString(){
    	return "Date" + "(" +  super.toString() + ")";
    }
}
