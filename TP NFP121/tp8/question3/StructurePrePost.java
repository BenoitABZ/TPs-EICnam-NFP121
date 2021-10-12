package question3;

public class StructurePrePost<T> extends Decorateur<T>{

    public StructurePrePost(StructureI<T> structure){
        super(structure);
    }

    private StructureI<T> clone(StructureI<T> s) {
      return null;
    }

    /* Vecteur d'état, nécessaire aux pré-post assertions, noms à voir */
    private StructureI<T> pre_structure;
    private Exception cause;
    /* fin des déclarations du vecteur d'état */
    
    private void pre(){ /* initialisation du vecteur */
        this.pre_structure = clone(structure);
        cause = null;
    }

    protected boolean pre_operation(){
        pre();
        return true;
    }

    protected boolean post_operation(T t){
        try{
            return (pre_structure.condition() && pre_structure.equals(structure) && cause instanceof StructureTypeException) ||
            (!pre_structure.condition() && /** ... && */ cause==null);
        }catch(Exception e){
            assert false: "Une exception ne devrait pas être levée ici " + e.getMessage();
            return false;
        }

    }

    public void operation(T t) throws StructureTypeException{

        assert pre_operation() : "pre-assertion operation en échec" ;
        try{
            super.operation(t);
        }catch(Exception e){
            cause = e;
            throw e;
        }finally{
            assert post_operation(t): "post-assertion empiler en échec" ;;
        }

    }

    protected boolean pre_condition(){
        pre();
        return true;
    }

    protected boolean post_condition(boolean result){
        return pre_structure.equals(structure) && (cause ==null) && 
        ((result==true /**&& pre_structure.taille()==0**/) || (result==false /** && pre_structure.taille()>=1**/));

    }

    public boolean condition(){
        assert pre_condition() : "pre-assertion condition en échec" ;
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
