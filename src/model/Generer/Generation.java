package model.Generer;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

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
import model.modules.Sae;
import model.modules.Stage;

import java.io.FileOutputStream;

public class Generation {

	private String haut;
	private String pied;
	private Intervenants intervenant;
	private Module module;
	private ArrayList<Affectations> listeTriee;
	HashMap<Module, ArrayList<Affectations>> hashMapModAffec;
	Set<String> printedItems = new HashSet<>();
	public Generation(Intervenants intervenant,HashMap<Module, ArrayList<Affectations>> map,ArrayList<Affectations> listeTriee,int cptIntervenant)
	{
		this.intervenant = intervenant;
		this.hashMapModAffec = map;
		this.listeTriee = listeTriee;
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
			PrintWriter pw = new PrintWriter( new FileOutputStream("./generation/Intervenant"+cptIntervenant+".html") );

			pw.print (this.haut);
			pw.println ( "		<div class=\"premiereLigne\">\n");
			pw.println ( "			<div>");
			pw.println ( "				<h2>Intervenant</h2>");
			pw.println ( "				<div class=\"barreBleue\">");
			pw.println ( "					<ul>");
			pw.println ( "						<li>Nom&nbsp;&nbsp;&nbsp;&nbsp;: "+intervenant.getNomIntervenant()+"</li>");
			pw.println ( "						<li>Prenom : "+intervenant.getPrenomIntervenant()+"</li>");
			pw.println ( "						<br>");

			pw.println ( "						<li>Heure Maximum : "+intervenant.getMaxHeures()+"</li>");
			pw.println ( "						<li>Heure Minimum : "+intervenant.getServices()+"</li>");
			pw.println ( "						<li>Heures Prévues : "+intervenant.getSommeSem()+"</li>");
			pw.println ( "					</ul>");
			pw.println ( "				</div>");
			pw.println ( "			</div>");
			pw.println ( "			<div>");
			pw.println ( "				<h2>Modules</h2>");
			pw.println ( "				<div class=\"barreBleue\">");
			pw.println ( "					<ul>");
			for (Affectations list : this.listeTriee) {
				ArrayList<Affectations> listeAffec= map.get(list.getModule());
				for (Affectations affectations : listeAffec) {
					if(affectations.getIntervenant() == this.intervenant ){
						if (!printedItems.contains(affectations.getModule().getLibLong())) {
            		    	pw.println ("					<li>"+ affectations.getModule().getCode()+" "+affectations.getModule().getLibLong()+"</li>");
            				printedItems.add(affectations.getModule().getLibLong());
						}
					}
				}
			}
			pw.println ( "					</ul>");
			pw.println ( "				</div>");
			pw.println ( "			</div>");
			pw.println ( "		</div>");
			pw.println ( "		<div class=\"gridRessource\">");
			pw.println (divRessource(hashMapModAffec));
			pw.println (divSae(hashMapModAffec));
			pw.println (divStage(hashMapModAffec));
			pw.println (divPPP(hashMapModAffec));
			pw.println ( "		</div>\n");
			pw.println (this.pied);
			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}

	public Generation(Module module ,HashMap<Module, ArrayList<Affectations>> map,ArrayList<Affectations> listeTriee,int cptModule)
	{
		this.module = module;
		this.hashMapModAffec = map;
		this.listeTriee = listeTriee;
		this.haut  ="<!DOCTYPE HTML>\n";
		this.haut +="<html lang=\"fr\">\n";
		this.haut +="	<head>\n" ;
		this.haut +="		<meta charset=\"utf-8\" />\n" ;
		this.haut +="		<title>ASTRE-"+ module.getCode()+" "+module.getLibLong() +"</title>\n" ;
		this.haut +="		<link href=\"style/style.css\" type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n";
		this.haut +="	</head>\n" ;
		this.haut +="	<body>\n" ;
		this.haut +="		<header>\n" ;
		this.haut +="			<img id=\"logoTitre\" src=\"../lib/logoAstre-nobg.png\" alt=\"LogoAstre\">\n" ;
		this.haut +="			<h1>ASTRE- "+ module.getCode() +" "+module.getLibLong() +"</h1>\n";
		this.haut +="		</header>\n";

		this.pied  ="	</body>\n";
		this.pied +="</html>\n";
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("./generation/module"+cptModule+".html") );

