<jsp:useBean id="show" scope="request" type="by.training.model.Serial"/>
<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 09.02.2020
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="customTag" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${cookie.language.value}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>

<div class="portfolio">
    <h1 class="text-center m-3" style="font-family: segoe print">${show.name}</h1>


    <div class="container-fluid">

        <div class="row">

            <%--            <div class="col-2 mb-3 p-0">--%>
            <%--                <jsp:useBean id="genres" scope="request" type="java.util.List"/>--%>
            <%--                <jsp:useBean id="country" scope="request" type="java.util.List"/>--%>
            <%--                <customTag:search-filter genres="${genres}" country="${country}"/>--%>
            <%--            </div>--%>

            <div class="col-10 mb-3">
                <div class="container-fluid">
                    <div class="row">

                        <div class="col-8 card p-0 border-light" style="width: 18rem  ;" >
                            <img class="card-img-top border " src="${show.fullLogo}" alt="Card image cap">
                            <div class="card-body">
                                <p class="card-text">${show.description}</p>
                            </div>


                        </div>

                        <div class="col-4 card p-0 border-light" >

                            <div class="card p-0 border-light" >
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">
                                        <fmt:message key="releaseDates" bundle="${ rb }"/>: <fmt:formatDate
                                            value="${show.releaseDate}"
                                            pattern="dd-MM-yyyy"/></li>
                                    <li class="list-group-item"><fmt:message key="country"
                                                                             bundle="${ rb }"/>: ${show.country.name}</li>
                                    <li class="list-group-item"><fmt:message key="studio"
                                                                             bundle="${ rb }"/>: ${show.studio.name}</li>
                                    <li class="list-group-item"><fmt:message key="genres" bundle="${ rb }"/>:<c:forEach
                                            items="${show.genres}"
                                            var="genre"> ${genre.name} </c:forEach></li>
                                    <c:if test="${sessionScope.user != null}">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/watch_serial.html?id=${show.id}"
                                               class="btn btn-primary btn-lg btn-block" role="button" aria-pressed="true">
                                                Смотреть</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>

                        </div>
                    </div>

                    <div>

                        <c:choose>
                            <c:when test="${sessionScope.user != null}">
                                <div>
                                    <form action="${pageContext.request.contextPath}/add_comment.html?id=${show.id}"
                                          method="post">
                                        <div class="form-group mt-3">
                                            <label for="exampleFormControlTextarea1"><fmt:message key="enterComment"
                                                                                                  bundle="${ rb }"/>:</label>
                                            <textarea name="comment" class="form-control"
                                                      id="exampleFormControlTextarea1"
                                                      rows="3"
                                                      placeholder="<fmt:message key="maxNumberCharacters" bundle="${ rb }" /> - 512"></textarea>
                                        </div>
                                        <button type="submit" class="btn btn-danger mb-2"><fmt:message key="send"
                                                                                                       bundle="${ rb }"/></button>
                                    </form>
                                </div>
                            </c:when>
                            <c:when test="${sessionScope.user == null}">
                                <div class="alert alert-danger mt-3" role="alert">
                                    <fmt:message key="commentRegistrUser" bundle="${ rb }"/> <a
                                        href="${pageContext.request.contextPath}/login.html"
                                        class="alert-link"><fmt:message key="signIn" bundle="${ rb }"/></a>.
                                </div>
                            </c:when>
                        </c:choose>


                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target=".bd-example-modal-xl">
                            Изменить
                        </button>


                        <!-- Modal -->
                        <div class="modal fade bd-example-modal-xl" tabindex="-1" role="dialog"
                             aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">

                            <div class="modal-dialog modal-dialog-scrollable modal-xl">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">

                                        <form method="post" enctype="multipart/form-data"
                                              action="${pageContext.request.contextPath}/change_serial.html?id=${show.id}">
                                            <div class="form-group mt-3">
                                                <label for="exampleFormControlInput1">Название сериала:</label>
                                                <input type="text" name="name" class="form-control"
                                                       id="exampleFormControlInput1"
                                                       placeholder="Название сериала" value="${show.name}">
                                            </div>

                                            <%--                                            <div class="form-group">--%>
                                            <%--                                                <label for="description">Описание сериала</label>--%>
                                            <%--                                                <textarea class="form-control" name="description" id="description"--%>
                                            <%--                                                          rows="3" placeholder="Описание сериала">${show.description}</textarea>--%>
                                            <%--                                            </div>--%>

                                            <div class="mb-3">
                                                <label for="validationTextarea">Textarea</label>
                                                <textarea class="form-control is-valid" id="validationTextarea"
                                                          placeholder="Описание сериала"
                                                          rows="5" name="description"
                                                          required>${show.description}</textarea>
                                                <div class="invalid-feedback">
                                                    Please enter a message in the textarea.
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label for="logo">Выберите фото превью</label>
                                                <input type="file" class="form-control-file" id="logo" name="logo">
                                            </div>

                                            <div class="form-group">
                                                <label for="full_logo">Выберите полное фото</label>
                                                <input type="file" class="form-control-file" id="full_logo"
                                                       name="full_logo">
                                            </div>

                                            <div class="form-group">
                                                <label for="date">Введите дату выхода сериала:</label>
                                                <input type="date" class="form-control" name="release_date" id="date"
                                                       value="${show.releaseDate}">
                                            </div>

                                            <div class="mb-2">
                                                <label>Выберите жанры:</label>
                                                <jsp:useBean id="genres" scope="request" type="java.util.List"/>
                                                <c:forEach var="g" items="${genres}">
                                                    <div class="custom-control custom-checkbox">
                                                        <input class="form-check-input" type="checkbox"
                                                               id="inlineCheckbox2" value="${g.id}"
                                                               name="genre"  ${show.genres.contains(g) ? 'checked' : ''}>
                                                        <label class="form-check-label"
                                                               for="inlineCheckbox2">${g.name}</label>
                                                    </div>
                                                </c:forEach>
                                            </div>

                                            <div class="mb-2">
                                                <label>Выберите страну:</label>
                                                <br>
                                                <select class="custom-select" name="country">
                                                    <jsp:useBean id="country" scope="request" type="java.util.List"/>
                                                    <c:forEach var="c" items="${country}">
                                                        <option value="${c.id}" ${show.country.equals(c) ? 'selected' : ''}>${c.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="mb-2">
                                                <label>Выберите студию:</label>
                                                <br>
                                                <select class="custom-select" name="studio">
                                                    <jsp:useBean id="studio" scope="request" type="java.util.List"/>
                                                    <c:forEach var="s" items="${studio}">
                                                        <option value="${s.id}" ${show.studio.equals(s) ? 'selected' : ''}>${s.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                                    Close
                                                </button>
                                                <button type="submit" class="btn btn-primary">Save changes</button>
                                            </div>

                                        </form>

                                    </div>

                                </div>
                            </div>
                        </div>

                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#delete">
                            Удалить
                        </button>

                        <!-- Modal -->
                        <div class="modal fade" id="delete" tabindex="-1"
                             role="dialog" aria-labelledby="exampleModalLongTitle"
                             aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Modal
                                            title</h5>
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form method="post"
                                              action="${pageContext.request.contextPath}/delete_serial.html?id=${show.id}">
                                            <p>Вы уверены, что хотите удалить сериал - ${show.name}? </p>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary"
                                                        data-dismiss="modal">Отмена
                                                </button>
                                                <button type="submit" class="btn btn-primary">
                                                    Удалить
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>


                <c:forEach items="${show.comments}" var="comment">
                    <div class="card text-center mt-3">
                        <div class="card-header text-left">
                                ${comment.user.login}
                        </div>
                        <div class="card-body">
                            <p class="card-text text-left">${comment.comment}</p>
                        </div>
                        <div class="card-footer text-muted">
                                <%--                                <fmt:parseDate value="${comment.publicationDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="time" type="date"/>--%>
                                <%--                                <fmt:formatDate pattern="dd-MMM-yyyy HH:mm" value="${time}"/>--%>
                                ${comment.publicationDate}
                        </div>
                    </div>
                </c:forEach>

            </div>

            <div class="card col-2 mb-3 p-0">
                <jsp:useBean id="last" scope="request" type="java.util.List"/>
                <customTag:last-serials last="${last}"/>
            </div>

        </div>  <!-- row -->

    </div>

</div>