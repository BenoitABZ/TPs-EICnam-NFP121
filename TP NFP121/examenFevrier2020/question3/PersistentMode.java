package question3;

import java.io.Serializable;

import question1.*;

public abstract class PersistentMode implements java.io.Serializable{

  /** Sauvegarde d'une instance de boîte aux lettres.
   * @param mb la boîte mail
   * @param fileName le nom du fichier
   * @throws Exception levée d'une exception pour toute anomalie
   */
  public abstract void writeMailBox(IMailBox mb, String fileName) throws Exception;
  
  /** restitution d'une instance de boîte aux lettres.
   * @param fileName le nom du fichier
   * @throws Exception levée d'une exception pour toute anomalie
   * @return la boîte mail
   */
  public abstract IMailBox readMailBox(String fileName) throws Exception;

}
