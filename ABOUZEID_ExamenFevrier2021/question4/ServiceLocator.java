package question4;

import java.util.*;
import question1.*;

/**
 * 
 * @author Votre nom, prénom    // a completer
 */
public class ServiceLocator implements ServiceLocatorI{

   // Un cache, cf: le patron ServiceLocator original
    private Map<String,MediateurI> cache;

    public int cacheAccess; // pour des statistiques sur l'intérêt d'un cache
                            // cf. la classe de tests TestServiceLocator ligne 78

    /** Invariant de classe.
     * @return vrai si l'invariant est satisfait
     */
    public boolean repOk(){
        return false; // a completer
    }

    /** Fonction d'abstraction. optionnelle, optionnelle
     * @return une représentation dite abstraite
     */
    public <T> T af(){
        return null;
    }

    public ServiceLocator(){
       // a completer
    }

    public void ajouterMediateur(MediateurI mediateur) throws Exception{
       // a completer
    }

    public void setMediateur(MediateurI mediateur) throws Exception{
        ajouterMediateur(mediateur);
    }

    public MediateurI rechercher(String nom) throws Exception{
        MediateurI t = cache.get(nom);
        if(t==null){ // t n'est pas dans le cache
 
            // a completer
            
            
        }else{
            cacheAccess++;
        }
        return t;
    }

    public Iterator<String> iterator(){
        return null; // a completer
    }
}


