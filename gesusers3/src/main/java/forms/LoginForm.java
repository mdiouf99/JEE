package forms;



import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;
import dao.UtilisateurDao;

public class LoginForm {
	private Utilisateur utilisateur;
	
	private static final String CHAMP_LOGIN = "login";
	private static final String CHAMP_PASSWORD = "password";
	
	private static final String EMPTY_FIELD_MESSAGE = "Vous devez renseigner ce champs";
	private static final String BAD_CREDENTIALS = "CREDENTIALS INVALID";
	
	private boolean status;
	private String statusMessage;
	
	private HttpServletRequest request;
	
	public LoginForm(HttpServletRequest request) {
		this.request = request;
	    this.status = false;
	    this.statusMessage = BAD_CREDENTIALS;
	}
	
	public boolean login() {
		String login = getParameter(CHAMP_LOGIN);
		String password = getParameter(CHAMP_PASSWORD);

		this.status = validerChamps(CHAMP_LOGIN, CHAMP_PASSWORD);
		
		utilisateur  = UtilisateurDao.getByUsername(login);
		
		if(this.status && utilisateur != null) {
			if(password.equals(utilisateur.getPassword())) {
				this.statusMessage = "Connexion Reussie";
			}		
			else {
				this.statusMessage = "Mot de passe incorrect";
				this.status = false;
			}
		}
		else{
				this.statusMessage = "Cet Utilisateur n'existe pas";
				this.status = false;
		}

		return this.status;
			
	}
	
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	private String getParameter(String parameter) {
		String valeur = this.request.getParameter(parameter);
		
		return (valeur == null) || valeur.trim().isEmpty() ? null : valeur.trim();
	}
	
	private boolean validerChamps(String ...parameters) {
		for(String parameter : parameters){
			if(getParameter(parameter) == null) {
				this.setStatusMessage(EMPTY_FIELD_MESSAGE);
				return false;
			}
		}
		return true;
	}
}
