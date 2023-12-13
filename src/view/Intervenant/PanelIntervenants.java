package view.Intervenant;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import view.accueil.FrameAccueil;
import view.accueil.PanelAccueil;

public class PanelIntervenants extends JPanel {
    private JLabel lblListe;
    private JButton btnAjout;
    private JButton btnSupr;
    private JButton btnEnregistr;
    private JButton btnAnnuler;
    private JTable tblGrilleDonnees;

    private FrameAccueil frame;

    public PanelIntervenants(FrameAccueil frame) {

        this.frame = frame;
        frame.setTitle("Astre - Intervenants (Accueil)");
		frame.setMinimumSize(new Dimension(600, 400));

        // Création des composants
        this.lblListe = new JLabel("Liste Intervenant");
        this.btnAjout = new JButton("Ajouter");
        this.btnSupr = new JButton("Supprimer");
        this.btnEnregistr = new JButton("Enregistrer");
        this.btnAnnuler = new JButton("Annuler");

        this.tblGrilleDonnees = new JTable(new GrilleCatInt());
        this.tblGrilleDonnees.setFillsViewportHeight(true);

        JScrollPane spGrilleDonnees = new JScrollPane(this.tblGrilleDonnees);
        JPanel panelTable = new JPanel();
        JPanel panelBtn   = new JPanel();
        JPanel panelModif = new JPanel();

        // Layouts
        this.setLayout(new BorderLayout());
        panelTable.setLayout(new BorderLayout());
        panelBtn.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelModif.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Positionnement des composants

        panelTable.add(this.lblListe, BorderLayout.NORTH);
        panelTable.add(spGrilleDonnees, BorderLayout.CENTER);
        panelModif.add(this.btnAjout);
        panelModif.add(this.btnSupr);
        panelTable.add(panelModif, BorderLayout.SOUTH);

        panelBtn.add(this.btnEnregistr);
        panelBtn.add(this.btnAnnuler);

        this.add(panelTable, BorderLayout.CENTER);
        this.add(panelBtn, BorderLayout.SOUTH);
        this.setVisible(true);

        //Action
		this.btnAnnuler.addActionListener((e)->this.frame.changePanel(new PanelAccueil(this.frame)));
    }
}
