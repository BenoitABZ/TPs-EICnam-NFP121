package tp6.question3;

import tp6.question2.ExpressionBooleenne;

public class Assertion extends Instruction {

	private ExpressionBooleenne cond;

	public Assertion(ExpressionBooleenne cond) {
		this.cond = cond;
	}

	public <T> T accepter(VisiteurInstruction<T> vi) {
		return vi.visite(this);
	}

	public ExpressionBooleenne cond() {
		return cond;
	}

}