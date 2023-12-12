package model.modules;

import java.util.ArrayList;
import java.util.List;

import model.CategorieHeures;
import model.Etat;
import model.Semestres;

public class PPP extends Module {

	private List<Integer> listElementHP;
	private List<Integer> listElementHT;
	private List<Integer> listElementCM;
	private List<Integer> listElementTD;
	private List<Integer> listElementTP;

	public PPP(Semestres semestres, String code, String libLong, String libCourt, int heurePonctuel) {
		super(semestres, code, libLong, libCourt, heurePonctuel);

		this.listElementCM = new ArrayList<>();
		this.listElementHP = new ArrayList<>();
		this.listElementHT = new ArrayList<>();
		this.listElementTD = new ArrayList<>();
		this.listElementTP = new ArrayList<>();
	}

	@Override
	public void initList(int heurePNCM, int nbSemaineCM, int heureSemaineCM, int heurePonctuel, CategorieHeures catH) {

		List<Integer> list = null;
		CategorieHeures cH = catH;

		String lib = cH.getlibCatHeur();

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
			case "HP":
				list = this.listElementHP;
				break;
			case "H TUT":
				list = this.listElementHT;
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
