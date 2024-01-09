package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class JTextFieldNumber extends JTextField implements KeyListener {
    private boolean isFloat = false;
    private boolean hasPoint = false;

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

    public void setFloat(boolean isFloat) {
        this.isFloat = isFloat;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        boolean isPointOrComma = (e.getKeyChar() == '.' || e.getKeyChar() == ',');
        boolean containsPointOrComma = (this.getText().contains(",") || this.getText().contains("."));

        if (this.isFloat) {
            if (isPointOrComma && containsPointOrComma) {
                e.consume();
            } else if (!Character.isDigit(e.getKeyChar()) && !isPointOrComma) {
                e.consume();
            }
        } else {
            if (isPointOrComma) {
                e.consume();
            } else if (!Character.isDigit(e.getKeyChar()) && !isPointOrComma) {
                e.consume();
            }
        }
    }

    public void keyPressed(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}
}
