package model.Generer;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;

import javax.swing.text.html.parser.Element;

import controleur.Controleur;
import model.Affectations;
import model.CategorieHeures;
import model.CategorieIntervenant;
import model.Etat;
import model.Intervenants;
import model.Semestres;
import model.modules.Module;
import model.modules.Ressource;

import java.io.FileOutputStream;

public class Generation {

	private String haut;
	private String pied;
	private Intervenants intervenant;
	HashMap<Module, ArrayList<Affectations>> hashMapModAffec;
	Set<String> printedItems = new HashSet<>();
	public Generation(Intervenants intervenant,HashMap<Module, ArrayList<Affectations>> map,int cptIntervenant)
	{
		this.intervenant = intervenant;
		this.hashMapModAffec = map;
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
		this.haut +="			<h1>ASTRE- "+ intervenant.getNomIntervenant() +" "+intervenant.getPrenomIntervenant() +" - "+intervenant.getCategorieIntervenant().getLibCatInt()+"</h1>\n";
		this.haut +="		</header>\n";

		this.pied  ="	</body>\n";
		this.pied +="</html>\n";
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("./generation/ressource"+cptIntervenant+".html") );

			pw.print (this.haut);
			pw.println ( "		<div class=\"premiereLigne\">\n");
			pw.println ( "			<div>");
			pw.println ( "				<h2>Heures</h2>");
			pw.println ( "				<div class=\"barreBleue\">");
			pw.println ( "					<ul>");
			pw.println ( "						<li>Nom : "+intervenant.getNomIntervenant()+"</li>");
			pw.println ( "						<li>Prenom : "+intervenant.getPrenomIntervenant()+"</li>");
			pw.println ( "						<br>");
			pw.println ( "						<li>Heure Maximum : "+intervenant.getMaxHeures()+"</li>");
			pw.println ( "						<li>Heure Minimum : "+intervenant.getServices()+"</li>");
			pw.println ( "						<li>Heures Prévues : "+3+"</li>");
			pw.println ( "					</ul>");
			pw.println ( "				</div>");
			pw.println ( "			</div>");
			pw.println ( "			<div>");
			pw.println ( "				<h2>Ressources</h2>");
			pw.println ( "				<div class=\"barreBleue\">");
			pw.println ( "					<ul>");
			for (Affectations affec : intervenant.getLstAffectations()) {
				if (!printedItems.contains(affec.getModule().getLibLong())) {
                	pw.println ("					<li>"+ affec.getModule().getCode()+" "+affec.getModule().getLibLong()+"</li>");
            		printedItems.add(affec.getModule().getLibLong());
				}
			}
			pw.println ( "					</ul>");
			pw.println ( "				</div>");
			pw.println ( "			</div>");
			pw.println ( "		</div>");
			pw.println ( "		<div class=\"gridRessource\">");
			pw.println (divRessource(map));
			pw.println ( "		</div>\n");
			pw.println (this.pied);
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
		this.haut +="			<h1>ASTRE- "+ module.getCode() +" "+ module.getLibCourt() +" - Semestre "+ module.getSemestres().getNumSem()+"</h1>\n";
		this.haut +="			<hr>\n" ;
		this.haut +="		</header>";
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("./generation/ressource.html") );

			pw.print (this.haut);
			pw.println ( "		<div>");
			pw.println ( "			<h2>Année Total</h2>");
			pw.println ( "			<ul>");
			pw.println ( "					<li>Module 			   : "+module.getCode()+ " " + module.getLibLong()+ "</li>");
			//pw.println ( "					<li>Total Heure : "+module.getHeureTotal()+"</li>");
			pw.println ( "					<li>Nombre Intervenants: "+module.getLstAffectations().size()+"</li>");
			pw.println ( "				<br>");
			pw.println ( "			</ul>");
			pw.println ( "		</div>");
			pw.println ( "		<div>");
			pw.println ( "			<h2>Intervenants</h2>");
			pw.println ( "			<ul>");
			for (Affectations affec : module.getLstAffectations()) {
				if (!printedItems.contains(affec.getIntervenant().getNomIntervenant() + "" + affec.getIntervenant().getPrenomIntervenant())) {
					pw.println ( "					<li>"+ affec.getIntervenant().getNomIntervenant()+" "+affec.getIntervenant().getPrenomIntervenant()+ " / Nombre d'heure : "+ affec.getIntervenant().getServices() +"H</li>");
					printedItems.add(affec.getIntervenant().getNomIntervenant() + "" + affec.getIntervenant().getPrenomIntervenant());
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

	public String divRessource(HashMap<Module, ArrayList<Affectations>> map){
		String  divRess = "";
		String  codeActuel = "";
		Boolean premPassage = false;
		CategorieHeures catHeure = null;
		int cpt = 1;
		for (Module key : map.keySet()) {
			for (Affectations affec : map.get(key)) {
				if(affec.getModule() instanceof Ressource && affec.getIntervenant() == this.intervenant )
				{
					if (!affec.getModule().getCode().equals(codeActuel)){
						if(premPassage)
						{
							divRess+="					</ul>\n";
							divRess+="				</div>\n";
							divRess+="			</div>\n";
						}
						cpt = 1;
						premPassage = true;
						codeActuel= affec.getModule().getCode();
						divRess+="			<div>\n";
						divRess+="				<h2>"+ affec.getModule().getCode()+" "+affec.getModule().getLibLong()+"</h2>\n";
						divRess+="				<div class=\"barreBleue\">\n";
						divRess+="					<ul>\n";
					}

					if (affec.getModule().getCode().equals(codeActuel)){
						if (affec.getCategorieHeures()!=catHeure) {
							cpt = 1;
							divRess+="						<li class=\"\">"+affec.getCategorieHeures().getlibCatHeur()+"</li>\n";
							catHeure  = affec.getCategorieHeures();
						}
						divRess+="						<li>Affectation "+cpt+" :\n";
						divRess+="							<ul>\n";
						divRess+="								<li>Nb Heures : "+affec.getNbHeure()+"</li>\n";
						divRess+="								<li>Nb Semaine : "+affec.getNbSemaine()+"</li>\n";
						divRess+="							</ul>\n";
						divRess+="						</li>\n";
						cpt++;
					}
				}
			}
		}
		divRess+="					</ul>\n";
		divRess+="				</div>\n";
		divRess+="			</div>\n";
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
		Etat e = new Etat();
		HashMap <Module, ArrayList<Affectations>> hashMap = new HashMap <Module, ArrayList<Affectations>>();
		for (Affectations a : Etat.getAffectations()) {
			if(! hashMap.containsKey(a.getModule()))
			{
				hashMap.put(a.getModule(),new ArrayList < Affectations >());
			}
			hashMap.get(a.getModule()).add(a);
		}
		int cpt = 0;
		for (Intervenants i : Etat.getIntervenants()) {
			Generation g = new Generation(i,hashMap,cpt);
			cpt++;
		}
	}
}
