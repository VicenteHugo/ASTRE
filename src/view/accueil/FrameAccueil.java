package view.accueil;

import javax.swing.*;

public class FrameAccueil extends JFrame {
    
    public FrameAccueil() {
        this.add ( new PanelAccueil(this));
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