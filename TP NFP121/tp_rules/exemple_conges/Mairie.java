package exemple_conges;
import java.util.*;

public class Mairie{
    private String nom;
    private String lieu;
    private int joursDuMaire=0;
    private List<String> services;

    public Mairie(String nom, String lieu){
        this.nom  = nom;
        this.lieu = lieu;
        this.services = new ArrayList<>();
    }

    public void setJoursDuMaire(int joursDuMaire){this.joursDuMaire=joursDuMaire;}

    public int getJoursDuMaire(){return joursDuMaire;}    

    public void setNom(String nom){ this.nom = nom;}

    public String getNom(){return this.nom;}

    public void setServices(String[] services){
        this.services.addAll(Arrays.asList(services));
    }

    public List<String> getServices(){
        return this.services;
    }
    //public void setRegion(String lieu){ this.lieu = lieu;}
    public String getLieu(){return this.lieu;}

    public String toString(){ return "<" + nom + "," + lieu + ">";}

}
