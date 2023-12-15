package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.action.Action;
import model.action.Ajout;
import model.action.Suppression;
import model.modules.Module;
import model.modules.Ressource;
import model.modules.Sae;
import model.modules.Stage;
import model.modules.PPP;
import java.util.Scanner;
import java.io.FileInputStream;

public class Etat {
	/**Chemin vers le scripts sql.*/
	public static final String FICHIER = "./SQL/REALISATION/CreateTablesAstre.sql";

	/**Liste des Tables.Utile pour la verification de leurs présences. */
	public static final String[] LST_NOM_TABLES = new String[] 
	{ "CategorieIntervenants", "CategorieHeures", "Semestres", "Intervenants", "Modules","ModulesCatHeures","Affectation"};

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

	public Etat() {

		Etat.lstActions = new ArrayList<>();

		try {
			Class.forName("org.postgresql.Driver"); //Postgress
			// Class.forName("com.mysql.cj.jdbc.Driver"); //MySQL


			// Connection
			Etat.connec = DriverManager.getConnection("jdbc:postgresql://woody/hs220880","hs220880","SAHAU2004"); //Postgress
			// Etat.connec = DriverManager.getConnection("jdbc:mysql://localhost:3306/astre","root", ""); //MySQL
			
			Etat.recupererNomEtat();

			//Lancer le scripts en cas de Table détruite
			Etat.lireFichierSQL(Etat.FICHIER);


			Etat.genererInfos();

		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("SQL Error: " + e.getMessage());
		}
	}

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

	/*--------------------------------------------------------------*/
	/* LIAISON 1 */
	/*--------------------------------------------------------------*/

	// CREATE
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

	// Méthode GET
	public static ArrayList<CategorieHeures> getCategoriesHeures() {
		return Etat.lstCategorieHeures;
	}

	public static ArrayList<CategorieIntervenant> getCategoriesIntervenants() {
		return Etat.lstCategorieIntervenants;
	}

	public static ArrayList<Semestres> getSemestres() {
		return Etat.lstSemestres;
	}

	public static CategorieIntervenant getCatInt(String nom) {

		for (CategorieIntervenant c : Etat.lstCategorieIntervenants)
			if (c.getCodeCatInt().equals(nom))
				return c;

		return null;
	}

	public static CategorieHeures getCatHeure(String nom) {
		for (CategorieHeures c : Etat.lstCategorieHeures)
			if (c.getlibCatHeur().equals(nom))
				return c;

		return null;
	}

	public static void ajouterCategorieHeure(CategorieHeures categorieHeures) {
		Etat.lstCategorieHeures.add(categorieHeures);
	}

	/*--------------------------------------------------------------*/
	/* LIAISON 2 */
	/*--------------------------------------------------------------*/

	// Méthode CREATE
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

	public static void ajouterIntervenant(Intervenants inter) {
		Etat.lstIntervenants.add(inter);
	}

