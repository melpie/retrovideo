package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import be.vdab.dao.VideoDAO;
import be.vdab.entities.Film;

@WebServlet("/mandje.htm")
public class FilmBestellenServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/filmbestellen.jsp";
	private static final String REDIRECT_URL = "/mandje.htm";
	private final VideoDAO videoDAO = new VideoDAO();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			@SuppressWarnings("unchecked")
			Set<Long> filmNrsInMandje = (Set<Long>) session.getAttribute("mandje");
			if (filmNrsInMandje != null) {
				List<Film> filmsInMandje = new ArrayList<>();
				for (long filmNr : filmNrsInMandje) {
					filmsInMandje.add(videoDAO.findFilmByID(filmNr));
				}
			request.setAttribute("filmsInMandje", filmsInMandje);
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("filmid") != null) {
			
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Set<Long> filmNrsInMandje = (Set<Long>) session.getAttribute("mandje");
			
			if (filmNrsInMandje == null) {
				filmNrsInMandje = new LinkedHashSet<>();
			}
			
			try {
				filmNrsInMandje.add(Long.parseLong(request.getParameter("filmid")));
				session.setAttribute("mandje", filmNrsInMandje);
			} catch (Exception ex) {
				// een hacker heeft in de parameter nummer niet-getallen geplaatst
			}
			
		} else if (request.getParameterValues("verwijderFilmNrs") != null) {
			
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Set<Long> filmNrsInMandje = (Set<Long>) session.getAttribute("mandje"); 
			
			for (String nummerAlsString:request.getParameterValues("verwijderFilmNrs")){
				try {
					long nummer = Long.parseLong(nummerAlsString);
					filmNrsInMandje.remove(nummer);
					session.setAttribute("mandje", filmNrsInMandje);
				} catch (Exception ex) {
					
				}				
			}
			
		}
		
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + REDIRECT_URL));
		
	}

}
