package question1;

public class TestsSpecification extends junit.framework.TestCase {
    private static class EstPair implements SpecificationI<Integer>{
        public boolean isSatisfiedBy(Integer i){ 
            return i%2==0;
        }

        public String toString(){
            return "estPair";
        }
    }

    private static class EstInferieur implements SpecificationI<Integer>{
        private int valeur;
        public EstInferieur(int valeur){
            this.valeur = valeur;
        }

        public void setValeur(int valeur){
            this.valeur = valeur;
        }

        public EstInferieur(){ }

        public boolean isSatisfiedBy(Integer i){ 
            return i < valeur;
        }

        public String toString(){
            return "estInferieur_a_" + valeur;
        }
    }
    private static class EstSuperieur implements SpecificationI<Integer>{
        private int valeur;
        public EstSuperieur(int valeur){
            this.valeur = valeur;
        }

        public boolean isSatisfiedBy(Integer i){ 
            return i > valeur;
        }

        public String toString(){
            return "estSuperieur_a_" + valeur;
        }
    }

    private static class EstImpair extends Not<Integer>{
        public EstImpair(){
            super(new EstPair());
        }

        public String toString(){
            return "estImpair";
        }
    }

    public void testSimpleAvecDesEntiers() {
        SpecificationI<Integer> pair = new EstPair();
        SpecificationI<Integer> inf  = new EstInferieur(10);
        SpecificationI<Integer> sup  = new EstSuperieur(5);

        assertTrue(pair.isSatisfiedBy(4));
        assertEquals("estPair", pair.toString());
        assertTrue(inf.isSatisfiedBy(4));
        assertEquals("estInferieur_a_10", inf.toString());

        CompositeSpecification<Integer> all = new All<Integer>();
        assertFalse(all.add(sup).add(pair).add(inf).isSatisfiedBy(14));
        assertEquals("all(estSuperieur_a_5,estPair,estInferieur_a_10)", all.toString());
        CompositeSpecification<Integer> oneOf = new OneOf<Integer>();
        assertTrue(oneOf.add(sup).add(pair).add(inf).isSatisfiedBy(14));
        assertEquals("oneOf(estSuperieur_a_5,estPair,estInferieur_a_10)", oneOf.toString());

        SpecificationI<Integer> notOneOf = new Not(oneOf);
        assertFalse(notOneOf.isSatisfiedBy(14));
        assertEquals("not(oneOf(estSuperieur_a_5,estPair,estInferieur_a_10))", notOneOf.toString());

        SpecificationI<Integer> notAll = new Not(all);
        assertTrue(notAll.isSatisfiedBy(14));
        assertEquals("not(all(estSuperieur_a_5,estPair,estInferieur_a_10))", notAll.toString());

        CompositeSpecification<Integer> onlyOne = new OnlyOne<Integer>();
        assertTrue(onlyOne.add(sup).add(pair).add(inf).isSatisfiedBy(15));

    }

    public void testTableDeVeriteAnd(){
    	
    	
    	
        assertTrue(new And<>(new TRUE(),new TRUE<>()).isSatisfiedBy(null));
        assertFalse(new And<>(new TRUE<>(),new FALSE<>()).isSatisfiedBy(null));
        assertFalse(new And<>(new FALSE<>(),new TRUE<>()).isSatisfiedBy(null));
        assertFalse(new And<>(new FALSE<>(),new FALSE<>()).isSatisfiedBy(null));

        assertFalse(new And<>(new FALSE<>(),new MAYBE>()).isSatisfiedBy(null));
    }

    public void testTableDeVeriteOr(){
        assertTrue(new Or<>(new TRUE(),new TRUE()).isSatisfiedBy(null));
        assertTrue(new Or<>(new TRUE(),new FALSE()).isSatisfiedBy(null));
        assertTrue(new Or<>(new FALSE(),new TRUE()).isSatisfiedBy(null));
        assertFalse(new Or<>(new FALSE(),new FALSE()).isSatisfiedBy(null));

        assertTrue(new Or<>(new TRUE(),new MAYBE()).isSatisfiedBy(null));
    }

    public void testTableDeVeriteXor(){
        assertFalse(new Xor<>(new TRUE(),new TRUE()).isSatisfiedBy(null));
        assertTrue(new Xor<>(new TRUE(),new FALSE()).isSatisfiedBy(null));
        assertTrue(new Xor<>(new FALSE(),new TRUE()).isSatisfiedBy(null));
        assertFalse(new Xor<>(new FALSE(),new FALSE()).isSatisfiedBy(null));

    }

