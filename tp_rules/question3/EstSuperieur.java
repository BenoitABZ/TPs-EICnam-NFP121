package question3;

import question1.SpecificationI;

public  class EstSuperieur implements SpecificationI<Integer>{
    private static final boolean T = false; //true;
    private int valeur;
    public EstSuperieur(){}

    public void setValeur(int valeur){this.valeur = valeur;}

    public EstSuperieur(int valeur){
        this.valeur = valeur;
    }

    @Override
    public boolean isSatisfiedBy(Integer i){
        if(T)System.out.println("EstSuperieur_a/isSatisfiedBy " + (i>valeur) );
        return i > valeur;
    }

    public String toString(){
        return "estSuperieur_a_" + valeur;
    }
}

