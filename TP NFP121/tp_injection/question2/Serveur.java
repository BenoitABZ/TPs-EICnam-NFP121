package question2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Exemple du patron publish/suscribe (variante car pas de topic sur lequel sosuscrire)
 * 
 * Basé sur un modèle client/serveur simplifié
 * 
 * Un serveur reçoit les requêtes d'un client X et notifie un autre client Y
 * 
 * @author benoit
 *
 */
public class Serveur {

	Queue<Message> file = new LinkedList<Message>();

	List<ClientRecepteur> recepteurs = new ArrayList<>();

	public void broadcast() {
		if (file.isEmpty()) {
			System.out.println("No messages from publishers to display");
		} else {
			while (!file.isEmpty()) {
				Message message = file.remove();

				for (ClientRecepteur recepteur : recepteurs) {

					recepteur.setMessage(message);
				}
			}
		}
	}
	
	public Queue<Message> getFile() {
		return file;
	}


	public void setFile(Queue<Message> file) {
		this.file = file;
	}


	public List<ClientRecepteur> getRecepteurs() {
		return recepteurs;
	}


	public void setRecepteurs(List<ClientRecepteur> recepteurs) {
		this.recepteurs = recepteurs;
	}


	public void setMessage(Message message) {
		file.add(message);
	}

	
	public void setRecepteur(ClientRecepteur recepteur) {
		recepteurs.add(recepteur);
	}
}
