<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h2 class="text-center">Listagem de Usuário</h2>
<hr>
<table class="table table-sm table-hover">
	<thead>
		<tr>
			<th>Nome</th>
			<th>Login</th>
			<th>Perfil</th>
			<th>Prestador</th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${usuarios}" var="usuario">
			<tr>
				<td>${usuario.nome}</td>
				<td>${usuario.login}</td>
				<td>${usuario.perfil}</td>
				<td>${usuario.prestador.nome}</td>
				<td><a href="/uniresultado/usuario/alterar/${usuario.id}" class="btn btn-sm btn-info"><i class="fas fa-edit"></i></a></td>
				<td><a href="/uniresultado/usuario/excluir/${usuario.id}" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<a href="/uniresultado/usuario/formulario" class="btn btn-outline-primary"> Novo Usuário</a>
<a href="/uniresultado/home" class="btn  btn-outline-secondary">Página Inicial</a>