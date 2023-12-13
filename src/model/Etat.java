package model;

import java.sql.*;
import java.util.ArrayList;

import model.modules.Module;
import model.modules.Ressource;
import model.modules.Sae;
import model.modules.Stage;

public class Etat {
	private static Connection connec;
	// private static String name;

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

	public Etat(String name) {

		// Etat.name = name;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Connection
			Etat.connec = DriverManager.getConnection("jdbc:mysql://localhost:3306/astre", "root", "");

			// Générer les premières tables
			Etat.genererCategorieHeures();
			Etat.genererCategorieIntervenants();
			Etat.genererSemestres();

			// Générer les deuxièmes tables
			Etat.genererIntervenants();
			Etat.genererModules();

			// Générer les troisièmes tables
			Etat.genererAffections();

		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("SQL Error: " + e.getMessage());
		}
	}

	/*--------------------------------------------------------------*/
	/* LIAISON 0 */
	/*--------------------------------------------------------------*/

	// CREATE
	private static void genererCategorieHeures() {

		Etat.lstCategorieHeures = new ArrayList<>();

		try {

			Statement st = connec.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM CategorieHeures");

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
			ResultSet res = st.executeQuery("SELECT * FROM CategorieIntervenants");

			while (res.next()) {
				String code = res.getString("codeCatInt");
				String lib = res.getString("libCatInt");
				float coef = res.getFloat("coefCatInt");
				int hmin = res.getInt("heureMinCatInt");
				int hmax = res.getInt("heureMaxCatInt");

				Etat.lstCategorieIntervenants.add(new CategorieIntervenant(code, lib, coef, hmax, hmin));
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
			ResultSet res = st.executeQuery("SELECT * FROM Semestres");

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

		System.out.println(nom);
		for (CategorieIntervenant c : Etat.lstCategorieIntervenants)
			if (c.getLibCatInt().equals(nom))
				return c;

		return null;
	}

	public static CategorieHeures getCatHeure(String nom) {
		for (CategorieHeures c : Etat.lstCategorieHeures)
			if (c.getlibCatHeur().equals(nom))
				return c;

		return null;
	}

	/*--------------------------------------------------------------*/
	/* LIAISON 1 */
	/*--------------------------------------------------------------*/

	// Méthode CREATE
	public static void genererIntervenants() {

		Etat.lstIntervenants = new ArrayList<>();

		try {
			Statement st = connec.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM Intervenants");

			while (res.next()) {
				String nom = res.getString("nomInt");
				String prenom = res.getString("prenomInt");
				int hmin = res.getInt("heureMinInt");
				int hmax = res.getInt("heureMaxInt");

				CategorieIntervenant cat = Etat.getCatInt(res.getString("categInt"));

				Etat.lstIntervenants.add(new Intervenants(cat, nom, prenom, hmin, hmax));
			}

			res.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void genererModules() {

		Etat.lstModule = new ArrayList<>();

		try {
			Statement st = connec.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM Modules");

			while (res.next()) {
				Module m = null;

				String type = res.getString("typeMod");

				String code = res.getString("codeMod");
				String libLong = res.getString("libLongMod");
				String libCourt = res.getString("libCourtMod");
				int heurePonctuel = res.getInt("nbHeurPonc");

				Semestres sem = Etat.lstSemestres.get(res.getInt("semMod") - 1);

				System.out.println(type);

				if (type.equals("Ressource"))
					m = new Ressource(sem, code, libLong, libCourt, heurePonctuel);
				if (type.equals("Sae"))
					m = new Sae(sem, code, libLong, libCourt, heurePonctuel);
				if (type.equals("Stage"))
					m = new Stage(sem, code, libLong, libCourt, heurePonctuel);
				if (type.equals("PPP"))
					m = new Stage(sem, code, libLong, libCourt, heurePonctuel);

				Statement st1 = connec.createStatement();
				ResultSet res1 = st1.executeQuery("SELECT * FROM ModulesCatHeures WHERE codeMod = '" + code + "'");

				while (res1.next()) {

					CategorieHeures catH = Etat.getCatHeure(res1.getString("libCatHeur"));
					int heurePn = res1.getInt("nbHeurePN");
					int heureSem = res1.getInt("nbHeureSem");
					int nbSem = res1.getInt("nbSemaine");

					m.initList(heurePn, nbSem, heureSem, heurePonctuel, catH);
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

	public static Module getModule(String code) {
		for (Module m : Etat.lstModule)
			if (m.getCode().equals(code))
				return m;

		return null;
	}

	/*--------------------------------------------------------------*/
	/* LIAISON 3 */
	/*--------------------------------------------------------------*/

	// Méthode CREATE
	public static void genererAffections() {

		Etat.lstAffectations = new ArrayList<>();

		try {
			Statement st = connec.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM Affectation");

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
	
	public static ArrayList<Affectations> getAffectations() {return Etat.lstAffectations;}




	public static void main(String[] args) {
		new Etat("hey");

		for (Intervenants i : Etat.getIntervenants())
			System.out.println(i);

		for (Affectations a : Etat.getAffectations())
			System.out.println(a);

		System.out.println();
	}
}
