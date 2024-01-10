package view;

import java.awt.Font;

import javax.swing.JLabel;

public class JLabelModule extends JLabel{
	Font styleLib = new Font("Arial", Font.PLAIN,11);// Taille et style de police

	public JLabelModule()
	{
		super();
		this.setFont(styleLib);
	}
	
	public JLabelModule(String text)
	{
		super(text);
		this.setFont(styleLib);
	}

	public JLabelModule(String text, int i)
	{
		super(text, i);
		this.setFont(styleLib);
	}
}
