<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="text-center" id="titulo">
	<h1 class="h1">Lista de Resultados e Laudos</h1>
</section>
<br>
<section class="justify-content-center text-center">
	<table class="table table-sm table-striped table-borderless table-hover" id="tb_resultado">
		<thead>
			<tr>
				<th>Nrº Cartão Beneficiário</th>
				<th>Nrº Execução</th>
				<th>Tipo</th>
				<th>Data</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${resultados}" var="resultado">
				<tr>
					<td>${resultado.nrCartaoBeneficiario}</td>
					<td>${resultado.nrExecucaoOperadora }</td>
					<td>${resultado.tipoOperacao}</td>
					<td><fmt:formatDate value="${resultado.data}" pattern="dd-MM-yyyy" /></td>
					<td>${resultado.status}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>

<a href="/uniresultado/resultado/formulario" class="btn btn-outline-primary"> Novo Resultado</a>
<a href="/uniresultado/home" class="btn  btn-outline-secondary">Página Inicial</a>

<br><br><br>
<script>
$('#tb_resultado').DataTable( {
    "ordering": false,
    "info":     false
} );
</script>
