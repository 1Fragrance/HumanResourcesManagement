<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main-layout>
    <div class="container">
        <div class="jumbotron">
            <h1 class="index-title">Система управления персоналом</h1>
        </div>
    </div>
    <div class="form-group">
        <div class="container">
            <div class="col-md-12 text-center">
                <ul class="index-button-list">

                    <li class="index-button-list-item"><a href="${pageContext.request.contextPath}/employee" class="col-5 btn btn-dark btn-lg">Управление сотрудниками</a></li>
                    <li class="index-button-list-item"><a href="/tasks.html" class="col-5 btn btn-dark btn-lg">Управление офисами</a></li>
                    <li class="index-button-list-item"><a href="/workers.html" class="col-5 btn btn-dark btn-lg">Управление отделами</a></li>
                    <li class="index-button-list-item"><a href="/workers.html" class="col-5 btn btn-dark btn-lg">Управление должностями</a></li>
                    <li class="index-button-list-item"><a href="/workers.html" class="col-5 btn btn-dark btn-lg">Просмотр истории сотрудников</a></li>
                </ul>
            </div>
        </div>
    </div>
</t:main-layout>