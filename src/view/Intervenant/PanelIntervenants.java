package view.Intervenant;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import view.JButtonStyle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controleur.Controleur;
import view.accueil.FrameAccueil;
import view.accueil.PanelAccueil;

public class PanelIntervenants extends JPanel {
	private JLabel lblListe;
	private JButtonStyle btnAjout;
	private JButtonStyle btnSupr;
	private JButtonStyle btnEnregistr;
	private JButtonStyle btnAnnuler;
	private JTable tblGrilleDonnees;

	private FrameAccueil frame;

	private JFrame f;

	public PanelIntervenants(FrameAccueil frame) {

		this.frame = frame;
		frame.setTitle("Astre - Intervenants (Accueil)");
		frame.setMinimumSize(new Dimension(900, 400));

		// Création des composants
		this.lblListe = new JLabel("Liste Intervenant");
		this.btnAjout     = new JButtonStyle("Ajouter");
		this.btnSupr      = new JButtonStyle("Supprimer");
		this.btnEnregistr = new JButtonStyle("Enregistrer");
		this.btnAnnuler   = new JButtonStyle("Annuler");

		this.tblGrilleDonnees = new JTable(new GrilleInt());
		this.tblGrilleDonnees.setFillsViewportHeight(true);

		JScrollPane spGrilleDonnees = new JScrollPane(this.tblGrilleDonnees);
		JPanel panelTable = new JPanel();
		JPanel panelBtn = new JPanel();
		JPanel panelModif = new JPanel();
		JPanel panelBas = new JPanel();

		// Layouts
		this.setLayout(new BorderLayout());
		panelTable.setLayout(new BorderLayout());
		panelBtn.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelModif.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelBas.setLayout(new GridLayout(2, 2));

		// Positionnement des composants

		panelTable.add(this.lblListe, BorderLayout.NORTH);
		panelTable.add(spGrilleDonnees, BorderLayout.CENTER);

		panelModif.add(this.btnAjout);
		panelModif.add(this.btnSupr);
		panelBtn.add(this.btnEnregistr);
		panelBtn.add(this.btnAnnuler);

		panelBas.add(new JPanel());
		panelBas.add(panelModif);
		panelBas.add(panelBtn);
		panelBas.add(new JPanel());

		this.add(panelTable, BorderLayout.CENTER);
		this.add(panelBas, BorderLayout.SOUTH);
		this.setVisible(true);

		// Action
		this.btnAnnuler.addActionListener((e) -> this.annuler());
		this.btnAjout.addActionListener((e) -> {
			if(f != null) return;
			f = new JFrame();
			f.add(new PanelAddIntervenant(this, this.frame, f));
			f.setTitle("Ajout d'un Intervenant");
			f.pack();
			f.setResizable(false);
			f.setLocationRelativeTo(null);
			f.setAlwaysOnTop(true);
			f.setVisible(true);
		});
		this.btnSupr.addActionListener((e) -> this.supprimer());
		this.btnEnregistr.addActionListener((e) -> this.valider());

	}

	private void valider() {
		if (this.tblGrilleDonnees.isEditing())
			this.tblGrilleDonnees.getCellEditor().stopCellEditing();
		this.frame.changePanel(new PanelAccueil(this.frame));
		Controleur.getControleur().enregistrer();
	}

	private void annuler() {
		this.frame.changePanel(new PanelAccueil(this.frame));
		if(f != null)
			this.f.dispose();
			
		Controleur.getControleur().annuler();
	}

	private void supprimer() {

		int ind = this.tblGrilleDonnees.getSelectedRow();
		if(ind < 0){
			return ;
		}
		if (!Controleur.getControleur().supprimerIntervenant(ind)) {
			JOptionPane.showMessageDialog(this, "L'intervenant à encore des affectations", "Erreur", JOptionPane.ERROR_MESSAGE);
			return ;
		}
		
		if (ind >= 0)
			this.tblGrilleDonnees.setRowSelectionInterval(ind, ind);

		this.maj();
	}

	public void maj() {
		this.tblGrilleDonnees.setModel(new GrilleInt());
	}

	public void annulerAjout(){
		this.f.dispose();
		this.f = null;
	}
}
