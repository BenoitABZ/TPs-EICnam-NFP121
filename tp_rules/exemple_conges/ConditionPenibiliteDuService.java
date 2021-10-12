package exemple_conges;

import question1.SpecificationI;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ConditionPenibiliteDuService implements SpecificationI<Agent>{
    private static final boolean T = true;
    private List<String> services;

    public ConditionPenibiliteDuService(){
        this.services = new ArrayList<>();
    }

    public void setServices(String[] services){
        this.services.addAll(Arrays.asList(services));
    }

    public boolean isSatisfiedBy(Agent agent){
        List<String> services = agent.getServices();
        if(services.size()>0){
            String serviceActuel = services.get(services.size()-1);
            if(T)System.out.println("ConditionPenibilite.estSatisfaite : " + (services.contains(serviceActuel)));
            return services.contains(serviceActuel);
        }
        else
            throw new RuntimeException("l'agent " + agent + " est-il sans affectation ???");
    }

    public String toString(){
        return "conditionPenibiliteDuService";
    }
}