package view.parametrage;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import view.accueil.FrameAccueil;


public class PanelAddCatHeures extends JPanel implements ActionListener
{
	private JLabel lblErrCoef;
	
	private JTextField txtLib;
	private JTextField txtCoef;

	private JButton btnValider;
	private JButton btnAnnuler;


	private FrameAccueil frame;
	private Frame frameM;

	public PanelAddCatHeures (FrameAccueil frame, Frame frameM)
	{
		this.frame  = frame;
		this.frameM = frameM;


		//Création
		this.txtLib  = new JTextField(10);
		this.txtCoef = new JTextField(3);

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
		this.btnAnnuler.addActionListener(this);
		this.btnValider.addActionListener(this);
	}


	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnValider)
			;//On ajoute

		frameM.dispose();
	}
}
