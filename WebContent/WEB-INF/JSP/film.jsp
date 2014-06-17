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
		
		<h1>
			${film.titel}
		</h1>
		
		<c:url value="/images/${film.id}.jpg" var="filmImage"/>
		
		<img src="${filmImage}" title="${film.titel}" alt="${film.titel}"/>
		
		<br><br>
		
		Prijs <br>
		<strong>&euro; ${film.prijs}</strong><br><br>
		
		Voorraad <br>
		<strong>${film.voorraad}</strong><br><br>
		
		Gereserveerd <br>
		<strong>${film.gereserveerd}</strong><br><br>
		
		Beschikbaar <br>
		<strong>${film.voorraad - film.gereserveerd}</strong>
		
		<br>
		
		
	</body>

</html>