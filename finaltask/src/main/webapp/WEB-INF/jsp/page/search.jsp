<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="customTag" tagdir="/WEB-INF/tags" %>
<!-- portfolio -->
<div class="portfolio">
    <h1 class="text-center m-3" style="font-family: segoe print">Сериалы</h1>


    <div class="container-fluid">

        <div class="row">

            <div class="col-2 mb-3 p-0">
                <jsp:useBean id="genres" scope="request" type="java.util.List"/>
                <jsp:useBean id="country" scope="request" type="java.util.List"/>
                <jsp:useBean id="studio" scope="request" type="java.util.List"/>
                <customTag:search-filter genres="${genres}" country="${country}" studio="${studio}"/>
            </div>

            <div class="col-10 mb-3">
                <div class="container-fluid">
                    <div class="row">
                        <%--                        <c:forEach items="${shows}" var="s">--%>
                        <%--                            <div class="col-3 card p-0 mb-2">--%>
                        <%--                                <a href="/final/show.html?id=${s.id}">--%>
                        <%--                                    <img class="card-img-top" src="${s.logo}" alt="Card image cap" height="144px">--%>
                        <%--                                </a>--%>

                        <%--                                <div class="card-header" style="max-height: 3rem">--%>
                        <%--                                    <a class="card-title" href="/final/show.html?id=${s.id}"--%>
                        <%--                                       style="font-family: segoe print">--%>
                        <%--                                        <h5 class="text-center">${s.name}</h5>--%>
                        <%--                                    </a>--%>
                        <%--                                </div>--%>
                        <%--                                <div class="card-body">--%>
                        <%--                                    <p class="card-text"><em>${fn:substring(s.description, 0, 100)}...</em></p>--%>
                        <%--                                </div>--%>
                        <%--                                <div class="card-footer">--%>
                        <%--                                    <small class="text-muted">${s.studio.name}</small>--%>
                        <%--                                </div>--%>
                        <%--                            </div>--%>
                        <%--                        </c:forEach>--%>

                            <%--                            <div class="row row-cols-1 row-cols-md-3">--%>

                            <jsp:useBean id="shows" scope="request" type="java.util.List"/>

                            <c:if test="${empty shows}">
                                <div class="container">
                                    <div class="alert alert-info m-2 text-center" role="alert">
                                        <h4 class="alert-heading">УПС!</h4>
                                        <hr>
                                        <p class="mb-0">Не найдено сериалов с такими критериями :(</p>
                                    </div>
                                </div>
                            </c:if>


                            <c:forEach items="${shows}" var="s">
                                <div class="col-4 mb-4">
                                    <div class="card h-100">
                                        <a href="${pageContext.request.contextPath}/show.html?id=${s.id}">
                                            <img class="card-img-top" src="/final/${s.logo}" alt="Card image cap">
                                        </a>

                                        <div class="card-header">
                                        <a class="card-title"
                                           href="${pageContext.request.contextPath}/show.html?id=${s.id}"
                                           style="font-family: segoe print">
                                            <h5 class="text-center">${s.name}</h5>
                                        </a>
                                        </div>
                                        <div class="card-body">
                                            <p class="card-text"><em>${fn:substring(s.description, 0, 100)}...</em></p>
                                        </div>
                                        <div class="card-footer">
                                        <small class="text-muted">${s.studio.name}</small>
                                    </div>
                                </div>
                                </div>

                                <%--                                <div class="row no-gutters bg-light position-relative">--%>
                                <%--                                    <div class="col-md-6 mb-md-0 p-md-4">--%>
                                <%--                                        <a href="${pageContext.request.contextPath}/show.html?id=${s.id}">--%>
                                <%--                                            <img src="/final/${s.logo}" class="w-100" alt="...">--%>
                                <%--                                        </a>--%>

                                <%--                                    </div>--%>
                                <%--                                    <div class="col-md-6 position-static p-4 pl-md-0">--%>
                                <%--                                        <a class="card-title"--%>
                                <%--                                           href="${pageContext.request.contextPath}/show.html?id=${s.id}"--%>
                                <%--                                           style="font-family: segoe print">--%>
                                <%--                                            <h5 class="mt-0">${s.name}</h5>--%>
                                <%--                                        </a>--%>

                                <%--                                        <p>${s.description}</p>--%>
                                <%--                                        <small class="text-muted">${s.studio.name}</small>--%>
                                <%--                                    </div>--%>
                                <%--                                </div>--%>

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

        const getClickUrl = () => {
            const queryString = "${pageContext.request.queryString}";
            const param = queryString.split('&').filter((param) => !param.includes('page')).join('&');
            return (!param) ? "" : "&" + param;
        };

        var pagination5 = new Pagination({
            container: $("#pagination-5"),
            pageClickUrl: "?page={{page}}" + getClickUrl(),
            // pageClickUrl: function(num) { return "?page=" + num; },
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