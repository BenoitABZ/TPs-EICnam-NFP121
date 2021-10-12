package question3;


public class StructureRepOk<T> extends Decorateur<T>{
    public StructureRepOk(StructureI<T> structure){
        super(structure);
        assert super.structure.repOk() : " repOk invalide !";
    }

    
    public void operation(T t) throws StructureTypeException{
        assert structure.repOk() : " repOk invalide !";
        structure.operation(t);
        assert structure.repOk() : " repOk invalide !";
    }

    
    public boolean condition(){
        assert structure.repOk() : " repOk invalide !";
        boolean result = super.condition();
        assert structure.repOk() : " repOk invalide !";
        return result;
    }

    
}
