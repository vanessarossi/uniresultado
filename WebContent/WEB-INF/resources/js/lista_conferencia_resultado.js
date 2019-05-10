var totalPaginas;
var totalElementos;
var numero = 0;
var url;
var opcao;

$(document).ready(function(){
	opcao = $("#tipoConferencia option:selected" ).val();
	url = retornaUrl(opcao);
	pesquisarPagina(url, numero);
});


$( "#tipoConferencia" ).change(function() {
  	opcao = $("#tipoConferencia option:selected" ).val();
	url = retornaUrl(opcao);
	pesquisarPagina(url, numero);
});


function pesquisarPagina(url, numeroPagina) {
	if(url === null){
		url = retornaUrl(opcao);
	}
	$.ajax({
		url : url,
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
	$('#tabelaConferenciaResultado > tbody > tr').remove();
	for (var i = 0; i < listaResultados.length; i++) {
		var row = "<tr>";
		    row += "<td>"+ listaResultados[i]["nrCartaoBeneficiario"] +"</td>";
		    row += "<td>"+ listaResultados[i]["nrExecucaoOperadora"] +"</td>";
		    row += "<td>"+ listaResultados[i]["tipoOperacao"] +"</td>";
		    row += "<td>"+ listaResultados[i]["data"] +"</td>";
		    row += "<td>"+ '<a href="/uniresultado/resultado/cancelar/'+listaResultados[i]["id"]+'" class="btn btn-sm btn-danger"><i class="fas fa-window-close"></i></a>' +"</td>";
			row += "</tr>";
		$('#tabelaConferenciaResultado').append(row);
	}
}

function montarPaginacao(totalPaginas, numero) {
	$('#paginacao > li ').remove();
	var liInicial = "<li class='page-item'><a class='page-link' href='#' onclick='pesquisarPagina(null,"+0+")'>Primeira</a></li>";
	$("#paginacao").append(liInicial);
	for (var i = 0; i < totalPaginas; i++) {
		var active = numero === i ? 'active' : '';
		var li = "<li class='page-item "+active+"'><a class='page-link' href='#' onclick='pesquisarPagina(null,"+i+")'>"+(parseInt(i)+1)+"</a></li>";
		$('#paginacao').append(li);
	}
	var liFinal = "<li class='page-item'><a class='page-link' href='#' onclick='pesquisarPagina(null,"+(parseInt(totalPaginas) -1)+")'>Último</a></li>";
	$("#paginacao").append(liFinal);
}

function retornaUrl(opcao) {
	if (opcao === 'I') {
		return '/uniresultado/resultado/pesquisa/importado';
	}if(opcao === 'EV'){
		return '/uniresultado/resultado/pesquisa/errovalidacao';
	}
}