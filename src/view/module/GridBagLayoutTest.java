package view.module;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GridBagLayoutTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Exemple GridBagLayout avec Bordure");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());

        // Création des composants
        JButton titleButton = new JButton("Titre");
        JButton button1 = new JButton("Bouton 1");
        JButton button2 = new JButton("Bouton 2");
        JButton button3 = new JButton("Bouton 3");

        // Création des bordures
        Border titleBorder = BorderFactory.createLineBorder(Color.GREEN, 2); // Bordure verte pour le titre
        Border buttonsBorder = BorderFactory.createLineBorder(Color.BLUE, 2); // Bordure bleue pour les boutons

        // Application des bordures aux composants
        titleButton.setBorder(titleBorder);

        // Conteneur pour les boutons avec la bordure
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.add(button1);
        buttonsPanel.add(button2);
        buttonsPanel.add(button3);
        buttonsPanel.setBorder(buttonsBorder);

        // Définition des contraintes GridBagLayout
        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.gridwidth = GridBagConstraints.REMAINDER; // Étendre le titre sur toute la largeur
        gbcTitle.insets = new Insets(10, 10, 10, 10); // Marge pour le titre

        GridBagConstraints gbcButtons = new GridBagConstraints();
        gbcButtons.gridx = 0;
        gbcButtons.gridy = 1;
        gbcButtons.gridwidth = GridBagConstraints.REMAINDER; // Étendre les boutons sur toute la largeur
        gbcButtons.insets = new Insets(10, 10, 10, 10); // Marge pour les boutons

        // Ajout des composants au conteneur avec les contraintes
        panel.add(titleButton, gbcTitle);
        panel.add(buttonsPanel, gbcButtons);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
