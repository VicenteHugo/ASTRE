package view.accueil;

import javax.swing.*;
import java.awt.event.*;

import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class PanelAccueil extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JButton    btnAction;

	public PanelAccueil ( Controleur ctrl )
	{
		this.ctrl = ctrl;

		JPanel panelTracer, panelAction;

		this.setLayout ( new BorderLayout() );

		// création des composants;
		
		this.btnAction = new JButton ( "Action" );

	}
}
