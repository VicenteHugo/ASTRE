package view.parametrage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.accueil.FrameAccueil;
import view.accueil.PanelAccueil;

public class PanelParametre extends JPanel implements ActionListener {

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
        this.btnIntervenants.addActionListener(this);
        this.btnHeures.addActionListener(this);
        this.btnAccueil.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnIntervenants)
            this.frame.changePanel(new PanelIntPara(this.frame));

        if (e.getSource() == this.btnHeures)
            this.frame.changePanel(new PanelHeurePara(this.frame));

        if (e.getSource() == this.btnAccueil)
            this.frame.changePanel(new PanelAccueil(this.frame));
    }
}
