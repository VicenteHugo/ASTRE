package view.Etat;

import javax.swing.JFrame;

public class FrameEtat extends JFrame {
        
    public FrameEtat() {
        this.setTitle   ("Etats");
		this.setSize    (1000, 400);
		this.setLocation(40,40);
		PanelEtat panEt = new PanelEtat(this);
		this.add(panEt);
		this.setVisible(true);
	}

	public static void main(String [] args)
	{
		new FrameEtat();
	}
}