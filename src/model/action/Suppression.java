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
	private String   requetes;
	private List<String> info;




	public Suppression (Affectations a) 
	{ 
		this.requetes = "DELETE FROM Affectation WHERE intNom = ? AND intPrenom = ? AND codeMod = ? AND libCatHeur = ?";
		this.info = new ArrayList<>(List.of(a.getIntervenant().getNomIntervenant(), a.getIntervenant    ().getPrenomIntervenant(),
		                                    a.getModule     ().getCode()          , a.getCategorieHeures().getlibCatHeur       () ));
	}

	public Suppression (Module m) 
	{ 
		this.requetes = "DELETE FROM Affectation WHERE codeMod = ? ;" +
		                "DELETE FROM ModulesCatHeures WHERE codeMod = ? ;" +
		                "DELETE FROM Modules WHERE codeMod = ?" ;

		String code = m.getCode();
		this.info = new ArrayList<>(List.of(code, code, code));
	}

	public Suppression (Intervenants inter) 
	{ 
		this.requetes = "DELETE FROM Affectation WHERE intNom = ? AND intPrenom = ?;" +
		                "DELETE FROM Intervenants WHERE intNom = ? AND intPrenom = ?";

		String nom    = inter.getNomIntervenant();
		String prenom = inter.getPrenomIntervenant();

		this.info = new ArrayList<>(List.of(nom, prenom, nom, prenom));
	}

	public Suppression (CategorieHeures cat) 
	{
		this.requetes = "DELETE FROM Affectation WHERE libCatHeur = ? ;" +
				"DELETE FROM ModulesCatHeures WHERE libCatHeur = ? ;" +
				"DELETE FROM CategorieHeures WHERE libCatHeur = ?";

		String code = cat.getlibCatHeur();

		this.info = new ArrayList<>(List.of(code, code, code));
	}



	//On doit supprimer tous les Intervenants et donc leur Affectations.....
	public Suppression (CategorieIntervenant cat) 
	{
		List<Intervenants> lst = Etat.getIntervenants(cat);

		for (Intervenants i : lst)
		{
			Suppression sup = new Suppression(i);
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
