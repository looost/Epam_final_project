<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 03.03.2020
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>
<html>
<head>
    <title>${sessionScope.parser} parser</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<body style="width: 2500px">

<div>
    <form method="post" action="/task13/language">
        <label>
            <select name="language">
                <option value="ru_RU">Русский</option>
                <option value="en_US">English</option>
                <option value="by_BY">Беларускі</option>
            </select>
        </label>
        <input type="submit" value="<fmt:message key="send" bundle="${ rb }" />">
    </form>
</div>


<div class="container-fluid">
    <div class="row">
        <div class="col-2">

            <a href="/task13/xml" class="btn btn-info btn-lg active mt-3 mb-3" role="button" aria-pressed="true">
                <fmt:message key="back" bundle="${ rb }"/>
            </a>
        </div>

        <div class="col-10">
            <h1 class="text-center mt-3 mb-3"><fmt:message key="checking" bundle="${ rb }"/>
                - ${sessionScope.parser} </h1>
        </div>

    </div>

    <%--    <table class="table table-sm table-hover table-bordered" style="text-align: center">--%>
    <%--        <thead class="thead">--%>
    <%--        <tr>--%>
    <%--            <th rowspan="2">id</th>--%>
    <%--            <th rowspan="2">Serial name</th>--%>
    <%--            <th rowspan="2">Description</th>--%>
    <%--            <th rowspan="2">Logo</th>--%>
    <%--            <th rowspan="2">Full logo</th>--%>
    <%--            <th rowspan="2">Release date</th>--%>
    <%--            <th rowspan="2">Count like</th>--%>
    <%--            <th colspan="2">Country</th>--%>
    <%--            <th colspan="2">Studio</th>--%>
    <%--            <th rowspan="2" style="width: 150px">Genres</th>--%>
    <%--            <th rowspan="2">Comments</th>--%>
    <%--        </tr>--%>

    <%--        <tr>--%>
    <%--            <th>id</th>--%>
    <%--            <th>name</th>--%>
    <%--            <th>id</th>--%>
    <%--            <th>name</th>--%>
    <%--        </tr>--%>
    <%--        </thead>--%>

    <%--        <c:forEach items="${serials}" var="s">--%>
    <%--            <tr>--%>
    <%--                <td>${s.id}</td>--%>
    <%--                <td>${s.name}</td>--%>
    <%--                <td>${s.description}</td>--%>
    <%--                <td>${s.logo}</td>--%>
    <%--                <td>${s.fullLogo}</td>--%>
    <%--                <td>${s.releaseDate}</td>--%>
    <%--                <td>${s.countLike}</td>--%>
    <%--                <td>${s.country.id}</td>--%>
    <%--                <td>${s.country.name}</td>--%>
    <%--                <td>${s.studio.id}</td>--%>
    <%--                <td>${s.studio.name}</td>--%>
    <%--                <td><c:forEach items="${s.genres}" var="g">id=${g.id} name=${g.name} <br>--%>
    <%--                    <hr>--%>
    <%--                </c:forEach></td>--%>
    <%--                <td><c:forEach items="${s.comments}"--%>
    <%--                               var="c"> User: id=${c.user.id} login=${c.user.login} password=${c.user.password} role=${c.user.role}--%>
    <%--                    Comment text: ${c.comment} Publication date: ${c.publicationDate} <br>--%>
    <%--                    <hr>--%>
    <%--                </c:forEach></td>--%>
    <%--            </tr>--%>
    <%--        </c:forEach>--%>

    <%--    </table>--%>

    <hr>
    <hr>

    <table class="table table-sm table-hover table-bordered" style="text-align: center">
        <thead class="thead">
        <tr>
            <th rowspan="2">id</th>
            <th rowspan="2">Serial name</th>
            <th rowspan="2">Description</th>
            <th rowspan="2">Logo</th>
            <th rowspan="2">Full logo</th>
            <th rowspan="2">Release date</th>
            <th rowspan="2">Count like</th>
            <th rowspan="2">Country</th>
            <th rowspan="2">Studio</th>
            <th rowspan="2" style="width: 150px">Genres</th>
            <th rowspan="2">Comments</th>
        </tr>

        </thead>

        <c:forEach items="${sessionScope.serials}" var="s">
            <tr>
                <td>${s.id}</td>
                <td>${s.name}</td>
                <td>${s.description}</td>
                <td>${s.logo}</td>
                <td>${s.fullLogo}</td>
                <td>${s.releaseDate}</td>
                <td>${s.countLike}</td>
                <td>${s.country.name}</td>
                <td>${s.studio.name}</td>
                <td><c:forEach items="${s.genres}" var="g">${g.name} <br>
                    <hr>
                </c:forEach></td>
                <td><c:forEach items="${s.comments}"
                               var="c"> User: login=${c.user.login}
                    Comment text: ${c.comment} Publication date: ${c.publicationDate} <br>
                    <hr>
                </c:forEach></td>
            </tr>
        </c:forEach>

    </table>


</div>
</body>
</html>
