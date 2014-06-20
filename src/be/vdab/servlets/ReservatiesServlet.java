package be.vdab.servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import be.vdab.dao.VideoDAO;

/**
 * Servlet implementation class ReservatiesServlet
 */
@WebServlet("/reservaties.htm")
public class ReservatiesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final VideoDAO videoDAO = new VideoDAO();
	private static final String VIEW = "/WEB-INF/JSP/reservaties.jsp";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("reservaties", videoDAO.getReservaties());
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}

}
