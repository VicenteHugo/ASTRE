package view.module;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import view.accueil.FrameAccueil;

public class PanelRessources extends JPanel {
    
    private FrameAccueil frame;

    private JButton btnAjouter;
    private JButton btnSupprimer;
    private JButton btnEnregistrer;
    private JButton btnAnnuler;

    private JTable tblGrilleDonnees;
    
    public PanelRessources(FrameAccueil frame){
        
        // Frame
        this.frame = frame;
        frame.setTitle("Astre - Prévisionnel - Module");
		frame.setMinimumSize(new Dimension(700, 400));

        // Composants
        this.btnAjouter     = new JButton("Ajouter");
        this.btnSupprimer   = new JButton("Supprimer");
        this.btnEnregistrer = new JButton("Enregistrer");
        this.btnAnnuler     = new JButton("Annuler");

        // Layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;

        //Positionnement
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(2,5));
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
        this.add(panelInfo, gbc);

        gbc.gridy++;

        JPanel panelNombre = new JPanel();
        panelNombre.setLayout(new GridLayout(2,3));
        panelNombre.add(new JLabel("nb Etd"));
        panelNombre.add(new JLabel("nb gp TD"));
        panelNombre.add(new JLabel("nb gp TP"));

        panelNombre.add(new JTextField("85"));
        panelNombre.add(new JTextField("4"));
        panelNombre.add(new JTextField("7"));
        this.add(panelNombre, gbc);

        gbc.gridy++;
        this.add(new JLabel("PN local(nb h tot/etd)"));
        gbc.gridy++;

        JPanel panelPnLocal = new JPanel();
        panelPnLocal.setLayout(new GridLayout(3,5));
        panelPnLocal.add(new JLabel());
        panelPnLocal.add(new JLabel("CM"));
        panelPnLocal.add(new JLabel("TD"));
        panelPnLocal.add(new JLabel("TP"));
        panelPnLocal.add(new JLabel("E"));

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

        this.add(panelPnLocal, gbc);

        gbc.gridx++;

        JPanel panelRépartition = new JPanel();
        panelRépartition.setLayout(new GridLayout(3,5));
        panelRépartition.add(new JLabel("CM"));
        panelRépartition.add(new JLabel());
        panelRépartition.add(new JLabel("TD"));
        panelRépartition.add(new JLabel());
        panelRépartition.add(new JLabel("TP"));
        panelRépartition.add(new JLabel());

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

        this.add(panelRépartition, gbc);

        gbc.gridx++;

        JPanel panelRépartition2 = new JPanel();
        panelRépartition2.setLayout(new GridLayout(4,6));
        panelRépartition2.add(new JLabel());
        panelRépartition2.add(new JLabel("CM"));
        panelRépartition2.add(new JLabel("TD"));
        panelRépartition2.add(new JLabel("TP"));
        panelRépartition2.add(new JLabel("heure ponctuelle"));
        panelRépartition2.add(new JLabel("E"));

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

        this.add(panelRépartition2, gbc);

        gbc.gridy++;

        this.tblGrilleDonnees = new JTable(new GrilleRessources());
        this.tblGrilleDonnees.setFillsViewportHeight(true);

        JScrollPane spGrilleDonnees = new JScrollPane(this.tblGrilleDonnees);
        this.add(spGrilleDonnees, gbc);
    }

}
