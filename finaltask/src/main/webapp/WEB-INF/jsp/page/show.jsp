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
                            <button type="button" class="btn btn-danger">Лайк</button>
                            <div class="card-body">
                                <p class="card-text">${show.description}</p>
                            </div>


                        </div>

                        <div class="col-4 card p-0 border-light" >

                            <div class="card p-0 border-light" >
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">Даты выхода: <fmt:formatDate value="${show.releaseDate}"
                                                                                             pattern="dd-MM-yyyy"/></li>
                                    <li class="list-group-item">Страна: ${show.country.name}</li>
                                    <li class="list-group-item">Студия: ${show.studio.name}</li>
                                    <li class="list-group-item">Жанры:<c:forEach items="${show.genres}"
                                                                                 var="genre"> ${genre.name} </c:forEach></li>

                                </ul>
                            </div>

                        </div>


                    </div>

                </div>
                <c:choose>
                <c:when test="${sessionScope.user != null}">
                <div>
                    <form action="/final/show?id=${show.id}" method="post">
                        <div class="form-group mt-3">
                            <label for="exampleFormControlTextarea1">Оставить свой комментарий:</label>
                            <textarea name="comment" class="form-control" id="exampleFormControlTextarea1" rows="3"
                                      placeholder="Максимальное количество симолов - 255"></textarea>
                        </div>
                        <button type="submit" class="btn btn-danger mb-2">Отправить</button>
                    </form>
                </div>
                </c:when>
                <c:when test="${sessionScope.user == null}">
                    <div class="alert alert-danger mt-3" role="alert">
                        Оставлять комментарии могут только зарегистрированные пользователи. <a href="/final/login" class="alert-link">Войти</a>.
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

                <div class="card bg-dark text-white mb-1">
                    <img class="card-img" src="img/pik.jpg" alt="Card image">
                    <div class="card-img-overlay overlay p-0">
                        <div class="layout">
                            <p class="info">2 сезон 12 серия</p>
                            <p class="data">02.02.2020</p>
                        </div>
                    </div>
                </div>
                <div class="card bg-dark text-white mb-1">
                    <img class="card-img" src="img/pik.jpg" alt="Card image">
                    <div class="card-img-overlay overlay p-0">
                        <div class="layout">
                            <p class="info">2 сезон 12 серия</p>
                            <p class="data">02.02.2020</p>
                        </div>
                    </div>
                </div>
                <div class="card bg-dark text-white mb-1">
                    <img class="card-img" src="img/pik.jpg" alt="Card image">
                    <div class="card-img-overlay overlay p-0">
                        <div class="layout">
                            <p class="info">2 сезон 12 серия</p>
                            <p class="data">02.02.2020</p>
                        </div>
                    </div>
                </div>
                <div class="card bg-dark text-white mb-1">
                    <img class="card-img" src="img/pik.jpg" alt="Card image">
                    <div class="card-img-overlay overlay p-0">
                        <div class="layout">
                            <p class="info">2 сезон 12 серия</p>
                            <p class="data">02.02.2020</p>
                        </div>
                    </div>
                </div>


            </div>

        </div>  <!-- row -->

    </div>

</div>