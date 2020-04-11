<!DOCTYPE html>

<html lang="en">
<head>
    <%--    <meta charset="utf-8" />--%>
    <%--    <title>S-Pagination DEMO</title>--%>
    <%--    <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>--%>
    <%--    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU=" crossorigin="anonymous"></script>--%>
    <%--    <script type="text/javascript">--%>
    <%--        <%@ include file="../../../js/dist/pagination.js" %>--%>
    <%--    </script>--%>
    <%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">--%>
    <%--    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">--%>
    <%--&lt;%&ndash;    <link rel="stylesheet" href="dist/pagination.css" />&ndash;%&gt;--%>
    <%--    <style>--%>
    <%--        body {--%>
    <%--            margin: 0 20px;--%>
    <%--        }--%>
    <%--    </style>--%>


    <!-- Required meta tags -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <%--    <link href="/css/styles2.css" rel="stylesheet" type="text/css">--%>

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"
            integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
            integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU=" crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>

    <script type="text/javascript">
        <%@ include file="../../../js/dist/pagination.js" %>
    </script>

    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <title>Shows</title>
</head>
<body>
<h1>S-Pagination DEMO</h1>

<div id="content">
    <h3>Basic pagination (default)</h3>
    <div>
        <samp>
            <span>Current page:</span>
            <span id="page-number-1"></span>
        </samp>

        <div id="pagination-1"></div>
    </div>
    <hr/>

    <h3>Pagination with text input and limited elements count</h3>
    <div>
        <samp>
            <span>Current page:</span>
            <span id="page-number-2"></span>
        </samp>

        <div id="pagination-2"></div>
    </div>
    <hr/>

    <h3>Pagination with slider and limited elements count</h3>
    <div>
        <samp>
            <span>Current page:</span>
            <span id="page-number-3"></span>
        </samp>

        <div id="pagination-3"></div>
    </div>
    <hr/>

    <h3>Pagination with enabled enhanced mode</h3>
    <div>
        <samp>
            <span>Current page:</span>
            <span id="page-number-4"></span>
        </samp>

        <div id="pagination-4"></div>
    </div>
    <hr/>

    <h3>Pagination with all options enabled</h3>
    <div>
        <samp>
            <span>Current page:</span>
            <span id="page-number-all"></span>
        </samp>

        <div id="pagination-all"></div>
    </div>
    <hr/>

    <h3>Pagination using redirect to specified page</h3>
    <div>
        <samp>
            <span>Current page:</span>
            <span id="page-number-5"></span>
        </samp>

        <div id="pagination-5"></div>
    </div>
    <hr/>
</div>

<script>
    function pageClick1(pageNumber) {
        $("#page-number-1").text(pageNumber);
    }

    function pageClick2(pageNumber) {
        $("#page-number-2").text(pageNumber);
    }

    function pageClick3(pageNumber) {
        $("#page-number-3").text(pageNumber);
    }

    function pageClick4(pageNumber) {
        $("#page-number-4").text(pageNumber);
    }

    function pageClickAll(pageNumber) {
        $("#page-number-all").text(pageNumber);
    }

    function pageClick5(pageNumber) {
        $("#page-number-5").text(pageNumber);
    }

    $(document).ready(function () {
        var itemsCount = 300;
        var itemsOnPage = 10;

        var pagination1 = new Pagination({
            container: $("#pagination-1"),
            pageClickCallback: pageClick1
        });
        pagination1.make(itemsCount, itemsOnPage);

        var pagination2 = new Pagination({
            container: $("#pagination-2"),
            pageClickCallback: pageClick2,
            maxVisibleElements: 9,
            showInput: true,
            inputTitle: "Go to page"
        });
        pagination2.make(itemsCount, itemsOnPage);

        var pagination3 = new Pagination({
            container: $("#pagination-3"),
            pageClickCallback: pageClick3,
            maxVisibleElements: 9,
            showSlider: true
        });
        pagination3.make(itemsCount, itemsOnPage);

        var pagination4 = new Pagination({
            container: $("#pagination-4"),
            pageClickCallback: pageClick4,
            enhancedMode: true
        });
        pagination4.make(itemsCount, itemsOnPage);

        var paginationAll = new Pagination({
            container: $("#pagination-all"),
            pageClickCallback: pageClickAll,
            callPageClickCallbackOnInit: true,
            showInput: true,
            showSlider: true,
            enhancedMode: true,
            maxVisibleElements: 20,
            inputTitle: "Go to page"
        });
        paginationAll.make(itemsCount, itemsOnPage);

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
            pageClickCallback: pageClick5,
            callPageClickCallbackOnInit: true,
            showInput: true,
            showSlider: true,
            enhancedMode: true,
            maxVisibleElements: 20,
            inputTitle: "Go to page"
        });
        pagination5.make(itemsCount, itemsOnPage, getParameterByName("page"));
    });
</script>
</body>
</html>
