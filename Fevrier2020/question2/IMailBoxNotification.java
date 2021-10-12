
package question2;

import question1.*;

public interface IMailBoxNotification extends IMailBox{

  /** Inscription d'un observateur(Listener) auprès d'un destinataire.
   * Rappel: pas d'homonyme possible. 
   * Plusieurs observateurs sont possibles par destinataire.
   * @param listener l'observateur, est notifié à chaque réception de courrier
   * @param dest  le nom de l’utilisateur
   * @throws Exception si le destinataire n'est pas inscrit
   */
  public void addMailBoxListener(String dest, MailBoxListener listener) throws Exception;
}