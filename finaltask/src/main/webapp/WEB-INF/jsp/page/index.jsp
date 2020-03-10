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
<!-- portfolio -->
<div class="portfolio">
    <h1 class="text-center m-3" style="font-family: segoe print">Сериалы</h1>


    <div class="container-fluid">

        <div class="row">

            <div class="col-1 mb-3 p-0">
                <c:forEach var="g" items="${genres}">
                    <a class="nav-link" href="#">${g.name}</a>
                </c:forEach>

            </div>

            <div class="col-9 mb-3">
                <div class="container-fluid">
                    <div class="row">

                        <div class="row row-cols-1 row-cols-md-4">
                        <c:forEach items="${shows}" var="s">
                            <div class="col mb-4">
                                <div class="card h-100">
                                <a href="/final/show?id=${s.id}">
                                    <img class="card-img-top" src="${s.logo}" alt="Card image cap" >
                                </a>

                                <div class="card-header" style="max-height: 3rem">
                                    <a class="card-title" href="/final/show?id=${s.id}" style="font-family: segoe print">
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

                <div class="card text-white bg-dark mb-3" style="max-height: 3rem;">
                    <div class="card-header">
                        <h6 class="text-center" style="font-family: segoe print">Новые сериалы:</h6>
                    </div>
                </div>

<c:forEach var="l" items="${last}">

    <div class="card mb-3">
        <a href="/final/show?id=${l.id}">
            <img class="card-img-top" src="${l.logo}" alt="Card image cap">
        </a>
        <div class="card-body">
            <h5 class="card-title">${l.name}</h5>
            <p class="card-text"><small class="text-muted"><fmt:formatDate value="${l.releaseDate}"
                                                                           pattern="dd-MM-yyyy"/> </small></p>
        </div>
    </div>


<%--                <div class="card bg-dark text-white mb-1">--%>
<%--                    <img class="card-img" src="${l.logo}" alt="Card image">--%>
<%--                    <div class="card-img-overlay overlay p-0">--%>
<%--                        <div class="layout">--%>
<%--                            <p class="info">${l.name}</p>--%>
<%--                            <p class="data">${l.releaseDate}</p>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
</c:forEach>

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
        <li class="page-item active"><a class="page-link" href="#">1</a></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item">
            <a class="page-link" href="#">Следующая</a>
        </li>
    </ul>
</nav>
