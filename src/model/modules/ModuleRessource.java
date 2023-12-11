package model.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Semestres;

public class ModuleRessource extends Module {

	private String type;

	private int nbHeureTotal;
	private List<Integer> listeNombreHeurePn;
	private HashMap<Integer, Integer> listeNombreHeure;

	private int heurePonctuel;

	private int nombreHeurePnTotal;
	private int nombreHeureAffecte;

	public ModuleRessource(String libelleLongModule, String libelleCourtModule, Semestres semestres, int nbEtudiant,
			boolean valide,
			int nbGroupeTD, int nbGroupeTP, int nbHeureCMParSemaine, int nbHeureTDParSemaine,
			int nbHeureTPParSemaine, int nbHeureTutParSemaine, int nbSemaineCM, int nbSemaineTD, int nbSemaineTP,
			int nbSemaineTut, int nbHeureCM_PN, int nbHeureTD_PN, int nbHeureTut_PN, int nbHeureTP_PN,
			int nbHeurePonctuel) {

		super(libelleLongModule, libelleCourtModule, semestres, nbEtudiant, nbGroupeTD, nbGroupeTP, valide);
		this.listeNombreHeurePn = new ArrayList<Integer>();
		this.listeNombreHeure = new HashMap<Integer, Integer>();

		this.initListHeurePn(nbHeureCM_PN, nbHeureTD_PN, nbHeureTP_PN, nbHeureTut_PN);
		this.initNombreHeureSemaine(nbHeureCMParSemaine, nbHeureTDParSemaine, nbHeureTPParSemaine, nbHeureTutParSemaine,
				nbSemaineCM, nbSemaineTD, nbSemaineTP, nbSemaineTut);

		this.heurePonctuel = heurePonctuel;

	}

	public void initListHeurePn(int nbHeureCM_PN, int nbHeureTD_PN, int nbHeureTP_PN, int nbHeureTut_PN) {
		this.listeNombreHeurePn.add(nbHeureCM_PN);
		this.listeNombreHeurePn.add(nbHeureTD_PN);
		this.listeNombreHeurePn.add(nbHeureTP_PN);
		this.listeNombreHeurePn.add(nbHeureTut_PN);
	}

	public void initNombreHeureSemaine(int nbHeureCMParSemaine, int nbHeureTDParSemaine,
			int nbHeureTPParSemaine, int nbHeureTutParSemaine, int nbSemaineCM, int nbSemaineTD, int nbSemaineTP,
			int nbSemaineTut) {
		this.listeNombreHeure.put(nbSemaineCM, nbHeureCMParSemaine);
		this.listeNombreHeure.put(nbSemaineTD, nbHeureTDParSemaine);
		this.listeNombreHeure.put(nbSemaineTP, nbHeureTPParSemaine);
		this.listeNombreHeure.put(nbSemaineTut, nbHeureTutParSemaine);
	}

}
