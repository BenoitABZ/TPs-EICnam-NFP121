package question1;

import java.util.*;

public class TestMediateur extends junit.framework.TestCase{
    private static final boolean T = true;     // Traces sur la console
    private static final boolean REPOK = true;  // Vérification de l'invariant

    public static class CommandeComparator<E> implements java.util.Comparator<CommandeA<E>>{
        private int mode=0; // =0 ordre par priorite
                            // =1 ordre par le nom de l'évènement

        public CommandeComparator(){this(0);}

        public CommandeComparator(int mode){this.mode = mode;}

        public int compare(CommandeA<E> c1, CommandeA<E> c2){
            if(mode==1)
                return c1.getNom().compareTo(c2.getNom()); // ordre lexicographique des noms des commandes

            assert mode==0;
            return Integer.compare(c2.getPriorite(),c1.getPriorite()); // ordre décroissant selon la priorité
        }
    }

    public static class TestCommande extends CommandeA<EvenementI>{
        private static int nombreAppelsExecuter = 0; // pour les tests : nombre d'appels de la méthode executer
        private static List<TestCommande> lesCommandesExecutees = new ArrayList<>();
        private EvenementI evenement; // pour les tests : verification de la notification

        public TestCommande(String nom){
            super(nom);
        }

        public boolean executer(EvenementI evenement){
            this.evenement = evenement;
            nombreAppelsExecuter++;
            lesCommandesExecutees.add(this);
            if(T)System.out.println(getNom() + ", executer: " + evenement);
            return true;
        }

        public EvenementI getEvenement(){  // test de la notification
            EvenementI evt = this.evenement;
            this.evenement = null;
            return evt;
        }

        public static int getNombreAppelsExecuter(){
            int nombre = TestCommande.nombreAppelsExecuter;
            TestCommande.nombreAppelsExecuter = 0;
            return nombre;
        }

        public static List<TestCommande> getLesCommandesExecutees(){
            List<TestCommande> lesCommandes = new ArrayList<>(lesCommandesExecutees);
            TestCommande.lesCommandesExecutees.clear();
            return lesCommandes;
        }

        public String toString(){
            return getNom();
        }
    }

    protected void setUp(){

    }

    public void testUnAjoutEtUnePublication() throws Exception{
        MediateurI mediateur = new Mediateur("mediateur");
        if(REPOK) assert mediateur.repOk();

        CommandeI<EvenementI> commande = new TestCommande("commandeMeteo");
        if(REPOK) assert mediateur.repOk();
        mediateur.ajouterTheme("meteo",commande);
        if(REPOK) assert mediateur.repOk();

        EvenementI evenement = new Evenement();
        evenement.setTheme("meteo");
        evenement.putParametre("date","24-12-2020");
        evenement.putParametre("heure","10:00");
        evenement.putParametre("temperature",8);
        evenement.putParametre("precipitation",64);
        evenement.putParametre("humidite",75);
        evenement.putParametre("vent",18);
        assertEquals(6,evenement.getParametres().size());

        assertTrue(commande instanceof TestCommande);
        TestCommande testCommande = (TestCommande) commande;
        assertEquals(null, testCommande.getEvenement());
        mediateur.publier(evenement);
        EvenementI evenementRecu = testCommande.getEvenement();
        assertNotNull(evenementRecu);
        assertNotNull(evenementRecu.getParametres());
        assertEquals(6,evenementRecu.getParametres().size());
        assertEquals("meteo",evenementRecu.getTheme());
        assertEquals("24-12-2020",evenementRecu.getParametre("date"));
        assertEquals(new Integer(75), evenementRecu.getParametre("humidite"));

        if(REPOK) assert mediateur.repOk();
    }

