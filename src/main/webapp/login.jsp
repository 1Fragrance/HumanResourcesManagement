<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main-layout>
    <div class="row login-window">
        <div class="container col-md-4">
        <div class="card">
        <article class="card-body">
            <h4 class="card-title mb-4 mt-1">Вход в систему</h4>
            <form action="login" method="post">
                <div class="form-group">
                    <label>Логин</label>
                    <input name="userName" class="form-control" placeholder="Логин" type="text">
                </div>
                <div class="form-group">
                    <label>Пароль</label>
                    <input class="form-control" placeholder="******" type="password" name="password">
                </div>
                <p style="color: red;">${errorString}</p>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"> Войти в систему  </button>
                </div>
            </form>
        </article>
        </div>
        </div>
    </div>
</t:main-layout>