package view.module;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Affectations;

public class GrillePPP extends AbstractTableModel {

	private String[] tabEntetes;
	private Object[][] tabDonnees;

	public GrillePPP(Module mod) {
		List<Affectations> listAffectations = new ArrayList<>();
		this.tabDonnees = new Object[listAffectations.size()][5];

		for (int lig = 0; lig < listAffectations.size(); lig++) {
			Affectations affectations = listAffectations.get(lig);
			if (affectations.getModule().getClass().getName().equals("PPP")) {
				//List<Integer> listInfosHeure = affectations.getModule().getHeures() //il sert à quoi ?
				//		.get(affectations.getCategorieHeures());
				this.tabDonnees[0][lig] = affectations.getIntervenant().getNomIntervenant();
				this.tabDonnees[1][lig] = affectations.getCategorieHeures().getlibCatHeur();
				this.tabDonnees[2][lig] = affectations.getNbHeure();
				this.tabDonnees[3][lig] = affectations.getNbHeure()
						* affectations.getCategorieHeures().getcoefCatHeur();
				this.tabDonnees[4][lig] = affectations.getCommentaire();

			}
		}

		this.tabEntetes = new String[] { "Intervenants", "Type", "Nb h", "tot eqtd", "commentaire" };

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

	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	public boolean isCellEditable(int row, int col) {
		return true;
	}

	public void setValueAt(Object value, int row, int col) {

		this.tabDonnees[row][col] = value;

	}
}
