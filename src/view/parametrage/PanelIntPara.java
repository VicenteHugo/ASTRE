package view.parametrage;

import javax.swing.*;
import java.awt.*;

public class PanelIntPara extends JPanel {

	private FrameParametre frame;
	private JTable tblGrilleDonnees;

	private JButton btnValider;
	private JButton btnRetour;

	public PanelIntPara(FrameParametre frame) 
	{

		// Frame
		frame.setTitle("Astre - Paramètre (Intervenants)");
		this.frame = frame;

		
		// Création des composants
		JScrollPane spGrilleDonnees;
		this.tblGrilleDonnees = new JTable(new GrilleCatInt());
		this.tblGrilleDonnees.setFillsViewportHeight(true);

		this.btnValider = new JButton("Valider");
		this.btnRetour  = new JButton("Annuler");


		// Layout
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// Positionnement
		gbc.gridx = 0;
		gbc.gridy = 0;

		spGrilleDonnees = new JScrollPane(this.tblGrilleDonnees);
		this.add(spGrilleDonnees, gbc);
		
		JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBtn.add(this.btnRetour , gbc);
		panelBtn.add(this.btnValider, gbc);

		gbc.gridy++;
		this.add(panelBtn, gbc);


		//
		
	}

}
