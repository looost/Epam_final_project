<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 03.03.2020
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Xml Exercise</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body>
<div class="container-fluid">
    <h1>Hello, world!</h1>
    <form action="/xml/parser" method="post">
        <div class="form-group">
            <label>Загрузите xml файл</label>
            <input type="file" class="form-control-file">
        </div>
        <div>
            <label>Выберете способ парсинга XML</label>
            <select class="form-control" name="parser">
                <option>SAX</option>
                <option>StAX</option>
                <option>DOM</option>
            </select>
        </div>
        <div>
            <button type="submit" class="btn btn-primary mt-3">Отправить</button>
        </div>
    </form>
</div>
</body>
</html>
