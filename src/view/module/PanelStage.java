package view.module;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import view.accueil.FrameAccueil;
import view.previsionnel.PanelPrevi;
import controleur.*;

public class PanelStage extends JPanel {

	private FrameAccueil frame;

    private JButton    btnAjouter,btnSupprimer,btnEnregistrer,btnAnnuler;
    private JLabel     lblTypeModuleT,lblSemestreT,lblCodeT,lblLongT,lblCourtT;
    private JTextField txtTypeModule,txtSemestre,txtCode,txtLong,txtCourt, nbEtd,nbGpTD,nbGpTP;
	private JCheckBox  checkBoxValid;
    
    private JPanel panelHaut, panelBas;

    private JTable tblGrilleDonnees;
    
    public PanelStage(FrameAccueil frame){
        
        //Création style
        Font styleLib = new Font("Arial", Font.PLAIN,11 );// Taille et style de police
        UIManager.put("Label.font", styleLib); //Pour tout les labels

		Border outline = BorderFactory.createLineBorder(Color.GRAY);
        
        
        // Frame
        this.frame = frame;
        this.frame.setTitle("Astre - Prévisionnel - Module");
		this.frame.setMinimumSize(new Dimension(1200, 700));
		this.frame.setResizable(true);

        // Creation Composants
        this.btnAjouter     = new JButton("Ajouter");
        this.btnSupprimer   = new JButton("Supprimer");
        this.btnEnregistrer = new JButton("Enregistrer");
        this.btnAnnuler     = new JButton("Annuler");
        
        this.lblTypeModuleT = new JLabel("type module");
        this.lblSemestreT   = new JLabel("semestre");
        this.lblCodeT       = new JLabel("code");
        this.lblLongT       = new JLabel("libellé long");
        this.lblCourtT      = new JLabel("libellé court");
        
        this.txtTypeModule = new JTextField("Stage/Suivi",9);
        this.txtSemestre   = new JTextField("S4",8);
        this.txtCode       = new JTextField("S4.ST",6);
        this.txtLong       = new JTextField("Stage",30);
        this.txtCourt      = new JTextField("Stage",10);

        this.nbEtd    = new JTextField("52",3);
        this.nbGpTD   = new JTextField("2",3);
        this.nbGpTP   = new JTextField("4",3);

		this.checkBoxValid = new JCheckBox("Validation");

        this.panelHaut = new JPanel();
        this.panelBas  = new JPanel();        

        this.txtTypeModule.setEditable(false);// non editable
        this.txtSemestre  .setEditable(false);
        this.nbEtd        .setEditable(false);
        this.nbGpTD       .setEditable(false);
        this.nbGpTP       .setEditable(false);
		
		this.nbEtd     .setHorizontalAlignment(JTextField.CENTER);//centrer le texte
        this.nbGpTD    .setHorizontalAlignment(JTextField.CENTER);
        this.nbGpTP    .setHorizontalAlignment(JTextField.CENTER);
        // Layout
        this.setLayout(new GridLayout(2, 1, 0, 0));
		this.panelHaut.setLayout(new FlowLayout(FlowLayout.LEFT,10,0));
		this.panelBas.setLayout(new BorderLayout());

		// Positionnement


		JPanel panelInfo    = new JPanel();
        JPanel panelInfoTab = new JPanel();
		panelInfo.setLayout(new GridBagLayout());
        panelInfoTab.setLayout(new GridBagLayout());
        GridBagConstraints cGeneral = new GridBagConstraints();//constraintsGeneraux modifiable
        cGeneral.anchor = GridBagConstraints.LINE_START;
		cGeneral.gridx = 0;
		cGeneral.gridy = 0;
		cGeneral.insets = new java.awt.Insets(5, 0, 5, 5);

		
        panelInfoTab.add(this.lblTypeModuleT,cGeneral);
		cGeneral.gridx ++;
        panelInfoTab.add(this.lblSemestreT,cGeneral);
		cGeneral.gridx ++;
        panelInfoTab.add(this.lblCodeT,cGeneral);
		cGeneral.gridx ++;
        panelInfoTab.add(this.lblLongT,cGeneral);
		cGeneral.gridx ++;
        panelInfoTab.add(this.lblCourtT,cGeneral);
		
		cGeneral.gridx = 0;
		cGeneral.gridy ++;
        panelInfoTab.add(this.txtTypeModule,cGeneral);
		
		cGeneral.gridx ++;
        panelInfoTab.add(this.txtSemestre,cGeneral);
		
		cGeneral.gridx ++;
        panelInfoTab.add(this.txtCode,cGeneral);
		
		cGeneral.gridx ++;
        panelInfoTab.add(this.txtLong,cGeneral);
		
		cGeneral.gridx ++;
        panelInfoTab.add(this.txtCourt,cGeneral);

        cGeneral.gridy++;
        cGeneral.gridx = 0;
        panelInfoTab.add(new JLabel("nb Etd"), cGeneral);
        cGeneral.gridx++;
		panelInfoTab.add(new JLabel("nb gp TD"), cGeneral);
        cGeneral.gridx++;
		panelInfoTab.add(new JLabel("nb gp TP"), cGeneral);

		cGeneral.gridy++;
        cGeneral.gridx = 0;
        panelInfoTab.add(this.nbEtd, cGeneral);
        cGeneral.gridx++;
		panelInfoTab.add(this.nbGpTD, cGeneral);
        cGeneral.gridx++;
		panelInfoTab.add(this.nbGpTP, cGeneral);
		panelInfo.add(panelInfoTab);

		cGeneral.gridx = 0;
		cGeneral.gridy = 0;
        cGeneral.anchor = GridBagConstraints.WEST;
        panelInfo.add(panelInfoTab,cGeneral);

		//PN local
        JPanel panelInfoTab2 = new JPanel();
        panelInfoTab2.setLayout(new GridBagLayout());
        JPanel panelPnLocal = new JPanel();
		JPanel panelPnLocalTab = new JPanel();
		panelPnLocal.setLayout(new GridBagLayout());
		panelPnLocalTab.setLayout(new GridBagLayout());
        panelPnLocalTab.setBorder(outline);
		GridBagConstraints gbcTab   = new GridBagConstraints();
		cGeneral.anchor = GridBagConstraints.CENTER;
		cGeneral.gridx  = 0;
		cGeneral.gridy  = 0;
		gbcTab.gridx    = 1;
		gbcTab.gridy    = 0;

		gbcTab.insets = new java.awt.Insets(5, 5, 5, 5);
		
		//Première Ligne (entetes)
		panelPnLocalTab.add(new JLabel("REH"), gbcTab);
        gbcTab.gridx++;
		panelPnLocalTab.add(new JLabel("h Tut"), gbcTab);
        gbcTab.gridx++;
		panelPnLocalTab.add(new JLabel("∑"), gbcTab);

		//Deuxième ligne
		gbcTab.gridx = 0;
		gbcTab.gridy++;
		panelPnLocalTab.add(new JLabel("Total (eqtd) promo"), gbcTab);
        gbcTab.gridx++;
		panelPnLocalTab.add(new JTextField("52",3), gbcTab);
        gbcTab.gridx++;
		panelPnLocalTab.add(new JTextField("0",3), gbcTab);
        gbcTab.gridx++;
		panelPnLocalTab.add(new JTextField("52",3), gbcTab);

		panelPnLocal.add(new JLabel("PN local(nb h tot/etd)"), cGeneral);
		cGeneral.gridy++;
		panelPnLocal.add(panelPnLocalTab, cGeneral);
		

		//PanelRepartition
        JPanel PanelRepartition = new JPanel();
		JPanel PanelRepartitionTab = new JPanel();
        PanelRepartition.setLayout(new GridBagLayout());
		PanelRepartitionTab.setLayout(new GridBagLayout());
        PanelRepartitionTab.setBorder(outline);
		JPanel panelHeure = new JPanel();
		panelHeure.setLayout(new GridBagLayout());
		cGeneral.gridx   = 1;
		cGeneral.gridy   = 0;
		cGeneral.insets = new java.awt.Insets(5, 5, 0, 5);

		panelHeure.add(new JLabel("REH"), cGeneral);
        cGeneral.gridx ++;
		panelHeure.add(new JLabel("h Tut"), cGeneral);
		cGeneral.gridx ++;
		panelHeure.add(new JLabel("∑"), cGeneral);

		cGeneral.gridx = 0;
		cGeneral.gridy = 1;
        panelHeure.add(new JLabel("Total promo (eqtd)"), cGeneral);
        cGeneral.gridx ++;
		panelHeure.add(new JTextField("52",3), cGeneral);
        cGeneral.gridx ++;
		panelHeure.add(new JTextField("",3), cGeneral);
        cGeneral.gridx ++;
		panelHeure.add(new JTextField("52",3), cGeneral);
        cGeneral.gridx ++;

		cGeneral.gridx = 0;
		cGeneral.gridy = 2;
        panelHeure.add(new JLabel("Total affecté (eqtd)"), cGeneral);
        cGeneral.gridx++;
		panelHeure.add(new JTextField("3",3), cGeneral);
        cGeneral.gridx++;
		panelHeure.add(new JTextField("0",3), cGeneral);
        cGeneral.gridx++;
		panelHeure.add(new JTextField("3",3), cGeneral);
		
		cGeneral.gridx = 0;
		cGeneral.gridy = 0;
		cGeneral.gridwidth =7;
		cGeneral.gridheight=2;

		PanelRepartitionTab.add(panelHeure,cGeneral);
		cGeneral.gridheight=1;
		cGeneral.gridwidth =1;
        
		cGeneral.gridx = 0;
		cGeneral.gridy = 0;
		PanelRepartition.add(new JLabel("Répartition"), cGeneral);
		cGeneral.gridy ++;
		PanelRepartition.add(PanelRepartitionTab, cGeneral);
		
		cGeneral.gridx = 0;
		cGeneral.gridy = 0;
		cGeneral.gridheight = 2;
		panelInfoTab2.add(panelPnLocal,cGeneral);
		cGeneral.gridx ++;
		cGeneral.gridheight = 4;
        panelInfoTab2.add(PanelRepartition,cGeneral);
		cGeneral.gridx = 0;
		cGeneral.gridy = 6;
		cGeneral.gridheight = 1;
		panelInfoTab2.add(this.checkBoxValid,cGeneral);
		
		cGeneral.gridx = 0;
		cGeneral.gridy = 1;
        cGeneral.anchor = GridBagConstraints.WEST;
		
        panelInfo.add(panelInfoTab2,cGeneral);
        this.panelHaut.add(panelInfo);


        JPanel panelTable = new JPanel();
        panelTable.setLayout(new BorderLayout());
        panelTable.add(new JLabel("Affectation"), BorderLayout.NORTH);

        this.tblGrilleDonnees = new JTable(new GrilleRessources());
		this.tblGrilleDonnees.setFillsViewportHeight(true);

		JScrollPane spGrilleDonnees = new JScrollPane(this.tblGrilleDonnees);
		panelTable.add(spGrilleDonnees, BorderLayout.CENTER);

        JPanel panelBouton = new JPanel();
        panelBouton.add(this.btnAjouter);
        panelBouton.add(this.btnSupprimer);

        panelTable.add(panelBouton,BorderLayout.SOUTH);

        this.panelBas.add(panelTable, BorderLayout.CENTER);

        JPanel panelBouton2 = new JPanel();
        panelBouton.add(this.btnAnnuler);
        panelBouton.add(this.btnEnregistrer);

        this.panelBas.add(panelBouton2,BorderLayout.SOUTH);

		this.add(panelHaut);
		this.add(panelBas);

		this.btnAnnuler.addActionListener((e) -> this.frame.changePanel(new PanelPrevi(this.frame)));
        this.btnAjouter.addActionListener((e)->{
			JFrame f = new JFrame();
			f.add(new PanelAddSAEIntervenant(this.frame, f));
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