<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 06.03.2020
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-2">

            <a href="/xml" class="btn btn-info btn-lg active mt-3 mb-3" role="button" aria-pressed="true">Вернуться
                назад</a>
        </div>
        <div class="col-10">
            <h1 class="text-center mt-3 mb-3">${error}</h1>
        </div>

    </div>
</body>
</html>
