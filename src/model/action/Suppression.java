package model.action;

import java.util.ArrayList;
import java.util.List;

import model.Affectations;
import model.CategorieHeures;
import model.CategorieIntervenant;
import model.Etat;
import model.Intervenants;
import model.modules.Module;

public class Suppression extends Action
{
	//pas de maj pour le nom de la méthode
	public Suppression (Affectations a) 
	{ 
		this.requetes = "DELETE FROM Affectation" + Etat.nom + " WHERE nomInt = ? AND prenomInt = ? AND codeMod = ? AND libCatHeur = ?";
		this.info = new ArrayList<>(List.of(a.getIntervenant().getNomIntervenant(), a.getIntervenant    ().getPrenomIntervenant(),
		                                    a.getModule     ().getCode()          , a.getCategorieHeures().getlibCatHeur       () ));
	}

	public Suppression (Module m) 
	{ 
		this.requetes  = "DELETE FROM Affectation" + Etat.nom + " WHERE codeMod = ?;" ;
		this.requetes += "DELETE FROM Modules" + Etat.nom + " WHERE codeMod = ?;" ;
		this.requetes += "DELETE FROM ModulesCatHeures" + Etat.nom + " WHERE codeMod = ?;" ;

		String code = m.getCode();
		this.info = new ArrayList<>(List.of(code, code, code));
	}

	public Suppression (Intervenants inter) 
	{ 
		this.requetes = "DELETE FROM Intervenants" + Etat.nom + " WHERE nomInt = ? AND prenomInt = ?";

		String nom    = inter.getNomIntervenant();
		String prenom = inter.getPrenomIntervenant();

		this.info = new ArrayList<>(List.of(nom, prenom));
	}

	public Suppression (CategorieHeures cat) 
	{
		this.requetes = "DELETE FROM CategorieHeures" + Etat.nom + " WHERE libCatHeur = ?";

		String code = cat.getlibCatHeur();

		this.info = new ArrayList<>(List.of(code));
	}



	//On doit supprimer tous les Intervenants et donc leur Affectations.....
	public Suppression(CategorieIntervenant cat) {
		
		this.requetes = "DELETE FROM CategorieIntervenants" + Etat.nom + " WHERE codeCatInt = ?" ;

		String code    = cat.getCodeCatInt();

		this.info = new ArrayList<>(List.of(code));
	}
	
}
