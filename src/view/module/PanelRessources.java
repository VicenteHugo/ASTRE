package view.module;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

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

import view.Intervenant.PanelAddIntervenant;
import view.accueil.FrameAccueil;
import view.previsionnel.PanelPrevi;

public class PanelRessources extends JPanel {

	private FrameAccueil frame;

    private JButton    btnAjouter,btnSupprimer,btnEnregistrer,btnAnnuler;
    private JLabel     typeModuleT,semestreT,codeT,libLongT,libCourtT;
    private JTextField typeModule,semestre,code,libLong,libCourt, nbEtd,nbGpTD,nbGpTP;
    private JPanel panelHaut, panelBas;

    private JTable tblGrilleDonnees;
    
    public PanelRessources(FrameAccueil frame){
        
        //Création style
        Font styleLib = new Font("Arial", Font.PLAIN,11 );// Taille et style de police
        UIManager.put("Label.font", styleLib); //Pour tout les labels
        
        
        // Frame
        this.frame = frame;
        frame.setTitle("Astre - Prévisionnel - Module");
		frame.setMinimumSize(new Dimension(1200, 600));

        // Creation Composants
        this.btnAjouter     = new JButton("Ajouter");
        this.btnSupprimer   = new JButton("Supprimer");
        this.btnEnregistrer = new JButton("Enregistrer");
        this.btnAnnuler     = new JButton("Annuler");
        
        this.typeModuleT = new JLabel("type module");
        this.semestreT   = new JLabel("semestre");
        this.codeT       = new JLabel("code");
        this.libLongT    = new JLabel("libellé long");
        this.libCourtT   = new JLabel("libellé court");
        
        this.typeModule = new JTextField("Ressource",9);
        this.semestre   = new JTextField("S1",8);
        this.code       = new JTextField("R1.01",6);
        this.libLong    = new JTextField("Initiation au développement",30);
        this.libCourt   = new JTextField("Init dev",10);

        this.nbEtd    = new JTextField("85",3);
        this.nbGpTD   = new JTextField("4",3);
        this.nbGpTP   = new JTextField("7",3);

        this.panelHaut = new JPanel();
        this.panelBas  = new JPanel();        

        this.typeModule.setEditable(false);// non editable
        this.semestre  .setEditable(false);
        this.nbEtd     .setEditable(false);
        this.nbGpTD    .setEditable(false);
        this.nbGpTP    .setEditable(false);
		
		this.nbEtd     .setHorizontalAlignment(JTextField.CENTER);//centrer le texte
        this.nbGpTD    .setHorizontalAlignment(JTextField.CENTER);
        this.nbGpTP    .setHorizontalAlignment(JTextField.CENTER);
        // Layout
        this.setLayout(new GridLayout(2, 1, 0, 0));
		this.panelHaut.setLayout(new GridLayout(2, 1,0,0));
		this.panelBas.setLayout(new BorderLayout());

		// Positionnement

		Border outline = BorderFactory.createLineBorder(Color.black);


        JPanel panelInfo = new JPanel();
		panelInfo.setBorder(null);
        panelInfo.setLayout(new GridBagLayout());
        GridBagConstraints cGeneral = new GridBagConstraints();//constraintsGeneraux modifiable
        cGeneral.anchor = GridBagConstraints.LINE_START;
		cGeneral.gridx = 0;
		cGeneral.gridy = 0;
		cGeneral.insets = new java.awt.Insets(5, 0, 5, 5);

		
        panelInfo.add(this.typeModuleT,cGeneral);
		cGeneral.gridx ++;
        panelInfo.add(this.semestreT,cGeneral);
		cGeneral.gridx ++;
        panelInfo.add(this.codeT,cGeneral);
		cGeneral.gridx ++;
        panelInfo.add(this.libLongT,cGeneral);
		cGeneral.gridx ++;
        panelInfo.add(this.libCourtT,cGeneral);
		
		cGeneral.gridx = 0;
		cGeneral.gridy ++;
        panelInfo.add(this.typeModule,cGeneral);
		
		cGeneral.gridx ++;
        panelInfo.add(this.semestre,cGeneral);
		
		cGeneral.gridx ++;
        panelInfo.add(this.code,cGeneral);
		
		cGeneral.gridx ++;
        panelInfo.add(this.libLong,cGeneral);
		
		cGeneral.gridx ++;
        panelInfo.add(this.libCourt,cGeneral);

        cGeneral.gridy++;
        cGeneral.gridx = 0;
        panelInfo.add(new JLabel("nb Etd"), cGeneral);
        cGeneral.gridx++;
		panelInfo.add(new JLabel("nb gp TD"), cGeneral);
        cGeneral.gridx++;
		panelInfo.add(new JLabel("nb gp TP"), cGeneral);

		cGeneral.gridy++;
        cGeneral.gridx = 0;
        panelInfo.add(this.nbEtd, cGeneral);
        cGeneral.gridx++;
		panelInfo.add(this.nbGpTD, cGeneral);
        cGeneral.gridx++;
		panelInfo.add(this.nbGpTP, cGeneral);

		cGeneral.gridx =5;
        panelInfo.add(new JLabel("Répartition"), cGeneral);
		panelInfo.setBorder(outline);
		this.panelHaut.setBorder(outline);
        this.panelHaut.add(panelInfo);

		//PN local
        JPanel panelInfo2 = new JPanel();
        panelInfo2.setLayout(new GridBagLayout());
        JPanel panelPnLocal = new JPanel();
		JPanel panelPnLocalTab = new JPanel();
		panelPnLocal.setLayout(new GridBagLayout());
		panelPnLocalTab.setLayout(new GridBagLayout());
        panelPnLocalTab.setBorder(outline);
		GridBagConstraints gbcTab   = new GridBagConstraints();
		cGeneral.anchor = GridBagConstraints.CENTER;
		cGeneral.gridx   = 0;
		cGeneral.gridy   = 0;
		gbcTab.gridx   = 1;
		gbcTab.gridy   = 0;

		gbcTab.insets = new java.awt.Insets(5, 5, 5, 5);
		
		//Première Ligne (entetes)
		panelPnLocalTab.add(new JLabel("CM"), gbcTab);
        gbcTab.gridx++;
		panelPnLocalTab.add(new JLabel("TD"), gbcTab);
        gbcTab.gridx++;
		panelPnLocalTab.add(new JLabel("TP"), gbcTab);
        gbcTab.gridx++;
		panelPnLocalTab.add(new JLabel("∑"), gbcTab);

		//Deuxième ligne
        gbcTab.gridx = 1;
		gbcTab.gridy++;
		panelPnLocalTab.add(new JTextField("6",3), gbcTab);
        gbcTab.gridx++;
		panelPnLocalTab.add(new JTextField("65",3), gbcTab);
        gbcTab.gridx++;
		panelPnLocalTab.add(new JTextField("28",3), gbcTab);
        gbcTab.gridx++;
		panelPnLocalTab.add(new JTextField("99",3), gbcTab);

        //Troisième ligne
		gbcTab.gridx = 0;
		gbcTab.gridy++;
		panelPnLocalTab.add(new JLabel("Total (eqtd) promo"), gbcTab);
        gbcTab.gridx++;
		panelPnLocalTab.add(new JTextField("9",3), gbcTab);
        gbcTab.gridx++;
		panelPnLocalTab.add(new JTextField("260",3), gbcTab);
        gbcTab.gridx++;
		panelPnLocalTab.add(new JTextField("196",3), gbcTab);
        gbcTab.gridx++;
		panelPnLocalTab.add(new JTextField("465",3), gbcTab);

		panelPnLocal.add(new JLabel("PN local(nb h tot/etd)"), cGeneral);
		cGeneral.gridy++;
		panelPnLocal.add(panelPnLocalTab, cGeneral);
		

		//PanelRepartition
        JPanel PanelRepartition = new JPanel();
        PanelRepartition.setLayout(new GridBagLayout());
        
		JPanel panelSemaine = new JPanel();
		panelSemaine.setLayout(new GridBagLayout());
		cGeneral.gridx   = 0;
		cGeneral.gridy   = 0;
		cGeneral.insets = new java.awt.Insets(5, 5, 0, 5);

		cGeneral.gridwidth = 2;
		panelSemaine.add(new JLabel("CM"), cGeneral);
        cGeneral.gridx +=2;
		panelSemaine.add(new JLabel("TD"), cGeneral);
		cGeneral.gridx +=2;
		panelSemaine.add(new JLabel("TP"), cGeneral);

		cGeneral.gridwidth = 1;
		cGeneral.gridx = 0;
		cGeneral.gridy = 1;
        panelSemaine.add(new JLabel("nb Sem"), cGeneral);
        cGeneral.gridx ++;
		panelSemaine.add(new JLabel("nb h/sem"), cGeneral);
        cGeneral.gridx ++;
		panelSemaine.add(new JLabel("nb Sem"), cGeneral);
        cGeneral.gridx ++;
		panelSemaine.add(new JLabel("nb h/sem"), cGeneral);
        cGeneral.gridx ++;
		panelSemaine.add(new JLabel("nb Sem"), cGeneral);
        cGeneral.gridx ++;
		panelSemaine.add(new JLabel("nb h/sem"), cGeneral);

		cGeneral.gridx = 0;
		cGeneral.gridy = 2;
        panelSemaine.add(new JTextField("6",3), cGeneral);
        cGeneral.gridx++;
		panelSemaine.add(new JTextField("1",3), cGeneral);
        cGeneral.gridx++;
		panelSemaine.add(new JTextField("14",3), cGeneral);
        cGeneral.gridx++;
		panelSemaine.add(new JTextField("4",3), cGeneral);
        cGeneral.gridx++;
		panelSemaine.add(new JTextField("14",3), cGeneral);
        cGeneral.gridx++;
		panelSemaine.add(new JTextField("2",3), cGeneral);
		panelSemaine.setBorder(outline);
		
		cGeneral.gridx = 0;
		cGeneral.gridy = 0;
		cGeneral.gridwidth =7;
		cGeneral.gridheight=2;

		PanelRepartition.add(panelSemaine,cGeneral);
		cGeneral.gridheight=1;
		cGeneral.gridwidth =1;
        cGeneral.gridx = 7;


		PanelRepartition.add(new JLabel("CM"), cGeneral);
        cGeneral.gridx++;
		PanelRepartition.add(new JLabel("TD"), cGeneral);
        cGeneral.gridx++;
		PanelRepartition.add(new JLabel("TP"), cGeneral);
        cGeneral.gridx++;
		PanelRepartition.add(new JLabel("heure ponctuelle"), cGeneral);
        cGeneral.gridx++;
		PanelRepartition.add(new JLabel("∑"), cGeneral);

		cGeneral.gridy++;
		cGeneral.gridx = 7;
		PanelRepartition.add(new JTextField("6",3), cGeneral);
        cGeneral.gridx++;
		PanelRepartition.add(new JTextField("56",3), cGeneral);
        cGeneral.gridx++;
		PanelRepartition.add(new JTextField("28",3), cGeneral);
        cGeneral.gridx++;
		PanelRepartition.add(new JTextField("9",3), cGeneral);
        cGeneral.gridx++;
		PanelRepartition.add(new JTextField("99",3), cGeneral);

        cGeneral.gridy++;
		cGeneral.gridx = 7;
		PanelRepartition.add(new JLabel("Total promo(eqtd)"), cGeneral);
        cGeneral.gridx++;
		PanelRepartition.add(new JTextField("9",3), cGeneral);
        cGeneral.gridx++;
		PanelRepartition.add(new JTextField("224",3), cGeneral);
        cGeneral.gridx++;
		PanelRepartition.add(new JTextField("196",3), cGeneral);
        cGeneral.gridx++;
		PanelRepartition.add(new JTextField("36",3), cGeneral);
        cGeneral.gridx++;
		PanelRepartition.add(new JTextField("465",3), cGeneral);

        cGeneral.gridy++;
		cGeneral.gridx = 7;
		PanelRepartition.add(new JLabel("Total affecté(eqtd)"), cGeneral);
        cGeneral.gridx++;
		PanelRepartition.add(new JTextField("9",3), cGeneral);
        cGeneral.gridx++;
		PanelRepartition.add(new JTextField("224",3), cGeneral);
        cGeneral.gridx++;
		PanelRepartition.add(new JTextField("168",3), cGeneral);
        cGeneral.gridx++;
		PanelRepartition.add(new JTextField("36",3), cGeneral);
        cGeneral.gridx++;
		PanelRepartition.add(new JTextField("437",3), cGeneral);

		cGeneral.gridx = 0;
		cGeneral.gridy = 0;
		
		panelInfo2.add(panelPnLocal,cGeneral);
		cGeneral.gridx ++;
        panelInfo2.add(PanelRepartition,cGeneral);

        this.panelHaut.add(panelInfo2);


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
			f.add(new PanelAddRessourceIntervenant(this.frame, f));
			f.setTitle("Affecter un Intervenant");
			f.pack();
			f.setLocationRelativeTo(null);
			f.setAlwaysOnTop(true);
			f.setVisible(true);
		});
	}
}