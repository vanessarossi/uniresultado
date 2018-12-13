<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<section class="text-center" id="titulo">
	<h1 class="h1">Conferência de Resultados Pendentes</h1>
</section>
<br>
<section class="justify-content-center text-center">
	<table class="table table-sm table-striped table-borderless table-hover">
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
					<td><td><a href="/uniresultado/resultado/arquivo/${resultado.id}" target="_blank" class="btn btn-sm btn-info"> <i class="fas fa-download"></i> </a></td></td>
					<td><a href="/uniresultado/resultado/cancelar/${resultado.id}" class="btn btn-sm btn-danger"><i class="fa fa-times"></i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>

<a href="/uniresultado/resultado/formulario" class="btn btn-outline-primary"> Novo Resultado</a>
<a href="/uniresultado/home" class="btn  btn-outline-secondary">Página Inicial</a>
