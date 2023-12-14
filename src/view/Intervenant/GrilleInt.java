package view.Intervenant;

import java.util.ArrayList;
import java.util.List;
import model.Intervenants;
import model.Semestres;
import model.modules.Module;

import javax.swing.table.AbstractTableModel;

import controleur.Controleur;
import model.Affectations;

public class GrilleInt extends AbstractTableModel {

	private String[] tabEntetes;
	private Object[][] tabDonnees;

	public GrilleInt() {

		List<Intervenants> lstIntervenant = Controleur.getControleur().getIntervenants();

		tabDonnees = new Object[lstIntervenant.size()][15];

		for (int lig = 0; lig < lstIntervenant.size(); lig++) {

			Intervenants intervenants = lstIntervenant.get(lig);
			List<Integer> listeSemaine = new ArrayList<>();
			int nbHeure;

			for (Semestres semestres : Controleur.getControleur().getSemestres()) {
				nbHeure = 0;
				for (Module module : Controleur.getControleur().getModules(semestres)) {
					for (Affectations affectations : Controleur.getControleur().getAffectations()) {
						if (affectations.getModule().equals(module)
								&& intervenants.equals(affectations.getIntervenant())) {
							nbHeure += affectations.getNbSemaine() * affectations.getNbGroupe();
						}
					}

				}
				listeSemaine.add(nbHeure);
			}


			tabDonnees[lig][0] = intervenants.getCategorieIntervenant().getCodeCatInt();
			tabDonnees[lig][1] = intervenants.getNomIntervenant();
			tabDonnees[lig][2] = intervenants.getPrenomIntervenant();
			tabDonnees[lig][3] = intervenants.getServices();
			tabDonnees[lig][4] = intervenants.getMaxHeures();
			tabDonnees[lig][5] = intervenants.getCoefficient();
			tabDonnees[lig][6] = listeSemaine.get(0);
			tabDonnees[lig][7] = listeSemaine.get(2);
			tabDonnees[lig][8] = listeSemaine.get(4);
			tabDonnees[lig][9] = listeSemaine.get(0) + listeSemaine.get(2) + listeSemaine.get(4);
			tabDonnees[lig][10] = listeSemaine.get(1);
			tabDonnees[lig][11] = listeSemaine.get(3);
			tabDonnees[lig][12] = listeSemaine.get(5);
			tabDonnees[lig][13] = listeSemaine.get(1) + listeSemaine.get(3) + listeSemaine.get(5);
			tabDonnees[lig][14] = listeSemaine.get(0) + listeSemaine.get(2) + listeSemaine.get(4) + listeSemaine.get(1)
					+ listeSemaine.get(3) + listeSemaine.get(5);
		}

		this.tabEntetes = new String[] { "Catégorie", "Nom", "Prénom", "hServ", "hMax", "CoefTP", "S1", "S3", "S5",
				"sTot", "S2", "S4", "S6", "sTot", "Total" };

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

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	public boolean isCellEditable(int row, int col) {
		return true;
	}

	public void setValueAt(Object value, int row, int col) {

		this.tabDonnees[row][col] = value;

	}
}
