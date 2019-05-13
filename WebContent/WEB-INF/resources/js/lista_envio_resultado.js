var totalPaginas;
var totalElementos;
var numero = 0;

$(document).ready(function(){
	pesquisarPagina(numero);
});

function pesquisarPagina(numeroPagina) {
	$.ajax({
		url : '/uniresultado/resultado/pesquisa/envio',
		type : 'get',
		data: {page : numeroPagina},
		beforeSend : function(){}
	})
	.done(function(response){
		var listaResultados = response["content"];
		totalPaginas = response["totalPages"];
		totalElementos = response["totalElements"];
		numero = response["number"];
		

		montarTabela(listaResultados);
		montarPaginacao(totalPaginas, numero);
	})
	.fail(function(jqXHR, textStatus, msg){
	});
}

function montarTabela(listaResultados) {
	$('#tabelaEnvio > tbody > tr').remove();
	for (var i = 0; i < listaResultados.length; i++) {
		var row = "<tr>";
		    row += "<td>"+ listaResultados[i]["nrCartaoBeneficiario"] +"</td>";
		    row += "<td>"+ listaResultados[i]["nrExecucaoOperadora"] +"</td>";
		    row += "<td>"+ listaResultados[i]["tipoOperacao"] +"</td>";
		    row += "<td>"+ listaResultados[i]["data"] +"</td>";
			row += "</tr>";
		$('#tabelaEnvio').append(row);
	}
}

function montarPaginacao(totalPaginas, numero) {
	$('#paginacao > li ').remove();
	var liInicial = "<li class='page-item'><a class='page-link' href='#' onclick='pesquisarPagina("+0+")'>Primeira</a></li>";
	$("#paginacao").append(liInicial);

	if (totalPaginas > 10) {
		if (numero <= 5) {
			for (var i = 0; i <= numero ; i ++) {
				var active = numero === i ? 'active' : '';
				var li = "<li class='page-item "+active+"'><a class='page-link' href='#' onclick='pesquisarPagina("+i+")'>"+(parseInt(i)+1)+"</a></li>";
				$('#paginacao').append(li);
			}
			for (var i = (numero+1); i < 10 ; i++) {
				var active = numero === i ? 'active' : '';
				var li = "<li class='page-item "+active+"'><a class='page-link' href='#' onclick='pesquisarPagina("+i+")'>"+(parseInt(i)+1)+"</a></li>";
				$('#paginacao').append(li);
			}
		}
		if (numero > 5) {
			for (var i = (numero - 5); i <= numero ; i ++) {
				var active = numero === i ? 'active' : '';
				var li = "<li class='page-item "+active+"'><a class='page-link' href='#' onclick='pesquisarPagina("+i+")'>"+(parseInt(i)+1)+"</a></li>";
				$('#paginacao').append(li);
			}
			var numeroMaximo = ((numero + 5)  > totalPaginas) ? totalPaginas : (numero + 5);
			for (var i = (numero+1); i < numeroMaximo ; i++) {
				var active = numero === i ? 'active' : '';
				var li = "<li class='page-item "+active+"'><a class='page-link' href='#' onclick='pesquisarPagina("+i+")'>"+(parseInt(i)+1)+"</a></li>";
				$('#paginacao').append(li);
			}
		}
	}else{
		for (var i = 0; i < totalPaginas; i++) {
			var active = numero === i ? 'active' : '';
			var li = "<li class='page-item "+active+"'><a class='page-link' href='#' onclick='pesquisarPagina("+i+")'>"+(parseInt(i)+1)+"</a></li>";
			$('#paginacao').append(li);
		}
	}
	var liFinal = "<li class='page-item'><a class='page-link' href='#' onclick='pesquisarPagina("+(parseInt(totalPaginas) -1)+")'>Último</a></li>";
	$("#paginacao").append(liFinal);
}