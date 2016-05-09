package fi.omapizzeria.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.ArrayList;

import dao.PizzaDAO;
import dao.TayteDAO;
import fi.omapizzeria.admin.bean.Tayte;

/**
 * Servlet implementation class lisaaPitsa
 */
@WebServlet("/lisaaPitsa")
public class lisaaPitsa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public lisaaPitsa() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TayteDAO taytehallinata = new TayteDAO();
		List<Tayte> taytelista = new ArrayList<Tayte>();
		double hinta = 0;
		String nimi = request.getParameter("nimi");
		String kuvaus = request.getParameter("kuvaus");
		String hintasana = request.getParameter("hinta");
		String tayteNimi;
		int pizzaId = 0;
		int tayteId = 0;
		boolean hintavirhe = false;
		boolean taytevirhe=false;
		String[] taytteet = request.getParameterValues("taytteet");
		
		try {
			for (int i = 0; i < taytteet.length; i++) {
				tayteId = Integer.parseInt(taytteet[i]);
				taytelista.add(taytehallinata.tuoTayte(tayteId));

			}
			
			if(taytteet.length>7){
				taytevirhe=true;
			}

		
		
		}
		catch(NullPointerException e){
			taytevirhe=true;
		}
	

		PizzaDAO kanta = new PizzaDAO();

		try {
			hinta = Double.parseDouble(hintasana);
		}

		catch (Exception e) {
			hintavirhe = true;
System.out.println("Hinta ei oo numero");
		}

		if (hintavirhe == false && taytevirhe== false) {

			pizzaId = kanta.lisaaPizza(nimi, hinta, taytelista);
			taytehallinata.luoPizzaTayte(nimi, pizzaId, taytelista);
			response.sendRedirect("/Sprintti/controller?added=true");
		}

		else if(hintavirhe==true){
			response.sendRedirect("/Sprintti/controller?hinta=true");
		}
		
		else if(taytevirhe==true ){
			response.sendRedirect("/Sprintti/controller?taytemaara=true");
		}
		
		
		
	}

}
