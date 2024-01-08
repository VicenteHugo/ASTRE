/*
* Auteur : Équipe 2
* Date   : Decembre 2023
* */


package view.parametrage;

// AWT
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

// SWING
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import view.JButtonStyle;

// ASTRE
import controleur.Controleur;
import view.accueil.FrameAccueil;


/**
 * Panel permettant la visualisation des Catégorie d'heures. (Paramètres -> Catégories Heures)
 */
public class PanelHeurePara extends JPanel {



	/** Frame principale. */
	private FrameAccueil frame;
	/** Tables des données. */
	private JTable tblGrilleDonnees;

	/** Boutton permettant de valider les modifications. Renvoie à l'acceuil. */
	private JButtonStyle btnValider;
	/** Boutton permettant d'annuler les modifications. Renvoie à l'acceuil. */
	private JButtonStyle btnRetour;



	/**
	 * Constructeur prenant en paramètre la FrameAcceuil pour changer de pannel.
	 * @param frame
	 */

	public PanelHeurePara(FrameAccueil frame) {

		/*       */
		/* Frame */
		/*       */

		this.frame = frame;

		//Taille
		this.frame.setTitle("Astre - Paramètre (Heures)");
		this.frame.setMinimumSize(new Dimension(520, 150));



		/*                         */
		/* Création des composants */
		/*                         */

		//Table des données
		JScrollPane spGrilleDonnees;
		this.tblGrilleDonnees = new JTable(new GrilleCatHeures());
		this.tblGrilleDonnees.setFillsViewportHeight(true);

		//Bouton
		this.btnValider = new JButtonStyle("Valider");
		this.btnRetour = new JButtonStyle("Annuler");



		/*                               */
		/* Positionnement des composants */
		/*                               */

		// Layout
		this.setLayout(new BorderLayout());

		// Positionnement
		spGrilleDonnees = new JScrollPane(this.tblGrilleDonnees);
		spGrilleDonnees.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBtn.add(this.btnRetour);
		panelBtn.add(this.btnValider);

		this.add(panelBtn, BorderLayout.SOUTH);
		this.add(spGrilleDonnees, BorderLayout.CENTER);



		/*                      */
		/* Style des composants */
		/*                      */

		// Button
        Dimension buttonSize = new Dimension(120, 20);
        this.btnValider  .setMinimumSize(buttonSize);
        this.btnRetour   .setMinimumSize(buttonSize);

        this.btnValider  .setPreferredSize(buttonSize);
        this.btnRetour   .setPreferredSize(buttonSize);

        Color coul = Color.decode("0xD0D0D0");
        this.btnValider  .setBackground(coul);
        this.btnRetour   .setBackground(coul);

		// JTable
		DefaultTableCellRenderer centre = new DefaultTableCellRenderer();
		centre.setHorizontalAlignment(JLabel.CENTER);
		this.tblGrilleDonnees.getColumnModel().getColumn(1).setCellRenderer(centre);



		/*                           */
		/* Activation des composants */
		/*                           */

		this.btnValider.addActionListener  ((e) -> this.valider  ());
		this.btnRetour.addActionListener   ((e) -> this.annuler  ());
	}
	


	/**
	 * Annule les modifications et retourne à l'acceuil paramétrage.
	 */
	private void annuler() {
		this.frame.changePanel(new PanelParametre(this.frame));
		Controleur.getControleur().annuler();
	}
	
	/**
	 * Valide les modifications et retourne à l'acceuil paramétrage.
	 */
	private void valider() {
		boolean isOk = true;
		if (this.tblGrilleDonnees.isEditing()){
			this.tblGrilleDonnees.getCellEditor().stopCellEditing();
			
		}
		if(isOk){
			this.frame.changePanel(new PanelParametre(this.frame));
			Controleur.getControleur().enregistrer();
		}
		
	}



	/**
	 * Remet à jour les donnée.
	 */
	public void maj() {
		this.tblGrilleDonnees.setModel(new GrilleCatHeures()); 
	}
}
