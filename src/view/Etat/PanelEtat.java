package view.Etat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controleur.Controleur;
import model.Etat;
import view.accueil.*;

public class PanelEtat extends JPanel {

    private FrameAccueil frame;
    
    private JComboBox<String> lstEtat;
    private JButton           btnSelection;
    private JButton           btnNouveau;
    private JButton           btnSupprimer;

    private JComboBox<String> lstGeneration;
    private JButton           btnGenerer;

    private JButton           btnRetour;

    public PanelEtat(FrameAccueil frame) {
        /* Frame */
        this.frame = frame;
        this.frame.setTitle("Etat");
        this.frame.setMinimumSize(new Dimension(620, 250));
        this.frame.setSize(new Dimension(620, 250));


        /* Création des composants */
        //Changement et création d'état
        this.lstEtat = new JComboBox<>(Controleur.getControleur().getEtats());
        this.lstEtat.setSelectedItem(Etat.nom);
        this.btnSelection = new JButton("Sélectionner");
        this.btnNouveau   = new JButton("Nouveau");
        this.btnSupprimer = new JButton("Supprimer");

        //Génerer les pages
        this.lstGeneration = new JComboBox<>(new String[] {"Par intervenants", "Par modules", "Recap intervenants"});
        this.lstGeneration.setSelectedIndex(0);
        this.btnGenerer = new JButton("Générer");

        //Retourner à l'acceuil
        this.btnRetour    = new JButton("Retour");


        /* STYLE */

        // Button
        Dimension buttonSize = new Dimension(140, 20); // Vous pouvez ajuster la taille selon vos besoins
        this.btnGenerer  .setMinimumSize(buttonSize);
        this.btnNouveau  .setMinimumSize(buttonSize);
        this.btnRetour   .setMinimumSize(buttonSize);
        this.btnSelection.setMinimumSize(buttonSize);
        this.btnSupprimer.setMinimumSize(buttonSize);

        this.btnGenerer  .setPreferredSize(buttonSize);
        this.btnNouveau  .setPreferredSize(buttonSize);
        this.btnRetour   .setPreferredSize(buttonSize);
        this.btnSelection.setPreferredSize(buttonSize);
        this.btnSupprimer.setPreferredSize(buttonSize);

        Color coul = Color.decode("0xD0D0D0");
        this.btnGenerer  .setBackground(coul);
        this.btnNouveau  .setBackground(coul);
        this.btnRetour   .setBackground(coul);
        this.btnSelection.setBackground(coul);
        this.btnSupprimer.setBackground(coul);


        //List
        Dimension listSize = new Dimension(150, 30);
        this.lstEtat      .setMinimumSize(listSize);
        this.lstGeneration.setMinimumSize(listSize);

        this.lstEtat      .setPreferredSize(listSize);
        this.lstGeneration.setPreferredSize(listSize);



        /* Layouts */

        // Création
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Centrage des composants
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST; // Aligner au centre
        gbc.insets = new Insets(10, 5, 10, 5); // Marges

        this.add(new JLabel("Changer d'état :"), gbc);
        gbc.gridx++;
        this.add(this.lstEtat, gbc);
        gbc.gridx++;
        JPanel panelButton = new JPanel(new GridLayout(3,1,5,5));
        panelButton.add(this.btnNouveau, gbc);
        panelButton.add(this.btnSupprimer, gbc);
        panelButton.add(this.btnSelection, gbc);
        this.add(panelButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        this.add(new JLabel("Generer : "), gbc);
        gbc.gridx++;
        this.add(this.lstGeneration, gbc);
        gbc.gridx++;
        this.add(this.btnGenerer, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        this.add(this.btnRetour, gbc);

        // Action
        this.btnRetour   .addActionListener((e)->this.quitter());
        this.btnNouveau  .addActionListener((e)->this.ajouterEtat());
        this.btnSelection.addActionListener((e)->this.changerEtat());
        this.btnSupprimer.addActionListener((e)->this.suppEtat());
    }

    private void changerEtat () {
        String nomEtat = (String) this.lstEtat.getSelectedItem();
        System.out.println("Etat changer pour : " + nomEtat);

        Controleur.getControleur().changerEtat(nomEtat);
    }

    private void suppEtat () {
        String nomEtat = (String) this.lstEtat.getSelectedItem();
        if (Controleur.getControleur().suppEtat(nomEtat)) {
            this.quitter();
        } else {
            JOptionPane.showMessageDialog(this, "Vous pouvez pas supprimer l'Etat sur lequel vous êtes connecté", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void quitter () {this.frame.changePanel(new PanelAccueil(this.frame));}

    private void ajouterEtat () {
        JFrame f = new JFrame();
		f.add(new PanelAddEtat(this, f));
		f.setTitle("Création d'un etat");
		f.pack();
		f.setLocationRelativeTo(null);
		f.setAlwaysOnTop(true);
		f.setVisible(true);
    }


}
