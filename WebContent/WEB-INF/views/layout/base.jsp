<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<spring:url value="/resources/js/jquery.min.js" var="jqueryJS"></spring:url>
<spring:url value="/resources/css/normalize.css" var="normalizeCSS"></spring:url>
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCSS"></spring:url>
<spring:url value="/resources/js/bootstrap.bundle.min.js" var="bootstrapJS"></spring:url>
<spring:url value="/resources/css/fontawesome.min.css" var="fontawesomeCSS"></spring:url>
<spring:url value="/resources/css/principal.css" var="principalCSS"></spring:url>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="/resources/img/logo_unimed.png" type="image/x-icon"/>
<title>UniResultado - <tiles:insertAttribute name="title" ignore="true"></tiles:insertAttribute></title>
<script type="text/javascript" src="${jqueryJS}"></script>
<link href="${normalizeCSS}" rel="stylesheet">
<link href="${bootstrapCSS}" rel="stylesheet">
<script type="text/javascript" src="${bootstrapJS}"></script>
<link href="${fontawesomeCSS}" rel="stylesheet">
<link href="${principalCSS}" rel="stylesheet">
</head>
<body>
	<div>
		<tiles:insertAttribute name="header" ignore="false"></tiles:insertAttribute>
	</div>
	<div class="container">
		<tiles:insertAttribute name="body" ignore="false"></tiles:insertAttribute>
	</div>
</body>
</html>