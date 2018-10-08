<%--
  Created by IntelliJ IDEA.
  User: satone
  Date: 2018/07/20
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="searchUser" class="Entity.User" scope="session"/>
<jsp:useBean id="errorString" class="java.lang.String" scope="request"/>
<html lang="ja">
<head>
    <title>ユーザー検索</title>
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>

<body>
<form action="/Top" method="get">
    <button type="submit" class="btn_1" style="position: absolute; left: 0px; top: 0px">トップ</button>
</form>

<br>
<h1>ユーザー検索</h1>

<br>
<%
    if (!errorString.equals("")) {
%>
<span style="background-color:#ffcc99">
    <%=errorString%>
</span>
<%
    }
%>

<form action="/UserSearch" method="post">
    <table BORDER="1" align="center">
        <tr align="center">
            <th>学籍番号</th>
            <td>
                <input type="text" name="userId"
                       value="<jsp:getProperty name="searchUser" property="userId"/>"/>
            </td>
        </tr>

        <tr align="center">
            <th>氏名</th>
            <td>
                <input type="text" name="name" value="<jsp:getProperty name="searchUser" property="name"/>"/>
            </td>
        </tr>
        <tr>
            <th>フリガナ</th>
            <td>
                <input type="text" name="phonetic" value="<jsp:getProperty name="searchUser" property="phonetic"/>"/>
            </td>
        </tr>
        <tr>
            <th>性別</th>
            <td>
                <input type="text" name="gender" value="<jsp:getProperty name="searchUser" property="genderString"/>"/>
            </td>
        </tr>
        <tr>
            <th>生年月日</th>
            <td>
                <input type="text" name="birthday" value="<jsp:getProperty name="searchUser" property="birthday"/>"/>
            </td>
        </tr>
        <tr>
            <th>郵便番号</th>
            <td>
                <input type="text" name="postalCode" value="<jsp:getProperty name="searchUser" property="postalCode"/>"/>
            </td>
        </tr>
        <tr>
            <th>住所</th>
            <td>
                <input type="text" name="address" value="<jsp:getProperty name="searchUser" property="address"/>"/>
            </td>
        </tr>
        <tr>
            <th>電話番号</th>
            <td>
                <input type="text" name="tel" value="<jsp:getProperty name="searchUser" property="tel"/>"/>
            </td>
        </tr>
        <tr>
            <th>ユーザー分類</th>
            <td>
                <input type="text" name="userClassification" value="<jsp:getProperty name="searchUser" property="userClassification"/>"/>
            </td>
        </tr>
        <tr>
            <th>学部・学科</th>
            <td>

            </td>
        </tr>
    </table>
    <button type="submit" name="action" class="btn_4" value="firstSearch">検索</button>
</form>
</body>
</html>