	public static void genererModules() {

		Etat.lstModule = new ArrayList<>();

		try {
			Statement st = connec.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM Modules"+ Etat.nom);

			while (res.next()) {
				Module m = null;

				String type = res.getString("typeMod");

				String code = res.getString("codeMod");
				String libLong = res.getString("libLongMod");
				String libCourt = res.getString("libCourtMod");
				int heurePonctuel = res.getInt("nbHeurPonc");

				Semestres sem = Etat.lstSemestres.get(res.getInt("semMod") - 1);

				if (type.equals("Ressource"))
					m = new Ressource(sem, code, libLong, libCourt, heurePonctuel);
				if (type.equals("Sae"))
					m = new Sae(sem, code, libLong, libCourt, heurePonctuel);
				if (type.equals("Stage"))
					m = new Stage(sem, code, libLong, libCourt, heurePonctuel);
				if (type.equals("PPP"))
					m = new PPP(sem, code, libLong, libCourt, heurePonctuel);

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

	// Méthode GET
	public static ArrayList<Intervenants> getIntervenants() {
		return Etat.lstIntervenants;
	}


	public static ArrayList<Module> getModules() {
		return Etat.lstModule;
	}

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

	public static Module getModule(String code) {
		for (Module m : Etat.lstModule)
			if (m.getCode().equals(code))
				return m;

		return null;
	}

	public static void ajouterCategorieIntervenant(CategorieIntervenant categorieIntervenant) {
		Etat.lstCategorieIntervenants.add(categorieIntervenant);
	}

	

	/*--------------------------------------------------------------*/
	/* LIAISON 3 */
	/*--------------------------------------------------------------*/

	// Méthode CREATE
	public static void genererAffectations() {

		Etat.lstAffectations = new ArrayList<>();

		try {
			Statement st = connec.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM Affectation"+ Etat.nom);

			while (res.next()) {

				Intervenants inter = Etat.getIntervenant(res.getString("intNom"), res.getString("intPrenom"));
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
	
	public static void ajouterAffectation(Affectations affect) {
		Etat.lstAffectations.add(affect);
	}

	public static ArrayList<Affectations> getAffectations() {
		return Etat.lstAffectations;
	}

	public static Affectations getAffectations(int i){
		return Etat.lstAffectations.get(i);
	}

	public static Affectations getAffectations(String nomInter) {
		for (Affectations i : Etat.lstAffectations)
			if (i.getIntervenant().getNomIntervenant().equals(nomInter))
				return i;

		return null;
	}
	/*--------------------------------------------------------------*/
	/* ACTIONS */
	/*--------------------------------------------------------------*/

	public static void ajouterAction(Action a) {
		Etat.lstActions.add(a);
	}

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



	/*--------------------------------------------------------------*/
	/* CREATION ET VERIFICATIONS DES TABLES */
	/*--------------------------------------------------------------*/

	private static void recupererNomEtat()
	{
		try {
			//On regarde si la table Etat existe 

			Statement st = connec.createStatement();

			//Si elle existe pas on la crée
			st.executeUpdate("CREATE TABLE IF NOT EXISTS Etat (etat  VARCHAR(25) PRIMARY KEY,dateCrea DATE DEFAULT CURRENT_DATE)");

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
	
	public static String[] getEtats() {

		List<String> etatsList = new ArrayList<>();
		try {

			Statement st = Etat.connec.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM Etat ORDER BY datecrea DESC");

			while (res.next())
				etatsList.add(res.getString("etat"));

		} catch (Exception e) {
			// TODO: handle exception
		}

		String[] etatsArray = new String[etatsList.size()];
		return etatsList.toArray(etatsArray);
	}
	
	private static void lireFichierSQL(String fic) {
		try {

			String commande = "";
			Scanner scan = new Scanner(new FileInputStream(fic));
			Statement statement = Etat.connec.createStatement();

			while (scan.hasNextLine()) {

				String l = scan.nextLine();

				if (!(l.contains("/*") || l.contains("*/") || l.contains("*") || l.contains("--"))) {
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

	public static void dupliquerEtat (String etatDest, String etatDep)
	{
		try {
			Statement st = Etat.connec.createStatement();

			for(String tables : Etat.LST_NOM_TABLES) {
				System.out.println("CREATE TABLE " + tables + etatDest + " AS TABLE " + tables + etatDep);
				st.executeUpdate("CREATE TABLE " + tables + etatDest + " AS TABLE " + tables + etatDep );
			}
			
			Etat.verifEtat();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public static void changerEtat (String nom) {
		Etat.nom = nom;
		System.out.println(Etat.nom);
		Etat.lireFichierSQL(Etat.FICHIER);
		Etat.genererInfos();
	}

	public static boolean creerEtat (String nom) {

		for (String etatsNom : Etat.getEtats())
			if (etatsNom.equals(nom)) return false;


		try {
			Statement st = Etat.connec.createStatement();
			st.executeUpdate("INSERT INTO Etat (etat) VALUES ('"+nom+"')");

			Etat.nom = nom;
			Etat.lireFichierSQL(Etat.FICHIER);
			Etat.verifEtat();
			Etat.genererInfos();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	private static void verifEtat () {
		if (Etat.getEtats().length > 5) {
			Etat.suppEtat(Etat.getEtats()[5]);
		}
	}

	public static boolean suppEtat (String nom) {

		if (Etat.nom.equals(nom)) return false;

		try {
			Statement st = Etat.connec.createStatement();


			for (String s : Etat.LST_NOM_TABLES)
				st.executeUpdate("DROP TABLE " + s + nom.toLowerCase() + " CASCADE");

			st.executeUpdate("DELETE FROM Etat WHERE etat = '" + nom.toLowerCase() + "'");

			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
