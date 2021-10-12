package exemple_conges;
import java.util.List;
import question2.CommandI;

// Quels sont les services communaux
//
public class CommandeCumulServices implements CommandI<Mairie,List<String>>{

    @Override
    public List<String> execute(Mairie mairie, List<String> resultat){
        resultat.addAll(mairie.getServices());
        return resultat;
    }
    
    public String toString(){
        return "commandeCumulServices";
    }

}