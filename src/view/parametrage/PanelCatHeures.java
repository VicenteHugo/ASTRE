package view.parametrage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelCatHeures extends JPanel implements ActionListener{

	private FrameParametre frame;
	private JTable tblGrilleDonnees;

	private JButton btnValider;
	private JButton btnRetour;

	public PanelCatHeures(FrameParametre frame) {

		// Frame
		frame.setTitle("Astre - Paramètre (Heures)");
		this.frame = frame;

		
		// Création des composants
		JScrollPane spGrilleDonnees;
		this.tblGrilleDonnees = new JTable(new GrilleCatHeures());
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


		// Actionnement
		this.btnRetour .addActionListener(this);
		this.btnValider.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.btnValider)
			; //On modifie les données dans la base
			
		this.frame.changePannel(new PanelParametre(this.frame));
	}

}
