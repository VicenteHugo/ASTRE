package view.previsionnel;

import view.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import view.accueil.FrameAccueil;
import view.accueil.PanelAccueil;
import view.module.PanelRessources;
import view.module.PanelSAE;

public class PanelPrevi extends JPanel {
    private FrameAccueil frame;

    private JTabbedPane ongletSemestres;
     
    private JButton btnCreerRessources;
    private JButton btnCreerSae;
    private JButton btnCreerStage;
    private JButton btnModifier;
    private JButton btnSupprimer;

    private JButton btnAccueil;


    public PanelPrevi(FrameAccueil frame) {

        this.frame = frame;
        frame.setTitle("Astre - Previsionnel (Accueil)");
		frame.setMinimumSize(new Dimension(600, 400));
        this.setLayout(new BorderLayout());

        ongletSemestres = new JTabbedPane(JTabbedPane.TOP);
        ongletSemestres.addTab("S1", new PanelSemestre());
        ongletSemestres.addTab("S2", new PanelSemestre());
        ongletSemestres.addTab("S3", new PanelSemestre());
        ongletSemestres.addTab("S4", new PanelSemestre());
        ongletSemestres.addTab("S5", new PanelSemestre());
        ongletSemestres.addTab("S6", new PanelSemestre());
        this.add(ongletSemestres);

        JPanel panel = new JPanel(new FlowLayout());

        this.btnCreerRessources = new JButton("Créer ressources");
        panel.add(this.btnCreerRessources);

        this.btnCreerSae = new JButton("Créer SAE");
        panel.add(this.btnCreerSae);

        this.btnCreerStage = new JButton("Créer stage/suivi");
        panel.add(this.btnCreerStage);

        this.btnModifier = new JButton("Modifier");
        panel.add(this.btnModifier);

        this.btnSupprimer = new JButton("Supprimer");
        panel.add(this.btnSupprimer);

        this.add(panel, BorderLayout.SOUTH);

        
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        this.btnAccueil = new JButton("Accueil");
        panel.add(this.btnAccueil);
        
        this.add(panel, BorderLayout.NORTH);
        
        
        this.btnCreerRessources.addActionListener((e)->{ this.frame.changePanel(new PanelRessources(this.frame));} );
        this.btnCreerSae.addActionListener((e)->{ this.frame.changePanel(new PanelSAE(this.frame));} );
        this.btnAccueil.addActionListener((e)->{ this.frame.changePanel(new PanelAccueil(this.frame));} );
    }
}
