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
    <link rel="stylesheet" type="text/css" href="LoginDesign.css">
    <title>ログイン</title>
</head>

<body>

<form action="<%=request.getContextPath()%>/LoginCheck" method="post">
    <table id="window">
    <tr><td id="site-box">
        <table id="part">
            <tr>
                <th>学籍番号</th>
                <td>
                    <input type="text" name="id"
                           value="<jsp:getProperty name="user" property="userId"/>"
                           required pattern="^[0-9A-Za-z]+$" autofocus/>
                </td>
            </tr>
            <tr>
                <th>パスワード</th>
                <td><input type="password" name="password" required></td>
            </tr>
        </table>
        <button type="submit" class="btn_1" name="login">ログイン</button>
    </td></tr>
    </table>
</form>
</body>
</html>
