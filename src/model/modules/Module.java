package model.modules;

import model.Semestres;

public class Module {

	private String libelleLongModule;
	private String libelleCourtModule;
	private Semestres semestres;
	private int nbEtudiant;
	private int nbGroupeTD;
	private int nbGroupeTP;
	private boolean valide;

	/*-------------------------------------------------------------*/
	/* CONSTRUCTEURS */
	/*-------------------------------------------------------------*/

	/**
	 * Constructeur prenant tous les paramètres demandées.
	 */

	public Module(String libelleLongModule, String libelleCourtModule, Semestres semestres, int nbEtudiant,
			int nbGroupeTD, int nbGroupeTP, boolean valide) {

		this.libelleLongModule = libelleLongModule;
		this.libelleCourtModule = libelleCourtModule;
		this.semestres = semestres;
		this.nbEtudiant = nbEtudiant;
		this.nbGroupeTD = nbGroupeTD;
		this.nbGroupeTP = nbGroupeTP;
		this.valide = valide;

	}

	public String getLibelleLongModule() {
		return libelleLongModule;
	}

	public String getLibelleCourtModule() {
		return libelleCourtModule;
	}

	public Semestres getSemestres() {
		return semestres;
	}

	public int getNbEtudiant() {
		return nbEtudiant;
	}

	public int getNbGroupeTD() {
		return nbGroupeTD;
	}

	public int getNbGroupeTP() {
		return nbGroupeTP;
	}

	public void setLibelleLongModule(String libelleLongModule) {
		this.libelleLongModule = libelleLongModule;
	}

	public void setLibelleCourtModule(String libelleCourtModule) {
		this.libelleCourtModule = libelleCourtModule;
	}

	public void setSemestres(Semestres semestres) {
		this.semestres = semestres;
	}

	public void setNbEtudiant(int nbEtudiant) {
		this.nbEtudiant = nbEtudiant;
	}

	public void setNbGroupeTD(int nbGroupeTD) {
		this.nbGroupeTD = nbGroupeTD;
	}

	public void setNbGroupeTP(int nbGroupeTP) {
		this.nbGroupeTP = nbGroupeTP;
	}

	public boolean isValidate() {
		return valide;
	}
}