package view.module;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.Controleur;
import model.Affectations;
import model.CategorieHeures;
import model.Intervenants;
import model.modules.Module;
import view.accueil.FrameAccueil;


public class PanelAddRessourceIntervenant extends JPanel {
	//si pas utiliser ne pas créer d'attribut
	private JLabel lblErrCoef;
	private JLabel lblErrHeurMax;
	private JLabel lblErrHeurMin;
	
	private JTextField txtNomIntervenant;
	private JTextField txtPrenomIntervenant;
	private JComboBox<String> boxCategorie;
	private JComboBox<String> boxIntervenant;
	private JTextField txtNbSemaine;
	private JTextField txtNbGroupe;
    private JTextField txtTotal;
    private JTextField txtCommentaire;

	private JButton btnValider;
	private JButton btnAnnuler;


	private FrameAccueil frame;
	private Frame frameM;
	private PanelRessources panel;
	private Module mod;

	public PanelAddRessourceIntervenant (PanelRessources panel,FrameAccueil frame, Frame frameM,Module mod) {
		this.panel = panel;
		this.frame  = frame;
		this.frameM = frameM;
		this.mod = mod;


		//Création
		ArrayList<CategorieHeures> l = Controleur.getControleur().getCategorieHeures();
		this.boxCategorie = new JComboBox<String>();
		for(int i=0; i < l.size(); i++){
			this.boxCategorie.addItem(l.get(i).getlibCatHeur());
		}
		ArrayList<Intervenants> i = Controleur.getControleur().getIntervenants();
		this.boxIntervenant = new JComboBox<String>();
		for(int j= 0;  j < i.size(); j++ ){
			this.boxIntervenant.addItem(i.get(j).getNomIntervenant() + " " + i.get(j).getPrenomIntervenant());
		} 
		this.txtNbSemaine         = new JTextField(3);
		this.txtNbGroupe          = new JTextField(3);
		this.txtCommentaire       = new JTextField(15);

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
		
		panelCentre.add(new JLabel("Intervenant : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.boxIntervenant, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		panelCentre.add(new JLabel("Type : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.boxCategorie, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		panelCentre.add(new JLabel("Nombre de semaine : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtNbSemaine, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		panelCentre.add(new JLabel("Nombre de groupe : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtNbGroupe, gbc);

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
		this.btnValider.addActionListener((e)->{
			Intervenants intervenant = null;
			CategorieHeures categ = null;
			int nbSemaine = Integer.parseInt(this.txtNbSemaine.getText());
			int nbGroupe  = Integer.parseInt(this.txtNbGroupe.getText());
			String[] parties = ((String) this.boxIntervenant.getSelectedItem()).split(" ");
			String nomIntervenant = parties[0];
			String prenomIntervenant = parties[1];
			

			for(Intervenants inter : Controleur.getControleur().getIntervenants()){
				if(inter.getNomIntervenant().equals(nomIntervenant) && 
				inter.getPrenomIntervenant().equals(prenomIntervenant)){
					intervenant = inter;
					break;
				}
			}

			for(CategorieHeures ch : Controleur.getControleur().getCategorieHeures()){
				if(ch.getlibCatHeur().equals(this.boxCategorie.getSelectedItem())){
					categ =ch;
				}
			}
			
			if(i != null){
				if(nbGroupe < 0 || nbSemaine < 0){
					JOptionPane.showMessageDialog(this,"Le nombre de groupe et le nombre de semaine doivent être supérieur à 0");
				}else{
					Affectations affectations = new Affectations(intervenant, this.mod,categ,nbSemaine, nbGroupe,this.txtCommentaire.getText());
					System.out.println(affectations);
					Controleur.getControleur().ajouterAffectation(affectations);
					this.frameM.dispose();
				}
			}else{
				JOptionPane.showMessageDialog(this,"L'intervenant rentré n'existe pas");
			}
			
			this.panel.maj();	
		});
		this.btnAnnuler.addActionListener((e)->this.frameM.dispose());
	}
}
