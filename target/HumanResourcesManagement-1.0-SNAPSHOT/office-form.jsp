<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:main-layout>
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                    <c:if test="${office != null && office.id != 0}">
                        <form action="officeEdit" method="post">
                    </c:if>
                    <c:if test="${office == null || office.id == 0}">
                        <form action="officeCreate" method="post">
                    </c:if>
                        <caption>
                            <h2>
                                <c:if test="${office != null && office.id != 0}">
                                    Редактирование информации об офисе
                                </c:if>
                                <c:if test="${office == null || office.id == 0}">
                                    Добавление нового офиса
                                </c:if>
                            </h2>
                        </caption>
                        <c:if test="${office != null && office.id != 0}">
                            <input type="hidden" name="id" value="${office.id}" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Название</label>
                            <input type="text" value="${office.internalName}" class="form-control" name="internalName">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Улица</label>
                            <input type="text" value="${office.streetAddress}" class="form-control" name="streetAddress">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Страна</label>
                            <input type="text" value="${office.country}" class="form-control" name="country">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Город</label>
                            <input type="text" value="${office.city}" class="form-control" name="city">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Индекс</label>
                            <input type="number" value="${office.postalCode}" class="form-control" name="postalCode">
                        </fieldset>
                        <p style="color: red;">${errorString}</p>
                        <button type="submit" class="btn btn-success">Save</button>
                    </form>
            </div>
        </div>
    </div>
</t:main-layout>