    public void testTableDeVeriteImplies(){
        // p ==> q <==> non p ou q
        assertTrue(new Implies<>(new TRUE(),new TRUE()).isSatisfiedBy(null));
        assertFalse(new Implies<>(new TRUE(),new FALSE()).isSatisfiedBy(null));
        assertTrue(new Implies<>(new FALSE(),new TRUE()).isSatisfiedBy(null));
        assertTrue(new Implies<>(new FALSE(),new FALSE()).isSatisfiedBy(null));
        assertTrue(new Implies<>(new Xor<>(new FALSE(),new FALSE()), new FALSE()).isSatisfiedBy(null));

        assertTrue(new Implies<>(new FALSE(),new MAYBE()).isSatisfiedBy(null));
    }

 

    public void testTableDeVeriteNoneNone(){
        CompositeSpecification<Object> none = new None<>().add(new FALSE()).add(new FALSE()).add(new FALSE());
        assertTrue(none.toString()+"!= true ???",none.isSatisfiedBy(null));
        none = new None<>().add(new TRUE()).add(none).add(none);
        assertFalse(none.toString()+"!= false ???",none.isSatisfiedBy(null));
        // System.out.println("\tnone : " + none);
        none= new None<>().add(none).add(none);        
        assertFalse(none.toString()+"!= false ???",none.isSatisfiedBy(null));
        none=new None<>().add(new TRUE()).add(new FALSE()).add(none);
        assertFalse(none.toString()+"!= false ???",none.isSatisfiedBy(null));
        none=new None<>().add(new FALSE()).add(new FALSE()).add(new FALSE());
        assertTrue(none.isSatisfiedBy(null));
        // System.out.println("\tnone : " + none);
        CompositeSpecification<Object> nonne = new None<>().add(none);//.add(none).add(none);
         // System.out.println("\tnonne : " + nonne);
        assertTrue(nonne.isSatisfiedBy(null));
        assertFalse(new None<>().add(new FALSE()).add(new TRUE()).add(nonne).isSatisfiedBy(null));
        assertFalse(new None<>().add(new TRUE()).add(new FALSE()).add(new FALSE()).add(nonne).isSatisfiedBy(null));

        assertTrue(new None<>().add(none).add(none).isSatisfiedBy(null));
        assertTrue(new None<>().add(new FALSE()).add(none).add(none).isSatisfiedBy(null));
    
        CompositeSpecification<Object> nonnette = new None<>().add(nonne);
        assertTrue(nonnette.toString() +"!=true ???",nonnette.isSatisfiedBy(null));
        
        nonne = new None<>().add(none).add(none).add(none);
        //System.out.println("\tnonne : " + nonne);
        assertTrue(nonne.toString() +"!=true ???",nonne.isSatisfiedBy(null));
        
        CompositeSpecification<Object> oneOf = new OneOf<>().add(new FALSE()).add(new TRUE()).add(new FALSE());
        nonne = new None<>().add(none).add(oneOf).add(none);
        //System.out.println("\tnonne : " + nonne);
        assertFalse(nonne.toString() +"!=false ???", nonne.isSatisfiedBy(null));
    
    }

    public void testTableDeVeriteEquiv(){
        // p ==> q et q ==> p alors p <==> q
        assertTrue(new Equiv<>(new TRUE(),new TRUE()).isSatisfiedBy(null));
        assertFalse(new Equiv<>(new TRUE(),new FALSE()).isSatisfiedBy(null));
        assertFalse(new Equiv<>(new FALSE(),new TRUE()).isSatisfiedBy(null));
        assertTrue(new Equiv<>(new FALSE(),new FALSE()).isSatisfiedBy(null));
    }

    public void testMayBe(){
        boolean mayBe = new MAYBE<>().isSatisfiedBy(null);
        assertTrue("mayBe || !mayBe", mayBe || !mayBe);
    }

    public void testAll(){
        assertFalse(new All<>().add(new FALSE()).add(new FALSE()).add(new FALSE()).isSatisfiedBy(null));
        assertFalse(new All<>().add(new FALSE()).add(new TRUE()).add(new FALSE()).isSatisfiedBy(null));
        assertFalse(new All<>().add(new TRUE()).add(new FALSE()).add(new FALSE()).isSatisfiedBy(null));
        assertTrue(new All<>().add(new TRUE()).add(new TRUE()).add(new TRUE()).isSatisfiedBy(null));
    }

