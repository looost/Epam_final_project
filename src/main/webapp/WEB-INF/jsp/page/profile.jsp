<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 09.02.2020
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-2 m-2">
            <div class="list-group">
                <a href="${pageContext.request.contextPath}/profile.html"
                   class="list-group-item list-group-item-action text-style active">
                    <i class="fas fa-user-edit"></i> <fmt:message key="profile" bundle="${ rb }"/></a>
                <a href="${pageContext.request.contextPath}/my_serial.html"
                   class="list-group-item list-group-item-action text-style">
                    <i class="fas fa-eye"></i> <fmt:message key="myShows" bundle="${ rb }"/></a>
                <a href="${pageContext.request.contextPath}/liked.html"
                   class="list-group-item list-group-item-action text-style">
                    <i class="fas fa-heart"></i> <fmt:message key="liked" bundle="${ rb }"/></a>
            </div>
        </div>
        <div class="col-9">


            <div class="row mt-3">
                <div class="col-md-2">
                    <img src="<c:url value="/img/avatar/${avatar}"/>"
                         class="img img-rounded img-fluid" alt=""/>
                </div>

                <div class="col-md-10">
                    <form class="was-validated mt-2" method="post" enctype="multipart/form-data"
                          action="${pageContext.request.contextPath}/change_avatar.html">
                        <div class="custom-file mb-3">
                            <label class="custom-file-label" for="validatedAvatar">Загрузите новый аватар</label>
                            <input type="file" class="custom-file-input" id="validatedAvatar" name="avatar" required>
                            <c:if test="${userAvatarProblem != null}">
                                <div class="invalid-feedback"><fmt:message key="${userAvatarProblem}"
                                                                           bundle="${ rb }"/></div>
                            </c:if>
                        </div>
                        <button type="submit" class="btn btn-primary">
                            <fmt:message key="edit" bundle="${ rb }"/>
                        </button>
                    </form>
                </div>
            </div>


            <form class="was-validated m-2 text-style" method="post"
                  action="${pageContext.request.contextPath}/change_password.html">
                <div class="form-group row">
                    <label for="staticLogin" class="col-sm-2 col-form-label "> <fmt:message key="login"
                                                                                            bundle="${ rb }"/></label>
                    <div class="col-sm-10">
                        <input type="text" readonly class="form-control-plaintext" id="staticLogin"
                               value="${sessionScope.login}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputPassword" class="col-sm-2 col-form-label"> <fmt:message key="password"
                                                                                             bundle="${ rb }"/></label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control is-invalid"
                               name="password" id="inputPassword"
                               placeholder="<fmt:message key="password" bundle="${ rb }"/>" required>
                        <c:if test="${invalidPassword != null}">
                            <div class="invalid-feedback"><fmt:message key="${invalidPassword}" bundle="${ rb }"/></div>
                        </c:if>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputNewPassword" class="col-sm-2 col-form-label"> <fmt:message
                            key="newPassword"
                            bundle="${ rb }"/></label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" name="newPassword"
                               id="inputNewPassword" placeholder="<fmt:message key="newPassword" bundle="${ rb }"/>"
                               required>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">
                    <fmt:message key="edit" bundle="${ rb }"/>
                </button>
            </form>


        </div>
    </div>
</div>