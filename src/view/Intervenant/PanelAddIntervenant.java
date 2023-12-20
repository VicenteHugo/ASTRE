package view.Intervenant;

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
import model.CategorieIntervenant;
import model.Intervenants;
import view.JTextFieldNumber;
import view.accueil.FrameAccueil;

public class PanelAddIntervenant extends JPanel {
	private JComboBox<String> boxCategorie;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextFieldNumber txtHServ;
	private JTextFieldNumber txtHMax;
	private JTextFieldNumber txtCoefTP;

	private JButton btnValider;
	private JButton btnAnnuler;

	private Frame frameM;

	private PanelIntervenants panelIntervenants;
	private boolean isValid = true;

	public PanelAddIntervenant(PanelIntervenants p,FrameAccueil frame, Frame frameM) {
		this.panelIntervenants = p;
		this.frameM = frameM;

		// Création
		ArrayList<CategorieIntervenant> l = Controleur.getControleur().getCategorieIntervenants();
		this.boxCategorie = new JComboBox<String>();
		for(int i=0; i < l.size(); i++){
			this.boxCategorie.addItem(l.get(i).getCodeCatInt());
		}


		this.txtNom = new JTextField(10);
		this.txtPrenom = new JTextField(10);
		this.txtHServ  = new JTextFieldNumber(5);
		this.txtHMax   = new JTextFieldNumber(5);
		this.txtCoefTP = new JTextFieldNumber(3);
		this.txtCoefTP.setFloat(true);

		this.btnAnnuler = new JButton("Annuler");
		this.btnValider = new JButton("Valider");

		// Layout
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
		panelCentre.add(this.boxCategorie, gbc);

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

		// Ajout
		this.add(panelCentre, BorderLayout.CENTER);
		this.add(panelBas, BorderLayout.SOUTH);

		// Activation
		
		this.btnValider.addActionListener((e) -> {
			this.isValid = true;
			CategorieIntervenant categ = null;
			for (CategorieIntervenant ch : Controleur.getControleur().getCategorieIntervenants()) {
				if (ch.getCodeCatInt().equals(this.boxCategorie.getSelectedItem())) {
					categ = ch;
					break;
				}
			}
			for(Intervenants inter : Controleur.getControleur().getIntervenants()){
				if(inter.getNomIntervenant().equals(this.txtNom.getText()) && inter.getPrenomIntervenant().equals(this.txtPrenom.getText())){
					JOptionPane.showMessageDialog(this, "Utilisateur déja crée, changer de nom ou de prénom");
					isValid = false;
					break;
				}

			}
			if (categ != null && isValid) {
				try {
					int heuresService = Integer.parseInt(this.txtHServ.getText());
					int heuresMax = Integer.parseInt(this.txtHMax.getText());
					float coef = (float) Integer.parseInt(this.txtCoefTP.getText());
					if(heuresMax > heuresService){
						Intervenants inter = new Intervenants(categ, this.txtNom.getText(), this.txtPrenom.getText(),
							heuresService,heuresMax,coef);
						Controleur.getControleur().ajouterIntervenant(inter);
						this.frameM.dispose();
					}else{
						JOptionPane.showMessageDialog(this,"Le nombre d'heure de services doit être inferieur à son nombre maximal");
					}
					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs valides pour les heures de service et les heures maximales.");
				}
			} else if(isValid){
				JOptionPane.showMessageDialog(this, "Catégorie introuvable.");
			}
			this.panelIntervenants.maj();
		});
		this.btnAnnuler.addActionListener((e) -> this.frameM.dispose());
	}
}
