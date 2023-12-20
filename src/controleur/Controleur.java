package controleur;

import view.accueil.FrameAccueil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Affectations;
import model.CategorieHeures;
import model.CategorieIntervenant;
import model.Etat;
import model.Intervenants;
import model.Semestres;
import model.action.Ajout;
import model.action.Modification;
import model.action.Suppression;
import model.modules.Module;
import model.modules.PPP;
import model.modules.Ressource;
import model.modules.Sae;
import model.modules.Stage;

public class Controleur {

	private static Controleur controleur;

	public Controleur() {
		new Etat();
		new FrameAccueil();
	}

	public static Controleur creerControleur() {
		if (Controleur.controleur == null)
			Controleur.controleur = new Controleur();

		return controleur;
	}

	/*-------------------------------------------------------------*/
	/* GET-TEURS */
	/*-------------------------------------------------------------*/

	public static Controleur getControleur() {
		return Controleur.controleur;
	}

	public ArrayList<CategorieIntervenant> getCategorieIntervenants() {
		return Etat.getCategoriesIntervenants();
	}

	public ArrayList<CategorieHeures> getCategorieHeures() {
		return Etat.getCategoriesHeures();
	}

	public ArrayList<Intervenants> getIntervenants() {
		return Etat.getIntervenants();
	}

	public Intervenants getIntervenants(int i){
		return Etat.getIntervenants(i);
	}

	public ArrayList<Module> getModules() {
		return Etat.getModules();
	}

	public ArrayList<Module> getModules(int semmestre) {

		ArrayList<Module> retour = new ArrayList<Module>();
		for (Module m : Etat.getModules()) {
			if (m.getSemestres().getNumSem() == semmestre) {
				retour.add(m);
			}
		}

		return retour;
	}

	public Module getModule(int i) {
		return Etat.getModules().get(i);
	}

	public ArrayList<Module> getModules(Semestres semestres) {
		ArrayList<Module> retour = new ArrayList<Module>();
		for (Module m : Etat.getModules()) {
			if (m.getSemestres().equals(semestres)) {
				retour.add(m);
			}
		}
		return retour;
	}

	public Module getModule(String code){
		return Etat.getModule(code);
	}

	public ArrayList<Semestres> getSemestres() {
		return Etat.getSemestres();
	}

	public ArrayList<Affectations> getAffectations() {
		return Etat.getAffectations();
	}

	public CategorieIntervenant getCategorieIntervenant(String nom){
		return Etat.getCatInt(nom);
	}

	public CategorieHeures getCategorieHeure (String nom) { return Etat.getCatHeure(nom);}

	public String[] getEtats() {
		return Etat.getEtats();
	}

	/*-------------------------------------------------------------*/
	/* AUTRE */
	/*-------------------------------------------------------------*/

	public boolean ajouterCategorieHeure(String lib, float coeff) {

		if (Etat.getCatHeure(lib) != null)
			return false;

		CategorieHeures cat = new CategorieHeures(lib, coeff);
		Etat.ajouterAction(new Ajout(cat));
		Etat.ajouterCategorieHeure(cat);
		return true;
	}

	public boolean ajouterCategorieIntervenant(String code, String libCat, float coeff, int heureMinCatInt,
			int heureMaxCatInt) {

		if (Etat.getCatInt(code) != null)
			return false;

		CategorieIntervenant c = new CategorieIntervenant(code, libCat, coeff, heureMinCatInt, heureMaxCatInt);
		Etat.ajouterAction(new Ajout(c));
		Etat.ajouterCategorieIntervenant(c);
		return true;
	}





	/*----------------------------------------------------*/
	/*                        ACTION                      */
	/*----------------------------------------------------*/

	/* GENERAL */
	public void enregistrer() { Etat.enregistrer(); }
	public void annuler    () { Etat.anuller();     }

	
	/* SEMESTRES */
	public void modifSemestres (Semestres sem) { Etat.ajouterAction(new Modification(sem));}


