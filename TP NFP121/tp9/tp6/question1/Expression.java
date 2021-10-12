package tp6.question1;

/**
 * Une expression arithmétique
 */
public abstract class Expression implements java.io.Serializable {

	public abstract <T> T accepter(VisiteurExpression<T> v);

}