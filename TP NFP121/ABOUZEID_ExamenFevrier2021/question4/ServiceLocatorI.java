package question4;



import java.util.Iterator;

import question1.*;

public interface ServiceLocatorI extends Iterable<String>{

    /** Recherche d'un mediateur, à l'aide de son nom.
     * @param nomDuMediateur le nom du mediateur
     * @return le médiateur avec ce nom
     */
    public MediateurI rechercher(String nomDuMediateur) throws Exception;

    /** Ajout d'un médiateur,
     * <b>Attention</b> pas de tests sur la présence éventuelle de doublons
     * @exception si l'ajout échoue 
     */
    public void ajouterMediateur(MediateurI mediateur) throws Exception;

    /** Ajout d'un médiateur, 'setMediateur': synonyme de 'ajouterMediateur'
     * pour femtoContainer et l'appel implicite des mutateurs setXXX
     * <b>Attention</b> pas de tests sur la présence éventuelle de doublons
     * @exception si l'ajout échoue 
     */
    public void setMediateur(MediateurI mediateur) throws Exception;

    /** Retourne un itérateur sur tous les mediateurs accessibles.
     *  i.e. tous les médiateurs ajoutés par les appels de {add,set}Mediateur
     */
    public Iterator<String> iterator();

     /** Invariant de classe.
     * @return vrai si l'invariant est satisfait
     */
    public boolean repOk();
    
    /** Fonction d'abstraction. optionnelle, optionnelle
     * @return une représentation dite abstraite
     */
    public <T> T af();

}