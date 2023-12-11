package view.accueil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAccueil extends JPanel {

    private JButton btnParametre, btnPrevisionnel, btnIntervenant, btnEtat;

    public PanelAccueil() {

        // création des composants
        this.btnParametre = new JButton("Paramètres");
        this.btnPrevisionnel = new JButton("Prévisionnel");
        this.btnIntervenant = new JButton("Intervenants");
        this.btnEtat = new JButton("Etats");

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //Positionnement
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // ajout des composants avec des marges
        this.add(btnParametre,gbc);
        gbc.gridy++;
        this.add(btnPrevisionnel,gbc);
        gbc.gridy++;
        this.add(btnIntervenant,gbc);
        gbc.gridy++;
        this.add(btnEtat,gbc);
    }
}
