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
<%
%>
<html lang="ja">
<head>
    <link rel="stylesheet" type="text/css" href="LoginDesign.css">
    <title>ログイン</title>
</head>
<body>
<form action="<%= request.getContextPath()%>/LoginCheck" method="post">
    <div>
        <table>
            <tr>
                <td>学籍番号</td>
                <td>
                    <input type="text" name="id"
                           value="<jsp:getProperty name="user" property="userId"/>"
                           required pattern="^[0-9A-Za-z]+$"/>
                </td>
            </tr>
            <tr>
                <td>パスワード</td>
                <td><input type="password" name="password" required></td>
            </tr>
        </table>
        <button type="submit" class="btn_1" name="login">ログイン</button>
    </div>
</form>
</body>
</html>
