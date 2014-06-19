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
					
				<li><a href="<c:out value='${reserverenURL}'/>">Reserveren</a></li>
				<li><a href="<c:out value='${mandjeURL}'/>">Mandje</a></li>
							           
	 		</ul>	 		
 		
 		</nav>
 		
 		<h1>Klant</h1> 	
 		
 		<c:url value="/klant.htm" var="klantURL"/>
 		
 		<form action="${klantURL}" method="get">
 		
	 		Familienaam bevat:<br><br>
	 		<input type="text" name="familienaam"><br><br>
	 		<input type="submit" value="Zoeken"/>
 		 		
 		</form>
 		
 		<br>
 		
 		<c:if test="${not empty klanten}">
 		
 			<table cellspacing='0'> 
									
				<thead>
					<tr>
						<th>Naam</th>
						<th>Straat - Huisnummer</th>
						<th>Postcode</th>
						<th>Gemeente</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="klant" items="${klanten}">
						
						<c:url value="/bevestigen.htm" var="bevestigenURL">
							<c:param name="klantid" value="${klant.id}"/>	
						</c:url>
						
						<tr>
							<td><a href="<c:out value='${bevestigenURL}'/>">${klant.familienaam} ${klant.voornaam}</a></td>
							<td>${klant.straatNummer}</td>
							<td>${klant.postcode}</td>
							<td>${klant.gemeente}</td>
						</tr>
					</c:forEach>
				</tbody>
				
			</table>
 		
 		</c:if>
		
	</body>	

</html>