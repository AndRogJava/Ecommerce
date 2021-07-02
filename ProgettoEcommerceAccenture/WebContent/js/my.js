function getOrdersById(user) {
	var userJson = {"user": user};
	
	$.ajax({
		url:"OrdineServlet",
		method:"GET",
		dataType:"json",
		data: userJson,
		contentType: 'application/json',
        mimeType: 'application/json',
		success:function(data){
			var s = "";
			$.each(data, function(key, value) {				
				s += '<div class="col-md-12 card singleOrder" id="singleOrder'+data[key].idOrdine+'">';
				s += '<div class="col-md-4">';
				s += '<label>Ordine effettuato il:</label>';
				s += '<div class="dataCard text-center"><h3>'+ getDate(data[key].dataOrdine) +'</h3></div>';
				s += '</div>';
				s += '<div class="col-md-4">';
				s += '<label>Prezzo totale:</label>';
				s += '<div class="priceCard text-center"><h3>'+ data[key].prezzoTotale +' Euro</h3></div>';
				s += '</div>';
				s += '<div class="buttonCard col-md-4 text-center"><button class="btn btn-primary" onclick="viewDetail('+data[key].idOrdine+')">Dettaglio</button></div>';
				s += '</div>';
			});
			
			$('#cardOrdini').html(s);
		}
	});
}

function getDate(data) {
	return data.dayOfMonth + "/" + data.monthValue + "/" + data.year;
}


function getUserByUsername(user) {
	var userJson = {"user": user};
	
	$.ajax({
		url:"UserServlet",
		method:"GET",
		dataType:"json",
		data: userJson,
		contentType: 'application/json',
        mimeType: 'application/json',
		success:function(data){			
			var s = "";
			s += '<label class="accountLabel">Username:</label>';
			s += '<div class="nameCard text-center"><h3>'+ data.username +'</h3></div>';
			s += '<label class="accountLabel">Nome e cognome:</label>';
			s += '<div class="nameCard text-center"><h3>'+ data.nome + ' ' + data.cognome +'</h3></div>';
			s += '<label class="accountLabel">Data di nascita:</label>';
			s += '<div class="nameCard text-center"><h3>'+ getDate(data.dataNascita) +'</h3></div>';
			
			$('#cardAccount').html(s);
			
			getAddressByUsername(user);
		}
	});
}



function getAddressByUsername(user) {
	var userJson = {"user": user};
	
	$.ajax({
		url:"AddressServlet",
		method:"GET",
		dataType:"json",
		data: userJson,
		contentType: 'application/json',
        mimeType: 'application/json',
		success:function(data){
			var s = "";
			s += '<label class="accountLabel">Indirizzo:</label>';
			s += '<div class="nameCard text-center"><h3>via '+ data.via +', '+ data.numero +'</h3></div>';
			s += '<div class="nameCard text-center"><h3>'+ data.citta +' ('+ data.cap +')</h3></div>';
			
			$('#cardAccount').append(s);
		}
	});
}

function viewDetail(idOrdine) {
	var s = '<label>fieuhiwhgihrgihiug5:</label>'
	$("#singleOrder"+id).append();
}


