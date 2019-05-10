var totalPaginas;
var totalElementos;
var numero = 0;
var data;
var status;


$(document).ready(function(){
	pesquisarPagina(numero);
});

function pesquisar() {
	pesquisarPagina(numero);
}

function pesquisarPagina(numeroPagina) {
	status = $("#statusLog option:selected" ).val();
	$.ajax({
		url : '/uniresultado/logEnvio/pesquisa',
		type : 'get',
		data: {page : numeroPagina, data : retornaData(), status : status},
		beforeSend : function(){}
	})
	.done(function(response){
		var listaLogEnvio = response["content"];
		totalPaginas = response["totalPages"];
		totalElementos = response["totalElements"];
		numero = response["number"];
		montarTabela(listaLogEnvio);
		montarPaginacao(totalPaginas, numero);
	})
	.fail(function(jqXHR, textStatus, msg){});
}



function montarTabela(listaLogEnvio) {
	$('#tabelaLogsEnvio > tbody > tr').remove();
	for (var i = 0; i < listaLogEnvio.length; i++) {

		var classeBotao = '';
		var classeIcone = '';

		if (listaLogEnvio[i]["status"] === 'E') {
			classeBotao = 'btn-success';
			classeIcone = 'fas fa-check-square';
		}else{
			classeBotao = 'btn-warning';
			classeIcone = 'fas fa-exclamation-triangle';
		}

		var row = "<tr>";
		    row += "<td>"+ listaLogEnvio[i]["status"] +"</td>";
		    row += "<td>"+ listaLogEnvio[i]["resultado"]["nrExecucaoOperadora"]+" - "+listaLogEnvio[i]["resultado"]["nrCartaoBeneficiario"]+"</td>";
		    row += "<td>"+ listaLogEnvio[i]["usuario"]["nome"] +"</td>";
		    row += "<td>"+ '<button type="button" class="btn btn-sm '+classeBotao+'" onclick=abrirModalResposta("'+listaLogEnvio[i]["id"]+'")> <i class="fas fa-check-square'+classeIcone+'"></i></button>';
			row += "</tr>";
		$('#tabelaLogsEnvio').append(row);
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

function abrirModalResposta(logEnvioId) {
	var resposta = '';

	$.ajax({
		url : '/uniresultado/logEnvio/pesquisa/id',
		type : 'get',
		data: {id : logEnvioId},
		beforeSend : function(){}
	})
	.done(function(response){
		resposta = response["resposta"];
		$("#resposta").text(resposta);
		$("#modalRespostaLog").modal('show');
	})
	.fail(function(jqXHR, textStatus, msg){});

	
}

function retornaData() {
	var data = $('#data').val();
	if (data === '' || data === null) {

		var hoje = new Date();
		var dia = String(hoje.getDate()).padStart(2, '0');
		var mes = String(hoje.getMonth() + 1).padStart(2, '0'); //January is 0!
		var ano = hoje.getFullYear().toString().substr(-2);

		hoje = dia + '/' + mes + '/' + ano;
		return hoje
	}else{
		var dia = data.substring(8,10);
		var mes = data.substring(5,7);
		var ano = data.substring(2,4);

		var dataConvertida = dia + '/' + mes + '/' + ano;

		return dataConvertida;
	}
}