package view.module;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Controleur;
import view.Intervenant.PanelAddIntervenant;
import view.accueil.FrameAccueil;
import view.previsionnel.PanelPrevi;

public class PanelRessources extends JPanel {

	private FrameAccueil frame;

	private JButton btnAjouter;
	private JButton btnSupprimer;
	private JButton btnEnregistrer;
	private JButton btnAnnuler;

	private JPanel panelGauche;
	private JPanel panelDroit;

	private JTable tblGrilleDonnees;

	public PanelRessources(FrameAccueil frame) {

		// Frame
		this.frame = frame;
		frame.setTitle("Astre - Prévisionnel - Module");
		frame.setMinimumSize(new Dimension(900, 500));

		// Composants
		this.btnAjouter = new JButton("Ajouter");
		this.btnSupprimer = new JButton("Supprimer");
		this.btnEnregistrer = new JButton("Enregistrer");
		this.btnAnnuler = new JButton("Annuler");

		this.panelGauche = new JPanel();
		this.panelDroit = new JPanel();

		// Layout
		this.setLayout(new GridLayout(1, 2, 10, 10));
		this.panelGauche.setLayout(new GridLayout(8, 1));
		this.panelDroit.setLayout(new GridLayout(2, 1));

		// Positionnement

		JPanel panelInfo = new JPanel();
		panelInfo.setLayout(new GridLayout(2, 5));
		panelInfo.add(new JLabel("type module"));
		panelInfo.add(new JLabel("semestre"));
		panelInfo.add(new JLabel("code"));
		panelInfo.add(new JLabel("libellé long"));
		panelInfo.add(new JLabel("libellé court"));

		panelInfo.add(new JTextField("Ressources"));
		panelInfo.add(new JTextField("S1"));
		panelInfo.add(new JTextField("R1.01"));
		panelInfo.add(new JTextField("Initiation au développement"));
		panelInfo.add(new JTextField("Init dev"));
		this.panelGauche.add(panelInfo);

		JPanel panelNombre = new JPanel();
		panelNombre.setLayout(new GridLayout(2, 3));
		panelNombre.add(new JLabel("nb Etd"));
		panelNombre.add(new JLabel("nb gp TD"));
		panelNombre.add(new JLabel("nb gp TP"));

		panelNombre.add(new JTextField("85"));
		panelNombre.add(new JTextField("4"));
		panelNombre.add(new JTextField("7"));
		this.panelGauche.add(panelNombre);

		this.panelGauche.add(new JLabel("PN local(nb h tot/etd)"));

		JPanel panelPnLocal = new JPanel();
		panelPnLocal.setLayout(new GridLayout(3, 5));
		panelPnLocal.add(new JLabel());
		panelPnLocal.add(new JLabel("CM"));
		panelPnLocal.add(new JLabel("TD"));
		panelPnLocal.add(new JLabel("TP"));
		panelPnLocal.add(new JLabel("∑"));

		panelPnLocal.add(new JLabel());
		panelPnLocal.add(new JTextField("6"));
		panelPnLocal.add(new JTextField("65"));
		panelPnLocal.add(new JTextField("28"));
		panelPnLocal.add(new JTextField("99"));

		panelPnLocal.add(new JLabel("Total (eqtd) promo"));
		panelPnLocal.add(new JTextField("9"));
		panelPnLocal.add(new JTextField("260"));
		panelPnLocal.add(new JTextField("196"));
		panelPnLocal.add(new JTextField("465"));

		this.panelGauche.add(panelPnLocal);
		this.panelGauche.add(new JLabel("Répartition"));

		JPanel panelRépartition = new JPanel();
		panelRépartition.setLayout(new GridLayout(3, 5));
		panelRépartition.add(new JLabel());
		panelRépartition.add(new JLabel("CM"));
		panelRépartition.add(new JLabel());
		panelRépartition.add(new JLabel("TD"));
		panelRépartition.add(new JLabel());
		panelRépartition.add(new JLabel("TP"));

		panelRépartition.add(new JLabel("nb Sem"));
		panelRépartition.add(new JLabel("nb h/sem"));
		panelRépartition.add(new JLabel("nb Sem"));
		panelRépartition.add(new JLabel("nb h/sem"));
		panelRépartition.add(new JLabel("nb Sem"));
		panelRépartition.add(new JLabel("nb h/sem"));

		panelRépartition.add(new JTextField("6"));
		panelRépartition.add(new JTextField("1"));
		panelRépartition.add(new JTextField("14"));
		panelRépartition.add(new JTextField("4"));
		panelRépartition.add(new JTextField("14"));
		panelRépartition.add(new JTextField("2"));

		this.panelGauche.add(panelRépartition);

		JPanel panelRépartition2 = new JPanel();
		panelRépartition2.setLayout(new GridLayout(4, 6));
		panelRépartition2.add(new JLabel());
		panelRépartition2.add(new JLabel("CM"));
		panelRépartition2.add(new JLabel("TD"));
		panelRépartition2.add(new JLabel("TP"));
		panelRépartition2.add(new JLabel("heure ponctuelle"));
		panelRépartition2.add(new JLabel("∑"));

		panelRépartition2.add(new JLabel());
		panelRépartition2.add(new JTextField("6"));
		panelRépartition2.add(new JTextField("56"));
		panelRépartition2.add(new JTextField("28"));
		panelRépartition2.add(new JTextField("9"));
		panelRépartition2.add(new JTextField("99"));

		panelRépartition2.add(new JLabel("Total promo(eqtd)"));
		panelRépartition2.add(new JTextField("9"));
		panelRépartition2.add(new JTextField("224"));
		panelRépartition2.add(new JTextField("196"));
		panelRépartition2.add(new JTextField("36"));
		panelRépartition2.add(new JTextField("465"));

		panelRépartition2.add(new JLabel("Total affecté(eqtd)"));
		panelRépartition2.add(new JTextField("9"));
		panelRépartition2.add(new JTextField("224"));
		panelRépartition2.add(new JTextField("168"));
		panelRépartition2.add(new JTextField("36"));
		panelRépartition2.add(new JTextField("437"));

		this.panelGauche.add(panelRépartition2);

		this.tblGrilleDonnees = new JTable(new GrilleRessources());
		this.tblGrilleDonnees.setFillsViewportHeight(true);

		JScrollPane spGrilleDonnees = new JScrollPane(this.tblGrilleDonnees);
		this.panelDroit.add(spGrilleDonnees);

		JPanel panelBouton = new JPanel();
		panelBouton.setLayout(new GridLayout(2, 2));
		JPanel panelBouton1 = new JPanel();
		JPanel panelBouton2 = new JPanel();
		JPanel panelCheckBox = new JPanel();

		panelBouton1.add(btnAjouter);
		panelBouton1.add(btnSupprimer);

		panelBouton2.add(btnEnregistrer);
		panelBouton2.add(btnAnnuler);

		JCheckBox chValidation = new JCheckBox("Validation");
		panelCheckBox.add(chValidation);

		panelBouton.add(panelBouton1);
		panelBouton.add(panelCheckBox);
		panelBouton.add(new JPanel());
		panelBouton.add(panelBouton2);

		this.panelDroit.add(panelBouton);

		this.add(panelGauche);
		this.add(panelDroit);

		this.btnAnnuler.addActionListener((e) -> this.frame.changePanel(new PanelPrevi(this.frame)));
        this.btnAjouter.addActionListener((e)->{
			JFrame f = new JFrame();
			f.add(new PanelAddRessourceIntervenant(this,this.frame, f));
			f.setTitle("Affecter un Intervenant");
			f.pack();
			f.setLocationRelativeTo(null);
			f.setAlwaysOnTop(true);
			f.setVisible(true);
		});

		this.btnEnregistrer.addActionListener((e) ->this.maj()       );
		this.btnSupprimer.addActionListener(  (e) ->this.supprimer());


	}
	private void supprimer() {

		int ind = this.tblGrilleDonnees.getSelectedRow();
		System.out.println(ind);
		Controleur.getControleur().supprimerIntervenant(ind);
		if (ind >= 0)
			this.tblGrilleDonnees.setRowSelectionInterval(ind, ind);
		this.maj();
	}

    public void maj() {
		this.tblGrilleDonnees.setModel(new GrilleRessources()); 
	}
}