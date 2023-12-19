package view.module;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controleur.Controleur;
import model.Affectations;
import model.modules.Module;

public class GrilleRessources extends AbstractTableModel {

	private String[] tabEntetes;
	private Object[][] tabDonnees;
	private List<String> prenomIntervenant;
	private List<Module> moduleIntervenant;

	public GrilleRessources() {
		List<Affectations> listAffectations = Controleur.getControleur().getAffectations();
		prenomIntervenant = new ArrayList<>();
		moduleIntervenant = new ArrayList<>();
		this.tabDonnees = new Object[listAffectations.size()][6];

		for (int lig = 0; lig < listAffectations.size(); lig++) {
			Affectations affectations = listAffectations.get(lig);
			if (affectations.getModule().getClass().getSimpleName().equals("Ressource")) {
				List<Integer> listInfosHeure = affectations.getModule().getHeures().get(affectations.getCategorieHeures());
				this.tabDonnees[lig][0] = affectations.getIntervenant().getNomIntervenant();
				this.tabDonnees[lig][1] = affectations.getCategorieHeures().getlibCatHeur();
				this.tabDonnees[lig][2] = affectations.getNbSemaine();
				this.tabDonnees[lig][3] = affectations.getNbGroupe() + " | "
						+ listInfosHeure.get(3);
				this.tabDonnees[lig][4] = affectations.getNbSemaine()
						* affectations.getNbGroupe()
						* listInfosHeure.get(2)
						* affectations.getCategorieHeures().getcoefCatHeur();
				this.tabDonnees[lig][5] = affectations.getCommentaire();
				prenomIntervenant.add(affectations.getIntervenant().getPrenomIntervenant());
				moduleIntervenant.add(affectations.getModule());
			}

		}
		this.tabEntetes = new String[] { "Intervenants", "Type", "Nb sem", "nb Gp/nb h", "tot eqtd", "commentaire" };
	}

	public int getRowCount() {
		return this.tabDonnees.length;
	}

	public int getColumnCount() {
		return this.tabEntetes.length;
	}

	public String getColumnName(int col) {
		return this.tabEntetes[col];
	}

	public Object getValueAt(int row, int col) {
		return this.tabDonnees[row][col];
	}

	public boolean isCellEditable(int row, int col) {
		if(col == 2){
			if(getValueAt(row, col-1).equals("HP")){
				return false;
			}
		}
		return true;
	}

	public void setValueAt(Object value, int row, int col) {

		if (value == this.tabDonnees[row][col])
			return;

		if(isCellEditable(row, col)){
			String nomInter  = (String)  this.tabDonnees[row][0];
			String type      = (String)  this.tabDonnees[row][1];
			int    nbSem     = (Integer) this.tabDonnees[row][2];
			int    nbGp      = (Integer) this.tabDonnees[row][3];
			//int    hTot      = (Integer) this.tabDonnees[row][4];//il sert à quoi ?
			String com       = (String)  this.tabDonnees[row][5];

			switch (col) {
				case 0:
					nomInter = (String) value;
					break;

				case 1:
					type = (String) value;
					break;

				case 2:
					nbSem = (Integer) value;
					break;

				case 3:
					nbGp = (Integer) value;
					break;

				/*case 4:
					hTot = (Integer) value;
					break;*/

				case 5:
					com = (String) value;
					break;

				default:
					break;
			}

			if (nbSem < 0 || nbGp < 0 )

				return;
			
			if(Controleur.getControleur().modifAffectation(row, nomInter,prenomIntervenant.get(row),moduleIntervenant.get(row),type, nbSem, nbGp, com))
				this.tabDonnees[row][col] = value;
		}
	}
}
