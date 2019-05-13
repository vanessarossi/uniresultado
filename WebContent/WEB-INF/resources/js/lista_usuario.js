var totalPaginas;
var totalElementos;
var numero = 0;

$(document).ready(function(){
	pesquisarPagina(numero);
});

function pesquisarPagina(numeroPagina) {
	$.ajax({
		url : '/uniresultado/usuario/pesquisa',
		type : 'get',
		data: {page : numeroPagina},
		beforeSend : function(){}
	})
	.done(function(response){
		var listaUsuarios = response["content"];
		totalPaginas = response["totalPages"];
		totalElementos = response["totalElements"];
		numero = response["number"];
		montarTabela(listaUsuarios);
		montarPaginacao(totalPaginas, numero);
	})
	.fail(function(jqXHR, textStatus, msg){});
}

function montarTabela(listaUsuarios) {
	$('#tabelaUsuarios > tbody > tr').remove();
	for (var i = 0; i < listaUsuarios.length; i++) {
		var row = "<tr>";
		    row += "<td>"+ listaUsuarios[i]["nome"] +"</td>";
		    row += "<td>"+ listaUsuarios[i]["login"] +"</td>";
		    row += "<td>"+ listaUsuarios[i]["perfil"] +"</td>";
		    row += "<td>"+ '<a href="/uniresultado/usuario/alterar/'+listaUsuarios[i]["id"]+'" class="btn btn-sm btn-info"><i class="fas fa-edit"></i></a>' +"</td>";
		    row += "<td>"+ '<a href="/uniresultado/usuario/excluir/'+listaUsuarios[i]["id"]+'" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></a>' +"</td>";
			row += "</tr>";
		$('#tabelaUsuarios').append(row);
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