package view.Intervenant;

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

import view.accueil.FrameAccueil;


public class PanelAddIntervenant extends JPanel {
	private JLabel lblErrCoef;
	private JLabel lblErrHeurMax;
	private JLabel lblErrHeurMin;
	
	private JTextField txtCategorie;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtHServ;
    private JTextField txtHMax;
    private JTextField txtCoefTP;

	private JButton btnValider;
	private JButton btnAnnuler;


	private FrameAccueil frame;
	private Frame frameM;

	public PanelAddIntervenant (FrameAccueil frame, Frame frameM) {
		this.frame  = frame;
		this.frameM = frameM;


		//Création
		this.txtCategorie  = new JTextField(10);
		this.txtNom        = new JTextField(3);
		this.txtPrenom     = new JTextField(5);
		this.txtHServ      = new JTextField(5);
        this.txtHMax       = new JTextField(5);
		this.txtCoefTP     = new JTextField(5);

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
		
		panelCentre.add(new JLabel("Catégorie de l'intervenant : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtCategorie, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		panelCentre.add(new JLabel("Nom : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtNom, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		panelCentre.add(new JLabel("Prénom : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtPrenom, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		panelCentre.add(new JLabel("Heure de service : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtHServ, gbc);

        gbc.gridx = 0;
		gbc.gridy++;
		panelCentre.add(new JLabel("Heure maximum : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtHMax, gbc);

        gbc.gridx = 0;
		gbc.gridy++;
		panelCentre.add(new JLabel("Coefficient heure TP : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtCoefTP, gbc);



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
