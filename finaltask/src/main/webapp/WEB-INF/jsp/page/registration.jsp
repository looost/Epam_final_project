<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>

<%--<div class="container-fluid">--%>
<%--    <div class="row">--%>
<%--        <div class="col-4"></div>--%>

<%--        <div class="col-4">--%>

<%--            <form method="post" action="${pageContext.request.contextPath}/registr.html">--%>
<%--                <div class="card mt-5 mb-5" style="background-color: #9999CC">--%>
<%--                    <form class="ml-3 mr-3">--%>
<%--                        <div class="form-group m-2">--%>
<%--                            <label for="inputEmail"><fmt:message key="login" bundle="${ rb }"/>:</label>--%>
<%--                            <input type="text" name="login"--%>
<%--                                   class="form-control ${incorrect != null ? 'is-invalid' : ''}" id="inputEmail"--%>
<%--                                   placeholder="<fmt:message key="enterLogin" bundle="${ rb }" />" required>--%>
<%--                            <c:if test="${not empty incorrect}">--%>
<%--                                <div class="invalid-feedback">${incorrect}</div>--%>
<%--                            </c:if>--%>
<%--                        </div>--%>
<%--                        <div class="form-group m-2">--%>
<%--                            <label for="inputPassword"><fmt:message key="password" bundle="${ rb }"/>:</label>--%>
<%--                            <input type="password" name="password" class="form-control " id="inputPassword"--%>
<%--                                   placeholder="<fmt:message key="enterPassword" bundle="${ rb }" />" required>--%>
<%--                        </div>--%>

<%--                        <div class="form-group m-2">--%>
<%--                            <label for="replayInputPassword"><fmt:message key="confirmPassword"--%>
<%--                                                                          bundle="${ rb }"/>:</label>--%>
<%--                            <input type="password" name="replayPassword" class="form-control " id="replayInputPassword"--%>
<%--                                   placeholder="<fmt:message key="confirmPassword" bundle="${ rb }" />" required>--%>
<%--                        </div>--%>

<%--                        <button type="submit" class="btn btn-primary m-2" onclick="return validateForm()">--%>
<%--                            <fmt:message key="registr" bundle="${ rb }"/>--%>
<%--                        </button>--%>

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
            <img src="<c:url value="/img/siginLogo.png"/>" alt="Sign In">
        </div>
        <!-- The content half -->
        <div class="col-md-6 bg-light">
            <div class="login d-flex align-items-center py-5">

                <!-- Demo content-->
                <div class="container">
                    <div class="row">
                        <div class="col-lg-10 col-xl-7 mx-auto">
                            <h3 class="display-5 text-style pb-2"><fmt:message key="justWelcome" bundle="${ rb }"/></h3>
                            <form method="post" action="${pageContext.request.contextPath}/registration.html">
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
                                                       class="text-style form-control rounded-pill border-0 shadow-sm px-4 ${incorrectLogin != null ? 'is-invalid' : ''}">
                                                <c:if test="${not empty incorrectLogin}">
                                                    <div class="invalid-feedback text-style">${incorrectLogin}</div>
                                                </c:if>
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
                                                       class="text-style form-control rounded-pill border-0 shadow-sm px-4 text-primary ${incorrectLoginOrPassword != null ? "is-invalid" : ""}">
                                            </div>
                                        </div>
                                        <div class="row mt-3">
                                            <div class="col-1 mr-2">
                                                <i class="fas fa-key fa-2x"></i>
                                            </div>
                                            <div class="col-10">
                                                <input id="password-check" type="password" name="replayPassword"
                                                       placeholder="<fmt:message key="confirmPassword" bundle="${ rb }" />"
                                                       required=""
                                                       class="text-style form-control rounded-pill border-0 shadow-sm px-4 text-primary ${incorrectLoginOrPassword != null ? "is-invalid" : ""}">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <button type="submit"
                                        class="btn btn-primary btn-block text-uppercase mb-2 rounded-pill shadow-sm"
                                <%--                                        onclick="return validateForm()"--%>
                                >
                                    <em><fmt:message key="registr" bundle="${ rb }"/></em></button>
                                <em><p class="message text-center text-style"><fmt:message key="alredyRegistr"
                                                                                           bundle="${ rb }"/>?
                                    <a href="${pageContext.request.contextPath}/login.html"
                                       class="text-style"><fmt:message key="signIn" bundle="${ rb }"/></a></p></em>
                            </form>
                        </div>

                    </div>
                </div>
            </div><!-- End -->

        </div>


    </div><!-- End -->
</div>

<%--<script type="text/javascript">--%>
<%--    function validateForm() {--%>
<%--        var password1 = document.getElementById('inputPassword');--%>
<%--        var password2 = document.getElementById('password-check');--%>
<%--        if (password1.value !== password2.value) {--%>
<%--            alert('<fmt:message key="passwordDoNotMatch" bundle="${ rb }" />');--%>
<%--            return false;--%>
<%--        }--%>
<%--    }--%>
<%--</script>--%>

<style>
    input[type=password]:invalid {
        border: solid 2px red;
    }
</style>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        var pass1 = document.querySelector('#inputPassword'),
            pass2 = document.querySelector('#password-check')
        pass1.addEventListener('input', function () {
            this.value != pass2.value ? pass2.setCustomValidity('<fmt:message key="passwordDoNotMatch" bundle="${ rb }" />') : pass2.setCustomValidity('')
        })
        pass2.addEventListener('input', function (e) {
            this.value != pass1.value ? this.setCustomValidity('<fmt:message key="passwordDoNotMatch" bundle="${ rb }" />') : this.setCustomValidity('')
        })
    })
</script>
