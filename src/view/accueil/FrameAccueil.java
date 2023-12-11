package view.accueil;

import javax.swing.*;
import java.awt.*;

import javax.swing.JFrame;

public class FrameAccueil extends JFrame{
    
    public FrameAccueil() {

        this.setTitle("Page d'accueil");
        this.setSize(400, 300);
        this.setLocation(50, 50 );

        this.add( new PanelAccueil( ctrl ) );

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
