package tp6.question2;

public abstract class ExpressionBooleenne implements java.io.Serializable {

	public abstract <T> T accepter(VisiteurExpressionBooleenne<T> v);

}