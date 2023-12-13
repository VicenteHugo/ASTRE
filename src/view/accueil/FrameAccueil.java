package view.accueil;

import javax.swing.*;

public class FrameAccueil extends JFrame {
    
    public FrameAccueil() {

        this.setTitle("Accueil");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.add ( new PanelAccueil(this));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    public void changePanel (JPanel panel){
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        this.revalidate();
    }
}