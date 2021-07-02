package ecommerce.ordine.controller;
import ecommerce.ordine.model.*;
import ecommerce.user.model.IndirizzoBean;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ecommerce.ordine.service.*;
import ecommerce.prodotto.model.ProdottoBean;
import ecommerce.prodotto.service.ProdottoService;
import ecommerce.exception.*;
import ecommerce.ordine.model.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class OrdineServlet
 */
@WebServlet("/OrdineServlet")
public class OrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdineService ordineService = new OrdineService();
		String user = request.getParameter("user");
		System.out.println(user);
		ArrayList<OrdineBean> lista = ordineService.getAllOrdiniByUser(user);

		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");
		mapper.writeValue(response.getOutputStream(), lista);
		
		/*OrdineService os = new OrdineService();
		
		HashMap<Integer,ArrayList<DettaglioBean>> mappaOrdine = os.getAllDettaglio();
		HashMap<OrdineBean,ArrayList<DettaglioBean>> result = new HashMap<OrdineBean,ArrayList<DettaglioBean>>();
		for(Integer id : mappaOrdine.keySet()) {
			OrdineBean ob = os.getOrdineById(id);
			result.put(ob,mappaOrdine.get(id));
		}
		request.setAttribute("mappaOrdini", result);
		RequestDispatcher rd = request.getRequestDispatcher("mostraOrdini.jsp");
		rd.forward(request, response);
		
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ObjectMapper mapper = new ObjectMapper();
		if(session != null && session.getAttribute("user") != null) {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String json = "";
			if(br!=null) {
				json = br.readLine();
				System.out.println(json);
			}
			
			HashMap<Integer,Integer> mappa = new HashMap<Integer,Integer>();
			IndirizzoBean indirizzo = new IndirizzoBean();
			JSONObject jsonObject = new JSONObject(json);
			JSONArray valoreProdotti = jsonObject.getJSONArray("products");
			for(int i=0; i< valoreProdotti.length(); i++) {
				mappa.put(valoreProdotti.getJSONObject(i).getInt("id"), valoreProdotti.getJSONObject(i).getInt("quantity"));
			}
			indirizzo.setVia(jsonObject.getJSONObject("address").getString("via"));
			indirizzo.setNumero(jsonObject.getJSONObject("address").getInt("numero"));
			indirizzo.setCitta(jsonObject.getJSONObject("address").getString("citta"));
			indirizzo.setCap(jsonObject.getJSONObject("address").getInt("cap"));
			indirizzo.setUtente((String)session.getAttribute("user"));
			
				System.out.println(mappa);
				System.out.println(indirizzo);
				
			OrdineService os = new OrdineService();
			try {
				OrdineBean ob = os.confermaOrdine(mappa,indirizzo);
				request.setAttribute("msg","Ordine effettuato con successo!");
			}catch(IdProductNotValidException e) {
				request.setAttribute("msg", "Impossibile aggiungere l'ordine, prodotto non disponibile");
			}catch(NotEnoughNumberOfProductException e){
				request.setAttribute("msg", "Impossibile aggiungere l'ordine, prodotto non disponibile");
			}finally {
				response.setContentType("application/json");
				mapper.writeValue(response.getOutputStream(), true);
			}
		}else {
			response.setContentType("application/json");
			mapper.writeValue(response.getOutputStream(), false);
		}
	}

}
