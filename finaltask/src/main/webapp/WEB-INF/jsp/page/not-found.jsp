<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>


<div id="notfound">
    <div class="notfound">
        <div class="notfound-404">
            <div></div>
            <h1>404</h1>
        </div>
        <h2 class="text-style">Page not found</h2>
        <p class="text-style"><fmt:message key="pageNotFound" bundle="${ rb }"/></p>
        <a href="${pageContext.request.contextPath}/index.html"><fmt:message key="home" bundle="${ rb }"/></a>
    </div>
</div>