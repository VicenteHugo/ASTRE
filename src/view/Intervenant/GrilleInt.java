package view.Intervenant;

import javax.swing.table.AbstractTableModel;
import model.CategorieIntervenant;

public class GrilleInt extends AbstractTableModel {

	private String[] tabEntetes;
	private Object[][] tabDonnees;

	public GrilleInt() {
		CategorieIntervenant cat;
		this.tabDonnees = new Object[][] {
				{ "info_ec", "Boukachour", "Hadhoum", 192, 364, 1, 106.5, 18.0, 0.0, 124.5, 0.0, 0.0, 0.0, 0.0, 124.5 },
				{ "vaca_pro", "Colignon", "Thomas", 120, 187, "2/3", 40.3, 0.0, 0.0, 40.3, 0.0, 0.0, 0.0, 0.0,
						40.3 }, };

		/*
		 * List<CategorieIntervenants> lstClients = ctrl.getCatInt();
		 * 
		 * tabDonnees = new Object[lstClients.size()][4];
		 * 
		 * for ( int lig=0; lig<lstClients.size(); lig++)
		 * {
		 * cat = lstClients.get(lig);
		 * 
		 * tabDonnees[lig][0] = cat.getlibCatInt ();
		 * tabDonnees[lig][1] = cat.getCoefCatInt ();
		 * tabDonnees[lig][2] = cat.getHeureMinCatInt();
		 * tabDonnees[lig][3] = cat.getHeureMaxCatInt();
		 * }
		 */

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
