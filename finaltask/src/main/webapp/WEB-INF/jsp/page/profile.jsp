<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 09.02.2020
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/profilepost.html">
        <div class="form-group mt-3">
            <label for="exampleFormControlInput1">Название сериала:</label>
            <input type="text" name="name" class="form-control" id="exampleFormControlInput1"
                   placeholder="Название сериала">
        </div>
        <div class="form-group">
            <label for="description">Описание сериала</label>
            <textarea class="form-control" name="description" id="description" rows="3"></textarea>
        </div>

        <div class="form-group">
            <label for="logo">Выберите фото превью</label>
            <input type="file" class="form-control-file" id="logo" name="logo">
        </div>

        <%--        <div class="form-group">--%>
        <%--            <label for="logo">Ссылка на лого</label>--%>
        <%--            <input type="text" name="logo" class="form-control" id="logo"--%>
        <%--                   placeholder="Ссылка на лого">--%>
        <%--        </div>--%>

        <div class="form-group">
            <label for="full_logo">Выберите полное фото</label>
            <input type="file" class="form-control-file" id="full_logo" name="full_logo">
        </div>

        <%--        <div class="form-group">--%>
        <%--            <label for="full_logo">Ссылка на полное лого</label>--%>
        <%--            <input type="text" name="full_logo" class="form-control" id="full_logo"--%>
        <%--                   placeholder="Ссылка на полное лого">--%>
        <%--        </div>--%>

        <div class="form-group">
            <label for="date">Введите дату выхода сериала:</label>
            <input type="date" class="form-control" name="release_date" id="date">
        </div>

        <div class="mb-2">
            <label>Выберите жанры:</label>
            <jsp:useBean id="genres" scope="request" type="java.util.List"/>
            <c:forEach var="g" items="${genres}">
                <div class="custom-control custom-checkbox">
                    <input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="${g.id}" name="genre">
                    <label class="form-check-label" for="inlineCheckbox2">${g.name}</label>
                </div>
            </c:forEach>
        </div>

        <div class="mb-2">
            <label>Выберите страну:</label>
            <br>
            <select class="custom-select" name="country">
                <jsp:useBean id="country" scope="request" type="java.util.List"/>
                <c:forEach var="c" items="${country}">
                    <option value="${c.id}">${c.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-2">
            <label>Выберите студию:</label>
            <select class="custom-select" name="studio">
                <jsp:useBean id="studio" scope="request" type="java.util.List"/>
                <c:forEach var="s" items="${studio}">
                    <option value="${s.id}">${s.name}</option>
                </c:forEach>
            </select>
        </div>


        <button type="submit" class="btn btn-primary">Добавить</button>
    </form>
</div>