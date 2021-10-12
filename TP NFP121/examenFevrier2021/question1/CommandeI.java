package question1;

/**
 * @param <E> l'entité transmise
 */
public interface CommandeI<E>{
    
    
  /* La méthode exécutée lors d'une publication.
   * @param e l'entité, l'événement transmis
   * @return true ou false en fonction du contexte, et du résultat attendu de l'exécution
   */
  public boolean executer(E e);
  

  
}
