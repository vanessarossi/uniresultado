<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section class="text-center" id="titulo">
	<h1 class="h1">Cadastro do Resultado / Laudo</h1>
</section>
<br>
<form:form action="/uniresultado/resultado/salvar" method="post" modelAttribute="resultado" enctype="multipart/form-data">
	<section>
		<div class="form-row">
			<div class="form-group col-md-2">
				<label>Tipo Operação</label>
				<form:select path="tipoOperacao" cssClass="form-control form-control-sm">
					<form:option value="">SELECIONE</form:option>
					<form:option value="L">LAUDO</form:option>
					<form:option value="R">RESULTADO</form:option>
				</form:select>
				<form:errors path="tipoOperacao" />
			</div>
			<div class="form-group col-md-3">
				<label>Nº Cartão do Beneficiário</label>
				<form:input cssClass="form-control form-control-sm" path="nrCartaoBeneficiario"/>
				<form:errors path="nrCartaoBeneficiario" />
			</div>
			<div class="form-group col-md-3">
				<label>Nº Execução da Operadora</label>
				<form:input cssClass="form-control form-control-sm" path="nrExecucaoOperadora"/>
				<form:errors path="nrExecucaoOperadora" />
			</div>	
		</div>
		<div class="form-row">
			<div class="form-group col-md-4">
				<label>Arquivo</label>
				<input type="file" class="form-control form-control-sm" name="arquivo"/>
				<form:errors path="anexo" />
			</div>
		</div>
		<form:hidden path="formatoArquivo" value="pdf" />
		<form:hidden path="status" value="I" />
		<form:hidden path="id" />
	</section>
	
	<section>
		<c:if test="${resultado.id != null}">
			<table class="table table-sm table-striped table-borderless table-hover" id="tabelaResultados">
				<thead>
					<tr>
						<th>Código de Tabela</th>
						<th>Código de Exame</th>
						<th>Quantidade</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${resultado.exames}" var="exame">
						<tr>
							<td>${exame.codigoTabela}</td>
							<td>${exame.codigoExame}</td>
							<td>${exame.qtde}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</section>
	<a href="/uniresultado/resultado/listagem" class="btn btn-primary">Ver resultados cadastrados</a>
	<a href="/uniresultado/home" class="btn btn-secondary">Página Inicial</a>
	<button type="submit" id="salvar" class="btn btn-success">Salvar</button>
	<br>
</form:form>
<spring:url value="/resources/js/form_resultado.js" var="formResultadoJS"></spring:url>
<script type="text/javascript" src="${formResultadoJS}"></script>