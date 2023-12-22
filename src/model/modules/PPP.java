package model.modules;

import java.util.ArrayList;
import java.util.List;

import model.CategorieHeures;
import model.Semestres;

public class PPP extends Module {

	public PPP(Semestres semestres, String code, String libLong, String libCourt, int heurePonctuel, boolean valid) {
		super(semestres, code, libLong, libCourt, heurePonctuel, valid);
	}

	@Override
	public void initList(int heurePNCM, int nbSemaineCM, int heureSemaineCM, CategorieHeures catH) {

		List<Integer> list = new ArrayList<>();
		list.add(heurePNCM);
		list.add(nbSemaineCM);
		list.add(heureSemaineCM);
		this.listCategorieHeure.add(catH);
		this.heures.put(catH, list);
	}
	
	public int getHeurePn() {
		int somme = 0;

		for (CategorieHeures catH : this.heures.keySet()) {

			int heurePN = this.heures.get(catH).get(0);

			if (catH.getlibCatHeur().equals("TD")) heurePN = heurePN * this.semestres.getNbGpTdSem();
			if (catH.getlibCatHeur().equals("TP")) heurePN = heurePN * this.semestres.getNbGpTpSem();

			somme += heurePN * catH.getcoefCatHeur();
		}
		return somme;
	}
}
