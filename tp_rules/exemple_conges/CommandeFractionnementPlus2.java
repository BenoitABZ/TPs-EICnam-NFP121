package exemple_conges;

import question2.CommandI;

public class CommandeFractionnementPlus2 implements CommandI<Agent, ResultatConges> {
	
	private int plus2;

	@Override
	public ResultatConges execute(Agent e, ResultatConges r) throws Exception {
		r.incrementer(plus2);
		return r;

	}

	public int getPlus2() {
		return plus2;
	}

	// à injecter
	public void setPlus2(int plus1) {
		this.plus2 = plus1;
	}

}
