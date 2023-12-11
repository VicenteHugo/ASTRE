package view.parametrage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelParametre extends JPanel implements ActionListener{

	private JButton btnIntervenants;
	private JButton btnHeures;

	private FrameParametre frame;
	
	public PanelParametre(FrameParametre frame) {

		//Frame
		frame.setTitle("Astre - Paramètre (Acceuil)");
		this.frame = frame;


		//Création 
		this.btnIntervenants = new JButton("Catégorie intervenants");
		this.btnHeures       = new JButton("Catégorie heures");
		
		//Layout
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		//Positionnement
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);

		this.add(this.btnIntervenants, gbc);

		gbc.gridy++;
		this.add(this.btnHeures, gbc);


		//Action
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnIntervenants)
			this.frame.changePannel(new PanelIntPara());
	}
	
}
