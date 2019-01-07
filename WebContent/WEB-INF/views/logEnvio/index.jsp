<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section class="text-center" id="titulo">
	<h1 class="h1">Logs de Envio</h1>
</section>
<br>
<section class="justify-content-center text-center">
	<table class="table table-sm table-striped table-borderless table-hover" id="logsEnvio">
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
</section>
<a href="/uniresultado/home" class="btn  btn-outline-secondary">Página Inicial</a>
<br>
