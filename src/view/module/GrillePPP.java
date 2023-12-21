package view.module;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Affectations;
import model.modules.Module;

public class GrillePPP extends AbstractTableModel {

	private String[] tabEntetes;
	private Object[][] tabDonnees;

	public GrillePPP(Module mod) {
		List<Affectations> listAffectations = mod.getLstAffectations();
		this.tabEntetes = new String[] { "Intervenants", "Type", "Nb h", "tot eqtd", "commentaire" };



		this.tabDonnees = new Object[listAffectations.size()][5];
		int lig = 0;

		for (Affectations a : listAffectations) {
			
			this.tabDonnees[lig][0] = a.getIntervenant().getNomIntervenant();
			this.tabDonnees[lig][1] = a.getCategorieHeures().getlibCatHeur();
			this.tabDonnees[lig][2] = a.getNbGroupe();
			this.tabDonnees[lig][3] = a.getNbGroupe() * a.getCategorieHeures().getcoefCatHeur();
			this.tabDonnees[lig][4] = a.getCommentaire();

			lig++;
		}


	}

	public int getRowCount     () { return this.tabDonnees.length; }
	public int getColumnCount  () { return this.tabEntetes.length; }

	public String  getColumnName (int col)          { return this.tabEntetes[col]; }
	public boolean isCellEditable(int row, int col) { return false;}

	public Object   getValueAt    (int row, int col) { return this.tabDonnees[row][col]; }
	public Class<?> getColumnClass(int c)            { return getValueAt(0, c).getClass();}

	public void setValueAt(Object value, int row, int col) { this.tabDonnees[row][col] = value; }
}
