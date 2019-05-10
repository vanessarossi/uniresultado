<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<section class="text-center" id="titulo">
	<h1 class="h1">Logs de Envio</h1>
</section>
<br>
<section>
	<div class="form-row">
		<div class="input-group col-md-3">
			<input type="date" class="form-control" aria-describedby="btn_data" name="data">
			<div class="input-group-append">
				<button type="submit" class="btn btn-info" id="btn_data">Pesquisar</button>
			 </div>
		</div>
	</div>
</section>
<br><br>
<section class="justify-content-center text-center">
	<table class="table table-sm table-striped table-borderless table-hover" id="logsEnvio">
		<thead>
			<tr>
				<th>Status</th>
				<th>Resultado</th>
				<th>Usuário</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${logsEnvio}" var="logEnvio" varStatus="i">
				<tr>
					<td>${logEnvio.status}</td>
					<td>${logEnvio.resultado.nrExecucaoOperadora} - ${logEnvio.resultado.nrCartaoBeneficiario}</td>
					<td>${logEnvio.usuario.nome}</td>
					<td>
						<c:if test="${logEnvio.status eq 'ER'}">
							<button type="button" class="btn btn-sm btn-warning" onclick="abrirModalResposta('${logEnvio.getRespostaReplace()}')"> <i class="fas fa-exclamation-triangle"></i></button>
						</c:if> 
						<c:if test="${logEnvio.status eq 'E'}">
							<button type="button" class="btn btn-sm btn-success" onclick="abrirModalResposta('${logEnvio.getRespostaReplace()}')"> <i class="fas fa-check-square"></i></button>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>
<a href="/uniresultado/home" class="btn  btn-outline-secondary">Página Inicial</a>
<br><br><br>
<div class="modal" tabindex="-1" role="dialog" id="modalRespostaLog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Log de Resposta</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p id="resposta"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<spring:url value="/resources/js/lista_log_envio.js" var="listaLogEnvioJS"></spring:url>
<script type="text/javascript" src="${listaLogEnvioJS}"></script>