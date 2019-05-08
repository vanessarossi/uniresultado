<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section class="text-center" id="titulo">
	<h1 class="h1">Listagem do Prestador</h1>
</section>
<br>
<form:form action="/uniresultado/prestador/salvar" method="post" modelAttribute="prestador">
	<div class="form-row">
		<div class="form-group col-md-4">
			<label>Nome</label>
			<form:input cssClass="form-control form-control-sm" path="nome" />
			<form:errors path="nome" />
		</div>
		<div class="form-group col-md-2">
			<label>Prestador Origem</label>
			<form:input cssClass="form-control form-control-sm" path="prestadorOrigem" />
			<form:errors path="prestadorOrigem" />
		</div>
		<div class="form-group col-md-4">
			<label>Sistema do Prestador</label>
			<form:input cssClass="form-control form-control-sm" path="sistemaPrestador" />
			<form:errors path="sistemaPrestador" />
		</div>		
	</div>
	<div class="form-row">
		<div class="form-group col-md-5">
			<label>Endpoint</label>
			<form:input cssClass="form-control form-control-sm" path="endpoint" />
			<form:errors path="endpoint" />
		</div>
		<div class="form-group col-md-3">
			<label>Login Webservice</label>
			<form:input cssClass="form-control form-control-sm" path="loginWebservice" />
			<form:errors path="loginWebservice" />
		</div>
		<div class="form-group col-md-3">
			<label>Senha Webservice</label>
			<form:input cssClass="form-control form-control-sm" path="senhaWebservice" />
			<form:errors path="senhaWebservice" />
		</div>	
	</div>
	<form:hidden path="id" />
	<button type="submit" class="btn btn-success">Salvar</button>
	<a href="/uniresultado/home" class="btn btn-secondary">Página Inicial</a>
	<br>
</form:form>