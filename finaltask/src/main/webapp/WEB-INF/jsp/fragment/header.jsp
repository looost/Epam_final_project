<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 09.02.2020
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

    <a class="navbar-brand" href="/final/index">
        <img src="img/logo.png" width="123" height="27" class="d-inline-block align-top" alt="">
    </a>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/final/index">Главная<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/final/index">Сериалы</a>
            </li>
            <c:if test="${sessionScope.user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="/final/profile">Профиль</a>
                </li>
            </c:if>
        </ul>
        <form class="form-inline my-2 my-lg-0" method="get" action="/final/search">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Поиск" name="query">
            <button class="btn btn-outline-success my-2 my-sm-0 mr-3" type="submit">Поиск</button>
        </form>
    </div>
    <c:if test="${sessionScope.user == null}">
        <div>
            <a href="/final/login" class="btn btn-dark btn-lg active" role="button" aria-pressed="true">Войти</a>
        </div>
    </c:if>
    <c:if test="${sessionScope.user != null}">
        <div>
            <a href="/final/profile" class="btn btn-dark btn-lg active mr-3" role="button"
               aria-pressed="true">${sessionScope.user}</a>
        </div>
        <div>
            <a href="/final/logout" class="btn btn-dark btn-lg active" role="button" aria-pressed="true">Выход</a>
        </div>
    </c:if>
</nav>