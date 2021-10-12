package question1;

public class ThemeDejaPresentException extends MediateurException{

    public ThemeDejaPresentException(){
        super();
    }

    public ThemeDejaPresentException(String message){
        super(message);
    }
}