	/* CATEGORIE-HEURE */
	public void supprimerCategorieHeure(int i) {
		if (i >= 0 && i < Etat.getCategoriesHeures().size()) {
			CategorieHeures cat = Etat.getCategoriesHeures().remove(i);
			Etat.ajouterAction(new Suppression(cat));
		}
	}

	public boolean supprimerCategorieIntervenants(int i) {
		CategorieIntervenant cat = Etat.getCategoriesIntervenants().get(i);

		if (Etat.pasUtiliser(cat)) {
			Etat.ajouterAction(new Suppression(cat));
			Etat.getCategoriesIntervenants().remove(cat);
			return true;
		}

		return false;
	}

	public boolean modifCategorieHeures(int i, String lib, float coef) {

		CategorieHeures cOld = Etat.getCategoriesHeures().get(i);

		// Si la clé est pris par autre chose que l'objet actuelle et que l'indice est
		// bon
		if ((Etat.getCatHeure(lib) == null || Etat.getCatHeure(lib) == cOld) && i >= 0
				&& i < Etat.getCategoriesHeures().size()) {

			// On remplace l'objet
			CategorieHeures cNew = new CategorieHeures(lib, coef);
			Etat.getCategoriesHeures().add(i, cNew);
			Etat.getCategoriesHeures().remove(cOld);

			// On ajouter l'action
			Etat.ajouterAction(new Modification(cOld, cNew));
			return true;
		}

		return false;
	}


	/* CATEGORIE INTERVENANTS */
	public boolean modifCategorieIntervenants(int i, String code, String lib, float coef, int hMax, int hMin) {

		CategorieIntervenant cOld = Etat.getCategoriesIntervenants().get(i);

		// Si la clé est pris par autre chose que l'objet actuelle et que l'indice est
		// bon
		if ((Etat.getCatInt(code) == null || Etat.getCatInt(code) == cOld) && i >= 0
				&& i < Etat.getCategoriesIntervenants().size()) {

			// On remplace l'objet
			CategorieIntervenant cNew = new CategorieIntervenant(code, lib, coef, hMax, hMin);
			Etat.getCategoriesIntervenants().add(i, cNew);
			Etat.getCategoriesIntervenants().remove(cOld);

			// On ajouter l'action
			Etat.ajouterAction(new Modification(cOld, cNew));
			return true;
		}

		return false;
	}


	/* INTERVENANTS */
	public void ajouterIntervenant(Intervenants inter) {
		Etat.ajouterAction(new Ajout(inter));
		Etat.ajouterIntervenant(inter);
	}

	public void supprimerIntervenant(int ind) {
		if (ind >= 0 && ind < Etat.getIntervenants().size()) {
			Intervenants inter = Etat.getIntervenants().remove(ind);
			Etat.ajouterAction(new Suppression(inter));
		}
	}

	public boolean modifIntervenant(int i, CategorieIntervenant categ, String nomIntervenant, String prenomIntervenant, int services, int mexHeure, float coef) {
		Intervenants cOld = Etat.getIntervenants(i);

		if ((Etat.getIntervenant(nomIntervenant, prenomIntervenant) == null
				|| Etat.getIntervenant(nomIntervenant, prenomIntervenant) == cOld) && i >= 0
				&& i < Etat.getIntervenants().size()) {
			// On remplace l'objet
			Intervenants cNew = new Intervenants(categ, nomIntervenant, prenomIntervenant, services, mexHeure,coef);
			Etat.getIntervenants().add(i, cNew);
			Etat.getIntervenants().remove(cOld);

			// On ajouter l'action
			Etat.ajouterAction(new Modification(cOld, cNew));
			return true;
		}

		return false;
	}


	/* AFFECTATIONS */
	
	public void ajouterAffectation(Affectations affect) {
		Etat.ajouterAction(new Ajout(affect));
		Etat.ajouterAffectation(affect);
	}

	public void supprimerAffectation(int ind) {

		if (ind >= 0 && ind < Etat.getAffectations().size()) {
			Affectations inter = Etat.getAffectations().remove(ind);
			Etat.ajouterAction(new Suppression(inter));
		}
	}
	
