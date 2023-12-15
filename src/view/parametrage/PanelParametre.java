package view.parametrage;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.accueil.FrameAccueil;
import view.accueil.PanelAccueil;

public class PanelParametre extends JPanel {

    private JButton btnIntervenants;
    private JButton btnHeures;
    private JButton btnAccueil;

    private FrameAccueil frame;

    public PanelParametre(FrameAccueil frame) {

        // Frame
        frame.setTitle("Astre - Paramètres (Accueil)");
        frame.setMinimumSize(new Dimension(400, 400));
        this.frame = frame;

        // Création des composants
        this.btnIntervenants = new JButton("Catégorie Intervenants");
        this.btnHeures = new JButton("Catégorie Heures");
        this.btnAccueil = new JButton("Accueil");

        // Ajustement de la taille des boutons
        Dimension buttonSize = new Dimension(200, 50);
        this.btnIntervenants.setPreferredSize(buttonSize);
        this.btnHeures.setPreferredSize(buttonSize);
        this.btnAccueil.setPreferredSize(buttonSize);

        // Ajustement de la police des boutons
        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        this.btnIntervenants.setFont(buttonFont);
        this.btnHeures.setFont(buttonFont);
        this.btnAccueil.setFont(buttonFont);

        // Layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Positionnement
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);

        this.add(this.btnIntervenants, gbc);

        gbc.gridy++;
        this.add(this.btnHeures, gbc);

        gbc.gridy++;
        this.add(this.btnAccueil, gbc);

        // Action
        this.btnIntervenants.addActionListener((e)->this.frame.changePanel(new PanelIntPara(this.frame)));
        this.btnHeures.addActionListener((e)->this.frame.changePanel(new PanelHeurePara(this.frame)));
        this.btnAccueil.addActionListener((e)->this.frame.changePanel(new PanelAccueil(this.frame)));
    }
}
