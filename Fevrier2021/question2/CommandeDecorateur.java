package question2;

import question1.CommandeI;
/**
 * @author Votre nom, prénom    // a completer
 */
public abstract class CommandeDecorateur<E> implements CommandeI<E>{
    
	private CommandeI<E> commande;

    public CommandeDecorateur(CommandeI<E> commande){
    	
    	this.commande = commande;
        
    }
    
    public CommandeDecorateur(){}
    
    public void setCommande(CommandeI<E> commande){
    	
    	this.commande = commande;
        
    }

    public boolean executer(E e){
        return commande.executer(e);
    }
    
    public String toString(){
        return commande.toString();
    }
}
