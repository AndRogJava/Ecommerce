package ecommerce.ordine.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ecommerce.connection.ConnectionFactory;
import ecommerce.ordine.model.OrdineBean;

public class OrdineDaoImpl implements IOrdineDao {

//	final static Logger logger = Logger.getLogger(OrdineDaoImpl.class);

	Connection conn = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	public int getSequence() {
		int idOrd=0;
		String sequenzaOrdine= "select ordine_seq.nextval from dual";
		conn = ConnectionFactory.getIstance().getConnection();
		try {
			PreparedStatement pst= conn.prepareStatement(sequenzaOrdine);
			synchronized(this) {
				ResultSet rs= pst.executeQuery();
				if (rs.next()) {
					idOrd = rs.getInt(1);
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return idOrd;
	}

	@Override
	public boolean addOrdine(OrdineBean ordine) {
		//logger.info("AddOrdine caricato");
		
		String query = "insert into ordine values (?,?,?,?,?)";
		conn = ConnectionFactory.getIstance().getConnection();
		ordine.setIdOrdine(getSequence());
		int i=0;
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1,ordine.getIdOrdine());
			preparedStatement.setDate(2, Date.valueOf(ordine.getDataOrdine()));
			preparedStatement.setDouble(3, ordine.getPrezzoTotale());
			preparedStatement.setString(4, ordine.getUtente());
			preparedStatement.setInt(5, ordine.getIndirizzo());
			
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
			System.out.println("Ordine aggiunto");
			return true;
		}
		else {
			System.out.println("Ordine non aggiunto");
			return false;
		}	

	}

	@Override
	public boolean deleteOrdine(int idOrdine) {
		
		//logger.info("DeleteOrdine caricato");
		
		String query = "delete from ordine where id_ordine = ?";
		conn = ConnectionFactory.getIstance().getConnection();
		int i = 0;
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, idOrdine);
			i = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			//logger.error("Errore nel database", e);
			e.printStackTrace();
		}catch(Exception e) {
			//logger.error("Errore",e);
			e.printStackTrace();
		}

		if (i>0) {
			System.out.println("Ordine eliminato");
			return true;
		}
		else {
			System.out.println("Ordine non presente");
			return false;
		}
	}

	@Override
	public boolean updateOrdine(OrdineBean ordine) {

		//logger.info("UpdateOrdine caricato");
				String query =  "update ordine  SET  data_ordine = ?, prezzo_totale=?, utente=?, indirizzo=? where id_ordine = ?";
				conn = ConnectionFactory.getIstance().getConnection();
				int i = 0;
				try {
					preparedStatement = conn.prepareStatement(query);
					
					preparedStatement.setDate(1, Date.valueOf(ordine.getDataOrdine()));
					preparedStatement.setDouble(2, ordine.getPrezzoTotale());
					preparedStatement.setString(3, ordine.getUtente());
					preparedStatement.setInt(4, ordine.getIndirizzo());
					preparedStatement.setInt(5, ordine.getIdOrdine());
					
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
	public OrdineBean getOrdineById(int idOrdine) {
		//logger.info("getOrdineById caricato");
		String query = "select * from ordine where id_ordine= ?";
		conn = ConnectionFactory.getIstance().getConnection();

		OrdineBean ordineAppoggio = null;

		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, idOrdine);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				
				ordineAppoggio= new OrdineBean();
				ordineAppoggio.setIdOrdine(idOrdine);
				ordineAppoggio.setDataOrdine(resultSet.getDate(2).toLocalDate());
				ordineAppoggio.setPrezzoTotale(resultSet.getDouble(3));
				ordineAppoggio.setUtente(resultSet.getString(4));
				ordineAppoggio.setIndirizzo(resultSet.getInt(5));
		
			}
		} catch (SQLException e) {
			//logger.error("Errore nel database", e);
			e.printStackTrace();
		}catch(Exception e) {
			//logger.error("Errore",e);
			e.printStackTrace();
		}

		return ordineAppoggio;	

	}

	@Override
	public ArrayList<OrdineBean> getAllOrdini() {
		//logger.info("getAllOrdini caricato");
		
		String query = "select * from ordine";

		ArrayList<OrdineBean> listaOrdini= new ArrayList<OrdineBean>();
		conn = ConnectionFactory.getIstance().getConnection();

		try {
			preparedStatement = conn.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				OrdineBean ordineAppoggio=new OrdineBean();
				ordineAppoggio.setIdOrdine(resultSet.getInt(1));
				ordineAppoggio.setDataOrdine(resultSet.getDate(2).toLocalDate());
				ordineAppoggio.setPrezzoTotale(resultSet.getDouble(3));
				ordineAppoggio.setUtente(resultSet.getString(4));
				ordineAppoggio.setIndirizzo(resultSet.getInt(5));
				
			listaOrdini.add(ordineAppoggio);

			}
		} catch (SQLException e) {
			//logger.error("Errore nel database", e);
			e.printStackTrace();
		}catch(Exception e) {
			//logger.error("Errore",e);
			e.printStackTrace();
		}
		return listaOrdini;
	}

	


}
