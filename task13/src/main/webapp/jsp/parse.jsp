<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 03.03.2020
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<body style="width: 2500px">
<div class="container-fluid">
    <row>
        <h1 class="text-center">Проверка работы парсера - ${parser} </h1>
    </row>
    <table class="table table-sm table-hover table-bordered">
        <thead>
        <tr>
            <th>id</th>
            <th>Serial name</th>
            <th>Description</th>
            <th>Logo</th>
            <th>Full logo</th>
            <th>Release date</th>
            <th>Count like</th>
            <th colspan="2">Country</th>
            <th colspan="2">Studio</th>
            <th>Genres</th>
            <th>Comments</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${serials}" var="s">
            <tr>
                <th scope="row">${s.id}</th>
                <td>${s.name}</td>
                <td>${s.description}</td>
                <td>${s.logo}</td>
                <td>${s.fullLogo}</td>
                <td>${s.releaseDate}</td>
                <td>${s.countLike}</td>
                <td>${s.country.id}</td>
                <td>${s.country.name}</td>
                <td>${s.studio.id}</td>
                <td>${s.studio.name}</td>
                <td>${s.genres}</td>
                <td>${s.comments}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="table table-sm table-hover table-bordered" style="text-align: center">
        <tr>
            <th rowspan="3">id</th>
            <th rowspan="3">Serial name</th>
            <th rowspan="3">Description</th>
            <th rowspan="3">Logo</th>
            <th rowspan="3">Full logo</th>
            <th rowspan="3">Release date</th>
            <th rowspan="3">Count like</th>
            <th colspan="2">Country</th>
            <th colspan="2">Studio</th>
            <th colspan="6">Genres</th>
        </tr>
        <tr>
            <th rowspan="2">id</th>
            <th rowspan="2">name</th>
            <th rowspan="2">id</th>
            <th rowspan="2">name</th>
            <th colspan="2">genre1</th>
            <th colspan="2">genre2</th>
            <th colspan="2">genre3</th>
        </tr>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>id</th>
            <th>name</th>
            <th>id</th>
            <th>name</th>
        </tr>
        <c:forEach items="${serials}" var="s">
            <tr>
                <td>${s.id}</td>
                <td>${s.name}</td>
                <td>${s.description}</td>
                <td>${s.logo}</td>
                <td>${s.fullLogo}</td>
                <td>${s.releaseDate}</td>
                <td>${s.countLike}</td>
                <td>${s.country.id}</td>
                <td>${s.country.name}</td>
                <td>${s.studio.id}</td>
                <td>${s.studio.name}</td>
                <td>а</td>
                <td>а</td>
                <td>а</td>
                <td>а</td>
                <td>а</td>
                <td>а</td>
            </tr>
        </c:forEach>
    </table>

    <table class="table table-sm table-hover table-bordered" style="text-align: center">
        <tr>
            <th rowspan="2">id</th>
            <th rowspan="2">Serial name</th>
            <th rowspan="2">Description</th>
            <th rowspan="2">Logo</th>
            <th rowspan="2">Full logo</th>
            <th rowspan="2">Release date</th>
            <th rowspan="2">Count like</th>
            <th colspan="2">Country</th>
            <th colspan="2">Studio</th>
            <th rowspan="2" style="width: 150px">Genres</th>
            <th rowspan="2">Comments</th>
        </tr>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>id</th>
            <th>name</th>
        </tr>

        <c:forEach items="${serials}" var="s">
            <tr>
                <td>${s.id}</td>
                <td>${s.name}</td>
                <td>${s.description}</td>
                <td>${s.logo}</td>
                <td>${s.fullLogo}</td>
                <td>${s.releaseDate}</td>
                <td>${s.countLike}</td>
                <td>${s.country.id}</td>
                <td>${s.country.name}</td>
                <td>${s.studio.id}</td>
                <td>${s.studio.name}</td>
                <td><c:forEach items="${s.genres}" var="g">id=${g.id} name=${g.name} <br></c:forEach></td>
                <td><c:forEach items="${s.comments}"
                               var="c"> User: id=${c.user.id} login=${c.user.login} password=${c.user.password} role=${c.user.role}
                    Comment text: ${c.comment} Publication date: ${c.publicationDate} <br></c:forEach></td>
            </tr>
        </c:forEach>

    </table>


</div>
</body>
</html>
