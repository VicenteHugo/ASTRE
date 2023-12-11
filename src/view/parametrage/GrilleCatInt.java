package view.parametrage;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import controleur.Controleur;
import model.CategorieIntervenants;

public class GrilleCatInt extends AbstractTableModel {

	private String[]   tabEntetes;
	private Object[][] tabDonnees;

	public GrilleCatInt ()
	{
		CategorieIntervenants cat;
		List<CategorieIntervenants> lstClients = ctrl.getCatInt();

		tabDonnees = new Object[lstClients.size()][4];

		for ( int lig=0; lig<lstClients.size(); lig++)
		{
			cat = lstClients.get(lig);

			tabDonnees[lig][0] = cat.getlibCatInt     ();
			tabDonnees[lig][1] = cat.getCoefCatInt    ();
			tabDonnees[lig][2] = cat.getHeureMinCatInt();
			tabDonnees[lig][3] = cat.getHeureMaxCatInt();
		}

		this.tabEntetes = new String[]   {"Libellé", "Coef", "Heures minimum", "Heures maximums"};

	}

	public int    getRowCount   () {return this.tabEntetes.length;}
	public int    getColumnCount() {return this.tabDonnees.length;}
	
	public Object getValueAt    (int rowIndex, int columnIndex) {return this.tabDonnees[rowIndex][columnIndex];}
	public Class  getColumnClass(int c) {return getValueAt(0, c).getClass();}
	
	public boolean isCellEditable(int row, int col) { return col < 6;}
	
	// public void setValueAt(Object value, int row, int col) {
	// 	boolean bRet;

	// 	if (col == 2) {
	// 		bRet = this.ctrl.majPremiumClient(row, (Boolean) value);
	// 		if (bRet) {
	// 			this.tabDonnees[row][col] = value;
	// 			this.fireTableCellUpdated(row, col);
	// 		}
	// 	}

	// }
}
