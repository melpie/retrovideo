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
				<li><a href="<c:out value='${reserverenURL}'/>">Reserveren</a></li>	
	 		</ul>	 		
 		
 		</nav>
 		
 		<h1>Rapport</h1>
 		
 		De reservatie is OK.

	</body>
	
</html>