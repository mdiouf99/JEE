package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;
import dao.UtilisateurDao;

public class AddUserForm {
	private Utilisateur utilisateur;
	
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_LOGIN = "login";
	private static final String CHAMP_PASSWORD = "password";
	private static final String CHAMP_PASSWORD_BIS = "passwordBis";
	
	private static final String EMPTY_FIELD_MESSAGE = "Vous devez renseigner ce champs";
	private static final String WRONG_PASSWORD_MESSAGE = "Les mots de pass ne sont pas conformes";
	
	private boolean status;
	private String statusMessage;
	
	private HttpServletRequest request;
	private HashMap<String, String> erreurs;
	
	public AddUserForm(HttpServletRequest request) {
		this.request = request;
		this.erreurs = new HashMap<String, String>();
	}
	
	public boolean ajouter() {
		String nom = getParameter(CHAMP_NOM);
		String prenom = getParameter(CHAMP_PRENOM);
		String login = getParameter(CHAMP_LOGIN);
		String password = getParameter(CHAMP_PASSWORD);
		String passwordBis = getParameter(CHAMP_PASSWORD_BIS);
		
		this.utilisateur = new Utilisateur(prenom, nom, login, password);
		
		validerChamps(CHAMP_NOM, CHAMP_PRENOM, CHAMP_LOGIN, CHAMP_PASSWORD, CHAMP_PASSWORD_BIS);
		validerPasswords();
		
		if(this.erreurs.isEmpty()) {
			this.status = true;
			this.statusMessage = "Ajout effectué avec succes";
			UtilisateurDao.ajouter(this.utilisateur);
		}
		else {
			status = false;
			statusMessage = "Ajout échoué";
		}
		
		return status;
			
	}
	
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
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

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Map<String, String> getErreurs() {
		return this.erreurs;
	}

	private String getParameter(String parameter) {
		String valeur = request.getParameter(parameter);
		
		return (valeur == null) || valeur.trim().isEmpty() ? null : valeur.trim();
	}
	
	private void validerChamps(String ...parameters) {
		for(String parameter : parameters){
			if(getParameter(parameter) == null)
				this.erreurs.put(parameter,  EMPTY_FIELD_MESSAGE);
		}
	}
	
	private void validerPasswords() {
		String password = this.getParameter(CHAMP_PASSWORD);
		String passwordBis = this.getParameter(CHAMP_PASSWORD_BIS);
		if( password != null && !password.equals(passwordBis) )
			this.erreurs.put(CHAMP_PASSWORD, WRONG_PASSWORD_MESSAGE);
	}
	
}
