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
	
		<c:url value="/reservaties.htm" var="reservatiesURL"/>
		<a href="<c:out value='${reservatiesURL}'/>">Reserveren</a>
		
		<br>
		
		<c:choose>
		
			<c:when test="${not empty foutFilmid}">
				${foutFilmid} Ga terug naar pagina Reserveren en kies een film. 
			</c:when>
				
			<c:otherwise>
				
				<h1>
					${film.titel}
				</h1>

				<c:url value="/images/${film.id}.jpg" var="filmImage"/>
				
				<c:choose>
					<c:when test="${film.voorraad==film.gereserveerd}">
						<img src="${filmImage}" title="${film.titel}  (reservatie niet mogelijk)" alt="${film.titel}"/>
					</c:when>
					<c:otherwise>
						<img src="${filmImage}" title="${film.titel} (reservatie mogelijk)" alt="${film.titel}"/>
					</c:otherwise>
				</c:choose>
				
				<br><br>
				
				Prijs <br>
				<strong>&euro; ${film.prijs}</strong><br><br>
				
				Voorraad <br>
				<strong>${film.voorraad}</strong><br><br>
				
				Gereserveerd <br>
				<strong>${film.gereserveerd}</strong><br><br>
				
				Beschikbaar <br>
				<strong>${film.voorraad - film.gereserveerd}</strong>
				
				<br><br>
				
				<c:url value="/mandje.htm" var="mandjeURL">
					<c:param name="filmid" value="${film.id}"/>
				</c:url>
				
				<c:if test="${film.voorraad>film.gereserveerd}">
					<form action="${mandjeURL}" method="post">
						<input type="submit" value="In mandje" id="filminmandje"/>
					</form>
				</c:if>
				
				
			</c:otherwise>	
				
		</c:choose>		
		
	</body>

</html>