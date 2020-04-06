<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 09.02.2020
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="mt-3 ml-4">
        <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" id="pills-serial-tab" data-toggle="pill" href="#pills-home" role="tab"
                   aria-controls="pills-home" aria-selected="true">Сериал</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="pills-genre-tab" data-toggle="pill" href="#pills-profile" role="tab"
                   aria-controls="pills-profile" aria-selected="false">Жанр</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="pills-country-tab" data-toggle="pill" href="#pills-contact" role="tab"
                   aria-controls="pills-contact" aria-selected="false">Страна</a>
            </li>
        </ul>
        <div class="tab-content" id="pills-tabContent">
            <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-serial-tab">
                <form method="post" enctype="multipart/form-data"
                      action="${pageContext.request.contextPath}/profilepost.html">
                    <div class="form-group mt-3">
                        <label for="exampleFormControlInput1">Название сериала:</label>
                        <input type="text" name="name" class="form-control" id="exampleFormControlInput1"
                               placeholder="Название сериала">
                    </div>
                    <div class="form-group">
                        <label for="description">Описание сериала</label>
                        <textarea class="form-control" name="description" id="description" rows="3"></textarea>
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
                        <input type="file" class="form-control-file" id="full_logo" name="full_logo">
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

                    <div class="mb-2">
                        <label>Выберите жанры:</label>
                        <jsp:useBean id="genres" scope="request" type="java.util.List"/>
                        <c:forEach var="g" items="${genres}">
                            <div class="custom-control custom-checkbox">
                                <input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="${g.id}"
                                       name="genre">
                                <label class="form-check-label" for="inlineCheckbox2">${g.name}</label>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="mb-2">
                        <label>Выберите страну:</label>
                        <br>
                        <select class="custom-select" name="country">
                            <jsp:useBean id="country" scope="request" type="java.util.List"/>
                            <c:forEach var="c" items="${country}">
                                <option value="${c.id}">${c.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-2">
                        <label>Выберите студию:</label>
                        <select class="custom-select" name="studio">
                            <jsp:useBean id="studio" scope="request" type="java.util.List"/>
                            <c:forEach var="s" items="${studio}">
                                <option value="${s.id}">${s.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </form>
            </div>

            <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-genre-tab">
                <jsp:include page="../fragment/genre_panel.jsp"/>
            </div>

            <div class="tab-pane fade" id="pills-contact" role="tabpanel" aria-labelledby="pills-country-tab">
                <form method="post" action="${pageContext.request.contextPath}/add_country.html">
                    <div class="form-group mt-3">
                        <div class="mb-2">
                            <label for="addCountry">Название страны:</label>
                            <input type="text" name="country" class="form-control" id="addCountry"
                                   placeholder="Название страны">
                        </div>
                        <button type="submit" class="btn btn-primary">Добавить</button>
                    </div>
                </form>
            </div>

        </div>
    </div>

    <%--    <form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/profilepost.html">--%>
    <%--        <div class="form-group mt-3">--%>
    <%--            <label for="exampleFormControlInput1">Название сериала:</label>--%>
    <%--            <input type="text" name="name" class="form-control" id="exampleFormControlInput1"--%>
    <%--                   placeholder="Название сериала">--%>
    <%--        </div>--%>
    <%--        <div class="form-group">--%>
    <%--            <label for="description">Описание сериала</label>--%>
    <%--            <textarea class="form-control" name="description" id="description" rows="3"></textarea>--%>
    <%--        </div>--%>

    <%--        <div class="form-group">--%>
    <%--            <label for="logo">Выберите фото превью</label>--%>
    <%--            <input type="file" class="form-control-file" id="logo" name="logo">--%>
    <%--        </div>--%>

    <%--        &lt;%&ndash;        <div class="form-group">&ndash;%&gt;--%>
    <%--        &lt;%&ndash;            <label for="logo">Ссылка на лого</label>&ndash;%&gt;--%>
    <%--        &lt;%&ndash;            <input type="text" name="logo" class="form-control" id="logo"&ndash;%&gt;--%>
    <%--        &lt;%&ndash;                   placeholder="Ссылка на лого">&ndash;%&gt;--%>
    <%--        &lt;%&ndash;        </div>&ndash;%&gt;--%>

    <%--        <div class="form-group">--%>
    <%--            <label for="full_logo">Выберите полное фото</label>--%>
    <%--            <input type="file" class="form-control-file" id="full_logo" name="full_logo">--%>
    <%--        </div>--%>

    <%--        &lt;%&ndash;        <div class="form-group">&ndash;%&gt;--%>
    <%--        &lt;%&ndash;            <label for="full_logo">Ссылка на полное лого</label>&ndash;%&gt;--%>
    <%--        &lt;%&ndash;            <input type="text" name="full_logo" class="form-control" id="full_logo"&ndash;%&gt;--%>
    <%--        &lt;%&ndash;                   placeholder="Ссылка на полное лого">&ndash;%&gt;--%>
    <%--        &lt;%&ndash;        </div>&ndash;%&gt;--%>

    <%--        <div class="form-group">--%>
    <%--            <label for="date">Введите дату выхода сериала:</label>--%>
    <%--            <input type="date" class="form-control" name="release_date" id="date">--%>
    <%--        </div>--%>

    <%--        <div class="mb-2">--%>
    <%--            <label>Выберите жанры:</label>--%>
    <%--            <jsp:useBean id="genres" scope="request" type="java.util.List"/>--%>
    <%--            <c:forEach var="g" items="${genres}">--%>
    <%--                <div class="custom-control custom-checkbox">--%>
    <%--                    <input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="${g.id}" name="genre">--%>
    <%--                    <label class="form-check-label" for="inlineCheckbox2">${g.name}</label>--%>
    <%--                </div>--%>
    <%--            </c:forEach>--%>
    <%--        </div>--%>

    <%--        <div class="mb-2">--%>
    <%--            <label>Выберите страну:</label>--%>
    <%--            <br>--%>
    <%--            <select class="custom-select" name="country">--%>
    <%--                <jsp:useBean id="country" scope="request" type="java.util.List"/>--%>
    <%--                <c:forEach var="c" items="${country}">--%>
    <%--                    <option value="${c.id}">${c.name}</option>--%>
    <%--                </c:forEach>--%>
    <%--            </select>--%>
    <%--        </div>--%>

    <%--        <div class="mb-2">--%>
    <%--            <label>Выберите студию:</label>--%>
    <%--            <select class="custom-select" name="studio">--%>
    <%--                <jsp:useBean id="studio" scope="request" type="java.util.List"/>--%>
    <%--                <c:forEach var="s" items="${studio}">--%>
    <%--                    <option value="${s.id}">${s.name}</option>--%>
    <%--                </c:forEach>--%>
    <%--            </select>--%>
    <%--        </div>--%>

    <%--        <button type="submit" class="btn btn-primary">Добавить</button>--%>
    <%--    </form>--%>
</div>