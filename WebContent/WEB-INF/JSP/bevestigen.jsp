<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>RETRO VIDEO</title>
		
		<%-- <link rel="stylesheet" href="${contextPath}/styles/default.css"/> --%>
		
		<c:url value="/styles/default.css" var="URLDefaultCSS"/>
        <link rel="stylesheet" href="${URLDefaultCSS}"/>
	</head>
	
	<body>
	
		<nav>
			
			<ul class=genreMenu>
			
				<c:url value="/reservaties.htm" var="reserverenURL"/>
				<c:url value="/mandje.htm" var="mandjeURL"/>
				<c:url value="/klant.htm" var="klantURL"/>
					
				<li><a href="<c:out value='${reserverenURL}'/>">Reserveren</a></li>
				<li><a href="<c:out value='${mandjeURL}'/>">Mandje</a></li>
				<li><a href="<c:out value='${klantURL}'/>">Klant</a></li>
							           
	 		</ul>	 		
 		
 		</nav>
 		
 		<c:choose>
 		
 			<c:when test="${not empty foutKlantid}">
 				${foutKlantid}
 				Ga terug naar Klant pagina en kies een klant.
 			</c:when>
 			<c:when test="${not empty foutSession}">
 				${foutSession}
 				Ga terug naar Reserveren pagina en kies een film.
 			</c:when>
 			<c:otherwise>
 			
		 		<h1>Bevestigen</h1>
		 		
		 		 		
		 		${aantal} 
		 		<c:choose>
		 			<c:when test="${aantal==1}">
		 				film
		 			</c:when>
		 			<c:otherwise>
		 				films
		 			</c:otherwise>
		 		</c:choose>
		 		voor ${klant.voornaam} ${klant.familienaam}:
		 		
		 		<table cellspacing='0'> 
											
					<thead>
						<tr>
							<th>Film</th>
							<th>Prijs</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="film" items="${filmsInMandje}">
							<tr>
								<td>${film.titel}</td>
								<td>&euro; ${film.prijs}</td>
							</tr>
						</c:forEach>
					</tbody>
						
				</table>
				
						<br><br>
				
				<c:url value="/bevestigen.htm" var="bevestigenURL">
					<c:param name="klantid" value="${klant.id}"/>
				</c:url>
				
				<form action="${bevestigenURL}" method="post">
					<input type="submit" value="Bevestigen"/>
				</form>
	
			</c:otherwise>
	
		</c:choose>
	
	</body>
	
</html>