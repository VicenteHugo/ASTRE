package model.action;

import java.util.ArrayList;
import java.util.List;

import model.Affectations;
import model.CategorieHeures;
import model.CategorieIntervenant;
import model.Etat;
import model.Intervenants;
import model.modules.Module;

public class Modification extends Action {

	public Modification(Affectations aOld, Affectations aNew) {
		
		this.requetes = "UPDATE Affectation" + Etat.nom + " SET intNom, intPrenom = ?, codeMod = ?, libCatHeur = ?,nbSem = ?, nbGroupe = ?, commentaire = ? WHERE intNom = ? AND intPrenom = ? AND codeMod = ? AND libCatHeur = ?";
		
		this.info = new ArrayList<>(
				List.of(aNew.getIntervenant().getNomIntervenant(), aNew.getIntervenant().getPrenomIntervenant(),
						aNew.getModule().getCode(), aNew.getCategorieHeures().getlibCatHeur(), aNew.getNbSemaine(),
						aNew.getNbGroupe(), aNew.getCommentaire(), aOld.getIntervenant().getNomIntervenant(),
						aOld.getModule().getCode(), aOld.getCategorieHeures().getlibCatHeur() ));
	}

	public Modification(Module mOld, Module mNew) {

		this.requetes = "UPDATE Modules" + Etat.nom + " SET codeMod = ?, semMod = ?, libCourtMod = ?, libLongMod = ?, validMod = ?, nbHeurPonc = ? WHERE codeMod = ?";

		this.info = new ArrayList<>(
				List.of(mNew.getCode(), mNew.getSemestres(), mNew.getLibCourt(), mNew.getLibLong(), mNew.isValide(), mNew.getHeurePonctuel(), mOld.getCode()));
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
}