package question3;

import question1.*;
import java.util.*;
import java.io.*;

public class TestMaillons extends junit.framework.TestCase{
    private static final boolean T = true;

    public static class TestCommande extends Maillon<EvenementI> implements CommandeI<EvenementI>{
        private EvenementI evenement; // pour les tests : verification de la notification
        private String nom;

        public TestCommande(String nom){
            this.nom = nom;
        }

        public TestCommande(){
            this.nom = "";
        }

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

        public String toString(){
            return "TestCommande(" + nom + ")";
        }
    }

    protected void setUp(){

    }

    public void testPublication() throws Exception{
        MediateurI mediateur = new Mediateur("mediateur");
        TestCommande testCommande = new TestCommande("commandeMeteo");
        CommandeI<EvenementI> commande = new MaillonDate(new MaillonHumidite(new MaillonPrecipitation(testCommande)));
        mediateur.ajouterTheme("meteo",commande);

        EvenementI evenement = new Evenement();
        evenement.setTheme("meteo");
        evenement.putParametre("date","24-12-2020");
        evenement.putParametre("heure","10:00");
        evenement.putParametre("temperature",8);
        evenement.putParametre("precipitation",64);
        evenement.putParametre("humidite",75);
        evenement.putParametre("vent",18);
        assertEquals(6,evenement.getParametres().size());

        assertEquals(null, testCommande.getEvenement());
        mediateur.publier(evenement);

        EvenementI evenementRecu = testCommande.getEvenement();
        assertNull(evenementRecu);

        evenement.putParametre("humidite",50);
        mediateur.publier(evenement);
        evenementRecu = testCommande.getEvenement();
        assertNotNull(evenementRecu);
        assertNotNull(evenementRecu.getParametres());
        assertEquals(6,evenementRecu.getParametres().size());
        assertEquals("meteo",evenementRecu.getTheme());
        assertEquals("24-12-2020",evenementRecu.getParametre("date"));
        assertEquals(new Integer(50), evenementRecu.getParametre("humidite"));

        mediateur.retirerTheme("meteo");
        commande = new MaillonPrioritaire(1000,new MaillonDate(new MaillonHumidite(new MaillonPrecipitation(testCommande))));
        mediateur.ajouterTheme("meteo",commande);
        evenement.setPriorite(1000);
        assertEquals(null, testCommande.getEvenement());
        mediateur.publier(evenement);
        evenementRecu = testCommande.getEvenement();
        assertNull(evenementRecu);

    }

    public void testMaillons() throws Exception{

        TestCommande testCommande = new TestCommande("commandeMeteo");
        CommandeI<EvenementI> commande = new MaillonDate(new MaillonHumidite(new MaillonPrecipitation(testCommande)));

        EvenementI evenement = new Evenement();
        evenement.setTheme("meteo");
        evenement.putParametre("date","24-12-2020");
        evenement.putParametre("heure","10:00");
        evenement.putParametre("temperature",8);
        evenement.putParametre("precipitation",64);
        evenement.putParametre("humidite",75);
        evenement.putParametre("vent",18);
        assertEquals(6,evenement.getParametres().size());

        assertEquals(null, testCommande.getEvenement());
        commande.executer(evenement);

        EvenementI evenementRecu = testCommande.getEvenement();
        assertNull(evenementRecu);

        evenement.putParametre("humidite",50);
        commande.executer(evenement);
        
        evenementRecu = testCommande.getEvenement();
        assertNotNull(evenementRecu);
        assertNotNull(evenementRecu.getParametres());
        assertEquals(6,evenementRecu.getParametres().size());
        assertEquals("meteo",evenementRecu.getTheme());
        assertEquals("24-12-2020",evenementRecu.getParametre("date"));
        assertEquals(new Integer(50), evenementRecu.getParametre("humidite"));


    }

    public void testToString() throws Exception{
        MediateurI mediateur = new Mediateur("mediateur");
        TestCommande testCommande = new TestCommande("commandeMeteo");
        assertEquals("TestCommande(commandeMeteo)",testCommande.toString());
        CommandeI<EvenementI> commande = new MaillonPrecipitation(testCommande);
        assertEquals("Precipitation(TestCommande(commandeMeteo))",commande.toString());
        commande = new MaillonHumidite(new MaillonPrecipitation(testCommande));
        assertEquals("Humidite(Precipitation(TestCommande(commandeMeteo)))",commande.toString());
        commande = new MaillonDate(new MaillonHumidite(new MaillonPrecipitation(testCommande)));
        assertEquals("Date(Humidite(Precipitation(TestCommande(commandeMeteo))))",commande.toString());
    }

