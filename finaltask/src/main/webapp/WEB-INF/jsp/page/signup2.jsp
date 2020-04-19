<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>


<%--<div class="container-fluid">--%>
<%--    <div class="row">--%>
<%--        <div class="col-4"></div>--%>

<%--        <div class="col-4">--%>

<%--            <form method="post" action="${pageContext.request.contextPath}/loginpost.html">--%>
<%--                <div class="card mt-5 mb-5" style="background-color: #9999CC">--%>
<%--                    <form class="ml-3 mr-3">--%>
<%--                        <div class="form-group m-2">--%>
<%--                            <label for="inputEmail"><fmt:message key="login" bundle="${ rb }"/>:</label>--%>
<%--                            <input type="text" name="login"--%>
<%--                                   class="form-control ${incorrectLoginOrPassword != null ? "is-invalid" : ""}"--%>
<%--                                   id="inputEmail"--%>
<%--                                   placeholder="<fmt:message key="enterLogin" bundle="${ rb }" />" required>--%>
<%--                        </div>--%>
<%--                        <div class="form-group m-2">--%>
<%--                            <label for="inputPassword"><fmt:message key="password" bundle="${ rb }"/>:</label>--%>
<%--                            <input type="password" name="password"--%>
<%--                                   class="form-control  ${incorrectLoginOrPassword != null ? "is-invalid" : ""}"--%>
<%--                                   id="inputPassword"--%>
<%--                                   placeholder="<fmt:message key="enterPassword" bundle="${ rb }" />" required>--%>
<%--                            <c:if test="${incorrectLoginOrPassword != null}">--%>
<%--                                <div class="invalid-feedback">${incorrectLoginOrPassword}</div>--%>
<%--                            </c:if>--%>
<%--                        </div>--%>
<%--                        <p class="message text-center"><fmt:message key="notRegistr" bundle="${ rb }"/>--%>
<%--                            <a href="${pageContext.request.contextPath}/registration.html"><fmt:message--%>
<%--                                    key="createAccount" bundle="${ rb }"/></a></p>--%>
<%--                        <button type="submit" class="btn btn-primary m-2"><fmt:message key="signIn"--%>
<%--                                                                                       bundle="${ rb }"/></button>--%>

<%--                    </form>--%>
<%--                </div>--%>
<%--            </form>--%>


<%--        </div>--%>


<%--        <div class="col-4"></div>--%>


<%--    </div>--%>
<%--</div>--%>


<div class="container-fluid pl-0">
    <div class="row no-gutter">
        <!-- The image half -->
        <div class="col-md-6 d-none d-md-flex bg-image">
            <img src="${pageContext.request.contextPath}/img/siginLogo.png" alt="Sign In">
        </div>


        <!-- The content half -->
        <div class="col-md-6 bg-light">
            <div class="login d-flex align-items-center py-5">

                <!-- Demo content-->
                <div class="container">
                    <div class="row">
                        <div class="col-lg-10 col-xl-7 mx-auto">
                            <h3 class="display-5">Добро пожаловать</h3>
                            <p class="text-muted mb-4">Введите логин и пароль что бы войти</p>
                            <form method="post" action="${pageContext.request.contextPath}/loginpost.html">
                                <div class="form-group mb-3">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <div class="col-1 mr-2 mb-0 pb-0">
                                                <i class="fas fa-user fa-2x"></i>
                                            </div>
                                            <div class="col-10">
                                                <input id="inputLogin" type="text" name="login"
                                                       placeholder="<fmt:message key="login" bundle="${ rb }"/>"
                                                       required="" autofocus=""
                                                       class="form-control rounded-pill border-0 shadow-sm px-4 ${incorrectLoginOrPassword != null ? "is-invalid" : ""}">
                                            </div>
                                        </div>
                                        <div class="row mt-3">
                                            <div class="col-1 mr-2">
                                                <i class="fas fa-key fa-2x"></i>
                                            </div>
                                            <div class="col-10">
                                                <input id="inputPassword" type="password" name="password"
                                                       placeholder="<fmt:message key="password" bundle="${ rb }"/>"
                                                       required=""
                                                       class="form-control rounded-pill border-0 shadow-sm px-4 text-primary ${incorrectLoginOrPassword != null ? "is-invalid" : ""}">
                                                <c:if test="${incorrectLoginOrPassword != null}">
                                                    <div class="invalid-feedback">${incorrectLoginOrPassword}</div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <button type="submit"
                                        class="btn btn-primary btn-block text-uppercase mb-2 rounded-pill shadow-sm">
                                    <em><fmt:message key="signIn"
                                                     bundle="${ rb }"/></em></button>
                                <em><p class="message text-center"><fmt:message key="notRegistr" bundle="${ rb }"/>
                                    <a href="${pageContext.request.contextPath}/registration.html"><fmt:message
                                            key="createAccount" bundle="${ rb }"/></a></p></em>
                            </form>
                        </div>

                    </div>
                </div>
            </div><!-- End -->

        </div>
    </div><!-- End -->

</div>
