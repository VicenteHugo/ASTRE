package view.module;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;

import javax.swing.*;

import view.JLabelModule;
import view.JTextFieldNumber;
import view.accueil.FrameAccueil;
import view.previsionnel.PanelPrevi;
import controleur.*;
import model.Semestres;
import model.modules.Module;

public class PanelSAE extends JPanel implements ActionListener{

    // Modules
    private JTextField txtCodeMod;
    private JTextField txtLibLongMod;
    private JTextField txtLibCourtMod;
    private JTextField txtTypeMod;
	private JCheckBox  cbValide;

    // Semestres
    private JTextFieldNumber txtSem;
    private JTextFieldNumber txtNbEtd;
    private JTextFieldNumber txtNbGpTd;
	private JTextFieldNumber txtNbGpTp;
	
	//Heures PN

	private JTextFieldNumber txtHeureEtdSaePN;
	private JTextFieldNumber txtHeureEtdTutPN;
	private JTextFieldNumber txtHeureEtdTotPN ;


	//Repartition

	private JTextFieldNumber txtEtdSaePromRep;
	private JTextFieldNumber txtEtdSaeAffectRep;
	private JTextFieldNumber txtEtdTotPromRep;
	private JTextFieldNumber txtEtdTotAffectRep;
	private JTextFieldNumber txtEtdTutPromRep;  
	private JTextFieldNumber txtEtdTutAffectRep;

	//Affectation 
	private JTable tblGrilleDonnees;
	private JButton btnAjouter;
	private JButton btnSupprimer;

	//Boutton
	private JButton btnSauvegarder;
	private JButton btnAnnuler;

	//Object
	private FrameAccueil frame;
	private Module       mod;


