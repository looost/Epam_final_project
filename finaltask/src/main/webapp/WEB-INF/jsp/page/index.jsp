<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 09.02.2020
  Time: 00:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="customTag" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${cookie.language.value}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>
<!-- portfolio -->
<div class="portfolio">
    <%--    <h1 class="text-center m-3" style="font-family: segoe print">Сериалы</h1>--%>
    <div class="jumbotron jumbotron-fluid">
        <div class="container">
            <h1 class="display-4"><fmt:message key="serials" bundle="${ rb }"/></h1>
            <hr class="my-4">
            <p class="lead"><fmt:message key="welcome" bundle="${ rb }"/></p>
        </div>
    </div>

    <div class="container-fluid">

        <div class="row">


            <div class="col-2 mb-2 p-0">
                <jsp:useBean id="genres" scope="request" type="java.util.List"/>
                <jsp:useBean id="country" scope="request" type="java.util.List"/>
                <customTag:search-filter genres="${genres}" country="${country}"/>
            </div>


            <%--            <div class="col-1 mb-3 p-0">--%>
            <%--                <jsp:useBean id="genres" scope="request" type="java.util.List"/>--%>
            <%--                <c:forEach var="g" items="${genres}">--%>
            <%--                    <a class="nav-link"--%>
            <%--                       href="${pageContext.request.contextPath}/search.html?genre=${g.id}">${g.name}</a>--%>
            <%--                </c:forEach>--%>
            <%--            </div>--%>

            <div class="col-8 mb-3">
                <div class="container-fluid">
                    <div class="row">

                        <div class="row row-cols-1 row-cols-md-3">
                            <jsp:useBean id="shows" scope="request" type="java.util.List"/>
                        <c:forEach items="${shows}" var="s">
                            <div class="col mb-4">
                                <div class="card h-100">
                                    <a href="${pageContext.request.contextPath}/show.html?id=${s.id}">
                                    <img class="card-img-top" src="/final/${s.logo}" alt="Card image cap">
                                </a>

                                <div class="card-header" style="max-height: 3rem">
                                    <a class="card-title" href="${pageContext.request.contextPath}/show.html?id=${s.id}"
                                       style="font-family: segoe print">
                                        <h5 class="text-center">${s.name}</h5>
                                    </a>
                                </div>
                                <div class="card-body">
                                    <p class="card-text"><em>${fn:substring(s.description, 0, 100)}...</em></p>
                                </div>
                                <div class="card-footer">
                                    <small class="text-muted">${s.studio.name}</small>
                                </div>
                            </div>
                            </div>
                        </c:forEach>
                        </div>

                    </div>
                </div>
            </div>

            <div class="card col-2 mb-3 p-0">
                <jsp:useBean id="last" scope="request" type="java.util.List"/>
                <customTag:last-serials last="${last}"/>
            </div>

        </div>  <!-- row -->

    </div>

</div>
</div>

<nav aria-label="Page navigation example">
    <ul class="pagination pagination-lg justify-content-center">
        <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Предыдущая</a>
        </li>
        <li class="page-item active"><a class="page-link"
                                        href="${pageContext.request.contextPath}/index.html?page=1">1</a></li>
        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/index.html?page=2">2</a>
        </li>
        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/index.html?page=3">3</a>
        </li>
        <li class="page-item">
            <a class="page-link" href="#">Следующая</a>
        </li>
    </ul>
</nav>

