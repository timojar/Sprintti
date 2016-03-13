package fi.omapizzeria.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MenuDao;
import fi.omapizzeria.admin.bean.Pizza;


/**
 * Servlet implementation class menuController
 */
@WebServlet("/menuController")
public class menuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public menuController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	MenuDao menukanta= new MenuDao();
	
	Pizza pizza=null;
	int id=0;
	String nimi, kuvaus;
	double hinta;
	List<Pizza>pizzalista=menukanta.haePizzat();
	List<Pizza>pizzamenu=new ArrayList<Pizza>();
	for(int i=0; i<pizzalista.size(); i++){
		
	pizza=pizzalista.get(i);
	
	nimi=pizza.getNimi();
	kuvaus=pizza.getKuvaus();
	hinta=pizza.getHinta();
	id=i+1;
	pizzamenu.add(new Pizza( id,  nimi,  hinta,  kuvaus));
		
	}
	
	request.setAttribute("menu", pizzamenu);
	
	request.getRequestDispatcher("menu.jsp").forward(request, response);
	
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
