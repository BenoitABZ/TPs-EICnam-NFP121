package question2;

/**
 * Client qui envoie une requête (Publisher)
 * 
 * @author benoit
 *
 */
public class ClientEmetteur {

	private Message message;

	public void setPublication(Serveur serveur) {
		serveur.setMessage(message);

	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
