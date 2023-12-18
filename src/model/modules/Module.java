package model.modules;

import java.util.List;

import java.util.ArrayList;
import java.util.HashMap;

import model.CategorieHeures;
import model.Semestres;

public abstract class Module implements Comparable<Module> {

	/*-------------------------------------------------------------*/
	/* ATTRIBUTS */
	/*-------------------------------------------------------------*/

	/** Semestre assignée à un module */
	private Semestres semestres;

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
	 * Liste d'heures(heures PN, nb Semaines, heures par semaines, heures ponctuels)
	 * par catégorie
	 */
	protected HashMap<CategorieHeures, List<Integer>> heures;

	/*
	 * Constructeur du Module prenant en paramètre le semestre, le code, le libellé
	 * long et court et les heures ponctuels
	 */
	public Module(Semestres semestres, String code, String libLong, String libCourt, int heurePonctuel) {
		this.semestres = semestres;
		this.code = code;
		this.libLong = libLong;
		this.libCourt = libCourt;
		this.heurePonctuel = heurePonctuel;
		this.valide = false;
		this.listCategorieHeure = new ArrayList<CategorieHeures>();
		this.heures = new HashMap<>();
		initHashMap();
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
		int heurePN = 0;
		for (CategorieHeures catH : this.heures.keySet()) {
			heurePN += this.heures.get(catH).get(0);
		}
		return heurePN;
	}

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

	public int compareTo(Module module) {
		return this.code.compareTo(module.code);
	}

	@Override
	public String toString() {
		return "Module [semestres=" + semestres + ", code=" + code + ", libLong=" + libLong + ", libCourt=" + libCourt
				+ ", valide=" + valide + ", heurePonctuel=" + heurePonctuel + ", listCategorieHeure="
				+ listCategorieHeure + ", heures=" + heures + "]";
	}

	public void initHashMap(){
		ArrayList<Integer> list = new ArrayList<>();
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);	
		this.heures.put(null,list);
	}
}