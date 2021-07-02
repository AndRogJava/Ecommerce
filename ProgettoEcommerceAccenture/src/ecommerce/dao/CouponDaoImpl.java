package ecommerce.coupon.dao;

import ecommerce.coupon.model.CouponBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ecommerce.connection.ConnectionFactory;

public class CouponDaoImpl implements IDaoCoupon{
	
	Connection conn = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
//	public int getSequence() {
//		//TODO impostare il nome ufficiale della sequenza
//		int idCoupon=0;
//		String sequenzaCoupon= "select coupon_seq.nextval from dual";
//		conn = ConnectionFactory.getIstance().getConnection();
//		try {
//			PreparedStatement pst= conn.prepareStatement(sequenzaCoupon);
//			synchronized(this) {
//				ResultSet rs= pst.executeQuery();
//				if (rs.next()) {
//					idCoupon = rs.getInt(1);
//				}
//			}
//		}catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return idCoupon;
//	}

	@Override
	public boolean addCoupon(CouponBean coupon) {
		String query = "insert into coupon (id_coupon, username, importo, utilizzato) values"+"(coupon_seq.nextval,?,?,?)";
		conn = ConnectionFactory.getIstance().getConnection();
		int i=0;
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, coupon.getUsername());
			preparedStatement.setDouble(2, coupon.getImporto());
			preparedStatement.setString(3, (coupon.isUtilizzato()) ? "Y" : "N");
			
			i=preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			//logger.error("Errore nel database", e);
			e.printStackTrace();
		}catch(Exception e) {
			//logger.error("Errore",e);
			e.printStackTrace();
		}
		if (i>0) {
			System.out.println("Coupon aggiunto");
			return true;
		}
		else {
			System.out.println("Coupon non aggiunto");
			return false;
		}	

	}

	@Override
	public boolean deleteCoupon(int idCoupon) { //TEST OK
		String query = "delete from coupon where id_coupon = ?";
		conn = ConnectionFactory.getIstance().getConnection();
		int i = 0;
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, idCoupon);
			i = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			//logger.error("Errore nel database", e);
			e.printStackTrace();
		}catch(Exception e) {
			//logger.error("Errore",e);
			e.printStackTrace();
		}

		if (i>0) {
			System.out.println("Coupon eliminato");
			return true;
		}
		else {
			System.out.println("Coupon non presente");
			return false;
		}
	}

	@Override
	public boolean updateCoupon(CouponBean coupon) {
		String query =  "update coupon  SET importo=?, utilizzato=? where id_coupon = ?";
		conn = ConnectionFactory.getIstance().getConnection();
		int i = 0;
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setDouble(1, coupon.getImporto());
			preparedStatement.setString(2, (coupon.isUtilizzato()) ? "Y" : "N");
			preparedStatement.setInt(3, coupon.getIdCoupon());

			i = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			//logger.error("Errore nel database", e);
			e.printStackTrace();
		}catch(Exception e) {
			//logger.error("Errore",e);
			e.printStackTrace();
		}
		if (i > 0)
			return true;
		else				

			return false;
	}

	@Override
	public CouponBean getCouponById(int idCoupon) { //TEST OK
		String query = "select * from coupon where id_coupon= ?";
		conn = ConnectionFactory.getIstance().getConnection();

		CouponBean couponAppoggio = null;

		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, idCoupon);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				couponAppoggio = new CouponBean();
				couponAppoggio.setIdCoupon(idCoupon);
				couponAppoggio.setUsername(resultSet.getString(2));
				couponAppoggio.setImporto(resultSet.getDouble(3));
				couponAppoggio.setDataEmissione(resultSet.getDate(4).toLocalDate());
				couponAppoggio.setDaataScadenza(resultSet.getDate(5).toLocalDate());
				couponAppoggio.setUtilizzato((resultSet.getString(6).equals("Y") ? true : false));

			}
		} catch (SQLException e) {
			//logger.error("Errore nel database", e);
			e.printStackTrace();
		}catch(Exception e) {
			//logger.error("Errore",e);
			e.printStackTrace();
		}

		return couponAppoggio;	
	}

	@Override
	public ArrayList<CouponBean> getAllCoupon() { //TEST OK
		String query = "select * from Coupon";

		ArrayList<CouponBean> listaCoupon= new ArrayList<CouponBean>();
		conn = ConnectionFactory.getIstance().getConnection();

		try {
			preparedStatement = conn.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				CouponBean couponAppoggio= new CouponBean();
				couponAppoggio.setIdCoupon(resultSet.getInt(1));
				couponAppoggio.setUsername(resultSet.getString(2));
				couponAppoggio.setImporto(resultSet.getDouble(3));
				couponAppoggio.setDataEmissione(resultSet.getDate(4).toLocalDate());
				couponAppoggio.setDaataScadenza(resultSet.getDate(5).toLocalDate());
				couponAppoggio.setUtilizzato((resultSet.getString(6).equals("Y") ? true : false));

				listaCoupon.add(couponAppoggio);
			}
		} catch (SQLException e) {
			//logger.error("Errore nel database", e);
			e.printStackTrace();
		}catch(Exception e) {
			//logger.error("Errore",e);
		}
		return listaCoupon;
	}


	@Override
	public ArrayList<CouponBean> getAllCouponByUser(String username) { //TEST OK
		String query = "select * from Coupon where username=?";
		conn = ConnectionFactory.getIstance().getConnection();

		ArrayList<CouponBean> arrayCoupon = new ArrayList<CouponBean>();
		

		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				CouponBean couponAppoggio= new CouponBean();
				couponAppoggio.setIdCoupon(resultSet.getInt(1));
				couponAppoggio.setUsername(username);
				couponAppoggio.setImporto(resultSet.getDouble(3));
				couponAppoggio.setDataEmissione(resultSet.getDate(4).toLocalDate());
				couponAppoggio.setDaataScadenza(resultSet.getDate(5).toLocalDate());
				couponAppoggio.setUtilizzato((resultSet.getString(6).equals("Y") ? true : false));
				
				arrayCoupon.add(couponAppoggio);
			}
		} catch (SQLException e) {
			//logger.error("Errore nel database", e);
			e.printStackTrace();
		}catch(Exception e) {
			//logger.error("Errore",e);
			e.printStackTrace();
		}

		return arrayCoupon;	
	}

	
	
}