			pw.print (this.haut);
			pw.println ( "		<div class=\"premiereLigne\">\n");
			pw.println ( "			<div>");
			pw.println ( "				<h2>ANNÉE TOTALE</h2>");
			pw.println ( "				<div class=\"barreBleue\">");
			pw.println ( "					<ul>");
			pw.println ( "						<li>Module&nbsp;&nbsp;&nbsp;&nbsp;: "+module.getCode()+" "+module.getLibLong()+"</li>");
			pw.println ( "						<li>Heures totales(TD) : "+module.getHeureTotal()+"</li>");
			pw.println ( "						<li>Nombres d'intervenants(TD) : "+"</li>");
			pw.println ( "						<br>");
			pw.println ( "					</ul>");
			pw.println ( "				</div>");
			pw.println ( "			</div>");
			pw.println ( "			<div>");
			pw.println ( "				<h2>COEFFICIENT HEURES</h2>");
			pw.println ( "				<div class=\"barreBleue\">");
			pw.println ( "					<ul>");
			for (CategorieHeures cat : module.getListCategorieHeure()) {
				{
            		pw.println ("					<li>"+ cat.getlibCatHeur()+": "+module.getHeures().get(cat)+"</li>");
				}
			}
			pw.println ( "					</ul>");
			pw.println ( "				</div>");
			pw.println ( "			</div>");
			pw.println ( "		</div>");
			pw.println ( "		<div class=\"gridRessource\">");
			for (CategorieHeures cat : module.getListCategorieHeure()) {
				{
            		pw.println (divHeure(hashMapModAffec,cat.getlibCatHeur()));
				}
			}
			
