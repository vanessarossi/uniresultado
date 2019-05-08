<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:url value="/resources/css/login.css" var="loginCSS"></spring:url>
<link href="${loginCSS}" rel="stylesheet">


<section id="login">
    <div class="container">
    	<div class="row">
    	    <div class="col-11 col-sm-11 col-md-11 col-lg-11 col-xl-11">
        	    <div class="form-wrap">
                <h1>Identificação do Usuário</h1>
                    <form role="form" action="/uniresultado/login" method="post" id="login-form" autocomplete="off">
                        <div class="form-group">
                            <label for="usuario" class="sr-only">Login</label>
                           	<input type="text" id="usuario" name="login" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label for="senha" class="sr-only">Password</label>
                            <input type="password" id="senha" name="senha" class="form-control" />
                        </div>
                        <input type="submit" id="btn-login" class="btn btn-custom btn-lg btn-block" value="Entrar">
                        <p class="text-center"><em>v2.0 - Unimed São José do Rio Pardo</em></p>
                    </form>
                    <hr>
        	    </div>
    		</div> 
    	</div>
    </div> 
</section>