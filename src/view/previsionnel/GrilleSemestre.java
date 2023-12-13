package view.previsionnel;

import javax.swing.table.AbstractTableModel;
import controleur.Controleur;
import model.modules.Module;

public class GrilleSemestre extends AbstractTableModel {
    private Object[][] tabDonnees;

    public GrilleSemestre(int semestre) {
        Controleur ctrl = Controleur.getControleur();
        
        int nbModule = ctrl.getModules(semestre).size();
        this.tabDonnees = new Object[nbModule][4];

        int cpt = 0;
        for (Module module : ctrl.getModules(semestre)) {
            this.tabDonnees[cpt][0] = module.getCode();
            this.tabDonnees[cpt][1] = module.getLibLong();
            this.tabDonnees[cpt][2] = module.getHeurePonctuel() + "/"  + module.getHeurePn();
            this.tabDonnees[cpt][3] = module.isValide();
        }
    }

    @Override
    public int getRowCount() { return this.tabDonnees.length; }

    @Override
    public int getColumnCount() { return this.tabDonnees[0].length; }

    @Override
    public Object getValueAt(int row, int col) { return this.tabDonnees[row][col]; }
    
    @Override
    public boolean isCellEditable(int row, int col) { return false; }

    @Override
    public void setValueAt(Object value, int row, int col) {
        this.tabDonnees[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    @Override
    public Class getColumnClass(int col) { return getValueAt(0, col).getClass();}
}
