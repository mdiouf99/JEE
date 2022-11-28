<%@ page language="java" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<link rel="stylesheet" href="<c:url value='/css/design.css'/>">
<script src="<c:url value='/js/script.js'/>"></script>
</head>
<body>
<div id="entete">Gestion des utilisateurs</div>
	<div id="menu">
		<ul>
			<c:choose>
				<c:when test="${ sessionScope.isConnected }">
					<li><a href="<c:url value='/list'/>">Lister</a></li>
					<li><a href="<c:url value='/add'/>">Ajouter</a></li>
					<li><a href="<c:url value='/logout'/>">Déconnexion</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="<c:url value='/login'/>">Connexion</a></li>
				</c:otherwise>
			</c:choose>
			
			
			
		</ul>
	</div>
