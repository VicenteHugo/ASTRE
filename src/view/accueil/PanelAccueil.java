package view.accueil;

import view.parametrage.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAccueil extends JPanel implements ActionListener {

    private JButton btnParametre, btnPrevisionnel, btnIntervenant, btnEtat;
    private FrameAccueil  frame;
    public PanelAccueil(FrameAccueil frame) {

        // création des composants
        this.frame = frame;
        this.btnParametre = new JButton("Paramètres");
        this.btnPrevisionnel = new JButton("Prévisionnel");
        this.btnIntervenant = new JButton("Intervenants");
        this.btnEtat = new JButton("Etats");

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
                this.frame.changePanel(new PanelParametre(this.frame));
            if (e.getSource() == this.btnIntervenant)
                this.frame.changePanel(new PanelParametre(this.frame));
            if (e.getSource() == this.btnEtat)
                this.frame.changePanel(new PanelParametre(this.frame));
        }
}
