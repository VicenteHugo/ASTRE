package view.previsionnel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import controleur.Controleur;
import model.Semestres;
import view.JTextFieldNumber;

public class PanelSemestre extends JPanel implements FocusListener {
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
        this.grilleSemestre.setFillsViewportHeight(true);
        this.grilleSemestre.setShowVerticalLines(false);


        this.txtNbETD     .addFocusListener(this);
        this.txtNbGpTD    .addFocusListener(this);
        this.txtNbGpTP    .addFocusListener(this);
        this.txtNbSemaines.addFocusListener(this);

        this.add(this.grilleSemestre, BorderLayout.CENTER);
    }

    public JTable getTable(){
        return this.grilleSemestre;
    }

    public void majGrille(int semestre){
        this.grilleSemestre.setModel(new GrilleSemestre(semestre));
    }

    public Semestres getSemNew () {
        this.sem.setNbEtdSem (Integer.parseInt(this.txtNbETD     .getText()));
        this.sem.setNbGpTdSem(Integer.parseInt(this.txtNbGpTD    .getText()));
        this.sem.setNbGpTpSem(Integer.parseInt(this.txtNbGpTP    .getText()));
        this.sem.setNbSemSem (Integer.parseInt(this.txtNbSemaines.getText()));

        return this.sem;
    }

    public void focusGained(FocusEvent e) {}

    public void focusLost(FocusEvent e) {
        this.sem.setNbEtdSem (Integer.parseInt(this.txtNbETD     .getText()));
        this.sem.setNbGpTdSem(Integer.parseInt(this.txtNbGpTD    .getText()));
        this.sem.setNbGpTpSem(Integer.parseInt(this.txtNbGpTP    .getText()));
        this.sem.setNbSemSem (Integer.parseInt(this.txtNbSemaines.getText()));
    }
}
