package question3;



public class Structure<T> implements StructureI<T>{
 
    public Structure(){
    }
    
    public void operation(T t) throws StructureTypeException{
        if(condition()) throw new StructureTypeException();
        
    }
    // d'autres operations
    
    // mutateurs
    
    // accesseurs
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

