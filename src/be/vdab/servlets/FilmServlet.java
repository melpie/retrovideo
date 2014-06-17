package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.VideoDAO;

/**
 * Servlet implementation class FilmServlet
 */
@WebServlet("/film.htm")
public class FilmServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final VideoDAO videoDAO = new VideoDAO();
	private static final String VIEW = "/WEB-INF/JSP/film.jsp";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Long filmid = Long.parseLong(request.getParameter("filmid"));		
			request.setAttribute("film", videoDAO.findFilmByID(filmid));
						
		} catch (NumberFormatException ex) {
            request.setAttribute("fout", "filmid niet correct");
        }
		
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}

}
