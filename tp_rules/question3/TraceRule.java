package question3;

import question1.SpecificationI;
import question2.CommandI;
import question3.RuleI;

public class TraceRule<E,R> extends DecoratorRule<E,R>{
   
    public TraceRule(RuleI<E,R> rule){
        super(rule);
    }
   
    public TraceRule(){
        super();
    }
    
    public R execute(E e,R r) throws Exception {
        System.err.print("\t" + rule.toString());
        return super.execute(e,r);
    }
}