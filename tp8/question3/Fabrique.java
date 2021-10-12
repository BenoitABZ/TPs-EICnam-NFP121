package question3;



public class Fabrique<T> implements FabriqueI<StructureI<T>>{ 
   public StructureI<T> creer(){
       return new Structure<>();
    }
    // public StructureI<T> creer(int capacite){
       // return new Structure<>(capacite);
    // }
}
