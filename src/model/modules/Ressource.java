package model.modules;

import java.util.ArrayList;
import java.util.List;

import model.CategorieHeures;
import model.Semestres;

public class Ressource extends Module {

	public Ressource(Semestres semestres, String code, String libLong, String libCourt, int heurePonctuel) {
		super(semestres, code, libLong, libCourt, heurePonctuel);
	}

	@Override
	public void initList(int heurePN, int nbSemaine, int heureSemaine, int heurePonctuel, CategorieHeures catH) {

		List<Integer> list = new ArrayList<>();

		list.add(heurePN);
		list.add(nbSemaine);
		list.add(heureSemaine);
		list.add(heurePonctuel);

		this.listCategorieHeure.add(catH);
		this.heures.put(catH, list);
	}
}