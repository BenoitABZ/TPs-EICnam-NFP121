package question4;

import question1.Contributeur;
import question1.GroupeDeContributeurs;
import question1.SoldeDebiteurException;
import question2.*;
import question3.*;
import static question2.Main.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.jdom.*;
import org.jdom.output.*;
import java.io.ByteArrayOutputStream;

/**
 * IHM permettant la mise en oeuvre des livrables des questions 1,2 et 3
 * 
 * @author benoit
 *
 */
public class IHM extends JFrame {

	private JTextArea resultat = new JTextArea("", 7, 60);
	private JButton debiter = new JButton("d�biter");
	private JButton crediter = new JButton("cr�diter");
	private JTextField somme = new JTextField(4);

	private GroupeDeContributeurs g;

	public IHM() {
		this.setTitle("Cotisant");
		Container container = this.getContentPane();
		somme.setText("40");
		container.setLayout(new BorderLayout());

		container.add(resultat, BorderLayout.NORTH);
		JPanel p = new JPanel(new FlowLayout());
		p.add(somme);
		p.add(debiter);
		p.add(crediter);
		container.add(p, BorderLayout.SOUTH);

		g = new GroupeDeContributeurs("g");
		g.ajouter(new Contributeur("g_a", 100));
		g.ajouter(new Contributeur("g_b", 50));
		g.ajouter(new Contributeur("g_c", 150));
		GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
		g1.ajouter(new Contributeur("g1_a1", 70));
		g1.ajouter(new Contributeur("g1_b1", 200));
		g.ajouter(g1);

		try {
			resultat.setText(Main.arbreXML(g)); // actualiser();
		} catch (Exception e) {
		}

		debiter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// une nouvelle tentative de débit n'a aucun effet sur l'affichage, la transaction ayant échouée
				AbstractTransaction t = new TransactionDebit(g);

				try {
					// on débite l'ensemble des contributeurs du groupe
					t.debit(new Integer(somme.getText()));
				} catch (NumberFormatException e1) {

					e1.printStackTrace();
				} catch (SoldeDebiteurException e1) {

					e1.printStackTrace();
				}

				try {
					// regénération de l'arbre XML
					resultat.setText(Main.arbreXML(g));
				} catch (Exception e1) {

					e1.printStackTrace();
				}

			}

		});
		crediter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// on crédite l'ensemble des contributeurs du groupe
				g.credit(new Integer(somme.getText()));

				try {
					// regénération de l'arbre XML
					resultat.setText(Main.arbreXML(g));
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		});

		this.pack();
		this.setVisible(true);
	}

	public static void main() {
		new IHM();
	}

}

/*
 * Bibliographie: cours NFP121, doc Oracle
 * 
 * Conclusion: mise en oeuvre d'un assemblage de pattern afin de réaliser une application.
 * 
 * Le squelette : pattern composite avec implémentaion d'un contributeur et d'un groupe de contributeurq.
 * 
 * Un débit/crédit ou consultation de solde s'effectue sur tous les élément du composite (contributeurs et composites etc..)
 * 
 * Visite de cette arborescence afin d'y effectuer des controles grace au pattern visitor (SansDoublon, DebitMaximal et CompositeValide)
 * 
 * DebitMaximal peut nous permettre de prévenir tout problème lié à l'impact partiel que peut avoir un débit sur un groupe de contributeurs mais nécéssite une condition.
 * 
 * Solution : la transaction!
 * 
 * Le pattern template method nous permet le choix de la solution transactionnelle retenue. 
 * 
 * Dans notre cas il s'agit de la RAM (pile JAVA) et les méthodes beginTransaction() et rollback() ont été implémenté graceà un assemblage du pattern memento et visitor.
 * 
 * Une ébauche du patron Command nous a permis d'externaliser la fonction de débit afin d'y inclure les concepts transactionnelle.
 * 
 * Enfin une IHM a permis la mise en oeuvre de l'application.
 * 
 * Nous avons pu definir l'architecture de cette application en quelques mots illustrant la forces du design pattern.
 * 
 */
