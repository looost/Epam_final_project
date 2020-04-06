<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ attribute name="last" required="true" type="java.util.List" %>

<fmt:setLocale value="${cookie.language.value}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>

<div class="card text-white bg-dark mb-3" style="max-height: 3rem;">
    <div class="card-header">
        <h6 class="text-center" style="font-family: segoe print">
            <fmt:message key="newShows" bundle="${ rb }"/>
        </h6>
    </div>
</div>

<jsp:useBean id="last" scope="request" type="java.util.List"/>
<c:forEach var="l" items="${last}">
    <div class="card mb-3">
        <a href="${pageContext.request.contextPath}/show.html?id=${l.id}">
            <img class="card-img-top" src="${l.logo}" alt="Card image cap">
        </a>
        <div class="card-body">
            <h5 class="card-title">${l.name}</h5>
            <p class="card-text"><small class="text-muted">
                <fmt:formatDate value="${l.releaseDate}" pattern="dd-MM-yyyy"/> </small>
            </p>
        </div>
    </div>
</c:forEach>