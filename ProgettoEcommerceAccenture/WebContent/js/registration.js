var username = $("#username");
var password = $("#password");
var confPassword = $("#confirmPassword");
var nome = $("#nome");
var cognome = $("#cognome");
var via = $("#via");
var numero = $("#numero");
var citta = $("#citta");
var cap = $("#cap");
var dataNascita = $("#data_nascita");
var regex = /(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,16}/;

username.on('keyup',function(){
	checkUsername();});
password.on('keyup',function(){
	checkPassword();});
nome.on('keyup',function(){
	checkNome();});
cognome.on('keyup',function(){
	checkCognome();});
confPassword.on('keyup',function(){
	checkConfirmPassword();});
dataNascita.on('keyup',function(){
	checkData();});
via.on('keyup',function(){
	checkIndirizzo();});
numero.on('keyup',function(){
	checkIndirizzo();});
citta.on('keyup',function(){
	checkIndirizzo();});
cap.on('keyup',function(){
	checkCap();
	checkIndirizzo();});

// Funzione per validare la registrazione
function Validate(){
	if(checkUsername() && checkNome() && checkCognome && checkPassword() && checkConfirmPassword() && checkData() && checkIndirizzo() )
		return true;
	else
		return false;
}

function checkUsername(){
	if(username.val() != "" && username.val().length >= 6){
		$("#errorUsername").html("");
		$("#errorUsername").addClass("hideDiv");
		return true;
		
	}else{
		$("#errorUsername").html("L'username deve essere di almeno 6 caratteri");
		$("#errorUsername").removeClass("hideDiv");
		return false;
	}
}
function checkPassword(){
	if(password.val() != "" && password.val().match(regex)){
		$("#errorPassword").html("");
		$("#errorPassword").addClass("hideDiv");
		return true;
	}else{
		$("#errorPassword").html("Almeno 6 caratteri, max 16, lettera maiuscola, minuscola, numero e carattere speciale");
		$("#errorPassword").removeClass("hideDiv");
		return false;
	}
}

function checkNome(){
	if(nome.val() != ""){
		$("#errorNome").html("");
		$("#errorNome").addClass("hideDiv");
		return true;
	}else{
		$("#errorNome").html("Il nome &egrave; obbligatorio");
		$("#errorNome").removeClass("hideDiv");
		return false;
	}
}

function checkCognome(){
	if(nome.val() != ""){
		$("#errorCognome").html("");
		$("#errorCognome").addClass("hideDiv");
		return true;
	}else{
		$("#errorCognome").html("Il cognome &egrave; obbligatorio");
		$("#errorCognome").removeClass("hideDiv");
		return false;
	}
}
function checkConfirmPassword(){
	if(password.val() == confPassword.val()){
		$("#errorConfermaPassword").html("");
		$("#errorConfermaPassword").addClass("hideDiv");
		return true;
	}else{
		$("#errorConfermaPassword").html("Le due password sono diverse");
		$("#errorConfermaPassword").removeClass("hideDiv");
		return false;
	}
}

function upData(){
	var toDay = new Date();
	var toDayDate = toDay.getFullYear()+'/'+(toDay.getMonth()+1)+'/'+toDay.getDate();
	return toDayDate;
}

function checkData(){
	if(dataNascita.val() != "" && dataNascita.val() > "1900/01/01" && dataNascita.val() < upData()){
		$("#errorDataNascita").html("");
		$("#errorDataNascita").addClass("hideDiv");
		return true;
	}else{
		$("#errorDataNascita").html("La data deve essere compresa tra il 1900 e il "+upData());
		$("#errorDataNascita").removeClass("hideDiv");
		return false;
	}
}

function checkIndirizzo(){
	if(checkVia() && checkNumero() && checkCitta() && checkCap()){
		$("#errorIndirizzo").html("");
		$("#errorIndirizzo").addClass("hideDiv");
		return true;
	}
	else {
		return false;
	}
}

function checkVia() {
	if(via.val() != "") {
		$("#errorIndirizzo").html("");
		$("#errorIndirizzo").addClass("hideDiv");
		return true;
	}
	else {
		$("#errorIndirizzo").html("Inserire la via");
		$("#errorIndirizzo").removeClass("hideDiv");
		return false;
	}
}

function checkNumero() {
	var numRegex = /([0-9])/;
	if(numero.val() != "" && numero.val().match(numRegex)) {
		$("#errorIndirizzo").html("");
		$("#errorIndirizzo").addClass("hideDiv");
		return true;
	}
	else {
		$("#errorIndirizzo").html("Il campo numero deve contenere almeno un numero");
		$("#errorIndirizzo").removeClass("hideDiv");
		return false;
	}
}

function checkCitta() {
	if(citta.val() != "") {
		$("#errorIndirizzo").html("");
		$("#errorIndirizzo").addClass("hideDiv");
		return true;
	}
	else {
		$("#errorIndirizzo").html("Inserire citt&agrave;");
		$("#errorIndirizzo").removeClass("hideDiv");
		return false;
	}
}

function checkCap() {
	if(cap.val().length == 5) {
		$("#errorIndirizzo").html("");
		$("#errorIndirizzo").addClass("hideDiv");
		return true;
	}
	else {
		$("#errorIndirizzo").html("Il CAP deve essere lungo 5 caratteri");
		$("#errorIndirizzo").removeClass("hideDiv");
		return false;
	}
}




