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
	<table class="table table-sm table-striped table-borderless table-hover">
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
		<tbody>
			<c:forEach items="${resultados}" var="resultado" varStatus="i">
				<tr>
					<td>${resultado.nrCartaoBeneficiario}</td>
					<td>${resultado.nrExecucaoOperadora }</td>
					<td>${resultado.tipoOperacao}</td>
					<td><fmt:formatDate value="${resultado.data}" pattern="dd-MM-yyyy" /></td>
					<td><a href="/uniresultado/resultado/arquivo/${resultado.id}" target="_blank" class="btn btn-sm btn-info"> <i class="fas fa-download"></i> </a></td>
					<td>
						<input type="checkbox" name="resultados[${ i.index}].id"  id="resultado${ i.index}" value="${resultado.id}"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>

<a href="/uniresultado/resultado/enviar/todos" class="btn  btn-outline-info">Enviar todos</a>
<button type="submit" class="btn  btn-outline-info">Enviar selecionados</button>
<a href="/uniresultado/home" class="btn  btn-outline-secondary">Página Inicial</a>
<br>