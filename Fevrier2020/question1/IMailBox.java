package question1;

import java.util.Iterator;
import java.util.List;
import java.io.Serializable;
/**
 * Le patron MailBox.
 */
public interface IMailBox extends Iterable<String>, Serializable{

    /** Inscription d'un destinataire. 
     * L’inscription est obligatoire afin de recevoir des messages.
     * Attention Pas d'homonyme possible.
     * @param dest le destinataire le nom de l’utilisateur
     * @throws Exception si le destinataire est déjà inscrit ou un homonyme est en place
     */
    public void add(String dest) throws Exception;

    /** Dépôt d'un Message pour un destinataire.
     * @param message le message envoyé.
     * @param dest le destinataire du message
     * @throws Exception si le destinataire est inconnu
     */
    public void send(String message, String dest) throws Exception;

    /** Dépôt d'un Message pour plusieurs destinataires.
     * @param message le message envoyé.
     * @param dest les destinataires du message.
     * @throws Exception si un des destinataires (au moins) est inconnu.
     */
    public void send(String message, String[] dest) throws Exception;

    /** Relevé des messages pour ce destinataire.
     * @param dest le nom du destinataire.
     * @return la liste contenant les nouveaux messages, depuis le dernier relevé.
     * @throws Exception si le nom du destinataire est inconnu
     */
    public List<String> read(String dest) throws Exception;

    /** Obtention de la liste des destinataires.
     * @return un itérateur, parcours des inscrits
     */
    public Iterator<String> iterator();

    /** Relevé de tous les messages pour ce destinataire.
     * @param dest le nom du destinataire.
     * @return la liste contenant tous les messages, lus ou non.
     * @throws Exception si le nom du destinataire est inconnu
     */
    public List<String> getAllMessages(String dest) throws Exception;
    
    /** Relevé de tous les messages non lus pour ce destinataire.
     * @param dest le nom du destinataire.
     * @return la liste contenant tous les messages, non encorelus.
     * @throws Exception si le nom du destinataire est inconnu
     */
    public List<String> getAllUnreadMessages(String dest) throws Exception;
}
