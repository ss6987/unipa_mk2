<%@ page import="Entity.User" %><%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/13
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="Entity.User" scope="session"/>
<jsp:useBean id="targetUserId" class="java.lang.String" scope="session"/>
<jsp:useBean id="targetUser" class="Entity.User" scope="request"/>

<html lang="ja">
<head>
    <title>ユーザー管理</title>
</head>
<body>

<div>
    <form action="/Top" method="get">
        <button type="submit">トップ</button>
    </form>
</div>
<%
    if (!targetUserId.equals(user.getUserId())) {
%>
<form action="/UserSearch" method="post">
    <button type="submit" name="action" value="return">戻る</button>
</form>
<%
    }
%>

<h1 align="center">ユーザー管理</h1>

<table BORDER="1" align="center">
    <tr align="center">
        <th>学籍番号</th>
        <td>
            <%=targetUser.getUserId()%>
        </td>
    </tr>

    <tr align="center">
        <th>氏名</th>
        <td>
            <jsp:getProperty name="targetUser" property="name"/>
        </td>
    </tr>
    <tr align="center">
        <th>フリガナ</th>
        <td>
            <jsp:getProperty name="targetUser" property="phonetic"/>
        </td>
    </tr>

    <tr align="center">
        <th>性別</th>
        <td>
            <jsp:getProperty name="targetUser" property="genderString"/>
        </td>
    </tr>

    <tr align="center">
        <th>ユーザー分類</th>
        <td>
            <jsp:getProperty name="targetUser" property="userClassification"/>
        </td>
    </tr>

    <%
        if (targetUser.getUserClassification().equals("学生")) {
    %>
    <tr align="center">
        <th>学部・学科</th>
        <td>
        </td>
    </tr>

    <%
        }
    %>


    <tr align="center">
        <th>生年月日</th>
        <td>
            <jsp:getProperty name="targetUser" property="birthday"/>
        </td>
    </tr>

    <tr align="center">
        <th>郵便番号</th>
        <td>
            <jsp:getProperty name="targetUser" property="postalCode"/>
        </td>
    </tr>

    <tr align="center">
        <th>住所</th>
        <td>
            <jsp:getProperty name="targetUser" property="address"/>
        </td>
    </tr>

    <tr align="center">
        <th>電話番号</th>
        <td>
            <jsp:getProperty name="targetUser" property="tel"/>
        </td>
    </tr>
</table>
<%
    if (user.getUserClassification().equals("管理者")) {
%>
    <form action="/Top" method="post">
        <button type="submit" name="action" value="UserUpdate">更新</button>
    </form>
<%
    }
%>

<%
    if (user.getUserId() == targetUser.getUserId()) {
%>
<h2 align="center">パスワード設定</h2>
<form action="/UserUpdate" method="post">
    <table BORDER="1" align="center">
        <tr>
            <th>更新前パスワード</th>
            <td>
                <input type="password" name="before_password" required>
            </td>
        </tr>
        <tr>
            <th>更新後パスワード</th>
            <td>
                <input type="password" name="after_password" required>
            </td>
        </tr>
    </table>
    <button type="submit" name="action" value="update_password">パスワード更新</button>
</form>
<%
    }
%>

<%
    if (user.getUserClassification().equals("学生")) {
%>
<h2 align="center">保護者用パスワード設定</h2>
<table BORDER="1" align="center">
    <tr>
        <th>パスワード<br>確認用パスワード</th>
        <td><input type="password" name="pass"><br><input type="password" name="kakuninpass"></td>
    </tr>
</table>
<%
    }
%>

</body>
</html>
