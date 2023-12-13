package model.modules;

import java.util.ArrayList;
import java.util.List;

import model.CategorieHeures;
import model.Semestres;

public class Sae extends Module {

	public Sae(Semestres semestres, String code, String libLong, String libCourt, int heurePonctuel) {
		super(semestres, code, libLong, libCourt, heurePonctuel);
	}

	public void initList(int heurePNCM, int nbSemaineCM, int heureSemaineCM, CategorieHeures catH) {

		List<Integer> list = new ArrayList<>();
		list.add(heurePNCM);
		list.add(nbSemaineCM);
		list.add(heureSemaineCM);
		this.listCategorieHeure.add(catH);
		this.heures.put(catH, list);
	}

}