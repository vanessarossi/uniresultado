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
		<div class="form-group col-md-4">
			<label>Status do Log</label>
			<select class="form-control form-control-sm" id="statusLog" name="statusLog">
				<option value="T">Todos</option>
				<option value="E">Enviado com sucesso</option>
				<option value="ER">Enviado com erro</option>
			</select>
		</div>
		<div class="form-group col-md-3">
			<label>Data de Envio</label>
			<div class="input-group">
				<input type="date" class="form-control" aria-describedby="btn_data" id="data" name="data">
				<div class="input-group-append">
					<button  class="btn btn-info" id="btn_data" onclick="pesquisar()">Pesquisar</button>
				 </div>
			</div>
		</div>
	</div>
</section>
<br><br>
<section class="justify-content-center text-center">
	<table class="table table-sm table-striped table-borderless table-hover" id="tabelaLogsEnvio">
		<thead>
			<tr>
				<th>Status</th>
				<th>Resultado</th>
				<th>Usuário</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<nav>
		<ul class="pagination justify-content-end" id="paginacao"></ul>
	</nav>
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