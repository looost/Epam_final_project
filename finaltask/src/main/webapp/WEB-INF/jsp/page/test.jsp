<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="./styles.css">

    <title>Shows</title>
</head>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">

        <a class="navbar-brand" href="#">
            <img src="img/logo.png" width="123" height="27" class="d-inline-block align-top" alt="">
        </a>


        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Главная <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Сериалы</a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
        <div>

            <a class="nav-link" href="#">Вход</a>

        </div>
    </nav>
</header>


<body>
<div class="portfolio">
    <h1 class="text-center m-3" style="font-family: segoe print">Мир Дикого Запада</h1>


    <div class="container-fluid">

        <div class="row">

            <div class="col-10 mb-3">
                <div class="container-fluid">
                    <div class="row">

                        <div class="col-8 card p-0 border-light">
                            <img class="card-img-top border " src="${show.fullLogo}" alt="Card image cap">
                            <div class="card-body">
                                <p class="card-text">История о преступниках, решивших ограбить Королевский монетный двор
                                    Испании и украсть 2,4 млрд евро.</p>
                            </div>
                        </div>

                        <div class="col-4 card p-0 border-light">

                            <div class="card p-0 border-light">
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">
                                        Дата выхода: 02-10-2016
                                    </li>
                                    <li class="list-group-item">Страна: США</li>
                                    <li class="list-group-item">Студия: HBO</li>
                                    <li class="list-group-item">Жанры: Драма Мистика Научная фантастика</li>

                                </ul>
                            </div>

                        </div>
                    </div>

                    <div>


                        <div>
                            <form action="${pageContext.request.contextPath}/add_comment.html?id=${show.id}"
                                  method="post">
                                <div class="form-group mt-3">
                                    <label for="exampleFormControlTextarea1">ВВкдите коммент:</label>
                                    <textarea name="comment" class="form-control"
                                              id="exampleFormControlTextarea1"
                                              rows="3"
                                              placeholder="введите"></textarea>
                                </div>
                                <button type="submit" class="btn btn-danger mb-2">оправить</button>
                            </form>
                        </div>


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
                            <fmt:parseDate value="${comment.publicationDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="time"
                                           type="date"/>
                            <fmt:formatDate pattern="dd-MMM-yyyy HH:mm" value="${time}"/>
                                <%--                                ${comment.publicationDate}--%>
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


</body>


<footer id="sticky-footer" class="py-4 bg-dark text-white-50">
    <div class="container text-center">
        <small>Copyright &copy; Shows</small>
    </div>
</footer>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>