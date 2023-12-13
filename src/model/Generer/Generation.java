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
			PrintWriter pw = new PrintWriter( new FileOutputStream("test.html") );

            pw.println ( "<!DOCTYPE HTML>\n");
            pw.println ( "<html lang=\"fr\">\n" );
			pw.println ( "<head>\n" );
			pw.println ( "<meta charset=\"utf-8\" />\n" );
            pw.println ( "<title>ASTRE-*NomPrenom*</title>\n" );
            pw.println ( "<link href=\"site.css\" type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n" );
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
			pw.println ( "<ul>\n" );
			pw.println ( "<li>Niveau 1\n" );
			pw.println ( "<ul>\n" );
			pw.println ( "<li>Niveau 2</li>\n" );
			pw.println ( "<li>Niveau 2</li>\n" );
			pw.println ( "</ul>\n" );
			pw.println ( "</li>\n" );
			pw.println ( "<li>Niveau 1</li>\n" );
			pw.println ( "</ul>\n" );
			pw.println ( "</div>\n" );
			
			pw.println ( "<dt>Heure total par semaine :</dt>\n" );
			pw.println ( "<dd>TD</dd>\n" );
			pw.println ( "<dd>TP</dd>\n" );
			pw.println ( "<dd>CM</dd>\n" );
			pw.println ( "<dd>REH</dd>\n" );
			pw.println ( "<dd>Autre</dd>\n" );

			pw.println ( "<ol type=\"A\">\n" );
			pw.println ( "<li value = \"2\">grand B</li>\n" );
			pw.println ( "<ol type=\"a\">\n" );
			pw.println ( "<li value = \"1\">petit a</li>\n" );
			pw.println ( "<li value = \"2\">petit b</li>\n" );


			pw.println ( "<li value = \"3\">petit c</li>\n" );
			pw.println ( "</ol>\n" );

			pw.println ( "<li value = \"3\">grand C</li>\n" );
			pw.println ( "<li value = \"4\">grand D</li>\n" );
			pw.println ( "</ol>\n" );

			pw.println ( "<hr>\n" );

			pw.println ( "<ul>\n" );
			pw.println ( "<li>Niveau 1\n" );
			pw.println ( "<ul>\n" );
			pw.println ( "<li>Niveau 2</li>\n" );
			pw.println ( "<li>Niveau 2</li>\n" );
			pw.println ( "</ul>\n" );
			pw.println ( "</li>\n" );
			pw.println ( "<li>Niveau 1</li>\n" );
			pw.println ( "</ul>\n" );
		    pw.println ( "</body>\n" );
            pw.println ( "</html>\n" );



			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}
}
