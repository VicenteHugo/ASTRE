package model.action;

import java.util.ArrayList;
import java.util.List;

import model.Affectations;
import model.CategorieHeures;
import model.CategorieIntervenant;
import model.Intervenants;
import model.modules.Module;

public class Modification extends Action {
	private String requetes;
	private List<Object> info;

	public Modification(Affectations aOld,Affectations aNew) {
		// Intervenants inter, Module mode, CategorieHeures categorie, int nbSemaine,
		// int nbGroupe, String commentaire

		// intNom intPrenom codeMod libCatHeur nbSem nbGroupe commentaire
		this.requetes = "UPDATE Affectation SET intNom, intPrenom = ?, codeMod = ?, libCatHeur = ?,nbSem = ?, nbGroupe = ?, commentaire = ? WHERE intNom = ? AND intPrenom = ? AND codeMod = ? AND libCatHeur = ?";
		
		
		this.info = new ArrayList<>(
				List.of(aNew.getIntervenant().getNomIntervenant(), aNew.getIntervenant().getPrenomIntervenant(),
						aNew.getModule().getCode(), aNew.getCategorieHeures().getlibCatHeur(), aNew.getNbSemaine(),
						aNew.getNbGroupe(), aNew.getCommentaire(), aOld.getIntervenant().getNomIntervenant(),
						aOld.getModule().getCode(), aOld.getCategorieHeures().getlibCatHeur() ));
	}

	public Modification(Module mOld) {
		this.requetes = "UPDATE Modules SET semMod = ?, libCourtMod = ?, libLongMod = ?, validMod = ?, nbHeurPonc = ? WHERE codeMod = ?";
		String code = m.getCode();
		this.info = new ArrayList<>(
				List.of(m.getSemestres(), m.getLibCourt(), m.getLibLong(), m.isValide(), m.getHeurePonctuel(), code));
	}

	public Modification(Intervenants inter) {
		this.requetes = "UPDATE Intervenant SET heureMinInt = ?, heureMaxInt = ?, categInt = ? WHERE intNom = ? AND intPrenom = ?;";

		String nom = inter.getNomIntervenant();
		String prenom = inter.getPrenomIntervenant();

		this.info = new ArrayList<>(
				List.of(inter.getServices(), inter.getMaxHeures(), inter.getCategorieIntervenant(), nom, prenom));
	}

	public Modification(CategorieHeures cat) {
		this.requetes = "UPDATE CategorieHeures SET coefCatHeur = ? WHERE libCatHeur = ? ;";

		String code = cat.getlibCatHeur();

		this.info = new ArrayList<>(List.of(cat.getcoefCatHeur(), code));
	}

	public Modification(CategorieIntervenant cat) {

		this.requetes = "UPDATE CategorieIntervenants SET libCatInt = ?, coefCatInt = ?, heureMinCatInt = ?, heureMaxCatInt = ? WHERE codeCatInt = ?;";

		String code = cat.getCodeCatInt();

		this.info.addAll(List.of(cat.getLibCatInt(), cat.getCoefCatInt(), cat.getHeureMinCatInt(),
				cat.getHeureMaxCatInt(), code));
	}

	public String getRequeteSQL() {
		return this.requetes;
	}

	public List<Object> getInfo() {
		return this.info;
	}

}
