package view.previsionnel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import controleur.Controleur;
import model.Etat;
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

public class PanelPrevi extends JPanel {
    private FrameAccueil frame;

    private JTabbedPane ongletSemestres;
     
    private JComboBox<String> cmbChoixCreer;

    private JButton btnCreer;
    private JButton btnModifier;
    private JButton btnSupprimer;

    private JButton btnAccueil;

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

        this.btnCreer = new JButton("Créer Ressource");
        panel.add(this.btnCreer);

        this.cmbChoixCreer = new JComboBox<String>();
        this.cmbChoixCreer.addItem("Créer Ressource");
        this.cmbChoixCreer.addItem("Créer Saé");
        this.cmbChoixCreer.addItem("Créer Stage");
        this.cmbChoixCreer.addItem("Créer PPP");

        panel.add(this.cmbChoixCreer);

        this.btnModifier = new JButton("Modifier");
        panel.add(this.btnModifier);

        this.btnSupprimer = new JButton("Supprimer");
        panel.add(this.btnSupprimer);

        this.add(panel, BorderLayout.SOUTH);

        
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        this.btnAccueil = new JButton("Accueil");
        panel.add(this.btnAccueil);
        
        this.add(panel, BorderLayout.NORTH);
        
        
        this.btnCreer.addActionListener((e)->this.creation(this.cmbChoixCreer.getSelectedIndex()) );
        this.btnAccueil.addActionListener((e)->{ this.frame.changePanel(new PanelAccueil(this.frame));} );
        this.btnModifier.addActionListener((e)->{ this.modifier();});
        this.cmbChoixCreer.addActionListener((e)->this.btnCreer.setText(this.cmbChoixCreer.getSelectedItem().toString()));
        this.btnSupprimer.addActionListener((e)-> this.supprimer());
        //this.ongletSemestres.addChangeListener((l)-> this.ongletSemestres.selec);
    }

    private void creation(int indice) {
        System.out.println("cjfbutfbytfv" + indice);
        if(indice < 0) indice = 0;


        switch (indice) {
            case 0 -> this.frame.changePanel(new PanelRessources(frame, Etat.getSemestres().get(this.ongletSemestres.getSelectedIndex())));
            case 1 -> this.frame.changePanel(new PanelSAE(frame));
            case 2 -> this.frame.changePanel(new PanelStage(frame));
            case 3 -> this.frame.changePanel(new PanelPPP(frame));
        }
    }


    private void modifier () {

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
			this.showMessageDialog("Selectionner un module");
			return;
        }

        if (m instanceof PPP)
            this.frame.changePanel(new PanelPPP(this.frame, m));
        if (m instanceof Ressource)
            this.frame.changePanel(new PanelRessources(this.frame,m));
        if (m instanceof Sae)
            this.frame.changePanel(new PanelSAE(this.frame));
        if (m instanceof Stage)
            this.frame.changePanel(new PanelStage(this.frame));
        
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
            Controleur.getControleur().supprimerModule(m);
            panelSemestre.majGrille(ongletSemestres.getSelectedIndex() + 1);
            
        }
    }

	private void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}