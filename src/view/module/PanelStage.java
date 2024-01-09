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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;

import view.JTextFieldNumber;
import view.JLabelModule;
import view.accueil.FrameAccueil;
import view.previsionnel.PanelPrevi;
import view.JButtonStyle;
import controleur.*;
import model.Affectations;
import model.CategorieHeures;
import model.Semestres;
import model.modules.Module;
import model.modules.Stage;

public class PanelStage extends JPanel implements ActionListener, FocusListener{

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

	JTextFieldNumber txtHeureEtdREHPN;
	JTextFieldNumber txtHeureEtdhTutPN;
	private JTextFieldNumber txtHeureEtdSPN ;


	//Repartition
	private JTextFieldNumber txtREHTotEtd;
	private JTextFieldNumber txtREHTotEtdAffect;
	private JTextFieldNumber txthTutTotEtd;  
	private JTextFieldNumber txthTutTotEtdAffect;


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


	public PanelStage(FrameAccueil frame, Semestres semestres) {
		this.frame = frame;
		this.frame.setTitle("Astre - Stage");
		
		this.mod   = new Stage(semestres, "", "", "", 0, false);

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
	
	
	
	public PanelStage (FrameAccueil frame, Module m) {
		this.frame = frame;
		this.frame.setTitle("Astre - Previsionnel");

		this.mod = m;

		loadPage(m.getSemestres());

		//txtCode
		this.txtCodeMod    .setText(this.mod.getCode());
		this.txtLibLongMod .setText(this.mod.getLibLong());
		this.txtLibCourtMod.setText(this.mod.getLibCourt());



		//CM
		HashMap<CategorieHeures, List<Integer>> map = this.mod.getHeures();

		List<Integer> lst = map.get(Controleur.getControleur().getCategorieHeure("TUT"));
		if (lst != null) {
			this.txtHeureEtdhTutPN.setText(lst.get(0) + "");		
			this.txthTutTotEtd    .setText(lst.get(2) + "");
		}


		lst = map.get(Controleur.getControleur().getCategorieHeure("RHE"));
		if (lst != null) {
			this.txtHeureEtdREHPN.setText(lst.get(0) + "");		
			this.txtREHTotEtd    .setText(lst.get(2) + "");
		}

		this.cbValide.setSelected(this.mod.isValide());

		//Juste pour faire les calculs
		this.focusLost(null);


		this.estNouveau = false;

	}



	public void loadPage(Semestres semestres) {

        /*                         */
        /* CREATION DES COMPOSANTS */
        /*                         */

		//Informations Modules
        this.txtCodeMod     = new JTextField(5);
        this.txtLibLongMod  = new JTextField(25);
        this.txtLibCourtMod = new JTextField(10);
		this.cbValide       = new JCheckBox ("Validation");

		//Informations Semestres
        this.txtTypeMod = new JTextField("Stage", 8);
        this.txtSem     = new JTextField("S" + semestres.getNumSem(), 5);
		this.txtNbEtd   = new JTextFieldNumber("" + semestres.getNbEtdSem (), 3);
		this.txtNbGpTd  = new JTextFieldNumber("" + semestres.getNbGpTdSem(), 3);
		this.txtNbGpTp  = new JTextFieldNumber("" + semestres.getNbGpTpSem(), 3); 


		//Informations calcul heure PN
        this.txtHeureEtdREHPN   = new JTextFieldNumber("52", 3);
        this.txtHeureEtdhTutPN  = new JTextFieldNumber("0", 3);
        this.txtHeureEtdSPN     = new JTextFieldNumber("52", 3);

		//Information répartition
		this.txtREHTotEtd        = new JTextFieldNumber("0", 3); 
		this.txthTutTotEtd       = new JTextFieldNumber("0", 3);
		this.txtREHTotEtdAffect  = new JTextFieldNumber("3", 3); 
		this.txthTutTotEtdAffect = new JTextFieldNumber("0", 3);


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
        gbcPanelHaut.insets = new Insets(2,2,2,2);
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
		panelHeurePN.add(new JLabelModule("REH"), gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(new JLabelModule("h Tut"), gbcHeurePN);
		gbcHeurePN.insets = new Insets(2,40,10,10);
		gbcHeurePN.gridx++;
		panelHeurePN.add(new JLabelModule("∑"), gbcHeurePN);
		gbcHeurePN.anchor = GridBagConstraints.LINE_START;
		
		
		gbcHeurePN.insets = new Insets(2, 2, 10, 2);
		gbcHeurePN.gridx  = 0;
		gbcHeurePN.gridy++;
		panelHeurePN.add(new JLabelModule("Total (eqtd) promo"), gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(this.txtHeureEtdREHPN,gbcHeurePN);
		gbcHeurePN.gridx++;
		panelHeurePN.add(this.txtHeureEtdhTutPN,gbcHeurePN);
		gbcHeurePN.insets = new Insets(2,40,10,10);
		gbcHeurePN.gridx++;
		panelHeurePN.add(this.txtHeureEtdSPN,gbcHeurePN);


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
        gbcRepar.gridx = 3;
        gbcRepar.gridy = 0;
        gbcRepar.weightx = 1;
        gbcRepar.weighty = 1;
        gbcRepar.insets = new Insets(2,5,0,5);
		// Ajout première ligne
		panelRepartition.add(new JLabelModule("REH", JLabel.CENTER), gbcRepar);
		gbcRepar.gridx++;
		panelRepartition.add(new JLabelModule("h Tut", JLabel.CENTER), gbcRepar);
		gbcRepar.insets = new Insets(2,340,2,10);
		gbcRepar.gridx++;
		panelRepartition.add(new JLabelModule("∑", JLabel.CENTER), gbcRepar);


		// Deuxième ligne
		gbcRepar.insets = new Insets(2,2,2,2);
		gbcRepar.gridwidth = 3;
		gbcRepar.gridy++;
		gbcRepar.gridx= 0;
		gbcRepar.anchor = GridBagConstraints.LINE_END;
		panelRepartition.add(new JLabelModule("Total promo (eqtd)"), gbcRepar);
		gbcRepar.anchor = GridBagConstraints.CENTER;
		gbcRepar.gridx += 3;
		gbcRepar.gridwidth = 1;
		panelRepartition.add(this.txtREHTotEtd, gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txthTutTotEtd, gbcRepar);
		gbcRepar.insets = new Insets(2,340,2,10);
		gbcRepar.gridx++;
		panelRepartition.add(this.txtTotEtd, gbcRepar);

		// Troisième ligne
		gbcRepar.insets = new Insets(2,2,2,2);
		gbcRepar.gridwidth = 3;
		gbcRepar.gridy ++;
		gbcRepar.gridx = 0;
		gbcRepar.anchor = GridBagConstraints.LINE_END;
		panelRepartition.add(new JLabelModule("Total affecté (eqtd)"), gbcRepar);
		gbcRepar.anchor = GridBagConstraints.CENTER;
		gbcRepar.gridx += 3;
		gbcRepar.gridwidth = 1;
		panelRepartition.add(this.txtREHTotEtdAffect, gbcRepar);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txthTutTotEtdAffect, gbcRepar);
		gbcRepar.insets = new Insets(2,340,2,10);
		gbcRepar.gridx ++;
		panelRepartition.add(this.txtTotEtdAffect, gbcRepar);



		// Table
		JPanel panelTable = new JPanel();
		panelTable.setLayout(new BorderLayout());
		this.tblGrilleDonnees = new JTable(new GrilleStage(this.mod));
		this.tblGrilleDonnees.setFillsViewportHeight(true);

		JPanel panelAjoutSupp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelAjoutSupp.add(this.btnAjouter);
		panelAjoutSupp.add(this.btnSupprimer);

		this.tblGrilleDonnees.setPreferredScrollableViewportSize(new Dimension(650, 200));;

		JScrollPane spGrilleDonnees = new JScrollPane(this.tblGrilleDonnees);
		panelTable.add(spGrilleDonnees, BorderLayout.CENTER);
		panelTable.add(new JLabelModule("Affectation"), BorderLayout.NORTH);
		panelTable.add(panelAjoutSupp, BorderLayout.SOUTH);


		JPanel panelRepInt = new JPanel(new GridBagLayout());
		GridBagConstraints gpcPanRepInt = new GridBagConstraints();
        gpcPanRepInt.gridx = 0;
        gpcPanRepInt.gridy = 0;
        gpcPanRepInt.weightx = 1;
        gpcPanRepInt.weighty = 1;
        gpcPanRepInt.insets = new Insets(2, 2, 2, 2);
		gpcPanRepInt.anchor = GridBagConstraints.CENTER;
		panelRepInt.add(panelRepartition,gpcPanRepInt);

		JPanel panelRep = new JPanel(new BorderLayout());
		panelRep.add(new JLabelModule("Repartition",JLabel.CENTER), BorderLayout.NORTH);
		panelRep.add(panelRepInt, BorderLayout.CENTER);




		// Ajout des pannels au panel central
		JPanel panelCentre = new JPanel(new GridBagLayout());
		GridBagConstraints gbcCentre = new GridBagConstraints();
		gbcCentre.insets = new Insets(2,2,2,2);
		gbcCentre.weightx = 1;
		gbcCentre.weighty = 1;
		gbcCentre.gridx = 0;
		gbcCentre.gridy = 0;
		gbcCentre.anchor = GridBagConstraints.FIRST_LINE_START;
		gbcCentre.fill=GridBagConstraints.HORIZONTAL;
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

		this.txtTotEtd.setEditable(false);
		this.txtHeureEtdSPN.setEditable(false);

		this.txtREHTotEtdAffect.setEditable(false);
		this.txthTutTotEtdAffect.setEditable(false);
		this.txtTotEtdAffect.setEditable(false);


		// Alignement
		this.txtNbEtd .setHorizontalAlignment(JTextField.CENTER);
		this.txtNbGpTd.setHorizontalAlignment(JTextField.CENTER);
		this.txtNbGpTp.setHorizontalAlignment(JTextField.CENTER);
		PanelStage.aligner(panelCentre, JTextField.RIGHT);

		//Bordure et fond
		PanelStage.fond(this, Color.decode("0xD0D0D0"), new Dimension(120,20));
		panelHeurePN.setBorder(BorderFactory.createLineBorder(Color.decode("0xD0D0D0")));
		panelRepInt.setBorder(BorderFactory.createLineBorder(Color.decode("0xD0D0D0")));





        /*                      */
        /* STYLE DES COMPOSANTS */
        /*                      */
		PanelStage.activer(this, this, this);

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


	private void sauvegarder () {

		if (this.txtCodeMod.getText().isEmpty()) {
			this.showMessageDialog("Le code est obligatoire");
			return;
		}

		boolean   val = this.cbValide      .isSelected();
		String    cod = this.txtCodeMod    .getText();
		String    liL = this.txtLibLongMod .getText();
		String    liC = this.txtLibCourtMod.getText();

		HashMap <CategorieHeures, List<Integer>> map = new HashMap<>();
		

		//                                                    PN                                                 SEMAINE                                      NB HEURE
		List<Integer> lstTUT = new ArrayList<Integer>(List.of(Integer.parseInt(this.txtHeureEtdhTutPN.getText()), 1, Integer.parseInt(this.txthTutTotEtd.getText())));
		List<Integer> lstRHE = new ArrayList<Integer>(List.of(Integer.parseInt(this.txtHeureEtdREHPN .getText()), 1, Integer.parseInt(this.txtREHTotEtd.getText())));

		map.put(Controleur.getControleur().getCategorieHeure("TUT"), lstTUT);
		map.put(Controleur.getControleur().getCategorieHeure("REH"), lstRHE);

		if (this.estNouveau) {
			this.mod.setCode         (cod);
			this.mod.setLibLong      (liL);
			this.mod.setLibCourt     (liC);
			this.mod.setValide       (val);
			this.mod.initList        (map);

			this.mod.setHeurePonctuel(0 );

			if (Controleur.getControleur().ajouterModule(this.mod)) {
				Controleur.getControleur().enregistrer();
				this.quitter();
				return;
			}


		}else{

			System.out.println(map);

			if (Controleur.getControleur().modifModules(mod, cod, liL, liC, 0, val, map)) {
				Controleur.getControleur().enregistrer();
				this.quitter();
				return;
			}
		}
		
		this.showMessageDialog("Le code est déja utiliser");
	}

	private void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}



	private void quitter () {
		if (this.f != null)
		this.f.dispose();
		this.frame.changePanel(new PanelPrevi(frame));
		Controleur.getControleur().annuler();
	}


	private void ajouter () {
		if(f != null) return;
		f = new JFrame();
        f.add(new PanelAddSAEIntervenant(this,this.frame, f,mod));
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
		Controleur.getControleur().supprimerIntervenant(ind);
		if (ind >= 0)
			this.tblGrilleDonnees.setRowSelectionInterval(ind, ind);
		this.maj();
	}

    public void maj() {
		this.tblGrilleDonnees.setModel(new GrilleStage(this.mod)); 
	}



	public void focusLost(FocusEvent e) {

		int totPN = Integer.parseInt(this.txtHeureEtdREHPN.getText()) + Integer.parseInt(this.txtHeureEtdhTutPN.getText());
		this.txtHeureEtdSPN.setText(totPN + "");

		int totEqtd = Integer.parseInt(this.txthTutTotEtd.getText()) + Integer.parseInt(this.txtREHTotEtd.getText());
		this.txtTotEtd.setText(totEqtd + "");


		/* CALCUL REPARTITION */

		//EQTD
		int rehAffect = 0;
		int tutAffect = 0;

		float coefREH = Controleur.getControleur().getCategorieHeure("REH").getcoefCatHeur();
		float coefTUT = Controleur.getControleur().getCategorieHeure("TUT").getcoefCatHeur();


		for (Affectations a : this.mod.getLstAffectations()) {

			if (a.getCategorieHeures().getlibCatHeur().equals("REH"))
				rehAffect +=  a.getHeureEqtd();

			if (a.getCategorieHeures().getlibCatHeur().equals("TUT"))
				tutAffect +=  a.getHeureEqtd();
		}

		this.txtREHTotEtdAffect.setText( rehAffect + "");
		this.txthTutTotEtdAffect.setText( tutAffect + "");

		this.txtTotEtdAffect.setText((rehAffect + tutAffect) + "");



	}

	public void focusGained(FocusEvent e) {
	}

	public void annulerAjout(){
		this.f.dispose();
		this.f = null;
	}
}