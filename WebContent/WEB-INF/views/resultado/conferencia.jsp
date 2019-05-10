<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section class="text-center" id="titulo">
	<h1 class="h1">Conferência de Resultados Pendentes</h1>
</section>
<br>
<div class="form-row">
	<div class="form-group col-md-4">
		<label>Tipo de Conferência</label>
		<select class="form-control form-control-sm" id="tipoConferencia">
			<option value="I">I - Resultados Importados</option>
			<option value="EV">EV - Resultados com Erro de Validação</option>
		</select>
	</div>
</div>
<section class="justify-content-center text-center">
	<table
		class="table table-sm table-striped table-borderless table-hover"
		id="tabelaConferenciaResultado">
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
		<tbody></tbody>
	</table>
	<nav>
		<ul class="pagination justify-content-end" id="paginacao"></ul>
	</nav>
</section>
<a href="/uniresultado/resultado/validar" class="btn btn-info">Validar Importados</a>
<a href="/uniresultado/resultado/formulario" class="btn btn-primary">Novo Resultado</a>
<a href="/uniresultado/home" class="btn btn-secondary">Página Inicial</a>
<spring:url value="/resources/js/lista_conferencia_resultado.js"
	var="listaConferenciaResultadoJS"></spring:url>
<script type="text/javascript" src="${listaConferenciaResultadoJS}"></script>