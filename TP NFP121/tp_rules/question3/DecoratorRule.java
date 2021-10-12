package question3;
import question1.*;
import question2.*;

public abstract class DecoratorRule<E,R> implements RuleI<E,R>{
    protected RuleI<E,R> rule;
    
    public DecoratorRule(RuleI<E,R> rule){
        this.rule = rule;
    }
    
    public DecoratorRule(){}
    public void setRule(RuleI<E,R> rule){
        this.rule = rule;
    }

     public R execute(E e,R r) throws Exception {
        return rule.execute(e,r);
    }
    
    public String toString(){
        return super.toString();
    }
}