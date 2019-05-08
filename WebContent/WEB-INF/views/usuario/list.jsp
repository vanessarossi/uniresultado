<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section class="text-center" id="titulo">
	<h1 class="h1">Listagem de Usuário</h1>
</section>
<br>
<section class="justify-content-center text-center">
	<table class="table table-sm table-striped table-borderless table-hover" id="tabelaUsuarios">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Login</th>
				<th>Perfil</th>
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
<a href="/uniresultado/usuario/formulario" class="btn btn-primary"> Novo Usuário</a>
<a href="/uniresultado/home" class="btn  btn-secondary">Página Inicial</a>
<spring:url value="/resources/js/lista_usuario.js" var="listaUsuarioJS"></spring:url>
<script type="text/javascript" src="${listaUsuarioJS}"></script>