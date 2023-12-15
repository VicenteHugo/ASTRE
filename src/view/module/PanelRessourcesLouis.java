package view.module;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

import view.Intervenant.PanelAddIntervenant;
import view.accueil.FrameAccueil;
import view.previsionnel.PanelPrevi;

public class PanelRessourcesLouis extends JPanel {

	private FrameAccueil frame;

	private JButton btnAjouter;
	private JButton btnSupprimer;
	private JButton btnEnregistrer;
	private JButton btnAnnuler;

	private JPanel panelHaut;
	private JPanel panelBas;

	private JTable tblGrilleDonnees;

	public PanelRessourcesLouis(FrameAccueil frame) {

		// Frame
		this.frame = frame;
		frame.setTitle("Astre - Prévisionnel - Module");
		frame.setMinimumSize(new Dimension(1000, 600));

		// Composants
		this.btnAjouter     = new JButton("Ajouter");
		this.btnSupprimer   = new JButton("Supprimer");
		this.btnEnregistrer = new JButton("Enregistrer");
		this.btnAnnuler     = new JButton("Annuler");

		this.panelHaut = new JPanel();
		this.panelBas = new JPanel();

		// Layout
		this.setLayout(new GridLayout(2, 1, 10, 10));
		this.panelHaut.setLayout(new GridLayout(2, 1));
		this.panelBas.setLayout(new BorderLayout());

		// Positionnement

        Border outline = BorderFactory.createLineBorder(Color.black);

		JPanel panelInfo = new JPanel();
		panelInfo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new java.awt.Insets(5, 0, 5, 5);

		panelInfo.add(new JLabel("type module"), gbc);
        gbc.gridx++;
		panelInfo.add(new JLabel("semestre"), gbc);
        gbc.gridx++;
		panelInfo.add(new JLabel("code"), gbc);
        gbc.gridx++;
		panelInfo.add(new JLabel("libellé long"), gbc);
        gbc.gridx++;
		panelInfo.add(new JLabel("libellé court"), gbc);

        gbc.gridy++;
        gbc.gridx = 0;
		panelInfo.add(new JTextField("Ressources",10), gbc);
        gbc.gridx++;
		panelInfo.add(new JTextField("S1",3), gbc);
        gbc.gridx++;
		panelInfo.add(new JTextField("R1.01",5), gbc);
        gbc.gridx++;
		panelInfo.add(new JTextField("Initiation au développement",30), gbc);
        gbc.gridx++;
		panelInfo.add(new JTextField("Init dev",10), gbc);

		gbc.gridy++;
        gbc.gridx = 0;
        panelInfo.add(new JLabel("nb Etd"), gbc);
        gbc.gridx++;
		panelInfo.add(new JLabel("nb gp TD"), gbc);
        gbc.gridx++;
		panelInfo.add(new JLabel("nb gp TP"), gbc);

		gbc.gridy++;
        gbc.gridx = 0;
        panelInfo.add(new JTextField("85",3), gbc);
        gbc.gridx++;
		panelInfo.add(new JTextField("4",2), gbc);
        gbc.gridx++;
		panelInfo.add(new JTextField("7",2), gbc);

        gbc.gridy++;
        gbc.gridx = 1;
		panelInfo.add(new JLabel("PN local(nb h tot/etd)"), gbc);
        gbc.gridx = 6;
        panelInfo.add(new JLabel("Répartition"), gbc);
        this.panelHaut.add(panelInfo);


        JPanel panelInfo2 = new JPanel();
        panelInfo2.setLayout(new GridLayout(1,2));
        JPanel panelPnLocal = new JPanel();
		panelPnLocal.setLayout(new GridBagLayout());
        //panelPnLocal.setBorder(outline);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.anchor = GridBagConstraints.LINE_START;
		gbc2.gridx = 1;
		gbc2.gridy = 0;
		gbc2.insets = new java.awt.Insets(5, 5, 5, 5);
		panelPnLocal.add(new JLabel("CM"), gbc2);
        gbc2.gridx++;
		panelPnLocal.add(new JLabel("TD"), gbc2);
        gbc2.gridx++;
		panelPnLocal.add(new JLabel("TP"), gbc2);
        gbc2.gridx++;
		panelPnLocal.add(new JLabel("∑"), gbc2);

        gbc2.gridx = 1;
		gbc2.gridy++;
		panelPnLocal.add(new JTextField("6"), gbc2);
        gbc2.gridx++;
		panelPnLocal.add(new JTextField("65"), gbc2);
        gbc2.gridx++;
		panelPnLocal.add(new JTextField("28"), gbc2);
        gbc2.gridx++;
		panelPnLocal.add(new JTextField("99"), gbc2);

        gbc2.gridx = 0;
		gbc2.gridy++;
		panelPnLocal.add(new JLabel("Total (eqtd) promo"), gbc2);
        gbc2.gridx++;
		panelPnLocal.add(new JTextField("9"), gbc2);
        gbc2.gridx++;
		panelPnLocal.add(new JTextField("260"), gbc2);
        gbc2.gridx++;
		panelPnLocal.add(new JTextField("196"), gbc2);
        gbc2.gridx++;
		panelPnLocal.add(new JTextField("465"), gbc2);

		panelInfo2.add(panelPnLocal);

        JPanel panelRépartition = new JPanel();
		panelRépartition.setLayout(new GridBagLayout());
        //panelRépartition.setBorder(outline);
        GridBagConstraints gbc3 = new GridBagConstraints();
		gbc3.gridx = 1;
		gbc3.gridy = 0;
		gbc3.insets = new java.awt.Insets(5, 5, 5, 5);
		panelRépartition.add(new JLabel("CM"), gbc3);
        gbc3.gridx++;
        gbc3.gridx++;
		panelRépartition.add(new JLabel("TD"), gbc3);
		gbc3.gridx++;
        gbc3.gridx++;
		panelRépartition.add(new JLabel("TP"), gbc3);

		gbc3.gridx = 0;
		gbc3.gridy++;
        panelRépartition.add(new JLabel("nb Sem"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JLabel("nb h/sem"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JLabel("nb Sem"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JLabel("nb h/sem"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JLabel("nb Sem"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JLabel("nb h/sem"), gbc3);

		gbc3.gridx = 0;
		gbc3.gridy++;
        panelRépartition.add(new JTextField("6"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JTextField("1"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JTextField("14"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JTextField("4"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JTextField("14"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JTextField("2"), gbc3);

        gbc3.gridx++;
        gbc3.gridx++;
        gbc3.gridy--;
		panelRépartition.add(new JLabel("CM"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JLabel("TD"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JLabel("TP"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JLabel("heure ponctuelle"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JLabel("∑"), gbc3);

		gbc3.gridx = 6;
        gbc3.gridy++;
		panelRépartition.add(new JTextField("6"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JTextField("56"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JTextField("28"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JTextField("9"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JTextField("99"), gbc3);

        gbc3.gridx = 5;
        gbc3.gridy++;
		panelRépartition.add(new JLabel("Total promo(eqtd)"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JTextField("9"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JTextField("224"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JTextField("196"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JTextField("36"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JTextField("465"), gbc3);

        gbc3.gridx = 5;
        gbc3.gridy++;
		panelRépartition.add(new JLabel("Total affecté(eqtd)"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JTextField("9"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JTextField("224"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JTextField("168"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JTextField("36"), gbc3);
        gbc3.gridx++;
		panelRépartition.add(new JTextField("437"), gbc3);

        panelInfo2.add(panelRépartition);

        this.panelHaut.add(panelInfo2);


        JPanel panelTable = new JPanel();
        panelTable.setLayout(new BorderLayout());
        panelTable.add(new JLabel("Affectation"), BorderLayout.NORTH);

        this.tblGrilleDonnees = new JTable(new GrilleRessources());
		this.tblGrilleDonnees.setFillsViewportHeight(true);

		JScrollPane spGrilleDonnees = new JScrollPane(this.tblGrilleDonnees);
		panelTable.add(spGrilleDonnees, BorderLayout.CENTER);

        JPanel panelBouton = new JPanel();
        panelBouton.add(this.btnAjouter);
        panelBouton.add(this.btnSupprimer);

        panelTable.add(panelBouton,BorderLayout.SOUTH);

        this.panelBas.add(panelTable, BorderLayout.CENTER);

        JPanel panelBouton2 = new JPanel();
        panelBouton.add(this.btnAnnuler);
        panelBouton.add(this.btnEnregistrer);

        this.panelBas.add(panelBouton2,BorderLayout.SOUTH);

		this.add(panelHaut);
		this.add(panelBas);

		this.btnAnnuler.addActionListener((e) -> this.frame.changePanel(new PanelPrevi(this.frame)));
        this.btnAjouter.addActionListener((e)->{
			JFrame f = new JFrame();
			f.add(new PanelAddRessourceIntervenant(this.frame, f));
			f.setTitle("Affecter un Intervenant");
			f.pack();
			f.setLocationRelativeTo(null);
			f.setAlwaysOnTop(true);
			f.setVisible(true);
		});
	}
}