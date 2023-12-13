package view.parametrage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import view.accueil.FrameAccueil;


public class PanelIntPara extends JPanel implements ActionListener {

	private FrameAccueil frame;
	private JTable tblGrilleDonnees;

	private JButton btnValider;
	private JButton btnRetour;

	private JButton btnAjouter;
	private JButton btnSupprimer;

	public PanelIntPara(FrameAccueil frame) {

		// Frame
		frame.setTitle("Astre - Paramètre (Intervenants)");
		frame.setMinimumSize(new Dimension(400, 150));
		this.frame = frame;

		// Création des composants
		JScrollPane spGrilleDonnees;

		this.tblGrilleDonnees = new JTable(new GrilleCatInt());
		this.tblGrilleDonnees.setFillsViewportHeight(true);

		this.btnValider = new JButton("Valider");
		this.btnRetour = new JButton("Annuler");
		this.btnAjouter = new JButton("Ajouter");
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
		this.tblGrilleDonnees.getColumnModel().getColumn(1).setMinWidth(30);
		this.tblGrilleDonnees.getColumnModel().getColumn(2).setMinWidth(50);
		this.tblGrilleDonnees.getColumnModel().getColumn(3).setMinWidth(50);

		// Actionnement
		this.btnRetour   .addActionListener(this);
		this.btnValider  .addActionListener(this);
		this.btnSupprimer.addActionListener(this);
		this.btnAjouter  .addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.btnValider) {
			; // On modifie les données dans la base
			this.frame.changePanel(new PanelParametre(this.frame));
		}

		if (e.getSource() == this.btnRetour) {
			this.frame.changePanel(new PanelParametre(this.frame));
		}

		if (e.getSource() == this.btnSupprimer) {
			System.out.println("Ligne " + this.tblGrilleDonnees.getSelectedRow() + " supprimé.");
		}

		if (e.getSource() == this.btnAjouter) {
			JFrame frame = new JFrame();
			frame.add(new PanelAddCatInt(this.frame, frame));
			frame.setTitle("Ajout d'une catégorie");
			frame.pack();
			frame.setAlwaysOnTop(true);
			frame.setVisible(true);
		}

	}

}