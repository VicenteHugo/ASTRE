package model.modules;

import java.util.List;
import java.util.HashMap;

import model.CategorieHeures;
import model.Semestres;

public class ModuleRetry {

	private Semestres semestres;
	private String code;
	private String libLong;
	private String libCourt;
	private boolean valide;

	/** Liste d'heures(heures PN, nb Semaines, heures par semaines) par catégorie */
	protected HashMap<CategorieHeures, List<Integer>> heures;

	public ModuleRetry(Semestres semestres, String code, String libLong, String libCourt) {
		this.semestres = semestres;
		this.code = code;
		this.libLong = libLong;
		this.libCourt = libCourt;

		this.valide = false;
		this.heures = new HashMap<>();
	}

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

	public HashMap<CategorieHeures, List<Integer>> getHeures() {
		return this.heures;
	}

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

	public void setHeures(HashMap<CategorieHeures, List<Integer>> heures) {
		this.heures = heures;
	}
}