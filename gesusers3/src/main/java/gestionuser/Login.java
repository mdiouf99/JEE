package gestionuser;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;


import forms.LoginForm;

/**
 * Servlet implementation class Login
 */
@WebServlet({"/login","/logout"} )

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_LOGIN_UTILISATEUR = "/WEB-INF/login.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		switch(request.getServletPath()) 
		{
		
			case "/login":
				request.getRequestDispatcher(VUE_LOGIN_UTILISATEUR).forward(request, response);
				
			case "/logout":
				HttpSession session = request.getSession();
				session.invalidate();
				response.sendRedirect("login");
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LoginForm form = new LoginForm(request);
		
		if(form.login()){
			request.getSession().setAttribute("isConnected", true);
			response.sendRedirect("list");
		}
		else {
			request.setAttribute("messageRecu", form.getStatusMessage());
			request.getRequestDispatcher(VUE_LOGIN_UTILISATEUR).forward(request, response);

		}
		
	}

}
