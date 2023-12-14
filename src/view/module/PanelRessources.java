package view.module;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import view.accueil.FrameAccueil;
import view.previsionnel.PanelPrevi;

public class PanelRessources extends JPanel {
    
    private FrameAccueil frame;

    private JButton    btnAjouter,btnSupprimer,btnEnregistrer,btnAnnuler;
    private JLabel     typeModuleT,semestreT,codeT,libLongT,libCourtT;
    private JTextField typeModule,semestre,code,libLong,libCourt, nbEtd,nbGpTD,nbGpTP;
    private JPanel panelGauche;
    private JPanel panelDroit;

    private JTable tblGrilleDonnees;
    
    public PanelRessources(FrameAccueil frame){
        
        //Création style
        Font styleLib = new Font("Arial", Font.PLAIN,11 );
        
        
        
        // Frame
        this.frame = frame;
        frame.setTitle("Astre - Prévisionnel - Module");
		frame.setMinimumSize(new Dimension(900, 500));

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
        
        this.typeModule = new JTextField("Ressource");
        this.semestre   = new JTextField("S1");
        this.code       = new JTextField("R1.01");
        this.libLong    = new JTextField("Initiation au développement");
        this.libCourt   = new JTextField("Init dev");

        this.nbEtd    = new JTextField("85");
        this.nbGpTD   = new JTextField("4");
        this.nbGpTP   = new JTextField("7");

        this.panelGauche = new JPanel();
        this.panelDroit  = new JPanel();
        this.typeModuleT.setFont(styleLib); // Taille et style de police
        this.semestreT  .setFont(styleLib);
        this.codeT      .setFont(styleLib);
        this.libLongT   .setFont(styleLib);
        this.libCourtT  .setFont(styleLib);
        this.typeModule.setPreferredSize(new Dimension(200, 60));
        this.semestre  .setPreferredSize(new Dimension(200, 50));
        this.nbEtd     .setPreferredSize(new Dimension(200, 100));
        this.nbGpTD    .setPreferredSize(new Dimension(200, 100));
        this.nbGpTP    .setPreferredSize(new Dimension(200, 100));

        this.typeModule.setEditable(false);
        this.semestre  .setEditable(false);
        this.nbEtd     .setEditable(false);
        this.nbGpTD    .setEditable(false);
        this.nbGpTP    .setEditable(false);
        // Layout
        this.setLayout(new GridLayout(1,2,10,10));
        this.panelGauche.setLayout(new GridLayout(8,1));
        this.panelDroit.setLayout(new GridLayout(2,1));

        //Positionnement
		

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4; // Étendre sur deux colonnes
        gbc.anchor = GridBagConstraints.CENTER; // Aligner au centre
        gbc.insets = new Insets(20, 10, 20, 10); // Marges

        panelInfo.add(this.typeModuleT);
        panelInfo.add(this.semestreT);
        panelInfo.add(this.codeT);
        panelInfo.add(this.libLongT);
        panelInfo.add(this.libCourtT);

        panelInfo.add(this.typeModule);
        panelInfo.add(this.semestre);
        panelInfo.add(this.code);
        panelInfo.add(this.libLong);
        panelInfo.add(this.libCourt);
        this.panelGauche.add(panelInfo);

        JPanel panelNombre = new JPanel();
        panelNombre.setLayout(new GridLayout(2,3));
        panelNombre.add(new JLabel("nb Etd"));
        panelNombre.add(new JLabel("nb gp TD"));
        panelNombre.add(new JLabel("nb gp TP"));

        panelNombre.add(this.nbEtd);
        panelNombre.add(this.nbGpTD);
        panelNombre.add(this.nbGpTP);
        this.panelGauche.add(panelNombre);

        this.panelGauche.add(new JLabel("PN local(nb h tot/etd)"));


        JPanel panelPnLocal = new JPanel();
        panelPnLocal.setLayout(new GridLayout(3,5));
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
        panelRépartition.setLayout(new GridLayout(3,5));
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
        panelRépartition2.setLayout(new GridLayout(4,6));
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
        panelBouton.setLayout(new GridLayout(2,2));
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

        this.btnAnnuler.addActionListener((e)->this.frame.changePanel(new PanelPrevi(this.frame)));
    }
}