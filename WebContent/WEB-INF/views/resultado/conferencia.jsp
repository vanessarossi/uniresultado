<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h2 class="text-center">Conferência de Resultados Pendentes</h2>
<hr>
<table class="table table-sm table-hover">
	<thead>
		<tr>
			<th>Nrº Cartão Beneficiário</th>
			<th>Nrº Execução</th>
			<th>Tipo</th>
			<th>Data</th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${resultados}" var="resultado">
			<tr>
				<td>${resultado.nrCartaoBeneficiario}</td>
				<td>${resultado.nrExecucaoOperadora }</td>
				<td>${resultado.tipoOperacao}</td>
				<td>${resultado.data}</td>
				<td>arquivo</td>
				<td><a href="/uniresultado/resultado/cancelar/${resultado.id}" class="btn btn-sm btn-danger"><i class="fa fa-times"></i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<a href="/uniresultado/resultado/formulario" class="btn btn-outline-primary"> Novo Resultado</a>
<a href="/uniresultado/home" class="btn  btn-outline-secondary">Página Inicial</a>
