package exemple_conges;

import question2.CommandI;

public class CommandeFractionnementPlus1 implements CommandI<Agent, ResultatConges> {

	private int plus1;

	@Override
	public ResultatConges execute(Agent e, ResultatConges r) throws Exception {
		r.incrementer(plus1);
		return r;

	}

	public int getPlus1() {
		return plus1;
	}

	// à injecter
	public void setPlus1(int plus1) {
		this.plus1 = plus1;
	}

}
