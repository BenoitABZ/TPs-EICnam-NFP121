package exemple_conges;

import question2.CommandI;

public class CommandeCumulConges implements CommandI<Agent,ResultatConges>{
    private int nombre;
    public void setNombre(int nombre){
        this.nombre = nombre;
    }

    public ResultatConges execute(Agent agent, ResultatConges resultat){
        resultat.incrementer(nombre);
        return resultat;
    }
    
    public String toString(){
        return "commandeCumulConges/"+nombre;
    }

}