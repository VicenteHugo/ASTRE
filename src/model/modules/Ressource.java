package model.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.CategorieHeures;
import model.Semestres;

public class Ressource extends ModuleRetry {

	private int nbHeurePN;
	private int nbSemaine;
	private int nbHeureSemaine;

	private List<Integer> listElement;

	public Ressource(Semestres semestres, String code, String libLong, String libCourt,
			int nbHeurePN, int nbSemaine, int nbHeureSemaine) {
		super(semestres, code, libLong, libCourt);
		this.nbHeurePN = nbHeurePN;
		this.nbHeureSemaine = nbHeureSemaine;
		this.nbSemaine = nbSemaine;

		this.listElement = new ArrayList<>();
		this.listElement.add(nbHeurePN);
		this.listElement.add(nbHeureSemaine);
		this.listElement.add(nbSemaine);
	}

	public void initList() {
		ArrayList<CategorieHeures> lstCategorieHeures = Etat.getCategorieHeure();
		for (CategorieHeures cH : lstCategorieHeures) {
			switch (cH.getlibCatHeur()) {
				case "CM":
					this.heures.put(cH, (java.awt.List) listElement);
					break;
				case "TD":
					this.heures.put(cH, (java.awt.List) listElement);
					break;
				case "TP":
					this.heures.put(cH, (java.awt.List) listElement);
					break;
				default:
					break;
			}
		}
	}

}
