package ecommerce.coupon.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecommerce.coupon.model.CouponBean;
import ecommerce.coupon.service.CouponService;

@WebServlet("/CouponServlet")
public class CouponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CouponService servCoupon = new CouponService();
		ArrayList<CouponBean> results = servCoupon.getAllCoupon();
////		TEST
//		for(CouponBean c: results)
//			response.getWriter().append(c.toString());
		
		//inserire jsp
		if(results == null || results.isEmpty()) {
			request.setAttribute("message", "Nessun coupon disponibile");
			request.getRequestDispatcher("").forward(request, response);
		}
		else {
			request.setAttribute("", results);
			request.getRequestDispatcher("").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
