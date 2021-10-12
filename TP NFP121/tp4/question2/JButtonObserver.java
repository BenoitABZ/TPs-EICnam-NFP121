package question2;

import java.awt.event.*;
//import java.awt.event. // � compl�ter
import java.awt.TextArea;

/**
 * JbuttonObserver, comme son nom l'indique est un observateur et implemente notamment la methode actionPerformed (ActionEvent e --> source de l'evenement) qui sera appelé en cas d'evenement sur un des boutons ecoutes.
 * 
 * actionPerformed() modifie ici le contenu en fonction de l'instance observatrice concernee et le bouton ayant genere l'evenement.
 * 
 * @author benoit
 * @version 0.0.1
 */
public class JButtonObserver implements ActionListener { 

	private String nom;
	private TextArea contenu;

	/**
	 * Constructeur d'objets de classe JButtonObserver
	 * 
	 * @param nom     le nom du bouton, jbo1, jbo2, jbo3, jmo1, jmo2, jmo3
	 * @param contenu la zone de texte de l'applette
	 */
	public JButtonObserver(String nom, TextArea contenu) {
		this.nom = nom;
		this.contenu = contenu;
	}

	/**
	 * affichage d'un message dans la zone de texte ce message est de la forme
	 * observateur this.nom : clic du bouton nom_du_bouton exemple : observateur
	 * jbo1 : clic du bouton A, voir la m�thode getActionCommand()
	 * 
	 * @param � compl�ter
	 */
	
	//...

	@Override
	public void actionPerformed(ActionEvent e) {

		String message = "observateur" + nom;

		if (e.getActionCommand() == "boutonA") {

			message = message + " clic du boutonA";

		}

		if (e.getActionCommand() == "boutonB") {

			message = message + " clic du bouton B";

		}

		if (e.getActionCommand() == "boutonC") {

			message = message + " clic du bouton C";

		}

		contenu.append(message + "\n");

	}

}
