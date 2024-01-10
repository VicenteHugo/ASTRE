package model.Generer;

import java.io.PrintWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.HashMap;
import java.util.Set;
import java.awt.image.*;
import javax.imageio.ImageIO;

import model.Affectations;
import model.CategorieHeures;
import model.Etat;
import model.Intervenants;
import model.modules.Module;


public class Generation {

	private String haut;
	private String pied;
	private Intervenants intervenant;
	private Module module;
	private List<Affectations> listeTriee;
	private Set<String> printedItems = new HashSet<String>();
	public Generation(Intervenants intervenant, String chemin)
	{
		this.intervenant = intervenant;
		this.listeTriee = new ArrayList<Affectations>();
		
		this.printedItems.clear();
		this.haut  ="<!DOCTYPE HTML>\n";
		this.haut +="<html lang=\"fr\">\n";
		this.haut +="	<head>\n" ;
		this.haut +="		<meta charset=\"utf-8\" />\n" ;
		this.haut +="		<title>ASTRE-"+ intervenant.getNomIntervenant()+" "+intervenant.getPrenomIntervenant() +"</title>\n" ;
		this.haut +="		<link href=\"style/style.css\" type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n";
		this.haut +="	</head>\n" ;
		this.haut +="	<body>\n" ;
		this.haut +="		<header>\n" ;
		this.haut +="			<img id=\"logoTitre\" src=\"image/logoAstre.png\" alt=\"LogoAstre\">\n" ;
		this.haut +="			<h1>ASTRE- "+ intervenant.getNomIntervenant() +" "+intervenant.getPrenomIntervenant() +" - "+intervenant.getCategorieIntervenant().getLibCatInt()+"</h1>\n";
		this.haut +="		</header>\n";

		this.pied  ="	</body>\n";
		this.pied +="</html>\n";
		
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream(chemin+ intervenant.getNomIntervenant() +" "+intervenant.getPrenomIntervenant()+".html") );

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
			this.listeTriee = this.intervenant.getLstAffectations();
			Collections.sort(this.listeTriee);
			for (Affectations a : this.listeTriee) {
				if (!this.printedItems.contains(a.getModule().getCode())) {
					pw.println ("					<li>"+ a.getModule().getCode()+" "+a.getModule().getLibLong()+"</li>");
					this.printedItems.add(a.getModule().getCode());
				}
			}
			pw.println ( "					</ul>");
			pw.println ( "				</div>");
			pw.println ( "			</div>");
			pw.println ( "		</div>");
			pw.println ( "		<div class=\"gridRessource\">");
            pw.println (divModule());			
			pw.println ( "		</div>\n");
			pw.println (this.pied);
			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}

	public Generation(Module module, String chemin)
	{
		this.module = module;
		Set<Intervenants> listeIntervenants =  new HashSet<>();
		for(Affectations a: module.getLstAffectations()){
			if(! listeIntervenants.contains(a.getIntervenant())){
				listeIntervenants.add(a.getIntervenant());
			}
		}
		this.printedItems.clear();
		this.haut  ="<!DOCTYPE HTML>\n";
		this.haut +="<html lang=\"fr\">\n";
		this.haut +="	<head>\n" ;
		this.haut +="		<meta charset=\"utf-8\" />\n" ;
		this.haut +="		<title>ASTRE-"+ module.getCode()+" "+module.getLibLong() +"</title>\n" ;
		this.haut +="		<link href=\"style/style.css\" type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n";
		this.haut +="	</head>\n" ;
		this.haut +="	<body>\n" ;
		this.haut +="		<header>\n" ;
		this.haut +="			<img id=\"logoTitre\" src=\"image/logoAstre.png\" alt=\"LogoAstre\">\n" ;
		this.haut +="			<h1>ASTRE- "+ module.getCode() +" "+module.getLibLong() +"</h1>\n";
		this.haut +="		</header>\n";
		this.pied  ="	</body>\n";
		this.pied +="</html>\n";
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream(chemin+ module.getCode()+module.getLibCourt() +".html") );

			pw.print (this.haut);
			pw.println ( "		<div class=\"premiereLigne\">\n");
			pw.println ( "			<div>");
			pw.println ( "				<h2>ANNÉE TOTALE</h2>");
			pw.println ( "				<div class=\"barreBleue\">");
			pw.println ( "					<ul>");
			pw.println ( "						<li>Module&nbsp;&nbsp;&nbsp;&nbsp;: "+module.getCode()+" "+module.getLibLong()+"</li>");
			pw.println ( "						<li>Heures totales(TD) : "+module.getHeureTotal()+"</li>");
			pw.println ( "						<li>Nombres d'intervenants : "+listeIntervenants.size() + "</li>");
			pw.println ( "						<br>");
			pw.println ( "					</ul>");
			pw.println ( "				</div>");
			pw.println ( "			</div>");
			pw.println ( "			<div>");
			pw.println ( "				<h2>COEFFICIENT HEURES</h2>");
			pw.println ( "				<div class=\"barreBleue\">");
			pw.println ( "					<ul>");
			this.listeTriee = this.module.getLstAffectations();
			Collections.sort(this.listeTriee);
			for (CategorieHeures cat : module.getListCategorieHeure()) {
				{
            		pw.println ("					<li>"+ cat.getlibCatHeur()+": "+cat.getcoefCatHeur()+"</li>");
				}
			}
			pw.println ( "					</ul>");
			pw.println ( "				</div>");
			pw.println ( "			</div>");
			pw.println ( "		</div>");
			pw.println ( "		<div class=\"gridRessource\">");
            pw.println (divHeure());
			pw.println ( "		</div>\n");
			pw.println (this.pied);
			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}
	/** Gestion des valeurs de l'intervenant */

	public String divModule(){
		String  divMod     = "";
		String  codeActuel = "";
		String  catHeure   = "";
		ArrayList<Affectations> affec;
		int     size = 0;
		int     totalH = 0;
		Boolean premPassage   = false;
		Boolean modulePresent = false;
		int cpt=0;
		HashMap<String, ArrayList<Affectations>> map = new HashMap<String, ArrayList<Affectations>>();
		for (Affectations a : this.intervenant.getLstAffectations()) {
			if(! map.containsKey(a.getCategorieHeures().getlibCatHeur()))
			{
				map.put(a.getCategorieHeures().getlibCatHeur(),new ArrayList < Affectations >());
			}
			map.get(a.getCategorieHeures().getlibCatHeur()).add(a);
		}
		this.printedItems.clear();
		for (Affectations a : this.listeTriee){
			if (!this.printedItems.contains(a.getModule().getCode())) {
				printedItems.add(a.getModule().getCode());
				if (!a.getModule().getCode().equals(codeActuel)){
					if(premPassage)
					{
						divMod+="					</ul>\n";
						divMod+="				</div>\n";
						divMod+="			</div>\n";
					}
					cpt = 1;
					premPassage = true;
					codeActuel= a.getModule().getCode();
					divMod+="			<div>\n";
					divMod+="				<h2>"+ a.getModule().getCode()+" "+a.getModule().getLibLong()+"</h2>\n";
					divMod+="				<div class=\"barreBleue\">\n";
					divMod+="					<ul>\n";
				}
				for (String key : map.keySet()) {
					affec = map.get(key);
					Collections.sort(affec);
					catHeure = "";
					size = 0;
					for (Affectations catA2 : affec) {
						if(catA2.getModule().equals(a.getModule())){size++;}
						}
					for (Affectations catA : affec) {
						modulePresent = true;
						if (catA.getModule().equals(a.getModule())){
							if (!catA.getCategorieHeures().getlibCatHeur().equals(catHeure)) {
								cpt = 0;
								totalH = 0;
								divMod+="						<li class=\"typeHeure\">"+catA.getCategorieHeures().getlibCatHeur()+":\n";
								catHeure  = catA.getCategorieHeures().getlibCatHeur();
							}
							cpt++;
							divMod+="								<ul>\n";
							divMod+="									<li>Affectation "+cpt+" :\n";
							divMod+="										<ul>\n";
							divMod+="											<li>Nb Heures/Semaine : "+catA.getNbHeure()+"</li>\n";
							divMod+="											<li>Nb Semaine&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: "+catA.getNbSemaine()+"</li>\n";
							divMod+="										</ul>\n";
							divMod+="									</li>\n";
							totalH+= catA.getNbHeure()*catA.getNbSemaine();

							if (cpt == size) {
								divMod+="									<li>Total des heures : "+totalH+"</li>\n";
								divMod+="								</ul>\n";
							}
							else{divMod+="								</ul>\n";}
						}
					}
				}
			}
		}
		if(modulePresent){
			divMod+="					</ul>\n";
			divMod+="				</div>\n";
			divMod+="			</div>\n";
		}
		return divMod;
	}
	public String divHeure(){
		String  divHeure  = "";
		String  typeHActuel = "";
		Intervenants profActuel = null;
		ArrayList<Affectations> affec;
		Boolean modulePresent = false;
		Boolean premPassage   = false;
		HashMap<Intervenants, ArrayList<Affectations>> map = new HashMap<Intervenants, ArrayList<Affectations>>();
		int cpt=0;
		for (Affectations a : this.module.getLstAffectations()) {
			if(! map.containsKey(a.getIntervenant()))
			{
				map.put(a.getIntervenant(),new ArrayList < Affectations >());
			}
			map.get(a.getIntervenant()).add(a);
		}
		this.printedItems.clear();
		for (Affectations a : this.listeTriee){
			if (!this.printedItems.contains(a.getCategorieHeures().getlibCatHeur())) {
				printedItems.add(a.getCategorieHeures().getlibCatHeur());
				if (!a.getCategorieHeures().getlibCatHeur().equals(typeHActuel)){
					if(premPassage)
					{
						divHeure+="					</ul>\n";
						divHeure+="				</div>\n";
						divHeure+="			</div>\n";
					}
					premPassage = true;
					typeHActuel= a.getCategorieHeures().getlibCatHeur();
					divHeure+="			<div>\n";
					divHeure+="				<h2>"+a.getCategorieHeures().getlibCatHeur()+"</h2>\n";
					divHeure+="				<div class=\"barreBleue\">\n";
					divHeure+="					<ul>\n";
				}
				for (Intervenants key : map.keySet()) {
					affec = map.get(key);
					Collections.sort(affec);
					profActuel = null;
					for (Affectations profA : affec) {
						modulePresent = true;
						if (profA.getCategorieHeures().equals(a.getCategorieHeures())){
							if (!profA.getIntervenant().equals(profActuel)) {
								cpt = 1;
								divHeure+="						<li class=\"typeHeure\">"+profA.getIntervenant().getNomIntervenant()+" "+profA.getIntervenant().getPrenomIntervenant()+":\n";
								profActuel  = profA.getIntervenant();
							}
							divHeure+="								<ul>\n";
							divHeure+="									<li>Affectation "+cpt+" :\n";
							divHeure+="										<ul>\n";
							divHeure+="											<li>Nb de groupes&nbsp;&nbsp;: "+profA.getNbGroupe()+"</li>\n";
							divHeure+="											<li>Nb de semaine&nbsp;&nbsp;: "+profA.getNbSemaine()+"</li>\n";
							divHeure+="											<li>Heures Totales: "+profA.getNbGroupe()*profA.getNbSemaine()+"</li>\n";
							divHeure+="										</ul>\n";
							divHeure+="									</li>\n";
							divHeure+="								</ul>\n";
							cpt++;
						}
					}
				}
			}
		}
		if(modulePresent){
					divHeure+="					</ul>\n";
					divHeure+="				</div>\n";
					divHeure+="			</div>\n";
				}
		return divHeure;
	}

	public static void genererStyle(String chemin){		
		new File(chemin+"style").mkdir();
		new File(chemin+"image").mkdir();
		System.out.println(chemin);
		try {
			BufferedImage image = ImageIO.read(new File("./lib/logoAstre.png"));
			ImageIO.write(image, "png", new File(chemin+"image/logoAstre.png"));		
		} catch (IOException e) {e.printStackTrace();}
		String s =  "*{\n"+
					"	margin: 0;\n"+
					"	padding: 0;\n"+
					"  	box-sizing: border-box;\n"+
				    "}\n"+
					"header {\n"+
					"	background-color: darkgrey;\n"+
					"	padding: 0%;\n"+
					"	text-align: left;\n"+
					"	color: whitesmoke;\n"+
					"	display: flex;\n"+
					"	flex-direction: row;\n"+
					"}\n"+
					"#logoTitre{\n"+
					"	width: 150px;\n"+
					"	height: auto;\n"+
					"	margin: 10px;\n"+
					"}\n"+
					"h1{\n"+
					"	padding-top: 50px;\n"+
					"	padding-bottom: 50px;\n"+
					"	margin: 10px;\n"+
					"	flex: auto;\n"+
					"}\n"+

					"body {\n"+
					"	font-family: 'Open Sans', sans-serif;\n"+
					"	background-color: whitesmoke;\n"+
					"}\n"+

					"div{\n"+
					"	font-family: 'Courier New', Courier, monospace;\n"+
					"}\n"+

					".premiereLigne{\n"+
					"	display: flex; \n"+
					"	flex-wrap: wrap;\n"+
					"	gap: 50px;\n"+
					"	padding: 20px;\n"+
					"	margin-bottom:50px;\n"+
					"}\n"+
					".gridRessource{\n"+
					"	margin-left: 20px;\n"+
					"	display: grid;\n"+
					"	grid-template-columns: repeat( auto-fit, minmax(470px, 470px) );\n"+
					"	grid-gap: 40px;\n"+
					"}\n"+
					".icecube{\n"+
					"	border: 1px solid #0097B2;\n"+
					"}\n"+
					".barreBleue{\n"+
					"	padding-left: 10px;\n"+
					"	margin-left: 20px;\n"+
					"	border-left: 3px solid #0097B2;\n"+
					"}\n"+
					"/**\n"+
					"*titre de la partie\n"+
					"*/\n"+
					"h2 {\n"+
					"	font-size: 20px;\n"+
					"	color: #0097B2;\n"+
					"}\n"+

					".module{\n"+
					"	font-size: 15px;\n"+
					"}\n"+


					"ul {\n"+
					"	list-style-type: none;\n"+
					"	padding: 0;\n"+
					"	margin: 0;\n"+
					"}\n"+

					"ul ul,\n"+
					"ul ol,\n"+
					"ol ul,\n"+
					"ol ol {\n"+
					"	margin-left: 20px;\n"+
					"}\n"+

					".ressource {\n"+
					"	display: inline-block;\n"+
					"	margin-right: 10px;\n"+
					"	width: 100px; /* Remplacez 100px par la largeur souhaitée */\n"+
					"	overflow: hidden; /* Pour s'assurer que le contenu ne dépasse pas la largeur spécifiée */\n"+
					"	white-space: nowrap; /* Empêche le texte de passer à la ligne */\n"+
					"}\n"+
						
					"/**\n"+
					"*liste des informations\n"+
					"*/\n"+

					"li {\n"+
					"	color: black;\n"+
					"	font-size: 15px;\n"+
					"	font-weight: normal;\n"+
					"}\n"+

					".typeHeure{\n"+
					"	font-weight: bold;\n"+
					"}\n"+

					"/**\n"+
					"*Placer un point ou autre avant la liste (compensation li sur la même ligne)\n"+
					"*/\n"+
					"li::before {\n"+
					"	content: \"'\"2022'; /**unicode**/\n"+
					"	font-size: 1.2em;\n"+
					"	margin-right: 5px;\n"+
					"}\n"+
					"/**\n"+
					"*information générer dans le li \n"+
					"*/\n"+
					".info {\n"+
					"	color: black;\n"+
					"	font-weight: normal;\n"+
					"	font-size: 12px;\n"+
					"}";
		
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream(chemin+"style/style.css") );
			pw.print(s);
			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}
	
	public static void generationIntervenants(String chemin){
		if(Etat.getIntervenants() != null && Etat.getIntervenants().size() > 0){
			Generation.genererStyle(chemin);
			for (Intervenants i : Etat.getIntervenants()) {
				new Generation(i,chemin);
			}
		}
	}
	public static void generationModules(String chemin){
		if(Etat.getModules() != null && Etat.getModules().size() > 0){
			Generation.genererStyle(chemin);
			for (Module m : Etat.getModules()) {
				new Generation(m,chemin);
			}
		}
	}
}