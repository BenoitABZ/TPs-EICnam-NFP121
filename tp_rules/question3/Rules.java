package question3;

import java.util.*;

public class Rules<E,R> implements RuleI<E,R>{
    private List<RuleI<E,R>> rules;
    
    public Rules(){
        this.rules = new ArrayList<>();
    }

    public void setRules(RuleI<E,R>[] rules){
        this.rules.addAll(Arrays.asList(rules));
    }
    
    public Rules<E,R> add(RuleI<E,R> rule){
    	
    	rules.add(rule);
        
        return this;
    }


    public R execute(E e,R r) throws Exception {
        for (RuleI<E,R> rule : rules) {
        	
        	r = rule.execute(e,r);
        	
        }
        return r;
    }

    public String toString(){
        String className=this.getClass().getSimpleName();
        StringBuffer sb = new StringBuffer(className+":"+"(");
        Iterator<RuleI<E,R>> it = this.rules.iterator();
        while(it.hasNext()){
            sb.append(it.next().toString());
            if(it.hasNext())sb.append(",");
        }
        sb.append(")");
        return sb.toString();
    }
}
