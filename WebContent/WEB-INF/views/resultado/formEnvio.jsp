<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="text-center" id="titulo">
	<h1 class="h1">Envio de Resultado / Laudo para o prontuário</h1>
</section>
<br>
<section class="justify-content-center text-center">
	<table class="table table-sm table-striped table-borderless table-hover" id="tabelaEnvio">
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

<a href="/uniresultado/resultado/enviar/todos" class="btn  btn-info">Enviar todos</a>
<button type="submit" class="btn  btn-info">Enviar selecionados</button>
<a href="/uniresultado/home" class="btn  btn-secondary">Página Inicial</a>
<br>
<spring:url value="/resources/js/lista_envio_resultado.js" var="listaEnvioResultadoJS"></spring:url>
<script type="text/javascript" src="${listaEnvioResultadoJS}"></script>