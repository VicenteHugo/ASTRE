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

	private FrameAccueil frameAccueil;

	public Controleur() {
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

	public ArrayList<Semestres> getSemestres() {
		return Etat.getSemestres();
	}
}
