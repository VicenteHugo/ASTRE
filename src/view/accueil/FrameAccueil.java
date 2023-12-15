package view.accueil;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.*;

public class FrameAccueil extends JFrame {
    
    public FrameAccueil() {
        this.changePanel( new PanelAccueil(this));
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    public void changePanel (JPanel panel){
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        this.revalidate();
    }
}