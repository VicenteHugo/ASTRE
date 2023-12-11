package view.parametrage;

import javax.swing.*;
import java.awt.*;

public class FrameParametre extends JFrame {

	public FrameParametre() {

		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add(new PanelParametre(this));

		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	

	public void changePannel (JPanel panel){
		this.getContentPane().removeAll();
		this.getContentPane().add(panel);
	}
	
	public static void main(String[] args) {
		new FrameParametre();
	}
	
}
