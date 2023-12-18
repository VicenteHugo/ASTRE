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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import view.accueil.FrameAccueil;
import view.previsionnel.PanelPrevi;

import controleur.*;
import model.CategorieHeures;
import model.Semestres;
import model.modules.Module;
import model.modules.Ressource;

public class PanelRessources extends JPanel implements ActionListener, KeyListener{

    // Modules
    private JTextField txtCodeMod;
    private JTextField txtLibLongMod;
    private JTextField txtLibCourtMod;
    private JTextField txtTypeMod;
	private JCheckBox  cbValide;

    // Semestres
    private JTextField txtSem;
    private JTextField txtNbEtd;
    private JTextField txtNbGpTd;
	private JTextField txtNbGpTp;
	
	//Heures PN
	private JTextField txtHeureCMPN;
	private JTextField txtHeureTDPN;
	private JTextField txtHeureTPPN;
	private JTextField txtHeureSPN ;

	private JTextField txtHeureEtdCMPN;
	private JTextField txtHeureEtdTDPN;
	private JTextField txtHeureEtdTPPN;
	private JTextField txtHeureEtdSPN ;


	//Repartition
	private JTextField txtCMNbSem  ;
	private JTextField txtCMNbHeure;
	private JTextField txtTDNbSem  ;
	private JTextField txtTDNbHeure;
	private JTextField txtTPNbSem  ;
	private JTextField txtTPNbHeure;

	private JTextField txtCMTot;
	private JTextField txtCMTotEtd;
	private JTextField txtCMTotEtdAffect;
	private JTextField txtTDTot;
	private JTextField txtTDTotEtd;
	private JTextField txtTDTotEtdAffect;
	private JTextField txtTPTot;
	private JTextField txtTPTotEtd;  
	private JTextField txtTPTotEtdAffect;

	private JTextField txtHPTot;
	private JTextField txtHPTotEtd;
	private JTextField txtHPTotEtdAffect;

	private JTextField txtTot;
	private JTextField txtTotEtd;
	private JTextField txtTotEtdAffect;

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


