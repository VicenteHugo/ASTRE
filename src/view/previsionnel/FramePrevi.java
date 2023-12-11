package view.previsionnel;


import javax.swing.JFrame;

import java.awt.BorderLayout;

public class FramePrevi extends JFrame{
    private PanelPrevi panel;
    
    public FramePrevi(){
        this.setTitle("Prévisionnel");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        panel = new PanelPrevi();
        this.add(panel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        FramePrevi frame = new FramePrevi();
    }
}
