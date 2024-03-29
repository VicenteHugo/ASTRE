package view.previsionnel;


import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import controleur.Controleur;
import model.modules.Module;

public class GrilleSemestre extends AbstractTableModel {
    private Object[][] tabDonnees;

    public GrilleSemestre(int semestre) {
        Controleur ctrl = Controleur.getControleur();
        
        List<Module> lst = ctrl.getModules(semestre);

        int nbModule = lst.size();

        this.tabDonnees = new Object[nbModule][4];

        int cpt = 0;
        for (Module module : ctrl.getModules(semestre)) {

            // Code/Nom
            this.tabDonnees[cpt][0] = module.getCode();
            this.tabDonnees[cpt][1] = module.getLibLong();

            //Heure calculer
            this.tabDonnees[cpt][2] = module.getHeureAffecte() + "/"  + module.getHeurePn();
            
            //Valider
            this.tabDonnees[cpt][3] = module.isValide();
            cpt++;
        }
    }

    @Override
    public int getRowCount() { return this.tabDonnees.length; }

    @Override
    public int getColumnCount() { return 4; }

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
    public Class<?> getColumnClass(int col) { return getValueAt(0, col).getClass();}

    public void maj(){
        fireTableDataChanged();
    }
}
