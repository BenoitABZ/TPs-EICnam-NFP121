package exemple_conges;

import question1.SpecificationI;

public class ConditionAnciennete implements SpecificationI<Agent>{
   private static final boolean T = true;
   private int nombreDAnneesDAncienneteRequis;

   public void setNombreDAnneesDAncienneteRequis(int nombre){
       this.nombreDAnneesDAncienneteRequis = nombre;
   }
    
   public boolean isSatisfiedBy(Agent agent){
      if(T)System.out.println("ConditionAnciennete.estSatisfaite : " + (agent.getAnciennete()>=nombreDAnneesDAncienneteRequis));
      return agent.getAnciennete()>=nombreDAnneesDAncienneteRequis;
   }
   
    public String toString(){
        return "conditionAnciennete";
    }
}
