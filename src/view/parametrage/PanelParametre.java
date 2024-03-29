package view.parametrage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import view.JButtonStyle;
import javax.swing.JPanel;

import view.accueil.FrameAccueil;
import view.accueil.PanelAccueil;

public class PanelParametre extends JPanel {

    private JButtonStyle btnIntervenants;
    private JButtonStyle btnHeures;
    private JButtonStyle btnAccueil;

    private FrameAccueil frame;

    public PanelParametre(FrameAccueil frame) {

        // Frame
        this.frame = frame;
        this.frame.setTitle("Astre - Paramètres (Accueil)");
        this.frame.setMinimumSize(new Dimension(300, 230));
        this.frame.setSize(300, 230);

        // Création des composants
        this.btnIntervenants = new JButtonStyle("Catégorie Intervenants");
        this.btnHeures = new JButtonStyle("Catégorie Heures");
        this.btnAccueil = new JButtonStyle("Accueil");

        /* STYLE */

        // Button
        Dimension buttonSize = new Dimension(200, 30); // Vous pouvez ajuster la taille selon vos besoins
        this.btnIntervenants.setMinimumSize(buttonSize);
        this.btnAccueil.setMinimumSize(buttonSize);
        this.btnHeures.setMinimumSize(buttonSize);

        this.btnIntervenants.setPreferredSize(buttonSize);
        this.btnAccueil.setPreferredSize(buttonSize);
        this.btnHeures.setPreferredSize(buttonSize);

        Color coul = Color.decode("0xD0D0D0");
        this.btnIntervenants.setBackground(coul);
        this.btnAccueil.setBackground(coul);
        this.btnHeures.setBackground(coul);

        // Layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Positionnement
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 20, 10, 20);

        this.add(this.btnIntervenants, gbc);

        gbc.gridy++;
        this.add(this.btnHeures, gbc);

        gbc.gridy++;
        this.add(this.btnAccueil, gbc);

        // Action
        this.btnIntervenants.addActionListener((e) -> this.frame.changePanel(new PanelIntPara(this.frame)));
        this.btnHeures.addActionListener((e) -> this.frame.changePanel(new PanelHeurePara(this.frame)));
        this.btnAccueil.addActionListener((e) -> this.frame.changePanel(new PanelAccueil(this.frame)));
    }
}
