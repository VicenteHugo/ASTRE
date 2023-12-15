package view.Etat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;


import controleur.Controleur;
import model.Etat;


public class PanelAddEtat extends JPanel {
	
	private JTextField txtNom;
	private JComboBox<String> lstEtat;


	private JButton btnValider;
	private JButton btnAnnuler;


	private PanelEtat panel;
	private Frame frameM;

	public PanelAddEtat(PanelEtat panel, Frame frameM) {
		this.panel = panel;
		this.frameM = frameM;

		this.frameM.setSize(350, 350);
		this.frameM.setResizable(false);

		//Création
		this.txtNom = new JTextField(10);
		this.lstEtat = new JComboBox<>();
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		this.lstEtat.setModel(comboBoxModel);

		comboBoxModel.addElement("Aucune");
		for (String s : Etat.getEtats())
			comboBoxModel.addElement(s);

		this.btnAnnuler = new JButton("Annuler");
		this.btnValider = new JButton("Créer");


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

		panelCentre.add(new JLabel("Nom de l'Etat : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtNom, gbc);

		gbc.gridy ++;
		gbc.gridx =0;
		panelCentre.add(new JLabel("Dupliquer depuis :"), gbc);
		gbc.gridx++;
		panelCentre.add(this.lstEtat, gbc);

		panelBas.add(this.btnAnnuler);
		panelBas.add(this.btnValider);


		//Ajout
		this.add(panelCentre, BorderLayout.CENTER);
		this.add(panelBas, BorderLayout.SOUTH);
		this.add(panelHaut, BorderLayout.NORTH);

		//Activation
		this.btnValider.addActionListener((e) -> this.ajouterEtat());
		this.btnAnnuler.addActionListener((e) -> this.frameM.dispose());
	}

	private void ajouterEtat() 
	{
		String sNom = this.txtNom.getText();

		if (sNom.isEmpty()) {
			showMessageDialog("Veuillez remplir toutes les données");
			return;
		}

		// Si la clé est déjà prise
		if (!Controleur.getControleur().creerEtat(sNom)) {
			showMessageDialog("Le nom entré est déjà utilisé");
		} else {

			if (!this.lstEtat.getSelectedItem().equals("Aucune"))
				System.out.println("On duplique pas");
			this.panel.quitter();
			this.frameM.dispose();
		}
	}

	private void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}

}
