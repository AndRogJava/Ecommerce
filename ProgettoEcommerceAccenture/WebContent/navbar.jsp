<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <link rel="stylesheet" href="css/socialNavbar.css">
<!-- <!DOCTYPE html>
  <html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style> 
    /* Remove the navbar's default rounded borders and increase the bottom margin */
    .navbar {
      margin-bottom: 50px;
      border-radius: 0;
      background-color: #EE7203;
    }
    /* Remove the jumbotron's default bottom margin 
    .jumbotron {
      margin-bottom: 0;
      background-color: white;
    }*/
    /* Add a gray background color and some padding to the footer 
    footer {
      background-color: #EE7203;
      padding: 25px;
    }
    footer p{
      color: black;
    }*/
    body {
      background-color: white;
      font-family: 'Ubuntu', sans-serif;
    }
  </style>
</head>
<body> -->

<!--  
<div class="jumbotron">
  <div class="container text-center">
    <h1>Online Store</h1>
    <p>Mission, Vission & Values</p>
  </div>
</div> -->
 

<% String user = (String) session.getAttribute("user"); %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="js/navBar.js"></script>

<nav class="navbar navbar-dark" style="background-color: #00ffc4ad;">
  <div class="container-fluid">
    <div class="navbar-header">
      
        <a class="navbar-brand" href="index.jsp">
        	<img src="./img/etsy.png" alt="logo" width="32" height="32" style="margin-left: 20px; margin-top: -7px">
        </a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
    <!-- class="active" -->
      <ul class="nav navbar-nav">
        <li id="l1"><a id="a1" href="index.jsp" style="color: black">Home</a></li>
        <li><a href="#"><span class="fa fa-facebook"></span></a></li>
   		<li><a href="#"> <span class="fa fa-twitter"></span></a> </li>
   		<li><a href="#"><span class="fa fa-instagram"></span></a></li>
   		<li><a href="#"><span class="fa fa-linkedin"></span> </a></li>
        <li id="l2" style="margin-left: 100px;"><a id="a2" href="Prodotto" style="color: black">Prodotti</a></li>
        <li id="l3"><a id="a3" href="offerte.jsp" style="color: black">Offerte</a></li>
        <li id="l4"><a id="a4" href="categorie.jsp" style="color: black">Categorie</a></li>
        <li id="l5"><a id="a5" href="contatti.jsp" style="color: black">Contatti</a></li>
      </ul>
     
      <ul class="nav navbar-nav navbar-right">
      <%if(user != null){ %>
      	<li><a href="#" style="color: black"><span class="glyphicon glyphicon-user" style="color: black; margin-left:10px"></span>Benvenuto <c:out value="${user}" /></a></li>
      	<li class="cart"><a onclick="sendCart(this)" style="color: black"><span class="glyphicon glyphicon-shopping-cart" style="color: black; margin-left:10px"></span>Carrello</a></li>
      	<li><a href="LoginServlet" style="color: black"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
      <%}else{ %>
      	<li><a href="login.jsp" style="color: black"><span class="glyphicon glyphicon-user" style="color: black"></span> Log In</a></li>
      <% } %>
        <!--<li><a href="#"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li> -->
      </ul>
    </div>
  </div>
</nav>
<script>setNavBar()</script>
<!--
<div class="container">
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">BLACK FRIDAY DEAL</div>
        <div class="panel-body"><img src="https://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
    <div class="col-sm-4">
      <div class="panel panel-danger">
        <div class="panel-heading" >BLACK FRIDAY DEAL</div>
        <div class="panel-body"><img src="https://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
    <div class="col-sm-4">
      <div class="panel panel-success">
        <div class="panel-heading">BLACK FRIDAY DEAL</div>
        <div class="panel-body"><img src="https://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
  </div>
</div><br>
<div class="container">
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">BLACK FRIDAY DEAL</div>
        <div class="panel-body"><img src="https://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">BLACK FRIDAY DEAL</div>
        <div class="panel-body"><img src="https://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">BLACK FRIDAY DEAL</div>
        <div class="panel-body"><img src="https://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
  </div>
</div><br><br>
<footer class="container-fluid text-center">
  <p>Online Store Copyright</p>
  <form class="form-inline">Get deals:
    <input type="email" class="form-control" size="50" placeholder="Email Address">
    <button type="button" class="btn btn-danger">Sign Up</button>
  </form>
</footer> 
</body>
</html> -->
