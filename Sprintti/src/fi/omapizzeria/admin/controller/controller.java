package fi.omapizzeria.admin.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

import fi.omapizzeria.admin.bean.*;

/**
 * @Timo Jarmala
 */

/**
 * Servlet implementation class controller
 */
@WebServlet("/controller")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		AdminDao admintiedot = new AdminDao();

		boolean vahvistus = false;
		String Kayttajanimi = "";
		String Salasana = "";

		HttpSession sessio = request.getSession(false);
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {

			for (int i = 0; i < cookies.length; i++) {

				if ("kayttunnus".equals(cookies[i].getName())) {
					Kayttajanimi = cookies[i].getValue();
				}

				if ("password".equals(cookies[i].getName())) {
					Salasana = cookies[i].getValue();
				}

			}
		}

		if (Salasana.equals("") && Kayttajanimi.equals("")) {
			try {

				Kayttajanimi = (String) sessio.getAttribute("tunnus");
				Salasana = (String) sessio.getAttribute("salasana");

			} catch (Exception e) {

			}
		}

		try {

			vahvistus = admintiedot.vahvistaTunnus(Salasana, Kayttajanimi);

			if (vahvistus == true) {

			}

			else if(vahvistus == false) {
				request.getRequestDispatcher("Login.jsp").forward(request,
						response);
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("Login.jsp")
					.forward(request, response);
		}

		int noofPizzas, pizzasperPage, page, nextIndex, noofPages, startindex;

		PizzaDAO kanta = new PizzaDAO();
		TayteDAO taytehallinta = new TayteDAO();

		noofPizzas = kanta.getnoofPizzas();
		pizzasperPage = 10;
		page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		}

		catch (Exception e) {

		}

		if (request.getParameter("page") == null) {
			page = 1;

		}

		String v = (String) request.getAttribute("visible");

		System.out.println(v);
		noofPages = 0;
		startindex = 2;
		nextIndex = (page - 1) * pizzasperPage;
		double jakojaanos = (double) noofPizzas % pizzasperPage;
		System.out.println("jakoj��nn�s " + jakojaanos);
		if (jakojaanos > 0) {

			noofPages = noofPizzas / pizzasperPage + 1;
		}

		else if (jakojaanos == 0)

		{
			noofPages = noofPizzas / pizzasperPage;
		}

		List<Pizza> pizzalista = kanta.haePizzat(nextIndex, pizzasperPage);
		List<Tayte> taytelista = taytehallinta.haeTaytteet();

		if (noofPages > 1) {
			startindex = 1;
		}

		request.setAttribute("currentpage", page);
		request.setAttribute("startindex", startindex);
		request.setAttribute("taytelista", taytelista);
		request.setAttribute("noofPages", noofPages);
		request.setAttribute("lista", pizzalista);
		
		if(vahvistus==true){
		request.getRequestDispatcher("list.jsp").forward(request, response);}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		

		ArrayList<Pizza> pizzalista;
		AsiakasDAO asiakastiedot=new AsiakasDAO();
		AdminDao admintiedot = new AdminDao();

		
		/**
		 * Otetaan parametrit login.jsp:st� vastaan.
		 */
		
		String salattavaTeksti = request.getParameter("Salasana");
		String Kayttajanimi = request.getParameter("Kayttajanimi");
		String muisti = request.getParameter("memory");
		String Salasana=null;
		Boolean vahvistus = false;
		boolean kayttvahvistus=false;
		boolean asiakasvahvistus = false;
		HttpSession sessio = request.getSession(false);
		
		admintiedot.checkUser(Kayttajanimi);
		/**
		 * Tarkistetaan onko kirjautuja admin vai k�ytt�j�. Jos kirjautuja on admin, salataan salasana ja 
		 * k�yd��nn admin tunnistus l�pi
		 */
		
		
		
		if (kayttvahvistus==true) {
			
				
		
		if (request.getParameter("Kayttajanimi") != null) {
			
			Salasana=admintiedot.salaaTeksti(salattavaTeksti, Kayttajanimi);
			vahvistus = admintiedot.vahvistaTunnus(Salasana, Kayttajanimi);
		}
		}
		
		/**
		 * Jos kirjautuja on asiakas, salataan salasana ja k�yd��n asiakastunnistus l�pi
		 */
		
		
		
		else 
			
		{
			
			Salasana=asiakastiedot.salaaTeksti(salattavaTeksti, Kayttajanimi);	
			
			
			asiakasvahvistus=asiakastiedot.vahvistaTunnus(Salasana, Kayttajanimi);
			
			/**
			 * Jos asiakasvahvistus on true luodaan sessio, johon talletetaan asiakkaan tunnukset.
			 * Jos kirjautuja on merkinnyt muista minut-toiminnon, tunnukset talletetaan ev�steisiin.
			 * Ohjataan lopuksi Asiakascontroller:lle.
			 */
			

			if (asiakasvahvistus == true) {
				
				System.out.println("Pit�is toimia Kaytta "+Kayttajanimi);
				System.out.println("Salasana"+Salasana);
				
				sessio = request.getSession(true);
				sessio.setAttribute("tunnus", Kayttajanimi);
				sessio.setAttribute("salasana", Salasana);
				if (muisti != null) {

					Cookie ck = new Cookie("kayttunnus", Kayttajanimi);
					ck.setMaxAge(60 * 60 * 24 * 365);
					response.addCookie(ck);

					ck = new Cookie("password", Salasana);
					ck.setMaxAge(60 * 60 * 24 * 365);
					response.addCookie(ck);
					
				}
				response.sendRedirect("/Sprintti/AsiakasController");
			}
			
			/**
			 * Jos tunnistautuminen on v��rin ohtajaan takaisin login.jsp:lle
			 */
			

			else {
				request.getRequestDispatcher("Login.jsp")
						.forward(request, response);

			}
			
		}
			
		/**
		 * Jos adminvahvistus on true luodaan sessio, johon talletetaan asiakkaan tunnukset.
		 * Jos kirjautuja on merkinnyt muista minut-toiminnon, tunnukset talletetaan ev�steisiin.
		 * Ohjataan lopuksi controller:lle.
		 */
		
		if (vahvistus == true) {

			sessio = request.getSession(true);
			sessio.setAttribute("tunnus", Kayttajanimi);
			sessio.setAttribute("salasana", Salasana);
			if (muisti != null) {

				Cookie ck = new Cookie("kayttunnus", Kayttajanimi);
				ck.setMaxAge(60 * 60 * 24 * 365);
				response.addCookie(ck);

				ck = new Cookie("password", Salasana);
				ck.setMaxAge(60 * 60 * 24 * 365);
				response.addCookie(ck);

			}



		else {
			request.getRequestDispatcher("Login.jsp")
					.forward(request, response);

		}}

		if(vahvistus==true){
		response.sendRedirect("/Sprintti/controller?added=true");
		}
	}
}
