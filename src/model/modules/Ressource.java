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
	private List<Integer> listElementTut;
	private Etat etat;

	public Ressource(Semestres semestres, String code, String libLong, String libCourt, int heurePonctuel) {

		super(semestres, code, libLong, libCourt, heurePonctuel);

		this.listElementCM = new ArrayList<Integer>();
		this.listElementTD = new ArrayList<Integer>();
		this.listElementTP = new ArrayList<Integer>();

	}

	@Override
	public void initList(int heurePN, int nbSemaine, int heureSemaine, int heurePonctuel, CategorieHeures catH) {
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

			case "H tut":
				list = this.listElementTut;
				break;
		}

		list.add(heurePN);
		list.add(nbSemaine);
		list.add(heureSemaine);
		list.add(heurePonctuel);

		this.heures.put(cH, list);
	}
}