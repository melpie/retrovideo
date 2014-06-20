package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.VideoDAO;

@WebServlet("/reserveren/filmspergenre.htm")
public class ReserverenPerGenreServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final VideoDAO videoDAO = new VideoDAO();
	private static final String VIEW = "/WEB-INF/JSP/filmspergenre.jsp";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("genres", videoDAO.getGenres());
		
		try {
			Long genre = Long.parseLong(request.getParameter("genreid"));		
			request.setAttribute("films", videoDAO.findFilmsByGenre(genre));
			request.setAttribute("huidiggenre", genre);
		} catch (NumberFormatException ex) {
	        request.setAttribute("foutGenreid", "Verkeerde genreid.");
	    }
			
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}

}
