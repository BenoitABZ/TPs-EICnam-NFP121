package question2;

/**
 * Client qui reçoit une requête (suscriber)
 * 
 * @author benoit
 *
 */
public class ClientRecepteur {

	private Message message;

	public void setRecepteur(Serveur serveur) {
		serveur.setRecepteur(this);
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
