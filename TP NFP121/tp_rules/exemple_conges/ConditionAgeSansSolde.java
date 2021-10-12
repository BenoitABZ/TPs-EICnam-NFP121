package exemple_conges;

import question1.SpecificationI;

public class ConditionAgeSansSolde implements SpecificationI<Agent>{

	   private int ageMinRequis;
	   
	   //à injecter 21 ans
	   public void setAgeMinRequis(int age){
	       this.ageMinRequis = age;
	   }
	    
	   public int getAgeMinRequis() {
		return ageMinRequis;
	}

	    public boolean isSatisfiedBy(Agent agent){
	      
	      return agent.getAge()<=ageMinRequis;
	   }
	   
	    public String toString(){
	        return "conditionCongesSansSolde";
	    }

}
