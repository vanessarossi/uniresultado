<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section class="text-center" id="titulo">
	<h1 class="h1">Confer�ncia de Resultados Pendentes</h1>
</section>
<br>
<section class="justify-content-center text-center">
	<table class="table table-sm table-striped table-borderless table-hover" id="tabelaConferenciaResultado">
		<thead>
			<tr>
				<th>Nr� Cart�o Benefici�rio</th>
				<th>Nr� Execu��o</th>
				<th>Tipo</th>
				<th>Data</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
</section>
<a href="/uniresultado/resultado/formulario" class="btn btn-primary"> Novo Resultado</a>
<a href="/uniresultado/home" class="btn  btn-secondary">P�gina Inicial</a>
<spring:url value="/resources/js/lista_conferencia_resultado.js" var="listaConferenciaResultadoJS"></spring:url>
<script type="text/javascript" src="${listaConferenciaResultadoJS}"></script>