package model.Generer;

import java.io.PrintWriter;

import javax.swing.text.html.HTML;

import java.io.FileOutputStream;

public class Generation {

	private String haut;
	private String pied;
	public static void main(String[] a)
	{
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("./generation/test.html") );

            pw.println ( "<!DOCTYPE HTML>\n");
            pw.println ( "<html lang=\"fr\">\n" );
			pw.println ( "<head>\n" );
			pw.println ( "<meta charset=\"utf-8\" />\n" );
            pw.println ( "<title>ASTRE-*NomPrenom*</title>\n" );
            pw.println ( "<link href=\"style/style.css\" type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n" );
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
			pw.println ( "<li>TD</li>\n" );
			pw.println ( "<li>TP</li>\n" );
			pw.println ( "<li>CM</li>\n" );
			pw.println ( "<li>REH</li>\n" );
			pw.println ( "<li>Autre</li>\n" );
			pw.println ( "</ul>\n" );
			pw.println ( "</li>\n" );
			pw.println ( "</ul>\n" );
			pw.println ( "</div>\n" );
			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}
}
