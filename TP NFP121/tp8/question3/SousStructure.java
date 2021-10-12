package question3;

public class SousStructure<T> extends Structure<T>{
 
    public SousStructure(){
    }
    
    @Override
    public void operation(T t) throws StructureTypeException{
        if(condition()) throw new StructureTypeException();
        
    }
    // d'autres operations
    
    // mutateurs
    
    // accesseurs
    @Override
    public boolean condition(){
        return false;
    }
    
    public boolean repOk(){
        return false;

    }
    public <A> A af(){
        return null; 
    }

   
}

