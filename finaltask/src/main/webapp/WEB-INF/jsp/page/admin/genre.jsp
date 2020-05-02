<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>

<div class="position-absolute w-100 d-flex flex-column p-4">

    <div aria-live="polite" aria-atomic="true" style="position: relative; min-height: 200px;">
        <div class="toast" style="position: fixed; bottom: 5%; right: 2%; z-index: 1000;" data-autohide="false">
            <div class="toast-header">
                <strong class="mr-auto">Bootstrap</strong>
                <small>11 mins ago</small>
                <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="toast-body">
                Данные успешно изменены!
            </div>
        </div>
    </div>

</div>


<div class="container-fluid text-style">

    <div class="row">
        <div class="col-2 m-2">
            <div class="list-group">
                <a href="${pageContext.request.contextPath}/admin/serial.html"
                   class="list-group-item list-group-item-action">
                    <fmt:message key="editSerial" bundle="${ rb }"/></a>
                <a href="${pageContext.request.contextPath}/admin/genre.html"
                   class="list-group-item list-group-item-action active">
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

            <div class="card-body">
                <form class="was-validated mt-2" method="post"
                      action="${pageContext.request.contextPath}/save_genre.html">
                    <c:if test="${not empty genreProblem}">
                        <div class="alert alert-danger text-style" role="alert">
                            <p><strong><fmt:message key="incorrectData" bundle="${ rb }"/>:</strong></p>
                            <ul>
                                <li><fmt:message key="incorrectName" bundle="${ rb }"/></li>
                                <li><fmt:message key="incorrectField" bundle="${ rb }"/></li>
                                <li><fmt:message key="incorrectNameLength" bundle="${ rb }"/></li>
                            </ul>
                        </div>
                    </c:if>
                    <div class="mb-3">
                        <label for="validationName"><fmt:message key="enterGenreName" bundle="${ rb }"/>:</label>
                        <input type="text" name="genre" class="form-control is-invalid"
                               id="validationName" placeholder="<fmt:message key="enterGenreName" bundle="${ rb }"/>"
                               required>
                    </div>
                    <button type="submit" class="btn btn-primary"><fmt:message key="add" bundle="${ rb }"/></button>
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
                    <jsp:useBean id="genres" scope="request" type="java.util.List"/>
                    <c:forEach var="g" items="${genres}">
                        <tr>
                            <th scope="row">${g.id}</th>
                            <td>${g.name}</td>
                            <td>
                                <!-- Button trigger modal -->
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#exampleModalLong${g.id}">
                                    <fmt:message key="edit" bundle="${ rb }"/>
                                </button>

                                <!-- Modal -->
                                <div class="modal fade" id="exampleModalLong${g.id}" tabindex="-1"
                                     role="dialog" aria-labelledby="exampleModalLongTitle"
                                     aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLongTitle">
                                                    <fmt:message key="edit" bundle="${ rb }"/>
                                                </h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <form method="post"
                                                      action="${pageContext.request.contextPath}/save_genre.html?id=${g.id}">
                                                    <div class="form-group mt-3">
                                                        <div class="mb-2">
                                                            <label for="changeGenre"><fmt:message key="newGenreName"
                                                                                                  bundle="${ rb }"/>
                                                                - ${g.name} </label>
                                                            <input type="text" name="genre"
                                                                   class="form-control" id="changeGenre"
                                                                   placeholder="<fmt:message key="enterGenreName" bundle="${ rb }"/>"
                                                                   value="${g.name}">
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
                                        data-target="#delete${g.id}">
                                    <fmt:message key="delete" bundle="${ rb }"/>
                                </button>

                                <!-- Modal -->
                                <div class="modal fade" id="delete${g.id}" tabindex="-1"
                                     role="dialog" aria-labelledby="exampleModalLongTitle"
                                     aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">
                                                    <fmt:message key="delete" bundle="${ rb }"/>
                                                </h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <form method="post"
                                                      action="${pageContext.request.contextPath}/delete_genre.html?id=${g.id}">
                                                    <p><fmt:message key="confirmDeleteGenre" bundle="${ rb }"/>
                                                        - ${g.name}? </p>
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
    (function () {
        if (${not empty ok}) {
            $('.toast').toast('show');
        }
    })();

</script>

<script>

    // function pageClick5(pageNumber) {
    //     $("#page-number-5").text(pageNumber);
    // }

    $(document).ready(function () {
        var itemsCount = ${countAllGenre};
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
