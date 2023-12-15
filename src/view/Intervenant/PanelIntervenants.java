package view.Intervenant;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
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

        this.tblGrilleDonnees = new JTable(new GrilleInt());
        this.tblGrilleDonnees.setFillsViewportHeight(true);

        JScrollPane spGrilleDonnees = new JScrollPane(this.tblGrilleDonnees);
        JPanel panelTable = new JPanel();
        JPanel panelBtn = new JPanel();
        JPanel panelModif = new JPanel();
        JPanel panelBas = new JPanel();

        // Layouts
        this.setLayout(new BorderLayout());
        panelTable.setLayout(new BorderLayout());
        panelBtn.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelModif.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelBas.setLayout(new GridLayout(2, 2));

        // Positionnement des composants

        panelTable.add(this.lblListe, BorderLayout.NORTH);
        panelTable.add(spGrilleDonnees, BorderLayout.CENTER);

        panelModif.add(this.btnAjout);
        panelModif.add(this.btnSupr);
        panelBtn.add(this.btnEnregistr);
        panelBtn.add(this.btnAnnuler);

        panelBas.add(new JPanel());
        panelBas.add(panelModif);
        panelBas.add(panelBtn);
        panelBas.add(new JPanel());

        this.add(panelTable, BorderLayout.CENTER);
        this.add(panelBas, BorderLayout.SOUTH);
        this.setVisible(true);

        //Action
        this.btnAnnuler.addActionListener((e) -> this.frame.changePanel(new PanelAccueil(this.frame)));
        this.btnAjout.addActionListener((e) -> {
            JFrame f = new JFrame();
            f.add(new PanelAddIntervenant(this.frame, f));
            f.setTitle("Ajout d'un Intervenant");
            f.pack();
            f.setResizable(false);
            f.setLocationRelativeTo(null);
            f.setAlwaysOnTop(true);
            f.setVisible(true);
        });

    }

    public void maj() {
		    this.tblGrilleDonnees.setModel(new GrilleInt()); 
	    }
}
