var listaProdotti = [];
var newAddress = false;


function aggiungiCarrello(id, prezzo, thiss){
	
	var cart = new Object();
	
	cart.idProdotto=id;
	//cart.prezzoProdotto=prezzo;
	var qta = $(thiss).prev().val();
	cart.quantita = qta;
	listaProdotti.push(cart);
	//console.log(qta);
	//sessionStorage.setItem("shoppingCart",JSON.stringify(listaProdotti));
	console.log(listaProdotti);
	aggiornaCarrello(qta);
	
}

var totale = 0;

function aggiornaCarrello(qta){
	qta = parseInt(qta);
	console.log(qta);
	totale += qta;
	document.querySelector('.cart span').textContent = "(" + totale + ")";
	sessionStorage.setItem("qtaTot",totale);
}

function creaCarrello(){
	sessionStorage.setItem("shoppingCart",JSON.stringify(listaProdotti));
}

function sendCart(){
	creaCarrello();
	var url = 'CartServlet';
	/*var xhr = new XMLHttpRequest();
	xhr.open("POST", "http://localhost:9910/mymagic/mymagic");
	xhr.setRequestHeader("content-type", "application/json");
	xhr.send(sessionStorage.getItem("shoppingCart"));*/
	
	$.ajax({
		url: "CartServlet",
		type:"POST",
		dataType: "json",
		data: sessionStorage.getItem("shoppingCart"),
		contentType: 'application/json',
	    mimeType: 'application/json',
	    async:false,
	    success:function(data){
	    	window.location.replace('cart.jsp');
		}
	});	
}



function loadCart(){
	listaProdotti = JSON.parse(sessionStorage.getItem("shoppingCart"));
}

function addQuantity(id,thiss){
	$.each(listaProdotti, function(i,prodotto){
		if(prodotto.idProdotto == id){
			prodotto.quantita++;
			$(thiss).parent().siblings(".input-qty").val(prodotto.quantita);
			aggiornaCarrello(prodotto.quantita);
		}
	});
	
}
function removeQuantity(id,thiss){
	$.each(listaProdotti,function(i,prodotto){
		if(prodotto.idProdotto == id){
			prodotto.quantita--;
			$(thiss).parent().siblings(".input-qty").val(prodotto.quantita);
			aggiornaCarrello(prodotto.quantita);
		}
	});
}

function checkout(){
	
	var indirizzo = new Object();
	//indirizzo.id= $("#idIndirizzo").val();
	var json={
			products:[],
			address:{
				
			}
	}
	$.each(listaProdotti,function(i,prodotto){
		json.products.push({"id" : prodotto.idProdotto, "quantity": prodotto.quantita});
	})
	
	if(newAddress == true) {
		json.address["via"] = $("#via").val();
		json.address["numero"] = $("#numero").val();
		json.address["citta"] = $("#citta").val();
		json.address["cap"] = $("#cap").val();
	}
	else {
		json.address["via"] = $("#viaLabel").html();
		json.address["numero"] = $("#numLabel").html();
		json.address["citta"] =  $("#cittaLabel").html();
		json.address["cap"] = $("#capLabel").html();	
	}
	/*indirizzo.via = $("#via").val();
	indirizzo.numero = $("#numero").val();
	indirizzo.citta= $("#citta").val();
	indirizzo.cap = $("#cap").val();*/
	
	
	
	var obj = JSON.stringify(json);
	console.log(obj);
	$.ajax({
		url: "OrdineServlet",
		type:"POST",
		dataType: "json",
		data: obj,
		contentType: 'application/json',
	    mimeType: 'application/json',
	    success:function(data){
	    	alert("Ordine effettuato con successo!");
			sessionStorage.clear();
			listaProdotti = [];
			window.location = "index.jsp";
		}
	});	
}
function removeProduct(id){
	console.log(id);
	
	listaProdotti = JSON.parse(sessionStorage.getItem("shoppingCart"));
	
	var qtaDel;
	var listaProdottiTemp = [];
	for(i = 0; i < listaProdotti.length; i++) {
		if(listaProdotti[i].idProdotto != id) {
			console.log(listaProdotti[i]);
			listaProdottiTemp.push(listaProdotti[i]);
		}
		else {
			qtaDel = listaProdotti[i].quantita;
		}
	}


	listaProdotti = listaProdottiTemp;
	console.log(listaProdotti);
	console.log(qtaDel);
	
	sessionStorage.setItem("shoppingCart",JSON.stringify(listaProdotti));
	var oldQta = sessionStorage.getItem("qtaTot");
	sessionStorage.setItem("qtaTot", oldQta - qtaDel);
	
	sendCart();
	
	/*
	$.each(listaProdotti,function(i,prodotto){
		if(prodotto.idProdotto == id){
			for(a= i,b=i+1; b< listaProdotti.length;a++){
				listaProdotti[a]=listaProdotti[b];
			}
			listaProdotti[listaProdotti.length-1]=null;		
		}
	});*/
}

if(sessionStorage.getItem("shoppingCart") != null){
	loadCart();
}

if(sessionStorage.getItem("qtaTot") != null){
	//sessionStorage.removeItem("qtaTot");
	document.querySelector('.cart span').textContent = "(" + sessionStorage.getItem("qtaTot") + ")";
}

function showNewAddress() {
	$(".orderAddress").hide();
	$(".newAddress").show();
}

function showOldAddress() {
	$(".orderAddress").show();
	$(".newAddress").hide();
}

