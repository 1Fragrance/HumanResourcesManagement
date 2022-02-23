<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main-layout>
    <div class="row">
        <div class="container">
            <h3 class="text-center">Список офисов</h3>
            <hr>
            <div class="container text-left">
                <a href="${pageContext.request.contextPath}/officeCreate" class="btn btn-success">Добавить новый офис</a>
            </div>
            <br>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Название</th>
                    <th>Страна</th>
                    <th>Город</th>
                    <th>Адрес</th>
                    <th>Индекс</th>
                    <th>Операции</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="office" items="${officeList}">
                    <tr>
                        <td>
                                ${office.id}
                        </td>
                        <td>
                                ${office.internalName}
                        </td>
                        <td>
                                ${office.country}
                        </td>
                        <td>
                                ${office.city}
                        </td>
                        <td>
                                ${office.streetAddress}
                        </td>
                        <td>
                                ${office.postalCode}
                        </td>
                        <td>
                            <a href="officeEdit?id=${office.id}">Редактировать</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="deleteOffice?id=${office.id}">Удалить</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</t:main-layout>
