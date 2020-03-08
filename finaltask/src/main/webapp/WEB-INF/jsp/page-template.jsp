<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 08.03.2020
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE><html lang="ru">
<head>
    <!-- Required meta tags -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/styles.css">
    <%--    <link href="/css/styles2.css" rel="stylesheet" type="text/css">--%>

    <title>Shows</title>
</head>

<body>

<header>
    <jsp:include page="/WEB-INF/jsp/fragment/header.jsp"/>
</header>
<main>
    <jsp:include page="${currentPage}"/>
</main>

</body>
<footer id="sticky-footer" class="py-4 bg-dark text-white-50">
    <jsp:include page="/WEB-INF/jsp/fragment/footer.jsp"/>
</footer>
</html>
