package model.modules;

import java.util.ArrayList;
import java.util.List;

import model.CategorieHeures;
import model.Etat;
import model.Semestres;

public class Ressource extends Module {

	private int nbHeurePN;
	private int nbSemaine;
	private int nbHeureSemaine;

	private List<Integer> listElementCM;
	private List<Integer> listElementTD;
	private List<Integer> listElementTP;
	private Etat etat;

	public Ressource(Semestres semestres, String code, String libLong, String libCourt,
			int nbHeurePN, int nbSemaine, int nbHeureSemaine) {
		super(semestres, code, libLong, libCourt);
		this.nbHeurePN = nbHeurePN;
		this.nbHeureSemaine = nbHeureSemaine;
		this.nbSemaine = nbSemaine;

		this.listElementCM = new ArrayList<Integer>();
		this.listElementTD = new ArrayList<Integer>();
		this.listElementTP = new ArrayList<Integer>();
	}

	public void initList(int heurePNCM, int nbSemaineCM, int heureSemaineCM, String lib) {
		List<Integer> list = null;
		CategorieHeures cH = null;

		switch (lib) {
			case "CM":
				list = this.listElementCM;
				break;

			case "TD":
				list = this.listElementTD;
				break;

			case "TP":
				list = this.listElementTP;
				break;
		}

		for (CategorieHeures catHeure : Etat.getCategoriesHeures()) {
			if (lib.equals(catHeure.getlibCatHeur())) {
				cH = catHeure;
				break;
			}
		}

		list.add(heurePNCM);
		list.add(nbSemaineCM);
		list.add(heureSemaineCM);

		this.heures.put(cH, list);
	}
}
