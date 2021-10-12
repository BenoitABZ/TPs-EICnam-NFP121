#
verbose=true

bean.id.1=anciennete
# ConditionAnciennete anciennete = new ConditionAnciennete();
anciennete.class=exemple_conges.ConditionAnciennete
# anciennete.setNombreDAnneesDAncienneteRequis(5);
anciennete.property.1=nombreDAnneesDAncienneteRequis
anciennete.property.1.param.1=5

bean.id.2=penibilite
# ConditionPenibiliteDuService penibilite = new ConditionPenibiliteDuService();
penibilite.class=exemple_conges.ConditionPenibiliteDuService
# penibilite.setService(new String[]{"Egouts","Voierie"});
penibilite.property.1=services
penibilite.property.1.param.1=Egouts Voierie

bean.id.3=bonifie
bonifie.class=exemple_conges.ConditionCongesBonifies
bonifie.property.1=regions
bonifie.property.1.param.1=Dom Saint-Barthélemy Saint-Martin Saint-Pierre-et-Miquelon

bean.id.4=joursDuMaire
#ConditionJoursDuMaire joursDuMaire = new ConditionJoursDuMaire();
joursDuMaire.class=exemple_conges.ConditionJoursDuMaire

bean.id.5=inc
inc.class=exemple_conges.CommandeCumulConges
inc.property.1=nombre
inc.property.1.param.1=1

bean.id.6=inc5
inc5.class=question2.MacroCommand
inc5.property.1=commands
inc5.property.1.param.1=inc inc inc inc inc

bean.id.7=inc10
inc10.class=question2.MacroCommand
inc10.property.1=commands
inc10.property.1.param.1=inc5 inc5

bean.id.8=regle_anciennete
regle_anciennete.class=question3.Rule
regle_anciennete.property.1=specification
regle_anciennete.property.1.param.1=anciennete
regle_anciennete.property.2=command
regle_anciennete.property.2.param.1=inc5

bean.id.9=regle_penibilite
regle_penibilite.class=question3.Rule
regle_penibilite.property.1=specification
regle_penibilite.property.1.param.1=penibilite
regle_penibilite.property.2=command
regle_penibilite.property.2.param.1=inc10

bean.id.10=regle_joursDuMaire
regle_joursDuMaire.class=question3.Rule
regle_joursDuMaire.property.1=specification
regle_joursDuMaire.property.1.param.1=joursDuMaire
regle_joursDuMaire.property.2=command
regle_joursDuMaire.property.2.param.1=inc10

bean.id.11=regles_conges
regles_conges.class=question3.Rules
regles_conges.property.1=rules
regles_conges.property.1.param.1=regle_anciennete regle_penibilite regle_joursDuMaire


bean.id.12=true
true.class=question1.TRUE

bean.id.13=regles_services
regles_services.class=question3.ReflectRule
regles_services.property.1=specificationName
regles_services.property.1.param.1=question1.TRUE
regles_services.property.2=commandName
regles_services.property.2.param.1=exemple_conges.CommandeCumulServices

bean.id.14=traceRuleServices
traceRuleServices.class=question3.TraceRule
traceRuleServices.property.1=rule
traceRuleServices.property.1.param.1=regles_services

bean.id.15=trace_regles_services
trace_regles_services.class=question3.TraceResultRule
trace_regles_services.property.1=rule
trace_regles_services.property.1.param.1=traceRuleServices

bean.id.16=traceRuleConges
traceRuleConges.class=question3.TraceRule
traceRuleConges.property.1=rule
traceRuleConges.property.1.param.1=regles_conges

bean.id.17=trace_regles_conges
trace_regles_conges.class=question3.TraceResultRule
trace_regles_conges.property.1=rule
trace_regles_conges.property.1.param.1=traceRuleConges
