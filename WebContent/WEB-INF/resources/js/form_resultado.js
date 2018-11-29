$(document).ready(function() {

});


function addExame(){
	var codigoExame = $('#codigoExame').val();
	var codigoTabela = "22";
	var qtde = $('#quantidade').val();
	var contador = $('#contador').val();

	if (validaAddExame(codigoExame,qtde)) {
		var row = "<tr id='"+contador+"'>";
        row += "<input type='hidden' name='exames["+contador+"].codigoExame' id='exame"+contador+"' value="+codigoExame+">";
				row += "<input type='hidden' name='exames["+contador+"].codigoTabela' id='codigoTabela"+contador+"' value="+codigoTabela+">";
        row += "<input type='hidden' name='exames["+contador+"].qtde' id='qtde"+contador+"' value="+qtde+">";
        row += "<td>" + codigoExame + "</td>";
        row += "<td>" + qtde + "</td>";
        row += "<td>" + codigoTabela + "</td>";
        row += "<td>";
        row += '<a class="btn btn-default btn-xs" onclick="removerExame('+contador+',0)"><span class="fa fa-trash-alt"></span></a>';
        row += "</td>";
        row += "</tr>";
	}
	$('#tabelaExames').append(row);
	$('#contador').val(parseInt(contador) + 1);
	limparFormExame();
}

function limparFormExame(){
 	$('#codigoExame').val("");
 	$('#quantidade').val("");
}

function validaAddExame(codigoExame,qtde) {
	if (codigoExame.trim() != null && codigoExame.trim() != '' && qtde.trim() != null && qtde.trim() != '') {
		return true;
	}else{
		return false;
	}
}

function removerExame(contador,id){
	if (parseInt(id) === 0) {
	        $('#'+contador).remove();
	    }else{

	    }
}
