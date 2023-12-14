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


public class PanelIntPara extends JPanel {

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
		f.add(new PanelAddCatInt(this, f));
		f.setTitle("Ajout d'une catégorie");
		f.pack();
		f.setLocationRelativeTo(null);
		f.setAlwaysOnTop(true);
		f.setVisible(true);
	}

	private void supprimer() {

		int ind = this.tblGrilleDonnees.getSelectedRow();
		System.out.println("hey " + ind);
		Controleur.getControleur().supprimerCategorieIntervenants(ind);

		System.out.println("Indice : " + ind);
		
		this.maj();

		int max = this.tblGrilleDonnees.getRowCount();

		if (ind >= 0 && ind < max)
			this.tblGrilleDonnees.setRowSelectionInterval(ind, ind);

		if (ind >= max && max != 0)
			this.tblGrilleDonnees.setRowSelectionInterval(max -1 , max -1);

	}



	public void maj() {
		this.tblGrilleDonnees.setModel(new GrilleCatInt()); 
	}

}
