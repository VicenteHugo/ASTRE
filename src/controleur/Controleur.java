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

	public static Controleur creerControleur() {
		if (controleur == null)
			controleur = new Controleur();

		return controleur;
	}

	public static Controleur getControleur() {
		return Controleur.controleur;
	}

	private Controleur() {
		this.frameAccueil = new FrameAccueil();
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

	public ArrayList<Module> getModules(Semestres semestres) {
		ArrayList<Module> retour = new ArrayList<>();
		for (Module m : Etat.getModules()) {
			if (m.getSemestres().equals(semestres)) {
				retour.add(m);
			}
		}
		return retour;
	}

	public int getNbModule() {
		return Etat.getModules().size();
	}

	public ArrayList<Semestres> getSemestres() {
		return Etat.getSemestres();
	}

	public static void main(String[] args) {
		Controleur.creerControleur();
	}

}
