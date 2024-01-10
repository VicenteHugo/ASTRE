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

import view.JLabelModule;
import view.JTextFieldNumber;
import view.accueil.FrameAccueil;
import view.previsionnel.PanelPrevi;
import view.JButtonStyle;

import controleur.*;
import model.Affectations;
import model.CategorieHeures;
import model.Semestres;
import model.modules.Module;
import model.modules.Ressource;

public class PanelRessources extends JPanel implements ActionListener, FocusListener{

    // Modules
    private JTextField txtCodeMod;
    private JTextField txtLibLongMod;
    private JTextField txtLibCourtMod;
    private JTextField txtTypeMod;
	private JCheckBox  cbValide;

    // Semestres
    private JTextField       txtSem;
    private JTextFieldNumber txtNbEtd;
    private JTextFieldNumber txtNbGpTd;
	private JTextFieldNumber txtNbGpTp;
	
	//Heures PN
	private JTextFieldNumber txtHeureCMPN;
	private JTextFieldNumber txtHeureTDPN;
	private JTextFieldNumber txtHeureTPPN;
	private JTextFieldNumber txtHeureSPN ;

	private JTextFieldNumber txtHeureEtdCMPN;
	private JTextFieldNumber txtHeureEtdTDPN;
	private JTextFieldNumber txtHeureEtdTPPN;
	private JTextFieldNumber txtHeureEtdSPN ;


	//Repartition
	private JTextFieldNumber txtCMNbSem  ;
	private JTextFieldNumber txtCMNbHeure;
	private JTextFieldNumber txtTDNbSem  ;
	private JTextFieldNumber txtTDNbHeure;
	private JTextFieldNumber txtTPNbSem  ;
	private JTextFieldNumber txtTPNbHeure;

	private JTextFieldNumber txtCMTot;
	private JTextFieldNumber txtCMTotEtd;
	private JTextFieldNumber txtCMTotEtdAffect;
	private JTextFieldNumber txtTDTot;
	private JTextFieldNumber txtTDTotEtd;
	private JTextFieldNumber txtTDTotEtdAffect;
	private JTextFieldNumber txtTPTot;
	private JTextFieldNumber txtTPTotEtd;  
	private JTextFieldNumber txtTPTotEtdAffect;

	private JTextFieldNumber txtHPTot;
	private JTextFieldNumber txtHPTotEtd;
	private JTextFieldNumber txtHPTotEtdAffect;

	private JTextFieldNumber txtTot;
	private JTextFieldNumber txtTotEtd;
	private JTextFieldNumber txtTotEtdAffect;

	//Affectation 
	JTable tblGrilleDonnees;
	private JButtonStyle btnAjouter;
	private JButtonStyle btnSupprimer;

	//Boutton
	private JButtonStyle btnSauvegarder;
	private JButtonStyle btnAnnuler;

	//Object
	private FrameAccueil frame;
	private Module       mod;
	private boolean      estNouveau;

	private JFrame f;


	public PanelRessources(FrameAccueil frame, Semestres semestres) {
		this.frame = frame;
		this.frame.setTitle("Astre - Ressource");
		
		this.mod   = new Ressource(semestres, "", "", "", 0, false);

		//Mettre la liste à 0
		HashMap <CategorieHeures, List<Integer>> map = new HashMap<>();

		//                                            PN                                             SEMAINE                                      NB HEURE
		List<Integer> lstCM = new ArrayList<Integer>(List.of(0,0,0));
		List<Integer> lstTP = new ArrayList<Integer>(List.of(0,0,0));
		List<Integer> lstTD = new ArrayList<Integer>(List.of(0,0,0));
		List<Integer> lstHP = new ArrayList<Integer>(List.of(0,0,0));


		map.put(Controleur.getControleur().getCategorieHeure("CM"), lstCM);
		map.put(Controleur.getControleur().getCategorieHeure("TP"), lstTP);
		map.put(Controleur.getControleur().getCategorieHeure("TD"), lstTD);
		map.put(Controleur.getControleur().getCategorieHeure("HP"), lstHP);


		this.mod.setHeures(map);
		this.estNouveau = true;

		loadPage(semestres);

    }


