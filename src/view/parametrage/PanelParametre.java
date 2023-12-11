package view.parametrage;

import javax.swing.*;

import view.accueil.FrameAccueil;
import view.accueil.PanelAccueil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelParametre extends JPanel implements ActionListener{

	private JButton btnIntervenants;
	private JButton btnHeures;
	private JButton btnAcceuil;

	private FrameAccueil frame;
	
	public PanelParametre(FrameAccueil frame) {

		//Frame
		frame.setTitle("Astre - Paramètre (Accueil)");
		frame.setMinimumSize(new Dimension(400, 400));
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
			this.frame.changePanel(new PanelIntPara(this.frame));

		if (e.getSource() == this.btnHeures)
			this.frame.changePanel(new PanelHeurePara(this.frame));

		if (e.getSource() == this.btnAcceuil)
			this.frame.changePanel(new PanelAccueil(this.frame));
	}
	
}
