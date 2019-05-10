<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section class="text-center" id="titulo">
	<h1 class="h1">Lista de Resultados e Laudos</h1>
</section>
<br>
<section>
	<div class="form-row">
		<div class="form-group col-md-4">
			<label>Status do Log</label>
			<select class="form-control form-control-sm" id="statusResultado" name="statusResultado">
				<option value="T">Todos</option>
				<option value="E">Enviado com sucesso</option>
				<option value="ER">Enviado com erro</option>
				<option value="EV">Erro de validação</option>
				<option value="P">Pendente para envio</option>
				<option value="C">Cancelado</option>
			</select>
		</div>
	</div>
</section>
<section class="justify-content-center text-center">
	<table class="table table-sm table-striped table-borderless table-hover" id="tabelaResultados">
		<thead>
			<tr>
				<th>Nrº Cartão Beneficiário</th>
				<th>Nrº Execução</th>
				<th>Tipo</th>
				<th>Data</th>
				<th>Status</th>
				<th></th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<nav>
		<ul class="pagination justify-content-end" id="paginacao"></ul>
	</nav>
</section>
<a href="/uniresultado/resultado/formulario" class="btn btn-outline-primary"> Novo Resultado</a>
<a href="/uniresultado/home" class="btn  btn-outline-secondary">Página Inicial</a>
<br><br>
<spring:url value="/resources/js/lista_resultado.js" var="listaResultadoJS"></spring:url>
<script type="text/javascript" src="${listaResultadoJS}"></script>
