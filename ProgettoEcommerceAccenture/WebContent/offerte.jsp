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
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Offerte</title>
</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="container" style="margin-top:20px;"> 
	<div id="row">
	 	<%--<c:forEach var="elem" items="${listaprodotti}">
			<div class="col-sm-4"> 
      			<div class="panel panel-warning">
       			 	<div class="panel-heading">${elem.nome} </div>
       				<div class="panel-body"><img src="${elem.url}" class="img-responsive" style="width:400px; height:200px;" alt="Image"></div>
        			<div class="panel-footer" style="display: block;width: 292px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">${elem.descrizione}</div>
        			<div class="panel-footer" style="display: block;width: 292px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">${elem.prezzo} Euro</div>
        			<div class="panel-footer" ><input style="width:50%" type="number" min='1' max='${elem.quantitaDisponibile}'/>
        				<button class='addCart' style="width:50%" type="button"  value='${elem.idProdotto}' onclick="aggiungiCarrello(${elem.idProdotto}, ${elem.prezzo}, this)">Aggiungi al carrelo</button>
        			</div>
        			
      			</div>
    		</div>
		</c:forEach>--%>
	</div>
</div>

<jsp:include page="footer.jsp" />

<script src= "js/offerte.js"></script>
</body>
</html>
