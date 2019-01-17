<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section class="text-center" id="titulo">
	<h1 class="h1">Logs de Envio</h1>
</section>
<br>
<form:form action="/uniresultado/logEnvio/pesquisar" method="post" modelAttribute="">
	<section>
		<div class="form-row">
			<div class="input-group col-md-3">
			  <input type="date" class="form-control" aria-describedby="btn_data" name="data">
			  <div class="input-group-append">
			    <button type="submit" class="btn btn-info" id="btn_data">Pesquisar</button>
			  </div>
			</div>
		</div>
	</section>
</form:form>
<br><br>
<section class="justify-content-center text-center">
	<table class="table table-sm table-striped table-borderless table-hover" id="logsEnvio">
		<thead>
			<tr>
				<th>Status</th>
				<th>Resultado</th>
				<th>Usuário</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${logsEnvio}" var="logEnvio" varStatus="i">
				<tr>
					<td>${logEnvio.status}</td>
					<td>${logEnvio.resultado.nrExecucaoOperadora} - ${logEnvio.resultado.nrCartaoBeneficiario}</td>
					<td>${logEnvio.usuario.nome}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>
<a href="/uniresultado/home" class="btn  btn-outline-secondary">Página Inicial</a>
<br><br><br>
<script>
$('#logsEnvio').DataTable( {
    "ordering": false,
    "info":     false
} );
</script>
