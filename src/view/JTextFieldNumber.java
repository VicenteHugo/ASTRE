package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class JTextFieldNumber extends JTextField implements KeyListener {
	public JTextFieldNumber() {
		super();
		this.addKeyListener(this);
	}

	public JTextFieldNumber(int ind) {
		super(ind);
		this.addKeyListener(this);
	}

	public JTextFieldNumber(String message) {
		super(message);
		this.addKeyListener(this);
	}

	public JTextFieldNumber(String message, int ind) {
		super(message, ind);
		this.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (!Character.isDigit(e.getKeyChar()))
			e.consume();
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
}
