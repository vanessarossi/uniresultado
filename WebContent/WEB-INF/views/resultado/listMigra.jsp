<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section class="text-center" id="titulo">
	<h1 class="h1">Lista Migra de Laudos</h1>
</section>
<br>
<section class="justify-content-center text-center">
	<table class="table table-sm table-striped table-borderless table-hover" id="tabelaMigra">
		<thead>
			<tr>
				<th>Códio</th>
				<th>Accession Number</th>
				<th></th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<nav>
		<ul class="pagination justify-content-end" id="paginacao"></ul>
	</nav>
</section>
<a href="/uniresultado/resultado/validar/migra" class="btn btn-info">Validar Laudos</a>
<a href="/uniresultado/home" class="btn  btn-outline-secondary">Página Inicial</a>
<br><br>
<spring:url value="/resources/js/lista_migra.js" var="listaMigraJS"></spring:url>
<script type="text/javascript" src="${listaMigraJS}"></script>
