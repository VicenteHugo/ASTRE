package view.previsionnel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import view.JTextFieldNumber;

public class PanelSemestre extends JPanel {
    private JTextFieldNumber nbGpTD;
    private JTextFieldNumber nbGpTP;
    private JTextFieldNumber nbEtd;
    private JTextFieldNumber nbSemaines;

    private JTable grilleSemestre;
    
    public PanelSemestre(int semestre) {
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(1, 8));

        JPanel panel2 = new JPanel(new FlowLayout());
        panel2.add(new JLabel("nb gp TD"));
        this.nbGpTD = new JTextFieldNumber("");
        this.nbGpTD.setColumns(2);
        panel2.add(this.nbGpTD);
        panel.add(panel2);


        panel2.add(new JLabel("nb gp TP"));
        this.nbGpTP = new JTextFieldNumber("  ");
        this.nbGpTP.setColumns(2);
        panel2.add(this.nbGpTP);
        panel.add(panel2);


        panel2.add(new JLabel("nb Etd"));
        this.nbEtd = new JTextFieldNumber("  ");
        this.nbEtd.setColumns(2);
        panel2.add(this.nbEtd);
        panel.add(panel2);


        panel2.add(new JLabel("nb semaines"));
        this.nbSemaines = new JTextFieldNumber("  ");
        this.nbSemaines.setColumns(2);
        panel2.add(this.nbSemaines);
        panel.add(panel2);


        this.add(panel, BorderLayout.NORTH);
 
        this.grilleSemestre = new JTable(new GrilleSemestre(semestre));
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
}
