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

<fmt:setLocale value="${cookie.language.value}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>

<div class="portfolio">
    <h1 class="text-center m-3" style="font-family: segoe print">${show.name}</h1>


    <div class="container-fluid">

        <div class="row">

            <div class="col-1 mb-3 p-0">
                <a class="nav-link" href="#">Драма</a>
                <a class="nav-link" href="#">История</a>
                <a class="nav-link" href="#">Приключение</a>
                <a class="nav-link" href="#">Боевик</a>

            </div>

            <div class="col-9 mb-3">
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
                                    <li>
                                        <a href="${pageContext.request.contextPath}/watch_serial.html?id=${show.id}"
                                           class="btn btn-primary btn-lg btn-block" role="button" aria-pressed="true">
                                            Смотреть</a>
                                    </li>
                                </ul>
                            </div>

                        </div>


                    </div>

                    <div>

                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-primary mt-3 mb-3" data-toggle="modal"
                                data-target="#exampleModalCenter">
                            Изменить
                        </button>


                        <!-- Modal -->
                        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
                             aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">

                                        <form method="post" enctype="multipart/form-data"
                                              action="${pageContext.request.contextPath}/profilepost.html">
                                            <div class="form-group mt-3">
                                                <label for="exampleFormControlInput1">Название сериала:</label>
                                                <input type="text" name="name" class="form-control"
                                                       id="exampleFormControlInput1"
                                                       placeholder="Название сериала">
                                            </div>
                                            <div class="form-group">
                                                <label for="description">Описание сериала</label>
                                                <textarea class="form-control" name="description" id="description"
                                                          rows="3"></textarea>
                                            </div>

                                            <div class="form-group">
                                                <label for="logo">Выберите фото превью</label>
                                                <input type="file" class="form-control-file" id="logo" name="logo">
                                            </div>

                                            <%--        <div class="form-group">--%>
                                            <%--            <label for="logo">Ссылка на лого</label>--%>
                                            <%--            <input type="text" name="logo" class="form-control" id="logo"--%>
                                            <%--                   placeholder="Ссылка на лого">--%>
                                            <%--        </div>--%>

                                            <div class="form-group">
                                                <label for="full_logo">Выберите полное фото</label>
                                                <input type="file" class="form-control-file" id="full_logo"
                                                       name="full_logo">
                                            </div>

                                            <%--        <div class="form-group">--%>
                                            <%--            <label for="full_logo">Ссылка на полное лого</label>--%>
                                            <%--            <input type="text" name="full_logo" class="form-control" id="full_logo"--%>
                                            <%--                   placeholder="Ссылка на полное лого">--%>
                                            <%--        </div>--%>

                                            <div class="form-group">
                                                <label for="date">Введите дату выхода сериала:</label>
                                                <input type="date" class="form-control" name="release_date" id="date">
                                            </div>


                                            <button type="submit" class="btn btn-primary">Добавить</button>
                                        </form>

                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                                        </button>
                                        <button type="button" class="btn btn-primary">Save changes</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>


                </div>
                <c:choose>
                    <c:when test="${sessionScope.user != null}">
                        <div>
                            <form action="${pageContext.request.contextPath}/add_comment.html?id=${show.id}"
                                  method="post">
                                <div class="form-group mt-3">
                                    <label for="exampleFormControlTextarea1"><fmt:message key="enterComment"
                                                                                          bundle="${ rb }"/>:</label>
                                    <textarea name="comment" class="form-control" id="exampleFormControlTextarea1"
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

                <c:forEach items="${show.comments}" var="comment">
                    <div class="card text-center mt-3">
                        <div class="card-header text-left">
                                ${comment.user.login}
                        </div>
                        <div class="card-body">
                            <p class="card-text text-left">${comment.comment}</p>
                        </div>
                        <div class="card-footer text-muted">
                            <fmt:formatDate value="${comment.publicationDate}" pattern="dd-MM-yyyy hh:mm:ss"/>

                        </div>
                    </div>
                </c:forEach>

            </div>

            <div class="card col-2 mb-3 p-0">

                <div class="card text-white bg-dark mb-3" style="max-height: 3rem;">
                    <div class="card-header">
                        <h6 class="text-center" style="font-family: segoe print"><fmt:message key="newShows"
                                                                                              bundle="${ rb }"/></h6>
                    </div>
                </div>

                <jsp:useBean id="last" scope="request" type="java.util.List"/>
                <c:forEach var="l" items="${last}">

                    <div class="card mb-3">
                        <a href="${pageContext.request.contextPath}/show.html?id=${l.id}">
                            <img class="card-img-top" src="${l.logo}" alt="Card image cap">
                        </a>
                        <div class="card-body">
                            <h5 class="card-title">${l.name}</h5>
                            <p class="card-text"><small class="text-muted"><fmt:formatDate value="${l.releaseDate}"
                                                                                           pattern="dd-MM-yyyy"/> </small>
                            </p>
                        </div>
                    </div>
                </c:forEach>

            </div>

        </div>  <!-- row -->

    </div>

</div>