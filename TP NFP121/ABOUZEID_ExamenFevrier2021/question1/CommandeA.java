package question1;


public abstract class CommandeA<E> implements CommandeI<E>{
    private String nom;
    private int priorite;
    
    public static final int PRIORITE_PAR_DEFAUT = 5;
    
    public CommandeA(String nom){
        this.nom = nom;
    }
    
    public String getNom(){return nom;}
        
    public int getPriorite(){return priorite;}
        
   
    public void setPriorite(int priorite){
        this.priorite = priorite;
    }
    

}
