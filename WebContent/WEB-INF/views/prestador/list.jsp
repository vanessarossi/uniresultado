<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<section class="text-center" id="titulo">
	<h1 class="h1">Listagem de Prestador</h1>
</section>
<br>
<section class="justify-content-center text-center">
	<table class="table table-sm table-striped table-borderless table-hover">
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
</section>

<a href="/uniresultado/prestador/formulario"
	class="btn btn-outline-primary"> Novo Prestador</a>
<a href="/uniresultado/home" class="btn  btn-outline-secondary">Página
	Inicial</a>
