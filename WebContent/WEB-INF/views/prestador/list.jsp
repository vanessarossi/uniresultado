<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section class="text-center" id="titulo">
	<h1 class="h1">Listagem de Prestador</h1>
</section>
<br>
<section class="justify-content-center text-center">
	<table class="table table-sm table-striped table-borderless table-hover" id="tabelaPrestadores">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Prestador Origem</th>
				<th>Sistema Prestador</th>
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

<a href="/uniresultado/prestador/formulario" class="btn btn-primary"> Novo Prestador</a>
<a href="/uniresultado/home" class="btn  btn-secondary">PáginaInicial</a>

<spring:url value="/resources/js/lista_prestador.js" var="listaPrestadorJS"></spring:url>
<script type="text/javascript" src="${listaPrestadorJS}"></script>