	public PanelRessources(FrameAccueil frame, Semestres semestres) {
		this.frame = frame;
		this.mod   = new Ressource(semestres, "R" + semestres.getNumSem() +".XX", "", "", 0, false);

		//Mettre la liste à 0
		HashMap <CategorieHeures, List<Integer>> map = new HashMap<>();

		//                                            PN                                             SEMAINE                                      NB HEURE
		List<Integer> lstCM = new ArrayList<>(List.of(0,0,0));
		List<Integer> lstTP = new ArrayList<>(List.of(0,0,0));
		List<Integer> lstTD = new ArrayList<>(List.of(0,0,0));
		List<Integer> lstHP = new ArrayList<>(List.of(0,0,0));


		map.put(Controleur.getControleur().getCategorieHeure("CM"), lstCM);
		map.put(Controleur.getControleur().getCategorieHeure("TP"), lstTP);
		map.put(Controleur.getControleur().getCategorieHeure("TD"), lstTD);
		map.put(Controleur.getControleur().getCategorieHeure("HP"), lstHP);


		this.mod.setHeures(map);

		Controleur.getControleur().ajouterModule(this.mod);

		
        /*                         */
        /* CREATION DES COMPOSANTS */
        /*                         */

		//Informations Modules
        this.txtCodeMod     = new JTextField("R" + semestres.getNumSem() +".XX",5);
        this.txtLibLongMod  = new JTextField(25);
        this.txtLibCourtMod = new JTextField(10);
		this.cbValide       = new JCheckBox ("Validation");

		//Informations Semestres
        this.txtTypeMod = new JTextField("Ressource", 8);
        this.txtSem     = new JTextField("S1", 5);
        this.txtNbEtd   = new JTextField("80", 3);
        this.txtNbGpTd  = new JTextField("80", 3);
        this.txtNbGpTp  = new JTextField("80", 3);



		//Informations heure PN
        this.txtHeureCMPN = new JTextField( "0", 3);
        this.txtHeureTDPN = new JTextField( "0", 3);
        this.txtHeureTPPN = new JTextField( "0", 3);

		//Informations calcul heure PN
        this.txtHeureSPN     = new JTextField("0", 3);
        this.txtHeureEtdCMPN = new JTextField("0", 3);
        this.txtHeureEtdTDPN = new JTextField("0", 3);
        this.txtHeureEtdTPPN = new JTextField("0", 3);
        this.txtHeureEtdSPN  = new JTextField("0", 3);

		//Information répartition
		this.txtCMNbSem   = new JTextField("0",3);
		this.txtCMNbHeure = new JTextField("0",3);
		this.txtTDNbSem   = new JTextField("0",3);
		this.txtTDNbHeure = new JTextField("0",3);
		this.txtTPNbSem   = new JTextField("0",3);
		this.txtTPNbHeure = new JTextField("0",3);


		this.txtCMTot          = new JTextField("0", 3); 
		this.txtTDTot          = new JTextField("0", 3);
		this.txtTPTot          = new JTextField("0", 3);
		this.txtCMTotEtd       = new JTextField("0", 3); 
		this.txtTDTotEtd       = new JTextField("0", 3);
		this.txtTPTotEtd       = new JTextField("0", 3);
		this.txtCMTotEtdAffect = new JTextField("0", 3); 
		this.txtTDTotEtdAffect = new JTextField("0", 3);
		this.txtTPTotEtdAffect = new JTextField("0", 3);

		this.txtHPTot          = new JTextField("0",3);
		this.txtHPTotEtd       = new JTextField("0",3);
		this.txtHPTotEtdAffect = new JTextField("0",3);

		this.txtTot          = new JTextField("0",3);
		this.txtTotEtd       = new JTextField("0",3);
		this.txtTotEtdAffect = new JTextField("0",3);

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
        panelHaut.add(new JLabel("Type module"), gbcPanelHaut);
        gbcPanelHaut.gridx++;
        panelHaut.add(new JLabel("Semestres"), gbcPanelHaut);
        gbcPanelHaut.gridx++;
        panelHaut.add(new JLabel("Code"), gbcPanelHaut);
        gbcPanelHaut.gridx++;
        panelHaut.add(new JLabel("Libellé long"), gbcPanelHaut);
        gbcPanelHaut.gridx++;
        panelHaut.add(new JLabel("Libellé court"), gbcPanelHaut);
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

        panelHaut.add(new JLabel("nb Etd"), gbcPanelHaut);
        gbcPanelHaut.gridx++;
        panelHaut.add(new JLabel("nb gp TD"), gbcPanelHaut);
        gbcPanelHaut.gridx++;
        panelHaut.add(new JLabel("nbgp TP"), gbcPanelHaut);

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
        gbcHeurePN.insets = new Insets(3, 3, 3, 3);


		//Ajout des JLabel première lignes
		gbcHeurePN.anchor = GridBagConstraints.LINE_START;
		gbcHeurePN.gridx = 1;
		panelHeurePN.add(new JLabel("CM"), gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(new JLabel("TD"), gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(new JLabel("TP"), gbcHeurePN);
		gbcHeurePN.gridx++;
		gbcHeurePN.anchor = GridBagConstraints.CENTER;
		panelHeurePN.add(new JLabel("∑"), gbcHeurePN);
		gbcHeurePN.anchor = GridBagConstraints.LINE_START;

		gbcHeurePN.gridx = 1;
		gbcHeurePN.gridy++;
		panelHeurePN.add(this.txtHeureCMPN,gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(this.txtHeureTDPN,gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(this.txtHeureTPPN,gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(this.txtHeureSPN,gbcHeurePN);

		gbcHeurePN.gridx = 0;
		gbcHeurePN.gridy++;
		panelHeurePN.add(new JLabel("Total (eqtd) promo"), gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(this.txtHeureEtdCMPN,gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(this.txtHeureEtdTDPN,gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(this.txtHeureEtdTPPN,gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(this.txtHeureEtdSPN,gbcHeurePN);


		JPanel panelHeurePNValid = new JPanel();
		panelHeurePNValid.setLayout(new BorderLayout());
		panelHeurePNValid.add(panelHeurePN, BorderLayout.CENTER);
		panelHeurePNValid.add(this.cbValide, BorderLayout.SOUTH);
		panelHeurePNValid.add(new JLabel("PN Local (nb h tot/etd)",JLabel.CENTER), BorderLayout.PAGE_START);


		//Repartion 
		JPanel panelRepartition = new JPanel();

		//Layout
		panelRepartition.setLayout(new GridBagLayout());
		GridBagConstraints gbcRepar = new GridBagConstraints();
        gbcRepar.gridx = 0;
        gbcRepar.gridy = 0;
        gbcRepar.weightx = 1;
        gbcRepar.weighty = 1;
        gbcRepar.insets = new Insets(2,5,2,5);

		// Ajout première ligne
		gbcRepar.gridwidth = 2;
		panelRepartition.add(new JLabel("CM"), gbcRepar);
		gbcRepar.gridx+= 2;
		panelRepartition.add(new JLabel("TD"), gbcRepar);
		gbcRepar.gridx+= 2;
		panelRepartition.add(new JLabel("TP"), gbcRepar);


		//Deuxième ligne
		gbcRepar.gridwidth = 1;
		gbcRepar.gridx     = 0;
		gbcRepar.gridy++;
		panelRepartition.add(new JLabel("nb Sem"), gbcRepar);
		gbcRepar.gridx++;
		panelRepartition.add(new JLabel("nb h/sem"), gbcRepar);
		gbcRepar.gridx++;
		panelRepartition.add(new JLabel("nb Sem"), gbcRepar);
		gbcRepar.gridx++;
		panelRepartition.add(new JLabel("nb h/sem"), gbcRepar);
		gbcRepar.gridx++;
		panelRepartition.add(new JLabel("nb Sem"), gbcRepar);
		gbcRepar.gridx++;
		panelRepartition.add(new JLabel("nb h/sem"), gbcRepar);

        gbcRepar.insets.left = 20;
		gbcRepar.gridx+= 2;
		gbcRepar.anchor = GridBagConstraints.LINE_START;
		panelRepartition.add(new JLabel("CM", JLabel.LEFT), gbcRepar);
        gbcRepar.insets.left = 2;
		gbcRepar.gridx++;
		panelRepartition.add(new JLabel("TD", JLabel.LEFT), gbcRepar);
		gbcRepar.gridx++;
		panelRepartition.add(new JLabel("TP", JLabel.LEFT), gbcRepar);

        gbcRepar.insets.left = 20;
		gbcRepar.anchor = GridBagConstraints.CENTER;
		gbcRepar.gridx ++;
		panelRepartition.add(new JLabel(), gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(new JLabel("HP", JLabel.LEFT), gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(new JLabel(), gbcRepar);
		gbcRepar.gridx ++;
        gbcRepar.insets.right = 20;
		panelRepartition.add(new JLabel("∑", JLabel.LEFT), gbcRepar);
        gbcRepar.insets.left = 2;



		//Troisieme
        gbcRepar.insets = new Insets(2,2,2,2);
		gbcRepar.gridx = 0;
		gbcRepar.gridy++;
		gbcRepar.anchor = GridBagConstraints.LINE_END;
		panelRepartition.add(this.txtCMNbHeure, gbcRepar);
		gbcRepar.gridx++;
		gbcRepar.anchor = GridBagConstraints.LINE_START;
		panelRepartition.add(this.txtCMNbSem  , gbcRepar);
		gbcRepar.gridx++;
		gbcRepar.anchor = GridBagConstraints.LINE_END;
		panelRepartition.add(this.txtTDNbHeure, gbcRepar);
		gbcRepar.gridx++;
		gbcRepar.anchor = GridBagConstraints.LINE_START;
		panelRepartition.add(this.txtTDNbSem  , gbcRepar);
		gbcRepar.gridx++;
		gbcRepar.anchor = GridBagConstraints.LINE_END;
		panelRepartition.add(this.txtTPNbHeure, gbcRepar);
		gbcRepar.gridx++;
		gbcRepar.anchor = GridBagConstraints.LINE_START;
		panelRepartition.add(this.txtTPNbSem  , gbcRepar);
		gbcRepar.gridx++;

        gbcRepar.insets.left = 20;
		gbcRepar.gridx++;
		panelRepartition.add(this.txtCMTot, gbcRepar);
        gbcRepar.insets.left = 2;
		gbcRepar.gridx++;
		panelRepartition.add(this.txtTDTot, gbcRepar);
		gbcRepar.gridx++;
		panelRepartition.add(this.txtTPTot, gbcRepar);

        gbcRepar.insets.left = 20;
		gbcRepar.anchor = GridBagConstraints.LINE_END;
		gbcRepar.gridx ++;
		panelRepartition.add(new JLabel(), gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txtHPTot, gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(new JLabel(), gbcRepar);
		gbcRepar.gridx ++;
        gbcRepar.insets.right = 20;
		panelRepartition.add(this.txtTot, gbcRepar);
        gbcRepar.insets.left = 2;



		// Quatrième ligne
		gbcRepar.anchor = GridBagConstraints.LINE_END;
        gbcRepar.insets = new Insets(2,2,2,2);
		gbcRepar.gridy++;
		gbcRepar.gridwidth = 3;
		gbcRepar.insets.top = 8;
		gbcRepar.gridx= 4;
		panelRepartition.add(new JLabel("Total promo (eqtd)"), gbcRepar);
		gbcRepar.gridx += 3;
		gbcRepar.gridwidth = 1;
		panelRepartition.add(this.txtCMTotEtd, gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txtTPTotEtd, gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txtTDTotEtd, gbcRepar);

        gbcRepar.insets.left = 20;
		gbcRepar.anchor = GridBagConstraints.LINE_END;
		gbcRepar.gridx ++;
		panelRepartition.add(new JLabel(), gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txtHPTotEtd, gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(new JLabel(), gbcRepar);
		gbcRepar.gridx ++;
        gbcRepar.insets.right = 20;
		panelRepartition.add(this.txtTotEtd, gbcRepar);
        gbcRepar.insets.left = 2;

		// Ciqnuième ligne
		gbcRepar.anchor = GridBagConstraints.LINE_END;
        gbcRepar.insets = new Insets(2,2,20,2);
		gbcRepar.gridy++;
		gbcRepar.gridwidth = 3;
		gbcRepar.gridx= 4;
		panelRepartition.add(new JLabel("Total affecté (eqtd)"), gbcRepar);
		gbcRepar.gridx += 3;
		gbcRepar.gridwidth = 1;
		panelRepartition.add(this.txtCMTotEtdAffect, gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txtTPTotEtdAffect, gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txtTDTotEtdAffect, gbcRepar);

        gbcRepar.insets.left = 20;
		gbcRepar.anchor = GridBagConstraints.LINE_END;
		gbcRepar.gridx ++;
		panelRepartition.add(new JLabel(), gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txtHPTotEtdAffect, gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(new JLabel(), gbcRepar);
		gbcRepar.gridx ++;
        gbcRepar.insets.right = 20;
		panelRepartition.add(this.txtTotEtdAffect, gbcRepar);



		// Table
		JPanel panelTable = new JPanel();
		panelTable.setLayout(new BorderLayout());
		this.tblGrilleDonnees = new JTable(new GrilleRessources());
		this.tblGrilleDonnees.setFillsViewportHeight(true);

		JPanel panelAjoutSupp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelAjoutSupp.add(this.btnAjouter);
		panelAjoutSupp.add(this.btnSupprimer);

		this.tblGrilleDonnees.setPreferredScrollableViewportSize(new Dimension(650, 200));;

		JScrollPane spGrilleDonnees = new JScrollPane(this.tblGrilleDonnees);
		panelTable.add(spGrilleDonnees, BorderLayout.CENTER);
		panelTable.add(new JLabel("Affectation"), BorderLayout.NORTH);
		panelTable.add(panelAjoutSupp, BorderLayout.SOUTH);

		JPanel panelRep = new JPanel(new BorderLayout());
		panelRep.add(new JLabel("Repartition",JLabel.CENTER), BorderLayout.NORTH);
		panelRep.add(panelRepartition, BorderLayout.CENTER);




		// Ajout des pannels au panel central
		JPanel panelCentre = new JPanel(new GridBagLayout());
		GridBagConstraints gbcCentre = new GridBagConstraints();
		gbcCentre.insets = new Insets(5, 20, 5, 5);
		gbcCentre.weightx = 1;
		gbcCentre.weighty = 1;
		gbcCentre.gridx = 0;
		gbcCentre.gridy = 0;
		gbcCentre.anchor = GridBagConstraints.NORTHWEST;

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
		gbcMain.anchor = GridBagConstraints.NORTHWEST;

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

		this.txtHeureSPN    .setEditable(false);
        this.txtHeureEtdCMPN.setEditable(false);
        this.txtHeureEtdTDPN.setEditable(false);
        this.txtHeureEtdTPPN.setEditable(false);
		this.txtHeureEtdSPN .setEditable(false);

		this.txtCMTot         .setEditable(false);
		this.txtTDTot         .setEditable(false);
		this.txtTPTot         .setEditable(false);
		this.txtCMTotEtd      .setEditable(false);
		this.txtTDTotEtd      .setEditable(false);
		this.txtTPTotEtd      .setEditable(false);
		this.txtCMTotEtdAffect.setEditable(false);
		this.txtTDTotEtdAffect.setEditable(false);
		this.txtTPTotEtdAffect.setEditable(false);

		this.txtHPTotEtd      .setEditable(false);
		this.txtHPTotEtdAffect.setEditable(false);

		this.txtTot         .setEditable(false);
		this.txtTotEtd      .setEditable(false);
		this.txtTotEtdAffect.setEditable(false);


		// Alignement
		this.txtNbEtd .setHorizontalAlignment(JTextField.CENTER);
		this.txtNbGpTd.setHorizontalAlignment(JTextField.CENTER);
		this.txtNbGpTp.setHorizontalAlignment(JTextField.CENTER);
		PanelRessources.aligner(panelCentre, JTextField.RIGHT);

		//Bordure et fond
		PanelRessources.fond(this, Color.decode("0xD0D0D0"), new Dimension(120,20));
		panelHeurePN.setBorder(BorderFactory.createLineBorder(Color.decode("0xD0D0D0")));
		panelRepartition.setBorder(BorderFactory.createLineBorder(Color.decode("0xD0D0D0")));





        /*                      */
        /* STYLE DES COMPOSANTS */
        /*                      */
		PanelRessources.activer(this, this, this);

    }


	public PanelRessources (FrameAccueil frame, Module m) {

		this (frame, m.getSemestres());
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
	 * Permettre de changer le stylede boutton + des JTextFiel inactif.
	 * @param container
	 * @param c
	 * @param d
	 */
	private static void fond(Container container, Color c, Dimension d) {
		for (Component component : container.getComponents()) {

			if (component instanceof JTextField && !((JTextField) component).isEditable()) {
				((JTextField) component).setBackground(c);
			}

			if (component instanceof JButton) {
				((JButton) component).setBackground(c);
				((JButton) component).setPreferredSize(d);
			}

			if (component instanceof Container) {
				fond((Container) component, c, d);
			}
		}
	}


	/**
	 * Permettre de changer le stylede boutton + des JTextFiel inactif.
	 * @param container
	 * @param c
	 * @param d
	 */
	private static void activer(Container container, ActionListener a, KeyListener k) {
		for (Component component : container.getComponents()) {

			if (component instanceof JTextField && ((JTextField) component).isEditable()) {
				((JTextField) component).addKeyListener(k);
			}

			if (component instanceof JButton) {
				((JButton) component).addActionListener(a);
			}

			if (component instanceof Container) {
				activer((Container) component, a,k);
			}
		}
	}


	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.btnAnnuler    ) this.quitter();
		if (e.getSource() == this.btnSauvegarder) this.sauvegarder();

		if (e.getSource() == this.btnSupprimer) this.supprimer();
		if (e.getSource() == this.btnAjouter  ) this.ajouter();


	}


	private void quitter () {
		this.frame.changePanel(new PanelPrevi(frame));
	}


	private void sauvegarder () {

		boolean   val = this.cbValide.isValid();
		String    cod = this.txtCodeMod.getText();
		String    liL = this.txtLibLongMod.getText();
		String    liC = this.txtLibCourtMod.getText();
		int       hp  = Integer.parseInt(this.txtHPTot.getText());

		HashMap <CategorieHeures, List<Integer>> map = new HashMap<>();

		//                                            PN                                             SEMAINE                                      NB HEURE
		List<Integer> lstCM = new ArrayList<>(List.of(Integer.parseInt(this.txtHeureCMPN.getText()), Integer.parseInt(this.txtCMNbSem.getText()), Integer.parseInt(this.txtCMNbHeure.getText())));
		List<Integer> lstTP = new ArrayList<>(List.of(Integer.parseInt(this.txtHeureTPPN.getText()), Integer.parseInt(this.txtTPNbSem.getText()), Integer.parseInt(this.txtTPNbHeure.getText())));
		List<Integer> lstTD = new ArrayList<>(List.of(Integer.parseInt(this.txtHeureTDPN.getText()), Integer.parseInt(this.txtTDNbSem.getText()), Integer.parseInt(this.txtTDNbHeure.getText())));
		List<Integer> lstHP = new ArrayList<>(List.of(Integer.parseInt(this.txtHPTot    .getText()), 1                                          , Integer.parseInt(this.txtHPTot    .getText())));


		map.put(Controleur.getControleur().getCategorieHeure("CM"), lstCM);
		map.put(Controleur.getControleur().getCategorieHeure("TP"), lstTP);
		map.put(Controleur.getControleur().getCategorieHeure("TD"), lstTD);
		map.put(Controleur.getControleur().getCategorieHeure("HP"), lstHP);

		if (Controleur.getControleur().modifModules(mod, cod, liL, liC, hp, val, map))
			this.quitter();
		else
			this.showMessageDialog("Le code est déja utiliser");
	}


	private void ajouter () {
		JFrame f = new JFrame();
        f.add(new PanelAddRessourceIntervenant(this,this.frame, f,mod));
        f.setTitle("Ajout d'un Intervenant");
		f.pack();
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setAlwaysOnTop(true);
		f.setVisible(true);
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

	private void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}


	@Override
	public void keyTyped(KeyEvent e) {

		String chiffreAv = ((JTextField) e.getSource()).getText();

		try {

			//Récupération des données
			int cmPN  = Integer.parseInt(this.txtHeureCMPN.getText());
			int cmSem = Integer.parseInt(this.txtCMNbSem  .getText());
			int cmHeu = Integer.parseInt(this.txtCMNbHeure.getText());
			
			int tdPN  = Integer.parseInt(this.txtHeureTDPN.getText());
			int tdSem = Integer.parseInt(this.txtTDNbSem  .getText());
			int tdHeu = Integer.parseInt(this.txtTDNbHeure.getText());
			
			int tpPN  = Integer.parseInt(this.txtHeureTPPN.getText());
			int tpSem = Integer.parseInt(this.txtTPNbSem  .getText());
			int tpHeu = Integer.parseInt(this.txtTPNbHeure.getText());

			int hpHeu = Integer.parseInt(this.txtHPTot.getText());

			float coefCM = Controleur.getControleur().getCategorieHeure("CM").getcoefCatHeur();
			float coefTD = Controleur.getControleur().getCategorieHeure("TD").getcoefCatHeur();
			float coefTP = Controleur.getControleur().getCategorieHeure("TP").getcoefCatHeur();
			float coefHP = Controleur.getControleur().getCategorieHeure("HP").getcoefCatHeur();



			/* CALCUL PN */

			// eqtd
			int cmPNetd = (int) (cmPN * coefCM);
			int tdPNetd = (int) (tdPN * coefTD);
			int tpPNetd = (int) (tpPN * coefTP);
			this.txtHeureEtdCMPN.setText( cmPNetd + "");
			this.txtHeureEtdTDPN.setText( tdPNetd + "");
			this.txtHeureEtdTPPN.setText( tpPNetd + "");

			//somme
			this.txtHeureSPN   .setText((cmPN + tdPN + tpPN) + "");
			this.txtHeureEtdSPN.setText((cmPNetd + tdPNetd + tpPNetd) + "");



			/* CALCUL REPARTITION */

			// semaine * heure
			int cmHTot = (cmHeu * cmSem);
			int tdHTot = (tpHeu * tpSem);
			int tpHTot = (tdHeu * tdSem);

			this.txtCMTot.setText( cmHTot + "");
			this.txtTPTot.setText( tdHTot + "");
			this.txtTDTot.setText( tpHTot + "");

			this.txtTot.setText((cmHTot + tdHTot + tpHTot + hpHeu) + "");

			//EQTD
			int cmHTotEtd = (int) ((cmHeu * cmSem) * coefCM);
			int tdHTotEtd = (int) ((tpHeu * tpSem) * coefTD);
			int tpHTotEtd = (int) ((tdHeu * tdSem) * coefTP);
			int hpHTotEtd = (int) ( hpHeu          * coefHP);

			this.txtCMTotEtd.setText( cmHTotEtd + "");
			this.txtTPTotEtd.setText( tdHTotEtd + "");
			this.txtTDTotEtd.setText( tpHTotEtd + "");
			this.txtHPTotEtd.setText( hpHTotEtd + "");

			this.txtTot.setText((cmHTotEtd + tdHTotEtd + tpHTotEtd + hpHTotEtd) + "");



			//On met tous dans la liste de mod
			this.mod.initList(tdHeu, tpSem, tpHeu, Controleur.getControleur().getCategorieHeure("CM"));
			this.mod.initList(tdHeu, tpSem, tpHeu, Controleur.getControleur().getCategorieHeure("TD"));
			this.mod.initList(tpPN, tpSem, tpHeu, Controleur.getControleur().getCategorieHeure("TP"));
			this.mod.initList(tdHeu, 1, tpHeu, Controleur.getControleur().getCategorieHeure("HP"));


		} catch (NumberFormatException ex) {
			this.showMessageDialog("Le chiffre saisie est inccorect.");

			if (chiffreAv.isEmpty())
				((JTextField) e.getSource()).setText("0");
			else
				((JTextField) e.getSource()).setText(chiffreAv);

		}
	}


	public void keyPressed (KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
}