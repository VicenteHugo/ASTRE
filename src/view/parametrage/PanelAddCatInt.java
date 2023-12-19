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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;


import controleur.Controleur;
import view.JTextFieldNumber;


public class PanelAddCatInt extends JPanel {
	
	private JTextField txtCode;
	private JTextField txtLib;
	private JTextFieldNumber txtCoef;
	private JTextFieldNumber txtHMax;
	private JTextFieldNumber txtHMin;

	private JButton btnValider;
	private JButton btnAnnuler;


	private PanelIntPara panel;
	private Frame frameM;

	public PanelAddCatInt(PanelIntPara panel, Frame frameM) {
		this.panel = panel;
		this.frameM = frameM;

		this.frameM.setSize(350, 350);
		this.frameM.setResizable(false);

		//Création
		this.txtCode = new JTextField(10);
		this.txtLib  = new JTextField(10);
		this.txtCoef = new JTextFieldNumber(3);
		this.txtCoef.setFloat(true);
		this.txtHMax = new JTextFieldNumber(5);
		this.txtHMin = new JTextFieldNumber(5);

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

		JPanel panelBas  = new JPanel(new FlowLayout());
		JPanel panelHaut = new JPanel(new FlowLayout());

		this.setLayout(new BorderLayout());

		// Positionnement
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 10, 5, 10);

		panelCentre.add(new JLabel("Code de la catégorie : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtCode, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		panelCentre.add(new JLabel("Libelle de la catégorie : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtLib, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		panelCentre.add(new JLabel("Coef : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtCoef, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		panelCentre.add(new JLabel("Heure minimum : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtHMin, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		panelCentre.add(new JLabel("Heure maximum : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtHMax, gbc);

		panelBas.add(this.btnAnnuler);
		panelBas.add(this.btnValider);


		//Ajout
		this.add(panelCentre, BorderLayout.CENTER);
		this.add(panelBas, BorderLayout.SOUTH);
		this.add(panelHaut, BorderLayout.NORTH);

		//Activation
		this.btnValider.addActionListener((e) -> this.ajouterCatInt());
		this.btnAnnuler.addActionListener((e) -> this.frameM.dispose());
	}

	private void ajouterCatInt() {
		String sCode = this.txtCode.getText().toLowerCase();
		String sLib = this.txtLib.getText();
		String sCoef = this.txtCoef.getText();
		String sHmin = this.txtHMin.getText();
		String sHmax = this.txtHMax.getText();

		if (sCode.isEmpty() || sLib.isEmpty() || sCoef.isEmpty() || sHmax.isEmpty() || sHmin.isEmpty()) {
			showMessageDialog("Veuillez remplir toutes les données");
			return;
		}

		try {
			float coef = Float.parseFloat(sCoef);
			int hmin = Integer.parseInt(sHmin);
			int hmax = Integer.parseInt(sHmax);

			if (hmax < hmin) {
				showMessageDialog("Maximum >= Minimum.");
				return;
			}

			if (hmax < 0 || hmin < 0 || coef < 0) {
				showMessageDialog("Pas de chiffre négatif");
				return;
			}

			// Si la clé est déjà prise
			if (!Controleur.getControleur().ajouterCategorieIntervenant(sCode, sLib, coef, hmin, hmax)) {
				showMessageDialog("Le code entré est déjà utilisé");
			} else {
				this.panel.maj();
				this.frameM.dispose();
			}
		} catch (NumberFormatException e) {
			showMessageDialog("Entrez des valeurs numériques pour le coefficient et les heures.");
		}
	}

	private void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}

}
