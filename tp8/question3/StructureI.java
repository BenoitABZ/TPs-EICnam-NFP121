package question3;



public interface StructureI<T> {
   
    public void operation(T t) throws StructureTypeException;
    // d'autres operations
    
    // mutateurs
    
    // accesseurs
    public boolean condition();
    
    //cf. Liskov
    public boolean repOk(); // invariant de classe
    public <A> A af();      // A comme Abstrait
    
}
