package model.action;

import java.util.ArrayList;
import java.util.List;

import model.Affectations;
import model.CategorieHeures;
import model.CategorieIntervenant;
import model.Etat;
import model.Intervenants;
import model.Semestres;
import model.modules.Module;

public class Modification extends Action {

//pas de maj pour le nom de la méthode
	public Modification(Affectations aOld, Affectations aNew) {
		
		this.requetes = "UPDATE Affectation" + Etat.nom + " SET intNom, intPrenom = ?, codeMod = ?, libCatHeur = ?,nbSem = ?, nbGroupe = ?, commentaire = ? WHERE intNom = ? AND intPrenom = ? AND codeMod = ? AND libCatHeur = ?";
		
		this.info = new ArrayList<>(
				List.of(aNew.getIntervenant().getNomIntervenant(), aNew.getIntervenant().getPrenomIntervenant(),
						aNew.getModule().getCode(), aNew.getCategorieHeures().getlibCatHeur(), aNew.getNbSemaine(),
						aNew.getNbGroupe(), aNew.getCommentaire(), aOld.getIntervenant().getNomIntervenant(),
						aOld.getModule().getCode(), aOld.getCategorieHeures().getlibCatHeur() ));
	}

	public Modification(Module mOld, Module mNew) {

		this.info = new ArrayList<>();this.requetes = "INSERT INTO Modules" + Etat.nom+ " (codeMod, semMod, typeMod, libCourtMod, libLongMod, validMod, nbHeurPonc) VALUES (?,?,?,?,?,?,?);";

		this.info = new ArrayList<>(List.of(mNew.getCode(), mNew.getSemestres().getNumSem(), mNew.getClass().getSimpleName(), mNew.getLibCourt(), mNew.getLibLong(), mNew.isValide(), mNew.getHeurePonctuel()));

		for (CategorieHeures cat : mNew.getHeures().keySet()) {
			List<Integer> lst = mOld.getHeures().get(cat);
			this.requetes += "UPDATE ModulesCatHeures" + Etat.nom+ " SET nbHeurePN = ?, nbHeureSem = ?, nbSemaine = ?, codeMod = ? WHERE codeMod = ? AND libCatHeur = ?;";

			this.info.addAll(List.of(lst.get(0), lst.get(2), lst.get(1), mNew.getCode(), mOld.getCode(), cat.getlibCatHeur()));
		}

		this.requetes += "UPDATE Affectation" + Etat.nom+ " SET codeMod = ? WHERE codeMod = ?;";
		this.info.addAll(List.of(mNew.getCode(), mOld.getCode()));

		

	}

	public Modification(Intervenants iOld, Intervenants iNew) {

		this.requetes = "UPDATE Intervenants" + Etat.nom + " SET nomInt = ?, prenomInt = ?, heureMinInt = ?, heureMaxInt = ?, categInt = ?, coefInt = ? WHERE nomInt = ? AND prenomInt = ?;";

		this.info = new ArrayList<>(
				List.of(iNew.getNomIntervenant(), iNew.getPrenomIntervenant(), iNew.getServices(), iNew.getMaxHeures(),
						iNew.getCategorieIntervenant().getCodeCatInt(),iNew.getCoefficient(),iOld.getNomIntervenant(), iOld.getPrenomIntervenant()));
	}

	public Modification(CategorieHeures cOld, CategorieHeures cNew) {
		this.requetes = "UPDATE CategorieHeures" + Etat.nom + " SET libCatHeur = ?, coefCatHeur = ? WHERE libCatHeur = ? ;";

		this.info = new ArrayList<>(List.of(cNew.getlibCatHeur(), cNew.getcoefCatHeur(), cOld.getlibCatHeur()));
	}

	public Modification(CategorieIntervenant cOld, CategorieIntervenant cNew) {

		this.requetes = "UPDATE CategorieIntervenants" + Etat.nom + " SET codeCatInt = ?, libCatInt = ?, coefCatInt = ?, heureMinCatInt = ?, heureMaxCatInt = ? WHERE codeCatInt = ?;";

		this.info = new ArrayList<>(List.of(cNew.getCodeCatInt(), cNew.getLibCatInt(), cNew.getCoefCatInt(), cNew.getHeureMinCatInt(), cNew.getHeureMaxCatInt(), cOld.getCodeCatInt()));
	}

	public Modification(Semestres sem) {

		this.requetes = "UPDATE Semestres" + Etat.nom + " SET nbGpTdSem = ?, nbGpTpSem = ?, nbEtdSem = ?, nbSemSem = ? WHERE numSem = ?;";

		this.info = new ArrayList<>(List.of(sem.getNbGpTdSem(), sem.getNbGpTpSem(), sem.getNbEtdSem(), sem.getNbSemSem(), sem.getNumSem()));
	}
}