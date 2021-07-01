<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
  <link rel="stylesheet" href="css/navbar.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
</head>
<body>
<jsp:include page="navbar.jsp" />

<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active" style="background-color:#58a9e8;">
        <img src="img/ecommerce2.png"   style="width:750px;  height: 400px;"   alt="Image">
        <div class="carousel-caption">
          
        </div>      
      </div>

      <div class="item" style="background-color:#0182c6;">
        <img src="img/saldi2.png"   style="width:700px;  height: 400px;" alt="Image">
        <div class="carousel-caption">
        </div>      
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
</div>
  

<div class="container" style="margin-top:20px;">    
  <div id="row" class="row">
  <%-- <c:forEach var="elem" items="${listacategorie }">
			<div class="col-sm-4"> 
      			<div class="panel panel-warning">
       			 	<div class="panel-heading">${elem.nome} </div>
       			 	
       				<div class="panel-body"> <a href= "WelcomServlet?id=${elem.idCategoria}">  <img src="${elem.urlImmagine}" class="img-responsive" style="width:400px; height:200px;" alt="Image" ></a></div>
        			<div class="panel-footer">${elem.descrizione}</div>
      			</div>
    		</div>
		</c:forEach> --%>
	</div>
</div>
  
  
  

  
  <!--  <div class="col-sm-4">
      <div class="panel panel-warning">
        <div class="panel-heading" >Negozio articoli scolastici</div>
        <div class="panel-body"><img src="bootstrap/img/diario.png" class="img-responsive" style="width:400px; height:200px;"alt="Image"></div>
        <div class="panel-footer">Vendita articoli da scuola</div>
      </div>
    </div>
    <div class="col-sm-4"> 
      <div class="panel panel-warning">
        <div class="panel-heading">Negozio di elettronica</div>
        <div class="panel-body"><img src="bootstrap/img/tecnologia.png" class="img-responsive" style="width:400px; height:200px;" alt="Image"></div>
        <div class="panel-footer">Vendita computer e telefoni</div>
      </div>
    </div>
    <div class="col-sm-4"> 
      <div class="panel panel-warning">
        <div class="panel-heading">Negozio musicale</div>
        <div class="panel-body"><img src="bootstrap/img/cd1.png" class="img-responsive" style="width:400px; height:200px;" alt="Image"></div>
        <div class="panel-footer">Vendita cd musicali</div>
      </div>
    </div>
  </div>
</div><br>

<div class="container">    
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-warning">
        <div class="panel-heading">Negozio di abbigliamento</div>
        <div class="panel-body"><img src="bootstrap/img/abbigliamento.png" class="img-responsive" style="width:400px; height:200px;" alt="Image"></div>
        <div class="panel-footer">Vendita abbigliamento</div>
      </div>
    </div>
    <div class="col-sm-4"> 
      <div class="panel panel-warning">
        <div class="panel-heading">Negozio alimentare</div>
        <div class="panel-body"><img src="bootstrap/img/cibi.png"class="img-responsive" style="width:400px; height:200px;" alt="Image"></div>
        <div class="panel-footer">Vendita alimentari</div>
      </div>
    </div>
    <div class="col-sm-4"> 
      <div class="panel panel-warning">
        <div class="panel-heading">Gioielleria</div>
        <div class="panel-body"><img src="bootstrap/img/gioeilli.png" class="img-responsive" style="width:400px; height:200px;" alt="Image"></div>
        <div class="panel-footer">Vendita gioielli</div>
      </div>
    </div>
  </div>
</div><br><br>-->


<jsp:include page="footer.jsp" />
</body>

<script src= "js/index.js"></script>
<script src= "js/ordine.js"></script>

</html>