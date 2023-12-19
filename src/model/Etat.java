package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.action.Action;
import model.modules.Module;
import model.modules.Ressource;
import model.modules.Sae;
import model.modules.Stage;
import model.modules.PPP;
import java.util.Scanner;
import java.io.FileInputStream;

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
			Class.forName("org.postgresql.Driver"); //Postgress

			// Connection
			Etat.connec = DriverManager.getConnection("jdbc:postgresql://woody/hs220880","hs220880","SAHAU2004"); //Postgress
			// Etat.connec = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hs220880","hs220880","SAHAU2004"); //Postgress
			
			Etat.recupererNomEtat();

			//Lancer le scripts en cas de Table détruite
			Etat.lireFichierSQL(Etat.FIC_CREATE);


			Etat.genererInfos();

		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("SQL Error: " + e.getMessage());
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
			ResultSet res = st.executeQuery("SELECT * FROM CategorieHeures"+ Etat.nom);

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
			ResultSet res = st.executeQuery("SELECT * FROM CategorieIntervenants"+ Etat.nom);

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
			ResultSet res = st.executeQuery("SELECT * FROM Semestres"+ Etat.nom);

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
			ResultSet res = st.executeQuery("SELECT * FROM Intervenants"+ Etat.nom);

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
					m = new Sae(sem, code, libLong, libCourt, heurePonctuel,valide);
				if (type.equals("Stage"))
					m = new Stage(sem, code, libLong, libCourt, heurePonctuel,valide);
				if (type.equals("PPP"))
					m = new PPP(sem, code, libLong, libCourt, heurePonctuel,valide);

				Statement st1 = connec.createStatement();
				ResultSet res1 = st1.executeQuery("SELECT * FROM ModulesCatHeures"+ Etat.nom);

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
			ResultSet res = st.executeQuery("SELECT * FROM Affectation"+ Etat.nom);

			while (res.next()) {

				Intervenants inter = Etat.getIntervenant(res.getString("nomInt"), res.getString("prenomInt"));
				Module mode = Etat.getModule(res.getString("codeMod"));
				CategorieHeures cat = Etat.getCatHeure(res.getString("libCatHeur"));
				int nbs = res.getInt("nbSem");
				int nbg = res.getInt("nbGroupe");
				String comm = res.getString("commentaire");

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
				//System.out.println("Ajout");
				retour.add(a);
			}
		}
		//System.out.println(retour);
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

				System.out.println(st);

				// On l'execute
				st.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

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
			}

		} catch (Exception e) {
			System.out.println("Methode marche pas");
		}
	}
	
	/* RECUPERER NOMS */
	public static String[] getEtats() {

		List<String> etatsList = new ArrayList<>();
		try {

			Statement st = Etat.connec.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM Etat ORDER BY datecrea DESC");

			while (res.next()) {
				etatsList.add(res.getString("etat"));
				System.out.println(res.getString("etat"));
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		String[] etatsArray = new String[etatsList.size()];
		return etatsList.toArray(etatsArray);
	}

	/* SET ETATS */
	public static void changerEtat (String nom) {
		Etat.nom = nom;
		System.out.println(Etat.nom);
		Etat.lireFichierSQL(Etat.FIC_CREATE);
		Etat.genererInfos();
	}

	/* CREER ETATS */
	public static void dupliquerEtat (String etatDest, String etatDep)
	{
		try {
			Statement st = Etat.connec.createStatement();

			for(String tables : Etat.LST_NOM_TABLES) {
				System.out.println("CREATE TABLE " + tables + etatDest + " AS TABLE " + tables + etatDep);
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
			System.out.println(e);
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

		System.out.println(Etat.nom.equals(nom));
		if (Etat.nom.equals(nom)) return false;

		try {
			Statement st = Etat.connec.createStatement();


			for (String s : Etat.LST_NOM_TABLES) {
				st.executeUpdate("DROP TABLE " + s + nom.toLowerCase() + " CASCADE");
				System.out.println("DROP TABLE " + s + nom.toLowerCase() + " CASCADE");
			}

			st.executeUpdate("DELETE FROM Etat WHERE etat = '" + nom + "'");

			return true;

		} catch (Exception e) {
			System.out.println(e);
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
						System.out.println(commande);
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

	public static void genererCSV() { Etat.lireFichierSQL(Etat.FIC_CSV); }
}
