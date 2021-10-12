package question3;

import question3.tp3.*;

import java.awt.*;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;

/**
 * L'IHM crée, assemble le modèle, la vue et le contrôle (classe IHMCalculette).
 * 
 * Ajout simple d'une nouvelle vue. Il suffit de l'instancier et de l'ajouter au panneau.
 * 
 * @author benoit
 * @version 0.0.1
 */

public class IHMCalculette extends JFrame {
    public IHMCalculette() {
        super("IHM Calculette");
        PileModele<Integer> modele = new PileModele<Integer>(new Pile2<Integer>(5));
        Controleur controle = new Controleur(modele);
        Vue vue = new Vue(modele);
        Vue2 vue2 = new Vue2(modele);

        setLayout(new GridLayout(3, 1));
        add(vue2);
        add(vue);       
        add(controle);
        pack();
        setLocation(200,200);
        setVisible(true);

    }

    public static void main(String[] args){
        new IHMCalculette();
    }
}
