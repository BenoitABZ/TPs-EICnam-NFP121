package question2;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JFrameDemo extends JFrame{
  private JPanel enHaut;
  private JPanel auCentre;
  private JButton boutonAjouter = new JButton("ajouter un smiley");

	public JFrameDemo(){
	  super("JFrameDemo");
	  JRootPane rootPane = this.getRootPane();
		enHaut = new JPanel();
    enHaut.add(boutonAjouter);
    auCentre = new JPanel();
    setLayout(new BorderLayout(5,5));
    add("North",enHaut);
    add("Center",auCentre);

    boutonAjouter.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        try{
          Smiley smiley = new Smiley();
          JButton jb = new JButton("smile");
          // jb.addActionListener(smiley);
          ParIntrospection.lierSourceEtListener( jb, smiley);
          // smiley.addMouseListener(new UnObservateurDeSouris(smiley));
          ParIntrospection.lierSourceEtListener( smiley, new UnObservateurDeSouris(smiley));
          JFrameDemo.this.enHaut.add(jb);
          JFrameDemo.this.auCentre.add(smiley);
          JFrameDemo.this.pack();
        }catch(Exception e){
          e.printStackTrace();
        }
      }
    });
    addWindowListener(
        new WindowAdapter(){
          public void windowClosing(WindowEvent we){
            System.exit(0);
          }
          public void windowClosed(WindowEvent we){
            System.exit(0);
          }
        }
      );
		pack();
		setVisible(true);
	}

	public static void main(String[] args){
	  new JFrameDemo();
	}

	private class UnObservateurDeSouris extends MouseAdapter implements MouseListener{
	  private Smiley smiley;
	  public UnObservateurDeSouris(Smiley smiley){ super(); this.smiley = smiley;}
    public void mouseClicked(MouseEvent e){
      smiley.toggleSmile();
    }
	}
	
	/** extrait de Client/Server programming with Java and Corba - Orfali, Robert - 2nd ed - John Wiley and sons
	 */
	private class Smiley extends Canvas  implements ActionListener{
  // Private data fields:

  private Color ourColor = Color.yellow;
  private boolean smile = true;

  // Construct a Smiley

  public Smiley(){
    setSize(250,250);
  }


  // public methods

  public synchronized void toggleSmile()
  {
    smile = !smile;
    repaint();
  }

 // called by event source
  public void actionPerformed(ActionEvent e)
  {
    toggleSmile();
  }
  // paint the Smiley face

  public void paint(Graphics g)
  {
    int cx = getSize().width/2; // center x
    int cy = getSize().height/2; // center y
    double r = Math.min(cx,cy)*0.97; // find smallest radius
    int r007 = (int)(r*0.07); int r007x2 = r007*2; // precompute
    int r020 = (int)(r*0.20); int r020x2 = r020*2; // misc.
    int r030 = (int)(r*0.30); int r030x2 = r030*2; // radius
    int r060 = (int)(r*0.60); int r060x2 = r060*2; // values
    int r100 = (int)(r*1.00); int r100x2 = r100*2;

    g.setColor(ourColor);
    g.fillOval(cx-r100,cy-r100,r100x2,r100x2); // face background

    g.setColor(Color.black); // black
    g.drawOval(cx-r100,cy-r100,r100x2,r100x2); // outline

    if (smile) g.drawArc(cx-r060,cy-r060,r060x2,r060x2,200,140); //smile
    else g.drawArc(cx-r060,cy+r030,r060x2,r060x2,020,140); // frown

    g.fillOval(cx-r030-r007,cy-r030-r007,r007x2,r007x2); // left eye
    g.fillOval(cx+r030-r007,cy-r030-r007,r007x2,r007x2); // right eye
    g.fillOval(cx-r007,cy+r020-r007,r007x2,r007x2); // nose
  }
}

	
}
