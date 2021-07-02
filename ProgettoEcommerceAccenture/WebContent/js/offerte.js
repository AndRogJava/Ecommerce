$(document).ready(function(){
	$.ajax({
		url:"OffertaServlet",
		method:"GET",
		dataType:"json",
		contentType: 'application/json',
        mimeType: 'application/json',
		success:function(data){
			console.log(data);
			var testo = new String();
			$.each(data,function(i,offerta){
				testo += `
                    <div class="card">
                        
                        <div class="card-prod-head">
                            <h4>` + offerta.nome + `</h4>
                        </div>
                        <div class="card-prod-body">
                        	<img style="object-fit: contain;" src="` + offerta.url + `" class="img-responsive cat-img" alt="Image">                		
                        </div>
                        <div class="card-prod-rear">
                            <p>` + offerta.descrizione + `</p>
                        	<p id="sconto">` + (offerta.prezzo + Math.ceil(offerta.prezzo / 100 * 30)) + ` &dollar; </p>
                            <p id="finalprice">` + offerta.prezzo + ` &dollar; </p>
                            <input type="number" min="1" max="` + offerta.quantitaDisponibile + `"/>
                            <button id="add" class="addCart" type="button" value="` + offerta.idProdotto + `" onclick="aggiungiCarrello(` + offerta.idProdotto + `, ` + offerta.prezzo + `, ` + this + `)"><i class="fas fa-cart-plus cart-icon"></i></button>
                        </div>
                    </div>`;
			});
			
			$('#row').html(testo);
		}
	});
	
});
