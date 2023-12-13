package model.action;

import java.util.ArrayList;
import java.util.List;

import model.Affectations;
import model.CategorieHeures;
import model.CategorieIntervenant;
import model.Etat;
import model.Intervenants;
import model.modules.Module;

public class Ajout extends Action {

	public Ajout(Affectations a) {

		this.requetes = "INSERT INTO Affectation(intNom, intPrenom, codeMod, libCatHeur, nbSem, nbGroupe, commentaire, etat) VALUES (?,?,?,?,?,?,?,?)";
		this.info = new ArrayList<>(List.of(a.getIntervenant().getNomIntervenant(),
		                                    a.getIntervenant().getPrenomIntervenant(), a.getModule().getCode(),
		                                    a.getNbSemaine(), a.getNbGroupe(), a.getCommentaire(), Etat.nom));
	}


	public Ajout(Module m) {

		this.requetes = "INSERT INTO Modules (codeMod, semMod, typeMod, libCourtMod, libLongMod, validMod, nbHeurPonc, etat) VALUES (?,?,?,?,?,?,?,?)";

		this.info = new ArrayList<>(List.of(m.getCode(), m.getSemestres(), m.getClass().getSimpleName(),
		                                    m.getLibCourt(), m.getLibLong(), m.isValide(), m.getHeurePonctuel(), Etat.nom));

		for (CategorieHeures cat : m.getHeures().keySet()) {
			List<Integer> lst = m.getHeures().get(cat);
			this.requetes += "INSERT INTO ModulesCatHeures (codeMod, libCatHeur, nbHeurePN, nbHeureSem, nbSemaine, etat) VALUES (?,?,?,?,?,?)";

			this.info.addAll(List.of(m.getCode(), cat.getlibCatHeur(), lst.get(0), lst.get(2), lst.get(1), Etat.nom));
		}
	}


	public Ajout(Intervenants inter) {

		this.requetes = "INSERT INTO Intervenants(nomInt, prenomInt, heureMinInt, heureMaxInt, categInt, etat) VALUES (?,?,?,?,?,?)";

		this.info = new ArrayList<>(List.of(inter.getNomIntervenant(), inter.getPrenomIntervenant(),
		                                    inter.getServices(), inter.getMaxHeures(), inter.getCategorieIntervenant().getCodeCatInt(), Etat.nom));
	}


	public Ajout(CategorieHeures cat) {

		this.requetes = "INSERT INTO CategorieHeures(libCatHeur, coefCatHeur, etat) VALUES (?,?,?)";

		this.info = new ArrayList<>(List.of(cat.getlibCatHeur(), cat.getcoefCatHeur(), Etat.nom));
	}


	// On doit supprimer tous les Intervenants et donc leur Affectations.....
	public Ajout(CategorieIntervenant cat) {
		
		this.requetes = "INSERT INTO CategorieIntervenants(codeCatInt, libCatInt, coefCatInt, heureMinCatInt, heureMaxCatInt,etat) VALUES (?,?,?,?,?,?)";

		this.info = new ArrayList<>(List.of(cat.getCodeCatInt(), cat.getLibCatInt(), cat.getCoefCatInt(), cat.getHeureMaxCatInt(), cat.getHeureMinCatInt(), Etat.nom));
	}
}
