/*

funzioni per formattare la i pulsanti sulla navbar a seconda dell'indirizzo
url richiesto dall' utente.

*/

function setNavBar() {
	const url = window.location.pathname.split(`/`)[2]; //ottengo porzione url dopo il secondo "/"
	let finalUrl= url.substr(0, url.indexOf('/'));  //taglio la stringa ottenuta al terzo "/"
	if (finalUrl == "") finalUrl = url; //se la stringa ottenuta dopo il taglio e' vuota allora riprendo la stringa precedente
	//trovo il tag <a> corrispondente all'url scelto dall'utente e lo formatto.
	$('ul.nav a[href="'+ url +'"]').parent().addClass('active');
	$('ul.nav a[href="'+ url +'"]').css('color', 'white');
}
