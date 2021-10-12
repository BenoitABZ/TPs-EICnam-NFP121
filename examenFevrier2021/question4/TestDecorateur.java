package question4;

import question1.*;
import question2.*;
import question3.*;
import container.*;


public class TestDecorateur extends junit.framework.TestCase{
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
        
        public String toString(){
            return "TestCommande(commandeMeteo)";
        }
    }

    protected void setUp(){

    }

    
    public void testDecorateurAvecFemtoContainer() throws Exception{
        ApplicationContext ctx = container.Factory.createApplicationContext("./question4/config.txt");
        MediateurI mediateur = ctx.getBean("mediateur");
        assertNotNull(mediateur);
        
        TestCommande testCommande = ctx.getBean("testCommande");
        assertEquals("TestCommande(commandeMeteo)",testCommande.toString());
        
        CommandeI<EvenementI> commande = ctx.getBean("chaineDeCommandes");
        
        if(T)System.out.println("commande: " + commande);
        
        // assertion à commenter si ajout d'un décorateur à la chaineDeCommandes
        assertEquals("Date(Humidite(Precipitation(TestCommande(commandeMeteo))))",commande.toString());

        // a completer en ajoutant au moins un test sur le nouveau décorateur injecté
    }
    
    
    
    public void testDecorateurSansFemtoContainer() throws Exception{

        TestCommande testCommande = new TestCommande("commandeMeteo");
        CommandeI<EvenementI> commande = new DecorateurDate(new DecorateurHumidite(testCommande));
        if(T)System.out.println("commande: " + commande);
        
        // assertion à commenter si ajout d'un décorateur à la chaineDeCommandes
        assertEquals("Date(Humidite(TestCommande(commandeMeteo)))",commande.toString());
 
        // a completer en ajoutant au moins un test sur le nouveau décorateur créé
    }

    


}



// commande: Date(Humidite(TestCommande(commandeMeteo)))
// verifyProperties......ok
// analyzeProperties
// className: question1.Mediateur
// id: mediateur, creation de : Mediateur
// className: question4.TestDecorateur$TestCommande
// id: testCommande, creation de : TestCommande
// className: question2.DecorateurPrecipitation
// id: decorateurPrecipitation, creation de : DecorateurPrecipitation
// className: question2.DecorateurHumidite
// id: decorateurHumidite, creation de : DecorateurHumidite
// className: question2.DecorateurDate
// id: chaineDeCommandes, creation de : DecorateurDate
// id: bean.id.1, mediateur, appels des mutateurs:
	// mediateur		setNom(mediateur1)
// id: bean.id.2, testCommande, appels des mutateurs:
	// testCommande		setNom(commandeMeteo)
// id: bean.id.3, decorateurPrecipitation, appels des mutateurs:
	// decorateurPrecipitation		setCommande(TestCommande(commandeMeteo))
// id: bean.id.4, decorateurHumidite, appels des mutateurs:
	// decorateurHumidite		setCommande(Precipitation(TestCommande(commandeMeteo)))
// id: bean.id.5, chaineDeCommandes, appels des mutateurs:
	// chaineDeCommandes		setCommande(Humidite(Precipitation(TestCommande(commandeMeteo))))
// commande: Date(Humidite(Precipitation(TestCommande(commandeMeteo))))



