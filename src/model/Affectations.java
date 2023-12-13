package model;

import model.modules.Module;

public class Affectations {
    /**Intervenant */
    private Intervenants intervenant;

    /**Module */
    private Module module;

    /**Categorie d'heure */
    private CategorieHeures categorieHeures;

    /**nombre d'heure semaine */
    private int nbSemaine;

    /**nombre d'heure semaine */
    private int nbGroupe;

    /**commentaire */
    private String commentaire;

    /**Constructeur
     */
    public Affectations(Intervenants inter, Module mode, CategorieHeures categorie, int nbSemaine, int nbGroupe, String commentaire) {
        this.intervenant = inter;
        this.module = mode;
        this.categorieHeures = categorie;
        this.nbSemaine = nbSemaine;
        this.commentaire = commentaire;
        this.nbGroupe = nbGroupe;
    }

    public Intervenants getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(Intervenants intervenant) {
        this.intervenant = intervenant;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
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



    
}
