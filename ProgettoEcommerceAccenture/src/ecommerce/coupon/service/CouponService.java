package ecommerce.coupon.service;

import java.util.ArrayList;

import ecommerce.coupon.dao.CouponDaoImpl;
import ecommerce.coupon.model.CouponBean;

public class CouponService {
	private CouponDaoImpl cd;
	
	public CouponService() {
		cd = new CouponDaoImpl();
	}

	public CouponDaoImpl getCd() {
		return cd;
	}

	public void setCd(CouponDaoImpl cd) {
		this.cd = cd;
	}
	
	public boolean addCoupon(CouponBean coupon) {
		return cd.addCoupon(coupon);
	}
	
	public boolean deleteCoupon(int id) {
		return cd.deleteCoupon(id);
	}
	
	public CouponBean getCouponById(int id) {
		return cd.getCouponById(id);
	}
	
	public ArrayList<CouponBean> getAllCoupon() {
		return cd.getAllCoupon();
	}
	
	public ArrayList<CouponBean> getCouponByUser(String username) {
		return cd.getAllCouponByUser(username);
	}

	@Override
	public String toString() {
		return "CouponService [cd=" + cd + "]";
	}
	
}
