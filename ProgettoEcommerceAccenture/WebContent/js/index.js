$(document).ready(function(){
	$.ajax({
		url:"CategoriaServlet",
		method:"GET",
		dataType:"json",
		contentType: 'application/json',
        mimeType: 'application/json',
		success:function(data){
			var testo = new String();
			$.each(data,function(i,categoria){
				testo += `
                    <div class="card">
                    	
                        <a href= "WelcomeServlet?id=` + categoria.idCategoria +`">  
	                        <div class="card-head">
	                        	<h4>` + categoria.nome + `</h4>
                        	</div>
			        		<div class="card-body">
                            	<img src= "` + categoria.urlImmagine + `" class="img-responsive cat-img" alt="Image" >
                            </div>
                        </a>
				        
                    </div>`;
      			
			});
			
			$('#row').html(testo);
		}
	});
});
