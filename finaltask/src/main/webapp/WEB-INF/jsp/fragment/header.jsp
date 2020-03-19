<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 09.02.2020
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${cookie.language.value}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

    <a class="navbar-brand" href="${pageContext.request.contextPath}/index.html">
        <img src="img/logo.png" width="123" height="27" class="d-inline-block align-top" alt="">
    </a>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/index.html">
                    <fmt:message key="home" bundle="${ rb }"/>
                    <span class="sr-only">(current)</span></a>
            </li>
            <%--            <li class="nav-item">--%>
            <%--                <a class="nav-link" href="/final/index.html">Сериалы</a>--%>
            <%--            </li>--%>
            <c:if test="${sessionScope.user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/profile.html">
                        <fmt:message key="profile" bundle="${ rb }"/>
                    </a>
                </li>
            </c:if>
        </ul>
        <form class="form-inline my-2 my-lg-0" method="get" action="${pageContext.request.contextPath}/search.html">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Поиск" name="query">
            <button class="btn btn-outline-success my-2 my-sm-0 mr-3" type="submit">
                <fmt:message key="search" bundle="${ rb }"/>
            </button>
        </form>
    </div>
    <c:if test="${sessionScope.user == null}">
        <div>
            <a href="${pageContext.request.contextPath}/login.html" class="btn btn-dark btn-lg active" role="button"
               aria-pressed="true">
                <fmt:message key="signIn" bundle="${ rb }"/>
            </a>
        </div>
    </c:if>
    <c:if test="${sessionScope.user != null}">
        <div class="dropdown">
            <a class="btn btn-dark btn-lg active" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
                    ${sessionScope.user}
            </a>

            <div class="dropdown-menu dropdown-menu-right dropdown-menu-lg-left" aria-labelledby="dropdownMenuLink">
                <a class="dropdown-item" href="${pageContext.request.contextPath}/profile.html">
                    <fmt:message key="profile" bundle="${ rb }"/>
                </a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/logout.html">
                    <fmt:message key="logOut" bundle="${ rb }"/>
                </a>
            </div>
        </div>
        <%--        <div>--%>
        <%--            <a href="/final/profile.html" class="btn btn-dark btn-lg active mr-3" role="button"--%>
        <%--               aria-pressed="true">${sessionScope.user}</a>--%>
        <%--        </div>--%>
        <%--        <div>--%>
        <%--            <a href="/final/logout.html" class="btn btn-dark btn-lg active" role="button" aria-pressed="true">Выход</a>--%>
        <%--        </div>--%>
    </c:if>
    <%--    <div class="dropdown-menu dropdown-menu-right dropdown-menu-lg-left" aria-labelledby="dropdownMenuLink">--%>
    <%--        <a class="dropdown-item" href="/final/profile.html">Профиль</a>--%>
    <%--        <div class="dropdown-divider"></div>--%>
    <%--        <a class="dropdown-item" href="/final/logout.html">Выход</a>--%>
    <%--    </div>--%>
    <div>
        <form method="post" action="${pageContext.request.contextPath}/language.html">
            <label>
                <select name="language">
                    <option value="ru_RU">Русский</option>
                    <option value="en_EU">English</option>
                    <option value="by_BY">Беларускі</option>
                </select>
            </label>
            <input type="submit" value="<fmt:message key="send" bundle="${ rb }" />">
        </form>
    </div>
</nav>