package ecommerce.ordine.dao;

import java.util.ArrayList;

import ecommerce.ordine.model.OrdineBean;

public interface IOrdineDao {

public boolean addOrdine(OrdineBean ordine);
public boolean deleteOrdine(int idOrdine);
public boolean updateOrdine(OrdineBean ordine);
public OrdineBean getOrdineById(int idOrdine);
public ArrayList<OrdineBean> getAllOrdini();
public ArrayList<OrdineBean> getAllOrdiniByUser(String user);


	
}
