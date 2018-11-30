<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<br>
<h3 class="text-center">Envio de Resultado / Laudo para o prontuário</h3>
<hr>

<table class="table table-sm table-hover">
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
				<td>${resultado.data}</td>
				<td>arquivo</td>
				<td>
					<input type="checkbox" name="resultados[${ i.index}].id"  id="resultado${ i.index}" value="${resultado.id}"/>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a href="/uniresultado/resultado/enviar/todos" class="btn  btn-outline-info">Enviar todos</a>
<button type="submit" class="btn  btn-outline-info">Enviar selecionados</button>
<a href="/uniresultado/home" class="btn  btn-outline-secondary">Página Inicial</a>
<br>