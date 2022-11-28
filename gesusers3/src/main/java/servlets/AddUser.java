package servlets;
import forms.AddUserForm;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import beans.Utilisateur;
import dao.UtilisateurDao;
/**
 * Servlet implementation class AddUser
 */
@WebServlet("/add")
public class AddUser extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	private static final String VUE_AJOUT_UTILISATEUR = "/WEB-INF/ajouterUtilisateur.jsp";
	private static final String	VUE_LIST_UTILISATEUR	= "/WEB-INF/listerUtilisateur.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		getServletContext().getRequestDispatcher(VUE_AJOUT_UTILISATEUR).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	    AddUserForm form = new AddUserForm(request);
	    
	    if(form.ajouter())
	    	response.sendRedirect("login");
	    else {
	    	request.setAttribute("utilisateur", form.getUtilisateur());
		    request.setAttribute("status", form.getStatus());
		    request.setAttribute("statusMessage", form.getStatusMessage());
		    request.setAttribute("erreurs", form.getErreurs());
		    getServletContext().getRequestDispatcher(VUE_AJOUT_UTILISATEUR).forward(request,  response);

	    }


		// String nom = request.getParameter("nom");
		// String prenom = request.getParameter("prenom");
		// String login = request.getParameter("login");
		// String password = request.getParameter("password");
	    
		// Utilisateur utilisateur = new Utilisateur(nom, prenom, login, password);
		// UtilisateurDao.ajouter(utilisateur);
	    
		// response.sendRedirect(request.getContextPath());
	}

}
