package model.Generer;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import model.Affectations;
import model.CategorieHeures;
import model.Intervenants;
import model.modules.Module;
import model.modules.Ressource;

import java.io.FileOutputStream;

public class TestGeneration {

	private String haut;
	private String pied;
	Set<String> printedItems = new HashSet<>();
	public TestGeneration()
	{
		this.haut  ="<!DOCTYPE HTML>\n";
		this.haut +="<html lang=\"fr\">\n";
		this.haut +="	<head>\n" ;
		this.haut +="		<meta charset=\"utf-8\" />\n" ;
		this.haut +="		<title>ASTRE-Nom Prenom</title>\n" ;
		this.haut +="		<link href=\"style/style.css\" type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n";
		this.haut +="	</head>\n" ;
		this.haut +="	<body>\n" ;
		this.haut +="		<header>\n" ;
		this.haut +="			<img id=\"logoTitre\" src=\"../lib/logoAstre-nobg.png\" alt=\"LogoAstre\">\n" ;
		this.haut +="			<h1>ASTRE- Nom Prenom - Categorie </h1>\n";
		this.haut +="		</header>\n";

		this.pied  ="	</body>\n";
		this.pied +="</html>\n";
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("./generation/ressourceTest.html") );

			pw.print (this.haut);
			pw.println ( "		<div class=\"premiereLigne\">\n");
			pw.println ( "			<div>");
			pw.println ( "				<h2>Heures</h2>");
			pw.println ( "				<ul>");
			pw.println ( "					<li>Nom : 1</li>");
			pw.println ( "					<li>Prenom : 2</li>");
			pw.println ( "					<br>");
			pw.println ( "					<li>Heure Maximum : 3</li>");
			pw.println ( "					<li>Heure Minimum : 4</li>");
			pw.println ( "					<li>Heures Prévues : 5</li>");
			pw.println ( "				</ul>");
			pw.println ( "			</div>");
			pw.println ( "			<div>");
			pw.println ( "				<h2>Ressources</h2>");
			pw.println ( "				<ul>");
			pw.println ("						<li>R1.01 Developpement d'application</li>");
			pw.println ( "				</ul>");
			pw.println ( "			</div>");
			pw.println ( "		</div>\n");
			pw.println ( "		<div class=\"gridRessource\">\n");
			pw.println ("				<div class=\"info\">\n");
			pw.println ("					<h2 class=\"module\">R1.01 Developpement d'application</h2>\n");
			pw.println ("					<ul>\n");
				
			pw.println ("						<li class=\"\">CM</li>");
			pw.println ("						<li>Affectation 1:\n");
			pw.println ("							<ul>\n");
			pw.println ("								<li>Nb Heures : 1</li>\n");
			pw.println ("								<li>Nb Semaine : 2</li>\n");
			pw.println ("							</ul>\n");
			pw.println ("						</li>\n");
			pw.println ("					</ul>\n");
			pw.println ("				</div>\n");
			
			pw.println ("				<div class=\"info\">\n");
			pw.println ("					<h2 class=\"module\">R1.01 Developpement d'application</h2>\n");
			pw.println ("					<ul>\n");
				
			pw.println ("						<li class=\"\">CM</li>");
			pw.println ("						<li>Affectation 1:\n");
			pw.println ("							<ul>\n");
			pw.println ("								<li>Nb Heures : 1</li>\n");
			pw.println ("								<li>Nb Semaine : 2</li>\n");
			pw.println ("							</ul>\n");
			pw.println ("						</li>\n");
			pw.println ("					</ul>\n");
			pw.println ("				</div>\n");
			pw.println ("				<div class=\"info\">\n");
			pw.println ("					<h2 class=\"module\">R1.01 Developpement d'application</h2>\n");
			pw.println ("					<ul>\n");
				
