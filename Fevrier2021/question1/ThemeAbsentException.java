package question1;


public class ThemeAbsentException extends MediateurException{
    public ThemeAbsentException(){
        super();
    }

    public ThemeAbsentException(String message){
        super(message);
    }
}
