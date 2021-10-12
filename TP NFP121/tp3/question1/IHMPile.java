package question1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

// pour le comportement attendu depuis votre r�pertoire
// ex�cuter cette commande tp3>appletviewer tp3.html
public class IHMPile extends JFrame implements ActionListener {
	private JTextField donnee = new JTextField(6);
	private JTextField sommet = new JTextField(6);
	private JLabel contenu = new JLabel("[]");

	private Pile p;

	public IHMPile() {
		super("IHM Pile");
		JButton boutonEmpiler = new JButton("empiler");
		JButton boutonDepiler = new JButton("depiler");

		JPanel enHaut = new JPanel();
		enHaut.add(donnee);
		enHaut.add(boutonEmpiler);
		enHaut.add(boutonDepiler);
		enHaut.add(sommet);
		setLayout(new BorderLayout(5, 5));
		add("North", enHaut);
		add("Center", contenu);
		enHaut.setBackground(Color.red);
		setLocation(100, 100);
		pack();
		setVisible(true);
		boutonEmpiler.addActionListener(this);
		boutonDepiler.addActionListener(this);

		p = new Pile(5);

	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("empiler")) {

			// � compl�ter
			// pour le comportement attendu depuis votre r�pertoire
			// ex�cuter cette commande tp3>appletviewer tp3.html

			// en cas d'exception
			// contenu.setText( /* � compl�ter */"" + " estPleine !");

			String input = donnee.getText();

			try {

				p.empiler(input);

				contenu.setText(p.toString());
				
				sommet.setText(input);

			} catch (PilePleineException ppe) {

				contenu.setText(" estPleine !");
			}

		} else {

			// � compl�ter
			// en cas d'exception
			// contenu.setText( /* � compl�ter */"" + " estVide !");

			try {
				p.depiler();

				contenu.setText(p.toString());

			} catch (PileVideException ppe) {

				contenu.setText(" estVide !");
			}
		}
	}

	public static void main(String[] args) {
		new IHMPile();
	}
}
