package exemple_alcool;


import container.ApplicationContext;
import java.util.*;

import question1.SpecificationI;

/**
 * Alcool moins de 18 mais avec l'usage de femtoContainer.
 * 
 */
public class RegleAlcoolMoinsDe18AvecFemtoContainer{

    public  static void main(String[] args) throws Exception{
        ApplicationContext ctx = null;
        ctx = container.Factory.createApplicationContext("./exemple_alcool/README.TXT");
        
        ClientDuBar clientDuBar = ctx.getBean("clientDuBar");
        SpecificationI<ClientDuBar> regle = ctx.getBean("regle");
        
         System.out.println("regle:" + regle);

        boolean consommationDAlcoolEstLegale = regle.isSatisfiedBy(clientDuBar);
        System.out.println("clientDuBar: " + clientDuBar);
        System.out.println("consommationDAlcoolEstLegale: " + consommationDAlcoolEstLegale);

    }

    private static <T> void printSpecified(Set<T> set, SpecificationI<T> spec) {
        for(T t : set) {
            if( spec.isSatisfiedBy(t) ) {
                System.out.println(t);
            }
        }
    }
  

}