			pw.println ("						<li class=\"\">CM</li>");
			pw.println ("						<li>Affectation 1:\n");
			pw.println ("							<ul>\n");
			pw.println ("								<li>Nb Heures : 1</li>\n");
			pw.println ("								<li>Nb Semaine : 2</li>\n");
			pw.println ("							</ul>\n");
			pw.println ("						</li>\n");
			pw.println ("					</ul>\n");
			pw.println ("				</div>\n");
			pw.println ("				<div class=\"info\">\n");
			pw.println ("					<h2 class=\"module\">R1.01 Developpement d'application</h2>\n");
			pw.println ("					<ul>\n");
				
			pw.println ("						<li class=\"\">CM</li>");
			pw.println ("						<li>Affectation 1:\n");
			pw.println ("							<ul>\n");
			pw.println ("								<li>Nb Heures : 1</li>\n");
			pw.println ("								<li>Nb Semaine : 2</li>\n");
			pw.println ("							</ul>\n");
			pw.println ("						</li>\n");
			pw.println ("					</ul>\n");
			pw.println ("				</div>\n");
			pw.println ("				<div class=\"info\">\n");
			pw.println ("					<h2 class=\"module\">R1.01 Developpement d'application</h2>\n");
			pw.println ("					<ul>\n");
				
			pw.println ("						<li class=\"\">CM</li>");
			pw.println ("						<li>Affectation 1:\n");
			pw.println ("							<ul>\n");
			pw.println ("								<li>Nb Heures : 1</li>\n");
			pw.println ("								<li>Nb Semaine : 2</li>\n");
			pw.println ("							</ul>\n");
			pw.println ("						</li>\n");
			pw.println ("					</ul>\n");
			pw.println ("				</div>\n");
			pw.println ("				<div class=\"info\">\n");
			pw.println ("					<h2 class=\"module\">R1.01 Developpement d'application</h2>\n");
			pw.println ("					<ul>\n");
				
			pw.println ("						<li class=\"\">CM</li>");
			pw.println ("						<li>Affectation 1:\n");
			pw.println ("							<ul>\n");
			pw.println ("								<li>Nb Heures : 1</li>\n");
			pw.println ("								<li>Nb Semaine : 2</li>\n");
			pw.println ("							</ul>\n");
			pw.println ("						</li>\n");
			pw.println ("					</ul>\n");
			pw.println ("				</div>\n");
			pw.println ("				<div class=\"info\">\n");
			pw.println ("					<h2 class=\"module\">R1.01 Developpement d'application</h2>\n");
			pw.println ("					<ul>\n");
				
			pw.println ("						<li class=\"\">CM</li>");
			pw.println ("						<li>Affectation 1:\n");
			pw.println ("							<ul>\n");
			pw.println ("								<li>Nb Heures : 1</li>\n");
			pw.println ("								<li>Nb Semaine : 2</li>\n");
			pw.println ("							</ul>\n");
			pw.println ("						</li>\n");
			pw.println ("					</ul>\n");
			pw.println ("				</div>\n");
			pw.println ("				<div class=\"info\">\n");
			pw.println ("					<h2 class=\"module\">R1.01 Developpement d'application</h2>\n");
			pw.println ("					<ul>\n");
				
			pw.println ("						<li class=\"\">CM</li>");
			pw.println ("						<li>Affectation 1:\n");
			pw.println ("							<ul>\n");
			pw.println ("								<li>Nb Heures : 1</li>\n");
			pw.println ("								<li>Nb Semaine : 2</li>\n");
			pw.println ("							</ul>\n");
			pw.println ("						</li>\n");
			pw.println ("					</ul>\n");
			pw.println ("				</div>\n");
			pw.println ("				<div class=\"info\">\n");
			pw.println ("					<h2 class=\"module\">R1.01 Developpement d'application</h2>\n");
			pw.println ("					<ul>\n");
				
