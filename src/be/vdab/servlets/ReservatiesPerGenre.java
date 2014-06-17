package be.vdab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import be.vdab.dao.VideoDAO;

/**
 * Servlet implementation class ReservatiesPerGenre
 */
@WebServlet("/reservaties/filmspergenre.htm")
public class ReservatiesPerGenre extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final VideoDAO videoDAO = new VideoDAO();
	private static final String VIEW = "/WEB-INF/JSP/filmspergenre.jsp";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long genre = Long.parseLong(request.getParameter("genre"));		
		request.setAttribute("films", videoDAO.findFilmsByGenre(genre));
		request.setAttribute("genres", videoDAO.getGenres());
		request.setAttribute("huidiggenre", genre);
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}


}
