package view.Etat;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import view.JButtonStyle;
import view.JComboBoxStyle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controleur.Controleur;
import model.Etat;
import view.accueil.*;

public class PanelEtat extends JPanel {

    private FrameAccueil frame;
    
    private JComboBoxStyle<String> lstEtat;
    private JButtonStyle           btnSelection;
    private JButtonStyle           btnNouveau;
    private JButtonStyle           btnSupprimer;

    private JComboBoxStyle<String> lstGeneration;
    private JButtonStyle           btnGenerer;

    private JButtonStyle           btnRetour;

    public PanelEtat(FrameAccueil frame) {
        /* Frame */
        this.frame = frame;
        this.frame.setTitle("Etat");
        this.frame.setMinimumSize(new Dimension(620, 250));
        this.frame.setSize(new Dimension(620, 250));


        /* Création des composants */
        //Changement et création d'état
        this.lstEtat = new JComboBoxStyle<>(Controleur.getControleur().getEtats());
        this.lstEtat.setSelectedItem(Etat.nom);
        this.btnSelection = new JButtonStyle("Sélectionner");
        this.btnNouveau   = new JButtonStyle("Nouveau");
        this.btnSupprimer = new JButtonStyle("Supprimer");

        //Génerer les pages
        this.lstGeneration = new JComboBoxStyle<>(new String[] {"Par intervenants (HTML)", "Par modules (HTML)", "Recap intervenants (CSV)"});
        this.lstGeneration.setSelectedIndex(0);
        this.btnGenerer = new JButtonStyle("Générer");

        //Retourner à l'acceuil
        this.btnRetour    = new JButtonStyle("Retour");


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
        this.btnGenerer  .addActionListener((e)->this.generer());
    }

    private void changerEtat () {
        String nomEtat = (String) this.lstEtat.getSelectedItem();

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


    private void generer() {
        String option = (String) this.lstGeneration.getSelectedItem();


        if (option.equals("Recap intervenants (CSV)")) {
            Controleur.getControleur().genererCSV();
        }

        if(option.equals("Par intervenants (HTML)")) {
            Controleur.getControleur().genererHTMLIntervenants();
        }

        if(option.equals("Par modules (HTML)")) {
            Controleur.getControleur().genererHTMLModules();
        }

    }


}
