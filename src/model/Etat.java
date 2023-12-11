package model;

import java.sql.*;
import java.util.ArrayList;

public class Etat
{
	private static Connection connec;

	private static ArrayList<CategorieHeures> lstCategorieHeures;


	public Etat()
	{
		try {
			Class.forName("org.mysql.Driver");
			// Connection
			Etat.connec = DriverManager.getConnection("jdbc:mysql://localhost:3306/Astre", "root", "");

			// Générer les deux catégories
			Etat.genererCategorieHeures();

		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("SQL Error: " + e.getMessage());
		}
	}
	
	private static void genererCategorieHeures ()
	{
		try 
		{
			Statement st = connec.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM CategorieHeures");

			while (res.next())
				

			res.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<CategorieHeures> getCategoriesHeures() { return Etat.lstCategorieHeures; }
}
