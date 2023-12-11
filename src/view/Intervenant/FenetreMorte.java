package view.Intervenant;
import javax.swing.JFrame;

public class FenetreMorte extends JFrame
{

	public FenetreMorte()
	{
		this.setTitle   ("Intervenants");
		this.setSize    (1000, 400);
		this.setLocation(40,40);
		PanelIntervenants panInt = new PanelIntervenants();
		this.add(panInt);
		this.setVisible(true);
	}

	public static void main(String [] args)
	{
		new FenetreMorte();
	}
}
