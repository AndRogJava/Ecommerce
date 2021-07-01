package ecommerce.prodotto.dao;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.plaf.synth.SynthScrollBarUI;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import ecommerce.connection.ConnectionFactory;
import ecommerce.prodotto.model.ProdottoBean;
import ecommerce.user.dao.UserDaoImpl;
import ecommerce.user.model.UserBean;

public class ProdottoDaoImpl implements IProdottoDao{

	private static final Logger logger = Logger.getLogger(ProdottoDaoImpl.class);

	Connection oggettoConnessione=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;

	public int getSequence(){
		int id=0;
		String query= "SELECT PRODOTTO_SEQ.NEXTVAL FROM DUAL";
		oggettoConnessione = ConnectionFactory.getIstance().getConnection();
		try {
			PreparedStatement preparedStatement= oggettoConnessione.prepareStatement(query);
			synchronized(this) {
				ResultSet resultSet= preparedStatement.executeQuery();
				if (resultSet.next()) {
					id = resultSet.getInt(1);
				}
			}
		}catch (SQLException e){
			logger.error("Errore del database" , e);
		}catch (Exception e) {			
			logger.error("Errore generico " , e);
		}
		return id;
	}
	@Override
	public boolean addProdotto(ProdottoBean prodotto) {
		logger.info("add prodotto " + prodotto.getNome());
		oggettoConnessione=ConnectionFactory.getIstance().getConnection();
		String query="INSERT INTO PRODOTTO VALUES (?,?,?,?,?,?)";
		boolean ctrl=false;
		prodotto.setIdProdotto(getSequence());
		try {
			preparedStatement=oggettoConnessione.prepareStatement(query);
			preparedStatement.setInt(1, prodotto.getIdProdotto());
			preparedStatement.setDouble(2, prodotto.getPrezzo());
			preparedStatement.setInt(3, prodotto.getQuantitaDisponibile());
			preparedStatement.setString(4, prodotto.getNome());
			if (prodotto.getDescrizione()==null) {
				preparedStatement.setString(5,null);
			}else {
				preparedStatement.setString(5,prodotto.getDescrizione());
			}
			if (prodotto.getUrl()==null) {
				preparedStatement.setString(6, null);
			}else {
				preparedStatement.setString(6, prodotto.getUrl());
			}
			int nModifiche=preparedStatement.executeUpdate();
			if (nModifiche==1) {
				ctrl=true;
			}
		}catch (SQLException e){
			System.out.println(e);
			logger.error("Errore del database" , e);
		}catch (Exception e) {		
			System.out.println(e);
			logger.error("Errore generico " , e);
		}
		logger.info("insert prodotto invoked!!!");
		return ctrl;
	}

	@Override
	public boolean deleteProdotto(int id) {
		logger.info("delete prodotto " + id);
		oggettoConnessione=ConnectionFactory.getIstance().getConnection();
		String query="DELETE FROM PRODOTTO WHERE ID_PRODOTTO=?";
		boolean ctrl=false;
		try {
			preparedStatement=oggettoConnessione.prepareStatement(query);
			preparedStatement.setInt(1, id);
			int nModifiche=preparedStatement.executeUpdate();
			if (nModifiche==1) {
				ctrl=true;
			}
		}catch (SQLException e){
			logger.error("Errore del database" , e);
		}catch (Exception e) {			
			logger.error("Errore generico " , e);
		}	
		logger.info("delete prodotto invoked!!!");
		return ctrl;
	}

	@Override
	public boolean updateProdotto(ProdottoBean prodotto) {
		logger.info("update prodotto " + prodotto.getIdProdotto());
		oggettoConnessione=ConnectionFactory.getIstance().getConnection();
		String query="UPDATE PRODOTTO SET ID_PRODOTTO=?,PREZZO=?,QUANTITA_DISPONIBILE=?,NOME=?,DESCRIZIONE=?,URL_IMMAGINE=? WHERE ID_PRODOTTO=?";
		boolean ctrl=false;
		try {
			preparedStatement=oggettoConnessione.prepareStatement(query);
			preparedStatement.setInt(1, prodotto.getIdProdotto());
			preparedStatement.setDouble(2, prodotto.getPrezzo());
			preparedStatement.setInt(3, prodotto.getQuantitaDisponibile());
			preparedStatement.setString(4, prodotto.getNome());
			if (prodotto.getDescrizione()==null) {
				preparedStatement.setString(5,null);
			}else {
				preparedStatement.setString(5,prodotto.getDescrizione());
			}
			if (prodotto.getUrl()==null) {
				preparedStatement.setString(6, null);
			}else {
				preparedStatement.setString(6, prodotto.getUrl());
			}
			preparedStatement.setInt(7, prodotto.getIdProdotto());
			int nModifiche=preparedStatement.executeUpdate();
			if (nModifiche==1) {
				ctrl=true;
			}
		}catch (SQLException e){
			logger.error("Errore del database" , e);
		}catch (Exception e) {			
			logger.error("Errore generico " , e);
		}	
		logger.info("update prodotto invoked!!!");
		return ctrl;
	}

