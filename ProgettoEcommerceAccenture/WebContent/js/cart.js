

/*let carts = document.querySelectorAll('.addCart');

/*for(let i=0; i < carts.length; i++){
	carts[i].addEventListener('click', () => {
		cartNumbers();
	})
}

function cartNumbers(){
	let productNumbers = localStorage.getItem('cartNumbers');
	productNumbers = parseInt(productNumbers);
	if(productNumbers){
		localStorage.setItem('cartNumbers', productNumbers + 1);
		document.querySelector('.cart span').textContent = productNumbers + 1;
	}else{
		localStorage.setItem('cartNumbers',1);
		document.querySelector('.cart span').textContent = 1;
	}
} */

var listaProdotti = [];


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
	var oldQta = sessionStorage.getItem("qtaTot");
	console.log("oldQta: " + oldQta);
	
	console.log("newQta: " + (oldQta+qta));
	
	
	qta = parseInt(qta);
	console.log(qta);
	totale += qta;
	document.querySelector('.cart span').textContent = "(" + totale + ")";
	sessionStorage.setItem("qtaTot",totale);
}

function creaCarrello(){
	sessionStorage.setItem("shoppingCart",JSON.stringify(listaProdotti));
}

function sendCart(thiss){
	creaCarrello();
	
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

if(sessionStorage.getItem("shoppingCart") != null){
	loadCart();
}

if(sessionStorage.getItem("qtaTot") != null){
	//sessionStorage.removeItem("qtaTot");
	document.querySelector('.cart span').textContent = "(" + sessionStorage.getItem("qtaTot") + ")";
}

