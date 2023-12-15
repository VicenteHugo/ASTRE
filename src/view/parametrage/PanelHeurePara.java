package view.parametrage;

// AWT
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

// SWING
import javax.swing.*;

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
	private JButton btnValider;
	/** Boutton permettant d'annuler les modifications. Renvoie à l'acceuil. */
	private JButton btnRetour;

	/** Boutton permettant d'ajouter des catégories d'heures. Créer un panelAddCatHeure. */
	private JButton btnAjouter;
	/** Boutton permettant de supprimer des catégories d'heures.*/
	private JButton btnSupprimer;



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
		this.btnValider = new JButton("Valider");
		this.btnRetour = new JButton("Annuler");
		this.btnAjouter = new JButton("Ajouter");
		this.btnSupprimer = new JButton("Supprimer");



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
		panelBtn.add(this.btnAjouter);
		panelBtn.add(this.btnSupprimer);
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
        this.btnAjouter  .setMinimumSize(buttonSize);
        this.btnSupprimer.setMinimumSize(buttonSize);

        this.btnValider  .setPreferredSize(buttonSize);
        this.btnRetour   .setPreferredSize(buttonSize);
        this.btnAjouter  .setPreferredSize(buttonSize);
        this.btnSupprimer.setPreferredSize(buttonSize);

        Color coul = Color.decode("0xD0D0D0");
        this.btnValider  .setBackground(coul);
        this.btnRetour   .setBackground(coul);
        this.btnAjouter  .setBackground(coul);
        this.btnSupprimer.setBackground(coul);

		// JTable
		Dimension tableSize = this.tblGrilleDonnees.getPreferredSize();
        this.tblGrilleDonnees.getColumn(0).setPreferredWidth(Math.round((tableSize.width - 250)* 0.70f));
        this.tblGrilleDonnees.getColumn(1).setPreferredWidth(Math.round((tableSize.width - 250)* 0.30f));



		this.tblGrilleDonnees.getColumnModel().getColumn(0).setMaxWidth((int) (tailleTbl*0.75));
		this.tblGrilleDonnees.getColumnModel().getColumn(1).setMaxWidth((int) (tailleTbl*0.25));



		/*                           */
		/* Activation des composants */
		/*                           */

		this.btnValider.addActionListener  ((e) -> this.valider  ());
		this.btnRetour.addActionListener   ((e) -> this.annuler  ());
		this.btnSupprimer.addActionListener((e) -> this.supprimer());
		this.btnAjouter.addActionListener  ((e) -> this.ajouter  ());
	}
	
	private void annuler() {
		this.frame.changePanel(new PanelParametre(this.frame));
		Controleur.getControleur().annuler();
	}
	
	private void valider() {
		this.frame.changePanel(new PanelParametre(this.frame));
		Controleur.getControleur().enregistrer();
	}

	private void ajouter() {
		JFrame f = new JFrame();
		f.add(new PanelAddCatHeures(this, f));
		f.setTitle("Ajout d'une catégorie");
		f.pack();
		f.setLocationRelativeTo(null);
		f.setAlwaysOnTop(true);
		f.setVisible(true);
	}

	private void supprimer() {

		int ind = this.tblGrilleDonnees.getSelectedRow();
		Controleur.getControleur().supprimerCategorieHeure(ind);


		this.maj();

		int max = this.tblGrilleDonnees.getRowCount();

		if (ind >= 0 && ind < max)
			this.tblGrilleDonnees.setRowSelectionInterval(ind, ind);

		if (ind >= max && max != 0)
			this.tblGrilleDonnees.setRowSelectionInterval(max - 1, max - 1);
	}



	public void maj() {
		this.tblGrilleDonnees.setModel(new GrilleCatHeures()); 
	}
}
