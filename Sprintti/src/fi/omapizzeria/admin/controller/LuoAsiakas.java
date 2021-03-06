package fi.omapizzeria.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AsiakasDAO;
import fi.omapizzeria.admin.bean.Asiakas;
import fi.omapizzeria.admin.bean.KantaAsiakas;
/**
 * @Timo Jarmala
 */
/**
 * Servlet implementation class LuoAsiakas
 */
@WebServlet("/LuoAsiakas")
public class LuoAsiakas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LuoAsiakas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		
		
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String etunimi=request.getParameter("enimi");
		String sukunimi=request.getParameter("snimi");
		String email=request.getParameter("email");
		String toimosoite=request.getParameter("osoite");
		String postitmp=request.getParameter("tmp");
		String postinro=request.getParameter("postinro");
	
		
		String salattavaTeksti=request.getParameter("salasana");
		String puhelinstr=request.getParameter("numero");
		boolean kayttvahvistus=true;

		
		
		AsiakasDAO asiakashallinta=new AsiakasDAO();
		kayttvahvistus=asiakashallinta.checkUser(email);
		if(kayttvahvistus==false){
		KantaAsiakas kasiakas=asiakashallinta.luoAsiakas(etunimi, sukunimi, email, salattavaTeksti, puhelinstr, toimosoite, postitmp, postinro);
		
		
		HttpSession sessio = request.getSession(true);
		sessio.setAttribute("asiakasnumero",kasiakas.getId());
		String logged="logged";
		sessio.setAttribute("logged", logged);
		sessio.setAttribute("etunimi", kasiakas.getEtunimi());
		sessio.setAttribute("sukunimi", kasiakas.getSukunimi());
		sessio.setAttribute("asiakas", kasiakas);
		sessio.setAttribute("tunnus", kasiakas.getEmail());
		sessio.setAttribute("salasana", kasiakas.getSalasana());
	
		response.sendRedirect("/Sprintti/menuController");
	}
		
		else{
			response.sendRedirect("/Sprintti/LuoAsiakas?email=true");
		}
	
	
	}

}
