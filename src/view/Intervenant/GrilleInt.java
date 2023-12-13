package view.Intervenant;

import java.util.ArrayList;
import java.util.List;
import model.Intervenants;
import model.Semestres;
import model.modules.*;
import model.modules.Module;

import javax.swing.table.AbstractTableModel;

import controleur.Controleur;
import model.CategorieIntervenant;

public class GrilleInt extends AbstractTableModel {

	private String[] tabEntetes;
	private Object[][] tabDonnees;

	public GrilleInt() {

		List<Intervenants> lstIntervenant = Controleur.getControleur().getIntervenants();

		tabDonnees = new Object[lstIntervenant.size()][14];

		for (int lig = 0; lig < lstIntervenant.size(); lig++) {
			Intervenants intervenants = lstIntervenant.get(lig);
			List<Integer> listeSemaine = new ArrayList<>();

			for (Semestres semestres : Controleur.getControleur().getSemestres()) {
				for (Module module : Controleur.getControleur().getModules(semestres)) {

				}
			}

			tabDonnees[lig][0] = intervenants.getCategorieIntervenant().getLibCatInt();
			tabDonnees[lig][1] = intervenants.getNomIntervenant();
			tabDonnees[lig][2] = intervenants.getPrenomIntervenant();
			tabDonnees[lig][3] = intervenants.getServices();
			tabDonnees[lig][4] = intervenants.getMaxHeures();
			tabDonnees[lig][5] = intervenants.getCoefficient();

			tabDonnees[lig][9] = intervenants.getSommeSemImpaire();
			tabDonnees[lig][13] = intervenants.getSommeSemPaire();

			tabDonnees[lig][14] = intervenants.getSommeSemImpaire() + intervenants.getSommeSemPaire();
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
