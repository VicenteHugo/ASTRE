package model.Generer;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import model.Affectations;
import model.CategorieHeures;
import model.CategorieIntervenant;
import model.Intervenants;
import model.Semestres;
import model.modules.Module;
import model.modules.Ressource;

import java.io.FileOutputStream;

public class Generation {

	private String haut;
	private String pied;
	Set<String> printedItems = new HashSet<>();
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
			pw.println ( "		<div>");
			pw.println ( "			<h2>Heures</h2>");
			pw.println ( "			<ul>");
			pw.println ( "					<li>Nom : "+intervenant.getNomIntervenant()+"</li>");
			pw.println ( "					<li>Prenom : "+intervenant.getPrenomIntervenant()+"</li>");
			pw.println ( "				<br>");
			pw.println ( "				<li>Heure Maximum : "+intervenant.getMaxHeures()+"</li>");
			pw.println ( "				<li>Heure Minimum : "+intervenant.getServices()+"</li>");
			pw.println ( "				<li>Heures Prévues : "+intervenant.getServices()+"</li>");
			pw.println ( "			</ul>");
			pw.println ( "		</div>");
			pw.println ( "		<div>");
			pw.println ( "			<h2>Ressources</h2>");
			pw.println ( "			<ul>");
			for (Affectations affec : intervenant.getLstAffectations()) {
				if (!printedItems.contains(affec.getModule().getLibLong())) {
                pw.println ( "					<li>"+ affec.getModule().getCode()+" "+affec.getModule().getLibLong()+"</li>");
                printedItems.add(affec.getModule().getLibLong());
			}
			}
			pw.println ( "			</ul>");
			pw.println ( "		</div>");

			
			pw.println ( "		</hr>");
			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}

	public Generation(Module module)
	{
		this.haut  ="<!DOCTYPE HTML>\n";
		this.haut +="<html lang=\"fr\">\n";
		this.haut +="	<head>\n" ;
		this.haut +="		<meta charset=\"utf-8\" />\n" ;
		this.haut +="		<title>ASTRE-"+ module.getCode()+" "+ module.getLibCourt() +"</title>\n" ;
		this.haut +="		<link href=\"style/style.css\" type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n";
		this.haut +="	</head>\n" ;
		this.haut +="	<body>\n" ;
		this.haut +="		<header>\n" ;
		this.haut +="			<img id=\"logoTitre\" src=\"../lib/logoAstre-nobg.png\" alt=\"LogoAstre\">\n" ;
		this.haut +="			<h1>ASTRE- "+ module.getCode() +" "+ module.getLibCourt() +" - "+ module.getSemestres()+" - *(Année)*</h1>\n";
		this.haut +="			<hr>\n" ;
		this.haut +="		</header>";
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("./generation/ressource.html") );

			pw.print (this.haut);
			pw.println ( "		<div>");
			pw.println ( "			<h2>Heures</h2>");
			pw.println ( "			<ul>");
			pw.println ( "					<li>Code : "+module.getCode()+"</li>");
			pw.println ( "					<li>Libellé : "+module.getLibLong()+"</li>");
			pw.println ( "				<br>");
			pw.println ( "				<li>Heures Pn : "+module.getHeurePn()+"</li>");
			pw.println ( "				<li>Heures Ponctuel : "+module.getHeurePonctuel()+"</li>");
			pw.println ( "				<li>Heures Prévues : "+module.getHeureAffecte()+"</li>");
			pw.println ( "				<li>Heures Total : "+module.getHeureTotal()+"</li>");
			pw.println ( "			</ul>");
			pw.println ( "		</div>");
			pw.println ( "		<div>");
			pw.println ( "			<h2>Intervenants</h2>");
			pw.println ( "			<ul>");
			for (Affectations affec : module.getLstAffectations()) {
				if (!printedItems.contains(affec.getIntervenant().getNomIntervenant())) {
					pw.println ( "					<li>"+ affec.getIntervenant().getNomIntervenant()+" "+affec.getIntervenant().getNomIntervenant()+"</li>");
					printedItems.add(affec.getIntervenant().getNomIntervenant());
				}
			}
			pw.println ( "			</ul>");
			pw.println ( "		</div>");

			
			pw.println ( "		</hr>");
			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}
	/** Gestion des valeurs de l'intervenant */

	public String divRessource(Intervenants intervenant){
		String divRess = "";
		for (Affectations affec2 : intervenant.getLstAffectations()) {
			if (!printedItems.contains(affec2.getModule().getLibLong())) {
				divRess+="	<div>\n";
				divRess+="			<h2>"+ affec2.getModule().getCode()+" "+affec2.getModule().getLibLong()+"</h2>\n";
				printedItems.add(affec2.getModule().getLibLong());
				divRess+="		<ul>\n";
				divRess+="			<li>Nb Groupes :</li>\n";
				divRess+="			<li>Nb Semaines :</li>\n";
				divRess+="			<br>\n";
				divRess+="			<li>Heures totales par semaine :\n";
				divRess+="				<ul>\n";
				divRess+="					<li class=\"ressource\">TD :</li>\n";
				divRess+="					<li class=\"ressource\">REH :</li>\n";
				divRess+="					<br>\n";
				divRess+="					<li class=\"ressource\">TP :</li>\n";
				divRess+="					<li class=\"ressource\">Autre :</li>\n";
				divRess+="					<li>CM :</li>\n";
				divRess+="				</ul>\n";
				divRess+="			</li>\n";
				divRess+="		</ul>\n";
				divRess+="	</div>\n";
			}
		}
		return divRess;
	}

	public String divSae(Intervenants intervenant){
		String divSae = "";
		for (Affectations affec2 : intervenant.getLstAffectations()) {
			if (!printedItems.contains(affec2.getModule().getLibLong())) {
				divSae+="	<div>\n";
				divSae+="			<h2>"+ affec2.getModule().getCode()+" "+affec2.getModule().getLibLong()+"</h2>\n";
				printedItems.add(affec2.getModule().getLibLong());
				divSae+="		<ul>\n";
				divSae+="			<li>Nb Groupes :</li>\n";
				divSae+="			<li>Nb Semaines :</li>\n";
				divSae+="			<br>\n";
				divSae+="			<li>Heures totales par semaine :\n";
				divSae+="				<ul>\n";
				divSae+="					<li class=\"ressource\">TD :</li>\n";
				divSae+="					<li class=\"ressource\">REH :</li>\n";
				divSae+="					<br>\n";
				divSae+="					<li class=\"ressource\">TP :</li>\n";
				divSae+="					<li class=\"ressource\">Autre :</li>\n";
				divSae+="					<li>CM :</li>\n";
				divSae+="				</ul>\n";
				divSae+="			</li>\n";
				divSae+="		</ul>\n";
				divSae+="	</div>\n";
			}
		}
		return divSae;
	}
	
	public String divStage(Intervenants intervenant){
		String divStage = "";
		for (Affectations affec2 : intervenant.getLstAffectations()) {
			if (!printedItems.contains(affec2.getModule().getLibLong())) {
				divStage+="	<div>\n";
				divStage+="			<h2>"+ affec2.getModule().getCode()+" "+affec2.getModule().getLibLong()+"</h2>\n";
				printedItems.add(affec2.getModule().getLibLong());
				divStage+="		<ul>\n";
				divStage+="			<li>Nb Groupes :</li>\n";
				divStage+="			<li>Nb Semaines :</li>\n";
				divStage+="			<br>\n";
				divStage+="			<li>Heures totales par semaine :\n";
				divStage+="				<ul>\n";
				divStage+="					<li class=\"ressource\">TD :</li>\n";
				divStage+="					<li class=\"ressource\">REH :</li>\n";
				divStage+="					<br>\n";
				divStage+="					<li class=\"ressource\">TP :</li>\n";
				divStage+="					<li class=\"ressource\">Autre :</li>\n";
				divStage+="					<li>CM :</li>\n";
				divStage+="				</ul>\n";
				divStage+="			</li>\n";
				divStage+="		</ul>\n";
				divStage+="	</div>\n";
			}
		}
		return divStage;
	}

	public static void main(String[] args) {
		CategorieIntervenant catP    =new CategorieIntervenant("Prof"  ,"Professeurs"             ,2,600,1024);
		CategorieIntervenant catPc   =new CategorieIntervenant("Cher"  ,"Professeurs-Chercheur"   ,4,300,600 );
		CategorieIntervenant catV    =new CategorieIntervenant("Vac"   ,"Vacataire"               ,4,50 ,100 );
		CategorieIntervenant catCont =new CategorieIntervenant("Cont"  ,"Contractuel"             ,4,30 ,80  );
		CategorieIntervenant catA    =new CategorieIntervenant("A"     ,"Autre"                   ,4,1  ,20  );
		
		
		CategorieHeures catCM      = new CategorieHeures("TD", 1);
		CategorieHeures catTD      = new CategorieHeures("TD", 1);
		CategorieHeures catTP      = new CategorieHeures("TD", 1);
		Intervenants    tBoucher = new Intervenants(catV,"Boucher" , "Teddy", 60 , 70 , 5);
		Semestres semestre1 = new Semestres(1, 3, 3, 80, 14);

		Ressource ressource1 = new Ressource(semestre1, "R1.01", "Developpement", "Dev", 40, false);
		Ressource ressource2 = new Ressource(semestre1, "R2.01", "Developpement2", "Dev2", 40, false);
		Affectations a,b,c,d;
		a = new Affectations(tBoucher, ressource1, catCM, 20, 4,null);
		b = new Affectations(tBoucher, ressource1, catTD, 20, 4,null);
		System.out.println("bobob");
		System.out.println(tBoucher.getLstAffectations());
		for (Affectations affec : tBoucher.getLstAffectations()) {
			System.out.println("bob");
			System.out.println(affec.getModule().getCode()+affec.getModule().getLibLong());
		}
		Generation g = new Generation(tBoucher);
	}
}
