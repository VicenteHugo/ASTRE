package model.Generer;

import java.io.PrintWriter;

import javax.swing.text.html.HTML;

import model.CategorieIntervenant;
import model.Intervenants;

import java.io.FileOutputStream;

public class Generation {

	private String haut;
	private String pied;
	public Generation(Intervenants intervenant)
	{
		this.haut  ="<!DOCTYPE HTML>\n";
		this.haut +="<html lang=\"fr\">\n";
		this.haut +="	<head>\n" ;
		this.haut +="		<meta charset=\"utf-8\" />\n" ;
		this.haut +="		<title>ASTRE-"+ intervenant.getNomIntervenant()+" "+intervenant.getPrenomIntervenant() +"</title>\n" ;
		this.haut +="		<link href=\"style/style.css\" type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n";
		this.haut +="	</head>\n" ;
		this.haut +="	<body>\n" ;
		this.haut +="		<header>\n" ;
		this.haut +="			<img id=\"logoTitre\" src=\"../lib/logoAstre-nobg.png\" alt=\"LogoAstre\">\n" ;
		this.haut +="			<h1>ASTRE- "+ intervenant.getNomIntervenant() +" "+intervenant.getPrenomIntervenant() +" - "+intervenant.getCategorieIntervenant().getLibCatInt()+" - *(Année)*</h1>\n";
		this.haut +="			<hr>\n" ;
		this.haut +="		</header>";
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("./generation/ressource.html") );

			pw.print (this.haut);
            
			

			pw.println ( "		<table>");
  			pw.println ( "			<tr>");
			pw.println ( "				<td>");
			pw.println ( "					<div>");
			pw.println ( "						<h2>Heures</h2>");
			pw.println ( "						<ul>");
			pw.println ( "							<li>Nom : "+intervenant.getNomIntervenant()+"</li>");
			pw.println ( "							<li>Prenom : "+intervenant.getPrenomIntervenant()+"</li>");
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
		CategorieIntervenant cat = new CategorieIntervenant("Vac", "Vacataire", 0, 0, 0);
		Intervenants intervenant = new Intervenants(cat,"Boucher" , "Teddy", 0, 0, 0);
		Generation g = new Generation(intervenant);
	}
}
