package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;

public class JButtonStyle extends JButton {
	public JButtonStyle(String text) {
		super(text);
		Color coul = Color.decode("0xD0D0D0");
        this.setBackground(coul);

		this.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				JButtonStyle.this.setBackground(Color.decode("0xAAAAAA"));
				JButtonStyle.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				JButtonStyle.this.setBackground(coul);
				JButtonStyle.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
	}
}