    public void testOneOf(){
        assertFalse(new OneOf<>().add(new FALSE()).add(new FALSE()).add(new FALSE()).isSatisfiedBy(null));
        assertTrue(new OneOf<>().add(new FALSE()).add(new TRUE()).add(new FALSE()).isSatisfiedBy(null));
        assertTrue(new OneOf<>().add(new TRUE()).add(new FALSE()).add(new FALSE()).isSatisfiedBy(null));
        assertTrue(new OneOf<>().add(new TRUE()).add(new TRUE()).add(new TRUE()).isSatisfiedBy(null));
    }

    public void testOnlyOne(){
        assertFalse(new OnlyOne<>().add(new FALSE()).add(new FALSE()).add(new FALSE()).isSatisfiedBy(null));
        assertTrue(new OnlyOne<>().add(new FALSE()).add(new TRUE()).add(new FALSE()).isSatisfiedBy(null));
        assertTrue(new OnlyOne<>().add(new TRUE()).add(new FALSE()).add(new FALSE()).isSatisfiedBy(null));
        assertFalse(new OnlyOne<>().add(new TRUE()).add(new TRUE()).add(new TRUE()).isSatisfiedBy(null));
    }

        public void testTableDeVeriteNone(){
        assertTrue(new None<>().add(new FALSE()).add(new FALSE()).isSatisfiedBy(null));
        assertFalse(new None<>().add(new TRUE()).add(new FALSE()).isSatisfiedBy(null));
        assertFalse(new None<>().add(new FALSE()).add(new TRUE()).isSatisfiedBy(null));
        assertFalse(new None<>().add(new TRUE()).add(new FALSE()).add(new FALSE()).isSatisfiedBy(null));

        assertTrue(new None<>().add(new FALSE()).add(new FALSE()).add(new FALSE()).isSatisfiedBy(null));
    }


    public void testReflectCondition(){
        ReflectSpecification<Object> inf = new ReflectSpecification<>();
        inf.setSpecification("question1.TestsSpecification$EstInferieur");
        assertTrue(inf.isSatisfiedBy(-14));

        // Accès à l'attribut valeur, 1) par un changement de type
        EstInferieur estInf = (EstInferieur)(SpecificationI)inf.getSpecification();
        estInf.setValeur(-20); // ici estinf == inf
        assertFalse(inf.isSatisfiedBy(-14));

        // Accès à l'attribut valeur, 2) par introspection
        java.lang.reflect.Field field_valeur = null;
        try{
            //Class<?> clazz = inf.getSpecification().getClass();
            //field_valeur = clazz.getDeclaredField("valeur");
            field_valeur = EstInferieur.class.getDeclaredField("valeur");
            assertEquals("valeur",field_valeur.getName());
            field_valeur.setAccessible(true);
            field_valeur.setInt(inf.getSpecification(),-10);
        }catch(Exception e){
            fail("L'attribut valeur n'est pas accessible par introspection ???");
        }
        assertTrue(inf.isSatisfiedBy(-14));
    }

    public void testReflectOr(){
        ReflectSpecification<Object> orSpec = new ReflectSpecification<>();
        orSpec.setSpecification("question1.Or");
        Binary<Object> or = (Binary)orSpec.getSpecification();
        or.setSpec1(new TRUE<>());
        or.setSpec2(new FALSE<>());
        assertTrue(or.isSatisfiedBy(null));
    }
    
    public void testReflectMayBe(){
        ReflectSpecification<Object> maybe = new ReflectSpecification<>();
        maybe.setSpecification("question1.MAYBE");
        assertTrue(maybe.isSatisfiedBy(null) || !maybe.isSatisfiedBy(null));
    }

    public void testReflectAndOr(){
        ReflectSpecification<Object> orSpec = new ReflectSpecification<>();
        orSpec.setSpecification("question1.Or");
        Binary<Object> or = (Binary)orSpec.getSpecification();
        or.setSpec1(new TRUE<>());
        or.setSpec2(new FALSE<>());
        ReflectSpecification<Object> andSpec = new ReflectSpecification<>();
        andSpec.setSpecification("question1.And");
        Binary<Object> and = (Binary)andSpec.getSpecification();
        and.setSpec1(or);
        and.setSpec2(new TRUE<>());
        assertTrue(and.isSatisfiedBy(null));
        
        and.setSpec2(new FALSE<>());
        assertFalse(and.isSatisfiedBy(null));
    }
    public void setUp() {
    }

    public void tearDown() {
        //Libérez ici les ressources engagées par setUp()
    }
}
