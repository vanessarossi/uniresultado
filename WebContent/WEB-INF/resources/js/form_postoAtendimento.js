$(document).ready(function() {
	
	$("#estados").change(function(){
		var estado_id = $(this).val();
		$.ajax({
			url: '/unisup/postoAtendimento/pesquisa/cidade/estado',
			type: 'GET',
			dataType: 'json',
			data: {id: estado_id},
			success: function(data){
				$('#cidades')
					.find('option')
				    .remove()
				    .end()
					.append($('<option>', { 
        				value: '',
        				text : 'SELECIONE' 
    				}));

				$.each(data, function(index, cidade) {
					$('#cidades').append($('<option>', { 
        				value: cidade.id,
        				text : cidade.nome 
    				}));
				});
			},
			error: function(){
				console.log("Erro ao carregar...");
			}
		})
		.done(function(){})
		.fail(function(){})
		.always(function(){});
	});
});