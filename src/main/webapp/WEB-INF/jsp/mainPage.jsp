<%--
  Created by IntelliJ IDEA.
  User: lunar
  Date: 24.07.2018
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
<table>
    <tr>
        <td>
            <form action="http://localhost:8080/unfinished_users">
                <input type="submit" value="Вывести список пользователей, не закончивших операцию" />
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <form action="http://localhost:8080/top-five-form-controller">
                <input type="submit" value="Вывести 5 самых популярных форм" />
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <form action="http://localhost:8080/used-form-for-last-hour">
                <input type="submit" value="Вывести формы за последний час" />
            </form>
        </td>
    </tr>
</table>
</body>
</html>
