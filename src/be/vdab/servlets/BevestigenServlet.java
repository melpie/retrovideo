package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import be.vdab.dao.VideoDAO;
import be.vdab.entities.Film;
import be.vdab.entities.Klant;

@WebServlet("/bevestigen.htm")
public class BevestigenServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/bevestigen.jsp";
	private static final String REDIRECT_URL = "/rapport.htm";
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
				request.setAttribute("aantal", filmsInMandje.size());
			}
		} else {
			request.setAttribute("foutSession", "Mandje niet gevuld.");		
		}
					
		try {
			String klantid = request.getParameter("klantid");
			Klant klant = null;
			if (klantid != null) {		
				klant = videoDAO.findKlantByID(Long.parseLong(klantid));
				if (klant != null) {
					request.setAttribute("klant", klant);
				} else {
					request.setAttribute("foutKlantid", "Verkeerde klantid. Klant niet gevonden in database.");
				}
			} else {
				request.setAttribute("foutKlantid", "Geen klantid.");
			}
		} catch (NumberFormatException ex) {
	        request.setAttribute("foutKlantid", "Verkeerde klantid.");
	    }
		
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		
		if (request.getParameter("klantid") != null & session != null) {
			@SuppressWarnings("unchecked")
			Set<Long> filmNrsInMandje = (Set<Long>) session.getAttribute("mandje");
			try {
				if (filmNrsInMandje != null) {
					for (long filmNr : filmNrsInMandje) {
						videoDAO.createReservatie(Long.parseLong(request.getParameter("klantid")),filmNr);
					}
				}
			} catch (NumberFormatException ex) {
	            request.setAttribute("fout", "klantid niet correct");
	        }
			
			session.invalidate();
		
		}
		
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + REDIRECT_URL));
		
	}

}
