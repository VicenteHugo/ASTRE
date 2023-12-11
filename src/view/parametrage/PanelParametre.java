package view.parametrage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelParametre extends JPanel implements ActionListener{

	private JButton btnIntervenants;
	private JButton btnHeures;
	private JButton btnAcceuil;

	private FrameParametre frame;
	
	public PanelParametre(FrameParametre frame) {

		//Frame
		frame.setTitle("Astre - Paramètre (Acceuil)");
		this.frame = frame;


		//Création 
		this.btnIntervenants = new JButton("Catégorie intervenants");
		this.btnHeures       = new JButton("Catégorie heures");
		this.btnAcceuil      = new JButton("Accueil");
		
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

		gbc.gridy++;
		this.add(this.btnAcceuil, gbc);


		//Action
		this.btnIntervenants.addActionListener(this);
		this.btnHeures      .addActionListener(this);
		this.btnAcceuil     .addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnIntervenants)
			this.frame.changePannel(new PanelIntPara(this.frame));

		if (e.getSource() == this.btnHeures)
			this.frame.changePannel(new PanelCatHeures(this.frame));

		if (e.getSource() == this.btnAcceuil)
			this.frame.dispose();
	}
	
}
