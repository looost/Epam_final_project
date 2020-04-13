<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-4"></div>

        <div class="col-4">

            <form method="post" action="${pageContext.request.contextPath}/registr.html">
                <div class="card mt-5 mb-5" style="background-color: #9999CC">
                    <form class="ml-3 mr-3">
                        <div class="form-group m-2">
                            <label for="inputEmail"><fmt:message key="login" bundle="${ rb }"/>:</label>
                            <input type="text" name="login"
                                   class="form-control ${incorrect != null ? 'is-invalid' : ''}" id="inputEmail"
                                   placeholder="<fmt:message key="enterLogin" bundle="${ rb }" />" required>
                            <c:if test="${not empty incorrect}">
                                <div class="invalid-feedback">${incorrect}</div>
                            </c:if>
                        </div>
                        <div class="form-group m-2">
                            <label for="inputPassword"><fmt:message key="password" bundle="${ rb }"/>:</label>
                            <input type="password" name="password" class="form-control " id="inputPassword"
                                   placeholder="<fmt:message key="enterPassword" bundle="${ rb }" />" required>
                            <%--                            <c:if test="${sessionScope.incorrect != null}">--%>
                            <%--                                <div class="invalid-feedback">${sessionScope.incorrect}</div>--%>
                            <%--                            </c:if>--%>


                        </div>

                        <button type="submit" class="btn btn-primary m-2">
                            <fmt:message key="registr" bundle="${ rb }"/>
                        </button>

                    </form>
                </div>
            </form>


        </div>


        <div class="col-4"></div>


    </div>
</div>
