<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h2 class="text-center">Listagem de Prestador</h2>
<hr>
<table class="table table-sm table-hover">
	<thead>
		<tr>
			<th>Nome</th>
			<th>Prestador Origem</th>
			<th>Sistema Prestador</th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${prestadores}" var="prestador">
			<tr>
				<td>${prestador.nome}</td>
				<td>${prestador.prestadorOrigem }</td>
				<td>${prestador.sistemaPrestador}</td>
				<td><a href="/uniresultado/prestador/alterar/${prestador.id}" class="btn btn-sm btn-info"><i class="fas fa-edit"></i></a></td>
				<td><a href="/uniresultado/prestador/excluir/${prestador.id}" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<a href="/uniresultado/prestador/formulario" class="btn btn-outline-primary"> Novo Prestador</a>
<a href="/uniresultado/home" class="btn  btn-outline-secondary">Página Inicial</a>