	public PanelSAE(FrameAccueil frame) {

		

		this.frame = frame;
		this.frame.setTitle("Astre - Previsionnel");
		//this.mod   = new Ressource(null, "", "", "", 0);
		
        /*                         */
        /* CREATION DES COMPOSANTS */
        /*                         */

		//Informations Modules
        this.txtCodeMod     = new JTextField(5);
        this.txtLibLongMod  = new JTextField(25);
        this.txtLibCourtMod = new JTextField(10);
		this.cbValide       = new JCheckBox ("Validation");

		//Informations Semestres
        this.txtTypeMod = new JTextField("SAE", 8);
        this.txtSem     = new JTextFieldNumber("S1", 5);
        this.txtNbEtd   = new JTextFieldNumber("52", 3);
        this.txtNbGpTd  = new JTextFieldNumber("2", 3);
        this.txtNbGpTp  = new JTextFieldNumber("4", 3);



		//Informations calcul heure PN
        this.txtHeureEtdSaePN = new JTextFieldNumber("40", 3);
        this.txtHeureEtdTutPN = new JTextFieldNumber("38", 3);
        this.txtHeureEtdTotPN = new JTextFieldNumber("78", 3);
		
		//Informations calcul repartitions
		this.txtEtdSaePromRep       = new JTextFieldNumber("0", 3); 
		this.txtEtdTutPromRep       = new JTextFieldNumber("0", 3);
		this.txtEtdTotPromRep       = new JTextFieldNumber("0", 3);

		this.txtEtdSaeAffectRep = new JTextFieldNumber("0", 3); 
		this.txtEtdTutAffectRep = new JTextFieldNumber("0", 3);
		this.txtEtdTotAffectRep = new JTextFieldNumber("0", 3);


		this.btnAjouter   = new JButton("Ajouter");
		this.btnSupprimer = new JButton("Supprimer");

		this.btnSauvegarder = new JButton("Sauvegarder");
		this.btnAnnuler     = new JButton("Annuler");
		

		
        /*                      */
        /* AJOUT DES COMPOSANTS */
        /*                      */
		
		/* PANEL HAUT */
		JPanel panelHaut = new JPanel();
        panelHaut.setLayout(new GridBagLayout());
        GridBagConstraints gbcPanelHaut = new GridBagConstraints();

        gbcPanelHaut.gridx = 0;
        gbcPanelHaut.gridy = 0;
        gbcPanelHaut.weightx = 1;
        gbcPanelHaut.weighty = 1;
        gbcPanelHaut.insets = new Insets(3, 3, 3, 3);
		gbcPanelHaut.anchor = GridBagConstraints.LINE_START;

        // Ajout des libellés première ligne
        panelHaut.add(new JLabelModule("Type module"), gbcPanelHaut);
        gbcPanelHaut.gridx++;
        panelHaut.add(new JLabelModule("Semestres"), gbcPanelHaut);
        gbcPanelHaut.gridx++;
        panelHaut.add(new JLabelModule("Code"), gbcPanelHaut);
        gbcPanelHaut.gridx++;
        panelHaut.add(new JLabelModule("Libellé long"), gbcPanelHaut);
        gbcPanelHaut.gridx++;
        panelHaut.add(new JLabelModule("Libellé court"), gbcPanelHaut);
        gbcPanelHaut.gridx++;

        // Ajout des textfields première ligne
		gbcPanelHaut.anchor = GridBagConstraints.CENTER;
        gbcPanelHaut.gridx = 0;
        gbcPanelHaut.gridy++;

        panelHaut.add(this.txtTypeMod, gbcPanelHaut);
        gbcPanelHaut.gridx++;
        panelHaut.add(this.txtSem, gbcPanelHaut);
        gbcPanelHaut.gridx++;
        panelHaut.add(this.txtCodeMod, gbcPanelHaut);
        gbcPanelHaut.gridx++;
        panelHaut.add(this.txtLibLongMod, gbcPanelHaut);
        gbcPanelHaut.gridx++;
        panelHaut.add(this.txtLibCourtMod, gbcPanelHaut);
        gbcPanelHaut.gridx++;

        // Ajout des libellés deuxième ligne
        gbcPanelHaut.gridx = 0;
        gbcPanelHaut.gridy++;

        panelHaut.add(new JLabelModule("nb Etd"), gbcPanelHaut);
        gbcPanelHaut.gridx++;
        panelHaut.add(new JLabelModule("nb gp TD"), gbcPanelHaut);
        gbcPanelHaut.gridx++;
        panelHaut.add(new JLabelModule("nbgp TP"), gbcPanelHaut);

        // Ajout des textfields deuxième ligne
        gbcPanelHaut.gridx = 0;
        gbcPanelHaut.gridy++;

        panelHaut.add(this.txtNbEtd, gbcPanelHaut);
        gbcPanelHaut.gridx++;
        panelHaut.add(this.txtNbGpTd, gbcPanelHaut);
        gbcPanelHaut.gridx++;
        panelHaut.add(this.txtNbGpTp, gbcPanelHaut);



		/* PANEL CENTRE */

		//Ajout des informations des heures PN
		JPanel panelHeurePN = new JPanel();

		//Layout
		panelHeurePN.setLayout(new GridBagLayout());
		GridBagConstraints gbcHeurePN = new GridBagConstraints();
        gbcHeurePN.gridx = 0;
        gbcHeurePN.gridy = 0;
        gbcHeurePN.weightx = 1;
        gbcHeurePN.weighty = 1;
        gbcHeurePN.insets = new Insets(2, 2, 2, 2);


		//Ajout des JLabelModule première lignes
		gbcHeurePN.anchor = GridBagConstraints.CENTER;
		gbcHeurePN.gridx = 1;
		panelHeurePN.add(new JLabelModule("h Sae"), gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(new JLabelModule("h Tut"), gbcHeurePN);
		gbcHeurePN.insets = new Insets(2,40,10,10);
		gbcHeurePN.gridx++;
		panelHeurePN.add(new JLabelModule("∑"), gbcHeurePN);
		gbcHeurePN.anchor = GridBagConstraints.LINE_START;

		gbcHeurePN.insets = new Insets(2, 3, 10, 3);
		gbcHeurePN.gridx  = 0;
		gbcHeurePN.gridy++;
		panelHeurePN.add(new JLabelModule("Total (eqtd) promo"), gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(this.txtHeureEtdSaePN,gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(this.txtHeureEtdTutPN,gbcHeurePN);
		gbcHeurePN.insets = new Insets(2,40,10,10);
		gbcHeurePN.gridx++;
		panelHeurePN.add(this.txtHeureEtdTotPN,gbcHeurePN);


		JPanel panelHeurePNValid = new JPanel();
		panelHeurePNValid.setLayout(new BorderLayout());
		panelHeurePNValid.add(panelHeurePN, BorderLayout.CENTER);
		panelHeurePNValid.add(this.cbValide, BorderLayout.SOUTH);
		panelHeurePNValid.add(new JLabelModule("PN Local (nb h tot/etd)",JLabel.CENTER), BorderLayout.NORTH);


		//Repartion 
		JPanel panelRepartition = new JPanel();

		//Layout
		panelRepartition.setLayout(new GridBagLayout());
		GridBagConstraints gbcRepar = new GridBagConstraints();
        gbcRepar.fill = GridBagConstraints.HORIZONTAL; // Remplit horizontalement
		gbcRepar.gridx = 0;
        gbcRepar.gridy = 0;
        gbcRepar.weightx = 1;
        gbcRepar.weighty = 1;
        gbcRepar.insets = new Insets(2,5,0,5);

		// Ajout première ligne
		gbcRepar.gridx = 3;
		panelRepartition.add(new JLabelModule("h Sae", JLabel.CENTER), gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(new JLabelModule("h Tot", JLabel.CENTER), gbcRepar);
		gbcRepar.gridx ++;
		gbcRepar.insets = new Insets(2,340,10,10);
		
		panelRepartition.add(new JLabelModule("∑", JLabel.CENTER), gbcRepar);

		//Deuxième ligne
		gbcRepar.insets = new Insets(2,5,5,5);
		gbcRepar.gridwidth = 3;
		gbcRepar.gridx     = 0;
		gbcRepar.gridy++;
		gbcRepar.anchor = GridBagConstraints.LINE_END;
		panelRepartition.add(new JLabelModule("Total promo (eqtd)"), gbcRepar);
		gbcRepar.anchor = GridBagConstraints.CENTER;
		gbcRepar.gridx += 3;
		gbcRepar.gridwidth = 1;
		panelRepartition.add(this.txtEtdSaePromRep, gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txtEtdTutPromRep, gbcRepar);
		gbcRepar.insets = new Insets(2,340,5,10);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txtEtdTotPromRep, gbcRepar);

		//Troisieme
		gbcRepar.insets = new Insets(2,5,5,5);
		gbcRepar.anchor = GridBagConstraints.CENTER;
		gbcRepar.gridy++;
		gbcRepar.gridwidth = 3;
		gbcRepar.gridx= 0;
		gbcRepar.anchor = GridBagConstraints.LINE_END;
		panelRepartition.add(new JLabelModule("Total affecté (eqtd)"), gbcRepar);
		gbcRepar.anchor = GridBagConstraints.CENTER;
		gbcRepar.gridx += 3;
		gbcRepar.gridwidth = 1;
		panelRepartition.add(this.txtEtdSaeAffectRep, gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txtEtdTutAffectRep, gbcRepar);
		gbcRepar.insets = new Insets(2,340,5,10);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txtEtdTotAffectRep, gbcRepar);

		// Table
		JPanel panelTable = new JPanel();
		panelTable.setLayout(new BorderLayout());
		this.tblGrilleDonnees = new JTable(new GrilleSAE());
		this.tblGrilleDonnees.setFillsViewportHeight(true);

		JPanel panelAjoutSupp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelAjoutSupp.add(this.btnAjouter);
		panelAjoutSupp.add(this.btnSupprimer);

		this.tblGrilleDonnees.setPreferredScrollableViewportSize(new Dimension(650, 200));;

		JScrollPane spGrilleDonnees = new JScrollPane(this.tblGrilleDonnees);
		panelTable.add(spGrilleDonnees, BorderLayout.CENTER);
		panelTable.add(new JLabelModule("Affectation"), BorderLayout.NORTH);
		panelTable.add(panelAjoutSupp, BorderLayout.SOUTH);

		JPanel panelRep = new JPanel(new BorderLayout());
		panelRep.add(new JLabelModule("Repartition",JLabel.CENTER), BorderLayout.NORTH);
		panelRep.add(panelRepartition, BorderLayout.CENTER);




		// Ajout des pannels au panel central
		JPanel panelCentre = new JPanel(new GridBagLayout());
		GridBagConstraints gbcCentre = new GridBagConstraints();
		gbcCentre.insets = new Insets(5, 5, 5, 5);
		gbcCentre.weightx = 1;
		gbcCentre.weighty = 1;
		gbcCentre.gridx = 0;
		gbcCentre.gridy = 0;
		gbcCentre.fill=GridBagConstraints.HORIZONTAL;
		gbcCentre.anchor = GridBagConstraints.FIRST_LINE_START;

		gbcCentre.gridwidth = 1;
		panelCentre.add(panelHeurePNValid, gbcCentre);
		gbcCentre.gridx++;
		gbcCentre.weighty = 1;
		panelCentre.add(panelRep, gbcCentre);
		gbcCentre.gridy++;
		panelCentre.add(panelTable, gbcCentre);


		

		JPanel panelBouger = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelBouger.add(this.btnSauvegarder);
		panelBouger.add(this.btnAnnuler);




        /*  PANEL CENTRAL  */
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbcMain = new GridBagConstraints();
		gbcMain.insets = new Insets(5, 20, 5, 20);
		gbcMain.weightx = 1;
		gbcMain.weighty = 0;
		gbcMain.gridy = 0;
		gbcMain.anchor = GridBagConstraints.FIRST_LINE_START;

		this.add(panelHaut, gbcMain);

		gbcMain.gridy ++;
		gbcMain.weighty = 1;

		this.add(panelCentre, gbcMain);

		gbcMain.gridy ++;
		gbcMain.weightx = 1;
		this.add(panelBouger, gbcMain);





        /*                      */
        /* STYLE DES COMPOSANTS */
        /*                      */
		
		// Rendre inactif
        this.txtTypeMod.setEditable(false);
        this.txtSem    .setEditable(false);
        this.txtNbEtd  .setEditable(false);
        this.txtNbGpTd .setEditable(false);
		this.txtNbGpTp .setEditable(false);

        this.txtHeureEtdSaePN.setEditable(false);
        this.txtHeureEtdTutPN.setEditable(false);
		this.txtHeureEtdTotPN .setEditable(false);

		this.txtEtdSaePromRep      .setEditable(false);
		this.txtEtdTotPromRep      .setEditable(false);
		this.txtEtdTutPromRep      .setEditable(false);

		this.txtEtdSaeAffectRep.setEditable(false);
		this.txtEtdTotAffectRep.setEditable(false);
		this.txtEtdTutAffectRep.setEditable(false);


		// Alignement
		this.txtNbEtd .setHorizontalAlignment(JTextField.CENTER);
		this.txtNbGpTd.setHorizontalAlignment(JTextField.CENTER);
		this.txtNbGpTp.setHorizontalAlignment(JTextField.CENTER);
		PanelSAE.aligner(panelCentre, JTextField.CENTER);

		//Bordure et style
		PanelSAE.style(this, Color.decode("0xD0D0D0"), new Dimension(120,20));
		panelHeurePN.setBorder(BorderFactory.createLineBorder(Color.decode("0xD0D0D0")));
		panelRepartition.setBorder(BorderFactory.createLineBorder(Color.decode("0xD0D0D0")));





        /*                      */
        /* STYLE DES COMPOSANTS */
        /*                      */
		PanelSAE.activer(this, this);




    }


	public PanelSAE (FrameAccueil frame, Module m) {

		this (frame);
		this.mod = m;

		//txtCode
		this.txtCodeMod    .setText(this.mod.getCode());
		this.txtLibLongMod .setText(this.mod.getLibLong());
		this.txtLibCourtMod.setText(this.mod.getLibCourt());
		
		//Semestres info
		Semestres s = this.mod.getSemestres();
		this.txtNbEtd .setText("" + s.getNbEtdSem());
		this.txtNbGpTd.setText("" + s.getNbGpTdSem());
		this.txtNbGpTp.setText("" + s.getNbGpTpSem());

	}








	/**
	 * Méthode pour aligner les TextField dans un contient précis.
	 * @param container
	 * @param alignment
	 */
	private static void aligner(Container container, int alignment) {
		for (Component component : container.getComponents()) {
			if (component instanceof JTextField) {
				((JTextField) component).setHorizontalAlignment(alignment);
			} else if (component instanceof Container) {
				aligner((Container) component, alignment);
			}
		}
	}


	/**
	 * Permettre de changer le style de bouton + des JTextField inactif.
	 * @param container
	 * @param c
	 * @param d
	 */
	private static void style(Container container, Color c, Dimension d) {
		for (Component component : container.getComponents()) {
			if (component instanceof JTextField && !((JTextField) component).isEditable()) {
				((JTextField) component).setBackground(c);
			}

			if (component instanceof JButton) {
				((JButton) component).setBackground(c);
				((JButton) component).setPreferredSize(d);
			}

			if (component instanceof Container) {
				style((Container) component, c, d);
			}
		}
	}


	/**
	 * Permettre de changer le style de bouton + des JTextField inactif.
	 * @param container
	 * @param c
	 * @param d
	 */
	private static void activer(Container container, ActionListener a) {
		for (Component component : container.getComponents()) {

			if (component instanceof JTextField && ((JTextField) component).isEditable()) {
				((JTextField) component).addActionListener(a);
			}

			if (component instanceof JButton) {
				((JButton) component).addActionListener(a);
			}

			if (component instanceof Container) {
				activer((Container) component, a);
			}
		}
	}


	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.btnAnnuler    ) this.quitter();
		if (e.getSource() == this.btnSauvegarder) this.quitter();

		if (e.getSource() == this.btnSupprimer) this.supprimer();
		if (e.getSource() == this.btnAjouter  ) this.ajouter();



	}


	private void quitter () {
		this.frame.changePanel(new PanelPrevi(frame));
	}


	private void ajouter () {
		System.out.println("Quitter");
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
		this.tblGrilleDonnees.setModel(new GrilleSAE()); 
	}
}