package ecommerce.cart.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ecommerce.cart.service.*;
import ecommerce.prodotto.dao.ProdottoDaoImpl;
import ecommerce.prodotto.model.ProdottoBean;
import ecommerce.user.model.IndirizzoBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ObjectMapper mapper = new ObjectMapper();
		RequestDispatcher rd=null;
		if(session!=null && session.getAttribute("user") != null) {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String json = "";
			System.out.println(json);
			if(br!=null) {
				json = br.readLine();
				System.out.println(json);
			}
			
			HashMap<Integer,Integer> mappa = new HashMap<Integer,Integer>();
			List<Map<String, Object>> jsonMap =  mapper.readValue(json , new TypeReference<List<Map<String, Object>>>(){});
			for(Map<String,Object> elem : jsonMap) {
				mappa.put(Integer.parseInt(elem.get("idProdotto").toString()), Integer.parseInt(elem.get("quantita").toString()));
			}
			CartService cs = new CartService();
			HashMap<IndirizzoBean,ArrayList<ProdottoBean>> lista = cs.getCartDetails((String)session.getAttribute("user"), mappa);
			//System.out.println(lista);
			response.setContentType("application/json");
			session.setAttribute("lista", lista);
			
			mapper.writeValue(response.getOutputStream(), true);
		}else {
			mapper.writeValue(response.getOutputStream(), false);
			
		}
	}

}
