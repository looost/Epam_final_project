<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="customTag" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${cookie.language.value}" scope="session"/>
<fmt:setBundle basename="property.text" var="rb"/>


<!-- portfolio -->
<div class="portfolio">

    <h1 class="text-center m-3 text-style"><fmt:message key="favoriteSerial" bundle="${ rb }"/></h1>

    <div class="container-fluid">

        <div class="row mt-4 pt-4">

            <div class="col-2 mb-2 p-0">
                <jsp:useBean id="genres" scope="request" type="java.util.List"/>
                <jsp:useBean id="country" scope="request" type="java.util.List"/>
                <jsp:useBean id="studio" scope="request" type="java.util.List"/>
                <customTag:search-filter genres="${genres}" country="${country}" studio="${studio}"/>
            </div>


            <div class="col-8 ">
                <div class="container">
                    <div class="row">

                        <table class="table table-hover text-style">
                            <thead>
                            <tr>
                                <th scope="col"><fmt:message key="title" bundle="${ rb }"/></th>
                                <th scope="col"><fmt:message key="numberIfLikes" bundle="${ rb }"/></th>
                                <th scope="col"><fmt:message key="country" bundle="${ rb }"/></th>
                                <th scope="col"><fmt:message key="studio" bundle="${ rb }"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <jsp:useBean id="shows" scope="request" type="java.util.List"/>
                            <c:forEach var="s" items="${shows}">
                                <tr>
                                    <td><a href="${pageContext.request.contextPath}/show.html?id=${s.id}">
                                            ${s.name}</a>
                                    </td>
                                    <td>${s.countLike}</td>
                                    <td>${s.country.name}</td>
                                    <td>${s.studio.name}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>

            <div class="col-2 mb-3 p-0">
                <jsp:useBean id="last" scope="request" type="java.util.List"/>
                <customTag:last-serials last="${last}"/>
            </div>

        </div>  <!-- row -->

    </div>

</div>
<%--</nav>--%>
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

