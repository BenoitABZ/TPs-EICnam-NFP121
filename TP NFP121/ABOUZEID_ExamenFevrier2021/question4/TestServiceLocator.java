
package question4;


import question1.*;
import question2.*;
import question3.*;

import container.*;

import java.util.*;

public class TestServiceLocator extends junit.framework.TestCase{
    private static final boolean T = true;
    
    public static class TestCommande extends Maillon<EvenementI> implements CommandeI<EvenementI>{
        private EvenementI evenement; // pour les tests : verification de la notification
        private String nom;

        public TestCommande(String nom){
            this.nom = nom;
        }

        public TestCommande(){}

        public void setNom(String nom){
            this.nom = nom;
        }

        public boolean executer(EvenementI evenement){
            this.evenement = evenement;
            if(T)System.out.println(nom + ", executer: " + evenement);
            return true;
        }

        public EvenementI getEvenement(){  // test de la notification
            EvenementI evt = this.evenement;
            this.evenement = null;
            return evt;
        }
    }

    protected void setUp(){

    }

    public void testServiceLocatorSansFemtoContainer() throws Exception{
        ServiceLocator serviceLocator = new ServiceLocator();

        MediateurI mediateur1 = new Mediateur("mediateur1");
        TestCommande testCommande1 = new TestCommande("commandeMeteo1");
        CommandeI<EvenementI> commande1 = new MaillonDate(new MaillonHumidite(new MaillonPrecipitation(testCommande1)));
        mediateur1.ajouterTheme("meteo",commande1);
        serviceLocator.ajouterMediateur(mediateur1);

        MediateurI mediateur2 = new Mediateur("mediateur2");
        TestCommande testCommande2 = new TestCommande("commandeMeteo2");
        CommandeI<EvenementI> commande2 = new MaillonDate(testCommande2);
        mediateur2.ajouterTheme("meteo",commande2);
        serviceLocator.ajouterMediateur(mediateur2);

        EvenementI evenement = new Evenement();
        evenement.setTheme("meteo");
        evenement.putParametre("date","24-12-2020");
        evenement.putParametre("heure","10:00");
        evenement.putParametre("temperature",8);
        evenement.putParametre("precipitation",64);
        evenement.putParametre("humidite",50);
        evenement.putParametre("vent",18);
        assertEquals(6,evenement.getParametres().size());

        serviceLocator.rechercher("mediateur1").publier(evenement);

        EvenementI evenementRecu = testCommande1.getEvenement();
        assertNotNull(evenementRecu);

        // mise en oeuvre du cache
        serviceLocator.rechercher("mediateur1");
        assertEquals(1,serviceLocator.cacheAccess);

    }

    public void testExceptionNomDuMediateurInconnu(){
        ServiceLocator serviceLocator = new ServiceLocator();
        try{
            serviceLocator.rechercher("mediateur_inconnu");
            fail("Une exception est attendue ici, le nom du mediateur est inconnu du ServiceLocator");
        }catch(Exception e){
            assertNotNull(e.getMessage());
        }

    }
    
    public void testIterateur() throws Exception{
        ServiceLocator serviceLocator = new ServiceLocator();
        MediateurI mediateur1 = new Mediateur("mediateur1");
        serviceLocator.ajouterMediateur(mediateur1);
        MediateurI mediateur2 = new Mediateur("mediateur2");
        serviceLocator.ajouterMediateur(mediateur2);
        List<String> mediateurs = new ArrayList<>();
        for(String mediateur : serviceLocator){
            mediateurs.add(mediateur);
        }
        assertEquals(2,mediateurs.size());
        assertTrue(mediateurs.contains("mediateur1"));
        assertTrue(mediateurs.contains("mediateur2"));
        assertEquals(2,mediateurs.size());

    }
    public void ignore_testServiceLocatorAvecFemtoContainer() throws Exception{
        ApplicationContext ctx = container.Factory.createApplicationContext("./question4/README.TXT");

        ServiceLocatorI serviceLocator = ctx.getBean("servicelocator");
        assertNotNull(serviceLocator);

        MediateurI mediateur1 = serviceLocator.rechercher("mediateur1");
        assertNotNull(mediateur1);

        CommandeI commande = ctx.getBean("chaineDesResponsables");
        assertNotNull(commande);
        mediateur1.ajouterTheme("meteo",commande);

        EvenementI evenement = new Evenement();
        evenement.setTheme("meteo");
        evenement.putParametre("date","24-12-2020");
        evenement.putParametre("heure","10:00");
        evenement.putParametre("temperature",8);
        evenement.putParametre("precipitation",64);
        evenement.putParametre("humidite",50);
        evenement.putParametre("vent",18);
        assertEquals(6,evenement.getParametres().size());
        mediateur1.publier(evenement);

        MediateurI mediateur2 = serviceLocator.rechercher("mediateur2");
        assertNotNull(mediateur2);

        commande = ctx.getBean("decorateurs");
        assertNotNull(commande);
        mediateur2.ajouterTheme("meteo",commande);

        evenement = new Evenement();
        evenement.setTheme("meteo");
        evenement.putParametre("date","28-12-2020");
        evenement.putParametre("heure","18:00");
        evenement.putParametre("temperature",4);
        evenement.putParametre("precipitation",85);
        evenement.putParametre("humidite",95);
        evenement.putParametre("vent",11);
        assertEquals(6,evenement.getParametres().size());
        mediateur2.publier(evenement);

    }

}