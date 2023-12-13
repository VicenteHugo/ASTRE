package model.action;

import java.util.ArrayList;
import java.util.List;

import model.Affectations;
import model.CategorieHeures;
import model.CategorieIntervenant;
import model.Etat;
import model.Intervenants;
import model.modules.Module;

public class Ajout extends Action
{
	private String   requetes;
	private List<Object> info;




	public Ajout (Affectations a) 
	{
		// Intervenants inter, Module mode, CategorieHeures categorie, int nbSemaine,
		// int nbGroupe, String commentaire

		// intNom intPrenom codeMod libCatHeur nbSem nbGroupe commentaire
		this.requetes = "INSERT INTO Affectation(intNom, intPrenom, codeMod, libCatHeur, nbSem, nbGroupe, commentaire) VALUES (?,?,?,?,?,?,?)";
		this.info = new ArrayList<>(List.of(a.getIntervenant().getNomIntervenant(), a.getIntervenant().getPrenomIntervenant(), a.getModule().getCode(),
		                                    a.getNbSemaine(), a.getNbGroupe(), a.getCommentaire()));
	}

	public Ajout (Module m) 
	{ 
		this.requetes = "INSERT INTO FROM Modules WHERE codeMod = ?" ;

		String code = m.getCode();
		this.info = new ArrayList<>(List.of(code, code, code));
	}

	public Ajout (Intervenants inter) 
	{ 
		this.requetes = "DELETE FROM Affectation WHERE intNom = ? AND intPrenom = ?;" +
		                "DELETE FROM Intervenants WHERE intNom = ? AND intPrenom = ?";

		String nom    = inter.getNomIntervenant();
		String prenom = inter.getPrenomIntervenant();

		this.info = new ArrayList<>(List.of(nom, prenom, nom, prenom));
	}

	public Ajout (CategorieHeures cat) 
	{
		this.requetes = "DELETE FROM Affectation WHERE libCatHeur = ? ;" +
				"DELETE FROM ModulesCatHeures WHERE libCatHeur = ? ;" +
				"DELETE FROM CategorieHeures WHERE libCatHeur = ?";

		String code = cat.getlibCatHeur();

		this.info = new ArrayList<>(List.of(code, code, code));
	}



	//On doit supprimer tous les Intervenants et donc leur Affectations.....
	public Ajout (CategorieIntervenant cat) 
	{
		List<Intervenants> lst = Etat.getIntervenants(cat);

		for (Intervenants i : lst)
		{
			Ajout sup = new Ajout(i);
			this.requetes += sup.requetes;
			this.info.addAll(sup.info);
		}


		this.requetes = "DELETE FROM Affectation WHERE libCatHeur = ? ;" +
		                "DELETE FROM ModulesCatHeures WHERE libCatHeur = ? ;" +
		                "DELETE FROM CategorieHeures WHERE libCatHeur = ?" ;

		String code    = cat.getCodeCatInt();

		this.info.addAll(List.of(code, code, code));
	}




	public String       getRequeteSQL() {return this.requetes;}
	public List<String> getInfo      () {return this.info;}
	
}
