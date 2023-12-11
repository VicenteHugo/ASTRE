package view.parametrage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelHeurePara extends JPanel implements ActionListener {

	private FrameParametre frame;
	private JTable tblGrilleDonnees;

	private JButton btnValider;
	private JButton btnRetour;

	private JButton btnAjouter;
	private JButton btnSupprimer;

	public PanelHeurePara(FrameParametre frame) {

		// Frame
		frame.setTitle("Astre - Paramètre (Heures)");
		frame.setMinimumSize(new Dimension(400,150));
		this.frame = frame;

		// Création des composants
		JScrollPane spGrilleDonnees;
		
		this.tblGrilleDonnees = new JTable(new GrilleCatHeures());
		this.tblGrilleDonnees.setFillsViewportHeight(true);

		this.btnValider   = new JButton("Valider");
		this.btnRetour    = new JButton("Annuler");
		this.btnAjouter   = new JButton("Ajouter");
		this.btnSupprimer = new JButton("Supprimer");

		// Layout
		this.setLayout(new BorderLayout());

		// Positionnement

		spGrilleDonnees = new JScrollPane(this.tblGrilleDonnees);
		spGrilleDonnees.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBtn.add(this.btnRetour);
		panelBtn.add(this.btnAjouter);
		panelBtn.add(this.btnSupprimer);
		panelBtn.add(this.btnValider);

		this.add(panelBtn, BorderLayout.SOUTH);
		this.add(spGrilleDonnees, BorderLayout.CENTER);

		// Taille colomne JTable
		this.tblGrilleDonnees.getColumnModel().getColumn(0).setMinWidth(80);
		this.tblGrilleDonnees.getColumnModel().getColumn(1).setMinWidth(50);


		// Actionnement
		this.btnRetour.addActionListener(this);
		this.btnValider.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.btnValider)
			; // On modifie les données dans la base

		this.frame.changePannel(new PanelParametre(this.frame));
	}

}
