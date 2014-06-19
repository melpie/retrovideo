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
		
		<h1>Reservaties</h1>
		
		<nav>
			
			<ul class=genreMenu>
			
				<c:forEach var="genre" items="${genres}" varStatus="genreStatus">
				
					<c:url value="/reservaties/filmspergenre.htm" var="genreURL">
						<c:param name="genreid" value="${genre.id}"/>
					</c:url>
					
					<c:choose>
						<c:when test="${genre.id==huidiggenre}">
							<li>${genre.naam}</li>
						</c:when>
						<c:otherwise>
							<li><a href="<c:out value='${genreURL}'/>">${genre.naam}</a></li>
						</c:otherwise>
					</c:choose>
							           
		 		</c:forEach>
	 		
	 		</ul>	 		
 		
 		</nav>
 		
 		<c:choose>
 		
 			<c:when test="${not empty foutGenreid}">
 				${foutGenreid} Kies een genre.
 			</c:when>
 		
 			<c:otherwise>
 			
		 		<div class=filmContent>
		 		
			 		<c:forEach var="film" items="${films}" varStatus="filmStatus">
			 			
			 			<div class="floatedImage">
			 			
				 			<c:url value="/images/${film.id}.jpg" var="filmImage"/>
				 			
				 			<c:url value="/film.htm" var="filmURL">
								<c:param name="filmid" value="${film.id}"/>
							</c:url>
				 			
				 			<c:choose>
									<c:when test="${film.voorraad==film.gereserveerd}">
										<a href="<c:out value='${filmURL}'/>">
											<img src="${filmImage}" title="${film.titel}  (reservatie niet mogelijk)" alt="${film.titel}"/>
										</a>							
									</c:when>
									<c:otherwise>
										<a href="<c:out value='${filmURL}'/>">
											<img src="${filmImage}" title="${film.titel} (reservatie mogelijk)" alt="${film.titel}"/>
										</a>
									</c:otherwise>
							</c:choose>
							
						</div>
					    
			 		</c:forEach>
			 		
			 	</div>
			 	
			 </c:otherwise>
			 	
		</c:choose>
	
	</body>
	
</html>