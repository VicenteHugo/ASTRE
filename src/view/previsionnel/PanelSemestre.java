package view.previsionnel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.Controleur;
import model.Semestres;
import view.JTextFieldNumber;

public class PanelSemestre extends JPanel {
    private JTextFieldNumber txtNbGpTD;
    private JTextFieldNumber txtNbGpTP;
    private JTextFieldNumber txtNbETD;
    private JTextFieldNumber txtNbSemaines;

    private JTable grilleSemestre;

    private Semestres sem;
    
    public PanelSemestre(int semestre) {

        this.sem = Controleur.getControleur().getSemestres().get(semestre-1);

        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(1, 8));

        JPanel panel2 = new JPanel(new FlowLayout());
        panel2.add(new JLabel("nb gp TD"));
        this.txtNbGpTD = new JTextFieldNumber(this.sem.getNbGpTdSem() + "");
        this.txtNbGpTD.setColumns(2);
        panel2.add(this.txtNbGpTD);
        panel.add(panel2);


        panel2.add(new JLabel("nb gp TP"));
        this.txtNbGpTP = new JTextFieldNumber(this.sem.getNbGpTpSem() +  "");
        this.txtNbGpTP.setColumns(2);
        panel2.add(this.txtNbGpTP);
        panel.add(panel2);


        panel2.add(new JLabel("nb Etd"));
        this.txtNbETD = new JTextFieldNumber(this.sem.getNbEtdSem() + "");
        this.txtNbETD.setColumns(2);
        panel2.add(this.txtNbETD);
        panel.add(panel2);


        panel2.add(new JLabel("nb semaines"));
        this.txtNbSemaines = new JTextFieldNumber(this.sem.getNbSemSem() + "");
        this.txtNbSemaines.setColumns(2);
        panel2.add(this.txtNbSemaines);
        panel.add(panel2);


        this.add(panel, BorderLayout.NORTH);
 
        this.grilleSemestre = new JTable(new GrilleSemestre(semestre));
        DefaultTableCellRenderer centre = new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.RIGHT);
        this.grilleSemestre.getColumnModel().getColumn(2).setCellRenderer(centre);
        this.grilleSemestre.setFillsViewportHeight(true);
        this.grilleSemestre.setShowVerticalLines(false);

        this.add(this.grilleSemestre, BorderLayout.CENTER);
    }

    public JTable getTable(){
        return this.grilleSemestre;
    }

    public void majGrille(int semestre){
        this.grilleSemestre.setModel(new GrilleSemestre(semestre));
    }

    public Semestres getSemOld () {
        return this.sem;
    }

    public Semestres getSemNew () {

        if (this.txtNbETD     .getText().isEmpty()) this.txtNbETD     .setText("0");
        if (this.txtNbGpTD    .getText().isEmpty()) this.txtNbGpTD    .setText("0");
        if (this.txtNbGpTP    .getText().isEmpty()) this.txtNbGpTP    .setText("0");
        if (this.txtNbSemaines.getText().isEmpty()) this.txtNbSemaines.setText("0");



        int etd = (Integer.parseInt(this.txtNbETD     .getText()));
        int td  = (Integer.parseInt(this.txtNbGpTD    .getText()));
        int tp  = (Integer.parseInt(this.txtNbGpTP    .getText()));
        int sem = (Integer.parseInt(this.txtNbSemaines.getText()));

        return new Semestres(this.sem.getNumSem(), td, tp, etd, sem);
    }
}
