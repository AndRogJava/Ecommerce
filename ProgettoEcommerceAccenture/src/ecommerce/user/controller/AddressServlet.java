package ecommerce.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import ecommerce.user.model.*;
import ecommerce.user.service.AddressService;
import ecommerce.user.service.UserService;

@WebServlet("/AddressServlet")
public class AddressServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(AddressServlet.class);
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddressService addressService = new AddressService();
		String user = request.getParameter("user");
		IndirizzoBean indirizzoBean = addressService.getUltimoIndirizzoByUser(user);

		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");
		mapper.writeValue(response.getOutputStream(), indirizzoBean);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("do post address servlet");
		
		IndirizzoBean address = (IndirizzoBean) request.getAttribute("address");
		
		RequestDispatcher rd=null;
				
		AddressService as = new AddressService();
		if(as.addAddress(address)) {
			logger.info("address added");
			rd = request.getRequestDispatcher("index.jsp");
			HttpSession session = request.getSession();
			session.setAttribute("user", address.getUtente());
			rd.forward(request, response);
		}else {
			logger.error("data of address not valid!!");
			rd = request.getRequestDispatcher("registration.jsp");
			request.setAttribute("msg","Impossibile aggiungere l'indirizzo");
			rd.forward(request, response);
		}
		
		
	}

}
