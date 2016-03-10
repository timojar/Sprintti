package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import fi.omapizzeria.admin.bean.Pizza;

public class PizzaDAO {

	private static Pizza pizza;

	private static ArrayList<Pizza> pizzalista, selaus;
	
	private int noofPizzas , nextIndex, pizzasperPage;
	
	
	
	
	
	
	
	public int getnoofPizzas (){
		
		Connection conn;
		
		ConnectionFactory yhteys = new ConnectionFactory();	
		
		conn = yhteys.getConnection();

		
		
		try {
			String sql = "select * from Pizza";

			Statement lkmHaku = conn.createStatement();

			ResultSet lkm = lkmHaku.executeQuery(sql);	
			
			while (lkm.next()) {
				
			 noofPizzas=lkm.getRow();
				 
				
			}
			
			
			
		}
		
		catch (Exception e){
		System.out.println("Virhe");	
			
			
			
		}
		
		finally{
			yhteys.suljeYhteys(conn);
		}
		
		
	return noofPizzas;	
	}
	
	

	public  ArrayList<Pizza> haePizzat(int nextIndex, int pizzasperPage) 
	
	
	{

		ConnectionFactory yhteys = new ConnectionFactory();
		pizzalista = new ArrayList<Pizza>();

		Connection conn;

		conn = yhteys.getConnection();

		try {

			String sql = "select SQL_CALC_FOUND_ROWS * from Pizza limit "+nextIndex+","+pizzasperPage
	                 ;
			
			System.out.println(sql);

			Statement haku = conn.createStatement();
				
			ResultSet hakutulokset = haku.executeQuery(sql);

			while (hakutulokset.next()) {

				int id = hakutulokset.getInt("id");

				String nimi = hakutulokset.getString("nimi");

				double hinta = hakutulokset.getDouble("hinta");

				pizzalista.add(new Pizza(id, nimi, hinta));

			}

		} catch (SQLException e) {

			e.printStackTrace();

			System.out.println("Tietokantahaku aiheutti virheen");

		}

		finally {
			yhteys.suljeYhteys(conn);
		}

		return pizzalista;
	}

	
	
	
	
	public static void lisaaPizza(String nimi, double hinta) {

		ConnectionFactory yhteys = new ConnectionFactory();
		pizzalista = new ArrayList<Pizza>();

		Connection conn;

		conn = yhteys.getConnection();

		int pizzalkm=0;
		
		
		
		try {
			
			String sql = "select * from Pizza";
			Statement lkmHaku = conn.createStatement();
			ResultSet lkm = lkmHaku.executeQuery(sql);	
			
			while (lkm.next()) {
			 pizzalkm=lkm.getRow();
			}
			
			int id=pizzalkm+1;
			
			String sqlInsert = "INSERT INTO pizza(id, nimi, hinta) VALUES (?, ?, ?)";
			PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert);
			stmtInsert.setInt(1, id);
			stmtInsert.setString(2, nimi);
			stmtInsert.setDouble(3, hinta);
			stmtInsert.executeUpdate();

		}

		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Lis�minen ei onnistunut");
		}

		finally {

			yhteys.suljeYhteys(conn);

		}

	}

	/**
	 * pizzanpoisto: Otetaan Pizza id vastaan parametrina
	 */
	public static void poistaPizza(int id) {

		ConnectionFactory yhteys = new ConnectionFactory();
		Connection conn;

		conn = yhteys.getConnection();

		/**
		 * pizzanpoisto: Yritt�m�ll� poistetaan pizza ja sen Pizza-id numeroita
		 * pit�� j�rjstell� uudelleen.
		 */

		try {
			selaus = new ArrayList<Pizza>();

			String sqldelete = "delete from pizza where id=?";
			PreparedStatement stmtdelete = conn.prepareStatement(sqldelete);
			stmtdelete.setInt(1, id);
			stmtdelete.executeUpdate();

			/**
			 * pizzanpoisto: Haetaan pizzalista tietokannasta selaamalla, jotta
			 * voidaan j�rjestell� id-numeroita uudelleen.
			 */

			String sql = "select * from Pizza";

			Statement haku = conn.createStatement();

			ResultSet hakutulokset = haku.executeQuery(sql);

			while (hakutulokset.next()) {

				id = hakutulokset.getInt("id");

				String nimi = hakutulokset.getString("nimi");

				double hinta = hakutulokset.getDouble("hinta");

				selaus.add(new Pizza(id, nimi, hinta));

			}

			/**
			 * pizzanpoisto: K�yd��n pizzat loopissa l�pi. odlID ottaa seuraavan
			 * numeron ja laittaa sit� edellisen numeron tilalle
			 */

			int t = 0;

			while (t < selaus.size()) {

				t++;

				/**
				 * Edelliset tunnukset ei muutu, koska id on primary key ja
				 * kahta samaa tunnusta ei voi olla. Se aiheuttaa poikkeuksen ja
				 * se poikkeus saadaan catch-lauseella kiinni. T�m�n ansioista
				 * ohjelma ei kaadu vaan jatkaa muiden tunnuksien muuttamista.
				 * 
				 */

				try {
					int oldId = t + 1;
					int newId = t;

					System.out.println(newId + " " + oldId + " tilalle");

					String sqlUpdate = "update Pizza set id= ? where id=?";
					PreparedStatement stmtUpdate = conn
							.prepareStatement(sqlUpdate);
					stmtUpdate.setInt(1, newId);
					stmtUpdate.setInt(2, oldId);
					stmtUpdate.executeUpdate();

				}

				catch (Exception e) {
					System.out.println("Edellinen tunnus on jo k�yt�ss�!");

					e.printStackTrace();

				}

			}

		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Poisto ei onnistunut");

		}

		finally {
			yhteys.suljeYhteys(conn);
		}

	}

}