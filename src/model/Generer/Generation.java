package model.Generer;

import java.io.PrintWriter;

import javax.swing.text.html.HTML;

import java.io.FileOutputStream;

public class Generation {

	private String haut;
	private String pied;
	public Generation()
	{
		this.haut  ="<!DOCTYPE HTML>\n";
		this.haut +="<html lang=\"fr\">\n";
		this.haut +="<head>\n" ;
		this.haut +="<meta charset=\"utf-8\" />\n" ;
		this.haut +="<title>ASTRE-*NomPrenom*</title>\n" ;
		this.haut +="<link href=\"style/style.css\" type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n";
		this.haut +="</head>\n" ;
		this.haut +="<body>\n" ;
		this.haut +="<header>\n" ;
		this.haut +="<h1>ASTRE-*NomPrenom*-*CatÃ©gorie*-*(Etat)*</h1>";
		this.haut +="</header>\n";
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("./generation/test.html") );

            pw.println ( "<!DOCTYPE HTML>\n");
            pw.println ( "<html lang=\"fr\">\n" );
			pw.println ( "<head>\n" );
			pw.println ( "<meta charset=\"utf-8\" />\n" );
            pw.println ( "<title>ASTRE-*NomPrenom*</title>\n" );
            pw.println ( "<link href=\"style/syle.css\" type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n" );
            pw.println ( "<style>\n" );
    		pw.println ( "ul {\n" );
      		pw.println ( "list-style-type: none;\n" );
     		pw.println ( "padding: 0;\n" );
    		pw.println ( "margin: 0;}\n" );

    		pw.println ( ".test {\n" );
      		pw.println ( "display: inline-block;\n" );
      		pw.println ( "margin-right: 10px;}\n" );
  			pw.println ( "</style>\n" );

			pw.println ( "</head>\n" );
            pw.println ( "<body>\n" );
			pw.println ( "<header>\n" );
			pw.println ( "<h1>ASTRE-*NomPrenom*-*CatÃ©gorie*-*(Etat)*</h1>");
			pw.println ( "</header>\n" );
            
			
			
			
			pw.println ( "<hr>\n" );

			pw.println ( "<div>\n" );
			pw.println ( "<h2>Heures</h2>\n" );
			pw.println ( "<ul type=\"square\">\n" );
			pw.println ( "<li>Nom :</li>\n" );
			pw.println ( "<li>Prenom :</li>\n" );
			pw.println ( "<br>\n");
			pw.println ( "<li>Heure Maximum :</li>\n" );
			pw.println ( "<li>Heure Minimum :</li>\n" );
			pw.println ( "<li>Heures PrÃ©vues :</li>\n" );
			pw.println ( "</ul>\n" );
			pw.println ( "</div>\n" );

			pw.println ( "<div>\n" );
			pw.println ( "<h2>Ressources</h2>\n" );
			pw.println ( "<ul type=\"square\">\n" );
			pw.println ( "</ul>\n" );
			pw.println ( "</div>\n" );

			pw.println ( "<div>\n" );
			pw.println ( "<h2>*NomRessourceX*</h2>\n" );
			pw.println ( "<ul>\n" );
			pw.println ( "<li>Nb Groupes :</li>\n" );
			pw.println ( "<li>Nb Semaines :</li>\n" );
			pw.println ( "<br>\n");
			pw.println ( "<li>Heures totales par semaine :\n" );
			pw.println ( "<ul>\n" );
			pw.println ( "<li class=\"test\">TD</li>\n" );
			pw.println ( "<li class=\"test\">TP</li>\n" );
			pw.println ( "<br>\n" );
			pw.println ( "<liclass=\"test\">CM</li>\n" );
			pw.println ( "<li class=\"test\">REH</li>\n" );
			pw.println ( "<li>Autre</li>\n" );
			pw.println ( "</ul>\n" );
			pw.println ( "</li>\n" );
			pw.println ( "</ul>\n" );
			pw.println ( "</div>\n" );
			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}

	public static void main(String[] args) {
		Generation g = new Generation();
	}
}
