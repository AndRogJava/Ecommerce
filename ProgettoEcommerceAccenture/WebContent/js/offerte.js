$(document).ready(function(){
	$.ajax({
		url:"OffertaServlet",
		method:"GET",
		dataType:"json",
		contentType: 'application/json',
        mimeType: 'application/json',
		success:function(data){
			var testo = new String();
			$.each(data,function(i,offerta){
				testo += '<div class="col-sm-4"><div class="panel panel-warning"><div class="panel-heading">'+offerta.nome+'</div>';
				testo += '<div class="panel-body"><img src="'+offerta.url+'" class="img-responsive" style="width:400px; height:200px;" alt="Image"></div>';
				testo += '<div class="panel-footer" style="display: block;width: 292px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">'+offerta.descrizione+'</div>';
				testo += '<div class="panel-footer" style="display: block;width: 292px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">'+offerta.prezzo+' Euro</div>';
				testo += '<div class="panel-footer"><input style="width:50%" type="number" min="1" max='+offerta.quantitaDisponibile+'/>';
				testo += '<button class="addCart" style="width:50%" type="button"  value="'+offerta.idProdotto+'" onclick="aggiungiCarrello('+offerta.idProdotto+', '+offerta.prezzo+', '+this+')">Aggiungi al carrelo</button></div></div></div>';
			});
			
			$('#row').html(testo);
		}
	});
});
