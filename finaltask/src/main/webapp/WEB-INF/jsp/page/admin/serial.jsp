<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>
<div class="container-fluid">

    <div class="row">
        <div class="col-2 m-2">
            <div class="list-group">
                <a href="${pageContext.request.contextPath}/admin/serial.html"
                   class="list-group-item list-group-item-action active">
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
                   class="list-group-item list-group-item-action">
                    <fmt:message key="editUser" bundle="${ rb }"/></a>
            </div>
        </div>

        <div class="col-9">
            <div class="tab-content" id="pills-tabContent">
                <div class="tab-pane fade show active" id="pills-home" role="tabpanel"
                     aria-labelledby="pills-serial-tab">

                    <form class="was-validated mt-2" method="post" enctype="multipart/form-data"
                          action="${pageContext.request.contextPath}/save_serial.html">

                        <div class="mb-3">
                            <label for="validationName"><fmt:message key="enterSerialName" bundle="${ rb }"/>:</label>
                            <input type="text" name="name" class="form-control is-invalid"
                                   id="validationName"
                                   placeholder="<fmt:message key="enterSerialName" bundle="${ rb }"/>">
                            <c:if test="${nameProblem != null}">
                                <div class="invalid-feedback">
                                        ${nameProblem}
                                </div>
                            </c:if>
                        </div>

                        <div class="mb-3">
                            <label for="validationDescription"><fmt:message key="enterSerialDescription"
                                                                            bundle="${ rb }"/>:</label>
                            <textarea class="form-control is-invalid" id="validationDescription"
                                      placeholder="<fmt:message key="enterSerialDescription" bundle="${ rb }"/>"
                                      name="description" rows="5"></textarea>
                            <c:if test="${descriptionProblem != null}">
                                <div class="invalid-feedback">
                                        ${descriptionProblem}
                                </div>
                            </c:if>
                        </div>

                        <div class="custom-file mb-3">
                            <input type="file" class="custom-file-input" id="validatedLogo" name="logo">
                            <label class="custom-file-label" for="validatedLogo"><fmt:message key="loadSerialLogo"
                                                                                              bundle="${ rb }"/></label>
                        </div>

                        <div class="custom-file mb-3">
                            <input type="file" class="custom-file-input" id="validatedFullLogo" name="full_logo">
                            <label class="custom-file-label" for="validatedFullLogo"><fmt:message
                                    key="loadSerialFullLogo" bundle="${ rb }"/></label>
                        </div>

                        <div class="form-group">
                            <label for="date"><fmt:message key="enterSerialReleaseDate" bundle="${ rb }"/>:</label>
                            <input type="date" class="form-control" name="release_date" id="date" required>
                            <c:if test="${releaseDateProblem != null}">
                                <div class="invalid-feedback">
                                        ${releaseDateProblem}
                                </div>
                            </c:if>
                        </div>

                        <div class="mb-2">
                            <label><fmt:message key="chooseGenre" bundle="${ rb }"/>:</label>
                            <jsp:useBean id="genres" scope="request" type="java.util.List"/>
                            <c:forEach var="g" items="${genres}">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="customCheck${g.id}"
                                           value="${g.id}"
                                           name="genre">
                                    <label class="custom-control-label" for="customCheck${g.id}">${g.name}</label>
                                </div>
                            </c:forEach>
                        </div>

                        <div class="mb-2">
                            <label><fmt:message key="chooseCountry" bundle="${ rb }"/>:</label>
                            <br>
                            <select class="custom-select" name="country">
                                <jsp:useBean id="country" scope="request" type="java.util.List"/>
                                <c:forEach var="c" items="${country}">
                                    <option value="${c.id}">${c.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="mb-2">
                            <label><fmt:message key="chooseStudio" bundle="${ rb }"/>:</label>
                            <select class="custom-select" name="studio">
                                <jsp:useBean id="studio" scope="request" type="java.util.List"/>
                                <c:forEach var="s" items="${studio}">
                                    <option value="${s.id}">${s.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary"><fmt:message key="add" bundle="${ rb }"/></button>
                    </form>


                </div>
            </div>
        </div>
    </div>
</div>
