<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- File CSS -->
    <link rel="stylesheet" href="css/login.css">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Sign in</title>
  </head>
  <body>

    <div class="d-flex bg_grad flex-column justify-content-center align-items-center">
      <div class="login_container">
      
        <form class="text-center" action="LoginServlet" id="formLogin" method="post" onsubmit="return verify()">
          
          <div class="input_container d-flex w-100">
            <i class="far fa-user icon d-flex align-items-center justify-content-center"></i>
            <input id="username" name="username" type="text" placeholder="Username">
          </div>
          <p class="text-center text-danger mt-1"><span id="userError"></span></p>
          
          <div class="input_container d-flex w-100">
            <i class="fas fa-key icon d-flex align-items-center justify-content-center"></i>
            <input id="password" name="password" type="password" placeholder="Password">
          </div>
          <p class="text-center text-danger mt-1"><span id="passError"></span></p>
          
          <button type = "submit" class="submit text-uppercase" form="formLogin" >Login</button>
        </form>
        <p class="text-center text-danger mt-1"> <c:out value="${msg}" /> </p>
      </div>
      <p class="forgot text-center mt-4"><a href="registration.jsp" style="color: #06D4B9">Registrati</a></p>
    </div>

    <!-- File JS -->
    <script src="js/login.js"></script>
  </body>
</html>
