package question3;

import question2.CommandI;

public class Inc implements CommandI<Integer,Integer>{
    private static final boolean T = false; //true;
    private int increment=1;
    public Inc(int increment){
        this.increment = increment;
    }

    public Integer execute(Integer entite,Integer i){
        if(T)System.out.println("inc.execute("+entite+","+i+")");
        return new Integer(i+increment);
    }

    public String toString(){
        return "inc";
    }

    public Inc(){}

}