    public void testDeuxAjoutsEtDeuxPublications() throws Exception{
        MediateurI mediateur = new Mediateur("mediateur");
        CommandeI<EvenementI> commande1 = new TestCommande("commandeMeteo");
        mediateur.ajouterTheme("meteo",commande1);
        CommandeI<EvenementI> commande2 = new TestCommande("commandeNFP121");
        mediateur.ajouterTheme("nfp121",commande2);

        EvenementI evenement1 = new Evenement();
        evenement1.setTheme("meteo");
        evenement1.putParametre("date","24-12-2020");
        evenement1.putParametre("heure","10:00");
        evenement1.putParametre("temperature",8);
        evenement1.putParametre("precipitation",64);
        evenement1.putParametre("humidite",75);
        evenement1.putParametre("vent",18);
        assertEquals(6,evenement1.getParametres().size());

        EvenementI evenement2 = new Evenement();
        evenement2.setTheme("nfp121");
        evenement2.putParametre("date","02-02-2021");
        evenement2.putParametre("heure","18:00");
        evenement2.putParametre("duree",3);
        evenement2.putParametre("condition","distanciel");
        assertEquals(4,evenement2.getParametres().size());

        assertTrue(commande1 instanceof TestCommande);
        TestCommande testCommande = (TestCommande) commande1;
        assertEquals(null, testCommande.getEvenement());
        mediateur.publier(evenement1);
        EvenementI evenementRecu = testCommande.getEvenement();
        assertNotNull(evenementRecu);
        assertNotNull(evenementRecu.getParametres());
        assertEquals(6,evenementRecu.getParametres().size());
        assertEquals("meteo",evenementRecu.getTheme());
        assertEquals("24-12-2020",evenementRecu.getParametre("date"));
        assertEquals(new Integer(75), evenementRecu.getParametre("humidite"));

        assertTrue(commande2 instanceof TestCommande);
        testCommande = (TestCommande) commande2;
        assertEquals(null, testCommande.getEvenement());
        mediateur.publier(evenement2);
        evenementRecu = testCommande.getEvenement();
        assertNotNull(evenementRecu);
        assertNotNull(evenementRecu.getParametres());
        assertEquals(4,evenementRecu.getParametres().size());
        assertEquals("nfp121",evenementRecu.getTheme());
        assertEquals("02-02-2021",evenementRecu.getParametre("date"));
        assertEquals(new Integer(3), evenementRecu.getParametre("duree"));

    }

    public void testAjoutsEtPublications() throws Exception{
        MediateurI mediateur = new Mediateur("mediateur");
        CommandeI<EvenementI> commande1 = new TestCommande("commande1");
        CommandeI<EvenementI> commande2 = new TestCommande("commande2");

        Commandes<EvenementI> commandes = new Commandes("commandesMeteo");
        commandes.addCommande(commande1);
        commandes.addCommande(commande2);
        mediateur.ajouterTheme("meteo",commandes);

        EvenementI evenement = new Evenement();
        evenement.setTheme("meteo");
        evenement.putParametre("date","24-12-2020");
        evenement.putParametre("heure","10:00");
        evenement.putParametre("temperature",8);
        evenement.putParametre("precipitation",64);
        evenement.putParametre("humidite",75);
        evenement.putParametre("vent",18);
        assertEquals(6,evenement.getParametres().size());

        assertTrue(commande2 instanceof TestCommande);
        TestCommande testCommande = (TestCommande) commande2;
        assertEquals(null, testCommande.getEvenement());
        mediateur.publier(evenement);
        EvenementI evenementRecu = testCommande.getEvenement();
        assertNotNull(evenementRecu);
        assertNotNull(evenementRecu.getParametres());
        assertEquals(6,evenementRecu.getParametres().size());
        assertEquals("meteo",evenementRecu.getTheme());
        assertEquals("24-12-2020",evenementRecu.getParametre("date"));
        assertEquals(new Integer(75), evenementRecu.getParametre("humidite"));

    }

