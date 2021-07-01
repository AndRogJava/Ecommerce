<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Registration Form</title>
  <link rel="stylesheet" href="css/registration.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
</head>
<body  style="background-color:#E3981C">
<div class="wrapper">
  <div class="title">
    Registration Form
  </div>
  <div class="form">
  	<form method="post" action="RegistrationServlet" onsubmit="return Validate()">
    <div class="inputfield">
      <label>Username</label>
      <input id="username" name="username" type="text" class="input" required>
    </div>
    <div id="errorUsername" class="hideDiv alert alert-danger" role="alert">
	</div>
    <div class="inputfield">
      <label>Nome</label>
      <input id="nome" name="nome" type="text" class="input" required>
    </div>
    <div id="errorNome" class="hideDiv alert alert-danger" role="alert">
	</div>
    <div class="inputfield">
      <label>Cognome</label>
      <input id="cognome" name="cognome" type="text" class="input" required>
    </div>
    <div id="errorCognome" class="hideDiv alert alert-danger" role="alert">
	</div>
    <div class="inputfield">
      <label>Password</label>
      <input id="password" name="password" type="password" class="input" required>
    </div>
    <div id="errorPassword" class="hideDiv alert alert-danger" role="alert">
	</div>
    <div class="inputfield">
      <label>Conferma Password</label>
      <input type="password" id="confirmPassword" name="confirmPassword" class="input" required>
    </div>
    <div id="errorConfermaPassword" class="hideDiv alert alert-danger" role="alert">
	</div>

    <div class="inputfield">
      <label>Data di nascita</label>
      <input id="data_nascita" name="dataNascita" type="date" class="input" placeholder="dd/mm/yyyy" required>
    </div>
    <div id="errorDataNascita" class="hideDiv alert alert-danger" role="alert">
	</div>

    <div class="inputfield">
      <label>Via</label>
      <input id="via" name="via" type="text" class="input" required>
      <label style="margin-left: 10px">Numero</label>
      <input id="numero" name="numero" type="text" class="input" required>
    </div>
    <div class="inputfield">
      <label>Citt&agrave;</label>
      <input id="citta" name="citta" type="text" class="input" required>
      <label style="margin-left: 10px">CAP</label>
      <input id="cap" name="cap" type="number" class="input" required>
    </div>
    <div id="errorIndirizzo" class="hideDiv alert alert-danger" role="alert">
	</div>
    <div class="inputfield terms">
      <label class="check">
        <input type="checkbox" required>
        <span class="checkmark"></span>
      </label>
      <p>Agreed to terms and conditions</p>
    </div>
    <div class="inputfield">
      <input type="submit" value="Registrati" class="btn">
    </div>
    </form>
    
        <p align="left"> <c:out value="${msg}" /> </p>
  </div>
</div>
</body>
<script src="js/registration.js"></script>
</html>
