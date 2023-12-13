package controleur;

import view.accueil.FrameAccueil;

import java.util.ArrayList;

import model.CategorieHeures;
import model.CategorieIntervenant;
import model.Etat;
import model.Intervenants;
import model.Semestres;
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

	public ArrayList<Semestres> getSemestres() {
		return Etat.getSemestres();
	}

	/*-------------------------------------------------------------*/
	/* SET-TEURS */
	/*-------------------------------------------------------------*/

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	/*-------------------------------------------------------------*/
	/* MAIN */
	/*-------------------------------------------------------------*/
	public static void main(String[] args) {
		Controleur.creerControleur();
	}

}
