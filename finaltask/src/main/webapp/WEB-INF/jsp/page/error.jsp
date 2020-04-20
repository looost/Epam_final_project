
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.03.2020
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>
<div class="container-fluid">

    <%--    <div class="alert alert-danger m-2 text-center" role="alert">--%>
    <%--        <h4 class="alert-heading">УПС!</h4>--%>
    <%--        <hr>--%>
    <%--        <c:if test="${error != null}">--%>
    <%--            <p class="mb-0">${error}</p>--%>
    <%--        </c:if>--%>
    <%--    </div>--%>

    <%--    <div class="alert alert-danger m-2 " role="alert">--%>
    <%--        <h4 class="alert-heading">Code: ${statusCode }</h4>--%>
    <%--        <hr>--%>
    <%--        <c:choose>--%>
    <%--            <c:when test="${statusCode == 403}"><fmt:message key="accessError" bundle="${ rb }"/></c:when>--%>
    <%--            <c:when test="${statusCode == 404}"><fmt:message key="pageNotFound" bundle="${ rb }"/></c:when>--%>
    <%--            <c:when test="${statusCode == 500}"><fmt:message key="errorProcessingReq" bundle="${ rb }"/></c:when>--%>
    <%--            <c:otherwise><fmt:message key="cantProcessReq" bundle="${ rb }"/></c:otherwise>--%>
    <%--        </c:choose>--%>
    <%--    </div>--%>

    <div class="container py-5">
        <div class="row">
            <div class="col-md-2 text-center">
                <p><i class="fa fa-exclamation-triangle fa-5x"></i><br/>Status Code: ${statusCode }</p>
            </div>
            <div class="col-md-10">
                <h3 class="text-style"><fmt:message key="oppss" bundle="${ rb }"/></h3>
                <c:choose>
                    <c:when test="${statusCode == 403}"><p class="text-style"><fmt:message key="accessError"
                                                                                           bundle="${ rb }"/></p>
                    </c:when>
                    <c:when test="${statusCode == 500}"><p class="text-style"><fmt:message key="errorProcessingReq"
                                                                                           bundle="${ rb }"/></p>
                    </c:when>
                    <c:otherwise><p class="text-style"><fmt:message key="cantProcessReq" bundle="${ rb }"/></p>
                    </c:otherwise>
                </c:choose>
                <a class="btn btn-danger" href="javascript:history.back()">Go Back</a>
            </div>
        </div>
    </div>

</div>