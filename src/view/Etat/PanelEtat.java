package view.Etat;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.accueil.*;

public class PanelEtat extends JPanel implements ActionListener {

    private FrameAccueil frame;
    private JLabel lblTitre;
    private String[] etatEnsemble = { "", "2020-2021", "2021-2022", "2022-2023", "2023-2024", "2024-2025" };
    private JComboBox<String> lstEtat;
    private JButton btnSelection, btnRetour;

    public PanelEtat(FrameAccueil frame) {
        this.frame = frame;
        this.frame.setTitle("Etat");
        this.frame.setMinimumSize(new Dimension(600, 400));

        // Création des composants
        this.lblTitre = new JLabel("Choisissez votre État");
        this.lblTitre.setFont(new Font("Arial", Font.BOLD, 18)); // Taille et style de police

        this.lstEtat = new JComboBox<>(etatEnsemble);
        this.lstEtat.setSelectedIndex(0);
        
        this.btnSelection = new JButton("Sélectionner");
        this.btnRetour    = new JButton("Retour");

        Dimension buttonSize = new Dimension(120, 40);
        this.btnSelection.setPreferredSize(buttonSize);
        this.btnRetour.setPreferredSize(buttonSize);

        // Layouts
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Centrage des composants
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4; // Étendre sur deux colonnes
        gbc.anchor = GridBagConstraints.CENTER; // Aligner au centre
        gbc.insets = new Insets(20, 10, 20, 10); // Marges

        this.add(this.lblTitre, gbc);
        gbc.gridy++;
        this.add(this.lstEtat, gbc);
        gbc.gridy++;
        this.add(this.btnSelection, gbc);
        gbc.gridy++;
        this.add(this.btnRetour, gbc);

        // Action
        this.btnRetour.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnRetour)
			this.frame.changePanel(new PanelAccueil(this.frame));
    }
}
