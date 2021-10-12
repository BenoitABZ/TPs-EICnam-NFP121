package question3;

import java.util.List; // à remplacer par le choix de la classe abstraite

public class StructureAf<T> extends Decorateur<T>{
    
    public StructureAf(StructureI<T> structure){
        super(structure);
        //assert ??? : " af invalide !";
    }

    
    public void operation(T t) throws StructureTypeException{
        List<T> liste = super.af(); // une liste juste pour la syntaxe
        try{
            structure.operation(t); // operation concrete
            liste.add(t);           // operation abstraite
        }catch(Exception e){
            assert e instanceof StructureTypeException;
            // vérifier ici l'exception levée dans la représentation abstraite
            // si cette exception existe...
            throw e;
        }finally{
           assert structure.af().equals(liste) : " af invalide !";
        }
    }

   
    public boolean condition(){
        List<T> liste = super.af(); // une liste juste pour la syntaxe
        boolean b1 = structure.condition();
        boolean b  = super.condition();
        assert structure.af().equals(liste) : " af invalide !";
        assert (b==b1);
        return b;
    }

  
    
   
}