package ecommerce.coupon.model;

import java.time.LocalDate;

public class CouponBean {
	private int idCoupon;
	private String username;
	private double importo;
	private LocalDate dataEmissione;
	private LocalDate daataScadenza;
	private boolean utilizzato;
	
	public CouponBean() {
		
	}

	public int getIdCoupon() {
		return idCoupon;
	}

	public void setIdCoupon(int idCoupon) {
		this.idCoupon = idCoupon;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	public LocalDate getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(LocalDate dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public LocalDate getDaataScadenza() {
		return daataScadenza;
	}

	public void setDaataScadenza(LocalDate daataScadenza) {
		this.daataScadenza = daataScadenza;
	}

	public boolean isUtilizzato() {
		return utilizzato;
	}

	public void setUtilizzato(boolean utilizzato) {
		this.utilizzato = utilizzato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((daataScadenza == null) ? 0 : daataScadenza.hashCode());
		result = prime * result + ((dataEmissione == null) ? 0 : dataEmissione.hashCode());
		result = prime * result + idCoupon;
		long temp;
		temp = Double.doubleToLongBits(importo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + (utilizzato ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CouponBean other = (CouponBean) obj;
		if (daataScadenza == null) {
			if (other.daataScadenza != null)
				return false;
		} else if (!daataScadenza.equals(other.daataScadenza))
			return false;
		if (dataEmissione == null) {
			if (other.dataEmissione != null)
				return false;
		} else if (!dataEmissione.equals(other.dataEmissione))
			return false;
		if (idCoupon != other.idCoupon)
			return false;
		if (Double.doubleToLongBits(importo) != Double.doubleToLongBits(other.importo))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (utilizzato != other.utilizzato)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CouponBean [idCoupon=" + idCoupon + ", username=" + username + ", importo=" + importo
				+ ", dataEmissione=" + dataEmissione + ", daataScadenza=" + daataScadenza + ", utilizzato=" + utilizzato
				+ "]";
	}

}