	@Override
	public ProdottoBean getProdottoById(int id) {
		logger.info("get prodotto " + id);
		oggettoConnessione=ConnectionFactory.getIstance().getConnection();
		String query="SELECT * FROM PRODOTTO WHERE ID_PRODOTTO=?";
		ProdottoBean prodotto=null;
		try {
			preparedStatement=oggettoConnessione.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				prodotto=new ProdottoBean();
				prodotto.setIdProdotto(resultSet.getInt(1));
				prodotto.setPrezzo(resultSet.getDouble(2));
				prodotto.setQuantitaDisponibile(resultSet.getInt(3));
				prodotto.setNome(resultSet.getString(4));
				if (resultSet.getString(5)==null) {
					prodotto.setDescrizione(null);
				}else {
					prodotto.setDescrizione(resultSet.getString(5));
				}
				if (resultSet.getString(6)==null) {
					prodotto.setUrl(null);
				}else {
					prodotto.setUrl(resultSet.getString(6));
				}
			}
		}catch (SQLException e){
			logger.error("Errore del database" , e);
		}catch (Exception e) {			
			logger.error("Errore generico " , e);
		}	
		logger.info("get user invoked!!!");
		return prodotto;
	}

	@Override
	public LinkedList<ProdottoBean> getAllProdotto() {
		logger.info("get all prodotti");
		oggettoConnessione=ConnectionFactory.getIstance().getConnection();
		String query="SELECT * FROM PRODOTTO";
		LinkedList<ProdottoBean> listaProdotti=null;
		try {
			listaProdotti=new LinkedList<ProdottoBean>();
			preparedStatement=oggettoConnessione.prepareStatement(query);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				ProdottoBean prodottoAppoggio=new ProdottoBean();
				prodottoAppoggio.setIdProdotto(resultSet.getInt(1));
				prodottoAppoggio.setPrezzo(resultSet.getDouble(2));
				prodottoAppoggio.setQuantitaDisponibile(resultSet.getInt(3));
				prodottoAppoggio.setNome(resultSet.getString(4));
				
				System.out.println(resultSet.getString(5) + " " + resultSet.getString(6));
				
				if (resultSet.getString(5)==null) {
					prodottoAppoggio.setDescrizione(null);
				}else {
					prodottoAppoggio.setDescrizione(resultSet.getString(5));
				}
				if (resultSet.getString(6)==null) {
					prodottoAppoggio.setUrl(null);
				}else {
					prodottoAppoggio.setUrl(resultSet.getString(6));
				}
				
				listaProdotti.add(prodottoAppoggio);
			}
		}catch (SQLException e){
			logger.error("Errore del database" , e);
		}catch (Exception e) {			
			logger.error("Errore generico " , e);
		}	
		logger.info("get all prodotti invoked!!!");
		return listaProdotti;
	}
	@Override
	public ArrayList<ProdottoBean> getProdottoByCategoria(int idCategoria) {
		logger.info("get prodotti by categoria");
		oggettoConnessione=ConnectionFactory.getIstance().getConnection();
		String query="SELECT * FROM LISTA_PRODOTTI_CATEGORIA WHERE ID_CATEGORIA=?";
		ArrayList<ProdottoBean> listaProdotti=null;
		try {
			listaProdotti=new ArrayList<ProdottoBean>();
			preparedStatement=oggettoConnessione.prepareStatement(query);
			preparedStatement.setInt(1, idCategoria);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				ProdottoBean prodottoAppoggio=new ProdottoBean();
				prodottoAppoggio.setIdProdotto(resultSet.getInt(2));
				prodottoAppoggio.setPrezzo(resultSet.getDouble(3));
				prodottoAppoggio.setQuantitaDisponibile(resultSet.getInt(4));
				prodottoAppoggio.setNome(resultSet.getString(5));
				if (resultSet.getString(6)==null) {
					prodottoAppoggio.setDescrizione(null);
				}else {
					prodottoAppoggio.setDescrizione(resultSet.getString(6));
				}
				if (resultSet.getString(7)==null) {
					prodottoAppoggio.setUrl(null);
				}else {
					prodottoAppoggio.setUrl(resultSet.getString(7));
				}
				listaProdotti.add(prodottoAppoggio);
			}
		}catch (SQLException e){
			logger.error("Errore del database" , e);
		}catch (Exception e) {			
			logger.error("Errore generico " , e);
		}	
		logger.info("get prodotti by categoria invoked!!!");
		return listaProdotti;
	}



}
