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
                   class="list-group-item list-group-item-action active">
                    <fmt:message key="editStudio" bundle="${ rb }"/></a>
                <a href="${pageContext.request.contextPath}/admin/user.html"
                   class="list-group-item list-group-item-action">
                    <fmt:message key="editUser" bundle="${ rb }"/></a>
            </div>
        </div>
        <div class="col-9">

            <div class="card-body">
                <form method="post" action="${pageContext.request.contextPath}/save_studio.html" class="was-validated">
                    <div class="form-group">
                        <div class="mb-2">
                            <label for="addStudio"><fmt:message key="enterStudioName" bundle="${ rb }"/>:</label>
                            <input type="text" name="studio" class="form-control" id="addStudio"
                                   placeholder="<fmt:message key="enterStudioName" bundle="${ rb }"/>" required>
                            <c:if test="${not empty studioProblem}">
                                <div class="invalid-feedback text-style">
                                    <fmt:message key="${studioProblem}" bundle="${ rb }"/>
                                </div>
                            </c:if>
                        </div>
                        <button type="submit" class="btn btn-primary"><fmt:message key="add" bundle="${ rb }"/></button>
                    </div>
                </form>
            </div>

            <div class="card-body">

                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">id</th>
                        <th scope="col"><fmt:message key="title" bundle="${ rb }"/></th>
                        <th scope="col"><fmt:message key="editing" bundle="${ rb }"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <jsp:useBean id="studio" scope="request" type="java.util.List"/>
                    <c:forEach var="s" items="${studio}">
                        <tr>
                            <th scope="row">${s.id}</th>
                            <td>${s.name}</td>
                            <td>
                                <!-- Button trigger modal -->
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#exampleModalLong${s.id}">
                                    <fmt:message key="edit" bundle="${ rb }"/>
                                </button>

                                <!-- Modal -->
                                <div class="modal fade" id="exampleModalLong${s.id}" tabindex="-1"
                                     role="dialog" aria-labelledby="exampleModalLongTitle"
                                     aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLongTitle"><fmt:message
                                                        key="editing" bundle="${ rb }"/></h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <form method="post"
                                                      action="${pageContext.request.contextPath}/save_studio.html?id=${s.id}">
                                                    <div class="form-group mt-3">
                                                        <div class="mb-2">
                                                            <label for="changeStudio"><fmt:message key="newStudioName"
                                                                                                   bundle="${ rb }"/>
                                                                - ${s.name} </label>
                                                            <input type="text" name="studio"
                                                                   class="form-control" id="changeStudio"
                                                                   placeholder="<fmt:message key="newStudioName" bundle="${ rb }"/>"
                                                                   value="${s.name}">
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                                data-dismiss="modal"><fmt:message key="cancel"
                                                                                                  bundle="${ rb }"/>
                                                        </button>
                                                        <button type="submit" class="btn btn-primary">
                                                            <fmt:message key="confrim" bundle="${ rb }"/>
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Button trigger modal -->
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#delete${s.id}">
                                    <fmt:message key="delete" bundle="${ rb }"/>
                                </button>

                                <!-- Modal -->
                                <div class="modal fade" id="delete${s.id}" tabindex="-1"
                                     role="dialog" aria-labelledby="exampleModalLongTitle"
                                     aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title"><fmt:message key="delete"
                                                                                     bundle="${ rb }"/></h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <form method="post"
                                                      action="${pageContext.request.contextPath}/delete_studio.html?id=${s.id}">
                                                    <p><fmt:message key="confirmDeleteStudio" bundle="${ rb }"/>
                                                        - ${s.name}? </p>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                                data-dismiss="modal"><fmt:message key="cancel"
                                                                                                  bundle="${ rb }"/>
                                                        </button>
                                                        <button type="submit" class="btn btn-primary">
                                                            <fmt:message key="delete" bundle="${ rb }"/>
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>

<div id="content">
    <div id="pagination-5" class="pagination pagination-lg justify-content-center "></div>
</div>

<script>

    // function pageClick5(pageNumber) {
    //     $("#page-number-5").text(pageNumber);
    // }

    $(document).ready(function () {
        var itemsCount = ${countAllStudio};
        var itemsOnPage = ${itemsOnPage};

        function getParameterByName(name, url) {
            if (!url) url = window.location.href;
            name = name.replace(/[\[\]]/g, "\\$&");
            var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
                results = regex.exec(url);
            if (!results) return null;
            if (!results[2]) return '';
            return decodeURIComponent(results[2].replace(/\+/g, " "));
        }

        var pagination5 = new Pagination({
            container: $("#pagination-5"),
            pageClickUrl: "?page={{page}}",
            //pageClickUrl: function(num) { return "?page=" + num; },
            // pageClickCallback: pageClick5,
            callPageClickCallbackOnInit: true,
            // showInput: true,
            // showSlider: true,
            // enhancedMode: true,
            maxVisibleElements: 20,
            inputTitle: "Go to page"
        });
        pagination5.make(itemsCount, itemsOnPage, getParameterByName("page"));
    });
</script>
