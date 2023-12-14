package controleur;

import view.accueil.FrameAccueil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

public class Controleur {

	private static Controleur controleur;
	private FrameAccueil frameAccueil;
	private Etat etat;

	public Controleur() {
		this.frameAccueil = new FrameAccueil();
		this.etat = new Etat();
	}

	public static Controleur creerControleur() {
		if (controleur == null)
			controleur = new Controleur();

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

	public ArrayList<Module> getModules() {
		return Etat.getModules();
	}

	public ArrayList<Module> getModules(int i) {

		ArrayList<Module> retour = new ArrayList<>();
		for (Module m : Etat.getModules()) {
			if (m.getSemestres().getNumSem() == i) {
				retour.add(m);
			}
		}

		return retour;
	}

	public ArrayList<Module> getModules(Semestres semestres) {
		ArrayList<Module> retour = new ArrayList<>();
		for (Module m : Etat.getModules()) {
			if (m.getSemestres().equals(semestres)) {
				retour.add(m);
			}
		}
		return retour;
	}

	public ArrayList<Semestres> getSemestres() {
		return Etat.getSemestres();
	}

	public ArrayList<Affectations> getAffectations() {
		return Etat.getAffectations();
	}

	/*-------------------------------------------------------------*/
	/* SET-TEURS */
	/*-------------------------------------------------------------*/

	public void setEtat(Etat etat) {
		this.etat = etat;
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

	public void enregistrer() {
		Etat.enregistrer();
	}

	public void annuler() {
		Etat.anuller();
	}

	public void supprimerCategorieHeure(int i) {
		if (i >= 0 && i < Etat.getCategoriesHeures().size()) {
			CategorieHeures cat = Etat.getCategoriesHeures().remove(i);
			Etat.ajouterAction(new Suppression(cat));
		}
	}

	public void supprimerCategorieIntervenants(int i) {
		if (i >= 0 && i < Etat.getCategoriesIntervenants().size()) {
			CategorieIntervenant cat = Etat.getCategoriesIntervenants().remove(i);
			Etat.ajouterAction(new Suppression(cat));
			System.out.println("Suppresion : " + cat);
		}
	}

	public boolean modifCategorieHeures(int i, String lib, float coef) {

		CategorieHeures cOld = Etat.getCategoriesHeures().get(i);

		// Si la clé est pris par autre chose que l'objet actuelle et que l'indice est
		// bon
		if ((Etat.getCatHeure(lib) == null || Etat.getCatHeure(lib) == cOld) && i >= 0
				&& i < Etat.getCategoriesHeures().size()) {

			// On remplace l'objet
			CategorieHeures cNew = new CategorieHeures(lib, coef);
			System.out.println(cNew);
			Etat.getCategoriesHeures().add(i, cNew);
			Etat.getCategoriesHeures().remove(cOld);

			// On ajouter l'action
			Etat.ajouterAction(new Modification(cOld, cNew));
			return true;
		}

		return false;
	}

	public boolean modifCategorieIntervenants(int i, String code, String lib, float coef, int hMax, int hMin) {

		CategorieIntervenant cOld = Etat.getCategoriesIntervenants().get(i);

		System.out.println("Meme objet ? : " + (Etat.getCatInt(code) == cOld));
		System.out.println("Objet null ? : " + (Etat.getCatInt(code) == null));

		// Si la clé est pris par autre chose que l'objet actuelle et que l'indice est
		// bon
		if ((Etat.getCatInt(code) == null || Etat.getCatInt(code) == cOld) && i >= 0
				&& i < Etat.getCategoriesIntervenants().size()) {

			// On remplace l'objet
			CategorieIntervenant cNew = new CategorieIntervenant(code, lib, coef, hMax, hMin);
			System.out.println(cNew);
			Etat.getCategoriesIntervenants().add(i, cNew);
			Etat.getCategoriesIntervenants().remove(cOld);

			// On ajouter l'action
			Etat.ajouterAction(new Modification(cOld, cNew));
			return true;
		}

		return false;
	}

	public void ajouterIntervenant(Intervenants inter) {
		Etat.ajouterAction(new Ajout(inter));
		Etat.ajouterIntervenant(inter);
	}

	public void supprimerIntervenant(Intervenants inter) {

	}

	/*-------------------------------------------------------------*/
	/* MAIN */
	/*-------------------------------------------------------------*/
	public static void main(String[] args) {
		Controleur.creerControleur();
	}

}