    public void testAjoutsCommandesEtPublicationOrdonnee() throws Exception{
        MediateurI mediateur = new Mediateur("mediateur");
        CommandeA<EvenementI> commande1 = new TestCommande("commandeDate");
        CommandeA<EvenementI> commande2 = new TestCommande("commandeHeure");
        CommandeA<EvenementI> commande3 = new TestCommande("commandeVent");
        CommandeA<EvenementI> commande4 = new TestCommande("commandeHumidite");

        Commandes<EvenementI> commandes = new Commandes("commandes");
        assertEquals("commandes",commandes.getNom());
        commandes.addCommandes(new CommandeI[]{commande1,commande3,commande1,commande2,commande2,commande4});
        mediateur.ajouterTheme("meteo",commandes);
        EvenementI evenement = new Evenement();
        evenement.setTheme("meteo");
        evenement.putParametre("date","28-12-2020");
        evenement.putParametre("heure","4:00");
        evenement.putParametre("vent","100");
        TestCommande.getNombreAppelsExecuter();
        assertEquals(0,TestCommande.getNombreAppelsExecuter());
        mediateur.publier(evenement);
        assertEquals(6,TestCommande.getNombreAppelsExecuter());

        /** Les commandes ont maintenant une priorité suivie de l'installation du "comparator" */
        commande1.setPriorite(1);commande2.setPriorite(2);commande3.setPriorite(3);commande4.setPriorite(4);
        commandes.setComparator(new CommandeComparator());
        TestCommande.getLesCommandesExecutees();
        assertTrue(TestCommande.getLesCommandesExecutees().isEmpty());
        /** publication ordonnee */
        mediateur.publier(evenement);
        //
        // le résultat obtenu est-il bien ordonné avec plusieurs assertions ou l'appel ci-après
        List<TestCommande> lesCommandes = TestCommande.getLesCommandesExecutees();
        if(T)System.out.println(lesCommandes);
        assertEquals("commandeHumidite",lesCommandes.get(0).getNom());
        assertEquals("commandeVent",lesCommandes.get(1).getNom());
        // etc...

        TestCommande precedent = lesCommandes.get(0);
        for (TestCommande element : lesCommandes) {
            assertTrue(Integer.compare(precedent.getPriorite(),element.getPriorite()) >= 0);
            precedent = element;
        }

    }
    
    
        public void testAjoutsCommandesEtPublicationOrdonneeSelonLesNoms() throws Exception{
        MediateurI mediateur = new Mediateur("mediateur");
        CommandeA<EvenementI> commande1 = new TestCommande("commandeDate");
        CommandeA<EvenementI> commande2 = new TestCommande("commandeHeure");
        CommandeA<EvenementI> commande3 = new TestCommande("commandeVent");
        CommandeA<EvenementI> commande4 = new TestCommande("commandeHumidite");

        Commandes<EvenementI> commandes = new Commandes("commandes");
        assertEquals("commandes",commandes.getNom());
        commandes.addCommandes(new CommandeI[]{commande1,commande3,commande1,commande2,commande2,commande4});
        mediateur.ajouterTheme("meteo",commandes);
        EvenementI evenement = new Evenement();
        evenement.setTheme("meteo");
        evenement.putParametre("date","28-12-2020");
        evenement.putParametre("heure","4:00");
        evenement.putParametre("vent","100");
        TestCommande.getNombreAppelsExecuter();
        assertEquals(0,TestCommande.getNombreAppelsExecuter());
        mediateur.publier(evenement);
        assertEquals(6,TestCommande.getNombreAppelsExecuter());

        commandes.setComparator(new CommandeComparator(1)); // (1) relation d'ordre sur les noms des commandes
        TestCommande.getLesCommandesExecutees();
        assertTrue(TestCommande.getLesCommandesExecutees().isEmpty());
        /** publication ordonnee */
        mediateur.publier(evenement);
        //
        // le résultat obtenu est-il bien ordonné avec plusieurs assertions ou l'appel ci-après
        List<TestCommande> lesCommandes = TestCommande.getLesCommandesExecutees();
        if(T)System.out.println(lesCommandes);
        // [commandeDate, commandeDate, commandeHeure, commandeHeure, commandeHumidite, commandeVent]
        assertEquals("commandeDate",lesCommandes.get(0).getNom());
        assertEquals("commandeDate",lesCommandes.get(1).getNom());
        assertEquals("commandeHeure",lesCommandes.get(2).getNom());
        // etc...

        TestCommande precedent = lesCommandes.get(0);
        for (TestCommande element : lesCommandes) {
            assertTrue(precedent.getNom().compareTo(element.getNom()) <= 0);
            precedent = element;
        }

    }

    public void testRelationOrdreEntreEvenement(){
        EvenementI e1 = new Evenement();
        e1.setPriorite(1000);
        EvenementI e2 = new Evenement();
        e2.setPriorite(10000);
        assertEquals(-1,e1.compareTo(e2));
        assertEquals(1,e2.compareTo(e1));
        e2.setPriorite(1000);
        assertEquals(0,e1.compareTo(e2));
        assertEquals(0,e2.compareTo(e1));
        e2.setPriorite(100);
        assertEquals(1,e1.compareTo(e2));
        assertEquals(-1,e2.compareTo(e1));
    }

