package model;

public class Affectations {
    /**Intervenant */
    private Intervenants intervenant;

    /**Module */
    private Module module;

    /**Categorie d'heure */
    private CategorieHeures categorieHeures;

    /**nombre d'heure semaine */
    private int nbSemaine;

    /**commentaire */
    private String commentaire;

    /**Constructeur
     */
    public Affectations(Intervenants inter, Module module, CategorieHeures categorie,int nbSemaine, String commentaire) {
        this.intervenant     = inter;
        this.module          = module;
        this.categorieHeures = categorie;
        this.nbSemaine       = nbSemaine;
        this.commentaire     = commentaire;
    }

    public String getNomIntervenant() {
        return this.nomIntervenant;
    }

    public void setNomIntervenant(String nomIntervenant) {
        this.nomIntervenant = nomIntervenant;
    }

    public String getPrenomIntervenant() {
        return this.prenomIntervenant;
    }

    public void setPrenomIntervenant(String prenomIntervenant) {
        this.prenomIntervenant = prenomIntervenant;
    }

    public String getCodeModule() {
        return this.codeModule;
    }

    public void setCodeModule(String codeModule) {
        this.codeModule = codeModule;
    }

    public String getLibelleCategorieHeure() {
        return this.libelleCategorieHeure;
    }

    public void setLibelleCategorieHeure(String libelleCategorieHeure) {
        this.libelleCategorieHeure = libelleCategorieHeure;
    }

    public int getNbHeureSemaine() {
        return this.nbHeureSemaine;
    }

    public void setNbHeureSemaine(int nbHeureSemaine) {
        this.nbHeureSemaine = nbHeureSemaine;
    }

    public int getNbSemaine() {
        return this.nbSemaine;
    }

    public void setNbSemaine(int nbSemaine) {
        this.nbSemaine = nbSemaine;
    }

    public String getCommentaire() {
        return this.commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    
}
