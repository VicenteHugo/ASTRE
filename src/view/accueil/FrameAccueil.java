package view.accueil;

import javax.swing.*;
import java.awt.*;

public class FrameAccueil extends JFrame{
    
    public FrameAccueil() {

        this.setTitle("Accueil");
        this.setSize(400, 300);
        this.setLocation(400, 200 );

        this.add ( new PanelAccueil());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    public void changePannel (JPanel panel){
        this.setContentPane(panel);
        this.revalidate();
    }

    public static void main(String[] args){
        FrameAccueil f = new FrameAccueil();
    }

}