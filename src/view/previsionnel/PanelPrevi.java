package view.previsionnel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PanelPrevi extends JPanel {
    private JTabbedPane ongletSemestres;
     
    private JButton btnCreerRessources;
    private JButton btnCreerSae;
    private JButton btnCreerStage;
    private JButton btnModifier;
    private JButton btnSupprimer;

    public PanelPrevi() {
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
    }
}
