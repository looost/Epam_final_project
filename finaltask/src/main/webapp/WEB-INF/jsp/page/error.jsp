
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
<div class="container">

    <div class="alert alert-danger m-2 text-center" role="alert">
        <h4 class="alert-heading">УПС!</h4>
        <hr>
        <c:if test="${error != null}">
            <p class="mb-0">${error}</p>
        </c:if>
    </div>

</div>