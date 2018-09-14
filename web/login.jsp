<%--
  Created by IntelliJ IDEA.
  User: SS
  Date: 2018/09/14
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ログイン</title>
</head>
<body>
<form action="<%= request.getContextPath()%>/Login" method="post">
    <table>
        <tr>
            <td>学籍番号</td>
            <td><input type="text" name="id"/></td>
        </tr>
        <tr>
            <td>パスワード</td>
            <td><input type="password" name="password"></td>
        </tr>
    </table>
    <button type="submit" name="login">ログイン</button>
</form>
</body>
</html>
