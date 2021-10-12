package question3;

public abstract class Decorateur<T> implements StructureI<T>{
    protected StructureI<T> structure;

    public Decorateur(StructureI<T> structure){
        this.structure=structure;
    }

    public void operation(T t) throws StructureTypeException{
        this.structure.operation(t);
    }

    public boolean condition(){
        return structure.condition();
    }

  
    public boolean repOk(){
        return structure.repOk();
    }

    public <A> A af(){
        return structure.af();
    }
}
