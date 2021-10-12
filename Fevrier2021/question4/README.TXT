#
verbose=true

# mediateur_1 = new Mediateur("mediateur1");
bean.id.1=mediateur_1
mediateur_1.class=question1.Mediateur
mediateur_1.property.1=nom
mediateur_1.property.1.param.1=mediateur1

# mediateur_2 = new Mediateur("mediateur2");
bean.id.2=mediateur_2
mediateur_2.class=question1.Mediateur
mediateur_2.property.1=nom
mediateur_2.property.1.param.1=mediateur2

#testCommande = new TestCommande("commandeMeteo1");
bean.id.3=testCommande
testCommande.class=question4.TestServiceLocator$TestCommande
testCommande.property.1=nom
testCommande.property.1.param.1=commandeMeteo1

#  CommandeI<EvenementI> commande = new MaillonDate(new MaillonHumidite(new MaillonPrecipitation(testCommande)));
bean.id.4=maillonPrecipitation
maillonPrecipitation.class=question3.MaillonPrecipitation
maillonPrecipitation.property.1=successeur
maillonPrecipitation.property.1.param.1=testCommande

bean.id.5=maillonHumidite
maillonHumidite.class=question3.MaillonHumidite
maillonHumidite.property.1=successeur
maillonHumidite.property.1.param.1=maillonPrecipitation

bean.id.6=chaineDesResponsables
chaineDesResponsables.class=question3.MaillonDate
chaineDesResponsables.property.1=successeur
chaineDesResponsables.property.1.param.1=maillonHumidite


bean.id.7=testCommande2
testCommande2.class=question4.TestServiceLocator$TestCommande
testCommande2.property.1=nom
testCommande2.property.1.param.1=commandeMeteo2

#  
bean.id.8=decorateurPrecipitation
decorateurPrecipitation.class=question2.DecorateurPrecipitation
decorateurPrecipitation.property.1=commande
decorateurPrecipitation.property.1.param.1=testCommande2

bean.id.9=decorateurHumidite
decorateurHumidite.class=question2.DecorateurHumidite
decorateurHumidite.property.1=commande
decorateurHumidite.property.1.param.1=decorateurPrecipitation

bean.id.10=decorateurs
decorateurs.class=question2.DecorateurDate
decorateurs.property.1=commande
decorateurs.property.1.param.1=decorateurHumidite


# A placer en dernier, contrainte due à l'initialisation des beans étant des variables d'instance 
#                      d'autres beans, notamment les médiateuur ci-dessous
bean.id.11=servicelocator
servicelocator.class=question4.ServiceLocator
servicelocator.property.1=mediateur
servicelocator.property.1.param.1=mediateur_1
servicelocator.property.2=mediateur
servicelocator.property.2.param.1=mediateur_2





