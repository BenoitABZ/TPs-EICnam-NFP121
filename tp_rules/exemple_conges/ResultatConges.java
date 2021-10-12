package exemple_conges;
public class ResultatConges{
   private int nombreDeJours;
  
    public int getNombreDeJours(){
        return this.nombreDeJours;
    }
    public void setNombreDeJours(int nombreDeJours){
        this.nombreDeJours = nombreDeJours;
    }
    public void incrementer(int n){
        this.nombreDeJours = this.nombreDeJours +n;
    }
    public String toString(){
        return "Resultat<"+nombreDeJours+">";
    }
}