<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ attribute name="genres" required="true" type="java.util.List" %>
<%@ attribute name="country" required="true" type="java.util.List" %>

<fmt:setLocale value="${cookie.language.value}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>

<div class="ml-2">
    <form method="get" action="${pageContext.request.contextPath}/search.html">
        <input class="form-control mr-sm-2 mb-3" type="search" placeholder="Search" aria-label="Поиск" name="query">
        <div class="mt-2">
            <span class="badge badge-dark mb-1"><fmt:message key="genres" bundle="${ rb }"/></span>
            <c:forEach var="g" items="${genres}">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="customCheck${g.id}" value="${g.id}"
                           name="genre">
                    <label class="custom-control-label" for="customCheck${g.id}">${g.name}</label>
                </div>
            </c:forEach>
        </div>
        <div class="mt-2">
            <span class="badge badge-dark mb-1"><fmt:message key="country" bundle="${ rb }"/></span>
            <c:forEach var="c" items="${country}">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="customCountryCheck${c.id}" value="${c.id}"
                           name="country">
                    <label class="custom-control-label" for="customCountryCheck${c.id}">${c.name}</label>
                </div>
            </c:forEach>
        </div>
        <button type="submit" class="btn btn-secondary btn-lg btn-block mt-3">
            <fmt:message key="search" bundle="${ rb }"/>
        </button>
    </form>
</div>