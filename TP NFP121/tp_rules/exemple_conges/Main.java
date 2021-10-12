package exemple_conges;
// https://www.service-public.fr/particuliers/vosdroits/N19978
//
import container.*;
import question1.SpecificationI;
import question2.*;
import question3.*;

import java.time.LocalDate;
import java.util.*;

public class Main{
    static Mairie mairie; // pour les tests
    static Agent agent;
    
    static{
        mairie = new Mairie("Porto-Vecchio","Corse");
        mairie.setJoursDuMaire(5);
        mairie.setServices(new String[]{"Voierie","Environnement","Ecoles"});
        
        agent = new Agent("Pasquale","Portivechju");
        agent.setMairie(mairie);
        agent.setService("Voierie");
        agent.setAnciennete(15);
    }

    public static void TestSansFemtoContainer() throws Exception{
        ConditionAnciennete anciennete = new ConditionAnciennete();
        anciennete.setNombreDAnneesDAncienneteRequis(5);
        ConditionPenibiliteDuService penibilite = new ConditionPenibiliteDuService();
        penibilite.setServices(new String[]{"Egouts","Voierie"});
        ConditionJoursDuMaire joursDuMaire = new ConditionJoursDuMaire();

        ResultatConges conges = new ResultatConges();
        // en règle if/the java
        if(anciennete.isSatisfiedBy(agent))
           conges.incrementer(5);
        if(penibilite.isSatisfiedBy(agent))
           conges.incrementer(10);
        if(joursDuMaire.isSatisfiedBy(agent))
           conges.incrementer(10);
           
        System.out.println("conges de "+ agent + ":" + conges);
           
        conges = new ResultatConges();
        // en règles 
        CommandeCumulConges inc = new CommandeCumulConges();
        inc.setNombre(1);
        MacroCommand<Agent,ResultatConges> inc5 = new MacroCommand<>();
        CommandI<Agent,ResultatConges>[] tab= new CommandI[]{inc,inc,inc,inc,inc};
        inc5.setCommands(new CommandI[]{inc,inc,inc,inc,inc});
        MacroCommand<Agent,ResultatConges> inc10 = new MacroCommand<>();
        inc10.setCommands(new CommandI[]{inc5,inc5});
                
        Rule<Agent,ResultatConges> regleAnciennete = new Rule<>();
        regleAnciennete.setSpecification(anciennete);
        regleAnciennete.setCommand(inc5);
        Rule<Agent,ResultatConges> reglePenibilite = new Rule<>();
        reglePenibilite.setSpecification(penibilite);
        reglePenibilite.setCommand(inc10);
        
        Rule<Agent,ResultatConges> regleJoursDuMaire = new Rule<>();
        regleJoursDuMaire.setSpecification(joursDuMaire);
        regleJoursDuMaire.setCommand(inc10);
        
        //Rules<Agent,ResultatConges> rules = new Rules<>();
        //System.out.println("conges de "+ agent + ":" + conges);
        
        CommandeFractionnementPlus1 command1 = new CommandeFractionnementPlus1();
        command1.setPlus1(1);

        CommandeFractionnementPlus2 command2 = new CommandeFractionnementPlus2();
        command2.setPlus2(2);
        
        ConditionFractionnementPlus1 condition1 = new ConditionFractionnementPlus1();
        condition1.setMai(LocalDate.of(2020, 05, 01));
        condition1.setOctobre(LocalDate.of(2020, 31, 10));
        
        ConditionFractionnementPlus2 condition2 = new ConditionFractionnementPlus2();
        condition2.setMai(LocalDate.of(2020, 05, 01));
        condition2.setOctobre(LocalDate.of(2020, 31, 10));
        
        Rules<Agent,ResultatConges> rules = new Rules<>();
        
        Rule<Agent,ResultatConges> rule1 = new Rule<>();     
        rule1.setCommand(command1);
        rule1.setSpecification(condition1);
        
        Rule<Agent,ResultatConges> rule2 = new Rule<>();     
        rule2.setCommand(command2);
        rule2.setSpecification(condition2);
        
        rules.add(rule1);
        rules.add(rule2);
        
        rules.execute(agent, conges);

    }
    
    public static void TestAvecFemtoContainer() throws Exception{
        ApplicationContext ctx = Factory.createApplicationContext("./exemple_conges/README.TXT");
        ResultatConges conges = new ResultatConges();
        
        //RuleI<Agent,ResultatConges> rule1 = ctx.getBean("regles_conges");
        RuleI<Agent,ResultatConges> rule1 = ctx.getBean("trace_regles_conges");
        conges = rule1.execute(agent,conges);
        //System.out.println("rule1: "+ rule1);
        System.out.println("conges de "+ agent + ":" + conges);
        
        // Quels sont les services municipaux ? entite:Mairie, résultat:Une liste
        //RuleI<Mairie,List<String>> rule2 = ctx.getBean("regles_services");
        RuleI<Mairie,List<String>> rule2 = ctx.getBean("trace_regles_services");
        List<String> services = new ArrayList<>();
        services = rule2.execute(mairie,services);
        System.out.println("services de "+ mairie + ":" + services);
    }
}
