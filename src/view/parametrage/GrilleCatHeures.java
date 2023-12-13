package view.parametrage;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import controleur.Controleur;
import model.CategorieHeures;

public class GrilleCatHeures extends AbstractTableModel {

	private String[] tabEntetes;
	private Object[][] tabDonnees;

	public GrilleCatHeures() {

		CategorieHeures cat;
		this.tabEntetes = new String[] { "Libellé", "Coef" };


		Controleur ctrl = Controleur.getControleur();
		List<CategorieHeures> lstClients = ctrl.getCategorieHeures();

		tabDonnees = new Object[lstClients.size()][this.tabEntetes.length];

		for (int lig = 0; lig < lstClients.size(); lig++) {
			cat = lstClients.get(lig);

			tabDonnees[lig][0] = cat.getlibCatHeur();
			tabDonnees[lig][1] = cat.getcoefCatHeur();
		}

		// Taille

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
