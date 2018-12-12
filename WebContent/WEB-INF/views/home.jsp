<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="principal"/>
<section class="text-center">
	<p class="lead">Sistema para integração do resultado/laudo com o
		prontuário eletrônico Unimed</p>
	<p class="lead">Bem vindo, ${principal.username} !</p>
</section>

<section id="menu">
	<div class="row card-columns justify-content-center">
		<div class="col-11 col-sm-11 col-md-4 col-lg-4 col-xl-4">
			<a href="/uniresultado/prestador/listagem">
				<div class="card text-center card-pantone" id="prestador">
					<i class="fas fa-building fa-5x"></i>
					<div class="card-body">
						<h5 class="card-title">Cadastro de Prestador</h5>
					</div>
				</div>
			</a>		</div>
		<div class=" col-11 col-sm-11 col-md-4 col-lg-4 col-xl-4">
			<a href="/uniresultado/usuario/listagem">
				<div class="card text-center card-pantone428c" id="usuario">
					<i class="fas fa-user fa-5x"></i>
					<div class="card-body">
						<h5 class="card-title">Cadastro de Usuário</h5>
					</div>
				</div>
			</a>
		</div>
	</div>
	<div class="row card-columns justify-content-center">
		<div class=" col-11 col-sm-11 col-md-4 col-lg-4 col-xl-4">
			<a href="/uniresultado/resultado/formulario">
				<div class="card text-center card-pantone348c" id="cadastroResultado">
					<i class="fas fa-file-alt fa-5x"></i>
					<div class="card-body">
						<h5 class="card-title">Cadastro de Resultado/Laudo</h5>
					</div>
				</div>
			</a>
		</div>
		<div class=" col-11 csol-sm-11 col-md-4 col-lg-4 col-xl-4">
			<a href="/uniresultado/resultado/conferencia">
				<div class="card text-center card-pantone381c" id="envioResultado">
					<i class="fas fa-clipboard-check fa-5x"></i>
					<div class="card-body">
						<h5 class="card-title">Conferência do Resultado/Laudo</h5>
					</div>
				</div>
			</a>
		</div>
	</div>
	<div class="row card-columns justify-content-center">
		<div class=" col-11 csol-sm-11 col-md-4 col-lg-4 col-xl-4">
			<a href="/uniresultado/resultado/envio">
				<div class="card text-center card-pantone357c" id="envioResultado">
					<i class="fas fa-upload fa-5x"></i>
					<div class="card-body">
						<h5 class="card-title">Enviar para o prontuário</h5>
					</div>
				</div>
			</a>
		</div>
		<div class=" col-11 csol-sm-11 col-md-4 col-lg-4 col-xl-4">
			<a href="/uniresultado/logEnvio/listagem">
				<div class="card text-center card-pantone2627c" id="logEnvio">
					<i class="fas fa-clipboard-list fa-5x"></i>
					<div class="card-body">
						<h5 class="card-title">Log de Envio</h5>
					</div>
				</div>
			</a>
		</div>
	</div>
	<div class="row card-columns justify-content-center">
		<div class=" col-11 csol-sm-11 col-md-4 col-lg-4 col-xl-4">
			<a href="/uniresultado/logout">
				<div class="card text-center card-pantone192c" id="conferenciaResultado">
					<i class="fas fa-sign-out-alt fa-5x"></i>
					<div class="card-body">
						<h5 class="card-title">Sair do Sistema</h5>
					</div>
				</div>
			</a>
		</div>
	</div>
</section>

</sec:authorize>