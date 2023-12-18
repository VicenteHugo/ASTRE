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
import model.CategorieIntervenant;
import model.Intervenants;
import model.modules.*;
import model.modules.Module;
import view.accueil.FrameAccueil;


public class PanelAddRessourceIntervenant extends JPanel {
	private JLabel lblErrCoef;
	private JLabel lblErrHeurMax;
	private JLabel lblErrHeurMin;
	
	private JTextField txtNomIntervenant;
	private JTextField txtPrenomIntervenant;
	private JComboBox<String> boxCategorie;	
	private JTextField txtNbSemaine;
	private JTextField txtNbGroupe;
    private JTextField txtTotal;
    private JTextField txtCommentaire;

	private JButton btnValider;
	private JButton btnAnnuler;


	private FrameAccueil frame;
	private Frame frameM;
	private PanelRessources panel;
	private String code;

	public PanelAddRessourceIntervenant (PanelRessources panel,FrameAccueil frame, Frame frameM) {
		this.panel = panel;
		this.frame  = frame;
		this.frameM = frameM;


		//Création
		ArrayList<CategorieHeures> l = Controleur.getControleur().getCategorieHeures();
		this.boxCategorie = new JComboBox<String>();
		for(int i=0; i < l.size(); i++){
			this.boxCategorie.addItem(l.get(i).getlibCatHeur());
		}
		this.txtNomIntervenant    = new JTextField(10);
		this.txtPrenomIntervenant = new JTextField(10);
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
		
		panelCentre.add(new JLabel("Nom de l'intervenant : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtNomIntervenant, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		panelCentre.add(new JLabel("Prénom de l'intervenant : "), gbc);
		gbc.gridx++;
		panelCentre.add(this.txtPrenomIntervenant, gbc);

		
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
			Intervenants i = null;
			CategorieHeures categ = null;
			Module module = null;
			int nbSemaine = Integer.parseInt(this.txtNbSemaine.getText());
			int nbGroupe  = Integer.parseInt(this.txtNbGroupe.getText());
			String code = panel.getCode();
			for(Intervenants inter : Controleur.getControleur().getIntervenants()){
				if(inter.getNomIntervenant().equals(this.txtNomIntervenant.getText()) && 
				inter.getPrenomIntervenant().equals(this.txtPrenomIntervenant.getText())){
					i = inter;
					break;
				}
			}
			for(CategorieHeures ch : Controleur.getControleur().getCategorieHeures()){
				if(ch.getlibCatHeur().equals(this.boxCategorie.getSelectedItem())){
					categ =ch;
				}
			}
			for(Module m : Controleur.getControleur().getModules()){
				if(m.getCode() == code){
					module = m;
					break;
				}
			}
			if(i != null){
				if(nbGroupe < 0 || nbSemaine < 0){
					JOptionPane.showMessageDialog(this,"Le nombre de groupe et le nombre de semaine doivent être supérieur à 0");
				}else{
					Affectations affectations = new Affectations(i, module,categ,nbSemaine, nbGroupe,this.txtCommentaire.getText());
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
