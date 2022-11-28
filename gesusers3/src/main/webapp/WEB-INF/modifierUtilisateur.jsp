<%@page import="beans.Utilisateur" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification d'un utilisateur</title>
</head>
<body>
	<%@include file="inc/header.jsp" %>

	<h1>Modification d'un utilisateur</h1>
	<form method="post">
		<fieldset>
			<legend>modification d'un utilisateur</legend>
			<label for="nom">Nom</label><br>
			<input type="text" name="nom" id="nom" value=${ utilisateur.nom }><br>
			
			<label for="prenom">Prenom</label><br>
			<input type="text" name="prenom" id="prenom" value=${ utilisateur.prenom }><br>
			
			<label for="login">Login</label><br>
			<input type="text" name="login" id="login" value=${ utilisateur.login }><br>
			
			<label for="password">Password</label><br>
			<input type="text" name="password" id="password" value=${ utilisateur.password }><br><br>
			
			<input type="submit" value="Modifier">
		</fieldset>
	</form>
	<%@include file="inc/footer.jsp" %>
