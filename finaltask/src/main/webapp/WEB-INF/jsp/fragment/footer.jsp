<%@ page import="by.training.tag.VendorMap" %><%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 09.02.2020
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%
    VendorMap map = new VendorMap();
    request.setAttribute("rw", map);
%>
<div class="container text-center">
    <%--    <ctg:info-time/>--%>
    <ctg:table-revenue rows="${ rw.size }" head="Revenue">
        ${ rw.revenue }
    </ctg:table-revenue>
    <br/>
    <ctg:table-revenue>5 rub BulbaComp</ctg:table-revenue>
    <small>Copyright &copy; Shows</small>
</div>