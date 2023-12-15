package view.previsionnel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controleur.Controleur;

import model.modules.Module;
import model.modules.PPP;
import model.modules.Ressource;
import model.modules.Sae;
import model.modules.Stage;
import view.accueil.FrameAccueil;
import view.accueil.PanelAccueil;
import view.module.PanelRessources;
import view.module.PanelSAE;
import view.module.PanelStage;

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
        ongletSemestres.addTab("S1", new PanelSemestre(1));
        ongletSemestres.addTab("S2", new PanelSemestre(2));
        ongletSemestres.addTab("S3", new PanelSemestre(3));
        ongletSemestres.addTab("S4", new PanelSemestre(4));
        ongletSemestres.addTab("S5", new PanelSemestre(5));
        ongletSemestres.addTab("S6", new PanelSemestre(6));
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
        this.btnCreerStage.addActionListener((e)->{ this.frame.changePanel(new PanelStage(this.frame));} );
        this.btnAccueil.addActionListener((e)->{ this.frame.changePanel(new PanelAccueil(this.frame));} );
        this.btnModifier.addActionListener((e)->{ this.modifier();});
        //this.btnSupprimer.addActionListener(e)->Controleur.getControleur;
    }


    private void modifier () {
        int ind = this.ongletSemestres.getSelectedIndex();

        if (ind < 0) {
			this.showMessageDialog("Selectionner un module");
			return;
        }

        Module m = Controleur.getControleur().getModule(ind);

        if (m instanceof PPP)
            System.out.println("Page pas fait pout ppp");
        if (m instanceof Ressource)
            this.frame.changePanel(new PanelRessources(this.frame,m));
        if (m instanceof Sae)
            this.frame.changePanel(new PanelSAE(this.frame));
        if (m instanceof Stage)
            this.frame.changePanel(new PanelStage(this.frame));
        
    }


	private void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}