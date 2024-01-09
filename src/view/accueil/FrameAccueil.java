package view.accueil;

import javax.swing.*;

public class FrameAccueil extends JFrame {
    
    public FrameAccueil() {
        this.changePanel( new PanelAccueil(this));
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false);

        this.setVisible(true);
    }

    public void changePanel (JPanel panel){
        this.setContentPane(panel);
        this.revalidate();
        this.pack();
        this.setMinimumSize(this.getSize());
        this.setLocationRelativeTo(null);
    }
}