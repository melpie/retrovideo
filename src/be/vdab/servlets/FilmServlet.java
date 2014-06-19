package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import be.vdab.dao.VideoDAO;
import be.vdab.entities.Film;

@WebServlet("/film.htm")
public class FilmServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final VideoDAO videoDAO = new VideoDAO();
	private static final String VIEW = "/WEB-INF/JSP/film.jsp";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Long filmid = Long.parseLong(request.getParameter("filmid"));	
			Film film = videoDAO.findFilmByID(filmid);
			if (film != null) {
				request.setAttribute("film", film);
			} else {
				request.setAttribute("foutFilmid", "Verkeerde filmid. Film niet gevonden in database.");
			}
		} catch (NumberFormatException ex) {
            request.setAttribute("foutFilmid", "Verkeerde filmid.");
        }
		
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}

}
