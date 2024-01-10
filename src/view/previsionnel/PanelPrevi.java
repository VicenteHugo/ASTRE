package view.previsionnel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import view.JButtonStyle;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import controleur.Controleur;
import model.Etat;
import model.Semestres;
import model.modules.Module;
import model.modules.PPP;
import model.modules.Ressource;
import model.modules.Sae;
import model.modules.Stage;
import view.accueil.FrameAccueil;
import view.accueil.PanelAccueil;
import view.module.PanelPPP;
import view.module.PanelRessources;
import view.module.PanelSAE;
import view.module.PanelStage;

import view.JComboBoxStyle;

public class PanelPrevi extends JPanel {
    private FrameAccueil frame;

    private JTabbedPane ongletSemestres;
     
    private JComboBoxStyle<String> cmbChoixCreer;

    private JButtonStyle btnCreer;
    private JButtonStyle btnModifier;
    private JButtonStyle btnSupprimer;

    private JButtonStyle btnSauvegarder;
    private JButtonStyle btnQuitter;

    public PanelPrevi(FrameAccueil frame) {

        this.frame = frame;
        this.frame.setTitle("Astre - Previsionnel (Accueil)");
		this.frame.setMinimumSize(new Dimension(600, 400));
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

        this.cmbChoixCreer = new JComboBoxStyle<String>();
        this.cmbChoixCreer.addItem("Créer Ressource");
        this.cmbChoixCreer.addItem("Créer Saé");
        this.cmbChoixCreer.addItem("Créer Stage");
        this.cmbChoixCreer.addItem("Créer PPP");
        panel.add(this.cmbChoixCreer);

        this.btnCreer = new JButtonStyle("Créer Ressource");
        panel.add(this.btnCreer);

        this.btnModifier = new JButtonStyle("Modifier");
        panel.add(this.btnModifier);

        this.btnSupprimer = new JButtonStyle("Supprimer");
        panel.add(this.btnSupprimer);

        this.add(panel, BorderLayout.SOUTH);

        
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        this.btnSauvegarder = new JButtonStyle("Sauvegarder");
        this.btnQuitter     = new JButtonStyle("Annuler");
        panel.add(this.btnQuitter);
        panel.add(this.btnSauvegarder);
        
        this.add(panel, BorderLayout.NORTH);
        
        
        this.btnCreer      .addActionListener((e)->this.creation(this.cmbChoixCreer.getSelectedIndex()) );
        this.btnSauvegarder.addActionListener((e)->{ this.sauvegarde(); this.frame.changePanel(new PanelAccueil(this.frame));});
        this.btnQuitter    .addActionListener((e)->{ Controleur.getControleur().annuler()    ;this.frame.changePanel(new PanelAccueil(this.frame));} );
        this.btnModifier   .addActionListener((e)->{ this.modifier();});
        this.cmbChoixCreer .addActionListener((e)->this.btnCreer.setText(this.cmbChoixCreer.getSelectedItem().toString()));
        this.btnSupprimer  .addActionListener((e)-> this.supprimer());
        //this.ongletSemestres.addChangeListener((l)-> this.ongletSemestres.selec);
    }

    private void creation(int indice) {

        boolean euModif = false;
        //Si des donner on était modifier on demander à enregistrer ou non 
        for(int i = 0; i < this.ongletSemestres.getTabCount();i++){
            PanelSemestre panelSemestre = (PanelSemestre) ongletSemestres.getComponentAt(i);

            if (!panelSemestre.getSemNew().equals(panelSemestre.getSemOld()))
                euModif = true;
        }

        //On demande d'enregistrer ou non
        if (euModif) {
            int reply = JOptionPane.showConfirmDialog(this, "Des semestres ont été modifiés. Voulez vous sauvegarder les modifications ?", "Modif", JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {
                this.sauvegarde();
            } else {
                Controleur.getControleur().annuler();
            }
        }



        if(indice < 0) indice = 0;
        Semestres sem = Etat.getSemestres().get(this.ongletSemestres.getSelectedIndex());

        switch (indice) {
            case 0 -> this.frame.changePanel(new PanelRessources(frame, sem));
            case 1 -> this.frame.changePanel(new PanelSAE       (frame, sem));
            case 2 -> this.frame.changePanel(new PanelStage     (frame, sem));
            case 3 -> this.frame.changePanel(new PanelPPP       (frame, sem));
        }
    }


    private void modifier () {

        boolean euModif = false;
        //Si des donner on était modifier on demander à enregistrer ou non 
        for(int i = 0; i < this.ongletSemestres.getTabCount();i++){
            PanelSemestre panelSemestre = (PanelSemestre) ongletSemestres.getComponentAt(i);

            if (!panelSemestre.getSemNew().equals(panelSemestre.getSemOld()))
                euModif = true;
        }

        //On demande d'enregistrer ou non
        if (euModif) {
            int reply = JOptionPane.showConfirmDialog(this, "Des semestres ont été modifiés. Voulez vous sauvegarder les modifications ?", "Modif", JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {
                this.sauvegarde();
            } else {
                Controleur.getControleur().annuler();
            }
        }



        int indice = 0;
        Module m = null;
        
        for(int i = 0; i < this.ongletSemestres.getTabCount();i++){
            PanelSemestre panelSemestre = (PanelSemestre) ongletSemestres.getComponentAt(i);
            JTable table = panelSemestre.getTable();
            if(table.getSelectedRow() != -1){
                indice = table.getSelectedRow();
                String code = (String) table.getValueAt(table.getSelectedRow(),0);
                m =  Controleur.getControleur().getModule(code);
            }
        }


        if ( indice < 0) {
			JOptionPane.showMessageDialog(this, "Selectionnez un module", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
        }


        if (m instanceof PPP)
            this.frame.changePanel(new PanelPPP       (this.frame, m));
        if (m instanceof Ressource)
            this.frame.changePanel(new PanelRessources(this.frame,m));
        if (m instanceof Sae)
            this.frame.changePanel(new PanelSAE       (this.frame,m));
        if (m instanceof Stage)
            this.frame.changePanel(new PanelStage(this.frame,m));
        
    }
    
    public void supprimer(){

        Module m = null;
        PanelSemestre panelSemestre = (PanelSemestre) ongletSemestres.getSelectedComponent();
        JTable table = panelSemestre.getTable();
        int[] selectedRow = table.getSelectedRows();

        for (int i =0; i < selectedRow.length; i++) {
            int val = selectedRow[i];
            val-= i;
            String code = (String) table.getValueAt(val, 0);
            m = Controleur.getControleur().getModule(code);

            if (!Controleur.getControleur().supprimerModule(m))
                JOptionPane.showMessageDialog(this, "Ce module à encore des affectations", "Erreur", JOptionPane.ERROR_MESSAGE);

            panelSemestre.majGrille(ongletSemestres.getSelectedIndex() + 1);
        }
    }


    private void sauvegarde () {

        for(int i = 0; i < this.ongletSemestres.getTabCount();i++){
            PanelSemestre panelSemestre = (PanelSemestre) ongletSemestres.getComponentAt(i);
            Semestres sem = panelSemestre.getSemNew();
            Controleur.getControleur().modifSemestres(sem);
        }
        
        Controleur.getControleur().enregistrer();
    }
}