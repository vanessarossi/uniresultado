<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<section class="text-center">
	<h1 class="display-3">UniResultado</h1>
	<p class="lead">Sistema para integração do resultado/laudo com o prontuário eletrônico Unimed</p>
	<p class="lead">Bem vindo, nomePessoa !</p>
</section>
<section>
	<a href="/uniresultado/prestador/listagem">Cadastro de Prestador</a>
	<a href="/uniresultado/usuario/listagem">Cadastro de Usuário</a>
	<a href="/uniresultado/resultado/formulario">Cadastro de Resultado/Laudo</a>
	<a href="/uniresultado/resultado/conferencia">Conferência do Resultado/Laudo</a>
	<a href="/uniresultado/resultado/envio">Envio de Resultado/Laudo para o prontuário</a>
	<a href="/uniresultado/logEnvio/listagem">Log de Envio</a>
</section>