package view.module;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import view.accueil.FrameAccueil;
import view.previsionnel.PanelPrevi;

public class PanelSAE extends JPanel {
    
    private FrameAccueil frame;

    private JButton btnAjouter;
    private JButton btnSupprimer;
    private JButton btnEnregistrer;
    private JButton btnAnnuler;

    private JPanel panelGauche;
    private JPanel panelDroit;

    private JTable tblGrilleDonnees;
    
    public PanelSAE(FrameAccueil frame){
        
        // Frame
        this.frame = frame;
        frame.setTitle("Astre - Prévisionnel - Module");
		frame.setMinimumSize(new Dimension(900, 500));

        // Composants
        this.btnAjouter     = new JButton("Ajouter");
        this.btnSupprimer   = new JButton("Supprimer");
        this.btnEnregistrer = new JButton("Enregistrer");
        this.btnAnnuler     = new JButton("Annuler");

        this.panelGauche = new JPanel();
        this.panelDroit  = new JPanel();

        // Layout
        this.setLayout(new GridLayout(1,2,10,10));
        this.panelGauche.setLayout(new GridLayout(7,1));
        this.panelDroit.setLayout(new GridLayout(2,1));

        //Positionnement
		

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(2,5));
        panelInfo.add(new JLabel("type module"));
        panelInfo.add(new JLabel("semestre"));
        panelInfo.add(new JLabel("code"));
        panelInfo.add(new JLabel("libellé long"));
        panelInfo.add(new JLabel("libellé court"));

        panelInfo.add(new JTextField("SAE"));
        panelInfo.add(new JTextField("S3"));
        panelInfo.add(new JTextField("S3.01"));
        panelInfo.add(new JTextField("Développement d'une application"));
        panelInfo.add(new JTextField("Dev appli"));
        this.panelGauche.add(panelInfo);

        JPanel panelNombre = new JPanel();
        panelNombre.setLayout(new GridLayout(2,3));
        panelNombre.add(new JLabel("nb Etd"));
        panelNombre.add(new JLabel("nb gp TD"));
        panelNombre.add(new JLabel("nb gp TP"));

        panelNombre.add(new JTextField("52"));
        panelNombre.add(new JTextField("2"));
        panelNombre.add(new JTextField("4"));
        this.panelGauche.add(panelNombre);

        this.panelGauche.add(new JLabel("PN local(nb h tot/etd)"));


        JPanel panelPnLocal = new JPanel();
        panelPnLocal.setLayout(new GridLayout(2,4));
        panelPnLocal.add(new JLabel());
        panelPnLocal.add(new JLabel("h Sae"));
        panelPnLocal.add(new JLabel("h Tut"));
        panelPnLocal.add(new JLabel("∑"));

        panelPnLocal.add(new JLabel("Total (eqtd) promo"));
        panelPnLocal.add(new JTextField("40"));
        panelPnLocal.add(new JTextField("38"));
        panelPnLocal.add(new JTextField("78"));

        this.panelGauche.add(panelPnLocal);
        this.panelGauche.add(new JLabel("Répartition"));

        JPanel panelRépartition = new JPanel();
        panelRépartition.setLayout(new GridLayout(3,3));
        panelRépartition.add(new JLabel());
        panelRépartition.add(new JLabel("h Sae"));
        panelRépartition.add(new JLabel("h Tut"));
        panelRépartition.add(new JLabel("∑"));

        panelRépartition.add(new JLabel("total promo (eqdt)"));
        panelRépartition.add(new JTextField("40"));
        panelRépartition.add(new JTextField("38"));
        panelRépartition.add(new JTextField("78"));

        panelRépartition.add(new JLabel("total affecté (eqdt)"));
        panelRépartition.add(new JTextField("40"));
        panelRépartition.add(new JTextField("38"));
        panelRépartition.add(new JTextField("78"));

        this.panelGauche.add(panelRépartition);

        this.tblGrilleDonnees = new JTable(new GrilleSAE());
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