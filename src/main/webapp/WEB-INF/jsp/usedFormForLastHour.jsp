<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lunar
  Date: 24.07.2018
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style><%@include file="/resources/tablestyle.css"%></style>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td colspan="2">
            Список форм за последний час:
        </td>
    </tr>
    <tr>
        <th>SSOID</th>
        <th>GRP</th>
    </tr>
    <c:forEach var="userData" items = "${usedForms}">
        <tr>
            <td>${userData.ssoid}</td>
            <td>${userData.grp}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
