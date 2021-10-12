package question3;

import java.util.*;
import question1.*;
import question2.*;
import question3.*;

public class TestsTraceRule extends junit.framework.TestCase {

    public void testTraceRule() throws Exception{
        SpecificationI<Integer> sup = new EstSuperieur(0);
        MacroCommand<Integer,Integer> plus2 = new MacroCommand<>();
        plus2.add(new Inc()).add(new Inc());
        
        RuleI<Integer,Integer> rule = new Rule<>(sup,plus2);
        Integer res = rule.execute(2,0);
        //assertEquals(2, res.intValue());
        
        rule = new TraceRule<Integer,Integer>(rule); 

        res = 0; Integer x = 3;

        res = rule.execute(x,res);          // [res==0,x==3]if sup(x,4) res = plus2.execute(res);
        Integer res1 = rule.execute(5,res); // [x==5]if inf(x,4) res1 = plus2.execute(res);

        Rules<Integer,Integer> cluster = new Rules();
        cluster.add(rule).add(rule).add(rule);

        Integer res2 = cluster.execute(2,0);

    }
    
    public void testTraceResultRule() throws Exception{
        SpecificationI<Integer> inf = new EstInferieur(4);
        MacroCommand<Integer,Integer> plus2 = new MacroCommand<>();
        plus2.add(new Inc()).add(new Inc());
        
        RuleI<Integer,Integer> rule = new TraceResultRule<>(new Rule<>(inf,plus2)); 

        Integer res = 0, x = 3;

        res = rule.execute(x,res);          // [res==0,x==3]if inf(x,4) res = plus2.execute(res);
        Integer res1 = rule.execute(5,res); // [x==5]if inf(x,4) res1 = plus2.execute(res);

        Rules<Integer,Integer> cluster = new Rules();
        cluster.add(rule).add(rule).add(rule);

        Integer res2 = cluster.execute(2,0);

    }
    
    public void testTraceResultRuleTraceRule() throws Exception{
        SpecificationI<Integer> sup = new EstSuperieur(0);
        MacroCommand<Integer,Integer> plus2 = new MacroCommand<>();
        plus2.add(new Inc()).add(new Inc());
        RuleI<Integer,Integer> rule = new Rule<>(sup,plus2);
         
        rule =  new TraceResultRule<>( new TraceRule<>(rule)); 

        Integer res = 0, x = 3;

        res = rule.execute(x,res);          


        Integer res1 = rule.execute(5,res); 
       
        Rules<Integer,Integer> cluster = new Rules();
        cluster.add(rule).add(rule).add(rule);

        Integer res2 = cluster.execute(5,0);
        
    }

}
