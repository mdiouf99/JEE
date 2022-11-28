<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String messageRecu = (String)request.getAttribute("message");
	final String APP_ROOT = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Status</title>
	<link rel="stylesheet" href="<%= APP_ROOT %>/css/design.css">
</head>
<body>
	<%@include file="inc/header.jsp" %>
	<div id="corps">
		<h1 id="titre-principal"><%= messageRecu %></h1>
	</div>
x	<%@include file="inc/footer.jsp" %>
</body>
</html>