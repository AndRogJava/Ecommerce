package ecommerce.coupon.dao;

import java.util.ArrayList;

import ecommerce.coupon.model.CouponBean;

public interface IDaoCoupon {
	
	public boolean addCoupon (CouponBean coupon);
	public boolean deleteCoupon (int idCoupon);
	public boolean updateCoupon (CouponBean coupon);
	public CouponBean getCouponById (int idCoupon);
	public ArrayList<CouponBean> getAllCoupon();
	public ArrayList<CouponBean> getAllCouponByUser(String utente);
		
}
