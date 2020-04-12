<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-fluid">

    <div class="row">
        <div class="col-2 m-2">
            <div class="list-group">
                <a href="${pageContext.request.contextPath}/admin/serial.html"
                   class="list-group-item list-group-item-action active">
                    Редактирование сериала</a>
                <a href="${pageContext.request.contextPath}/admin/genre.html"
                   class="list-group-item list-group-item-action">
                    Редактирование жанра</a>
                <a href="${pageContext.request.contextPath}/admin/country.html"
                   class="list-group-item list-group-item-action">
                    Редактирование страны</a>
                <a href="${pageContext.request.contextPath}/admin/studio.html"
                   class="list-group-item list-group-item-action">
                    Редактирование студии</a>
            </div>
        </div>

        <div class="col-9">
            <div class="tab-content" id="pills-tabContent">
                <div class="tab-pane fade show active" id="pills-home" role="tabpanel"
                     aria-labelledby="pills-serial-tab">
                    <form method="post" enctype="multipart/form-data"
                          action="${pageContext.request.contextPath}/add_serial.html">
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

                        <div class="form-group">
                            <label for="full_logo">Выберите полное фото</label>
                            <input type="file" class="form-control-file" id="full_logo" name="full_logo">
                        </div>

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
            </div>
        </div>
    </div>
</div>
