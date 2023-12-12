package model;

public class IntervenantModuleCategorieHeure {
    /**nom de l'intervenant */
    private String nomIntervenant;

    /**prenom de l'intervenant */
    private String prenomIntervenant;

    /**code du module */
    private String codeModule;

    /**libelle categorie d'heure */
    private String libelleCategorieHeure;

    /**nombre d'heure semaine */
    private int nbHeureSemaine;

    /**nombre de semaines */
    private int nbSemaine;

    /**commentaire */
    private String commentaire;

    /**Constructeur
     */
    public IntervenantModuleCategorieHeure(String nomIntervenant, String prenomIntervenant, String codeModule, String libelleCategorieHeure, int nbHeureSemaine, int nbSemaine, String commentaire) {
        this.nomIntervenant        = nomIntervenant;
        this.prenomIntervenant     = prenomIntervenant;
        this.codeModule            = codeModule;
        this.libelleCategorieHeure = libelleCategorieHeure;
        this.nbHeureSemaine        = nbHeureSemaine;
        this.nbSemaine             = nbSemaine;
        this.commentaire           = commentaire;
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
