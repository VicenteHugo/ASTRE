package model;

import java.util.ArrayList;
import java.util.List;

public class Intervenants {

	/*-------------------------------------------------------------*/
	/* ATTRIBUTS */
	/*-------------------------------------------------------------*/

	/** Catégorie de l'intervenant. */
	private CategorieIntervenants categorieIntervenant;

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

	/** Total des heures dans les semestres parires. */
	private int totauxSemestrePaire;

	/** Coefficient attitré à l'intervenant en fonction de sa catégorie. */
	private float coefficient;

	/** Liste des heures pour chaque semestre. */
	private List<Integer> listeHeuresSemestre;

	/** Nombre total d'heure */
	private int totalHeures;

	/*-------------------------------------------------------------*/
	/* CONSTRUCTEURS */
	/*-------------------------------------------------------------*/

	/**
	 * Constructeur prenant tous les paramètres demandées et initialisants les
	 * autres par default.
	 */
	public Intervenants(CategorieIntervenants categorieIntervenant, String nomIntervenant, String prenomIntervenant,
			int services, int maxHeures, float coefficient) {

		this.categorieIntervenant = categorieIntervenant;
		this.nomIntervenant = nomIntervenant;
		this.prenomIntervenant = prenomIntervenant;
		this.services = services;
		this.maxHeures = maxHeures;
		this.coefficient = this.categorieIntervenant.getCoefCatInt();
		this.listeHeuresSemestre = new ArrayList<Integer>();

		this.totauxSemestreImpaires = listeHeuresSemestre.get(1) + listeHeuresSemestre.get(3)
				+ listeHeuresSemestre.get(5);
		this.totauxSemestrePaire = listeHeuresSemestre.get(0) + listeHeuresSemestre.get(2)
				+ listeHeuresSemestre.get(4);
		this.totalHeures = totauxSemestreImpaires + totauxSemestrePaire;
	}

	/*-------------------------------------------------------------*/
	/* GET-TEURS */
	/*-------------------------------------------------------------*/

	public CategorieIntervenants getCategorieIntervenant() {
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

	/*-------------------------------------------------------------*/
	/* SET-TEURS */
	/*-------------------------------------------------------------*/

	public void setCategorieIntervenant(CategorieIntervenants categorieIntervenant) {
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

	public void initListeSemestre() {

	}

	@Override
	public String toString() {
		return "Intervenants [categorieIntervenant=" + categorieIntervenant + ", nomIntervenant=" + nomIntervenant
				+ ", prenomIntervenant=" + prenomIntervenant + ", services=" + services + ", maxHeures=" + maxHeures
				+ ", totauxSemestreImpaires=" + totauxSemestreImpaires + ", totauxSemestrePaire=" + totauxSemestrePaire
				+ ", coefficient=" + coefficient + ", listeHeuresSemestre=" + listeHeuresSemestre + "]";
	}

}
