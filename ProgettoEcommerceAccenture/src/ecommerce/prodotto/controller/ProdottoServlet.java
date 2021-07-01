package ecommerce.prodotto.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecommerce.prodotto.model.ProdottoBean;
import ecommerce.prodotto.service.ProdottoService;

@WebServlet("/Prodotto")
public class ProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdottoService servProd = new ProdottoService();
		LinkedList<ProdottoBean> results = servProd.getAllProdotto();
		if(results==null || results.isEmpty()) {
			request.setAttribute("message", "Al momento non ci sono prodotti disponibili");
			request.getRequestDispatcher("listaprodotti.jsp").forward(request, response);
		}
		else {
			request.setAttribute("listaprodotti", results);
			request.getRequestDispatcher("listaprodotti.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
