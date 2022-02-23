<%@tag description="layout" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="t" %>


<!DOCTYPE html>
<html>
<head>
    <title>Система управления персоналом</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style><%@include file="/WEB-INF/styles.css"%></style>
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark " style="background-color: tomato">
        <div class="container-fluid">
        <ul class="navbar-nav navbar-left">
            <li><a href="${pageContext.request.contextPath}/" class="navbar-brand"> Система управления персоналом </a></li>
            <t:if test="${user != null}">
            <li><a href="${pageContext.request.contextPath}/employee" class="nav-link">Сотрудники</a></li>
            <li><a href="${pageContext.request.contextPath}/office" class="nav-link">Офисы</a></li>
            <li><a href="${pageContext.request.contextPath}/department" class="nav-link">Отделы</a></li>
            <li><a href="${pageContext.request.contextPath}/position" class="nav-link">Должности</a></li>
            </t:if>
        </ul>
        <t:if test="${user != null}">
            <form method="post" action="logout">
            <ul class="nav navbar-nav navbar-right">
                <li><a onclick="this.closest('form').submit();return false;" class="nav-link" href="${pageContext.request.contextPath}/logout">Выход из системы</a></li>
            </ul>
            </form>
        </t:if>
        </div>
    </nav>
</header>

<jsp:doBody/>
</body>
</html>