package model.modules;

import java.util.ArrayList;
import java.util.List;

import model.CategorieHeures;
import model.Etat;
import model.Semestres;

public class Stage extends Module {

	private int nbHeurePN;

	private List<Integer> listElementRhe;
	private List<Integer> listElementTut;
	private Etat etat;

	public Stage(Semestres semestres, String code, String libLong, String libCourt) {
		super(semestres, code, libLong, libCourt);

		this.nbHeurePN = nbHeurePN;
		this.listElementRhe = new ArrayList<Integer>();
		this.listElementTut = new ArrayList<Integer>();
	}

	public void initList(int nbHeurePN, String lib) {
		List<Integer> list = null;
		CategorieHeures cH = null;

		switch (lib) {
			case "RHE":
				list = this.listElementRhe;
				break;

			case "TUT":
				list = this.listElementTut;
				break;
		}

		for (CategorieHeures catHeure : Etat.getCategoriesHeures()) {
			if (lib.equals(catHeure.getlibCatHeur())) {
				cH = catHeure;
				break;
			}
		}

		list.add(nbHeurePN);
		this.heures.put(cH, list);
	}

}
