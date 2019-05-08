<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section class="text-center" id="titulo">
	<h1 class="h1">Cadastro de Usuário</h1>
</section>
<form:form action="/uniresultado/usuario/salvar" method="post" modelAttribute="usuario">
	<div class="form-row">
		<div class="form-group col-md-2">
			<label>Login</label>
			<form:input cssClass="form-control form-control-sm" path="login" />
			<form:errors path="login" />
		</div>
		<div class="form-group col-md-4">
			<label>Nome</label>
			<form:input cssClass="form-control form-control-sm" path="nome" />
			<form:errors path="nome" />
		</div>
		<div class="form-group col-md-2">
			<label>Senha</label>
			<form:password cssClass="form-control form-control-sm" path="senha" />
			<form:errors path="senha" />
		</div>
	</div>
	<div class="form-row">
		<div class="form-group col-md-4">
			<label>E-mail</label>
			<form:input cssClass="form-control form-control-sm" path="email" />
			<form:errors path="email" />
		</div>
		<div class="form-group col-md-1">
			<label>Ativo</label>
			<form:select path="ativo" cssClass="form-control form-control-sm">
				<form:option value="1">Sim</form:option>
				<form:option value="0">Não</form:option>
			</form:select>
			<form:errors path="ativo" />
		</div>		
	</div>
	<div class="form-row">
		<div class="form-group col-md-4">
			<label>Prestador</label>
			<form:select path="prestador.id" cssClass="form-control form-control-sm">
				<form:option value="">SELECIONE</form:option>
				<form:options items="${prestadores}" itemLabel="nome" itemValue="id"/>
			</form:select>
			<form:errors path="prestador.id" />
		</div>
		<div class="form-group col-md-4">
			<label>Perfil</label>
			<form:select path="perfil" cssClass="form-control form-control-sm">
				<form:option value="">SELECIONE</form:option>
				<form:option value="ROLE_ADMIN">ADMINISTRADOR</form:option>
				<form:option value="ROLE_USER">PRESTADOR</form:option>
			</form:select>
			<form:errors path="perfil" />
		</div>
	</div>
	<form:hidden path="id" />
	<button type="submit" class="btn  btn-success">Salvar</button>
	<a href="/uniresultado/home" class="btn  btn-secondary">Página Inicial</a>
	<br>
</form:form>
