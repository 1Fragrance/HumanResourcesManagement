<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="activeStatus" value="1" scope="application" />

<t:main-layout>
<div class="row">
    <div class="container">
        <h3 class="text-center">История сотрудников</h3>
        <hr>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>ФИО Сотрудника</th>
                <th>Дата начала</th>
                <th>Дата окончания</th>
                <th>Отдел</th>
                <th>Должность</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="history" items="${historyList}">
                <tr>
                    <td>
                        ${history.id}
                    </td>
                    <td>
                        ${history.employee.getFullName()}
                    </td>
                    <td>
                        ${history.startDate}
                    </td>
                    <td>
                        ${history.endDate}
                    </td>
                    <td>
                        ${history.department.name}
                    </td>
                    <td>
                        ${history.position.title}
                    </td>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</t:main-layout>
