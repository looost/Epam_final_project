<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="customTag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>
<!-- portfolio -->
<div class="portfolio">
    <h1 class="text-center m-3 text-style"><fmt:message key="liked" bundle="${ rb }"/></h1>


    <div class="container-fluid">

        <div class="row">

            <div class="col-2 m-2">
                <div class="list-group">
                    <a href="${pageContext.request.contextPath}/profile.html"
                       class="list-group-item list-group-item-action text-style">
                        <i class="fas fa-user-edit"></i> <fmt:message key="profile" bundle="${ rb }"/></a>
                    <a href="${pageContext.request.contextPath}/my_serial.html"
                       class="list-group-item list-group-item-action text-style">
                        <i class="fas fa-eye"></i> <fmt:message key="myShows" bundle="${ rb }"/></a>
                    <a href="${pageContext.request.contextPath}/liked.html"
                       class="list-group-item list-group-item-action text-style active">
                        <i class="fas fa-heart"></i> <fmt:message key="liked" bundle="${ rb }"/></a>
                </div>
            </div>

            <div class="col-9 mb-3">
                <div class="container-fluid">
                    <div class="row">

                        <jsp:useBean id="shows" scope="request" type="java.util.List"/>

                        <c:if test="${empty shows}">
                            <div class="container">
                                <div class="alert alert-info m-2 text-center" role="alert">
                                    <h4 class="alert-heading">УПС!</h4>
                                    <hr>
                                    <p class="mb-0">У вас пока нету понравившехся сериалов!</p>
                                </div>
                            </div>
                        </c:if>


                        <c:forEach items="${shows}" var="s">
                            <div class="col-4 mb-4">
                                <div class="card h-100">
                                    <a href="${pageContext.request.contextPath}/show.html?id=${s.id}">
                                        <img class="card-img-top" src="<c:url value="/img/${s.logo}"/>"
                                             alt="Card image cap" height="168" width="269">
                                    </a>

                                    <div class="card-header">
                                        <a class="card-title"
                                           href="${pageContext.request.contextPath}/show.html?id=${s.id}">
                                            <h5 class="text-center text-style">${s.name}</h5>
                                        </a>
                                    </div>
                                    <div class="card-body">
                                        <p class="card-text"><em>${fn:substring(s.description, 0, 100)}...</em></p>
                                    </div>
                                    <div class="card-footer">
                                        <small class="text-muted text-style">${s.studio.name}</small>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>


        </div>  <!-- row -->

    </div>

</div>


<div id="content">
    <div id="pagination-5" class="pagination pagination-lg justify-content-center "></div>
</div>

<script>

    $(document).ready(function () {
        var itemsCount = ${countAllSerial};
        var itemsOnPage = ${itemsOnPage};

        function getParameterByName(name, url) {
            if (!url) url = window.location.href;
            name = name.replace(/[\[\]]/g, "\\$&");
            var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
                results = regex.exec(url);
            if (!results) return null;
            if (!results[2]) return '';
            return decodeURIComponent(results[2].replace(/\+/g, " "));
        }

        var pagination5 = new Pagination({
            container: $("#pagination-5"),
            pageClickUrl: "?page={{page}}",
            //pageClickUrl: function(num) { return "?page=" + num; },
            // pageClickCallback: pageClick5,
            callPageClickCallbackOnInit: true,
            // showInput: true,
            // showSlider: true,
            // enhancedMode: true,
            maxVisibleElements: 20,
            inputTitle: "Go to page"
        });
        pagination5.make(itemsCount, itemsOnPage, getParameterByName("page"));
    });
</script>

