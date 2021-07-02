<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="bootstrap.css">
  <link rel="stylesheet" href="css/navbar.css">
    <link rel="stylesheet" href="css/my.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Modifica dati </title>
</head>
<body>
<jsp:include page="navbar.jsp" />
	<div class="col-md-12">
		<div class="col-md-8">
			<h2>Ordini</h2>
			<div id="cardOrdini"></div>
		</div>
		
		<div class="col-md-4">
			<h2>Account</h2>
			<div id="cardAccount" class="cardAccount">
			<!-- 
				<label class="accountLabel">Username:</label>
				<div class="nameCard text-center"><h3>pippo11</h3></div>
				
				<label class="accountLabel">Nome e cognome:</label>
				<div class="nameCard text-center"><h3>Pippo Pluto</h3></div>
				
				<label class="accountLabel">Data di nascita:</label>
				<div class="nameCard text-center"><h3>1/11/1999</h3></div>
				
				<label class="accountLabel">Indirizzo:</label>
				<div class="nameCard text-center"><h3>via Monte Bianco, 22</h3></div>
				<div class="nameCard text-center"><h3>Milano (20030)</h3></div> -->
			</div>
		</div>
	</div>



</body>
<div style="display:none">
	<jsp:include page="footer.jsp" />
</div>

<script src="js/my.js"></script>
<script>
	getOrdersById("${user}");
	getUserByUsername("${user}");
</script>
</html>