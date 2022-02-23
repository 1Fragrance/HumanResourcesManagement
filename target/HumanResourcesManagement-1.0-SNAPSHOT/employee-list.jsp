<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="activeStatus" value="1" scope="application" />

<t:main-layout>
<div class="row">
    <div class="container">
        <h3 class="text-center">Список сотрудников</h3>
        <hr>
        <div class="container text-left">
            <a href="${pageContext.request.contextPath}/employeeCreate" class="btn btn-success">Добавить нового сотрудника</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>ФИО</th>
                <th>Почта</th>
                <th>Департамент</th>
                <th>Должность</th>
                <th>Статус</th>
                <th>Операции</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="employee" items="${employeeList}">
                <tr>
                    <td>
                        ${employee.id}
                    </td>
                    <td>
                        ${employee.getFullName()}
                    </td>
                    <td>
                        ${employee.email}
                    </td>
                    <td>
                        ${employee.department.name}
                    </td>
                    <td>
                        ${employee.position.title}
                    </td>
                    <td>
                        ${employee.statusName}
                    </td>
                    <td>
                        <c:if test="${!employee.admin}">
                            <c:choose>
                                <c:when test="${employee.status==activeStatus}">
                                    <a href="employeeBlock?id=${employee.id}">Заблокировать</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="employeeUnblock?id=${employee.id}">Разблокировать</a>
                                </c:otherwise>
                            </c:choose>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                        </c:if>

                        <a href="employeeEdit?id=${employee.id}">Редактировать</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deleteEmployee?id=${employee.id}">Удалить</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</t:main-layout>
