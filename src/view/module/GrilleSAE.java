package view.module;

import javax.swing.table.AbstractTableModel;

public class GrilleSAE extends AbstractTableModel {
    
    private String[]   tabEntetes;
	private Object[][] tabDonnees;

	public GrilleSAE()
	{
		this.tabDonnees = new Object[][] {{ "Bouckachour H","SAE",10, 10 ,""},
		                                  { "Laffaech","SAE" ,5 , 5 , ""},};

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

		this.tabEntetes = new String[]   {"Intervenants", "Type", "Nb h","tot eqtd","commentaire"};

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
