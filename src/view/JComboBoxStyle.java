package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class JComboBoxStyle<T> extends JComboBox<T> {
    public JComboBoxStyle(T[] items) {
        super(items);

        Color coul = Color.decode("0xD0D0D0");
        this.setBackground(coul);

        this.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 12));

        this.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JComboBoxStyle.this.setBackground(Color.decode("0xAAAAAA"));
                JComboBoxStyle.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                JComboBoxStyle.this.setBackground(coul);
                JComboBoxStyle.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        Component comp = this.getComponent(0);
        comp.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                comp.setBackground(Color.decode("0xAAAAAA"));
                comp.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                comp.setBackground(coul);
            }
        });

        this.setRenderer(new ComboBoxStyle<>(this));
    }
    
    public JComboBoxStyle() {
		super();

		Color coul = Color.decode("0xD0D0D0");
        this.setBackground(coul);

		this.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 12));

  	this.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				JComboBoxStyle.this.setBackground(Color.decode("0xAAAAAA"));
				JComboBoxStyle.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				JComboBoxStyle.this.setBackground(coul);
				JComboBoxStyle.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

        Component comp = this.getComponent(0);
        comp.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                comp.setBackground(Color.decode("0xAAAAAA"));
                comp.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                comp.setBackground(coul);
            }
        });


        this.setRenderer(new ComboBoxStyle<>(this));
    }        
}

class ComboBoxStyle<T> extends JLabel implements ListCellRenderer<T> {
    public ComboBoxStyle(JComboBoxStyle<T> combo) {
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(value != null) {
            setText(value.toString());
            setBackground(isSelected ? Color.decode("0xAAAAAA") : Color.decode("0xD0D0D0"));
        }

        return this;
    }
}