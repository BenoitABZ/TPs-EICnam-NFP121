package question2;

import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.TextArea;

/**
 * JMouseObserver, comme son nom l'indique est un observateur et implemente des methodes de mouse"Action" (MouseEvent e --> source de l'evenement lié à la souris) qui sera appelé en cas d'evenement lié à la souris sur un des boutons ecoutes.
 * 
 * Implementation de mouseEntered() pour répondre au cahier des charges. Cette méthode modifie le contenu en fonction lorsque l'instance observatrice generera l'evenement de survole avec la souris.
 * 
 * @author benoit
 * @version 0.0.1
 */
public class JMouseObserver implements MouseListener {

	private String nom;
	private TextArea contenu;

	/**
	 * Constructeur d'objets de classe JButtonObserver
	 */
	public JMouseObserver(String nom, TextArea contenu) {
		this.nom = nom;
		this.contenu = contenu;
	}

	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * affichage d'un message dans la zone de texte ce message est de la forme
	 * observateur this.nom : souris entrée en (X,Y) exemple : observateur jmo1 :
	 * souris entrée en (15,20)
	 * 
	 * utilisation des methodes getX() et get(Y) pour recuperer la position du curseur
	 * 
	 * @param
	 */
	public void mouseEntered(MouseEvent e) {
		
		String message = "observateur" + nom + " : souris entrée en (" + e.getX() + "," + e.getY() + ")";

		contenu.append(message + "\n");
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

}
