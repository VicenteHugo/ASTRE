package model;

import model.modules.Module;

public class Affectations {
	/** Intervenant */
	private Intervenants intervenant;

	/** Module */
	private Module module;

	/** Categorie d'heure */
	private CategorieHeures categorieHeures;

	/** nombre d'heure semaine */
	private int nbSemaine;

	/** nombre d'heure semaine */
	private int nbGroupe;

	/** commentaire */
	private String commentaire;

	private int nbHeure;

	/**
	 * Constructeur
	 */
	public Affectations(Intervenants inter, Module mode, CategorieHeures categorie, int nbSemaine, int nbGroupe,
			String commentaire) {
		this.intervenant = inter;
		this.module = mode;
		this.categorieHeures = categorie;
		this.nbSemaine = nbSemaine;
		this.commentaire = commentaire;
		this.nbGroupe = nbGroupe;


		mode.addAffectations(this);
		inter.addAffectations(this);
	}

	public Affectations(Intervenants inter, Module mode, CategorieHeures categorie, int nbHeure, String commentaire) {
		this.intervenant = inter;
		this.module = mode;
		this.categorieHeures = categorie;
		this.nbHeure = nbHeure;
		this.commentaire = commentaire;
		this.nbGroupe = 1;
		this.nbSemaine = 0;	
	}

	public Intervenants getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(Intervenants intervenant) {
		this.intervenant = intervenant;
		intervenant.addAffectations(this);
}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;		
		module.addAffectations(this);
	}

	public CategorieHeures getCategorieHeures() {
		return categorieHeures;
	}

	public void setCategorieHeures(CategorieHeures categorieHeures) {
		this.categorieHeures = categorieHeures;
	}

	public int getNbSemaine() {
		return nbSemaine;
	}

	public void setNbSemaine(int nbSemaine) {
		this.nbSemaine = nbSemaine;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	@Override
	public String toString() {
		return "Affectations [intervenant=" + intervenant + ", module=" + module + ", categorieHeures="
				+ categorieHeures + ", nbSemaine=" + nbSemaine + ", nbGroupe=" + nbGroupe + ", commentaire="
				+ commentaire + "]";
	}

	public int getNbGroupe() {
		return nbGroupe;
	}

	public void setNbGroupe(int nbGroupe) {
		this.nbGroupe = nbGroupe;
	}

	public int getNbHeure() {
		return nbHeure;
	}

	public void setNbHeure(int nbHeure) {
		this.nbHeure = nbHeure;
	}

	public void delete() {
		this.module.delAffectations(this);
	}

	public int getHeureEqtd() {
		int nbH = this.module.getHeures().get(this.categorieHeures).get(2);

		int heure = (int) Math.ceil((this.nbHeure +  nbH * this.nbGroupe * this.nbSemaine) * this.categorieHeures.getcoefCatHeur());

		return heure;
	}

}
