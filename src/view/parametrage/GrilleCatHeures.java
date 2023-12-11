package view.parametrage;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import controleur.Controleur;
import model.CategorieIntervenants;

public class GrilleCatHeures extends AbstractTableModel {

	private String[]   tabEntetes;
	private Object[][] tabDonnees;

	public GrilleCatHeures() {
		
		CategorieIntervenants cat;
		this.tabDonnees = new Object[][] {{ "info_ec" , 1.0},
		                                  { "vaca_pro", 1.0},
		                                  { "vac_sd"  , 1.0},
		                                  { "vaca_ret", 1.0},
		                                  { "info_sd" , 1.0} };

		/*
		List<CategorieIntervenants> lstClients = ctrl.getCatInt();
		
		tabDonnees = new Object[lstClients.size()][4];

		for ( int lig=0; lig<lstClients.size(); lig++)
		{
			cat = lstClients.get(lig);

			tabDonnees[lig][0] = cat.getlibCatInt     ();
			tabDonnees[lig][1] = cat.getCoefCatInt    ();
			tabDonnees[lig][2] = cat.getHeureMinCatInt();
			tabDonnees[lig][3] = cat.getHeureMaxCatInt();
		}*/

		this.tabEntetes = new String[]   {"Libellé", "Coef"};

	}

	public int    getRowCount   () {return this.tabDonnees.length;}
	public int    getColumnCount() {return this.tabEntetes.length;}
	
	public String getColumnName (int col)          { return this.tabEntetes[col];          }
	public Object getValueAt    (int row, int col) { return this.tabDonnees[row][col];     }
	public Class  getColumnClass(int c)            {return getValueAt(0, c).getClass();}
	
	public boolean isCellEditable(int row, int col) { return true;}
	
	public void setValueAt(Object value, int row, int col) {

		this.tabDonnees[row][col] = value;

	}
}
