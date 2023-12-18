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

		this.requetes = "INSERT INTO Affectation" + Etat.nom+ " (intNom, intPrenom, codeMod, libCatHeur, nbSem, nbGroupe, commentaire) VALUES (?,?,?,?,?,?,?)";
		this.info = new ArrayList<>(List.of(a.getIntervenant().getNomIntervenant(),
				a.getIntervenant().getPrenomIntervenant(), a.getModule().getCode(),
				a.getNbSemaine(), a.getNbGroupe(), a.getCommentaire()));
	}

	public Ajout(Module m) {

		this.requetes = "INSERT INTO Modules" + Etat.nom+ " (codeMod, semMod, typeMod, libCourtMod, libLongMod, validMod, nbHeurPonc) VALUES (?,?,?,?,?,?,?)";

		this.info = new ArrayList<>(List.of(m.getCode(), m.getSemestres(), m.getClass().getSimpleName(),
				m.getLibCourt(), m.getLibLong(), m.isValide(), m.getHeurePonctuel()));

		for (CategorieHeures cat : m.getHeures().keySet()) {
			List<Integer> lst = m.getHeures().get(cat);
			this.requetes += "INSERT INTO ModulesCatHeures" + Etat.nom+ " (codeMod, libCatHeur, nbHeurePN, nbHeureSem, nbSemaine) VALUES (?,?,?,?,?)";

			this.info.addAll(List.of(m.getCode(), cat.getlibCatHeur(), lst.get(0), lst.get(2), lst.get(1)));
		}
	}

	public Ajout(Intervenants inter) {

		this.requetes = "INSERT INTO Intervenants" + Etat.nom + " (nomInt, prenomInt, heureMinInt, heureMaxInt, categInt,coefInt) VALUES (?,?,?,?,?,?)";

		this.info = new ArrayList<>(List.of(inter.getNomIntervenant(), inter.getPrenomIntervenant(),
				inter.getServices(), inter.getMaxHeures(), inter.getCategorieIntervenant().getCodeCatInt(),inter.getCoefficient()));
	}

	public Ajout(CategorieHeures cat) {

		this.requetes = "INSERT INTO CategorieHeures" + Etat.nom + " (libCatHeur, coefCatHeur) VALUES (?,?)";

		this.info = new ArrayList<>(List.of(cat.getlibCatHeur(), cat.getcoefCatHeur()));
	}

	// On doit supprimer tous les Intervenants et donc leur Affectations.....
	public Ajout(CategorieIntervenant cat) {

		this.requetes = "INSERT INTO CategorieIntervenants" + Etat.nom
				+ " (codeCatInt, libCatInt, coefCatInt, heureMinCatInt, heureMaxCatInt) VALUES (?,?,?,?,?)";

		this.info = new ArrayList<>(List.of(cat.getCodeCatInt(), cat.getLibCatInt(), cat.getCoefCatInt(),
				cat.getHeureMinCatInt(), cat.getHeureMaxCatInt()));
	}
}
