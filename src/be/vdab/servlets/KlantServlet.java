package be.vdab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import be.vdab.dao.VideoDAO;

@WebServlet("/klant.htm")
public class KlantServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/klant.jsp";
	private final VideoDAO videoDAO = new VideoDAO();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String familieNaam = request.getParameter("familienaam");
		
		if (familieNaam == "") {
			request.setAttribute("fout", "Tik minsten één letter.");
		} else if(familieNaam != null) {
			request.setAttribute("klanten", videoDAO.findKlantenBySearchString(familieNaam));
		} 
	
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
}
