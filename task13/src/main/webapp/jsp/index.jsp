<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 03.03.2020
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Xml Exercise</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body>
<div class="container-fluid pt-5">
    <fmt:setLocale value="${sessionScope.language}" scope="session"/>
    <fmt:setBundle basename="property.text" var="rb"/>
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

    <form action="/task13/parser" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label><fmt:message key="upload" bundle="${ rb }"/> </label>
            <input name="data" type="file" class="form-control-file" accept="application/xml">
        </div>
        <div>
            <label><fmt:message key="choise" bundle="${ rb }"/></label>
            <select class="form-control" name="parser">
                <option>SAX</option>
                <option>StAX</option>
                <option>DOM</option>
            </select>
        </div>
        <div>
            <button type="submit" class="btn btn-primary mt-3"><fmt:message key="send" bundle="${ rb }"/></button>
        </div>
    </form>
</div>
</body>
</html>
