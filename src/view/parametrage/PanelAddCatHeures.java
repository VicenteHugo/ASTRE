package view.parametrage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.Controleur;


public class PanelAddCatHeures extends JPanel {
	
	private JTextField txtLib;
	private JTextField txtCoef;

	private JButton btnValider;
	private JButton btnAnnuler;


	private PanelHeurePara panel;
	private Frame frameM;

	public PanelAddCatHeures (PanelHeurePara panel, Frame frameM)
	{
		this.panel  = panel;
		this.frameM = frameM;
		this.frameM.setSize(350, 150);
		this.frameM.setResizable(false);


		//Création
		this.txtLib  = new JTextField(10);
		this.txtCoef = new JTextField(3);

		this.btnAnnuler = new JButton("Annuler");
		this.btnValider = new JButton("Valider");


        /* STYLE */

        // Button
        Dimension buttonSize = new Dimension(120, 20); // Vous pouvez ajuster la taille selon vos besoins
        this.btnAnnuler.setMinimumSize(buttonSize);
        this.btnValider.setMinimumSize(buttonSize);

        this.btnAnnuler.setPreferredSize(buttonSize);
        this.btnValider.setPreferredSize(buttonSize);

        Color coul = Color.decode("0xD0D0D0");
        this.btnAnnuler.setBackground(coul);
        this.btnValider.setBackground(coul);


		//Layout
		JPanel panelCentre = new JPanel();
		panelCentre.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JPanel panelBas = new JPanel(new FlowLayout());

		this.setLayout(new BorderLayout());
		
		// Positionnement
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 10, 5, 10);
		
		panelCentre.add(new JLabel("Libelle de la catégorie : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtLib, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		panelCentre.add(new JLabel("Coef : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtCoef, gbc);



		panelBas.add(this.btnAnnuler);
		panelBas.add(this.btnValider);

		//Ajout
		this.add(panelCentre, BorderLayout.CENTER);
		this.add(panelBas, BorderLayout.SOUTH);

		//Activation
		this.btnValider.addActionListener((e)->this.ajouterCatHeures());
		this.btnAnnuler.addActionListener((e)->this.frameM.dispose());
	}
	
	private void ajouterCatHeures ()
	{
		String sCoef = this.txtCoef.getText();
		String sLib  = this.txtLib .getText().toUpperCase();

		if (sCoef.isEmpty() || sLib.isEmpty()) {
			showMessageDialog("Veuillez remplir toutes les données");
			return;
		}
		
		try 
		{
			float coef = Float.parseFloat(sCoef);

			if (coef < 0) {
				showMessageDialog("Pas de chiffre négatifs");
				return;
			}

			if(!Controleur.getControleur().ajouterCategorieHeure(sLib,coef))
				showMessageDialog("Le libellé entré est déjà utilisé");
			else {
				this.panel.maj();
				this.frameM.dispose();
			}

		} catch (Exception e) {
			showMessageDialog("Entrez des valeurs numériques pour le coefficient.");
		}
	}

	private void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}
