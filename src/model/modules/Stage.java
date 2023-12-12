package model.modules;

import java.util.ArrayList;
import java.util.List;

import model.CategorieHeures;
import model.Etat;
import model.Semestres;

public class Stage extends Module {

	private List<Integer> listElementRhe;
	private List<Integer> listElementTut;

	public Stage(Semestres semestres, String code, String libLong, String libCourt, int heurePonctuel) {
		super(semestres, code, libLong, libCourt, heurePonctuel);

		this.listElementRhe = new ArrayList<Integer>();
		this.listElementTut = new ArrayList<Integer>();
	}

	public void initList(int heurePNCM, int nbSemaineCM, int heureSemaineCM, int heurePonctuel, CategorieHeures catH) {

		List<Integer> list = null;
		CategorieHeures cH = catH;

		String lib = cH.getlibCatHeur();

		switch (lib) {
			case "REH":
				list = this.listElementRhe;
				break;

			case "H tut":
				list = this.listElementTut;
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
		list.add(heurePonctuel);
		this.heures.put(cH, list);
	}

}