	public PanelRessources (FrameAccueil frame, Module m) {
		this.frame = frame;
		this.frame.setTitle("Astre - Previsionnel");

		this.mod = m;

		loadPage(m.getSemestres());

		//txtCode
		this.txtCodeMod    .setText(this.mod.getCode());
		this.txtLibLongMod .setText(this.mod.getLibLong());
		this.txtLibCourtMod.setText(this.mod.getLibCourt());


		//Heure ponctuelle
		this.txtHPTot.setText(this.mod.getHeurePonctuel() + "");

		//CM
		HashMap<CategorieHeures, List<Integer>> map = this.mod.getHeures();

		List<Integer> lst = map.get(Controleur.getControleur().getCategorieHeure("CM"));
		if (lst != null) {
			this.txtHeureCMPN.setText(lst.get(0) + "");		
			this.txtCMNbSem  .setText(lst.get(1) + "");		
			this.txtCMNbHeure.setText(lst.get(2) + "");		
		}


		lst = map.get(Controleur.getControleur().getCategorieHeure("TP"));
		if (lst != null) {
			this.txtHeureTPPN.setText(lst.get(0) + "");		
			this.txtTPNbSem  .setText(lst.get(1) + "");		
			this.txtTPNbHeure.setText(lst.get(2) + "");	
		}	

		lst = map.get(Controleur.getControleur().getCategorieHeure("TD"));
		if (lst != null) { 
			this.txtHeureTDPN.setText(lst.get(0) + "");		
			this.txtTDNbSem  .setText(lst.get(1) + "");		
			this.txtTDNbHeure.setText(lst.get(2) + "");		
		}

		this.cbValide.setSelected(this.mod.isValide());

		//Juste pour faire les calculs
		this.focusLost(null);


		this.estNouveau = false;

	}

