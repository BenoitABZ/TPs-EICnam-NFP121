package question3;

import question1.SpecificationI;

public  class EstInferieur implements SpecificationI<Integer>{
    private int valeur;
    public EstInferieur(){}

    public void setValeur(int valeur){this.valeur = valeur;}

    public EstInferieur(int valeur){
        this.valeur = valeur;
    }

    public boolean isSatisfiedBy(Integer i){ 
        return i < valeur;
    }

    public String toString(){
        return "estInferieur_a_" + valeur;
    }
}

