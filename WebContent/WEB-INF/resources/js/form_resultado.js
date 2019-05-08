$(document).ready(function(){
	$('#salvar').hide();
});

function validarAutorizacao(){
	var nrCartaoBeneficiario = $('#nrCartaoBeneficiario').val();
	var nrExecucaoOperadora = $('#nrExecucaoOperadora').val();
	if (nrCartaoBeneficiario != '' && nrExecucaoOperadora != ''){
		alert("validoooou");
		$('#salvar').show();
	}else{
		alert("A validação não foi efetuada com sucesso, preencha o número do cartão e o número de execução.");
	}
}
