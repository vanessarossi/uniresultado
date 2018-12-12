<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<br>
<h3 class="text-center">Cadastro do Resultado / Laudo</h3>
<hr>
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
			<div class="form-group col-md-2">
				<label>Nº Cartão do Beneficiário</label>
				<form:input cssClass="form-control form-control-sm" path="nrCartaoBeneficiario" />
				<form:errors path="nrCartaoBeneficiario" />
			</div>
			<div class="form-group col-md-2">
				<label>Nº Execução da Operadora</label>
				<form:input cssClass="form-control form-control-sm" path="nrExecucaoOperadora" />
				<form:errors path="nrExecucaoOperadora" />
			</div>	
		</div>
		<div class="form-row">	
			<div class="form-group col-md-2">
				<label>Formato do Arquivo</label>
				<form:select path="formatoArquivo" cssClass="form-control form-control-sm">
					<form:option value="PDF">PDF</form:option>
					<form:option value="PNG">PNG</form:option>
				</form:select>
				<form:errors path="formatoArquivo" />
			</div>
			<div class="form-group col-md-4">
				<label>Arquivo</label>
				<input type="file" class="form-control form-control-sm" name="arquivo" />
				<form:errors path="anexo" />
			</div>
			<div class="form-group col-md-2">
				<label>Status</label>
				<form:select path="status" cssClass="form-control form-control-sm">
				<form:option value="">SELECIONE</form:option>
					<form:option value="P">PENDENTE</form:option>
					<form:option value="C">CANCELADO</form:option>
					<form:option value="E">ENVIADO</form:option>
					<form:option value="ER">ENVIADO COM ERRO</form:option>
				</form:select>
				<form:errors path="status" />
			</div>
		</div>
		<form:hidden path="id" />
	</section>
	<section>
		<div class="form-row">
			<div class="form-group col-md-2">
				<label>Código Exame</label>
				<input type="text" class="form-control form-control-sm" id="codigoExame"/>
			</div>
			<div class="form-group col-md-1">
				<label>Quantidade</label>
				<input type="text" class="form-control form-control-sm" id="quantidade"/>
			</div>
			<div class="form-group col-md-2">
				<br>
				<a href="#" class="btn  btn-success"  onclick="addExame()">+</a>
			</div>
		</div>
		<table class="table table-sm table-hover" id="tabelaExames">
			<thead>
				<tr>
					<th>Código Exame</th>
					<th>Quantidade</th>
					<th>Código Tabela</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${reultado.exames}" var="exame">
					<tr>
						<td>${exame.codigoExame}</td>
						<td>${exame.qtde}</td>
						<td>${exame.codigoTabela}</td>
						<td><a href="#" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input type="hidden" id="contador" value="${fn:length(resultado.exames)}" />
	</section>
	<button type="submit" class="btn  btn-outline-success">Salvar</button>
	<a href="/uniresultado/home" class="btn  btn-outline-secondary">Página Inicial</a>
	<br>
</form:form>
<spring:url value="/resources/js/form_resultado.js" var="formResultadoJS"></spring:url>
<script type="text/javascript" src="${formResultadoJS}"></script>