package exemple_alcool;

import java.util.*;
import question1.*;


/**
 *  Extrait de https://gist.github.com/marc-bouvier/84a6d0f1836762912ebe84d4828d0b13
 * 
 * <b>Design pattern Specification</b>
 * <p>Il permet de combiner (par chaînage) et de réutiliser des règles métier portant sur le même 
 * concept. En nommant ces règles sous forme de classes le code peut être lisible par les experts 
 * métiers</p>
 * <p><b>Exemple d'utilisation</b></p>
 * <pre><code>
 * boolean consommationDAlcoolEstLegale =
 * enFrance().and(aPlusDe18Ans())
 * .or(auxEtatsUnis().and(aPlusDe21Ans())
 * .or(
 *    enAllemagne()
 *    .and(vinsMousseuxEtBieres()
 *        .and(enPresenceDesParents().and(aPlusDe14Ans()))
 *        .or(aPlusDe16Ans()))
 *    .or(aPlusDe18Ans)
 * ).isSatisfiedBy(clientDuBar);
 * </code></pre>
 * <p><b>Exemple de prédicat</b></p>
 * <pre><code>
 * public Specification&lt;ClientDuBar&gt; aPlusDe18Ans() {
 *     return new LeafSpecification&lt;ClientDuBar&gt;() {
 *    {@literal @}Override
 *     public boolean isSatisfiedBy(ClientDuBar candidate) {
 *         return candidate.getAge()&gt;=18;
 *     }};
 * }
 * </code></pre>
 *
 * @param <T>
 * @see <a href="https://www.martinfowler.com/apsupp/spec.pdf">Specification par Eric Evans et Martin Fowler</a>
 * @see <a href="http://blog.xebia.fr/2009/12/29/le-pattern-specification-pour-la-gestion-de-vos-regles-metier/">Le pattern Specification pour la gestion de vos règles métier</a>
 * @see <a href="https://www.amazon.fr/Domain-Driven-Design-Distilled-Vaughn-Vernon-ebook/dp/B01JJSGE5S/ref=sr_1_1?s=english-books&ie=UTF8&qid=1503393085&sr=1-1&keywords=ddd+distilled">Domain-Driven Design Distilled (pour commencer en douceur)</a>
 * @see <a href="https://www.amazon.fr/Domain-Driven-Design-Tackling-Complexity-Software/dp/0321125215">Domain Driven Design (DDD)</a>
 * @see <a href="http://domainlanguage.com/">Eric Evans - @ericevans0</a>
 * @see <a href="https://www.martinfowler.com/">Martin Fowler - @martinfowler</a>
 */

public class RegleAlcoolMoinsDe18{

    public static class APlusDe implements SpecificationI<ClientDuBar>{ 
        private static final boolean T = true;
        private int age;
        public APlusDe(int age){
            this.age = age;
        }

        public APlusDe(){}

        public void setAge(int age){ this.age = age;}

        public boolean isSatisfiedBy(ClientDuBar candidate){
            if(T)System.out.println("ClientDuBar: APlusDe " + (candidate.getAge()>=age));
            return candidate.getAge()>=age;
        }

        public String toString(){
            return "aPlusDe"+age+"ans()";
        }
    }

    public static class EstEn implements SpecificationI<ClientDuBar>{ 
        private static final boolean T = true;
        private String lieu;
        public EstEn(String lieu){
            this.lieu = lieu;
        }

        public EstEn(){}

        public void setLieu(String lieu){ this.lieu = lieu;}

        public String getLieu(){ return this.lieu;}

        public boolean isSatisfiedBy(ClientDuBar candidate){
            return candidate.getBar().getLieu().contains(lieu);
        }

        public String toString(){
            return "En" + lieu +"()";
        }
    }

    public static class EstAux extends EstEn{ // renommage

        public EstAux(String lieu){
            super(lieu);
        }

        public EstAux(){}

        public String toString(){
            return "Aux" + getLieu() +"()";
        }
    }

    public static class VinsMousseuxEtBieres implements SpecificationI<ClientDuBar> { 
        private List<String> boissons;
        public VinsMousseuxEtBieres(String[] boissons){
            this();
            this.boissons.addAll(Arrays.asList(boissons));
        }

        public VinsMousseuxEtBieres(){
            this.boissons = new ArrayList<>();
            this.boissons.add("erdinger"); // par défaut...
        }
        
        public void setBoissons(String[] boissons){
            this.boissons.addAll(Arrays.asList(boissons));
        }

        public boolean isSatisfiedBy(ClientDuBar candidate){
            return boissons.contains(candidate.getBoisson());
        }

        public String toString(){
            return "VinsMousseuxEtBieres" + "()";
        }
    }

    
    
    public static void main(String[] args){
        System.out.println("exemple non terminé, extrait de https://gist.github.com/marc-bouvier/84a6d0f1836762912ebe84d4828d0b13");
        
        Bar pouchla = new Bar("POUCHLA","10 rue Mandar Paris, France");
        Bar zurSteipe = new Bar("Zur Steipe","Dietrichstrasse 54 Trier, Allemagne");

        ClientDuBar clientDuBar = new ClientDuBar("Jim",20,zurSteipe,"erdinger");

        SpecificationI<ClientDuBar> regle = 
            new OneOf()
            .add(new And(new EstEn("France"),new APlusDe(18)))
            .add(new And(new EstAux("EtatsUnis"),new APlusDe(21)))
            .add(new And(new EstEn("Allemagne"),
                    new Or(new And(new VinsMousseuxEtBieres(),new TRUE()),// à terminer
                        new APlusDe(18))));

        System.out.println("regle:" + regle);

        boolean consommationDAlcoolEstLegale = regle.isSatisfiedBy(clientDuBar);
        System.out.println("clientDuBar: " + clientDuBar);
        System.out.println("consommationDAlcoolEstLegale: " + consommationDAlcoolEstLegale);

        // * enFrance().and(aPlusDe18Ans())
        // * .or(auxEtatsUnis().and(aPlusDe21Ans())
        // * .or(
        // *    enAllemagne()
        // *    .and(vinsMousseuxEtBieres()
        // *        .and(enPresenceDesParents().and(aPlusDe14Ans()))
        // *        .or(aPlusDe16Ans()))
        // *    .or(aPlusDe18Ans)
        // * ).isSatisfiedBy(clientDuBar);
    }

}
