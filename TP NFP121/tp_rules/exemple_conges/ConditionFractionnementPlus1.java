package exemple_conges;

import java.time.LocalDate;
import java.util.List;

import question1.SpecificationI;

public class ConditionFractionnementPlus1 implements SpecificationI<Agent>{
	
	LocalDate mai;
	
	LocalDate octobre;

	public LocalDate getMai() {
		return mai;
	}

	//à injecter
	public void setMai(LocalDate mai) {
		this.mai = mai;
	}

	public LocalDate getOctobre() {
		return octobre;
	}
	
    //à injecter
	public void setOctobre(LocalDate octobre) {
		this.octobre = octobre;
	}

	@Override
	public boolean isSatisfiedBy(Agent e) {
		
		int count = 0;
		
		List<LocalDate> conges = e.getConges(); 
		
		for (LocalDate date : conges) {
			
			if (date.getMonthValue() < mai.getMonthValue() || date.getMonthValue() > octobre.getMonthValue()) {
				
				count++;
			}; 
		}
		
		if (count > 5 && count < 7) {
			
			return true;
		}else {
			
			return false;
		}
		
	}

}
