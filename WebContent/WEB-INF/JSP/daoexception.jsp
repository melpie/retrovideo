<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>

<html lang="nl">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>RETRO VIDEO</title>
		<c:url value="/styles/default.css" var="URLDefaultCSS"/>
        <link rel="stylesheet" href="${URLDefaultCSS}"/>
	</head>
	
	<body>
		
		<nav>
			
			<ul class=genreMenu>
				<c:url value="/reservaties.htm" var="reserverenURL"/>	
				<li><a href="<c:out value='${reserverenURL}'/>">Reserveren</a></li>
			</ul>	 		
 		
 		</nav>
 		
		<h1>Problemen bij het ophalen van data</h1>
		
		<c:url value="/images/datafout.jpg" var="image"/>
		<img src="${image}" alt="data fout"/>
		
		<p>We kunnen de gevraagde data niet ophalen
		wegens een technische storing.<br/>
		Gelieve de helpdesk te contacteren.</p>
		
	</body>

</html>