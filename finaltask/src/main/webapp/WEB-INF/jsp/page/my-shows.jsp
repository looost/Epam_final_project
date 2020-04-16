<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="customTag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>
<!-- portfolio -->
<div class="portfolio">
    <h1 class="text-center m-3" style="font-family: segoe print"><fmt:message key="myShows" bundle="${ rb }"/></h1>


    <div class="container-fluid">

        <div class="row">

            <div class="col-2 m-2">
                <div class="list-group">
                    <a href="${pageContext.request.contextPath}/profile.html"
                       class="list-group-item list-group-item-action">
                        <fmt:message key="profile" bundle="${ rb }"/></a>
                    <a href="${pageContext.request.contextPath}/my_serial.html"
                       class="list-group-item list-group-item-action active">
                        <fmt:message key="myShows" bundle="${ rb }"/></a>
                </div>
            </div>

            <div class="col-9 mb-3">
                <div class="container-fluid">
                    <div class="row">

                        <jsp:useBean id="shows" scope="request" type="java.util.List"/>

                        <c:if test="${empty shows}">
                            <div class="container">
                                <div class="alert alert-info m-2 text-center" role="alert">
                                    <h4 class="alert-heading">УПС!</h4>
                                    <hr>
                                    <p class="mb-0">Вы пока не смотритите никаких сериалов!</p>
                                </div>
                            </div>
                        </c:if>


                        <c:forEach items="${shows}" var="s">
                            <div class="col-4 mb-4">
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
