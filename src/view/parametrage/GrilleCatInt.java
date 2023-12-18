package view.parametrage;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import controleur.Controleur;
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

		for (int lig = 0; lig < lstCatInt.size(); lig++) 
		{
			cat = lstCatInt.get(lig);

			tabDonnees[lig][0] = cat.getCodeCatInt();
			tabDonnees[lig][1] = cat.getLibCatInt();
			tabDonnees[lig][2] = cat.getCoefCatInt();
			tabDonnees[lig][3] = cat.getHeureMinCatInt();
			tabDonnees[lig][4] = cat.getHeureMaxCatInt();
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

		if (value == this.tabDonnees[row][col])
			return;

		String code = (String)  this.tabDonnees[row][0];
		String lib  = (String)  this.tabDonnees[row][1];
		float  coef = (Float)   this.tabDonnees[row][2];
		int    hMin = (Integer) this.tabDonnees[row][3];
		int    hMax = (Integer) this.tabDonnees[row][4];

		switch (col) {
			case 0:
				code = (String) value;
				break;

			case 1:
				lib = (String) value;
				break;

			case 2:
				coef = (Float) value;
				break;

			case 3:
				hMin = (Integer) value;
				break;

			case 4:
				hMax = (Integer) value;
				break;

			default:
				break;
		}

		if (hMax < hMin || hMax < 0 || hMin < 0 || coef < 0)
			return;

		if (Controleur.getControleur().modifCategorieIntervenants(row, code, lib, coef, hMin, hMax))
			this.tabDonnees[row][col] = value;

	}
	
	

	// private void showMessageDialog(String message) {
	// 	JOptionPane.showMessageDialog(this., message, "Erreur", JOptionPane.ERROR_MESSAGE);
	// }
}
