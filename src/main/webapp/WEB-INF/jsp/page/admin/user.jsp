<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>
<div class="container-fluid text-style">

    <div class="row">
        <div class="col-2 m-2">
            <div class="list-group">
                <a href="${pageContext.request.contextPath}/admin/serial.html"
                   class="list-group-item list-group-item-action">
                    <fmt:message key="editSerial" bundle="${ rb }"/></a>
                <a href="${pageContext.request.contextPath}/admin/genre.html"
                   class="list-group-item list-group-item-action">
                    <fmt:message key="editGenre" bundle="${ rb }"/></a>
                <a href="${pageContext.request.contextPath}/admin/country.html"
                   class="list-group-item list-group-item-action">
                    <fmt:message key="editCountry" bundle="${ rb }"/></a>
                <a href="${pageContext.request.contextPath}/admin/studio.html"
                   class="list-group-item list-group-item-action">
                    <fmt:message key="editStudio" bundle="${ rb }"/></a>
                <a href="${pageContext.request.contextPath}/admin/user.html"
                   class="list-group-item list-group-item-action active">
                    <fmt:message key="editUser" bundle="${ rb }"/></a>
            </div>
        </div>
        <div class="col-9">

            <div class="card-body">
                <form method="post" action="${pageContext.request.contextPath}/add_user.html" class="was-validated">

                    <div class="mb-3">
                        <label for="validationLogin"><fmt:message key="enterLogin" bundle="${ rb }"/>:</label>
                        <input type="text" name="login" class="form-control is-invalid"
                               id="validationLogin" placeholder="<fmt:message key="enterLogin" bundle="${ rb }"/>"
                               required>
                        <c:if test="${not empty incorrectLogin}">
                            <div class="invalid-feedback text-style">
                                <fmt:message key="${incorrectLogin}" bundle="${ rb }"/>
                            </div>
                        </c:if>
                    </div>

                    <div class="mb-3">
                        <label for="validationPassword"><fmt:message key="password" bundle="${ rb }"/>:</label>
                        <input type="password" name="password" class="form-control is-invalid"
                               id="validationPassword" placeholder="<fmt:message key="password" bundle="${ rb }"/>"
                               required>
                    </div>

                    <div class="form-group">
                        <label for="roleValue"><fmt:message key="selectRole" bundle="${ rb }"/></label>
                        <select class="custom-select" id="roleValue" name="role" required>
                            <option value="0"><fmt:message key="administrator" bundle="${ rb }"/></option>
                            <option value="1"><fmt:message key="moderator" bundle="${ rb }"/></option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary"><fmt:message key="add" bundle="${ rb }"/></button>
                </form>

                <form method="get" action="${pageContext.request.contextPath}/admin/user.html">
                    <div class="form-row align-items-center">
                        <div class="col-8">
                            <input class="form-control mr-sm-2 text-style" type="search"
                                   placeholder="<fmt:message key="enterLogin" bundle="${ rb }"/>"
                                   name="login" value="${user.login}">
                        </div>
                        <div class="col-4">
                            <button type="submit" class="btn btn-secondary btn-block text-style">
                                <fmt:message key="search" bundle="${ rb }"/>
                            </button>
                        </div>
                    </div>
                </form>

                <c:if test="${not empty user}">
                    <form method="post" action="${pageContext.request.contextPath}/change_role.html">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th scope="col"><fmt:message key="login" bundle="${ rb }"/></th>
                                <th scope="col"><fmt:message key="role" bundle="${ rb }"/></th>
                                <th scope="col"><fmt:message key="selectRole" bundle="${ rb }"/></th>
                                <th scope="col"><fmt:message key="edit" bundle="${ rb }"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>
                                    <input type="text" readonly class="form-control-plaintext" id="staticLogin"
                                           name="login" value="${user.login}">
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.role == 0}">
                                            <fmt:message key="administrator" bundle="${ rb }"/>
                                        </c:when>
                                        <c:when test="${user.role == 1}">
                                            <fmt:message key="moderator" bundle="${ rb }"/>
                                        </c:when>
                                        <c:when test="${user.role == 2}">
                                            <fmt:message key="user" bundle="${ rb }"/>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <select class="custom-select" id="userRole" name="role" required>
                                        <option value="0"><fmt:message key="administrator" bundle="${ rb }"/></option>
                                        <option value="1"><fmt:message key="moderator" bundle="${ rb }"/></option>
                                        <option value="2"><fmt:message key="user" bundle="${ rb }"/></option>
                                    </select>
                                </td>
                                <td>
                                    <button type="submit" class="btn btn-secondary btn-block text-style">
                                        <fmt:message key="edit" bundle="${ rb }"/>
                                    </button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </c:if>

            </div>

        </div>
    </div>
</div>
