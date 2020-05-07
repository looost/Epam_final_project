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
    <h1 class="text-center m-3 text-style">${show.name}</h1>


    <div class="container-fluid">
        <div class="row">

            <div class="col-10 mb-3">

                <div class="container-fluid">
                    <div class="row">

                        <div class="card col-8 p-0">
                            <div class="p-0 border-light">
                                <img class="card-img-top border " src="<c:url value="/img/${show.fullLogo}"/>"
                                     alt="Card image cap">
                                <div class="card-body">
                                    <p class="card-text text-style text-justify">${show.description}</p>
                                    <c:if test="${sessionScope.user != null}">
                                        <div class="container">
                                            <div class="row">
                                                <c:choose>
                                                    <c:when test="${likedStatus == false}">
                                                        <div class="col-6">
                                                            <div class="text-center">
                                                                <a href="${pageContext.request.contextPath}/like.html?id=${show.id}"
                                                                   class="btn btn-outline-danger btn-lg btn-iconed btn-rounded "
                                                                   role="button"
                                                                   aria-pressed="true">
                                                                    <i class="fa fa-heart"></i> <span
                                                                        class="spn"><fmt:message key="like"
                                                                                                 bundle="${ rb }"/></span>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${likedStatus == true}">
                                                        <div class="col-6">
                                                            <div class="text-center">
                                                                <a href="${pageContext.request.contextPath}/dislike.html?id=${show.id}"
                                                                   class="btn btn-outline-danger btn-lg btn-iconed btn-rounded "
                                                                   role="button"
                                                                   aria-pressed="true">
                                                                    <i class="fas fa-heart-broken"></i> <span
                                                                        class="spn"><fmt:message key="dislike"
                                                                                                 bundle="${ rb }"/></span>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                </c:choose>


                                                <c:choose>
                                                    <c:when test="${watchStatus == false}">
                                                        <div class="col-6">
                                                            <div class="text-center">
                                                                <a href="${pageContext.request.contextPath}/watch_serial.html?id=${show.id}"
                                                                   class="btn btn-outline-info btn-lg btn-iconed btn-rounded"
                                                                   role="button"
                                                                   aria-pressed="true">
                                                                    <i class="far fa-eye"></i> <span
                                                                        class="spn"><fmt:message key="look"
                                                                                                 bundle="${ rb }"/></span>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </c:when>

                                                    <c:when test="${watchStatus == true}">
                                                        <div class="col-6">
                                                            <div class="text-center">
                                                                <a href="${pageContext.request.contextPath}/stop_watch_serial.html?id=${show.id}"
                                                                   class="btn btn-outline-info btn-lg btn-iconed btn-rounded"
                                                                   role="button"
                                                                   aria-pressed="true">
                                                                    <i class="fas fa-ban"></i> <span
                                                                        class="spn"><fmt:message key="dontWatch"
                                                                                                 bundle="${ rb }"/></span>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                </c:choose>


                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>


                        <div class="col-4 card p-0 border-light">
                            <div class="p-0 border-light">
                                <ul class="list-group list-group-flush">
                                    <div class="container">
                                        <li class="list-group-item">

                                            <div class="row">
                                                <div class="col-2">
                                                    <i class="far fa-calendar-alt fa-2x"></i>
                                                </div>
                                                <div class="col-10 text-style">
                                                    <fmt:message key="releaseDates" bundle="${ rb }"/>: <fmt:formatDate
                                                        value="${show.releaseDate}"
                                                        pattern="dd-MM-yyyy"/>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="row">
                                                <div class="col-2">
                                                    <i class="fas fa-globe-americas fa-2x">
                                                    </i>
                                                </div>
                                                <div class="col-10 text-style">
                                                    <fmt:message key="country"
                                                                 bundle="${ rb }"/>: ${show.country.name}
                                                </div>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="row">
                                                <div class="col-2">
                                                    <i class="fas fa-video fa-2x"></i>
                                                    </i>
                                                </div>
                                                <div class="col-10 text-style">
                                                    <fmt:message key="studio"
                                                                 bundle="${ rb }"/>: ${show.studio.name}
                                                </div>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="row">
                                                <div class="col-2 text-style">
                                                    <i class="fas fa-layer-group fa-2x"></i>
                                                    </i>
                                                </div>
                                                <div class="col-10 text-style">
                                                    <fmt:message key="genres" bundle="${ rb }"/>:<c:forEach
                                                        items="${show.genres}"
                                                        var="genre"> ${genre.name} </c:forEach>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="row">
                                                <div class="col-2 text-style">
                                                    <i class="fa fa-heart fa-2x"></i>
                                                </div>
                                                <div class="col-10 text-style">
                                                    <fmt:message key="numberIfLikes" bundle="${ rb }"/>
                                                    - ${show.countLike}
                                                </div>
                                            </div>
                                        </li>
                                    </div>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="container">
                    <div class="row">
                        <div class="col-10">

                            <div class="mt-3">
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
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">

                                                <form method="post" enctype="multipart/form-data"
                                                      action="${pageContext.request.contextPath}/save_serial.html?id=${show.id}">
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
                                                        <input type="file" class="form-control-file" id="logo"
                                                               name="logo">
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="full_logo">Выберите полное фото</label>
                                                        <input type="file" class="form-control-file" id="full_logo"
                                                               name="full_logo">
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="date">Введите дату выхода сериала:</label>
                                                        <input type="date" class="form-control" name="release_date"
                                                               id="date"
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
                                                            <jsp:useBean id="country" scope="request"
                                                                         type="java.util.List"/>
                                                            <c:forEach var="c" items="${country}">
                                                                <option value="${c.id}" ${show.country.equals(c) ? 'selected' : ''}>${c.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>

                                                    <div class="mb-2">
                                                        <label>Выберите студию:</label>
                                                        <br>
                                                        <select class="custom-select" name="studio">
                                                            <jsp:useBean id="studio" scope="request"
                                                                         type="java.util.List"/>
                                                            <c:forEach var="s" items="${studio}">
                                                                <option value="${s.id}" ${show.studio.equals(s) ? 'selected' : ''}>${s.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>

                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                                data-dismiss="modal">
                                                            Close
                                                        </button>
                                                        <button type="submit" class="btn btn-primary">Save changes
                                                        </button>
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


                            <c:choose>
                                <c:when test="${sessionScope.user != null}">
                                    <div class="mt-3 text-style">
                                        <form class="was-validated"
                                              action="${pageContext.request.contextPath}/save_comment.html?id=${show.id}"
                                              method="post">
                                            <div class="form-group mt-3">
                                                <label for="exampleFormControlTextarea1"><fmt:message key="enterComment"
                                                                                                      bundle="${ rb }"/>:</label>
                                                <textarea name="comment" class="form-control is-invalid"
                                                          id="exampleFormControlTextarea1"
                                                          rows="5"
                                                          placeholder="<fmt:message key="maxNumberCharacters" bundle="${ rb }" /> - 512"
                                                          required></textarea>
                                                <c:if test="${not empty commentProblem}">
                                                    <div class="invalid-feedback">
                                                        <fmt:message key="${commentProblem}" bundle="${ rb }"/>
                                                    </div>
                                                </c:if>
                                            </div>
                                            <button type="submit" class="btn btn-danger mb-2"><fmt:message key="send"
                                                                                                           bundle="${ rb }"/></button>
                                        </form>
                                    </div>
                                </c:when>
                                <c:when test="${sessionScope.user == null}">
                                    <%--                                    <div class="alert alert-danger mt-3" role="alert">--%>
                                    <%--                                        <fmt:message key="commentRegistrUser" bundle="${ rb }"/> <a--%>
                                    <%--                                            href="${pageContext.request.contextPath}/login.html"--%>
                                    <%--                                            class="alert-link"><fmt:message key="signIn" bundle="${ rb }"/></a>.--%>
                                    <%--                                    </div>--%>
                                    <div class="alert alert-arrow alert-arrow-warning d-flex rounded p-0 mt-3"
                                         role="alert">
                                        <div class="alert-icon d-flex justify-content-center align-items-center text-white flex-grow-0 flex-shrink-0">
                                            <i class="fas fa-exclamation-triangle"></i>
                                        </div>
                                        <div class="alert-message d-flex align-items-center py-2 pl-4 pr-3 text-style">
                                            <fmt:message key="commentRegistrUser" bundle="${ rb }"/> <a
                                                class="text-style"
                                                href="${pageContext.request.contextPath}/login.html"
                                                class="alert-link"> <fmt:message key="signIn" bundle="${ rb }"/></a>
                                        </div>
                                    </div>
                                </c:when>
                            </c:choose>

                        </div>
                    </div>
                </div>

                <%--                <div class="container">--%>
                <%--                    <c:forEach items="${show.comments}" var="comment">--%>
                <%--                        <div class="row">--%>
                <%--                            <div class="col-12">--%>
                <%--                                <div class="card text-center mt-3">--%>
                <%--                                    <div class="card-header text-left">--%>
                <%--                                            ${comment.user.login}--%>
                <%--                                    </div>--%>
                <%--                                    <div class="card-body pl-0">--%>
                <%--                                        <div class="container">--%>
                <%--                                            <div class="row">--%>
                <%--                                                <div class="col-1">--%>
                <%--                                                    <c:choose>--%>
                <%--                                                        <c:when test="${sessionScope.user.equals(comment.user.login)}">--%>
                <%--                                                            <i class="fas fa-comment fa-2x"></i>--%>
                <%--                                                        </c:when>--%>
                <%--                                                        <c:when test="${!sessionScope.user.equals(comment.user.login)}">--%>
                <%--                                                            <i class="far fa-comment fa-2x"></i>--%>
                <%--                                                        </c:when>--%>
                <%--                                                    </c:choose>--%>
                <%--                                                </div>--%>
                <%--                                                <div class="col-10">--%>
                <%--                                                    <p class="card-text text-left">--%>
                <%--                                                            ${comment.comment}--%>
                <%--                                                    </p>--%>
                <%--                                                </div>--%>
                <%--                                                <div class="col-1">--%>
                <%--                                                    <c:if test="${sessionScope.user.equals(comment.user.login)}">--%>

                <%--                                                        <a href="" data-toggle="modal"--%>
                <%--                                                           data-target="#change${comment.id}"> <i--%>
                <%--                                                                class="far fa-edit"></i></a>--%>

                <%--                                                        <!-- Modal -->--%>
                <%--                                                        <div class="modal fade" id="change${comment.id}" tabindex="-1"--%>
                <%--                                                             role="dialog" aria-labelledby="exampleModalLongTitle"--%>
                <%--                                                             aria-hidden="true">--%>
                <%--                                                            <div class="modal-dialog" role="document">--%>
                <%--                                                                <div class="modal-content">--%>
                <%--                                                                    <div class="modal-header">--%>
                <%--                                                                        <h5 class="modal-title"><fmt:message key="edit"--%>
                <%--                                                                                                             bundle="${ rb }"/></h5>--%>
                <%--                                                                        <button type="button" class="close"--%>
                <%--                                                                                data-dismiss="modal"--%>
                <%--                                                                                aria-label="Close">--%>
                <%--                                                                            <span aria-hidden="true">&times;</span>--%>
                <%--                                                                        </button>--%>
                <%--                                                                    </div>--%>
                <%--                                                                    <div class="modal-body">--%>
                <%--                                                                        <form method="post"--%>
                <%--                                                                              action="${pageContext.request.contextPath}/save_comment.html?commentId=${comment.id}&id=${show.id}">--%>
                <%--                                                                            <div class="form-group mt-3">--%>

                <%--                                                                                <textarea class="form-control"--%>
                <%--                                                                                          rows="5" name="comment"--%>
                <%--                                                                                          required>${comment.comment}</textarea>--%>
                <%--                                                                            </div>--%>
                <%--                                                                            <div class="modal-footer">--%>
                <%--                                                                                <button type="button"--%>
                <%--                                                                                        class="btn btn-secondary"--%>
                <%--                                                                                        data-dismiss="modal">--%>
                <%--                                                                                    <fmt:message key="cancel"--%>
                <%--                                                                                                 bundle="${ rb }"/>--%>
                <%--                                                                                </button>--%>
                <%--                                                                                <button type="submit"--%>
                <%--                                                                                        class="btn btn-primary">--%>
                <%--                                                                                    <fmt:message key="confrim"--%>
                <%--                                                                                                 bundle="${ rb }"/></button>--%>
                <%--                                                                            </div>--%>
                <%--                                                                        </form>--%>
                <%--                                                                    </div>--%>
                <%--                                                                </div>--%>
                <%--                                                            </div>--%>
                <%--                                                        </div>--%>
                <%--                                                    </c:if>--%>
                <%--                                                    <c:if test="${sessionScope.user.equals(comment.user.login) || sessionScope.role == 1}">--%>
                <%--                                                        <a href="" data-toggle="modal"--%>
                <%--                                                           data-target="#delete${comment.id}"> <i--%>
                <%--                                                                class="fas fa-trash-alt"></i></a>--%>

                <%--                                                        <!-- Modal -->--%>
                <%--                                                        <div class="modal fade" id="delete${comment.id}" tabindex="-1"--%>
                <%--                                                             role="dialog" aria-labelledby="exampleModalLongTitle"--%>
                <%--                                                             aria-hidden="true">--%>
                <%--                                                            <div class="modal-dialog" role="document">--%>
                <%--                                                                <div class="modal-content">--%>
                <%--                                                                    <div class="modal-header">--%>
                <%--                                                                        <h5 class="modal-title"><fmt:message--%>
                <%--                                                                                key="delete" bundle="${ rb }"/></h5>--%>
                <%--                                                                        <button type="button" class="close"--%>
                <%--                                                                                data-dismiss="modal"--%>
                <%--                                                                                aria-label="Close">--%>
                <%--                                                                            <span aria-hidden="true">&times;</span>--%>
                <%--                                                                        </button>--%>
                <%--                                                                    </div>--%>
                <%--                                                                    <div class="modal-body">--%>
                <%--                                                                        <form method="post"--%>
                <%--                                                                              action="${pageContext.request.contextPath}/delete_comment.html?id=${comment.id}">--%>
                <%--                                                                            <p>Вы уверены, что хотите удалить--%>
                <%--                                                                                комментарий? </p>--%>
                <%--                                                                            <div class="modal-footer">--%>
                <%--                                                                                <button type="button"--%>
                <%--                                                                                        class="btn btn-secondary"--%>
                <%--                                                                                        data-dismiss="modal">--%>
                <%--                                                                                    <fmt:message key="cancel"--%>
                <%--                                                                                                 bundle="${ rb }"/>--%>
                <%--                                                                                </button>--%>
                <%--                                                                                <button type="submit"--%>
                <%--                                                                                        class="btn btn-primary">--%>
                <%--                                                                                    <fmt:message key="delete"--%>
                <%--                                                                                                 bundle="${ rb }"/>--%>
                <%--                                                                                </button>--%>
                <%--                                                                            </div>--%>
                <%--                                                                        </form>--%>
                <%--                                                                    </div>--%>
                <%--                                                                </div>--%>
                <%--                                                            </div>--%>
                <%--                                                        </div>--%>
                <%--                                                    </c:if>--%>
                <%--                                                </div>--%>
                <%--                                            </div>--%>
                <%--                                        </div>--%>
                <%--                                    </div>--%>
                <%--                                    <div class="card-footer text-muted">--%>
                <%--                                        <i class="far fa-clock"></i>--%>
                <%--                                        <fmt:parseDate value="${comment.publicationDate}"--%>
                <%--                                                       pattern="yyyy-MM-dd'T'HH:mm"--%>
                <%--                                                       var="time"--%>
                <%--                                                       type="date"/>--%>
                <%--                                        <fmt:formatDate pattern="dd-MMM-yyyy HH:mm" value="${time}"/>--%>
                <%--                                    </div>--%>
                <%--                                </div>--%>
                <%--                            </div>--%>
                <%--                        </div>--%>
                <%--                    </c:forEach>--%>
                <%--                </div>--%>


                <div class="container">
                    <c:forEach items="${show.comments}" var="comment">
                        <div class="card mt-3">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-2">
                                        <img src="<c:url value="/img/avatar/${comment.user.avatar}"/>"
                                             class="img img-rounded img-fluid" alt=""/>
                                    </div>
                                    <div class="col-md-10">
                                        <p class="text-style">
                                            <strong>${comment.user.login}</strong>
                                        </p>
                                        <div class="clearfix"></div>
                                        <p class="text-style">${comment.comment}</p>
                                        <c:if test="${sessionScope.user.equals(comment.user.login)}">
                                            <a href="" data-toggle="modal"
                                               data-target="#change${comment.id}"> <i
                                                    class="far fa-edit"></i></a>

                                            <!-- Modal -->
                                            <div class="modal fade" id="change${comment.id}" tabindex="-1"
                                                 role="dialog" aria-labelledby="exampleModalLongTitle"
                                                 aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title"><fmt:message key="edit"
                                                                                                 bundle="${ rb }"/></h5>
                                                            <button type="button" class="close"
                                                                    data-dismiss="modal"
                                                                    aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <form method="post"
                                                                  action="${pageContext.request.contextPath}/save_comment.html?commentId=${comment.id}&id=${show.id}">
                                                                <div class="form-group mt-3">

                                                                                <textarea class="form-control"
                                                                                          rows="5" name="comment"
                                                                                          required>${comment.comment}</textarea>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button"
                                                                            class="btn btn-secondary"
                                                                            data-dismiss="modal">
                                                                        <fmt:message key="cancel" bundle="${ rb }"/>
                                                                    </button>
                                                                    <button type="submit"
                                                                            class="btn btn-primary"><fmt:message
                                                                            key="confrim" bundle="${ rb }"/></button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>

                                        <c:if test="${sessionScope.user.equals(comment.user.login) || sessionScope.role == 1}">
                                            <a href="" data-toggle="modal"
                                               data-target="#delete${comment.id}"> <i
                                                    class="fas fa-trash-alt"></i></a>

                                            <!-- Modal -->
                                            <div class="modal fade" id="delete${comment.id}" tabindex="-1"
                                                 role="dialog" aria-labelledby="exampleModalLongTitle"
                                                 aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title"><fmt:message key="delete"
                                                                                                 bundle="${ rb }"/></h5>
                                                            <button type="button" class="close"
                                                                    data-dismiss="modal"
                                                                    aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <form method="post"
                                                                  action="${pageContext.request.contextPath}/delete_comment.html?id=${comment.id}">
                                                                <p>Вы уверены, что хотите удалить
                                                                    комментарий? </p>
                                                                <div class="modal-footer">
                                                                    <button type="button"
                                                                            class="btn btn-secondary"
                                                                            data-dismiss="modal"><fmt:message
                                                                            key="cancel" bundle="${ rb }"/>
                                                                    </button>
                                                                    <button type="submit"
                                                                            class="btn btn-primary">
                                                                        <fmt:message key="delete" bundle="${ rb }"/>
                                                                    </button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                                <p class="text-secondary text-right"><i class="far fa-clock"></i>
                                    <fmt:parseDate value="${comment.publicationDate}"
                                                   pattern="yyyy-MM-dd'T'HH:mm"
                                                   var="time"
                                                   type="date"/>
                                    <fmt:formatDate pattern="dd-MMM-yyyy HH:mm" value="${time}"/></p>
                            </div>
                        </div>
                    </c:forEach>
                </div>


            </div>

            <div class="col-2 mb-3 p-0">
                <jsp:useBean id="last" scope="request" type="java.util.List"/>
                <customTag:last-serials last="${last}"/>
            </div>

        </div>  <!-- row -->

    </div>

</div>