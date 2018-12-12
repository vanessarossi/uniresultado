<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<br>
<h3 class="text-center">Logs de Envio</h3>
<hr>

<table class="table table-sm table-hover">
	<thead>
		<tr>
			<th>Data</th>
			<th>Status</th>
			<th>Resultado</th>
			<th>Usuário</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${logsEnvio}" var="logEnvio" varStatus="i">
			<tr>
				<td>${logEnvio.data}</td>
				<td>${logEnvio.status}</td>
				<td>${logEnvio.resultado.nrExecucaoOperadora} - ${logEnvio.resultado.nrCartaoBeneficiario}</td>
				<td>${logEnvio.usuario.nome}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a href="/uniresultado/home" class="btn  btn-outline-secondary">Página Inicial</a>
<br>