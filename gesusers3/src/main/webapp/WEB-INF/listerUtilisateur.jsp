<%@page import="beans.Utilisateur"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des utilisateurs</title>
</head>
<body>
	<jsp:include page="inc/header.jsp"/>
<div id="corps">
	<h1 id="titre-principal">Liste des utilisateurs</h1>
	<c:choose>
		<c:when test="${ empty utilisateurs }">
					<p> La liste des utilisateurs est vide !</p>
			
		</c:when>
		
		<c:otherwise>
		
			<table border="1" cellspacing="0" cellpadding="10">
				<tr>
					<th>ID</th>
					<th>Prénom</th>
					<th>Nom</th>
					<th>Login</th>
					<th>Password</th>
					<th colspan="2">Action</th>
					
				</tr>
				<c:forEach var="utilisateur" items="${ utilisateurs }">
					<tr>
						<td><c:out value="${ utilisateur.id }"/> </td>
						<td><c:out value="${ utilisateur.prenom }"/> </td>
						<td><c:out value="${ utilisateur.nom }"/> </td>
						<td><c:out value="${ utilisateur.login }"/> </td>
						<td><c:out value="${ utilisateur.password }"/> </td>
						<td><a href="update?id=${ utilisateur.id } ">Modifier</a></td>
						<td><a href="delete?id=${ utilisateur.id } " onclick="return confirmSuppression()">Supprimer</a></td>
					</tr>
				</c:forEach>
				
				
			</table>
		</c:otherwise>
	</c:choose>
		
	</div>
	<%@include file="inc/footer.jsp" %>
	