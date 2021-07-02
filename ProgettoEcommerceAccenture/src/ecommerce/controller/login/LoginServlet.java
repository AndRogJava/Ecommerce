package ecommerce.controller.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ecommerce.user.model.UserBean;
import ecommerce.user.service.UserService;
import ecommerce.validation.login.ValidationLogin;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(LoginServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Metodo get della servlet di Login");
		HttpSession session = request.getSession();
		session.invalidate();
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("Metodo post della servlet di Login");
		String usernameInput = request.getParameter("username");
		String passwordInput = request.getParameter("password");
		ValidationLogin vl = new ValidationLogin();
		if(!vl.validateUsername(usernameInput)) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			request.setAttribute("msg", ("Username deve avere almeno 6 caratteri"));
			rd.forward(request, response);
		}
		if(!vl.validatePassword(passwordInput)) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			request.setAttribute("msg", ("Password non rispetta vincoli di sicurezza"));
			rd.forward(request, response);
		}
		UserService us = new UserService();        
		UserBean checkBean = us.getUserByUsername(usernameInput);
		if(checkBean!=null) {
			if(checkBean.getPassword().equals(passwordInput)) {
				HttpSession session = request.getSession();
				session.setAttribute("user", usernameInput);
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			else {
				request.setAttribute("msg", ("password o username non corretti"));
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		}
		else {
			request.setAttribute("msg", ("utente non registrato"));
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}

}
