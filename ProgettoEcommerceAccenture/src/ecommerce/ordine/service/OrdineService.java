package ecommerce.ordine.service;

import ecommerce.exception.IdProductNotValidException;
import ecommerce.exception.NotEnoughNumberOfProductException;
import ecommerce.ordine.dao.DettaglioDaoImpl;
import ecommerce.ordine.dao.OrdineDaoImpl;
import ecommerce.ordine.dao.OrdineStoricoDaoImpl;
import ecommerce.ordine.model.DettaglioBean;
import ecommerce.ordine.model.OrdineBean;
import ecommerce.ordine.model.OrdineStoricoBean;
import ecommerce.prodotto.model.ProdottoBean;
import ecommerce.prodotto.service.ProdottoService;
import ecommerce.user.model.IndirizzoBean;
import ecommerce.user.model.UserBean;
import ecommerce.user.service.AddressService;
import ecommerce.user.service.UserService;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import java.time.LocalDate;

public class OrdineService {
	final static Logger logger = Logger.getLogger(OrdineService.class);
	private OrdineDaoImpl ordineDaoImpl;
	private DettaglioDaoImpl dettaglioDaoImpl;
	private OrdineStoricoDaoImpl ordineStoricoDaoImpl;
	public OrdineService() {
		ordineDaoImpl = new OrdineDaoImpl();
		dettaglioDaoImpl = new DettaglioDaoImpl();
	}
	public boolean addOrdine(OrdineBean ordine) {
		return ordineDaoImpl.addOrdine(ordine);
	}
	public boolean deleteOrdine(int idOrdine) {
		return ordineDaoImpl.deleteOrdine(idOrdine);
	}
	public boolean updateOrdine(OrdineBean ordine) {
		return ordineDaoImpl.updateOrdine(ordine);
	}
	public OrdineBean getOrdineById(int idOrdine) {
		return ordineDaoImpl.getOrdineById(idOrdine);
	}
	public ArrayList<OrdineBean> getAllOrdini(){
		return ordineDaoImpl.getAllOrdini();
	}
	public ArrayList<OrdineBean> getAllOrdiniByUser(String user){
		return ordineDaoImpl.getAllOrdiniByUser(user);
	}
	public boolean addDettaglio(DettaglioBean dettaglio) {
		return dettaglioDaoImpl.addDettaglio(dettaglio);
	}
	public boolean deleteDettaglio(int dettaglio) {
		return dettaglioDaoImpl.deleteDettaglio(dettaglio);
	}
	public boolean updateDettaglio(DettaglioBean dettaglio) {
		return dettaglioDaoImpl.updateDettaglio(dettaglio);
	}
	public DettaglioBean getDettaglioByIdDettaglio(int idDettaglio) {
		return dettaglioDaoImpl.getDettaglioByIdDettaglio(idDettaglio);
	}
	public ArrayList<ProdottoBean> getProdottiByIdOrdine(int idOrdine){
		return dettaglioDaoImpl.getProdottiByIdOrdine(idOrdine);
	}
	public ArrayList<DettaglioBean> getAllDettaglio(){
		return dettaglioDaoImpl.getAllDettaglio();
	}
	public boolean addOrdineStorico(OrdineStoricoBean ordineStorico) {
		return ordineStoricoDaoImpl.addOrdineStorico(ordineStorico);
	}
	public boolean deleteOrdineStorico(int idOrdine,int idProdotto) {
		return ordineStoricoDaoImpl.deleteOrdineStorico(idOrdine, idProdotto);
	}
	public boolean updateOridnestorico(OrdineStoricoBean ordineStorico) {
		return ordineStoricoDaoImpl.updateOridnestorico(ordineStorico);
	}
	public OrdineStoricoBean getOrdineStoricoById(int idOrdine,int idProdotto) {
		return ordineStoricoDaoImpl.getOrdineStoricoById(idOrdine, idProdotto);
	}
	public ArrayList<OrdineStoricoBean> getAllOrdineStorico(){
		return ordineStoricoDaoImpl.getAllOrdineStorico();
	}
	/* i prodotti dell'arrayList di ritorno hanno il valore quantit‡Disponibile che rappresenta il numero
	 * di prodotti che l'utente vuole acquistare
	 */
	public ArrayList<ProdottoBean> checkOrder(HashMap<Integer,Integer> mappaOrdine) throws IdProductNotValidException,NotEnoughNumberOfProductException{
		ProdottoService prodottoService = new ProdottoService();
		ArrayList<ProdottoBean> listaProdottiOrdine = new ArrayList<ProdottoBean>();
		for(Map.Entry<Integer, Integer> entry: mappaOrdine.entrySet()) {
			int idProdotto = entry.getKey();
			int quantitaAcquisto = entry.getValue();
			ProdottoBean temp = prodottoService.getProdottoById(idProdotto);
			if(temp==null) {
				throw new IdProductNotValidException();
			}
			if(temp.getQuantitaDisponibile()<quantitaAcquisto) {
				throw new NotEnoughNumberOfProductException();
			}
			temp.setQuantitaDisponibile(quantitaAcquisto);
			listaProdottiOrdine.add(temp);
		}
		return listaProdottiOrdine;
	}
	public OrdineBean confermaOrdine(HashMap<Integer,Integer> mappaOrdine,IndirizzoBean indirizzoUtente)throws IdProductNotValidException,NotEnoughNumberOfProductException{
		ProdottoService prodottoService = new ProdottoService();
		AddressService addressService = new AddressService();
		ArrayList<ProdottoBean> listaProdottiOrdine = checkOrder(mappaOrdine);
		OrdineBean ordine = new OrdineBean();
		int idOrdine = ordineDaoImpl.getSequence();
		ordine.setIdOrdine(idOrdine);
		ordine.setUtente(indirizzoUtente.getUtente());
		int idIndirizzoInput = indirizzoUtente.getIdIndirizzo();
		IndirizzoBean checkIndirizzo = addressService.getIndirizzoById(idIndirizzoInput);
		if(checkIndirizzo == null) {
			indirizzoUtente.setUltimoIndirizzo("Y");
			addressService.addAddress(indirizzoUtente);
			IndirizzoBean temp = addressService.getUltimoIndirizzoByUser(indirizzoUtente.getUtente());
			ordine.setIndirizzo(temp.getIdIndirizzo());
		}
		else {
			ordine.setIndirizzo(idIndirizzoInput);
			IndirizzoBean ultimoIndirizzoUsato = addressService.getUltimoIndirizzoByUser(indirizzoUtente.getUtente());
			ultimoIndirizzoUsato.setUltimoIndirizzo("N");
			addressService.updateIndirizzo(ultimoIndirizzoUsato);
			indirizzoUtente.setUltimoIndirizzo("Y");
			addressService.updateIndirizzo(indirizzoUtente);
		}
		double sommaCostoProdotti = 0;
		ordine.setDataOrdine(LocalDate.now());
		ordineDaoImpl.addOrdine(ordine);
		for(ProdottoBean prodotto:listaProdottiOrdine) {   
			DettaglioBean temp = new DettaglioBean();
			temp.setIdDettaglio(dettaglioDaoImpl.getSequence());
			temp.setIdOrdine(idOrdine);
			temp.setIdProdotto(prodotto.getIdProdotto());
			temp.setPrezzoUnitario(prodotto.getPrezzo());
			ProdottoBean prodottoVecchiaVersione = prodottoService.getProdottoById(prodotto.getIdProdotto());
			prodottoVecchiaVersione.setQuantitaDisponibile(prodottoVecchiaVersione.getQuantitaDisponibile()-prodotto.getQuantitaDisponibile());
			ProdottoBean prodottoAgggiornato = prodottoVecchiaVersione;
			prodottoService.updateProdotto(prodottoAgggiornato);
			temp.setQuantitaAcquistata(prodotto.getQuantitaDisponibile());
			dettaglioDaoImpl.addDettaglio(temp);
			sommaCostoProdotti += prodotto.getPrezzo()*prodotto.getQuantitaDisponibile();
		}
		ordine.setPrezzoTotale(sommaCostoProdotti);
		ordineDaoImpl.updateOrdine(ordine);
		return ordine;	
	} 
	/* i prodotti della listaProdottiOrdine devono avere il valore quantit‡Disponibile che rappresenta il numero
	 * di prodotti che l'utente vuole acquistare
	 */
	public double getTotalCost(HashMap<Integer,Integer> mappaOrdine) {
		ArrayList<ProdottoBean> listaProdottiOrdine = null;
		try {
			listaProdottiOrdine = checkOrder(mappaOrdine);
		}
		catch( IdProductNotValidException e) {
			logger.error(e.getStackTrace());
		}
		catch(NotEnoughNumberOfProductException e) {
			logger.error(e.getStackTrace());
		}
		double totalAmount = 0;
		for(ProdottoBean prodotto:listaProdottiOrdine) {
			totalAmount += prodotto.getPrezzo()*prodotto.getQuantitaDisponibile();
		}
		return totalAmount;
	}
	public boolean isBirthday(String username) {
		UserService userService = new UserService();
		UserBean utente = userService.getUserByUsername(username);
		LocalDate dataCompleanno = utente.getDataNascita();
		LocalDate oggi = LocalDate.now();
		if((dataCompleanno.getMonthValue()==oggi.getDayOfMonth())&&(dataCompleanno.getDayOfMonth()==oggi.getDayOfMonth())){
			return true;
		}
		else {
			return false;
		}
	}
	public double applySaleBirthday(double spesaUtente) {
		return spesaUtente*0.9;
	}
	public boolean isNoShippingFee(double spesaUtente) {
		if(spesaUtente>=100) {
			return true;
		}
		else
			return false;
	}
	/* i prodotti della listaProdottiOrdine devono avere il valore quantit‡Disponibile che rappresenta il numero
	 * di prodotti che l'utente acquista
	 */
	public double SummerSale(HashMap<Integer,Integer> mappaOrdine, int[] idCategoria) {
		ProdottoService prodottoService = new ProdottoService();
		ArrayList<ProdottoBean> listaProdottiOrdine = null;
		try {
			listaProdottiOrdine = checkOrder(mappaOrdine);
		}
		catch( IdProductNotValidException e) {
			logger.error(e.getStackTrace());
		}
		catch(NotEnoughNumberOfProductException e) {
			logger.error(e.getStackTrace());
		}
		double spesaScontata = getTotalCost(mappaOrdine);
		for(int i=0;i<idCategoria.length;i++) {
			ArrayList<ProdottoBean> listaProdottiScontati = prodottoService.getProdottoByCategoria(idCategoria[i]);
			ArrayList<Integer> listaProdottiScontatiId = new ArrayList<Integer>();
			for(ProdottoBean prodotto:listaProdottiScontati) {
				listaProdottiScontatiId.add(prodotto.getIdProdotto());
			}
			for(ProdottoBean prodotto:listaProdottiOrdine) {
				if(listaProdottiScontatiId.contains(prodotto.getIdProdotto())) {
					spesaScontata -= prodotto.getPrezzo()*prodotto.getQuantitaDisponibile()*0.25;
				}
			}
		}
		return spesaScontata;
	}
}
