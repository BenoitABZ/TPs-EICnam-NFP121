verbose=true

bean.id.1=i
i.class=java.util.concurrent.atomic.AtomicInteger

bean.id.2=inf10
inf10.class=question4.TestsRulesWithFemtoContainer$LessThan
inf10.property.1=operand
inf10.property.1.param.1=10

bean.id.3=inc1
inc1.class=question4.TestsRulesWithFemtoContainer$IncCommand
inc1.property.1=delta
inc1.property.1.param.1=1

bean.id.4=inc2
inc2.class=question4.TestsRulesWithFemtoContainer$IncCommand
inc2.property.1=delta
inc2.property.1.param.1=2

bean.id.5=inc
inc.class=question2.MacroCommand
inc.property.1=commands
inc.property.1.param.1=inc1 inc1
#inc.property.1.param.1=inc2 inc2

bean.id.6=rule1
rule1.class=question3.Rule
rule1.property.1=specification
rule1.property.1.param.1=inf10
rule1.property.2=command
rule1.property.2.param.1=inc

bean.id.7=produit
produit.class=question4.Produit
produit.property.1=name
produit.property.1.param.1=orange
produit.property.2=color
produit.property.2.param.1=orange
produit.property.3=poid
produit.property.3.param.2=2

bean.id.8=panier
panier.class=question4.Panier
panier.property.1=poidMax
panier.property.1.param.1=1
panier.property.2=ajouterProduit
panier.property.2.param.1=produit

bean.id.9=specification
specification.class=question4.AuPoidMax

bean.id.10=command
specification.class=question4.AuPoidMaxCommand


bean.id.11=rulePanier
rulePanier.class=question4.Rule
rulePanier.property.1=specification
rulePanier.property.1.param1=specification
rulePanier.property.2=command
rulePanier.property.2.param1=command



