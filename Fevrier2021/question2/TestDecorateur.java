package question2;

import question1.*;
import java.util.*;
import java.io.*;


public class TestDecorateur extends junit.framework.TestCase{
    private static final boolean T = true;

    public static class TestCommande implements CommandeI<EvenementI>{
        private EvenementI evenement; // pour les tests : verification de la notification
        private String nom;

        public TestCommande(String nom){
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
        CommandeI<EvenementI> commande = new DecorateurDate(new DecorateurHumidite(new DecorateurPrecipitation(testCommande)));
        mediateur.ajouterTheme("meteo",commande);

        EvenementI evenement = new Evenement();
        evenement.setTheme("meteo");
        evenement.putParametre("date","24-12-2020");
        evenement.putParametre("heure","10:00");
        evenement.putParametre("temperature",8);
        evenement.putParametre("precipitation",64);
        evenement.putParametre("humidite",50);
        evenement.putParametre("vent",18);
        assertEquals(6,evenement.getParametres().size());

        assertEquals(null, testCommande.getEvenement());
        mediateur.publier(evenement);

        EvenementI evenementRecu = testCommande.getEvenement();
        assertNotNull(evenementRecu);
        assertNotNull(evenementRecu.getParametres());
        assertEquals(6,evenementRecu.getParametres().size());
        assertEquals("meteo",evenementRecu.getTheme());
        assertEquals("24-12-2020",evenementRecu.getParametre("date"));
        assertEquals(new Integer(50), evenementRecu.getParametre("humidite"));

    }

    public void testPublicationHumiditeSuperieurA75() throws Exception{
        MediateurI mediateur = new Mediateur("mediateur");
        TestCommande testCommande = new TestCommande("commandeMeteo");
        CommandeI<EvenementI> commande = new DecorateurDate(new DecorateurHumidite(new DecorateurPrecipitation(testCommande)));
        mediateur.ajouterTheme("meteo",commande);

        EvenementI evenement = new Evenement();
        evenement.setTheme("meteo");
        evenement.putParametre("date","24-12-2020");
        evenement.putParametre("heure","10:00");
        evenement.putParametre("temperature",8);
        evenement.putParametre("precipitation",64);
        evenement.putParametre("humidite",100);
        evenement.putParametre("vent",18);
        assertEquals(6,evenement.getParametres().size());

        assertEquals(null, testCommande.getEvenement());
        mediateur.publier(evenement);

        EvenementI evenementRecu = testCommande.getEvenement();
        assertNull(evenementRecu);

    }

    public void testToString() throws Exception{
        MediateurI mediateur = new Mediateur("mediateur");
        TestCommande testCommande = new TestCommande("commandeMeteo");
        assertEquals("TestCommande(commandeMeteo)",testCommande.toString());
        CommandeI<EvenementI> commande = new DecorateurPrecipitation(testCommande);
        assertEquals("Precipitation(TestCommande(commandeMeteo))",commande.toString());
        commande = new DecorateurHumidite(new DecorateurPrecipitation(testCommande));
        assertEquals("Humidite(Precipitation(TestCommande(commandeMeteo)))",commande.toString());
        commande = new DecorateurDate(new DecorateurHumidite(new DecorateurPrecipitation(testCommande)));
        assertEquals("Date(Humidite(Precipitation(TestCommande(commandeMeteo))))",commande.toString());
    }

    public void testDecorateurs() throws Exception{

        TestCommande testCommande = new TestCommande("commandeMeteo");
        CommandeI<EvenementI> commande = new DecorateurDate(new DecorateurHumidite(new DecorateurPrecipitation(testCommande)));

        EvenementI evenement = new Evenement();
        evenement.setTheme("meteo");
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
        assertNotNull(evenementRecu);
        assertNotNull(evenementRecu.getParametres());
        assertEquals(6,evenementRecu.getParametres().size());
        assertEquals("meteo",evenementRecu.getTheme());
        assertEquals("24-12-2020",evenementRecu.getParametre("date"));
        assertEquals(new Integer(50), evenementRecu.getParametre("humidite"));

    }

    public void testSortieConsoleConforme() throws Exception{
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

        CommandeI<EvenementI> commande = new DecorateurDate(new DecorateurHumidite(new DecorateurPrecipitation(testCommande)));
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

        System.out.println("sortieConsole : " + sortieConsole);
        assertTrue(sortieConsole.contains("24-12-2020")); // la date
        assertTrue(sortieConsole.contains("50"));  // humidite
        assertTrue(sortieConsole.contains("64")); // precipitation

    }
    
}


// avec T == true
// meteo, date: 24-12-2020
// meteo, humidite: 50
// meteo, precipitation: 64
// commandeMeteo, executer: <meteo,5,{date=24-12-2020, precipitation=64, heure=10:00, vent=18, humidite=50, temperature=8}>
// meteo, date: 24-12-2020
// meteo, humidite: 100
// meteo, date: 24-12-2020
// meteo, humidite: 50
// meteo, precipitation: 64
// commandeMeteo, executer: <meteo,5,{date=24-12-2020, precipitation=64, heure=10:00, vent=18, humidite=50, temperature=8}>
// sortieConsole : meteo, date: 24-12-2020
// meteo, humidite: 50
// meteo, precipitation: 64
// commandeMeteo, executer: <meteo,5,{date=24-12-2020, precipitation=64, heure=10:00, vent=18, humidite=50, temperature=8}>

