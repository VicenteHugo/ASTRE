package model;

import java.util.ArrayList;
import java.util.List;

public class Intervenants {

	/*-------------------------------------------------------------*/
	/* ATTRIBUTS */
	/*-------------------------------------------------------------*/

	/** Catégorie de l'intervenant. */
	private CategorieIntervenant categorieIntervenant;

	/** Nom de l'intervenant. */
	private String nomIntervenant;

	/** Prénom de l'intervenant. */
	private String prenomIntervenant;

	/** Nombre d'heures dues. */
	private int services;

	/** Total des heures services et des heures complémentaires autorisées. */
	private int maxHeures;

	/** Total des heures dans les semestres impaires. */
	private int totauxSemestreImpaires;

	/** Total des heures dans les semestres paires. */
	private int totauxSemestrePaire;

	/** Coefficient attitré à l'intervenant en fonction de sa catégorie. */
	private float coefficient;

	/** Liste des heures pour chaque semestre. */
	private List<Integer> listeHeuresSemestre;

	/** Nombre total d'heure */
	private int totalHeures;

	/** List des affectations qu'ils fait */
	private List<Affectations> lstAffectations;


	/*-------------------------------------------------------------*/
	/* CONSTRUCTEURS */
	/*-------------------------------------------------------------*/

	/**
	 * Constructeur prenant tous les paramètres demandées et initialisants les
	 * autres par default.
	 */
	public Intervenants(CategorieIntervenant categorieIntervenant, String nomIntervenant, String prenomIntervenant,
			int services, int maxHeures, float coef) {

		this.categorieIntervenant = categorieIntervenant;
		this.nomIntervenant = nomIntervenant;
		this.prenomIntervenant = prenomIntervenant;
		this.services = services;
		this.maxHeures = maxHeures;

		this.coefficient = coef;
		this.listeHeuresSemestre = new ArrayList<Integer>();
		this.lstAffectations     = new ArrayList<Affectations>();
	}

	/*-------------------------------------------------------------*/
	/* GET-TEURS */
	/*-------------------------------------------------------------*/

	public CategorieIntervenant getCategorieIntervenant() {
		return categorieIntervenant;
	}

	public String getNomIntervenant() {
		return nomIntervenant;
	}

	public String getPrenomIntervenant() {
		return prenomIntervenant;
	}

	public int getServices() {
		return services;
	}

	public int getMaxHeures() {
		return maxHeures;
	}

	public float getCoefficient() {
		return coefficient;
	}

	public int getSommeSemPaire() {
		return (int) ((listeHeuresSemestre.get(0) + listeHeuresSemestre.get(2) + listeHeuresSemestre.get(4))
				* this.coefficient);
	}

	public int getSommeSemImpaire() {
		return (int) ((listeHeuresSemestre.get(1) + listeHeuresSemestre.get(3) + listeHeuresSemestre.get(5))
				* this.coefficient);
	}

	public int getSommeSem() {
		return this.getSommeSemPaire() + this.getSommeSemImpaire();
	}

	public List<Affectations> getLstAffectations() {
		return lstAffectations;
	}

	/*-------------------------------------------------------------*/
	/* SET-TEURS */
	/*-------------------------------------------------------------*/

	public void setCategorieIntervenant(CategorieIntervenant categorieIntervenant) {
		this.categorieIntervenant = categorieIntervenant;
	}

	public void setNomIntervenant(String nomIntervenant) {
		this.nomIntervenant = nomIntervenant;
	}

	public void setPrenomIntervenant(String prenomIntervenant) {
		this.prenomIntervenant = prenomIntervenant;
	}

	public void setServices(int services) {
		this.services = services;
	}

	public void setMaxHeures(int maxHeures) {
		this.maxHeures = maxHeures;
	}

	public void setCoefficient(float coefficient) {
		this.coefficient = coefficient;
	}

	/*-------------------------------------------------------------*/
	/* AUTRES */
	/*-------------------------------------------------------------*/

	@Override
	public String toString() {
		return "Intervenants [categorieIntervenant=" + categorieIntervenant.getLibCatInt() + ", nomIntervenant=" + nomIntervenant
				+ ", prenomIntervenant=" + prenomIntervenant + ", services=" + services + ", maxHeures=" + maxHeures
				+ ", totauxSemestreImpaires=" + totauxSemestreImpaires + ", totauxSemestrePaire=" + totauxSemestrePaire
				+ ", coefficient=" + coefficient + ", listeHeuresSemestre=" + listeHeuresSemestre + "]";
	}

	public void addAffectations (Affectations a) { this.lstAffectations.add(a);}

}
