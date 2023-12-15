package view.accueil;

import view.Intervenant.PanelIntervenants;
import view.parametrage.*;
import view.previsionnel.*;
import view.Etat.*;

import javax.swing.*;

import java.awt.*;

public class PanelAccueil extends JPanel {

    private JButton btnParametre, btnPrevisionnel, btnIntervenant, btnEtat;
    private FrameAccueil frame;

    public PanelAccueil(FrameAccueil frame) {

        this.frame = frame;
        this.frame.setTitle("Accueil");
		this.frame.setMinimumSize(new Dimension(400, 230));
        this.frame.setSize(400, 230);

        // création des composants
        this.frame.setTitle("Astre - Accueil");
        this.btnParametre = new JButton("Paramètres");
        this.btnPrevisionnel = new JButton("Prévisionnel");
        this.btnIntervenant = new JButton("Intervenants");
        this.btnEtat = new JButton("Etats");

        /* STYLE */

        // Button
        Dimension buttonSize = new Dimension(150, 30); // Vous pouvez ajuster la taille selon vos besoins
        this.btnParametre   .setMinimumSize(buttonSize);
        this.btnPrevisionnel.setMinimumSize(buttonSize);
        this.btnIntervenant .setMinimumSize(buttonSize);
        this.btnEtat        .setMinimumSize(buttonSize);
        
        this.btnParametre   .setPreferredSize(buttonSize);
        this.btnPrevisionnel.setPreferredSize(buttonSize);
        this.btnIntervenant .setPreferredSize(buttonSize);
        this.btnEtat.setPreferredSize(buttonSize);
        
        Color coul = Color.decode("0xD0D0D0");
        this.btnParametre   .setBackground(coul);
        this.btnPrevisionnel.setBackground(coul);
        this.btnIntervenant .setBackground(coul);
        this.btnEtat        .setBackground(coul);


        // Texte
        JLabel lblLogo = new JLabel("Astre");
        lblLogo.setFont(new Font("Cambria", Font.BOLD, 50));

        // Layout
        JPanel panelHaut = new JPanel();
        JPanel paneCentre = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        this.setLayout(new BorderLayout());

        // Positionnement
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 20, 20, 20);

        // ajout des composants avec des marges
        paneCentre.add(this.btnIntervenant, gbc);
        gbc.gridx++;
        paneCentre.add(this.btnPrevisionnel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        paneCentre.add(this.btnParametre, gbc);
        gbc.gridx++;
        paneCentre.add(this.btnEtat, gbc);

        panelHaut.add(lblLogo);

        this.add(panelHaut, BorderLayout.NORTH);
        this.add(paneCentre, BorderLayout.CENTER);

        this.btnParametre.addActionListener((e) -> this.frame.changePanel(new PanelParametre(this.frame)));
        this.btnPrevisionnel.addActionListener((e) -> this.frame.changePanel(new PanelPrevi(this.frame)));
        this.btnIntervenant.addActionListener((e) -> this.frame.changePanel(new PanelIntervenants(this.frame)));
        this.btnEtat.addActionListener((e) -> this.frame.changePanel(new PanelEtat(this.frame)));

    }
}