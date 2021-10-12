package question3;


import java.util.List;
import java.util.ArrayList;

/** DAO/CRUD : un "DAO" (Data Access Object ) 
 *  qui propose les 4 opérations élémentaires Create Retrieve Update Delete (+ findAll)
 *  @param <T> pour Le type souhaité
 *  @param <ID> pour l'identifiant, en général unique, par instance de T
 */
public interface DAO<T,ID>{

   /** Création de l'instance t en BDD.
    * @param t l'instance de T
    * @throws Exception si l'opération est en Ã©chec
    */
   public void create(T t) throws Exception;
   
   /** Permet de retrouver une instance de T à  partir de son identifiant.
    * @param id l'identifiant associé à  t
    * @return l'instance associée
    * @throws Exception si l'opération est en échec
    */
   public T retrieve(ID id) throws Exception;
   
   /** Obtention de la liste des éléments.
    * @return la liste ordonnée selon l'ordre des enregistrements (des create)
    * @throws Exception si l'opération est en échec
    */
   public List<T> findAll() throws Exception;
   
   /** Mise à  jour de t dans la base.
    * @param t instance de T, existante
    * @throws Exception si l'opération est en échec
    */
   public void update(T t) throws Exception;
   
   /** Retrait de t dans la base.
    * @param id l'identifiant associé à  t
    * @throws Exception si l'opération est en échec
    */   
   public void delete(ID id) throws Exception;
   
     /** Critère de sélection d'éléments de type T. */
   public interface Criteria<T>{
	   public boolean isTrue(T t);
   }
   
   /** Filtrage des éléments selon le critère transmis.
    * 
    * @param criteria le critère de sélection
    * @return la liste des éléments satisfaisant le critère
    */
   public List<T> filter(Criteria<T> criteria) throws Exception;
   
   /** Une classe interne et static pour une implémentation toute prête de filter.
    */
   public static class DAOUtils{
     public  static <T,ID> List<T> filter(DAO<T,ID> dao, Criteria<T> criteria) throws Exception{
		   List<T> list = new ArrayList<T>();
		   for(T t: dao.findAll()){
			   if(criteria.isTrue(t))
				   list.add(t);
		   }   
		   return list;
		 }
	}
   
}


