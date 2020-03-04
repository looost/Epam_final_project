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
<div class="container-fluid pt-5">

    <form action="/xml/parser" method="post" enctype="multipart/form-data" >
        <div class="form-group">
            <label>Загрузите xml файл</label>
            <input name="data" type="file" class="form-control-file" accept="application/xml">
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

    <%--    <form action="/xml/parser1" method="post" enctype="multipart/form-data">--%>
    <%--    <input name="description" type="text"><br>--%>
    <%--    <input name="data" type="file"><br>--%>
    <%--    <input type="submit"><br>--%>
    <%--    </form>--%>

    <%--    <form method="POST" action="/xml/parser"  >--%>
    <%--        File:--%>
    <%--        <input type="file" name="file" id="file" /> <br/>--%>
    <%--        Destination:--%>
    <%--        <input type="text" value="/tmp" name="parser"/>--%>
    <%--        </br>--%>
    <%--        <input type="submit" value="Upload" name="upload" id="upload" />--%>
    <%--    </form>--%>
</div>
</body>
</html>
