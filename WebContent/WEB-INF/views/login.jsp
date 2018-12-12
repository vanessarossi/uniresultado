<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<form action="/uniresultado/login" method="post" class="align-middle" >
	<div class="form-row">
		<div class="form-group col-md-3">
			<label>Login</label>
			<input type="text" name="login" class="form-control" placeholder="Login" autofocus="autofocus" data-rule-required="true" id="login"/>
		</div>
	</div>
	<div class="form-row">
		<div class="form-group col-md-3">
			<label>Senha</label>
			<input type="password" id="senha" name="senha" class="form-control" placeholder="Senha" data-rule-required="true"/>	
		</div>
	</div>
	<button type="submit" class="btn btn-lg btn-secondary">Entrar</button>
</form>


