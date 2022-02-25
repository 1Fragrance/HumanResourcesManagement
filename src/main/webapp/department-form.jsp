<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:main-layout>
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <c:if test="${department != null}">
                <form action="departmentEdit" method="post">
                    </c:if>
                    <c:if test="${department == null}">
                    <form action="departmentCreate" method="post">
                        </c:if>
                        <caption>
                            <h2>
                                <c:if test="${department != null}">
                                    Редактирование информации об отделе
                                </c:if>
                                <c:if test="${department == null}">
                                    Добавление нового отдела
                                </c:if>
                            </h2>
                        </caption>
                        <c:if test="${department != null}">
                            <input type="hidden" name="id" value="${department.id}" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Название</label>
                            <input type="text" value="${department.name}" class="form-control" name="name">
                        </fieldset>


                        <fieldset class="form-group">
                            <label>Офис</label>
                            <select name="officeId" class="form-control">
                                <c:forEach items="${offices}" var="office">
                                    <option value="${office.value}"
                                            <c:if test="${office.value eq department.officeId}">selected="selected"</c:if>
                                    >${office.name}</option>
                                </c:forEach>
                            </select>
                        </fieldset>
                        <p style="color: red;">${errorString}</p>
                        <button type="submit" class="btn btn-success">Save</button>
                    </form>
            </div>
        </div>
    </div>
</t:main-layout>
