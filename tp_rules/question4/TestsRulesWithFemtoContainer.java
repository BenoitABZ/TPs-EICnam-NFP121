package question4;

import container.ApplicationContext;
import container.Factory;

import question1.SpecificationI;
import question2.CommandI;
import question3.Rule;
import java.util.concurrent.atomic.AtomicInteger;

public class TestsRulesWithFemtoContainer extends junit.framework.TestCase {

        public static class IncCommand implements CommandI<Integer,AtomicInteger>{
        private int delta=1;
        public void setDelta(int delta){
            this.delta = delta;
        }

        public AtomicInteger execute(Integer unused, AtomicInteger i){
            i.addAndGet(delta);
            return i;
        }

        public String toString(){
            return "inc";
        }
    }

    
    public static class GreaterThan implements SpecificationI<Integer>{
        private static final boolean T = true; //false;
        private int operand;
        public void setOperand(int operand){ this.operand = operand;}

        public boolean isSatisfiedBy(Integer i){
            if(T)System.out.println(" i > operand: return " + i + " > " + operand);
            return i>operand;
        }
    }

    public static class LessThan implements SpecificationI<Integer>{
        private static final boolean T = true; //false;
        private int operand;
        public void setOperand(int operand){ this.operand = operand;}

        public boolean isSatisfiedBy(Integer i){
            if(T)System.out.println(" i < operand: return " + i + " < " + operand);
            return i<operand;
        }

        public String toString(){
            return "i<"+operand;
        }
    }

    public void testSimpleAvecDesEntiers() throws Exception{
        ApplicationContext ctx = Factory.createApplicationContext("./question4/README.TXT");

        AtomicInteger i = ctx.getBean("i"); // AtomicInteger est mutable, Integer ne l'est pas...
        System.out.println("i: " + i.get());
        
        Rule<Integer,AtomicInteger> rule1 = ctx.getBean("rule1");
        // rule1 : if(i>10) i = i+1;
        System.out.println("rule1: " + rule1);
        AtomicInteger res = new AtomicInteger(0);
        res = rule1.execute(0,res);
        System.out.println("res : " + res.get());
        assertEquals("res != 2 ???", 2,res.get());
        res = rule1.execute(15,res);
        System.out.println("res : " + res.get());
        assertEquals("res != 2 ???", 2,res.get());
        


    }
    
    public void testPanier() throws Exception{
    	
    	 ApplicationContext ctx = Factory.createApplicationContext("./question4/README.TXT");
    	 
    	 Panier panier = ctx.getBean("panier");
    	 
    	 Rule<Panier, String> rulePanier = ctx.getBean("rulePanier");
    	 
    	 String result = "";
    	 
    	 result = (String) rulePanier.execute(panier, result);
    	 
    	 assertEquals("Poid MAX du panier atteint", result);
   
        


    }
}
    
