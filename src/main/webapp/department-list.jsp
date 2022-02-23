<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main-layout>
    <div class="row">
        <div class="container">
            <h3 class="text-center">Список отделов</h3>
            <hr>
            <div class="container text-left">
                <a href="${pageContext.request.contextPath}/departmentCreate" class="btn btn-success">Добавить новый отдел</a>
            </div>
            <br>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Название</th>
                    <th>Офис</th>
                    <th>Операции</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="department" items="${departmentList}">
                    <tr>
                        <td>
                                ${department.id}
                        </td>
                        <td>
                                ${department.name()}
                        </td>
                        <td>
                                ${department.office.internalName}
                        </td>

                        <td>
                            <a href="departmentEdit?id=${department.id}">Редактировать</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="deleteDepartment?id=${department.id}">Удалить</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</t:main-layout>