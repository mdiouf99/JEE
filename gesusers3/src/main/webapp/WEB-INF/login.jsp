	<%@include file="inc/header.jsp" %>
	<div id="corps">
		<h4 class="erreur">${messageRecu}</h4>
		<h1 id="titre-principal">Authentification d'un utilisateur</h1>
		<form method="POST">
			<div class="formItem">
				<label for="login">Login:</label>
				<input type="text" id="login" name="login"/>
			</div>
			<div class="formItem">
				<label for="password">Password:</label>
				<input type="password" id="password" name="password"/>
			</div>
			<div class="formItem">
				<input type="submit" value="envoyer"/>
			</div>
		</form>
	</div>
	<%@include file="inc/footer.jsp" %>