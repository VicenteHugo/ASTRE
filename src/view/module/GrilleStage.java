package view.module;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import controleur.Controleur;
import model.Affectations;
import model.modules.Module;

public class GrilleStage extends AbstractTableModel {

	private String[] tabEntetes;
	private Object[][] tabDonnees;

	public GrilleStage(Module mod) {

		List<Affectations> listAffectations = Controleur.getControleur().getAffectations(mod);
		this.tabDonnees = new Object[listAffectations.size()][5];

		for (int lig = 0; lig < listAffectations.size(); lig++) {
			Affectations affectations = listAffectations.get(lig);
			affectations.getIntervenant().setHeures(mod.getSemestres(), affectations.getHeureEqtd() );

			this.tabDonnees[lig][0] = affectations.getIntervenant().getNomIntervenant();
			this.tabDonnees[lig][1] = affectations.getCategorieHeures().getlibCatHeur();
			this.tabDonnees[lig][2] = affectations.getNbGroupe();
			this.tabDonnees[lig][3] = affectations.getNbGroupe()* affectations.getCategorieHeures().getcoefCatHeur();
			this.tabDonnees[lig][4] = affectations.getCommentaire();
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
