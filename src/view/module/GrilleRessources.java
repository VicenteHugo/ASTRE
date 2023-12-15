package view.module;

import javax.swing.table.AbstractTableModel;

public class GrilleRessources extends AbstractTableModel {
    
    private String[]   tabEntetes;
	private Object[][] tabDonnees;

	public GrilleRessources()
	{
		this.tabDonnees = new Object[][] {{ "LePivert","CM",6, 1 , 9, "3cm d'1h30"},
		                                  { "LeGrix","TD" ,14 , 2 , 112, ""},};

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

		this.tabEntetes = new String[]   {"Intervenants", "Type", "Nb sem", "nb Gp/nb h","tot eqtd","commentaire"};

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
