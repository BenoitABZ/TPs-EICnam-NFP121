package question1;
import java.util.*;

public class MAYBE<E> implements SpecificationI<E>{
   private final static boolean[] res = new boolean[]{true,false,true,false,true,false,true};
   private static Random random = new Random();
   
   public boolean isSatisfiedBy(E e){
      return res[random.nextInt(res.length)];
    }
    
    public String toString(){
       return "MAYBE";
    }
}