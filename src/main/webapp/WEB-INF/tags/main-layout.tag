<%@tag description="layout" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="t" %>


<!DOCTYPE html>
<html>
<head>
    <title>Система управления персоналом</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="/" class="navbar-brand"> Система управления персоналом </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="${pageContext.request.contextPath}/employee" class="nav-link">Сотрудники</a></li>
        </ul>
    </nav>
</header>

<jsp:doBody/>
</body>
</html>