	private void loadPage(Semestres semestres){
		/*                         */
        /* CREATION DES COMPOSANTS */
        /*                         */

		//Informations Modules
        this.txtCodeMod     = new JTextField("",5);
        this.txtLibLongMod  = new JTextField(25);
        this.txtLibCourtMod = new JTextField(10);
		this.cbValide       = new JCheckBox ("Validation");

		//Informations Semestres
        this.txtTypeMod = new JTextField("Ressource", 8);
        this.txtSem     = new JTextField("S" + semestres.getNumSem(), 5);
		this.txtNbEtd   = new JTextFieldNumber("" + semestres.getNbEtdSem (), 3);
		this.txtNbGpTd  = new JTextFieldNumber("" + semestres.getNbGpTdSem(), 3);
		this.txtNbGpTp  = new JTextFieldNumber("" + semestres.getNbGpTpSem(), 3); 



		//Informations heure PN
        this.txtHeureCMPN = new JTextFieldNumber( "0", 3);
        this.txtHeureTDPN = new JTextFieldNumber( "0", 3);
        this.txtHeureTPPN = new JTextFieldNumber( "0", 3);

		//Informations calcul heure PN
        this.txtHeureSPN     = new JTextFieldNumber("0", 3);
        this.txtHeureEtdCMPN = new JTextFieldNumber("0", 3);
        this.txtHeureEtdTDPN = new JTextFieldNumber("0", 3);
        this.txtHeureEtdTPPN = new JTextFieldNumber("0", 3);
        this.txtHeureEtdSPN  = new JTextFieldNumber("0", 3);

		//Information répartition
		this.txtCMNbSem   = new JTextFieldNumber("0",3);
		this.txtCMNbHeure = new JTextFieldNumber("0",3);
		this.txtTDNbSem   = new JTextFieldNumber("0",3);
		this.txtTDNbHeure = new JTextFieldNumber("0",3);
		this.txtTPNbSem   = new JTextFieldNumber("0",3);
		this.txtTPNbHeure = new JTextFieldNumber("0",3);


		this.txtCMTot          = new JTextFieldNumber("0", 3); 
		this.txtTDTot          = new JTextFieldNumber("0", 3);
		this.txtTPTot          = new JTextFieldNumber("0", 3);
		this.txtCMTotEtd       = new JTextFieldNumber("0", 3); 
		this.txtTDTotEtd       = new JTextFieldNumber("0", 3);
		this.txtTPTotEtd       = new JTextFieldNumber("0", 3);
		this.txtCMTotEtdAffect = new JTextFieldNumber("0", 3); 
		this.txtTDTotEtdAffect = new JTextFieldNumber("0", 3);
		this.txtTPTotEtdAffect = new JTextFieldNumber("0", 3);

		this.txtHPTot          = new JTextFieldNumber("0",3);
		this.txtHPTotEtd       = new JTextFieldNumber("0",3);
		this.txtHPTotEtdAffect = new JTextFieldNumber("0",3);

		this.txtTot          = new JTextFieldNumber("0",3);
		this.txtTotEtd       = new JTextFieldNumber("0",3);
		this.txtTotEtdAffect = new JTextFieldNumber("0",3);

		this.btnAjouter   = new JButtonStyle("Ajouter");
		this.btnSupprimer = new JButtonStyle("Supprimer");

		this.btnSauvegarder = new JButtonStyle("Sauvegarder");
		this.btnAnnuler     = new JButtonStyle("Annuler");
		

		
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
        gbcPanelHaut.insets = new Insets(2, 2, 2, 2);
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
		panelHeurePN.add(new JLabelModule("CM"), gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(new JLabelModule("TD"), gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(new JLabelModule("TP"), gbcHeurePN);
		
		gbcHeurePN.insets.right=10;
		gbcHeurePN.gridx++;
		panelHeurePN.add(new JLabelModule("∑"), gbcHeurePN);
		gbcHeurePN.insets.right=2;
		gbcHeurePN.anchor = GridBagConstraints.LINE_START;

		gbcHeurePN.gridx = 1;
		gbcHeurePN.gridy++;
		panelHeurePN.add(this.txtHeureCMPN,gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(this.txtHeureTDPN,gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(this.txtHeureTPPN,gbcHeurePN);
		gbcHeurePN.insets.right=10;
		gbcHeurePN.gridx++;
		panelHeurePN.add(this.txtHeureSPN,gbcHeurePN);
		gbcHeurePN.insets.right=2;


		gbcHeurePN.insets.bottom=10;
		gbcHeurePN.gridx = 0;
		gbcHeurePN.gridy++;
		panelHeurePN.add(new JLabelModule("Total (eqtd) promo"), gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(this.txtHeureEtdCMPN,gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(this.txtHeureEtdTDPN,gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(this.txtHeureEtdTPPN,gbcHeurePN);
		gbcHeurePN.insets.right=10;
		gbcHeurePN.gridx++;
		panelHeurePN.add(this.txtHeureEtdSPN,gbcHeurePN);


		JPanel panelHeurePNValid = new JPanel();
		panelHeurePNValid.setLayout(new BorderLayout());
		panelHeurePNValid.add(panelHeurePN, BorderLayout.CENTER);
		panelHeurePNValid.add(this.cbValide, BorderLayout.SOUTH);
		panelHeurePNValid.add(new JLabelModule("PN Local (nb h tot/etd)",JLabel.CENTER), BorderLayout.PAGE_START);


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
		panelRepartition.add(new JLabelModule("CM"), gbcRepar);
		gbcRepar.gridx+= 2;
		panelRepartition.add(new JLabelModule("TD"), gbcRepar);
		gbcRepar.gridx+= 2;
		panelRepartition.add(new JLabelModule("TP"), gbcRepar);


		//Deuxième ligne
		gbcRepar.gridwidth = 1;
		gbcRepar.gridx     = 0;
		gbcRepar.gridy++;
		panelRepartition.add(new JLabelModule("nb Sem"), gbcRepar);
		gbcRepar.gridx++;
		panelRepartition.add(new JLabelModule("nb h/sem"), gbcRepar);
		gbcRepar.gridx++;
		panelRepartition.add(new JLabelModule("nb Sem"), gbcRepar);
		gbcRepar.gridx++;
		panelRepartition.add(new JLabelModule("nb h/sem"), gbcRepar);
		gbcRepar.gridx++;
		panelRepartition.add(new JLabelModule("nb Sem"), gbcRepar);
		gbcRepar.gridx++;
		panelRepartition.add(new JLabelModule("nb h/sem"), gbcRepar);

        gbcRepar.insets.left = 20;
		gbcRepar.gridx+= 2;
		gbcHeurePN.anchor = GridBagConstraints.CENTER;
		panelRepartition.add(new JLabelModule("CM", JLabel.LEFT), gbcRepar);
        gbcRepar.insets.left = 2;
		gbcRepar.gridx++;
		panelRepartition.add(new JLabelModule("TD", JLabel.LEFT), gbcRepar);
		gbcRepar.gridx++;
		panelRepartition.add(new JLabelModule("TP", JLabel.LEFT), gbcRepar);

        gbcRepar.insets.left = 20;
		gbcRepar.gridx ++;
		panelRepartition.add(new JLabelModule(), gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(new JLabelModule("HP", JLabel.LEFT), gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(new JLabelModule(), gbcRepar);
		gbcRepar.gridx ++;
        gbcRepar.insets.right = 10;
		panelRepartition.add(new JLabelModule("∑", JLabel.LEFT), gbcRepar);
        gbcRepar.insets.left = 2;



		//Troisieme
        gbcRepar.insets = new Insets(2,2,2,2);
		gbcRepar.gridx = 0;
		gbcRepar.gridy++;
		gbcRepar.anchor = GridBagConstraints.LINE_END;
		panelRepartition.add(this.txtCMNbSem  , gbcRepar);
		gbcRepar.gridx++;
		gbcRepar.anchor = GridBagConstraints.LINE_START;
		panelRepartition.add(this.txtCMNbHeure, gbcRepar);
		gbcRepar.gridx++;
		gbcRepar.anchor = GridBagConstraints.LINE_END;
		panelRepartition.add(this.txtTDNbSem  , gbcRepar);
		gbcRepar.gridx++;
		gbcRepar.anchor = GridBagConstraints.LINE_START;
		panelRepartition.add(this.txtTDNbHeure, gbcRepar);
		gbcRepar.gridx++;
		gbcRepar.anchor = GridBagConstraints.LINE_END;
		panelRepartition.add(this.txtTPNbSem  , gbcRepar);
		gbcRepar.gridx++;
		gbcRepar.anchor = GridBagConstraints.LINE_START;
		panelRepartition.add(this.txtTPNbHeure, gbcRepar);
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
		panelRepartition.add(new JLabelModule(), gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txtHPTot, gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(new JLabelModule(), gbcRepar);
		gbcRepar.gridx ++;
        gbcRepar.insets.right = 10;
		panelRepartition.add(this.txtTot, gbcRepar);
        gbcRepar.insets.left = 2;



		// Quatrième ligne
		gbcRepar.anchor = GridBagConstraints.LINE_END;
        gbcRepar.insets = new Insets(2,2,2,2);
		gbcRepar.gridy++;
		gbcRepar.gridwidth = 3;
		gbcRepar.insets.top = 8;
		gbcRepar.gridx= 4;
		panelRepartition.add(new JLabelModule("Total promo (eqtd)"), gbcRepar);
		gbcRepar.gridx += 3;
		gbcRepar.gridwidth = 1;
		panelRepartition.add(this.txtCMTotEtd, gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txtTDTotEtd, gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txtTPTotEtd, gbcRepar);

        gbcRepar.insets.left = 20;
		gbcRepar.anchor = GridBagConstraints.LINE_END;
		gbcRepar.gridx ++;
		panelRepartition.add(new JLabelModule(), gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txtHPTotEtd, gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(new JLabelModule(), gbcRepar);
		gbcRepar.gridx ++;
        gbcRepar.insets.right = 10;
		panelRepartition.add(this.txtTotEtd, gbcRepar);
        gbcRepar.insets.left = 2;

		// Ciqnuième ligne
		gbcRepar.anchor = GridBagConstraints.LINE_END;
        gbcRepar.insets = new Insets(2,2,10,2);
		gbcRepar.gridy++;
		gbcRepar.gridwidth = 3;
		gbcRepar.gridx= 4;
		panelRepartition.add(new JLabelModule("Total affecté (eqtd)"), gbcRepar);
		gbcRepar.gridx += 3;
		gbcRepar.gridwidth = 1;
		panelRepartition.add(this.txtCMTotEtdAffect, gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txtTDTotEtdAffect, gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txtTPTotEtdAffect, gbcRepar);

        gbcRepar.insets.left = 20;
		gbcRepar.anchor = GridBagConstraints.LINE_END;
		gbcRepar.gridx ++;
		panelRepartition.add(new JLabelModule(), gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txtHPTotEtdAffect, gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(new JLabelModule(), gbcRepar);
		gbcRepar.gridx ++;
        gbcRepar.insets.right = 10;
		panelRepartition.add(this.txtTotEtdAffect, gbcRepar);



		// Table
		JPanel panelTable = new JPanel();
		panelTable.setLayout(new BorderLayout());
		this.tblGrilleDonnees = new JTable(new GrilleRessources(this.mod));
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

			if (component instanceof JButtonStyle) {
				((JButtonStyle) component).setBackground(c);
				((JButtonStyle) component).setPreferredSize(d);
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
	private static void activer(Container container, ActionListener a, FocusListener k) {
		for (Component component : container.getComponents()) {

			if (component instanceof JTextField && ((JTextField) component).isEditable()) {
				((JTextField) component).addFocusListener(k);
			}

			if (component instanceof JButtonStyle) {
				((JButtonStyle) component).addActionListener(a);
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

		this.focusLost(null);
	}


	private void quitter () {
		if(f != null)
		this.f.dispose();
		this.frame.changePanel(new PanelPrevi(this.frame));
		Controleur.getControleur().annuler();
	}


	private void sauvegarder () {

		if (this.txtCodeMod.getText().isEmpty()) {
			this.showMessageDialog("Le code est obligatoire");
			return;
		}

		boolean   val = this.cbValide      .isSelected();
		String    cod = this.txtCodeMod    .getText();
		String    liL = this.txtLibLongMod .getText();
		String    liC = this.txtLibCourtMod.getText();
		int       hp  = Integer.parseInt(this.txtHPTot.getText());

		HashMap <CategorieHeures, List<Integer>> map = new HashMap<>();
		

		//                                                   PN                                             SEMAINE                                      NB HEURE
		List<Integer> lstCM = new ArrayList<Integer>(List.of(Integer.parseInt(this.txtHeureCMPN.getText()), Integer.parseInt(this.txtCMNbSem.getText()), Integer.parseInt(this.txtCMNbHeure.getText())));
		List<Integer> lstTP = new ArrayList<Integer>(List.of(Integer.parseInt(this.txtHeureTPPN.getText()), Integer.parseInt(this.txtTPNbSem.getText()), Integer.parseInt(this.txtTPNbHeure.getText())));
		List<Integer> lstTD = new ArrayList<Integer>(List.of(Integer.parseInt(this.txtHeureTDPN.getText()), Integer.parseInt(this.txtTDNbSem.getText()), Integer.parseInt(this.txtTDNbHeure.getText())));

		map.put(Controleur.getControleur().getCategorieHeure("CM"), lstCM);
		map.put(Controleur.getControleur().getCategorieHeure("TP"), lstTP);
		map.put(Controleur.getControleur().getCategorieHeure("TD"), lstTD);

		System.out.println(this.estNouveau);

		if (this.estNouveau) {
			this.mod.setCode         (cod);
			this.mod.setLibLong      (liL);
			this.mod.setLibCourt     (liC);
			this.mod.setValide       (val);
			this.mod.setHeurePonctuel(hp );

			this.mod.setListCategorieHeure(List.of(Controleur.getControleur().getCategorieHeure("CM"),Controleur.getControleur().getCategorieHeure("TP"), Controleur.getControleur().getCategorieHeure("TD")));
			this.mod.setHeures(map);

			System.out.println("HASHMAP \n " + this.mod.getHeures() + " \n\n\n");
			System.out.println("LIST    \n " + this.mod.getListCategorieHeure() + " \n\n\n");

			if (Controleur.getControleur().ajouterModule(this.mod)) {
				Controleur.getControleur().enregistrer();
				this.quitter();
				return;
			}


		}else{
			if (Controleur.getControleur().modifModules(mod, cod, liL, liC, hp, val, map)) {
				Controleur.getControleur().enregistrer();
				this.quitter();
				return;
			}
		}
		
		this.showMessageDialog("Le code est déja utiliser");
	}


	private void ajouter () {

		this.mod.setCode(this.txtCodeMod.getText());

		if(f != null) return;
		f = new JFrame();
        f.add(new PanelAddRessourceIntervenant(this,this.frame, f,mod));
        f.setTitle("Ajout d'une affectation");
		f.pack();
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setAlwaysOnTop(true);
		f.setVisible(true);
	}


	private void supprimer() {
		int ind = this.tblGrilleDonnees.getSelectedRow();
		if(ind < 0){
			return ;
		}
		ArrayList<Affectations> list = (ArrayList<Affectations>) Controleur.getControleur().getAffectations(this.mod);
		Controleur.getControleur().supprimerAffectation(list.get(ind));
		if (ind >= 0)
			this.tblGrilleDonnees.setRowSelectionInterval(ind, ind);
		
		this.tblGrilleDonnees.setModel(new GrilleRessources(this.mod)); 
	}	

	private void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}


	public void focusLost(FocusEvent e) {

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

		int gpTd = Integer.parseInt(this.txtNbGpTd.getText());
		int gpTp = Integer.parseInt(this.txtNbGpTp.getText());

		float coefCM = Controleur.getControleur().getCategorieHeure("CM").getcoefCatHeur();
		float coefTD = Controleur.getControleur().getCategorieHeure("TD").getcoefCatHeur();
		float coefTP = Controleur.getControleur().getCategorieHeure("TP").getcoefCatHeur();
		float coefHP = Controleur.getControleur().getCategorieHeure("HP").getcoefCatHeur();



		/* CALCUL PN */

		// eqtd
		int cmPNetd = Math.round(cmPN * coefCM);
		int tdPNetd = Math.round(tdPN * coefTD) * gpTd;
		int tpPNetd = Math.round(tpPN * coefTP) * gpTp;
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
		int cmHTotEtd = Math.round((cmHeu * cmSem) * coefCM);
		int tpHTotEtd = Math.round((tpHeu * tpSem) * coefTD * gpTp);
		int tdHTotEtd = Math.round((tdHeu * tdSem) * coefTP * gpTd);
		int hpHTotEtd = Math.round( hpHeu          * coefHP * gpTd);

		this.txtCMTotEtd.setText( cmHTotEtd + "");
		this.txtTDTotEtd.setText( tdHTotEtd + "");
		this.txtTPTotEtd.setText( tpHTotEtd + "");
		this.txtHPTotEtd.setText( hpHTotEtd + "");

		this.txtTotEtd.setText((cmHTotEtd + tdHTotEtd + tpHTotEtd + hpHTotEtd) + "");

		// Affecté 
		int cmAffect = 0;
		int tdAffect = 0;
		int tpAffect = 0;
		int hpAffect = 0;


		for (Affectations a : this.mod.getLstAffectations()) {

			if (a.getCategorieHeures().getlibCatHeur().equals("CM"))
				cmAffect += a.getHeureEqtd();

			if (a.getCategorieHeures().getlibCatHeur().equals("TD"))
				tdAffect +=  a.getHeureEqtd();

			if (a.getCategorieHeures().getlibCatHeur().equals("TP"))
				tpAffect +=  a.getHeureEqtd();

			if (a.getCategorieHeures().getlibCatHeur().equals("HP"))
				hpAffect +=  a.getHeureEqtd();
		}

		this.txtCMTotEtdAffect.setText(cmAffect + "");
		this.txtTDTotEtdAffect.setText(tdAffect + "");
		this.txtTPTotEtdAffect.setText(tpAffect + "");
		this.txtHPTotEtdAffect.setText(hpAffect + "");

		this.txtTotEtdAffect.setText(cmAffect + tdAffect + tpAffect + hpAffect + "");
	}
	
	public void focusGained(FocusEvent e) {
	}

	public void annulerAjout(){
		this.f.dispose();
		this.f = null;
	}
}