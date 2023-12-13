package view.accueil;

import view.Intervenant.PanelIntervenants;
import view.parametrage.*;
import view.previsionnel.*;
import view.Etat.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAccueil extends JPanel implements ActionListener {

    private JButton btnParametre, btnPrevisionnel, btnIntervenant, btnEtat;
    private FrameAccueil  frame;
    public PanelAccueil(FrameAccueil frame) {

        // création des composants
        this.frame = frame;
        this.frame.setTitle("Accueil");
        this.btnParametre    = new JButton("Paramètres");
        this.btnPrevisionnel = new JButton("Prévisionnel");
        this.btnIntervenant  = new JButton("Intervenants");
        this.btnEtat         = new JButton("Etats");
        
        Dimension buttonSize = new Dimension(120, 40); // Vous pouvez ajuster la taille selon vos besoins
        this.btnParametre.setPreferredSize(buttonSize);
        this.btnPrevisionnel.setPreferredSize(buttonSize);
        this.btnIntervenant.setPreferredSize(buttonSize);
        this.btnEtat.setPreferredSize(buttonSize);
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //Positionnement
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // ajout des composants avec des marges
        this.add(btnParametre,gbc);
        gbc.gridy++;
        this.add(btnPrevisionnel,gbc);
        gbc.gridy++;
        this.add(btnIntervenant,gbc);
        gbc.gridy++;
        this.add(btnEtat,gbc);
        
        this.btnParametre.addActionListener(this);
		this.btnPrevisionnel.addActionListener(this);
		this.btnIntervenant.addActionListener(this);
        this.btnEtat.addActionListener(this);
        
    }
    public void actionPerformed(ActionEvent e) 
	    {
            if (e.getSource() == this.btnParametre)
			    this.frame.changePanel(new PanelParametre(this.frame));
            if (e.getSource() == this.btnPrevisionnel)
                this.frame.changePanel(new PanelPrevi(this.frame));
            if (e.getSource() == this.btnIntervenant)
                this.frame.changePanel(new PanelIntervenants(this.frame));
            if (e.getSource() == this.btnEtat)
                this.frame.changePanel(new PanelEtat(this.frame));
        }
}