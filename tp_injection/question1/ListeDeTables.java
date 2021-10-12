package question1;

import java.util.*;
/** Un usage 'détourné' de l'appel du mutateur.
 *  L'appel du mutateur ajoute le paramètre transmis à une liste interne.
 */

public class ListeDeTables{
    private static final boolean T=true;
    private List<Table> liste;
    
    public ListeDeTables(){
        this.liste = new ArrayList<Table>();
    }
    public void setTable(Table t){
        liste.add(t);
        if(T)System.out.println("add(" + t + ")");
    }
    public void setTables(Table[] tables){
        for(Table t:tables)
          //liste.add(t);
          setTable(t);
          
        String s = null;
        if(T) s ="[";
        if(T)for(Table table : tables)s=s+table;
        if(T)s = s +"]";
        if(T)System.out.println("setTables(" + s + ")");
    }
    public int taille(){
        return liste.size();
    }
    public String toString(){
        return this.liste.toString();
    }
    static{
        if(T)System.out.println("Chargement de la classe ListeDeTables");
    }
}