package model.modules;

import java.util.ArrayList;
import java.util.List;

import model.CategorieHeures;
import model.Etat;
import model.Semestres;

public class Ressource extends Module {

	private List<Integer> listElementCM;
	private List<Integer> listElementTD;
	private List<Integer> listElementTP;
	private Etat etat;

	public Ressource(Semestres semestres, String code, String libLong, String libCourt) {
						
		super(semestres, code, libLong, libCourt);

		this.listElementCM = new ArrayList<Integer>();
		this.listElementTD = new ArrayList<Integer>();
		this.listElementTP = new ArrayList<Integer>();
	}

	public void initList(int heurePNCM, int nbSemaineCM, int heureSemaineCM, CategorieHeures catH) {

		List<Integer> list = null;
		CategorieHeures cH = catH;

		String lib = catH.getlibCatHeur();

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

		list.add(heurePNCM);
		list.add(nbSemaineCM);
		list.add(heureSemaineCM);

		this.heures.put(cH, list);
	}
}
