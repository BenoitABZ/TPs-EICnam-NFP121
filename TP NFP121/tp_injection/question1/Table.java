package question1;

import java.util.Collection;
import java.util.Arrays;
public class Table{
    private static final boolean T = true;
    
    private Collection<Integer> collection;
    
    public Table(){
         if(T)System.out.println("Création d'une instance de la classe Table");
    }
    public void setCollection(Collection<Integer> collection){
        if(T)System.out.println("Appel de setCollection(" + collection +")");
        this.collection = collection;
    }
    public void setInt(int i){
        if(T)System.out.println("Appel de setInt(" + i +")");
        collection.add(i);
    }
    public void setTab(Integer[] tab){
        if(T)System.out.println("Appel de setTab(" + Arrays.asList(tab) +")");
        collection.addAll(Arrays.asList(tab));
    }
    public int taille(){
        return collection.size();
    }
    public String toString(){
        return collection.toString();
    }
    static{
        if(T)System.out.println("Chargement de la classe Table");
    }
}
