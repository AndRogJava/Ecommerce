/*var username = document.forms[""]["username"];
var password = document.forms[""]["password"];*/

var regex = /(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,16}/;
var username = document.getElementById("username");
var password = document.getElementById("password");

/*var user_error = document.getElementById('userError');
var password_error = document.getElementById('passError');


username.addEventListener('keyup', userVerify, true);
password.addEventListener('keyup', passVerify, true);*/


function Validate() {
	if(userVerify() && passVerify())
		return true;
	else
		return false;
}

function userVerify() {
	  if (username.value == "") {
		  username.style.border = "1px solid red";
		  user_error.innerHTML = "Username è obbligatorio";
		  username.focus();
		  return false;
	  }else if(username.value.length < 3){
		  username.style.border = "1px solid red";
		  user_error.innerHTML = "Username deve avere almeno 3 caratteri";
		  username.focus();
		  return false;
	  }else{
		  username.style.border="1px solid green";
		  user_error.innerHTML = "";
		  return true;
	  }
}

function passVerify() {
	  if (password.value == "") {
		  password.style.border = "1px solid red";
		  password_error.textContent = "La password è obbligatoria";
		  password.focus();
		  return false;
	  }else if(!password.value.match(regex)){
		  password.style.border = "1px solid red";
		  password_error.innerHTML = "La password deve contenere almeno: una lettera minuscola, maiuscola, un numero, un carattere speciale";
		  password.focus();
		  return false;
	  }else{
		  password.style.border = "1px solid green";
		  password_error.innerHTML = "";
		  return true;
	  }
	 
}


function verify(){
	console.log("Sono in verify() javascript");
	if(username.value.length < 3){
		  username.style.border = "1px solid red";
		  document.getElementById("userError").innerHTML = "Username deve avere almeno 3 caratteri";
		  username.focus();
		  return false;
	  }else{
		  username.style.border="1px solid green";
		  document.getElementById("userError").innerHTML = "";
	  }
	
	if(!password.value.match(regex)){
		  password.style.border = "1px solid red";
		  document.getElementById("passError").innerHTML = "La password deve contenere almeno: una lettera minuscola, maiuscola, un numero, un carattere speciale";
		  password.focus();
		  return false;
	  }else{
		  password.style.border = "1px solid green";
		  document.getElementById("passError").innerHTML = "";
	  }
	return true;
	
}