<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ecommerce.prodotto.model.ProdottoBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
  <link rel="stylesheet" href="css/navbar.css">
  <link rel="stylesheet" href="css/index.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
<title>Prodotti</title>
</head>
<body>
<jsp:include page="navbar.jsp" />


<div class="container my_3">    
  	<div id="row" class="row">

		<c:forEach var="elem" items="${listaprodotti}">
			<div class="card"> 
       			 	<div class="card-prod-head" >
       			 		<h4>${elem.nome}</h4>
    			 	</div>
       				<div class="card-prod-body">
       					<img style="object-fit: contain;" src="${elem.url}" class="img-responsive cat-img" alt="Image">
       				</div>
       				<div class="card-prod-rear">
	        			<p>${elem.descrizione}</p>
	        			<p>${elem.prezzo} &dollar; </p>
	        			<input type="number" min='1' max='${elem.quantitaDisponibile}'/>
	        			<button id="add" class='addCart' type="button"  value='${elem.idProdotto}' onclick="aggiungiCarrello(${elem.idProdotto}, ${elem.prezzo}, this)"><i class="fas fa-cart-plus cart-icon"></i></button>	      				
       				</div>
    		</div>

		</c:forEach>
	</div>
</div>
<c:out value="${msg}"/>
<jsp:include page="footer.jsp" />
<script src="js/cart.js"></script>
</body>
</html>