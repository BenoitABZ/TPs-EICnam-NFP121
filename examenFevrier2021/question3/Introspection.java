package question3;

import java.util.Properties;

import question1.CommandeI;
import question1.EvenementI;

import java.io.*;
import java.lang.reflect.*;
/**
 * @author Votre nom, prénom    // a completer
 */
public class Introspection{


    /** Ajout d'un maillon comme successeur du maillonCourant.
     * La classe associée à ce maillon ne possède pas d'attribut à initialiser.
     * @param maillonCourant son successeur sera le maillon créé par introspection si il existe
     * @param nomDuMaillon le nom de la classe associée à ce maillon
     * @return le maillon ainsi créé
     */
    public static <E> Maillon<E> ajouterUnMaillon(Maillon<E> maillonCourant,String nomDuMaillon) throws Exception{
       
        Class<?> classMaillon = Class.forName(nomDuMaillon);
        Maillon<E> maillon = (Maillon<E>)classMaillon.newInstance();
        maillon.setSuccesseur(maillonCourant.getSuccesseur());
        maillonCourant.setSuccesseur(maillon);
        return maillon;
    }

    /** Ajout d'un maillon comme successeur du maillonCourant. 
     * Le nom du maillon est précisé dans un fichier texte (README.TXT) l'icône en haut à gauche
     * @param maillonCourant son successeur sera le maillon créé par introspection si il existe
     * @param nomDuFichier le nom du fichier dans lequel sera précisé le nomDuMaillon
     */
    public static <E> Maillon<E> ajouterUnMaillonDepuisUnFichier(Maillon<E> maillonCourant,String nomDuFichier)throws Exception{
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("README.TXT")));
 
        return ajouterUnMaillon(maillonCourant,properties.getProperty("nomDuMaillon"));
    }
}
