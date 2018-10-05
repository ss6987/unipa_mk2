<%@ page import="servlet.timetable.TimeTable" %>
<%@ page import="etc.ModelManager" %>
<%@ page import="Entity.Syllabus" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: SS
  Date: 2018/09/14
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="Entity.User" scope="session"/>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <%--<meta http-equiv="X-UA-Compatible" content="ie=edge">--%>
    <title>ログイン</title>
</head>
<body>
<form action="<%= request.getContextPath()%>/LoginCheck" method="post">
    <table>
        <tr>
            <td>学籍番号</td>
            <td>
                <input type="text" name="id"
                       value="<jsp:getProperty name="user" property="userId"/>"
                       required pattern="^[0-9A-Za-z]+$">
            </td>
        </tr>
        <tr>
            <td>パスワード</td>
            <td><input type="password" name="password" required></td>
        </tr>
    </table>
    <button type="submit" name="login">ログイン</button>
</form>
</body>
</html>