    public void testIterateurSurLesThemes() throws ThemeDejaPresentException{
        MediateurI mediateur = new Mediateur("mediateur");
        CommandeI<EvenementI> commande1 = new TestCommande("commande1");
        CommandeI<EvenementI> commande2 = new TestCommande("commande2");

        mediateur.ajouterTheme("meteo",commande1);
        mediateur.ajouterTheme("nfp121",commande2);
        List<String> themes = new ArrayList<>();
        for(String theme: mediateur){
            themes.add(theme);
        }
        assertEquals(2,themes.size());
        assertTrue(themes.contains("meteo"));
        assertTrue(themes.contains("nfp121"));
    }

    public void testDesExceptions(){
        MediateurI mediateur = new Mediateur("mediateur");
        TestCommande commande = new TestCommande("commandeMeteo");
        try{
            mediateur.ajouterTheme("meteo",commande);
        }catch(Exception e){
            fail("aucune exception n'est pas attendue ici ..."); 
        }

        try{
            mediateur.ajouterTheme("meteo",commande);
            fail("une exception est attendue ici ...");
        }catch(Exception e){
            assertTrue(e instanceof ThemeDejaPresentException);
        }

        try{
            mediateur.retirerTheme("meteo");
        }catch(Exception e){
            fail("aucune exception n'est pas attendue ici ...");
        }

        try{
            mediateur.retirerTheme("meteo");
            fail("une exception est attendue ici ...");
        }catch(Exception e){
            assertTrue(e instanceof ThemeAbsentException);
        }

        try{
            EvenementI evenement = new Evenement();
            evenement.setTheme("meteo");
            mediateur.publier(evenement);
            fail("une exception est attendue ici ...");
        }catch(Exception e){
            assertTrue(e instanceof ThemeAbsentException);
        }

        try{
            mediateur.ajouterTheme("meteo",null);
            fail("une exception est attendue ici ...");
        }catch(Exception e){
            assertTrue(e instanceof NullPointerException);
        }

        try{
            mediateur.ajouterTheme(null,commande);
            fail("une exception est attendue ici ...");
        }catch(Exception e){
            assertTrue(e instanceof NullPointerException);
        }
        try{
            mediateur.ajouterTheme("meteo",commande);
            EvenementI evenement = new Evenement();
            evenement.setTheme("meteo");
            mediateur.publier(null);
            fail("une exception est attendue ici ...");
        }catch(Exception e){
            assertTrue(e instanceof NullPointerException);
        }

        try{
            CommandeI<EvenementI> commande1 = new TestCommande("commandeDate");
            Commandes<EvenementI> commandes = new Commandes("commandes");
            commandes.addCommandes(new CommandeI[]{commande1,commande1,null});
            fail("une exception est attendue ici ...");
        }catch(Exception e){
            assertTrue(e instanceof NullPointerException);
        }
    }

}


// avec T==true, les traces obtenues
// commandeMeteo, executer: <meteo,5,{date=24-12-2020, precipitation=64, heure=10:00, vent=18, humidite=75, temperature=8}>
// commandeDate, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeVent, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeDate, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeHeure, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeHeure, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeHumidite, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeDate, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeDate, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeHeure, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeHeure, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeHumidite, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeVent, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// [commandeDate, commandeDate, commandeHeure, commandeHeure, commandeHumidite, commandeVent]
// commandeMeteo, executer: <meteo,5,{date=24-12-2020, precipitation=64, heure=10:00, vent=18, humidite=75, temperature=8}>
// commandeNFP121, executer: <nfp121,5,{date=02-02-2021, heure=18:00, condition=distanciel, duree=3}>
// commandeDate, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeVent, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeDate, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeHeure, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeHeure, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeHumidite, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeHumidite, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeVent, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeHeure, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeHeure, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeDate, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// commandeDate, executer: <meteo,5,{date=28-12-2020, heure=4:00, vent=100}>
// [commandeHumidite, commandeVent, commandeHeure, commandeHeure, commandeDate, commandeDate]
// commande1, executer: <meteo,5,{date=24-12-2020, precipitation=64, heure=10:00, vent=18, humidite=75, temperature=8}>
// commande2, executer: <meteo,5,{date=24-12-2020, precipitation=64, heure=10:00, vent=18, humidite=75, temperature=8}>

