package view.parametrage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
		this.btnValider.addActionListener((e)->{
			// On modifie les données dans la base
			this.frame.changePanel(new PanelParametre(this.frame));
		});
		this.btnRetour.addActionListener((e)->this.frame.changePanel(new PanelParametre(this.frame)));
		this.btnSupprimer.addActionListener((e)->System.out.println("Ligne " + this.tblGrilleDonnees.getSelectedRow() + " supprimé."));
		this.btnAjouter.addActionListener((e)->{
			JFrame f = new JFrame();
			f.add(new PanelAddCatInt(this.frame, f));
			f.setTitle("Ajout d'une catégorie");
			f.pack();
			f.setLocationRelativeTo(null);
			f.setAlwaysOnTop(true);
			f.setVisible(true);
		});
	}
}