	public boolean modifAffectation(int i, String nomIntervenant,String prenom, Module m,String type, int nbSem, int nbGp, String com) {
		Affectations    aOld = Etat.getAffectations(i);
		Intervenants    intervenants = null;
		CategorieHeures categ = null;

		if ((Etat.getAffectations(nomIntervenant) == null
				|| Etat.getAffectations(nomIntervenant) == aOld) && i >= 0
				&& i < Etat.getIntervenants().size()) {
			// On remplace l'objet
			for(Intervenants inter : Etat.getIntervenants()){
				if(inter.getNomIntervenant().equals(nomIntervenant) && inter.getPrenomIntervenant().equals(prenom)){
					intervenants = inter;
				}
			}
			for(CategorieHeures ch : Etat.getCategoriesHeures()) {
				if(ch.getlibCatHeur().equals(type)){
					categ = ch;
				}
			}
			Affectations aNew = new Affectations(intervenants, m, categ, nbSem, nbGp,com);
			Etat.getAffectations().add(i, aNew);
			Etat.getAffectations().remove(aOld);

			// On ajouter l'action
			Etat.ajouterAction(new Modification(aOld, aNew));
			return true;
		}

		return false;
	}

	/* MODULES */
    public boolean ajouterModule(Module mod) {        
        if (Etat.getModule(mod.getCode()) != null)
            return false;

        Etat.ajouterAction(new Ajout(mod));
        Etat.ajouterModule(mod);
        return true;
    }
    
    public boolean modifModules(Module mOld, String code, String libL, String libC, int hp, boolean v, HashMap<CategorieHeures, List<Integer>> heures) {

        if ((Etat.getModule(code) == null || Etat.getModule(code) == mOld)) {
            // On remplace l'objet
            Module mNew = null;

            if (mOld instanceof Ressource)
                mNew = new Ressource(mOld.getSemestres(), code, libL, libC, hp, v);
            if (mOld instanceof PPP)
                mNew = new PPP(mOld.getSemestres(), code, libL, libC, hp, v);
            if (mOld instanceof Sae)
                mNew = new Sae(mOld.getSemestres(), code, libL, libC, hp, v);
            if (mOld instanceof Stage)
                mNew = new Stage(mOld.getSemestres(), code, libL, libC, hp, v);

            mNew.setHeures(heures);

            int i = Etat.getModules().indexOf(mOld);
            Etat.getModules().remove(mOld);
            Etat.getModules().add(i, mNew);

			for (Affectations a : mOld.getLstAffectations())
				a.setModule(mNew);

            // On ajouter l'action
            Etat.ajouterAction(new Modification(mOld, mNew));
            return true;
        }

        return false;
    }

    public boolean supprimerModule(Module m) {

        if (Etat.pasUtiliser(m)) {
            Etat.ajouterAction(new Suppression(m));
            Etat.getModules().remove(m);
            return true;
     	}

       return false;
    }


	public boolean nomEtatLibre (String nom) {
		for (String nomEtat : Etat.getEtats())
			if (nomEtat.equals(nom))
				return false;
		
		return true;
	}

	public void changerEtat (String nom) {
		Etat.changerEtat(nom);
	}

	public boolean creerEtat (String nom) {
		return Etat.creerEtat(nom);
	}

	public void dupliquerEtat (String etatDest,String etatDep) {
		Etat.dupliquerEtat(etatDest, etatDep);
	}

	public boolean suppEtat (String etat) {
		return Etat.suppEtat(etat);
	}

	public void genererCSV () { Etat.genererCSV();}
	
	public List<Affectations> getAffectations(Module mod){
		return Etat.getAffectations(mod);
	}
	/*-------------------------------------------------------------*/
	/* MAIN */
	/*-------------------------------------------------------------*/
	public static void main(String[] args) {
		Controleur.creerControleur();

		// GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		// String[] policesDisponibles = ge.getAvailableFontFamilyNames();

		// for (String police : policesDisponibles) {
		// }
	}

}
