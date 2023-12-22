package model.modules;

import java.util.List;

import controleur.Controleur;

import java.util.ArrayList;
import java.util.HashMap;

import model.Affectations;
import model.CategorieHeures;
import model.Etat;
import model.Semestres;

public abstract class Module implements Comparable<Module> {

	/*-------------------------------------------------------------*/
	/* ATTRIBUTS */
	/*-------------------------------------------------------------*/

	/** Semestre assignée à un module */
	protected Semestres semestres;

	/** Code du module en fonction du semestre et de son libellé */
	private String code;

	/** Libellé long du module */
	private String libLong;

	/** Libellé court du module */
	private String libCourt;

	/** Boolean confirmant la validation du module */
	private boolean valide;

	/** Le nombre d'heure ponctuel */
	private int heurePonctuel;

	/** L'ensemble des catégories que le modules faient */
	protected List<CategorieHeures> listCategorieHeure;

	/**
	 * Liste d'heures(heures PN, nb Semaines, heures par semaines)
	 * par catégorie
	 */
	protected HashMap<CategorieHeures, List<Integer>> heures;

	/**
	 * Liste des affectations
	 */
	private List<Affectations> lstAffectations;

	/*
	 * Constructeur du Module prenant en paramètre le semestre, le code, le libellé
	 * long et court et les heures ponctuels
	 */
	public Module(Semestres semestres, String code, String libLong, String libCourt, int heurePonctuel, boolean valid) {
		this.semestres = semestres;
		this.code = code;
		this.libLong = libLong;
		this.libCourt = libCourt;
		this.heurePonctuel = heurePonctuel;
		this.valide = valid;
		this.listCategorieHeure = new ArrayList<CategorieHeures>();
		this.heures = new HashMap<>();

		this.lstAffectations = new ArrayList<Affectations>();
	}

	/*-------------------------------------------------------------*/
	/* GET-TEURS */
	/*-------------------------------------------------------------*/

	public Semestres getSemestres() {
		return this.semestres;
	}

	public String getCode() {
		return this.code;
	}

	public String getLibLong() {
		return this.libLong;
	}

	public String getLibCourt() {
		return this.libCourt;
	}

	public boolean isValide() {
		return this.valide;
	}

	public int getHeurePonctuel() {
		return this.heurePonctuel;
	}

	public HashMap<CategorieHeures, List<Integer>> getHeures() {
		return this.heures;
	}

	public List<CategorieHeures> getListCategorieHeure() {
		return listCategorieHeure;
	}

	public int getHeurePn(){
		int somme = 0;
		
		for (CategorieHeures catH : this.heures.keySet()) {

			int heurePN = this.heures.get(catH).get(0);

			System.out.println("Pour mod " + this.code + " (" +catH.getlibCatHeur()+") : " + heurePN + " * " + catH.getcoefCatHeur());

			somme += heurePN  * catH.getcoefCatHeur();

		}
		return somme;
	}

	public List<Affectations> getLstAffectations() {
		return lstAffectations;
	}


	public int getHeureAffecte () {
		int heure = 0;

		for (Affectations a : this.lstAffectations) {
			int nbHeureSem = 0;

			CategorieHeures cat = a.getCategorieHeures();
			
			if( this.heures.get(cat) != null) { //Si pas HP
				nbHeureSem = this.heures.get(cat).get(2);
				heure += a.getNbSemaine() * a.getNbGroupe() * nbHeureSem * cat.getcoefCatHeur();
			}else{
				heure += a.getNbGroupe() * cat.getcoefCatHeur();
			}

			System.out.println(this.code + " " + cat.getlibCatHeur() + " : " + a.getNbHeure() + " " + a.getNbSemaine() +" "+ a.getNbGroupe() +" "+ nbHeureSem +" "+ cat.getcoefCatHeur());

		}

		return heure;
	}


	public int getHeureTotal () {
		int heure = 0;

		for (CategorieHeures catH : this.heures.keySet()) {
			
			List<Integer> info = this.heures.get(catH);

			heure += info.get(1) * info.get(2) * catH.getcoefCatHeur();
		}

		float coef = Etat.getCatHeure("HP").getcoefCatHeur();

		return (int) (heure + this.heurePonctuel * coef);
	}



	public void addAffectations (Affectations a) { this.lstAffectations.add(a);}
	public void delAffectations (Affectations a) { this.lstAffectations.remove(a);}

	/*-------------------------------------------------------------*/
	/* SET-TEURS */
	/*-------------------------------------------------------------*/

	public void setSemestres(Semestres semestres) {
		this.semestres = semestres;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setLibLong(String libLong) {
		this.libLong = libLong;
	}

	public void setLibCourt(String libCourt) {
		this.libCourt = libCourt;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}

	public void setHeurePonctuel(int heurePonctuel) {
		this.heurePonctuel = heurePonctuel;
	}

	public void setHeures(HashMap<CategorieHeures, List<Integer>> heures) {
		this.heures = heures;
	}

	public void setListCategorieHeure(List<CategorieHeures> listCategorieHeure) {
		this.listCategorieHeure = listCategorieHeure;
	}

	/*-------------------------------------------------------------*/
	/* AUTRES */
	/*-------------------------------------------------------------*/

	abstract public void initList(int heurePN, int nbSemaine, int heureSemaine,
			CategorieHeures catH);

	public void initList(HashMap<CategorieHeures, List<Integer>> heures) {
		this.heures = heures;
	}

	public int compareTo(Module module) {
		return this.code.compareTo(module.code);
	}

	@Override
	public String toString() {
		return "Module [semestres=" + semestres + ", code=" + code + ", libLong=" + libLong + ", libCourt=" + libCourt
				+ ", valide=" + valide + ", heurePonctuel=" + heurePonctuel + ", listCategorieHeure="
				+ listCategorieHeure + ", heures=" + heures + "]";
	}
}