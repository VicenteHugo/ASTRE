package model.Generer;

import java.io.PrintWriter;


import java.io.FileOutputStream;

public class Generation {

	private String haut;
	private String pied;
	public Generation()
	{
		this.haut  ="<!DOCTYPE HTML>\n";
		this.haut +="<html lang=\"fr\">\n";
		this.haut +="	<head>\n" ;
		this.haut +="		<meta charset=\"utf-8\" />\n" ;
		this.haut +="		<title>ASTRE-*NomPrenom*</title>\n" ;
		this.haut +="		<link href=\"style/style.css\" type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n";
		this.haut +="	</head>\n" ;
		this.haut +="	<body>\n" ;
		this.haut +="		<header>\n" ;
		this.haut +="			<img src=\"../lib/logoAstre-nobg.png\" alt=\"LogoAstre\">\n" ;
		this.haut +="			<h1>ASTRE-*NomPrenom*-*CatÃ©gorie*-*(Etat)*</h1>\n";
		this.haut +="			<hr>\n" ;
		this.haut +="		</header>";
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("./generation/ressource.html") );

			pw.println (this.haut);
            
			

			pw.println ( "		<table>");
  			pw.println ( "			<tr>");
			pw.println ( "				<td>");
			pw.println ( "					<div>");
			pw.println ( "						<h2>Heures</h2>");
			pw.println ( "						<ul>");
			pw.println ( "							<li>Nom :</li>");
			pw.println ( "							<li>Prenom :</li>");
			pw.println ( "							<br>");
			pw.println ( "							<li>Heure Maximum :</li>");
			pw.println ( "							<li>Heure Minimum :</li>");
			pw.println ( "							<li>Heures PrÃ©vues :</li>");
			pw.println ( "						</ul>");
			pw.println ( "					</div>");
			pw.println ( "				</td>");
			
			pw.println ( "				<td>");
			pw.println ( "					<div>");
			pw.println ( "						<h2>Ressources</h2>");
			pw.println ( "						<ul>");
			pw.println ( "						</ul>");
			pw.println ( "					</div>");
			pw.println ( "				</td>");
			pw.println ( "			</tr>");

			pw.println ( "			<tr>");
			pw.println ( "				<td>");
			pw.println ( "					<div>");
			pw.println ( "						<h2>*NomRessourceX*</h2>");
			pw.println ( "						<ul>");
			pw.println ( "							<li>Nb Groupes :</li>");
			pw.println ( "							<li>Nb Semaines :</li>");
			pw.println ( "							<br>");
			pw.println ( "							<li>Heures totales par semaine :");
			pw.println ( "								<ul>" );
			pw.println ( "									<li class=\"ressource\">TD :</li>");
			pw.println ( "									<li class=\"ressource\">REH :</li>");
			pw.println ( "									<br>");
			pw.println ( "									<li class=\"ressource\">TP :</li>");
			pw.println ( "									<li class=\"ressource\">Autre :</li>");
			pw.println ( "									<li>CM :</li>");
			pw.println ( "								</ul>");
			pw.println ( "							</li>");
			pw.println ( "						</ul>");
			pw.println ( "					</div>");
			pw.println ( "				</td>");
			pw.println ( "			</tr>");
			pw.println ( "		</table>");
			pw.println ( "		</hr>");
			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}

	public static void main(String[] args) {
		Generation g = new Generation();
	}
}
