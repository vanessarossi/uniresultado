var totalPaginas;
var totalElementos;
var numero = 0;

$(document).ready(function(){
	pesquisarPagina(numero);
});

function pesquisarPagina(numeroPagina) {
	$.ajax({
		url : '/uniresultado/prestador/pesquisa',
		type : 'get',
		data: {page : numeroPagina},
		beforeSend : function(){}
	})
	.done(function(response){
		var listaPrestadores = response["content"];
		totalPaginas = response["totalPages"];
		totalElementos = response["totalElements"];
		numero = response["number"];
		montarTabela(listaPrestadores);
		montarPaginacao(totalPaginas, numero);
	})
	.fail(function(jqXHR, textStatus, msg){});
}

function montarTabela(listaPrestadores) {
	$('#tabelaPrestadores > tbody > tr').remove();
	for (var i = 0; i < listaPrestadores.length; i++) {
		var row = "<tr>";
		    row += "<td>"+ listaPrestadores[i]["nome"] +"</td>";
		    row += "<td>"+ listaPrestadores[i]["prestadorOrigem"] +"</td>";
		    row += "<td>"+ listaPrestadores[i]["sistemaPrestador"] +"</td>";
		    row += "<td>"+ '<a href="/uniresultado/prestador/alterar/'+listaPrestadores[i]["id"]+'" class="btn btn-sm btn-info"><i class="fas fa-edit"></i></a>' +"</td>";
		    row += "<td>"+ '<a href="/uniresultado/prestador/excluir/'+listaPrestadores[i]["id"]+'" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></a>' +"</td>";
			row += "</tr>";
		$('#tabelaPrestadores').append(row);
	}
}

function montarPaginacao(totalPaginas, numero) {
	$('#paginacao > li ').remove();
	var liInicial = "<li class='page-item'><a class='page-link' href='#' onclick='pesquisarPagina("+0+")'>Primeira</a></li>";
	$("#paginacao").append(liInicial);
	for (var i = 0; i < totalPaginas; i++) {
		var active = numero === i ? 'active' : '';
		var li = "<li class='page-item "+active+"'><a class='page-link' href='#' onclick='pesquisarPagina("+i+")'>"+(parseInt(i)+1)+"</a></li>";
		$('#paginacao').append(li);
	}
	var liFinal = "<li class='page-item'><a class='page-link' href='#' onclick='pesquisarPagina("+(parseInt(totalPaginas) -1)+")'>Último</a></li>";
	$("#paginacao").append(liFinal);
}
