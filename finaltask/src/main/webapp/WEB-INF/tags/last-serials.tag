<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ attribute name="last" required="true" type="java.util.List" %>

<fmt:setLocale value="${cookie.language.value}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>

<div class="card text-black bg-dark mb-3">
    <div class="card-header">
        <h6 class="text-center text-style">
            <em><fmt:message key="newShows" bundle="${ rb }"/></em>
        </h6>
    </div>
</div>

<jsp:useBean id="last" scope="request" type="java.util.List"/>
<c:forEach var="l" items="${last}">
    <div class="card mb-3 text-style">
        <a href="${pageContext.request.contextPath}/show.html?id=${l.id}">
            <img class="card-img-top rounded" src="<c:url value="/img/${l.logo}"/>" alt="Card image cap">
        </a>
        <div class="card-body text-center">
            <h5 class="card-title text-style">${l.name}</h5>
            <p class="card-text text-style"><small class="text-muted">
                <fmt:formatDate value="${l.releaseDate}" pattern="dd-MM-yyyy"/> </small>
            </p>
        </div>
    </div>
</c:forEach>