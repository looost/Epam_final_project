<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="customTag" tagdir="/WEB-INF/tags" %>
<!-- portfolio -->
<div class="portfolio">
    <h1 class="text-center m-3" style="font-family: segoe print">Сериалы</h1>


    <div class="container-fluid">

        <div class="row">

            <div class="col-2 mb-3 p-0">
                <jsp:useBean id="genres" scope="request" type="java.util.List"/>
                <jsp:useBean id="country" scope="request" type="java.util.List"/>
                <jsp:useBean id="studio" scope="request" type="java.util.List"/>
                <customTag:search-filter genres="${genres}" country="${country}" studio="${studio}"/>
            </div>

            <div class="col-10 mb-3">
                <div class="container-fluid">
                    <div class="row">

                        <%--                        <c:forEach items="${shows}" var="s">--%>
                        <%--                            <div class="col-3 card p-0 mb-2">--%>
                        <%--                                <a href="/final/show.html?id=${s.id}">--%>
                        <%--                                    <img class="card-img-top" src="${s.logo}" alt="Card image cap" height="144px">--%>
                        <%--                                </a>--%>

                        <%--                                <div class="card-header" style="max-height: 3rem">--%>
                        <%--                                    <a class="card-title" href="/final/show.html?id=${s.id}"--%>
                        <%--                                       style="font-family: segoe print">--%>
                        <%--                                        <h5 class="text-center">${s.name}</h5>--%>
                        <%--                                    </a>--%>
                        <%--                                </div>--%>
                        <%--                                <div class="card-body">--%>
                        <%--                                    <p class="card-text"><em>${fn:substring(s.description, 0, 100)}...</em></p>--%>
                        <%--                                </div>--%>
                        <%--                                <div class="card-footer">--%>
                        <%--                                    <small class="text-muted">${s.studio.name}</small>--%>
                        <%--                                </div>--%>
                        <%--                            </div>--%>
                        <%--                        </c:forEach>--%>

                            <div class="row row-cols-1 row-cols-md-3">
                            <jsp:useBean id="shows" scope="request" type="java.util.List"/>
                            <c:forEach items="${shows}" var="s">
                                <div class="col mb-4">
                                    <div class="card h-100">
                                        <a href="${pageContext.request.contextPath}/show.html?id=${s.id}">
                                            <img class="card-img-top" src="/final/${s.logo}" alt="Card image cap">
                                        </a>

                                        <div class="card-header" style="max-height: 3rem">
                                            <a class="card-title"
                                               href="${pageContext.request.contextPath}/show.html?id=${s.id}"
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


            </div>  <!-- row -->

    </div>

</div>

<nav aria-label="Page navigation example">
    <ul class="pagination pagination-lg justify-content-center">
        <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Предыдущая</a>
        </li>
        <li class="page-item active"><a class="page-link" href="#">1</a></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item">
            <a class="page-link" href="#">Следующая</a>
        </li>
    </ul>
</nav>