			pw.println ("						<li class=\"\">CM</li>");
			pw.println ("						<li>Affectation 1:\n");
			pw.println ("							<ul>\n");
			pw.println ("								<li>Nb Heures : 1</li>\n");
			pw.println ("								<li>Nb Semaine : 2</li>\n");
			pw.println ("							</ul>\n");
			pw.println ("						</li>\n");
			pw.println ("					</ul>\n");
			pw.println ("				</div>\n");
			pw.println ("				<div class=\"info\">\n");
			pw.println ("					<h2 class=\"module\">R1.01 Developpement d'application</h2>\n");
			pw.println ("					<ul>\n");
				
			pw.println ("						<li class=\"\">CM</li>");
			pw.println ("						<li>Affectation 1:\n");
			pw.println ("							<ul>\n");
			pw.println ("								<li>Nb Heures : 1</li>\n");
			pw.println ("								<li>Nb Semaine : 2</li>\n");
			pw.println ("							</ul>\n");
			pw.println ("						</li>\n");
			pw.println ("					</ul>\n");
			pw.println ("				</div>\n");
			pw.println ("				<div class=\"info\">\n");
			pw.println ("					<h2 class=\"module\">R1.01 Developpement d'application</h2>\n");
			pw.println ("					<ul>\n");
				
			pw.println ("						<li class=\"\">CM</li>");
			pw.println ("						<li>Affectation 1:\n");
			pw.println ("							<ul>\n");
			pw.println ("								<li>Nb Heures : 1</li>\n");
			pw.println ("								<li>Nb Semaine : 2</li>\n");
			pw.println ("							</ul>\n");
			pw.println ("						</li>\n");
			pw.println ("					</ul>\n");
			pw.println ("				</div>\n");
			pw.println ("				<div class=\"info\">\n");
			pw.println ("					<h2 class=\"module\">R1.01 Developpement d'application</h2>\n");
			pw.println ("					<ul>\n");
				
			pw.println ("						<li class=\"\">CM</li>");
			pw.println ("						<li>Affectation 1:\n");
			pw.println ("							<ul>\n");
			pw.println ("								<li>Nb Heures : 1</li>\n");
			pw.println ("								<li>Nb Semaine : 2</li>\n");
			pw.println ("							</ul>\n");
			pw.println ("						</li>\n");
			pw.println ("					</ul>\n");
			pw.println ("				</div>\n");
			pw.println ("				<div class=\"info\">\n");
			pw.println ("					<h2 class=\"module\">R1.01 Developpement d'application</h2>\n");
			pw.println ("					<ul>\n");
				
			pw.println ("						<li class=\"\">CM</li>");
			pw.println ("						<li>Affectation 1:\n");
			pw.println ("							<ul>\n");
			pw.println ("								<li>Nb Heures : 1</li>\n");
			pw.println ("								<li>Nb Semaine : 2</li>\n");
			pw.println ("							</ul>\n");
			pw.println ("						</li>\n");
			pw.println ("					</ul>\n");
			pw.println ("				</div>\n");
			pw.println ("				<div class=\"info\">\n");
			pw.println ("					<h2 class=\"module\">R1.01 Developpement d'application</h2>\n");
			pw.println ("					<ul>\n");
				
			pw.println ("						<li class=\"\">CM</li>");
			pw.println ("						<li>Affectation 1:\n");
			pw.println ("							<ul>\n");
			pw.println ("								<li>Nb Heures : 1</li>\n");
			pw.println ("								<li>Nb Semaine : 2</li>\n");
			pw.println ("							</ul>\n");
			pw.println ("						</li>\n");
			pw.println ("					</ul>\n");
			pw.println ("				</div>\n");
			pw.println ("				<div class=\"info\">\n");
			pw.println ("					<h2 class=\"module\">R1.01 Developpement d'application</h2>\n");
			pw.println ("					<ul>\n");
				
