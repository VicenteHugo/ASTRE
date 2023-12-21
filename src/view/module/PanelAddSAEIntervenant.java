package view.module;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import view.JButtonStyle;

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
import view.JTextFieldNumber;
import view.accueil.FrameAccueil;



public class PanelAddSAEIntervenant extends JPanel {	

	private JTextFieldNumber txtNbHeure;
    private JTextField txtCommentaire;

	private JComboBox<String> boxCategorie;
	private JComboBox<String> boxIntervenant;

	private JButtonStyle btnValider;
	private JButtonStyle btnAnnuler;


	private Frame frameM;
	private PanelSAE panel;
	private Module mod;

	public PanelAddSAEIntervenant (PanelSAE panel, FrameAccueil frame, Frame frameM, Module mod2) {
		this.frameM = frameM;
		this.panel = panel;
		this.mod = mod2;

		//Création

		ArrayList<CategorieHeures> l = Controleur.getControleur().getCategorieHeures();
		this.boxCategorie = new JComboBox<String>();
		for(int i=0; i < l.size(); i++){
			if(l.get(i).getlibCatHeur().equals("SAE") || l.get(i).getlibCatHeur().equals("RHE")){
				this.boxCategorie.addItem(l.get(i).getlibCatHeur());
			}
		}
		ArrayList<Intervenants> lstInter = Controleur.getControleur().getIntervenants();
		this.boxIntervenant = new JComboBox<String>();
		for(int j= 0;  j < lstInter.size(); j++ ){
			this.boxIntervenant.addItem(lstInter.get(j).getNomIntervenant() + " " + lstInter.get(j).getPrenomIntervenant());
		} 


		this.txtNbHeure    = new JTextFieldNumber(3);
		this.txtCommentaire  = new JTextField(15);

		this.btnAnnuler = new JButtonStyle("Annuler");
		this.btnValider = new JButtonStyle("Valider");

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
		panelCentre.add(this.boxIntervenant, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		panelCentre.add(new JLabel("Type : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.boxCategorie, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		panelCentre.add(new JLabel("Nombre d'heure : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtNbHeure, gbc);

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
			Intervenants intervenant = Controleur.getControleur().getIntervenants(this.boxIntervenant.getSelectedIndex());
			CategorieHeures categ = null;
			
			int nbHeure  = Integer.parseInt(this.txtNbHeure.getText());
			boolean isOk = false;

			for(CategorieHeures ch : Controleur.getControleur().getCategorieHeures()){
				if(ch.getlibCatHeur().equals(this.boxCategorie.getSelectedItem())){
					categ =ch;
				}
			}
			if (nbHeure < 0 ) {
				JOptionPane.showMessageDialog(this, "Le nombre d'heure doit être supérieur à 0");
			} else {
				String categerie = categ.getlibCatHeur();
				int nbHeureTotal = calculNbGroupe(nbHeure, categ);
				if(categerie.equals("SAE")){
					if(nbHeure > Integer.parseInt(panel.txtHeureEtdSaePN.getText())   ||   nbHeureTotal > Integer.parseInt(panel.txtHeureEtdSaePN.getText())){
						JOptionPane.showMessageDialog(this, "Trop d'heure assigné par rapport  à la " +  categerie +" choisis");
					}else{
					isOk = true;
					}
				}else{
					if( nbHeure > Integer.parseInt(panel.txtHeureEtdTutPN.getText())   ||   nbHeureTotal > Integer.parseInt(panel.txtHeureEtdTutPN.getText())){
						JOptionPane.showMessageDialog(this, "Trop d'heure assigné par rapport  à la " +  categerie +" choisis");
					}else{
						isOk = true;
					}

				}
				if(isOk){
					int nbSemaine = 1;
					Affectations affectations = new Affectations(intervenant, this.mod, categ, nbSemaine, nbHeure, this.txtCommentaire.getText());
					Controleur.getControleur().ajouterAffectation(affectations);
					this.frameM.dispose();
					panel.tblGrilleDonnees.setModel(new GrilleSAE(this.mod));
					this.panel.focusLost(null);		
				}
			}	
		});

		this.btnAnnuler.addActionListener((e)->this.frameM.dispose());
	}

	private int calculNbGroupe(int nbGroupe,CategorieHeures categ){
		List<Affectations> listAffectations = Controleur.getControleur().getAffectations(mod);
		int retour = 0;

		for(Affectations a : listAffectations){
			if(a.getCategorieHeures().getlibCatHeur().equals(categ.getlibCatHeur())){
				retour+= a.getNbGroupe();
			}
		}
		retour += nbGroupe;
		return retour;
	}
}
