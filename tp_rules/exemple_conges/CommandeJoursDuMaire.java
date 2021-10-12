package exemple_conges;


import question2.CommandI;

public class CommandeJoursDuMaire implements CommandI<Agent,ResultatConges>{


    public ResultatConges execute(Agent agent,ResultatConges resultat){
        resultat.incrementer(agent.getMairie().getJoursDuMaire());
        return resultat;
    }
    
    public String toString(){
        return "commandeJoursDuMaire";
    }

}