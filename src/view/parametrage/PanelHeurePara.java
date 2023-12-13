package view.parametrage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controleur.Controleur;
import view.accueil.FrameAccueil;


public class PanelHeurePara extends JPanel {

	private FrameAccueil frame;
	private JTable tblGrilleDonnees;

	private JButton btnValider;
	private JButton btnRetour;

	private JButton btnAjouter;
	private JButton btnSupprimer;

	public PanelHeurePara(FrameAccueil frame) {

		// Frame
		frame.setTitle("Astre - Paramètre (Heures)");
		frame.setMinimumSize(new Dimension(400, 150));
		this.frame = frame;

		// Création des composants
		JScrollPane spGrilleDonnees;

		this.tblGrilleDonnees = new JTable(new GrilleCatHeures());
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

		// Actionnement
		this.btnValider.addActionListener((e) -> this.valider());
		this.btnRetour.addActionListener((e) -> this.annuler());
		this.btnSupprimer.addActionListener((e) -> this.supprimer());
		this.btnAjouter.addActionListener((e) -> this.ajouter());
	}
	
	private void annuler() {
		this.frame.changePanel(new PanelParametre(this.frame));
		Controleur.getControleur().annuler();
	}
	
	private void valider() {
		this.frame.changePanel(new PanelParametre(this.frame));
		Controleur.getControleur().enregistrer();
	}

	private void ajouter() {
		JFrame f = new JFrame();
		f.add(new PanelAddCatHeures(this, f));
		f.setTitle("Ajout d'une catégorie");
		f.pack();
		f.setLocationRelativeTo(null);
		f.setAlwaysOnTop(true);
		f.setVisible(true);
	}

	private void supprimer() {

		int ind = this.tblGrilleDonnees.getSelectedRow();
		System.out.println(ind);
		Controleur.getControleur().supprimerCategorieHeure(ind);
		if (ind >= 0)
			this.tblGrilleDonnees.setRowSelectionInterval(ind, ind);
		
		this.maj();
	}



	public void maj() {
		this.tblGrilleDonnees.setModel(new GrilleCatHeures()); 
	}
}
