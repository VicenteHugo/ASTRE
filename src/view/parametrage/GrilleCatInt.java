package view.parametrage;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import controleur.Controleur;
import model.CategorieIntervenant;
import model.CategorieIntervenant;

public class GrilleCatInt extends AbstractTableModel {

	private String[] tabEntetes;
	private Object[][] tabDonnees;

	public GrilleCatInt() {

		Controleur ctrl = Controleur.getControleur();

		CategorieIntervenant cat;
		this.tabEntetes = new String[] { "Code", "Libellé", "Coef", "Heures minimum", "Heures maximum" };

		List<CategorieIntervenant> lstCatInt = ctrl.getCategorieIntervenants();

		tabDonnees = new Object[lstCatInt.size()][this.tabEntetes.length];

		for (int lig = 0; lig < lstCatInt.size(); lig++) {
			cat = lstCatInt.get(lig);

			tabDonnees[lig][0] = cat.getLibCatInt();
			tabDonnees[lig][1] = cat.getCoefCatInt();
			tabDonnees[lig][2] = cat.getHeureMinCatInt();
			tabDonnees[lig][3] = cat.getHeureMaxCatInt();
		}

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
