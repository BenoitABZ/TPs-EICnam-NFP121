package question1;
/** L'interface SpecificationI.
 * @param <E> L'entité métier, sur laquelle porte la condition.
 */
public interface SpecificationI<E>{
    /** La condition à satisfaire.
     * @param e le paramètre de la méthode
     */
    public abstract boolean isSatisfiedBy(E e);
    
    /** Cf. le Patron interpreter, ici reflété par toString().
     * @return une forme lisible 
     */
    public abstract String toString();
}
