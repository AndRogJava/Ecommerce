<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="ecommerce.user.model.IndirizzoBean" %>
<%@ page import="ecommerce.prodotto.model.ProdottoBean" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Carrello</title>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="css/cart.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  
  <link rel="stylesheet" href="css/navbar.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<jsp:include page="navbar.jsp" />
<body style="background-color:#FFF">
	
	<% HashMap<IndirizzoBean,ArrayList<ProdottoBean>> lista = (HashMap<IndirizzoBean,ArrayList<ProdottoBean>>) session.getAttribute("lista");%>

    <!-- Cart -->
    <div class="col-md-12">
    	<h1 class="text-center"><span class="title">Carrello</span></h1>
		<div class="col-md-8 hero-feature">
			<c:forEach var="entry" items="${lista}">
				<c:forEach var="prodotto" items="${entry.value}">			
					<div class="card cardProduct" id="cardProduct_${prodotto.idProdotto}">
						<div class="row no-gutters">
					    	<div class="col-md-4">
					    		<img src="${prodotto.url}" class="card-img img-responsive" alt="..." width=100 height=100>
					    	</div>
						    <div class="col-md-8">
						    	<div class="card-body">
						        	<h3 class="card-title" style="display: inline">${prodotto.nome}</h3>
						        	<div style="float:right; margin-right:10px">
    									<h2 style="display: inline" id="price_${prodotto.idProdotto}">${prodotto.prezzo}</h2>
						        		<h3 style="display: inline">€</h3>
						        	</div>

						        	<p class="card-text" style="color:#707070">${prodotto.descrizione}</p>
						        	
						        	<div class="input-group">
							        	<span class="input-group-btn">
							            	<button type="button" class="btn btn-default bootstrap-touchspin-down" onclick="removeQuantity(${prodotto.idProdotto},this)">
							                	<span class="glyphicon glyphicon-minus"></span>
							                </button>
							            </span>    
							            <input type="text" name="" id="qta_${prodotto.idProdotto}" value="<c:out value="${prodotto.quantitaDisponibile}"/>" class="form-control input-number input-qty">
							            <span class="input-group-btn">
							            	<button type="button" class="btn btn-default btn-number" onclick="addQuantity(${prodotto.idProdotto},this)">
							                	<span class="glyphicon glyphicon-plus"></span>
							                </button>
							            </span>
							        </div>
						        	
						        	<button class="btn btn-danger remove_cart" onclick="removeProduct(${prodotto.idProdotto})" rel="2"><i class="fa fa-trash-o" style="margin-right:5px"></i>Rimuovi</button>
						      	</div>
						    </div>
						</div>
					</div>
				</c:forEach>	
			</c:forEach>
    	</div>
    	
    	<div class="col-md-4">
		    <div class="cardPrice">
		    	<h3 class=""><span class="title">Prezzo totale</span></h3>
				<div class="priceContainer text-center"><h3>180.99 Euro</h3></div>
				<p>Applica coupon:</p>
				<input type="text" placeholder="Coupon" id="coupon" class="form-control" style="display:inline-block">
				<button class="btn btn-primary" style="display:inline-block">Applica</button>
			</div>
    	
    		<div class="orderAddress cardAddress">
    			<h3 class=""><span class="title">Indirizzo di spedizione</span></h3>
				<c:forEach var="entry" items="${lista}">
					<b>L'ordine sarà spedito presso:</b>
					<p>
						via <label id="viaLabel"><c:out value="${entry.key.via}"/></label>, <label id="numLabel"><c:out value="${entry.key.numero}"/></label> - 
						<label id="cittaLabel"><c:out value="${entry.key.citta}"/></label> (<label id="capLabel"><c:out value="${entry.key.cap}"/></label>)
					</p>
				</c:forEach>
				<div class="text-center" style="display:none">
					<button class="btn btn-primary" onclick="showNewAddress()">Modifica indirizzo</button>
				</div>
			</div>
				
			<div class="newAddress">
			    <b style="margin-top:20px">Nuovo indirizzo di spedizione</b>
			    <div class="form-group text-center" style="margin-top: 15px">
					<input id="via" name="via" type="text" class="form-control" placeholder="Via" required>
					<input id="numero" name="numero" type="text" class="form-control" placeholder="Numero" required>
					<input id="citta" name="citta" type="text" class="form-control" placeholder="Città" required>
					<input id="cap" name="cap" type="number" class="form-control" placeholder="CAP" required>
				</div>
				<div class="text-center">
					<button class="btn btn-primary" onclick="showOldAddress()">Usa indirizzo precedente</button>
				</div>
				<div id="errorIndirizzo" class="hideDiv alert alert-danger" role="alert" style="display:none"></div>
			</div>
    	</div>
    </div>
    
    <div class="text-center">
    	<div class="btn-group btns-cart">
        	<button type="button"  onclick="location.href='index.jsp'" class="btn btn-primary"><i class="fa fa-arrow-circle-left" style="margin-right:5px"></i>Continua shopping</button>
          	<button type="button" class="btn btn-primary" onclick="checkout()">Checkout<i class="fa fa-arrow-circle-right" style="margin-left:5px"></i></button>
      	</div>
    </div>
   
   	<div id="footer">
   		<jsp:include page="footer.jsp" />
   	</div>
</body>
<script src="js/cart.js"></script>
<script src="js/ordine.js"></script>
</html>