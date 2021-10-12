package tp6.question2;

public class Vrai extends ExpressionBooleenne {

	public <T> T accepter(VisiteurExpressionBooleenne<T> v) {
		return v.visite(this);
	}
	
}