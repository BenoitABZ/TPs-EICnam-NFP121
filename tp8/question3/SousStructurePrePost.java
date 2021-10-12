package question3;

public class SousStructurePrePost<T> extends StructurePrePost<T>{
    public SousStructurePrePost(StructureI<T> structure){
        super(structure);
    }

    private SousStructurePrePost<T> clone(StructureI<T> s) {
        return null;
    }

    /* Vecteur d'état, nécessaire aux pré-post assertions, noms à voir */
    private SousStructurePrePost<T> pre_sousStructure;
    private Exception cause;
    /* fin des déclarations du vecteur d'état */

    private void pre(){ /* initialisation du vecteur */
        this.pre_sousStructure = clone(structure);
        cause = null;
    }

    protected boolean pre_operation(){
        pre();
        return true;
    }

    protected boolean post_operation(T t){
        try{
            return true; // TODO
        }catch(Exception e){
            assert false: "Une exception ne devrait pas être levée ici " + e.getMessage();
            return false;
        }

    }

    public void operation(T t) throws StructureTypeException{
        // Eiffel, cofoja, etc ...
        assert super.pre_operation() || this.pre_operation();
        // pre_super ==> pre_sub   // page 176 Liskov 
        assert !super.pre_operation() || this.pre_operation() : "pre assertion operation invalide !";
        super.operation(t);
        // (pre_super && post_sub) ==> post_super Liskov
        assert (!(super.pre_operation() && this.post_operation(t))) || super.post_operation(t) : "post assertion operation invalide !";
        // Eiffel
        assert this.post_operation(t) && super.post_operation(t) : "post assertion operation invalide !";
    }

    protected boolean pre_condition(){
        pre();
        return true;
    }

    protected boolean post_condition(boolean result){
        return pre_sousStructure.equals(structure) && (cause ==null) && 
        ((result==true /**&& pre_structure.taille()==0**/) || (result==false /** && pre_structure.taille()>=1**/));

    }

    public boolean condition(){
        // Eiffel, cofoja, etc ...
        assert super.pre_condition() || this.pre_condition();
        // pre_super ==> pre_sub   // page 176 Liskov 
        assert !super.pre_condition() || this.pre_condition() : "pre assertion operation invalide !";

        boolean result = false;
        try{
            result = super.condition();
        }catch(Exception e){
            cause = e;
        }finally{
            assert post_condition(result) : "post-assertion condition en échec" ;
        }
        return result;
    }


}
