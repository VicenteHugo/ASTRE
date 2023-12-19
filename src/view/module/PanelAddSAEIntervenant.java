package view.module;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.JTextFieldNumber;
import view.accueil.FrameAccueil;


public class PanelAddSAEIntervenant extends JPanel {	
	private JTextField txtIntervenant;
	private JTextField txtType;
	private JTextFieldNumber txtNbSemaine;
    private JTextFieldNumber txtTotal;
    private JTextField txtCommentaire;

	private JButton btnValider;
	private JButton btnAnnuler;


	private Frame frameM;

	public PanelAddSAEIntervenant (FrameAccueil frame, Frame frameM) {
		this.frameM = frameM;


		//Création
		this.txtIntervenant  = new JTextField(10);
		this.txtType         = new JTextField(10);
		this.txtNbSemaine    = new JTextFieldNumber(3);
        this.txtTotal        = new JTextFieldNumber(5);
		this.txtCommentaire  = new JTextField(15);

		this.btnAnnuler = new JButton("Annuler");
		this.btnValider = new JButton("Valider");

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
		
		panelCentre.add(new JLabel("Nom de l'intervenant : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtIntervenant, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		panelCentre.add(new JLabel("Type : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtType, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		panelCentre.add(new JLabel("Nombre d'heure : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtNbSemaine, gbc);

        gbc.gridx = 0;
		gbc.gridy++;
		panelCentre.add(new JLabel("Total eqtd : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtTotal, gbc);

        gbc.gridx = 0;
		gbc.gridy++;
		panelCentre.add(new JLabel("Commentaire : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtCommentaire, gbc);



		panelBas.add(this.btnAnnuler);
		panelBas.add(this.btnValider);

		//Ajout
		this.add(panelCentre, BorderLayout.CENTER);
		this.add(panelBas, BorderLayout.SOUTH);

		//Activation
		this.btnValider.addActionListener((e)->/* On ajoute */System.out.println());
		this.btnAnnuler.addActionListener((e)->this.frameM.dispose());
	}
}
