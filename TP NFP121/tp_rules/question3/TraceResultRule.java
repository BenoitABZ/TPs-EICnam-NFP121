package question3;
public class TraceResultRule<E,R> extends DecoratorRule<E,R>{
    public TraceResultRule(RuleI<E,R> rule){
        super(rule);
    }
     public TraceResultRule(){
        super();
    }  
    public R execute(E e, R r) throws Exception {
        System.err.print("pre_execute:[e/" + e + ",r/" + r + "]");
        R r1 = super.rule.execute(e,r);
        System.err.println("post_execute:[r'/" + r1 + "]");
        return r1;
    }
}
