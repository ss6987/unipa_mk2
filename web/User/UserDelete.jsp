<%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/13
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="Entity.User" scope="session"/>
<jsp:useBean id="targetUser" class="Entity.User" scope="request"/>
<html lang="ja">
<head>
    <title>ユーザー削除確認</title>
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>
<body>

<form action="/Main" method="get">
    <button type="submit" name="action" class="btn_1" value="Top"> トップ</button>
</form>

<h1>ユーザー削除</h1>
<br>

以下の学生情報を完全に削除します。

<br>


<table BORDER="1" align="center">
    <tr align="center">
        <th>学籍番号</th>
        <td>
            <jsp:getProperty name="targetUser" property="userId"/>
        </td>
    </tr>

    <tr align="center">
        <th>氏名</th>
        <td>
            <jsp:getProperty name="targetUser" property="name"/>
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


    <tr align="center">
        <th>学部・学科</th>
        <td>
        </td>

    </tr>


    <tr align="center">
        <th>生年月日</th>
        <td>
            <jsp:getProperty name="targetUser" property="birthYear"/>
            年
            <jsp:getProperty name="targetUser" property="birthMonth"/>
            月
            <jsp:getProperty name="targetUser" property="birthDay"/>
            日
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

<form action="/Main" method="post">
    <input type="hidden" name="action" value="UserUpdate">
    <button type="submit" class="btn_1" name="login">もどる</button>
</form>
<form action="/UserDelete" method="post">
    <button type="submit" name="action" class="btn_3" value="delete" align="center"> 削除</button>
</form>
</body>
</html>