			pw.println ("						<li class=\"\">CM</li>");
			pw.println ("						<li>Affectation 1:\n");
			pw.println ("							<ul>\n");
			pw.println ("								<li>Nb Heures : 1</li>\n");
			pw.println ("								<li>Nb Semaine : 2</li>\n");
			pw.println ("							</ul>\n");
			pw.println ("						</li>\n");
			pw.println ("					</ul>\n");
			pw.println ("				</div>\n");
			pw.println ("				<div class=\"info\">\n");
			pw.println ("					<h2 class=\"module\">R1.01 Developpement d'application</h2>\n");
			pw.println ("					<ul>\n");
				
			pw.println ("						<li class=\"\">CM</li>");
			pw.println ("						<li>Affectation 1:\n");
			pw.println ("							<ul>\n");
			pw.println ("								<li>Nb Heures : 1</li>\n");
			pw.println ("								<li>Nb Semaine : 2</li>\n");
			pw.println ("							</ul>\n");
			pw.println ("						</li>\n");
			pw.println ("					</ul>\n");
			pw.println ("				</div>\n");
			pw.println ("				<div class=\"info\">\n");
			pw.println ("					<h2 class=\"module\">R1.01 Developpement d'application</h2>\n");
			pw.println ("					<ul>\n");
				
			pw.println ("						<li class=\"\">CM</li>");
			pw.println ("						<li>Affectation 1:\n");
			pw.println ("							<ul>\n");
			pw.println ("								<li>Nb Heures : 1</li>\n");
			pw.println ("								<li>Nb Semaine : 2</li>\n");
			pw.println ("							</ul>\n");
			pw.println ("						</li>\n");
			pw.println ("					</ul>\n");
			pw.println ("				</div>\n");

			pw.println ("				<div class=\"info\">\n");
			pw.println ("					<h2 class=\"module\">R1.01 Developpement d'application</h2>\n");
			pw.println ("					<ul>\n");
				
			pw.println ("						<li class=\"\">CM</li>");
			pw.println ("						<li>Affectation 1:\n");
			pw.println ("							<ul>\n");
			pw.println ("								<li>Nb Heures : 1</li>\n");
			pw.println ("								<li>Nb Semaine : 2</li>\n");
			pw.println ("							</ul>\n");
			pw.println ("						</li>\n");
			pw.println ("					</ul>\n");
			pw.println ("				</div>\n");
			
			pw.println ( "		</div>\n");
			pw.println (this.pied);
			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}

	public TestGeneration(Module module)
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
		String  divRess = "";
		String  codeActuel = "";
		Boolean premPassage = false;
		CategorieHeures catHeure = null;
		int cpt = 1;
		for (Affectations affec : intervenant.getLstAffectations()) {
			if(affec.getModule() instanceof Ressource)
			{
				if (!affec.getModule().getCode().equals(codeActuel)){
					if(premPassage)
					{
						divRess+="				</ul>\n";
						divRess+="			</div>\n";
					}
					cpt = 1;
					premPassage = true;
					codeActuel= affec.getModule().getCode();
					divRess+="			<div class=\"info\">\n";
					divRess+="				<h2 class=\"module\">"+ affec.getModule().getCode()+" "+affec.getModule().getLibLong()+"</h2>\n";
					divRess+="				<ul>\n";
				}

				if (affec.getModule().getCode().equals(codeActuel)){
					if (affec.getCategorieHeures()!=catHeure) {
						cpt = 1;
						divRess+="					<li class=\"\">"+affec.getCategorieHeures().getlibCatHeur()+"</li>\n";
						catHeure  = affec.getCategorieHeures();
					}
					divRess+="					<li>Affectation "+cpt+" :\n";
					divRess+="						<ul>\n";
					divRess+="							<li>Nb Heures : "+affec.getNbHeure()+"</li>\n";
					divRess+="							<li>Nb Semaine : "+affec.getNbSemaine()+"</li>\n";
					divRess+="						</ul>\n";
					divRess+="					</li>\n";
					cpt++;
				}
			}
		}
		divRess+="				</ul>\n";
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
		TestGeneration g = new TestGeneration();
	}
}