       public void testMaillonPrioritaire() throws Exception{
        TestCommande testCommande = new TestCommande("commandeMeteo");
        CommandeI<EvenementI> commande = new MaillonPrioritaire(100,new MaillonDate(new MaillonHumidite(new MaillonPrecipitation(testCommande))));

        EvenementI evenement = new Evenement();
        evenement.setTheme("meteo");
        evenement.setPriorite(1000);
        evenement.putParametre("date","24-12-2020");
        evenement.putParametre("heure","10:00");
        evenement.putParametre("temperature",8);
        evenement.putParametre("precipitation",64);
        evenement.putParametre("humidite",50);
        evenement.putParametre("vent",18);
        assertEquals(6,evenement.getParametres().size());

        assertEquals(null, testCommande.getEvenement());
        commande.executer(evenement);

        EvenementI evenementRecu = testCommande.getEvenement();
        assertNull(" Le maillon dit Prioritaire doit bloquer la propagation ???",evenementRecu); // le maillon pro

        evenement.setPriorite(50);
        commande.executer(evenement);

        evenementRecu = testCommande.getEvenement();
        assertNotNull(evenementRecu);
        assertNotNull(evenementRecu.getParametres());
        assertEquals("meteo", evenement.getTheme());
        assertEquals(50, evenement.getPriorite());
        assertEquals(6,evenementRecu.getParametres().size());
        assertEquals("meteo",evenementRecu.getTheme());
        assertEquals("24-12-2020",evenementRecu.getParametre("date"));
        assertEquals(new Integer(50), evenementRecu.getParametre("humidite"));
    }
    
    
    public void testMaillonPrioritaireElementaire() throws Exception{
        TestCommande testCommande = new TestCommande("commandeMeteo");
        MaillonPrioritaire maillonPrioritaire = new MaillonPrioritaire();
        maillonPrioritaire.setPriorite(100);
        maillonPrioritaire.setSuccesseur(testCommande);
        CommandeI<EvenementI> commande = maillonPrioritaire;

        EvenementI evenement = new Evenement();
        evenement.setTheme("meteo");
        evenement.setPriorite(1000);

        assertEquals(null, testCommande.getEvenement());
        commande.executer(evenement);

        EvenementI evenementRecu = testCommande.getEvenement();
        assertNull(" Le maillon dit Prioritaire doit bloquer la propagation ???",evenementRecu); // le maillon pro

        evenement.setPriorite(50);
        commande.executer(evenement);

        evenementRecu = testCommande.getEvenement();
        assertNotNull(evenementRecu);
        assertNotNull(evenementRecu.getParametres());
        assertTrue(maillonPrioritaire.toString().startsWith("Prioritaire"));
    }
    
    public void ignore_testSortieConsoleConforme() throws Exception{
        MediateurI mediateur = new Mediateur("mediateur");
        TestCommande testCommande = new TestCommande("commandeMeteo");

        EvenementI evenement = new Evenement();
        evenement.setTheme("meteo");
        evenement.putParametre("date","24-12-2020");
        evenement.putParametre("heure","10:00");
        evenement.putParametre("temperature",8);
        evenement.putParametre("precipitation",64);
        evenement.putParametre("humidite",50);
        evenement.putParametre("vent",18);
        assertEquals(6,evenement.getParametres().size());

        CommandeI<EvenementI> commande = new MaillonDate(new MaillonHumidite(new MaillonPrecipitation(testCommande)));
        mediateur.ajouterTheme("meteo",commande);

        PrintStream old = System.out;
        String sortieConsole = null;
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            mediateur.publier(evenement);
            System.out.flush();
            sortieConsole = baos.toString();
        }finally{
            System.setOut(old);
        }

        //System.out.println("sortieConsole : " + sortieConsole);
        assertTrue(sortieConsole.contains("24-12-2020")); // la date
        assertTrue(sortieConsole.contains("50"));  // humidite
        assertTrue(sortieConsole.contains("64")); // precipitation

    }

    public void testParIntrospection() throws Exception{
        Maillon<EvenementI> maillonPremier = new MaillonDate();
        assertNull(maillonPremier.getSuccesseur());
        Maillon<EvenementI> maillon = Introspection.ajouterUnMaillon(maillonPremier, "question3.MaillonHumidite");
        assertNotNull(maillonPremier.getSuccesseur());
        assertTrue(maillon instanceof MaillonHumidite);
        assertEquals(maillon, maillonPremier.getSuccesseur());

        assertTrue(maillonPremier.getSuccesseur() instanceof MaillonHumidite);
        maillon = Introspection.ajouterUnMaillon(maillon, "question3.MaillonPrecipitation");
        assertTrue(maillonPremier.getSuccesseur().getSuccesseur() instanceof MaillonPrecipitation);
        maillon = Introspection.ajouterUnMaillon(maillon, "question3.TestMaillons$TestCommande");
        //assertTrue(maillonPremier.getSuccesseur().getSuccesseur().getSuccesseur() instanceof Z_TestMaillons.TestCommande);
        assertEquals("Date(Humidite(Precipitation(TestCommande())))",maillonPremier.toString());
    }
}


// avec T == true
// meteo, date: 24-12-2020
// meteo, humidite: 75
// meteo, date: 24-12-2020
// meteo, humidite: 50
// meteo, precipitation: 64
// commandeMeteo, executer: <meteo,5,{date=24-12-2020, precipitation=64, heure=10:00, vent=18, humidite=50, temperature=8}>
// meteo priorite: 1000
// meteo, date: 24-12-2020
// meteo, humidite: 75
// meteo, date: 24-12-2020
// meteo, humidite: 50
// meteo, precipitation: 64
// commandeMeteo, executer: <meteo,5,{date=24-12-2020, precipitation=64, heure=10:00, vent=18, humidite=50, temperature=8}>
