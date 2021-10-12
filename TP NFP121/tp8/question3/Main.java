package question3;



public class Main{
 
    public static void test1() throws Exception{
        StructureI<Integer> structure = new StructureRepOk<>(new Structure<>());
        operations(structure);
        structure = new StructureAf<>(new Structure<>());
        operations(structure);
        structure = new StructureAf<>(new StructureRepOk<>(new Structure<>()));
        operations(structure);
        structure = new StructureAf<>(new StructureRepOk<>(new StructurePrePost<>(new Structure<>())));
        operations(structure);
    }
    
    
    public static void operations(StructureI<Integer> structure) throws Exception{
        int element = 1;
        while(!structure.condition()){
            structure.operation(element);
            element++;
        }
        // while(!structure.condition()){
            // element = structure.operation();
        // }        
    }
}
