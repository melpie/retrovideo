<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>RETRO VIDEO</title>
		
		<c:url value="/styles/default.css" var="URLDefaultCSS"/>
        <link rel="stylesheet" href="${URLDefaultCSS}"/>
	</head>
	
	<body>
		
		<nav>
			
			<ul class=genreMenu>
			
				<c:url value="/reserveren.htm" var="reserverenURL"/>
				<c:url value="/klant.htm" var="klantURL"/>
					
				<li><a href="<c:out value='${reserverenURL}'/>">Reserveren</a></li>
				<li><a href="<c:out value='${klantURL}'/>">Klant</a></li>
							           
	 		</ul>	 		
 		
 		</nav>
 		
 		<c:choose>
 		
 			<c:when test="${not empty foutSession}">
 				${foutSession} Ga terug naar Reserveren pagina en kies een film.
 			</c:when>
 		
 			<c:otherwise>
 		
		 		<h1>Mandje</h1> 		
		 		
		 		<c:url value="/mandje.htm" var="mandjeURL"/>
		 		
		 		<form action="${mandjeURL}" method="post">
		 		
			 		<table cellspacing='0'> 
											
						<thead>
							<tr>
								<th>Film</th>
								<th>Prijs</th>
								<th><input type="submit" value="Verwijderen"/></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="film" items="${filmsInMandje}">
								<tr>
									<td>${film.titel}</td>
									<td>&euro; ${film.prijs}</td>
									<td>
										<label><input type="checkbox" name="verwijderFilmNrs" value="${film.id}"></label>
									</td>
								</tr>
							</c:forEach>
						</tbody>
						
					</table>
		 		
		 		</form>
		 		
		 	</c:otherwise>	
		 		
		 </c:choose>
 		
	</body>
	
</html>