			pw.println ( "		</div>\n");
			pw.println (this.pied);
			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}
	/** Gestion des valeurs de l'intervenant */

	public String divRessource(HashMap<Module, ArrayList<Affectations>> map){
		String  divRess = "";
		String  codeActuel = "";
		ArrayList<Affectations> affec;
		Boolean premPassage = false;
		Boolean modulePresent = false;
		CategorieHeures catHeure = null;
		int cpt = 1;
		for (Affectations list : this.listeTriee) {
			affec = map.get(list.getModule());
			for (Affectations affectations : affec) {
				if(affectations.getModule() instanceof Ressource && affectations.getIntervenant() == this.intervenant )
				{
					modulePresent = true;
					if (!affectations.getModule().getCode().equals(codeActuel)){
						if(premPassage)
						{
							divRess+="					</ul>\n";
							divRess+="				</div>\n";
							divRess+="			</div>\n";
						}
						cpt = 1;
						premPassage = true;
						catHeure = null;
						codeActuel= affectations.getModule().getCode();
						divRess+="			<div>\n";
						divRess+="				<h2>"+ affectations.getModule().getCode()+" "+affectations.getModule().getLibLong()+"</h2>\n";
						divRess+="				<div class=\"barreBleue\">\n";
						divRess+="					<ul>\n";
					}

					if (affectations.getModule().getCode().equals(codeActuel)){
						if (affectations.getCategorieHeures()!=catHeure) {
							cpt = 1;
							divRess+="						<li class=\"typeHeure\">"+affectations.getCategorieHeures().getlibCatHeur()+": </li>\n";
							catHeure  = affectations.getCategorieHeures();
						}
						divRess+="						<li>Affectation "+cpt+" :\n";
						divRess+="							<ul>\n";
						divRess+="								<li>Nb Heures&nbsp;&nbsp;: "+affectations.getNbHeure()+"</li>\n";
						divRess+="								<li>Nb Semaine : "+affectations.getNbSemaine()+"</li>\n";
						divRess+="							</ul>\n";
						divRess+="						</li>\n";
						cpt++;
					}
				}
			}
		}
		if(modulePresent){
			divRess+="					</ul>\n";
			divRess+="				</div>\n";
			divRess+="			</div>\n";
		}
		return divRess;
	}

	public String divSae(HashMap<Module, ArrayList<Affectations>> map){
		String  divSae = "";
		String  codeActuel = "";
		ArrayList<Affectations> affec;
		Boolean premPassage = false;
		Boolean modulePresent = false;
		CategorieHeures catHeure = null;
		int cpt = 1;
		for (Affectations list : this.listeTriee) {
			affec = map.get(list.getModule());
			for (Affectations affectations : affec) {
				if(affectations.getModule() instanceof Sae && affectations.getIntervenant() == this.intervenant )
				{
					modulePresent = true;
					if (!affectations.getModule().getCode().equals(codeActuel)){
						if(premPassage)
						{
							divSae+="					</ul>\n";
							divSae+="				</div>\n";
							divSae+="			</div>\n";
						}
						cpt = 1;
						premPassage = true;
						catHeure = null;
						codeActuel= affectations.getModule().getCode();
						divSae+="			<div>\n";
						divSae+="				<h2>"+ affectations.getModule().getCode()+" "+affectations.getModule().getLibLong()+"</h2>\n";
						divSae+="				<div class=\"barreBleue\">\n";
						divSae+="					<ul>\n";
					}

					if (affectations.getModule().getCode().equals(codeActuel)){
						if (affectations.getCategorieHeures()!=catHeure) {
							cpt = 1;
							divSae+="						<li class=\"typeHeure\">"+affectations.getCategorieHeures().getlibCatHeur()+": </li>\n";
							catHeure  = affectations.getCategorieHeures();
						}
						divSae+="						<li>Affectation "+cpt+" :\n";
						divSae+="							<ul>\n";
						divSae+="								<li>Nb Heures&nbsp;&nbsp;: "+affectations.getNbHeure()+"</li>\n";
						divSae+="								<li>Nb Semaine : "+affectations.getNbSemaine()+"</li>\n";
						divSae+="							</ul>\n";
						divSae+="						</li>\n";
						cpt++;
					}
				}
			}
		}
		if(modulePresent){
			divSae+="					</ul>\n";
			divSae+="				</div>\n";
			divSae+="			</div>\n";
		}
		return divSae;
	}
	
	public String divStage(HashMap<Module, ArrayList<Affectations>> map){
		String  divStage = "";
		String  codeActuel = "";
		ArrayList<Affectations> affec;
		Boolean premPassage = false;
		Boolean modulePresent = false;
		CategorieHeures catHeure = null;
		int cpt = 1;
		for (Affectations list : this.listeTriee) {
			affec = map.get(list.getModule());
			for (Affectations affectations : affec) {
				if(affectations.getModule() instanceof Stage && affectations.getIntervenant() == this.intervenant )
				{
					modulePresent = true;
					if (!affectations.getModule().getCode().equals(codeActuel)){
						if(premPassage)
						{
							divStage+="					</ul>\n";
							divStage+="				</div>\n";
							divStage+="			</div>\n";
						}
						cpt = 1;
						premPassage = true;
						catHeure = null;
						codeActuel= affectations.getModule().getCode();
						divStage+="			<div>\n";
						divStage+="				<h2>"+ affectations.getModule().getCode()+" "+affectations.getModule().getLibLong()+"</h2>\n";
						divStage+="				<div class=\"barreBleue\">\n";
						divStage+="					<ul>\n";
					}

					if (affectations.getModule().getCode().equals(codeActuel)){
						if (affectations.getCategorieHeures()!=catHeure) {
							cpt = 1;
							divStage+="						<li class=\"typeHeure\">"+affectations.getCategorieHeures().getlibCatHeur()+": </li>\n";
							catHeure  = affectations.getCategorieHeures();
						}
						divStage+="						<li>Affectation "+cpt+" :\n";
						divStage+="							<ul>\n";
						divStage+="								<li>Nb Heures&nbsp;&nbsp;: "+affectations.getNbHeure()+"</li>\n";
						divStage+="								<li>Nb Semaine : "+affectations.getNbSemaine()+"</li>\n";
						divStage+="							</ul>\n";
						divStage+="						</li>\n";
						cpt++;
					}
				}
			}
		}
		if(modulePresent){
			divStage+="					</ul>\n";
			divStage+="				</div>\n";
			divStage+="			</div>\n";
		}
		return divStage;
	}

	public String divPPP(HashMap<Module, ArrayList<Affectations>> map){
		String  divPPP = "";
		String  codeActuel = "";
		ArrayList<Affectations> affec;
		Boolean premPassage = false;
		Boolean modulePresent = false;
		CategorieHeures catHeure = null;
		int cpt = 1;
		for (Affectations list : this.listeTriee) {
			affec = map.get(list.getModule());
			for (Affectations affectations : affec) {
				if(affectations.getModule() instanceof Stage && affectations.getIntervenant() == this.intervenant )
				{
					modulePresent = true;
					if (!affectations.getModule().getCode().equals(codeActuel)){
						if(premPassage)
						{
							divPPP+="					</ul>\n";
							divPPP+="				</div>\n";
							divPPP+="			</div>\n";
						}
						cpt = 1;
						premPassage = true;
						catHeure = null;
						codeActuel= affectations.getModule().getCode();
						divPPP+="			<div>\n";
						divPPP+="				<h2>"+ affectations.getModule().getCode()+" "+affectations.getModule().getLibLong()+"</h2>\n";
						divPPP+="				<div class=\"barreBleue\">\n";
						divPPP+="					<ul>\n";
					}

					if (affectations.getModule().getCode().equals(codeActuel)){
						if (affectations.getCategorieHeures()!=catHeure) {
							cpt = 1;
							divPPP+="						<li class=\"typeHeure\">"+affectations.getCategorieHeures().getlibCatHeur()+": </li>\n";
							catHeure  = affectations.getCategorieHeures();
						}
						divPPP+="						<li>Affectation "+cpt+" :\n";
						divPPP+="							<ul>\n";
						divPPP+="								<li>Nb Heures&nbsp;&nbsp;: "+affectations.getNbHeure()+"</li>\n";
						divPPP+="								<li>Nb Semaine : "+affectations.getNbSemaine()+"</li>\n";
						divPPP+="							</ul>\n";
						divPPP+="						</li>\n";
						cpt++;
					}
				}
			}
		}
		if(modulePresent){
			divPPP+="					</ul>\n";
			divPPP+="				</div>\n";
			divPPP+="			</div>\n";
		}
		return divPPP;
	}
	
	public String divHeure(HashMap<Module, ArrayList<Affectations>> map,String typeHeure){
		String  divHeure = "";
		String  hActuelle = "";
		ArrayList<Affectations> affec;
		Boolean typeH = false;
		affec = map.get(this.module);
		if(affec!=null){
			for (Affectations affectations : affec) {
				if(affectations.getModule() == this.module &&  affectations.getCategorieHeures().getlibCatHeur().equals(typeHeure))
				{
					typeH = true;
					if (!affectations.getCategorieHeures().getlibCatHeur().equals(hActuelle)){
						hActuelle= affectations.getCategorieHeures().getlibCatHeur();
						divHeure+="			<div>\n";
						divHeure+="				<h2>TD</h2>\n";
						divHeure+="				<div class=\"barreBleue\">\n";
						divHeure+="					<ul>\n";
					}
					
					divHeure+="						<li class=\"typeHeure\">"+affectations.getIntervenant().getNomIntervenant()+" "+affectations.getIntervenant().getPrenomIntervenant()+":\n";
					divHeure+="							<ul>\n";
					divHeure+="								<li>Nb de groupes&nbsp;&nbsp;: "+affectations.getNbGroupe()+"</li>\n";
					divHeure+="								<li>Nb de semaine&nbsp;&nbsp;: "+affectations.getNbSemaine()+"</li>\n";
					divHeure+="								<li>Heures Groupe/semaine: "+3+"</li>\n";
					divHeure+="							</ul>\n";
					divHeure+="						</li>\n";
					divHeure+="						<br>\n";
				}
			}
			if(typeH){

				divHeure+="					</ul>\n";
				divHeure+="				</div>\n";
				divHeure+="			</div>\n";
			}
		}
		return divHeure;
	}
	
	
	public static ArrayList<Affectations> triageInter(){
		new Etat();
		ArrayList<Affectations> listeNonTriee = Etat.getAffectations();
		ArrayList<Affectations> listeTriee = new ArrayList<Affectations>();
		//R
		for (Affectations a : listeNonTriee) {
			if(a.getModule().getCode().substring(0,1).equals("R")){
				boolean ajout = false;
				for (int i = 0; i<listeTriee.size();i++) 
				{
					if(ajout==false){
						if(listeTriee.get(i).getModule().getCode().substring(0,1).equals("R") && a.getModule().getCode().compareTo(listeTriee.get(i).getModule().getCode())<=0){
							listeTriee.add(listeTriee.indexOf(listeTriee.get(i)),a);
							ajout = true;
						}
					}
				}
				if(ajout == false){listeTriee.addLast(a);}
			}
		}
		
		//SAE
		for (Affectations a : listeNonTriee) {
			if(a.getModule().getCode().substring(0,1).equals("S")){
				if(! a.getModule().getCode().substring(3,5).equals("ST")){
					boolean ajout = false;
					for (int i = 0; i<listeTriee.size();i++) 
					{
						if(ajout==false){
						if(listeTriee.get(i).getModule().getCode().substring(0,1).equals("S") 
							&&	! listeTriee.get(i).getModule().getCode().substring(3,5).equals("ST")
							&&    a.getModule().getCode().compareTo(listeTriee.get(i).getModule().getCode())<=0){
							listeTriee.add(listeTriee.indexOf(listeTriee.get(i)),a);
							ajout = true;
						}
					}
					}
					if(ajout == false){listeTriee.add(a);}
				}
			}
		}

		//Stage	
		for (Affectations a : listeNonTriee) {
			if(a.getModule().getCode().substring(3,5).equals("ST")){
				boolean ajout = false;
				for (int i = 0; i<listeTriee.size();i++) 
				{
					if(ajout==false){
						if(listeTriee.get(i).getModule().getCode().substring(3,5).equals("ST") 
							&& a.getModule().getCode().compareTo(listeTriee.get(i).getModule().getCode())<=0){
							listeTriee.add(listeTriee.indexOf(listeTriee.get(i)),a);
							ajout = true;
						}
					}
				}
				if(ajout == false){listeTriee.addLast(a);}
			}
		}
		//PPP
		for (Affectations a : listeNonTriee) {
			
			if(a.getModule().getCode().substring(0,1).equals("P")){
				boolean ajout = false;
				for (int i = 0; i<listeTriee.size();i++) 
				{
					if(ajout==false){
						if(listeTriee.get(i).getModule().getCode().substring(0,1).equals("P") 
							&& a.getModule().getCode().compareTo(listeTriee.get(i).getModule().getCode())<=0){
							listeTriee.add(listeTriee.indexOf(listeTriee.get(i)),a);
							ajout = true;
						}
					}
				}
				if(ajout == false){listeTriee.addLast(a);}
			}
		}
		return listeTriee;
	}
	public static void generationIntervenants(){
		ArrayList<Affectations> listeTriee = Generation.triageInter();
		HashMap <Module, ArrayList<Affectations>> hashMap = new HashMap <Module, ArrayList<Affectations>>();

		for (Affectations a : listeTriee) {
			if(! hashMap.containsKey(a.getModule()))
			{
				hashMap.put(a.getModule(),new ArrayList < Affectations >());
			}
			hashMap.get(a.getModule()).add(a);
		}
		int cpt = 0;
		for (Intervenants i : Etat.getIntervenants()) {
			Generation g = new Generation(i,hashMap,listeTriee,cpt);
			cpt++;
		}
	}
	public static void generationModules(){
		ArrayList<Affectations> listeTriee = Generation.triageInter();
		HashMap <Module, ArrayList<Affectations>> hashMap = new HashMap <Module, ArrayList<Affectations>>();

		for (Affectations a : listeTriee) {
			if(! hashMap.containsKey(a.getModule()))
			{
				hashMap.put(a.getModule(),new ArrayList < Affectations >());
			}
			hashMap.get(a.getModule()).add(a);
		}
		int cpt = 0;
		for (Module i : Etat.getModules()) {
			Generation g = new Generation(i,hashMap,listeTriee,cpt);
			cpt++;
		}
	}
	public static void main(String[] args) {
		//Generation.generationIntervenants();
		Generation.generationModules();
		//for (Affectations a : Etat.getAffectations()) {
		//	Generation g = new Generation(a.getModule());
		//}
	}
}
