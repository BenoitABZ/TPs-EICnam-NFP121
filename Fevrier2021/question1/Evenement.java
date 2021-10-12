package question1;
import java.util.*;

/**
 * @author Votre nom, prénom    // a completer
 */

public class Evenement implements EvenementI{
    private static final boolean T = true;
    private static final boolean REPOK = true;

    private Map<String,Object> parametres;
    private String theme;
    private int priorite;

    /** Invariant de classe.
     * @return vrai si l'invariant est satisfait
     */
    public boolean repOk(){
        assert theme!=null && priorite>=0 && parametres!=null;
        for(Map.Entry<String,Object> entry : parametres.entrySet()){
            assert entry.getKey() != null && entry.getValue()!=null;
        }
        //if(T) System.out.println("repOk validé");
        return true;
    }

    public Map<String,Object> af(){
        return null;
    }

    public Evenement(){
        this.parametres = new HashMap<String,Object>();
        this.theme = EvenementI.THEME_PAR_DEFAUT;;
        this.priorite = EvenementI.PRIORITE_PAR_DEFAUT; // par défaut
        if(REPOK) assert repOk(): "invariant de classe en échec";
    }
    
    public int compareTo(EvenementI evenement){
        if(T)System.out.println("int compareTo(EvenementI evenement) (Classe Evenement) est à compléter");
        
        if(this.getPriorite() < evenement.getPriorite()) return -1;
        
        if(this.getPriorite() == evenement.getPriorite()) return 0;
        
        else {
        	
        	return 1;
        }
    }

    public void setTheme(String theme){
        if(REPOK) assert repOk(): "invariant de classe en échec";
        this.theme = theme;
        if(REPOK) assert repOk(): "invariant de classe en échec";
    }

    public String getTheme(){return theme;}

    public void putParametre(String clef, Object objet){
        if(REPOK) assert repOk(): "invariant de classe en échec";
        this.parametres.put(clef, objet);
        if(REPOK) assert repOk(): "invariant de classe en échec";
    }

    public Map<String,Object> getParametres(){
        try{
            if(REPOK) assert repOk(): "invariant de classe en échec";
            return this.parametres;
        }finally{
            if(REPOK) assert repOk(): "invariant de classe en échec";
        }

    }

    public <T> T getParametre(String clef){
        try{
            if(REPOK) assert repOk(): "invariant de classe en échec";
            return (T)this.parametres.get(clef);
        }finally{
            if(REPOK) assert repOk(): "invariant de classe en échec";
        }

    }

    public String toString(){
        return "<"+theme + "," + priorite + "," + parametres + ">";
    }

    public void setPriorite(int priorite){
        if(REPOK) assert repOk(): "invariant de classe en échec";
        this.priorite = priorite;
        if(REPOK) assert repOk(): "invariant de classe en échec";
    }

    public int getPriorite(){
        try{
            if(REPOK) assert repOk(): "invariant de classe en échec";
            return this.priorite;
        }finally{
            if(REPOK) assert repOk(): "invariant de classe en échec";
        }    }


}
