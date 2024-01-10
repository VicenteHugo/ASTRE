package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Generer.Generation;
import model.action.Action;
import model.modules.Module;
import model.modules.Ressource;
import model.modules.Sae;
import model.modules.Stage;
import view.Etat.PanelEtat;
import model.modules.PPP;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class Etat {

	/*-------------------------------------------------*/
	/*                    CONSTANTES                   */
	/*-------------------------------------------------*/

	/**Chemin vers le scripts sql.*/
	public static final String FIC_CREATE = "./SQL/REALISATION/CreateTablesAstre.sql";
	public static final String FIC_CSV    = "./SQL/REALISATION/Function.sql";

	/**Liste des Tables.Utile pour la verification de leurs présences. */
	public static final String[] LST_NOM_TABLES = new String[] 
	{ "CategorieIntervenants", "CategorieHeures", "Semestres", "Intervenants", "Modules","ModulesCatHeures","Affectation"};

	

	/*-------------------------------------------------*/
	/*                     VARIABLES                   */
	/*-------------------------------------------------*/

	// Connection/SQL
	/** Connection vers la bado. */
	private static Connection connec;
	/** Nom de l'état. */
	public static String nom;

	// Liste de liaison 0
	/** Liste des catégories d'heures (CM, TP, TD, etc...). */
	private static ArrayList<CategorieHeures> lstCategorieHeures;
	/** Liste des catégories d'intervenants. */
	private static ArrayList<CategorieIntervenant> lstCategorieIntervenants;
	/** Liste des catégories des semestres. */
	private static ArrayList<Semestres> lstSemestres;

	// Liste de laison 1
	/** Liste des intervenants. */
	private static ArrayList<Intervenants> lstIntervenants;
	/** Liste des modules. */
	private static ArrayList<Module> lstModule;

	// Liste de laison 2
	/** Liste des association. */
	private static ArrayList<Affectations> lstAffectations;

	// Action
	/** Liste des actions. */
	private static List<Action> lstActions;

	

	/*-------------------------------------------------*/
	/*                  CONSTRUCTEURS                  */
	/*-------------------------------------------------*/

	public Etat() {

		Etat.lstActions = new ArrayList<>();

		try {
			Scanner sc = new Scanner(new FileReader("../config/login.dat"));
			Scanner sc2 = new Scanner(sc.next());
			sc2.useDelimiter("=");
			sc2.next();
			String name = sc2.next();
			sc2.close();
			sc2 = new Scanner(sc.next());
			sc2.useDelimiter("=");
			sc2.next();
			String pwd = sc2.next();
			sc2.close();
			sc2 = new Scanner(sc.next());
			sc2.useDelimiter("=");
			sc2.next();
			String serveur = sc2.next();

			Class.forName("org.postgresql.Driver"); //Postgress

			// Connection
			 Etat.connec = DriverManager.getConnection("jdbc:postgresql://woody/hs220880","hs220880","SAHAU2004"); //Postgress
			//Etat.connec = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hugo","hugo","sui12345"); //Postgress
			//tat.connec = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hs220880","hs220880","SAHAU2004"); //Postgress
			// Etat.connec = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dave","dave","davepass"); //Postgress
			// Etat.connec = DriverManager.getConnection("jdbc:postgresql://" + serveur + ":5432/" + name, name, pwd); //avec instaler 
			Etat.recupererNomEtat();

			//Lancer le scripts en cas de Table détruite
			// Etat.lireFichierSQL(Etat.FIC_CREATE);


			Etat.genererInfos();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	

	/*-------------------------------------------------*/
	/*                 GENERATION INFOS                */
	/*-------------------------------------------------*/

	/* MAIN */

	private static void genererInfos() {
		// Générer les premières tables
		Etat.genererCategorieHeures();
		Etat.genererCategorieIntervenants();
		Etat.genererSemestres();

		// Générer les deuxièmes tables
		Etat.genererIntervenants();
		Etat.genererModules();

		// Générer les troisièmes tables
		Etat.genererAffectations();
	}


	/* LIAISON 1 */

	//CatHeures
	private static void genererCategorieHeures() {

		Etat.lstCategorieHeures = new ArrayList<>();

		try {

			Statement st = connec.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM CategorieHeures"+ Etat.nom + " ORDER BY libCatHeur");

			while (res.next())
				Etat.lstCategorieHeures
						.add(new CategorieHeures(res.getString("libCatHeur"), res.getFloat("coefCatHeur")));

			res.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//CatInt
	private static void genererCategorieIntervenants() {

		Etat.lstCategorieIntervenants = new ArrayList<>();

		try {
			Statement st = connec.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM CategorieIntervenants"+ Etat.nom + " ORDER BY codeCatInt");

			while (res.next()) {
				String code = res.getString("codeCatInt");
				String lib = res.getString("libCatInt");
				float coef = res.getFloat("coefCatInt");
				int hmin = res.getInt("heureMinCatInt");
				int hmax = res.getInt("heureMaxCatInt");

				Etat.lstCategorieIntervenants.add(new CategorieIntervenant(code, lib, coef, hmin, hmax));
			}

			res.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Semestres
	private static void genererSemestres() {

		Etat.lstSemestres = new ArrayList<>();

		try {
			Statement st = connec.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM Semestres"+ Etat.nom + " ORDER BY numSem");

			while (res.next()) {

				int numSem = res.getInt("numSem");
				int nbGpTdSem = res.getInt("nbGpTdSem");
				int nbGpTpSem = res.getInt("nbGpTpSem");
				int nbEtdSem = res.getInt("nbEtdSem");
				int nbSemSem = res.getInt("nbSemSem");

				Etat.lstSemestres.add(new Semestres(numSem, nbGpTdSem, nbGpTpSem, nbEtdSem, nbSemSem));
			}

			res.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/* LIAISON 2 */

	//Intervenants
	public static void genererIntervenants() {

		Etat.lstIntervenants = new ArrayList<>();

		try {
			Statement st = connec.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM Intervenants"+ Etat.nom+ " ORDER BY nomInt, prenomInt");

			while (res.next()) {
				String nom = res.getString("nomInt");
				String prenom = res.getString("prenomInt");
				int hmin = res.getInt("heureMinInt");
				int hmax = res.getInt("heureMaxInt");
				float coef = res.getFloat("coefInt");

				CategorieIntervenant cat = Etat.getCatInt(res.getString("categInt"));

				Etat.lstIntervenants.add(new Intervenants(cat, nom, prenom, hmin, hmax,coef));
			}

			res.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//Modules
	public static void genererModules() {

		Etat.lstModule = new ArrayList<>();

		try {
			Statement st = connec.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM Modules"+ Etat.nom + " ORDER BY codeMod");

			while (res.next()) {
				Module m = null;

				String type = res.getString("typeMod");

				String  code = res.getString("codeMod");
				String  libLong = res.getString("libLongMod");
				String  libCourt = res.getString("libCourtMod");
				int     heurePonctuel = res.getInt("nbHeurPonc");
				boolean valide        = res.getBoolean("validmod");

				Semestres sem = Etat.lstSemestres.get(res.getInt("semMod") - 1);

				if (type.equals("Ressource"))
					m = new Ressource(sem, code, libLong, libCourt, heurePonctuel,valide);
				if (type.equals("Sae"))
					m = new Sae      (sem, code, libLong, libCourt, heurePonctuel,valide);
				if (type.equals("Stage"))
					m = new Stage    (sem, code, libLong, libCourt, heurePonctuel,valide);
				if (type.equals("PPP"))
					m = new PPP      (sem, code, libLong, libCourt, heurePonctuel,valide);

				Statement st1 = connec.createStatement();
				ResultSet res1 = st1.executeQuery("SELECT * FROM ModulesCatHeures"+ Etat.nom+ " WHERE codeMod = '" + code +"'");

				while (res1.next()) {
					CategorieHeures catH = Etat.getCatHeure(res1.getString("libCatHeur"));
					int heurePn = res1.getInt("nbHeurePN");
					int heureSem = res1.getInt("nbHeureSem");
					int nbSem = res1.getInt("nbSemaine");

					m.initList(heurePn, nbSem, heureSem, catH);
				}

				Etat.lstModule.add(m);
			}

			res.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}


	/* LIAISON 3 */

	//Affectations
	public static void genererAffectations() {
		
		Etat.lstAffectations = new ArrayList<>();

		try {
			Statement st = connec.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM Affectation"+ Etat.nom + " ORDER BY libCatHeur, nomInt, prenomInt");

			while (res.next()) {

				Intervenants inter = Etat.getIntervenant(res.getString("nomInt"), res.getString("prenomInt"));
				System.out.println(res.getString("codeMod"));

				System.out.println("Code : " + res.getString("codeMod"));
				Module mode = Etat.getModule(res.getString("codeMod"));
				CategorieHeures cat = Etat.getCatHeure(res.getString("libCatHeur"));
				int nbs = res.getInt("nbSem");
				int nbg = res.getInt("nbGroupe");
				String comm = res.getString("commentaire");

				if (mode != null)
					Etat.lstAffectations.add(new Affectations(inter, mode, cat, nbs, nbg, comm));
			}

			res.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	/*-------------------------------------------------*/
	/*                 GET INFORMATIONS                */
	/*-------------------------------------------------*/

	/* ARRAY LIST */
	public static ArrayList<CategorieHeures>      getCategoriesHeures      () { return Etat.lstCategorieHeures;      }
	public static ArrayList<CategorieIntervenant> getCategoriesIntervenants() { return Etat.lstCategorieIntervenants;}
	public static ArrayList<Semestres>            getSemestres             () { return Etat.lstSemestres;            }

	public static ArrayList<Intervenants> getIntervenants() { return Etat.lstIntervenants;}
	public static ArrayList<Module>       getModules     () { return Etat.lstModule;      }

	public static ArrayList<Affectations> getAffectations() { return Etat.lstAffectations;}

	public static ArrayList<Affectations> getAffectations(Module mod){
		ArrayList<Affectations> retour = new ArrayList<Affectations>();
		for(Affectations a : lstAffectations){
			if(a.getModule() == mod){
				retour.add(a);
			}
		}

		return retour;
	}

	/* OBJECTS */

	//Cat Heures
	public static CategorieHeures getCatHeure(String nom) {
		for (CategorieHeures c : Etat.lstCategorieHeures)
			if (c.getlibCatHeur().equals(nom))
				return c;

		return null;
	}


	//Cat Intervenants
	public static CategorieIntervenant getCatInt(String nom) {

		for (CategorieIntervenant c : Etat.lstCategorieIntervenants)
			if (c.getCodeCatInt().equals(nom))
				return c;

		return null;
	}

	public static CategorieIntervenant getCatInt(int indice) {
		return Etat.lstCategorieIntervenants.get(indice);
	}

	
	//Intervenants 
	public static Intervenants getIntervenant(String nom, String prenom) {
		for (Intervenants i : Etat.lstIntervenants)
			if (i.getNomIntervenant().equals(nom) && i.getPrenomIntervenant().equals(prenom))
				return i;

		return null;
	}

	public static Intervenants getIntervenants(int i){
		return Etat.lstIntervenants.get(i);
	}

	public static List<Intervenants> getIntervenants(CategorieIntervenant cat) {

		List<Intervenants> lstIntervenants = new ArrayList<>();

		for (Intervenants i : Etat.lstIntervenants)
			if (i.getCategorieIntervenant() == cat)
				lstIntervenants.add(i);

		return lstIntervenants;
	}

	//Modules
	public static Module getModule(String code) {
		for (Module m : Etat.lstModule)
			if (m.getCode().equals(code))
				return m;

		return null;
	}


	//Affectations
	public static Affectations getAffectations(int i){
		return Etat.lstAffectations.get(i);
	}

	public static Affectations getAffectations(String nomInter) {
		for (Affectations i : Etat.lstAffectations)
			if (i.getIntervenant().getNomIntervenant().equals(nomInter))
				return i;

		return null;
	}
	


	/*-------------------------------------------------*/
	/*                VERIF INFORMATIONS               */
	/*-------------------------------------------------*/

	/**????? */
	public static boolean pasUtiliser (CategorieIntervenant cat) {
		for (Intervenants i : Etat.lstIntervenants) 
			if (i.getCategorieIntervenant() == cat)
				return false;

		return true;
	}

	/**???? */
	public static boolean pasUtiliser (CategorieHeures cat) {
		for (Affectations a : Etat.lstAffectations) 
			if (a.getCategorieHeures() == cat)
				return false;

		return true;
	}

	public static boolean pasUtiliser (Module m) {
		for (Affectations a : Etat.lstAffectations) 
			if (a.getModule() == m) 
				return false;

		return true;
	}

	public static boolean pasUtiliser (Intervenants i) {
		for (Affectations a : Etat.lstAffectations) 
			if (a.getIntervenant() == i) {
				return false;
			}

		return true;
	}
	


	/*-------------------------------------------------*/
	/*            AJOUT INFORMATIONS (LIST)            */
	/*-------------------------------------------------*/

	public static void ajouterCategorieHeure      (CategorieHeures      categorieHeures     ) { Etat.lstCategorieHeures      .add(categorieHeures)     ; }
	public static void ajouterCategorieIntervenant(CategorieIntervenant categorieIntervenant) { Etat.lstCategorieIntervenants.add(categorieIntervenant); }

	public static void ajouterIntervenant(Intervenants inter) {Etat.lstIntervenants.add(inter);}
	public static void ajouterModule     (Module       mod  ) {Etat.lstModule      .add(mod)  ;}

	public static void ajouterAffectation(Affectations affect) {Etat.lstAffectations.add(affect);}
	


	/*-------------------------------------------------*/
	/*            AJOUT INFORMATIONS (BADO)            */
	/*-------------------------------------------------*/
	
	/* ACTIONS */
	public static void ajouterAction(Action a) { Etat.lstActions.add(a); }

	public static void anuller() {
		Etat.lstActions.clear();
		Etat.genererInfos();
	}

	public static void enregistrer() {

		try {

			for (Action a : Etat.lstActions) {
				// On prépare la requêtes.
				PreparedStatement st = connec.prepareStatement(a.getRequeteSQL());

				// On met les info dans la requêtes
				List<Object> lstInfos = a.getInfo();

				for (int i = 1; i < lstInfos.size() + 1; i++) {

					Object info = lstInfos.get(i - 1);
					if (info instanceof String)
						st.setString(i, (String) info);

					if (info instanceof Integer)
						st.setInt(i, (Integer) info);

					if (info instanceof Float)
						st.setFloat(i, (Float) info);

					if (info instanceof Boolean)
						st.setBoolean(i, (Boolean) info);
				}


				// On l'execute
				System.out.println(st);
				st.executeUpdate();
			}
		} catch (Exception e) { e.printStackTrace(); }

		Etat.genererInfos();
		Etat.lstActions.clear();
	}
	


	/*-------------------------------------------------*/
	/*               GET/CHANGER/SET ETATS             */
	/*-------------------------------------------------*/

	/* LANCEMENTS */
	private static void recupererNomEtat()
	{
		try {
			//On regarde si la table Etat existe 

			Statement st = connec.createStatement();

			//Si elle existe pas on la crée
			st.executeUpdate("CREATE TABLE IF NOT EXISTS Etat (etat VARCHAR(25) PRIMARY KEY,dateCrea TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");

			ResultSet rs = st.executeQuery("SELECT * FROM Etat ORDER BY dateCrea DESC");

			if (rs.next()) {
				Etat.nom = rs.getString("etat");
			} else {
				st.executeUpdate("INSERT INTO Etat (etat) VALUES ('Etat1')");
				Etat.nom = "Etat1";

				Etat.lireFichierSQL(Etat.FIC_CREATE);
			}

		} catch (Exception e) {e.printStackTrace();}
	}
	
	/* RECUPERER NOMS */
	public static String[] getEtats() {

		List<String> etatsList = new ArrayList<>();
		try {

			Statement st = Etat.connec.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM Etat ORDER BY datecrea DESC");

			while (res.next()) {
				etatsList.add(res.getString("etat"));
			}

		} catch (Exception e) {e.printStackTrace();}

		String[] etatsArray = new String[etatsList.size()];
		return etatsList.toArray(etatsArray);
	}

	/* SET ETATS */
	public static void changerEtat (String nom) {
		Etat.nom = nom;
		Etat.genererInfos();
	}

	/* CREER ETATS */
	public static void dupliquerEtat (String etatDest, String etatDep)
	{
		try {
			Statement st = Etat.connec.createStatement();

			for(String tables : Etat.LST_NOM_TABLES) {
				st.executeUpdate("CREATE TABLE " + tables + etatDest + " AS TABLE " + tables + etatDep );
			}
			
			Etat.nom = etatDest;
			st.executeUpdate("INSERT INTO Etat (etat) VALUES ('"+nom+"')");

			Etat.verifEtat();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public static boolean creerEtat (String nom) {

		for (String etatsNom : Etat.getEtats())
			if (etatsNom.equals(nom)) return false;


		try {
			Statement st = Etat.connec.createStatement();
			st.executeUpdate("INSERT INTO Etat (etat) VALUES ('"+nom+"')");

			Etat.nom = nom;
			Etat.lireFichierSQL(Etat.FIC_CREATE);
			Etat.verifEtat();
			Etat.genererInfos();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/* SUPPRIMER ETAT */
	private static void verifEtat () {
		if (Etat.getEtats().length > 5) {
			Etat.suppEtat(Etat.getEtats()[5]);
		}
	}

	public static boolean suppEtat (String nom) {
		if (Etat.nom.equals(nom)) return false;

		try {
			Statement st = Etat.connec.createStatement();


			for (String s : Etat.LST_NOM_TABLES) {
				st.executeUpdate("DROP TABLE " + s + nom.toLowerCase() + " CASCADE");
			}

			st.executeUpdate("DELETE FROM Etat WHERE etat = '" + nom + "'");

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	


	/*-------------------------------------------------*/
	/*              EXECUTER FICHIER SQL               */
	/*-------------------------------------------------*/
	
	private static void lireFichierSQL(String fic) {
		try {

			String commande = "";
			Scanner scan = new Scanner(new FileInputStream(fic));
			Statement statement = Etat.connec.createStatement();

			while (scan.hasNextLine()) {

				String l = scan.nextLine();

				if (!(l.startsWith("/*") || l.startsWith("*/") || l.startsWith("*") || l.startsWith("--"))) {
					commande += " " + l;
					if (l.endsWith(";")) {
						commande = commande.replaceAll("ETAT", Etat.nom);
						statement.execute(commande);
						commande = "";
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	/*-------------------------------------------------*/
	/*               GENERATION FICHIER                */
	/*-------------------------------------------------*/

	public static void genererHTMLIntervenants(){
		new File(PanelEtat.getFichier()+ "/" + Etat.nom).mkdir();
		Generation.generationIntervenants(PanelEtat.getFichier() + "/" + Etat.nom + "/");
	}

	public static void genererHTMLModules(){
		new File(PanelEtat.getFichier()+ "/" + Etat.nom).mkdir();
		Generation.generationModules(PanelEtat.getFichier() + "/" + Etat.nom + "/");
	}

	// JAI PAS ACCES A LA COMMANDE DONC MODE BRUTAL
	public static void genererCSV() { 

		String sqlInt = "SELECT \n" + //
				"i.nomInt      AS \"Nom\",\n" + //
				"i.prenomInt   AS \"Prenom\",\n" + //
				"ci.libCatInt  AS \"categorie\",\n" + //
				"ci.coefCatInt AS \"coefficient\",\n" + //
				"i.heureMinInt AS \"service dû\",\n" + //
				"i.heureMaxInt AS \"heure maximum autorisés\",\n" + //
				"SUM(CASE WHEN s.numSem = 1 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END)  AS \"S1 (théo)\",\n" + //
				"SUM(CASE WHEN s.numSem = 1 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur ELSE 0 END)                                                                                                             AS \"S1 (réel)\",\n" + //
				"SUM(CASE WHEN s.numSem = 3 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END)  AS \"S3 (théo)\",\n" + //
				"SUM(CASE WHEN s.numSem = 3 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur ELSE 0 END)                                                                                                             AS \"S3 (réel)\",\n" + //
				"SUM(CASE WHEN s.numSem = 5 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END)  AS \"S5 (théo)\",\n" + //
				"SUM(CASE WHEN s.numSem = 5 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur ELSE 0 END)                                                                                                             AS \"S5 (réel)\",\n" + //
				"SUM(CASE WHEN s.numSem % 2 = 1 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END)  AS \"S Impaires (théo)\",\n" + //
				"SUM(CASE WHEN s.numSem % 2 = 1 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur ELSE 0 END)                                                                                                             AS \"S Impaires (réel)\",\n" + //
				"SUM(CASE WHEN s.numSem = 2 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END)  AS \"S2 (théo)\",\n" + //
				"SUM(CASE WHEN s.numSem = 2 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur ELSE 0 END)                                                                                                             AS \"S2 (réel)\",\n" + //
				"SUM(CASE WHEN s.numSem = 4 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END)  AS \"S4 (théo)\",\n" + //
				"SUM(CASE WHEN s.numSem = 4 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur ELSE 0 END)                                                                                                             AS \"S4 (réel)\",\n" + //
				"SUM(CASE WHEN s.numSem = 6 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END)  AS \"S6 (théo)\",\n" + //
				"SUM(CASE WHEN s.numSem = 6 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur ELSE 0 END)                                                                                                             AS \"S6 (réel)\",\n" + //
				"SUM(CASE WHEN s.numSem % 2 = 0 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END) AS \"S Paires (théo)\",\n" + //
				"SUM(CASE WHEN s.numSem % 2 = 0 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur ELSE 0 END)                                                                                                            AS \"S Paires (réel)\",\n" + //
				"SUM(CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END) AS \"Semestres (théo)\",\n" + //
				"SUM(mc.nbHeureSem * ci.coefCatInt * coefCatHeur)                                                                                                     AS \"Semestres (réel)\"\n" + //
				"\n" + //
				"FROM IntervenantsETAT i JOIN CategorieIntervenantsETAT ci ON i.categInt = ci.codeCatInt\n" + //
				"                         JOIN AffectationETAT a ON i.nomInt = a.nomInt AND i.prenomInt = a.prenomInt\n" + //
				"                         JOIN ModulesETAT m ON a.codeMod = m.codeMod\n" + //
				"                         JOIN ModulesCatHeuresETAT mc ON m.codeMod = mc.codeMod\n" + //
				"                         JOIN SemestresETAT s ON m.semMod = s.numSem\n" + //
				"                         JOIN CategorieHeuresETAT ch ON mc.libCatHeur = ch.libCatHeur\n" + //
				"GROUP BY\n" + //
				"    i.nomInt, i.prenomInt, ci.libCatInt, ci.coefCatInt, i.heureMinInt, i.heureMaxInt";

		

		String sqlSem = "SELECT "
                + "SUM(CASE WHEN s.numSem = 1 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END) AS \"S1 (théo)\", "
                + "SUM(CASE WHEN s.numSem = 1 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur ELSE 0 END) AS \"S1 (réel)\", "
                + "SUM(CASE WHEN s.numSem = 3 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END) AS \"S3 (théo)\", "
                + "SUM(CASE WHEN s.numSem = 3 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur ELSE 0 END) AS \"S3 (réel)\", "
                + "SUM(CASE WHEN s.numSem = 5 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END) AS \"S5 (théo)\", "
                + "SUM(CASE WHEN s.numSem = 5 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur ELSE 0 END) AS \"S5 (réel)\", "
                + "SUM(CASE WHEN s.numSem % 2 = 1 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END) AS \"S Impaires (théo)\", "
                + "SUM(CASE WHEN s.numSem % 2 = 1 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur ELSE 0 END) AS \"S Impaires (réel)\", "
                + "SUM(CASE WHEN s.numSem = 2 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END) AS \"S2 (théo)\", "
                + "SUM(CASE WHEN s.numSem = 2 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur ELSE 0 END) AS \"S2 (réel)\", "
                + "SUM(CASE WHEN s.numSem = 4 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END) AS \"S4 (théo)\", "
                + "SUM(CASE WHEN s.numSem = 4 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur ELSE 0 END) AS \"S4 (réel)\", "
                + "SUM(CASE WHEN s.numSem = 6 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END) AS \"S6 (théo)\", "
                + "SUM(CASE WHEN s.numSem = 6 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur ELSE 0 END) AS \"S6 (réel)\", "
                + "SUM(CASE WHEN s.numSem % 2 = 0 THEN CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END ELSE 0 END) AS \"S Paires (théo)\", "
                + "SUM(CASE WHEN s.numSem % 2 = 0 THEN mc.nbHeureSem * ci.coefCatInt * coefCatHeur ELSE 0 END) AS \"S Paires (réel)\", "
                + "SUM(CASE WHEN ci.libCatInt = 'TP' THEN mc.nbHeureSem * i.coefInt * ci.coefCatInt * coefCatHeur ELSE mc.nbHeureSem * ci.coefCatInt * coefCatHeur END) AS \"Semestres (théo)\", "
                + "SUM(mc.nbHeureSem * ci.coefCatInt * coefCatHeur) AS \"Semestres (réel)\" "
                + "FROM IntervenantsEtat1 i "
                + "JOIN CategorieIntervenantsEtat1 ci ON i.categInt = ci.codeCatInt "
                + "JOIN AffectationEtat1 a ON i.nomInt = a.nomInt AND i.prenomInt = a.prenomInt "
                + "JOIN ModulesEtat1 m ON a.codeMod = m.codeMod "
                + "JOIN ModulesCatHeuresEtat1 mc ON m.codeMod = mc.codeMod "
                + "JOIN SemestresEtat1 s ON m.semMod = s.numSem "
                + "JOIN CategorieHeuresEtat1 ch ON mc.libCatHeur = ch.libCatHeur;";


		sqlInt = sqlInt.replaceAll("ETAT", Etat.nom);
		sqlSem = sqlSem.replaceAll("ETAT", Etat.nom);


		try {
             PreparedStatement preparedStatement = Etat.connec.prepareStatement(sqlInt);
             ResultSet resultSet = preparedStatement.executeQuery();
             try (FileWriter csvWriter = new FileWriter("data_" + Etat.nom + ".csv")) {

				// Write CSV header
				csvWriter.append("Intervenants,,Categorie,,,,S1,,S3,,S5,,SSTot,,S2,,S4,,S6,,SSTot,,Totaux,,\n");
				csvWriter.append("nom,prenomn, categorie, coef,hmin, hmax, théo, réel, théo, réel, théo, réel, théo, réel, théo, réel, théo, réel, théo, réel, théo, réel, théo, réel\n");

				// Write data to CSV
				while (resultSet.next()) {
					//Intervenants
					csvWriter.append(resultSet.getString("Nom")).append(",");
					csvWriter.append(resultSet.getString("Prenom")).append(",");

					//Categorie
					csvWriter.append(resultSet.getString("categorie")).append(",");
					csvWriter.append(resultSet.getString("coefficient")).append(",");
					csvWriter.append(resultSet.getString("service dû")).append(",");
					csvWriter.append(resultSet.getString("heure maximum autorisés")).append(",");

					//S1
					csvWriter.append(resultSet.getString("S1 (théo)")).append(",");
					csvWriter.append(resultSet.getString("S1 (réel)")).append(",");
					//S3
					csvWriter.append(resultSet.getString("S3 (théo)")).append(",");
					csvWriter.append(resultSet.getString("S3 (réel)")).append(",");
					//S5
					csvWriter.append(resultSet.getString("S5 (théo)")).append(",");
					csvWriter.append(resultSet.getString("S5 (réel)")).append(",");
					//ST Imp
					csvWriter.append(resultSet.getString("S Impaires (théo)")).append(",");
					csvWriter.append(resultSet.getString("S Impaires (réel)")).append(",");

					//S2
					csvWriter.append(resultSet.getString("S1 (théo)")).append(",");
					csvWriter.append(resultSet.getString("S1 (réel)")).append(",");
					//S4
					csvWriter.append(resultSet.getString("S3 (théo)")).append(",");
					csvWriter.append(resultSet.getString("S3 (réel)")).append(",");
					//S6
					csvWriter.append(resultSet.getString("S5 (théo)")).append(",");
					csvWriter.append(resultSet.getString("S5 (réel)")).append(",");
					//ST Pai
					csvWriter.append(resultSet.getString("S Paires (théo)")).append(",");
					csvWriter.append(resultSet.getString("S Paires (réel)")).append(",");

					//ST Pai
					csvWriter.append(resultSet.getString("Semestres (théo)")).append(",");
					csvWriter.append(resultSet.getString("Semestres (réel)")).append("\n");
				}

				resultSet.close();

				preparedStatement = Etat.connec.prepareStatement(sqlSem);
				resultSet = preparedStatement.executeQuery();
				resultSet.next();

				//Intervenants
				csvWriter.append("Totaux,,,,,,");

				//S1
				csvWriter.append(resultSet.getString("S1 (théo)")).append(",");
				csvWriter.append(resultSet.getString("S1 (réel)")).append(",");
				//S3
				csvWriter.append(resultSet.getString("S3 (théo)")).append(",");
				csvWriter.append(resultSet.getString("S3 (réel)")).append(",");
				//S5
				csvWriter.append(resultSet.getString("S5 (théo)")).append(",");
				csvWriter.append(resultSet.getString("S5 (réel)")).append(",");
				//ST Imp
				csvWriter.append(resultSet.getString("S Impaires (théo)")).append(",");
				csvWriter.append(resultSet.getString("S Impaires (réel)")).append(",");

				//S2
				csvWriter.append(resultSet.getString("S1 (théo)")).append(",");
				csvWriter.append(resultSet.getString("S1 (réel)")).append(",");
				//S4
				csvWriter.append(resultSet.getString("S3 (théo)")).append(",");
				csvWriter.append(resultSet.getString("S3 (réel)")).append(",");
				//S6
				csvWriter.append(resultSet.getString("S5 (théo)")).append(",");
				csvWriter.append(resultSet.getString("S5 (réel)")).append(",");
				//ST Pai
				csvWriter.append(resultSet.getString("S Paires (théo)")).append(",");
				csvWriter.append(resultSet.getString("S Paires (réel)")).append(",");

				//ST Pai
				csvWriter.append(resultSet.getString("Semestres (théo)")).append(",");
				csvWriter.append(resultSet.getString("Semestres (réel)")).append("\n");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
