package ecommerce.prodotto.controller;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import ecommerce.categoria.service.*;
import ecommerce.prodotto.model.ProdottoBean;
import ecommerce.prodotto.service.ProdottoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class ProdottoServlet
 */
@WebServlet("/OffertaServlet")
public class OffertaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OffertaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdottoService cs = new ProdottoService();
		LinkedList<ProdottoBean> lista = cs.getAllProdotto();
		for(ProdottoBean p: lista) {
			applicaSconto(p);
		}
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");
		mapper.writeValue(response.getOutputStream(), lista);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
//	private ArrayList<ProdottoBean> caricaBean() {
//		ProdottoBean bean1 = new ProdottoBean();
//		bean1.setIdProdotto(1);
//		bean1.setNome("Tecnologia");
//		bean1.setUrl("img/tecnologia.png");
//		ProdottoBean bean2 = new ProdottoBean();
//		bean2.setIdProdotto(2);
//		bean2.setNome("Gioielli");
//		bean2.setUrl("img/gioeilli.png");
//		ProdottoBean bean3 = new ProdottoBean();
//		bean3.setIdProdotto(3);
//		bean3.setNome("Gioielli2");
//		bean3.setUrl("img/gioeilli.png");
//		ProdottoBean bean4 = new ProdottoBean();
//		bean4.setIdProdotto(4);
//		bean4.setNome("Gioielli3");
//		bean4.setUrl("img/gioeilli.png");
//		ArrayList<ProdottoBean> prova = new ArrayList<ProdottoBean>();
//		prova.add(bean1);
//		prova.add(bean2);
//		prova.add(bean3);
//		prova.add(bean4);
//		
//		return prova;
//	}
	
	private void applicaSconto(ProdottoBean p) {
		int sconto =  new Random().nextInt(50) + 1;
		String s = String.format("%.2f", (p.getPrezzo() - p.getPrezzo() * sconto / 100));
		double finale = 0;
		
		try {
			finale = NumberFormat.getInstance().parse(s).doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		p.setPrezzo(finale);
	}
}
