<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-4"></div>

        <div class="col-4">

            <form method="post" action="${pageContext.request.contextPath}/loginpost.html">
                <div class="card mt-5 mb-5" style="background-color: #9999CC">
                    <form class="ml-3 mr-3">
                        <div class="form-group m-2">
                            <label for="inputEmail"><fmt:message key="login" bundle="${ rb }"/>:</label>
                            <input type="text" name="login" class="form-control is-valid" id="inputEmail"
                                   placeholder="<fmt:message key="enterLogin" bundle="${ rb }" />" required>
                            <div class="valid-feedback">Good! Your login looks valid.</div>
                        </div>
                        <div class="form-group m-2">
                            <label for="inputPassword"><fmt:message key="password" bundle="${ rb }"/>:</label>
                            <input type="password" name="password" class="form-control is-invalid" id="inputPassword"
                                   placeholder="<fmt:message key="enterPassword" bundle="${ rb }" />" required>
                            <div class="invalid-feedback">Opps! You have entered an invalid password.</div>
                        </div>
                        <p class="message text-center"><fmt:message key="notRegistr" bundle="${ rb }"/>
                            <a href="${pageContext.request.contextPath}/registration.html"><fmt:message
                                    key="createAccount" bundle="${ rb }"/></a></p>
                        <button type="submit" class="btn btn-primary m-2"><fmt:message key="signIn"
                                                                                       bundle="${ rb }"/></button>

                    </form>
                </div>
            </form>


        </div>


        <div class="col-4"></div>


    </div>
</div>
