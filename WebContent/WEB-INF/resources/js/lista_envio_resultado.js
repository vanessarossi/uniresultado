$(document).ready(function(){
	$.ajax({
		url : '/uniresultado/resultado/pesquisa/envio',
		type : 'get',
		beforeSend : function(){

		}
	})
	.done(function(response){
		
		var listaResultados = response["content"];
		var totalElementos = response["totalElements"];
		var totalPaginas = response["totalPages"];
		var tamanho = response["size"];
		var numero = response["number"];

		for (var i = listaResultados.length - 1; i >= 0; i--) {
			var row = "<tr>";
		        row += "<td>"+ listaResultados[i]["nrCartaoBeneficiario"] +"</td>";
		        row += "<td>"+ listaResultados[i]["nrExecucaoOperadora"] +"</td>";
		        row += "<td>"+ listaResultados[i]["tipoOperacao"] +"</td>";
		        row += "<td>"+ listaResultados[i]["status"] +"</td>";
		        row += "<td>"+ listaResultados[i]["data"] +"</td>";
		        row += "<td>"+ '<a href="/uniresultado/resultado/alterar/'+listaResultados[i]["id"]+'" class="btn btn-sm btn-info"><i class="fas fa-edit"></i></a>' +"</td>";
		        row += "<td>"+ '<a href="/uniresultado/resultado/excluir/'+listaResultados[i]["id"]+'" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></a>' +"</td>";
				row += "</tr>";

				$("#tabelaConferenciaResultado").append(row);
		}

	})
	.fail(function(jqXHR, textStatus, msg){
		
	});

});