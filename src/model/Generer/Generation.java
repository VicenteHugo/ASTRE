package model.Generer;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.text.html.parser.Element;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

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
	private List<Affectations> listeTriee;
	private Set<Module> printedItems = new HashSet<Module>();
	public Generation(Intervenants intervenant)
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
		this.haut +="			<img id=\"logoTitre\" src=\"../lib/logoAstre-nobg.png\" alt=\"LogoAstre\">\n" ;
		this.haut +="			<h1>ASTRE- "+ intervenant.getNomIntervenant() +" "+intervenant.getPrenomIntervenant() +" - "+intervenant.getCategorieIntervenant().getLibCatInt()+"</h1>\n";
		this.haut +="		</header>\n";

		this.pied  ="	</body>\n";
		this.pied +="</html>\n";
		
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("./generation/"+ intervenant.getNomIntervenant() +" "+intervenant.getPrenomIntervenant()+".html") );

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
				if (!this.printedItems.contains(a.getModule())) {
					pw.println ("					<li>"+ a.getModule().getCode()+" "+a.getModule().getLibLong()+"</li>");
					this.printedItems.add(a.getModule());
				}
			}
			pw.println ( "					</ul>");
			pw.println ( "				</div>");
			pw.println ( "			</div>");
			pw.println ( "		</div>");
			pw.println ( "		<div class=\"gridRessource\">");
            pw.println (divModule("Ressource"));
            pw.println (divModule("Sae"));
			pw.println (divModule("Stage"));
			pw.println (divModule("PPP"));
			
			pw.println ( "		</div>\n");
			pw.println (this.pied);
			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}

	public Generation(Module module ,ArrayList<Affectations> listeTriee)
	{
		this.module = module;
		this.listeTriee = listeTriee;
		Set<Intervenants> listeIntervenants =  new HashSet<>();
		for(Affectations a: module.getLstAffectations()){
			if(! listeIntervenants.contains(a.getIntervenant())){
				listeIntervenants.add(a.getIntervenant());
			}
		}
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
			PrintWriter pw = new PrintWriter( new FileOutputStream("./generation/"+ module.getCode() +" "+module.getLibLong() +".html") );

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
            		pw.println (divHeure(cat.getlibCatHeur()));
			}
			
			pw.println ( "		</div>\n");
			pw.println (this.pied);
			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}
	/** Gestion des valeurs de l'intervenant */

	public String divModule(String typeMod){
		String  divMod     = "";
		String  codeActuel = "";
		String  catHeure   = "";
		ArrayList<Affectations> affec;
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
		System.out.println(typeMod);
		this.printedItems.clear();
		System.out.println("passageDeb");
		for (Affectations a : this.listeTriee){
			if (!this.printedItems.contains(a.getModule())) {
				//System.out.println(a.getModule().getCode());	
				printedItems.add(a.getModule());
				for (String libCat : map.keySet()) {
					affec = map.get(libCat);
					Collections.sort(affec);
					for (Affectations catA : affec) {
						if(catA.getModule().getClass().getSimpleName().equals(typeMod))
						{
							modulePresent = true;
							System.out.println(catA.getModule().getCode());
							if (!a.getModule().getCode().equals(codeActuel)){
								if(premPassage)
								{
									divMod+="					</ul>\n";
									divMod+="				</div>\n";
									divMod+="			</div>\n";
								}
								cpt = 1;
								premPassage = true;
								catHeure = null;
								codeActuel= a.getModule().getCode();
								divMod+="			<div>\n";
								divMod+="				<h2>"+ a.getModule().getCode()+" "+a.getModule().getLibLong()+"</h2>\n";
								divMod+="				<div class=\"barreBleue\">\n";
								divMod+="					<ul>\n";
							}

							if (a.getModule().getCode().equals(codeActuel)){
								if (!a.getCategorieHeures().getlibCatHeur().equals(catHeure)) {
									cpt = 1;
									divMod+="						<li class=\"typeHeure\">"+a.getCategorieHeures().getlibCatHeur()+":\n";
									catHeure  = a.getCategorieHeures().getlibCatHeur();
								}
								divMod+="								<ul>\n";
								divMod+="									<li>Affectation "+cpt+" :\n";
								divMod+="										<ul>\n";
								divMod+="											<li>Nb Heures&nbsp;&nbsp;: "+a.getNbHeure()+"</li>\n";
								divMod+="											<li>Nb Semaine : "+a.getNbSemaine()+"</li>\n";
								divMod+="										</ul>\n";
								divMod+="									</li>\n";
								divMod+="								</ul>\n";
								cpt++;
							}
						}
					}
				}
			}
		}
		System.out.println("passageFin");
		if(modulePresent){
			divMod+="					</ul>\n";
			divMod+="				</div>\n";
			divMod+="			</div>\n";
		}
		return divMod;
	}
	public String divHeure(String typeHeure){
		String  divHeure  = "";
		String  hActuelle = "";
		ArrayList<Affectations> affec;
		Boolean typeH = false;
		HashMap<CategorieHeures, ArrayList<Affectations>> map = new HashMap<CategorieHeures, ArrayList<Affectations>>();
		/**if(map!=null){
			for (CategorieHeures affectations : map) {
				if(affectations.getClass() == this.module &&  affectations.getCategorieHeures().getlibCatHeur().equals(typeHeure))
				{
					typeH = true;
					if (!affectations.getCategorieHeures().getlibCatHeur().equals(hActuelle)){
						hActuelle= affectations.getCategorieHeures().getlibCatHeur();
						divHeure+="			<div>\n";
						divHeure+="				<h2>"+typeHeure+"</h2>\n";
						divHeure+="				<div class=\"barreBleue\">\n";
						divHeure+="					<ul>\n";
					}
					
					divHeure+="						<li class=\"typeHeure\">"+affectations.getIntervenant().getNomIntervenant()+" "+affectations.getIntervenant().getPrenomIntervenant()+":\n";
					divHeure+="							<ul>\n";
					divHeure+="								<li>Nb de groupes&nbsp;&nbsp;: "+affectations.getNbGroupe()+"</li>\n";
					divHeure+="								<li>Nb de semaine&nbsp;&nbsp;: "+affectations.getNbSemaine()+"</li>\n";
					divHeure+="								<li>Heures Totales: "+affectations.getNbGroupe()*affectations.getNbSemaine()+"</li>\n";
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
		}**/
		return divHeure;
	}
	
	
	public static ArrayList<Affectations> triageInter(){
		new Etat();
		ArrayList<Affectations> listeNonTriee = Etat.getAffectations();
		ArrayList<Affectations> listeTriee = new ArrayList<Affectations>();
		//R
		for (Affectations a : listeNonTriee) {
			if(a.getModule().getClass().getSimpleName().equals("Ressource")&& a.getModule().getCode().length() == 5 ){
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
				if(ajout == false){listeTriee.add(a);}
			}
		}
		
		//SAE
		for (Affectations a : listeNonTriee) {
			if(a.getModule().getClass().getSimpleName().equals("Sae") && a.getModule().getCode().length() == 5 ){
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
				if(ajout == false){listeTriee.add(a);}
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
				if(ajout == false){listeTriee.add(a);}
			}
		}
		return listeTriee;
	}
	
	
	public static void generationIntervenants(){
		new Etat();
		Etat.changerEtat("fz");

		Generation g = new Generation(Etat.getIntervenants().get(0));
		int cpt = 0;
		//for (Intervenants i : Etat.getIntervenants()) {
		//	System.out.println(cpt + Etat.getIntervenants().get(cpt).getNomIntervenant());
		//	cpt++;
			//Generation g = new Generation(i);
		//}
	}
	public static void generationModules(){
		Etat.changerEtat("fz");
		ArrayList<Affectations> listeTriee = Etat.getAffectations();
		Collections.sort(listeTriee);
		HashMap <Module, ArrayList<Affectations>> hashMap = new HashMap <Module, ArrayList<Affectations>>();

		for (Affectations a : listeTriee) {
			if(! hashMap.containsKey(a.getModule()))
			{
				hashMap.put(a.getModule(),new ArrayList < Affectations >());
			}
			hashMap.get(a.getModule()).add(a);
		}
		for (Module i : Etat.getModules()) {
			//Generation g = new Generation(i,hashMap,listeTriee);
		}
	}
	public static void main(String[] args) {
		Generation.generationIntervenants();
		//Generation.generationModules();
	}
}