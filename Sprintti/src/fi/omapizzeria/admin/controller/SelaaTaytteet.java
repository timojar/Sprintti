package fi.omapizzeria.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDao;
import dao.TayteDAO;
import fi.omapizzeria.admin.bean.Tayte;
/**
 * @Timo Jarmala
 */
/**
 * Servlet implementation class SelaaTaytteet
 */
@WebServlet("/SelaaTaytteet")
public class SelaaTaytteet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelaaTaytteet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		boolean vahvistus=false;
		
		HttpSession muistipizzasta= request.getSession(false);
		
		vahvistus= tunnistaKayttaja(request, response);
		
		if (vahvistus == true) {

		}

		else if (vahvistus == false) {
			request.getRequestDispatcher("Login.jsp").forward(request,
					response);
		}
		
		
		TayteDAO dao=new TayteDAO();	
		
		
		
		List<Tayte>taytteet=dao.haeTaytteet();
		
		
		
		
		request.setAttribute("taytteet", taytteet);
		
		request.getRequestDispatcher("WEB-INF/taytteet.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	
	
	
	
	
	private boolean tunnistaKayttaja(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		
		boolean vahvistus = false;
		AdminDao admintiedot = new AdminDao();
		String Kayttajanimi = "";
		String Salasana = "";
		
		HttpSession sessio = request.getSession(false);
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {

			for (int i = 0; i < cookies.length; i++) {

				if ("kayttunnus".equals(cookies[i].getName())) {
					String logged = "logged";
					Kayttajanimi = cookies[i].getValue();
					request.setAttribute("logged", logged);
					request.setAttribute("tunnus", Kayttajanimi);
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
			

		}

		catch (Exception e) {
			e.printStackTrace();
			
		}
	
		
		return vahvistus;
	}
	
	
	
	
	
	
	
}
