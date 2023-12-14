package view.module;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Affectations;
import model.modules.Ressource;

public class GrilleRessources extends AbstractTableModel {

	private String[] tabEntetes;
	private Object[][] tabDonnees;

	public GrilleRessources() {
		List<Affectations> listAffectations = new ArrayList<>();
		this.tabDonnees = new Object[listAffectations.size()][5];

		for (int lig = 0; lig < listAffectations.size(); lig++) {

			tabDonnees[lig][0] = cat.getlibCatInt();
			tabDonnees[lig][1] = cat.getCoefCatInt();
			tabDonnees[lig][2] = cat.getHeureMinCatInt();
			tabDonnees[lig][3] = cat.getHeureMaxCatInt();
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
