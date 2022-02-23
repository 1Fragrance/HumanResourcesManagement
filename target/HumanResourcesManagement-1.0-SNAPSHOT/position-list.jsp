<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main-layout>
    <div class="row">
        <div class="container">
            <h3 class="text-center">Список должностей</h3>
            <hr>
            <div class="container text-left">
                <a href="${pageContext.request.contextPath}/positionCreate" class="btn btn-success">Добавить новую должность</a>
            </div>
            <br>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Название</th>
                    <th>Минимальная ЗП</th>
                    <th>Максимальная ЗП</th>
                    <th>Операции</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="position" items="${positionList}">
                    <tr>
                        <td>
                                ${position.id}
                        </td>
                        <td>
                                ${position.title}
                        </td>
                        <td>
                                ${position.minSalary}
                        </td>
                        <td>
                                ${position.maxSalary}
                        </td>
                        <td>
                            <a href="positionEdit?id=${position.id}">Редактировать</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="deletePosition?id=${position.id}">Удалить</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</t:main-layout>
