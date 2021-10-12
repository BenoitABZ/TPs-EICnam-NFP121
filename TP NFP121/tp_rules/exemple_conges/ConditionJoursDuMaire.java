package exemple_conges;

import question1.SpecificationI;
public class ConditionJoursDuMaire implements SpecificationI<Agent>{
    private static final boolean T = true;

    public boolean isSatisfiedBy(Agent agent){
        try{
            boolean resultat = agent.getMairie().getJoursDuMaire()>0;
            if(T)System.out.println("ConditionJoursDuMaire.estSatisfaite : " + resultat);
            return resultat;
        }catch(Exception e){
            return false;
        }
    }

    public String toString(){
        return "conditionJoursDuMaire";
    }
}