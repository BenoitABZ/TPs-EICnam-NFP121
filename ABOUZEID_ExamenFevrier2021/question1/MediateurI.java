package question1;

import java.util.Iterator;

public interface MediateurI extends Iterable<String>{

    /** Obtention du nom du médiateur.
     * @return le nom de médiateur, (ce nom ne peut-être égal à null)
     */
    public String getNom();

    /** Ajout d'un thème de publication.
     * @param theme le thème à ajouter
     * @param commande la commande exécutée lors d'une publication sur ce thème
     * @throws ThemeDejaPresentException si le thème a déjà été ajouté
     * @throws NullPointerException si le thème ou commande == null
     */
    public void ajouterTheme(String theme, CommandeI<EvenementI> commande) throws ThemeDejaPresentException;

    /** Retrait d'un thème de ce médiateur.
     * @param theme le thème à retirer
     * @throws ThemeAbsentException si le thème est absent du médiateur
     */
    public void retirerTheme(String theme) throws ThemeAbsentException;

    /** Publication d'un évènement avec ce thème.
     * @param evenement l'evénement contient le thème de publication
     * @throws ThemeAbsentException si le thème est absent du médiateur ou est égal à null
     * @throws NullPointerException si evenement == null
     */
    public void publier(EvenementI evenement) throws ThemeAbsentException;
    
    /** Itérateur du médiateur.
     * @return un itérateur sur les les thèmes presents
     */
    public Iterator<String> iterator();
    
    
    /** Invariant de classe.
     * @return vrai si l'invariant est satisfait
     */
    public boolean repOk();
    
    /** Fonction d'abstraction.
     * @return une représentation dite abstraite
     */
    public <T> T af();

}
