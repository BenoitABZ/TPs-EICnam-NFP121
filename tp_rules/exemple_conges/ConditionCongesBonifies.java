package exemple_conges;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import question1.SpecificationI;
public class ConditionCongesBonifies implements SpecificationI<Agent>{
    private static final boolean T = true;
    private List<String> regions;
    public ConditionCongesBonifies(){
        this.regions = new ArrayList<>();
    }
    public void setRegions(String[] regions){
        this.regions.addAll(Arrays.asList(regions));
    }

    public boolean isSatisfiedBy(Agent agent){
        try{
            //https://www.service-public.fr/particuliers/vosdroits/F1599
            boolean resultat = false; /* TODO */
           if(T)System.out.println("ConditionCongesBonifies.estSatisfaite : " + resultat);
           return resultat;
        }catch(Exception e){
            return false;
        }
    }

    public String toString(){
        return "ConditionCongesBonifies";
    }
}