package view.module;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import view.JButtonStyle;
import view.JComboBoxStyle;
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


public class PanelAddRessourceIntervenant extends JPanel{

	//certains attribut ne sont pas instancié
	private JComboBoxStyle<String> boxCategorie;
	private JComboBoxStyle<String> boxIntervenant;
	private JTextFieldNumber txtNbGroupe;
    private JTextField txtCommentaire;

	private JButtonStyle btnValider;
	private JButtonStyle btnAnnuler;

	private JLabel labelHeure;


	private Frame frameM;
	private Module mod;

	public PanelAddRessourceIntervenant (PanelRessources panel,FrameAccueil frame, Frame frameM,Module mod) {
		this.frameM = frameM;
		this.mod = mod;


		//Création
		ArrayList<CategorieHeures> l = Controleur.getControleur().getCategorieHeures();
		this.boxCategorie = new JComboBoxStyle<String>();
		for(int i=0; i < l.size(); i++){
			if(l.get(i).getlibCatHeur().equals("TP") || l.get(i).getlibCatHeur().equals("TD") || l.get(i).getlibCatHeur().equals("CM") ||l.get(i).getlibCatHeur().equals("HP")){
				this.boxCategorie.addItem(l.get(i).getlibCatHeur());
			}
		}
		ArrayList<Intervenants> lstInter = Controleur.getControleur().getIntervenants();
		this.boxIntervenant = new JComboBoxStyle<String>();
		for(int j= 0;  j < lstInter.size(); j++ ){
			this.boxIntervenant.addItem(lstInter.get(j).getNomIntervenant() + " " + lstInter.get(j).getPrenomIntervenant());
		} 

		this.txtCommentaire       = new JTextField(15);
		this.txtNbGroupe = new JTextFieldNumber(5);

		this.labelHeure = new JLabel("Nombre de groupe : ");

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
		panelCentre.add(labelHeure, gbc);
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

		this.boxCategorie.addActionListener(new ActionListener() {
           	
			@Override
            public void actionPerformed(ActionEvent e) {
                String categorieSelectionnee = (String) boxCategorie.getSelectedItem();
                if (categorieSelectionnee.equals("HP")) {
                    labelHeure.setText("Nombre d'heure : ");
                }else{
					labelHeure.setText("Nombre de groupe : ");
				}
            }
        });

		//Activation
		this.btnValider.addActionListener((e)->{

			if (this.txtNbGroupe.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Entrez des données valides", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}


			Intervenants intervenant = Controleur.getControleur().getIntervenants(this.boxIntervenant.getSelectedIndex());
			CategorieHeures categ = null;

			int nbGroupe  = Integer.parseInt(this.txtNbGroupe.getText());
			int nbSemaine = 1;

			boolean isOk = false;

			for(CategorieHeures ch : Controleur.getControleur().getCategorieHeures()){
				if(ch.getlibCatHeur().equals(this.boxCategorie.getSelectedItem())){
					categ =ch;
				}
			}	
			if (nbGroupe < 0 ) {
				JOptionPane.showMessageDialog(this, "Le nombre de groupe doit être supérieur à 0");
			} else {
				String categerie = categ.getlibCatHeur();
				int nbGroupeTotal = calculNbGroupe(nbGroupe, categ);
				if(!(categerie.equals("HP"))){
					if(categerie.equals("TD")){
						if(nbGroupe > mod.getSemestres().getNbGpTdSem() || nbGroupeTotal > mod.getSemestres().getNbGpTdSem()){
							JOptionPane.showMessageDialog(this, "Trop de groupe " +  categerie +" sont assignés");
						}else{
						ArrayList<Integer> list = (ArrayList<Integer>) mod.getHeures().get(categ);
						nbSemaine = list.get(1);
						isOk = true;
						}
					}else{
						if(nbGroupe > mod.getSemestres().getNbGpTpSem()|| nbGroupeTotal > mod.getSemestres().getNbGpTpSem()){
							JOptionPane.showMessageDialog(this, "Trop de groupe " +  categerie +" sont assignés");
						}else{
							ArrayList<Integer> list = (ArrayList<Integer>) mod.getHeures().get(categ);
							nbSemaine = list.get(1);
							isOk = true;
						}
					}
					
				}else{
					isOk = true;
				}
				if(!(categ.getlibCatHeur().equals("HP"))){
					int heure =  (int)Math.ceil((mod.getHeures().get(categ).get(2)*nbGroupe*nbSemaine)* categ.getcoefCatHeur());
					int nbHeureTotal = nbHeureTotal(intervenant,heure);
					if(heure > intervenant.getMaxHeures()|| nbHeureTotal > intervenant.getMaxHeures()){
							JOptionPane.showMessageDialog(this, "Attention trop d'heures sont assignés");
					}	
				}
				if(isOk){
					Affectations affectations = new Affectations(intervenant, this.mod, categ, nbSemaine, nbGroupe, this.txtCommentaire.getText());
					Controleur.getControleur().ajouterAffectation(affectations);
					this.frameM.dispose();
					panel.tblGrilleDonnees.setModel(new GrilleRessources(this.mod));
					panel.focusLost(null);

				}
			}
			panel.annulerAjout();
		});

		this.btnAnnuler.addActionListener((e)->panel.annulerAjout());
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

	private int nbHeureTotal(Intervenants inter,int nbHeure){
		int total = 0;
		List<Affectations> lAffectations = Controleur.getControleur().getAffectations(mod);
		for(Affectations a : lAffectations){
			if(a.getIntervenant().equals(inter)){
				total+= a.getHeureEqtd();
			}
		}
		total += nbHeure;
		return total;
	}

}
