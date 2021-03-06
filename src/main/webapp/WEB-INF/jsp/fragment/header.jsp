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

        <img src=" <c:url value="/img/logo.png"/>" width="123" height="27"
             class="d-inline-block align-top" alt="">
    </a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link text-style" href="${pageContext.request.contextPath}/index.html">
                    <fmt:message key="home" bundle="${ rb }"/>
                    <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-style" href="${pageContext.request.contextPath}/rating.html">
                    <fmt:message key="rating" bundle="${ rb }"/></a>
            <c:if test="${sessionScope.login != null}">
                <li class="nav-item">
                    <a class="nav-link text-style" href="${pageContext.request.contextPath}/profile.html">
                        <fmt:message key="profile" bundle="${ rb }"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-style" href="${pageContext.request.contextPath}/my_serial.html">
                        <fmt:message key="myShows" bundle="${ rb }"/>
                    </a>
                </li>
            </c:if>
            <c:if test="${sessionScope.role == 1 || sessionScope.role == 0}">
                <li class="nav-item">
                    <a class="nav-link text-style" href="${pageContext.request.contextPath}/admin/serial.html">
                        <fmt:message key="adminPanel" bundle="${ rb }"/>
                    </a>
                </li>
            </c:if>
        </ul>
        <form class="form-inline my-2 my-lg-0" method="get" action="${pageContext.request.contextPath}/search.html">
            <input class="form-control mr-sm-2 text-style" type="search"
                   placeholder="<fmt:message key="enterSerialName" bundle="${ rb }"/>" aria-label="Поиск" name="query">
            <button class="btn btn-outline-success my-2 my-sm-0 mr-3 text-style" type="submit">
                <fmt:message key="search" bundle="${ rb }"/>
            </button>
        </form>
    </div>
    <c:if test="${sessionScope.login == null}">
        <div>
            <a href="${pageContext.request.contextPath}/login.html" class="btn btn-dark btn-lg active text-style"
               role="button"
               aria-pressed="true">
                <fmt:message key="signIn" bundle="${ rb }"/>
            </a>
        </div>
    </c:if>
    <c:if test="${sessionScope.login != null}">
        <div class="dropdown">
            <a class="btn btn-dark btn-lg active text-style" href="#" role="button" id="dropdownMenuLink"
               data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
                    ${sessionScope.login}
            </a>

            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuLink">
                <a class="dropdown-item text-style" href="${pageContext.request.contextPath}/profile.html">
                    <fmt:message key="profile" bundle="${ rb }"/>
                </a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item text-style" href="${pageContext.request.contextPath}/logout.html">
                    <fmt:message key="logOut" bundle="${ rb }"/>
                </a>
            </div>
        </div>
    </c:if>
    <ul class="navbar-nav flex-row d-none d-md-flex ml-2">
        <li class="nav-item dropdown">
            <a class="nav-item nav-link dropdown-toggle mr-md-2" href="#" id="bd-versions" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-globe fa-2x"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item text-style"
                   href="${pageContext.request.contextPath}/language.html?language=ru_RU">Русский</a>
                <a class="dropdown-item text-style"
                   href="${pageContext.request.contextPath}/language.html?language=en_EU">English</a>
                <a class="dropdown-item text-style"
                   href="${pageContext.request.contextPath}/language.html?language=be_BY">Беларускі</a>
            </div>
        </li>
    </ul>

</nav>