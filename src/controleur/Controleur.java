package controleur;

public class Controleur {
    private static Controleur controleur;

    public static Controleur creerControleur() {
        if(controleur == null)
            controleur = new Controleur();
        
        return controleur;
    }  

    public static Controleur getControleur() {return Controleur.controleur;}


    public static void main(String[] args) {
        Controleur.creerControleur();
    }
}
