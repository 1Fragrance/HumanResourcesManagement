<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:main-layout>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${employee != null && employee.id != 0}">
                <form action="employeeEdit" method="post">
            </c:if>
            <c:if test="${employee == null || employee.id == 0}">
                <form action="employeeCreate" method="post">
            </c:if>
                    <caption>
                        <h2>
                            <c:if test="${employee != null && employee.id != 0}">
                                Редактирование сотрудника
                            </c:if>
                            <c:if test="${employee == null || employee.id == 0}">
                                Добавление нового сотрудника
                            </c:if>
                        </h2>
                    </caption>
                    <c:if test="${employee != null && employee.id != 0}">
                        <input type="hidden" name="id" value="${employee.id}" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Имя</label>
                        <input type="text" value="${employee.firstName}" class="form-control" name="firstName">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Фамилия</label>
                        <input type="text" value="${employee.lastName}" class="form-control" name="lastName">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Отчество</label>
                        <input type="text" value="${employee.patronymic}" class="form-control" name="patronymic">
                    </fieldset>

                <fieldset class="form-group">
                    <label>Номер телефона</label>
                    <input type="text" value="${employee.phoneNumber}" class="form-control" name="phoneNumber">
                </fieldset>

                <fieldset class="form-group">
                    <label>Почта</label>
                    <input type="email" value="${employee.email}" class="form-control" name="email">
                </fieldset>

                <c:if test="${employee != null}">
                <fieldset class="form-group">
                    <label>Статус</label>
                    <select name="status" class="form-control">
                        <c:forEach items="${statuses}" var="status">
                            <option value="${status.value}"
                            <c:if test="${status.value eq employee.status}">selected="selected"</c:if>
                            >${status.name}</option>
                        </c:forEach>
                    </select>
                </fieldset>
                </c:if>

                <fieldset class="form-group">
                    <label>Должность</label>
                    <select name="positionId" class="form-control">
                        <c:forEach items="${positions}" var="position">
                            <option value="${position.value}"
                            <c:if test="${position.value eq employee.positionId}">selected="selected"</c:if>
                            >${position.name}</option>
                        </c:forEach>
                    </select>
                </fieldset>

                <fieldset class="form-group">
                    <label>Департамент</label>
                    <select name="departmentId" class="form-control">
                        <c:forEach items="${departments}" var="department">
                            <option value="${department.value}"
                            <c:if test="${department.value eq employee.departmentId}">selected="selected"</c:if>
                            >${department.name}</option>
                        </c:forEach>
                    </select>
                </fieldset>

                <fieldset class="form-group">
                    <label>Заработная плата</label>
                    <input type="number" value="${employee.salary}" class="form-control" name="salary">
                </fieldset>

                <fieldset class="form-group">
                    <label>Логин</label>
                    <input type="text" value="${employee.userName}" class="form-control" name="userName">
                </fieldset>

                <fieldset class="form-group">
                    <label>Пароль</label>
                    <input type="password" value="${employee.password}" class="form-control" name="password">
                </fieldset>

                <c:if test="${employee != null && employee.id != 0}">
                <fieldset class="form-group">
                    <label>Является администратором:</label>
                    <br>
                    <c:if test="${employee.admin}">
                        <label>Да</label>
                    </c:if>
                    <c:if test="${!employee.admin}">
                        <label>Нет</label>
                    </c:if>
                </fieldset>
                </c:if>

                <c:if test="${employee != null && employee.id != 0}">
                <fieldset class="form-group">
                    <label>Дата создания</label>
                    <br>
                    <label>${employee.hireDate}</label>
                </fieldset>
                </c:if>
                    <p style="color: red;">${errorString}</p>
                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</t:main-layout>
