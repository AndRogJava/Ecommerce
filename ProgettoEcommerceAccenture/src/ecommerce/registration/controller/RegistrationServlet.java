package ecommerce.registration.controller;
import ecommerce.user.model.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ecommerce.registration.validator.*;
import ecommerce.user.service.*;
/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(RegistrationServlet.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("registration of user in post method");
		boolean data=false;
		UserBean utente = new UserBean();
		utente.setUsername(request.getParameter("username"));
		utente.setPassword( request.getParameter("password"));
		utente.setNome(request.getParameter("nome"));
		utente.setCognome(request.getParameter("cognome"));
		String confirmPassword = request.getParameter("confirmPassword");
		//data di nascita nel futuro (compreso oggi) e prima del 01/01/1900 non viene memorizzata in DB
		if(!request.getParameter("dataNascita").equals("") && request.getParameter("dataNascita") != null ) {
			if(DateValidator.validator(request.getParameter("dataNascita"))) { 
				utente.setDataNascita(LocalDate.parse(request.getParameter("dataNascita")));
				data=true;
			}
			//data=true;
		}
		
		UserService us = new UserService();
		RequestDispatcher rd = null;
		String via = (String)request.getParameter("via");
		String numero = (String)request.getParameter("numero");
		String citta = (String)request.getParameter("citta");
		String cap = (String)request.getParameter("cap");
		if(RegistrationValidator.validate(utente,confirmPassword) && data && AddressValidator.validate(via, numero, citta, cap)) {
			logger.info("dati validi");
			UserBean checkUtente = us.getUserByUsername(utente.getUsername());
			if(checkUtente == null) {
				us.addUser(utente);
				logger.info("Utente aggiunto");
				IndirizzoBean address = new IndirizzoBean();
				address.setVia(via);
				address.setNumero(Integer.parseInt(numero));
				address.setCitta(citta);
				address.setCap(Integer.parseInt(cap));
				address.setUtente(utente.getUsername());
				address.setUltimoIndirizzo("Y");
				request.setAttribute("address", address);
				rd = request.getRequestDispatcher("/AddressServlet");
				rd.forward(request, response);
				
			}else {
				logger.error("username gia presente");
				rd= request.getRequestDispatcher("registration.jsp");
				request.setAttribute("msg", "Impossibile registrare, username già  presente nel sito.");
				rd.forward(request, response);
			}
		}else {
			logger.error("dati non rispettani i vincoli");
			rd= request.getRequestDispatcher("registration.jsp");
			request.setAttribute("msg","Impossibile registrare, vincoli sui dati non rispettati!");
			rd.forward(request, response);
		}
		
	}

}
