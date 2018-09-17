<%@ page import="Entity.User" %><%--
  Created by IntelliJ IDEA.
  User: SS
  Date: 2018/09/17
  Time: 1:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="Entity.User" scope="session"/>

<html>
<head>
    <title>
        <jsp:getProperty name="user" property="name"/>
    </title>
</head>
<body>
<table>
    <tr>
        <th>ユーザーID</th>
        <td>
            <jsp:getProperty name="user" property="userId"/>
        </td>
    </tr>
    <tr>
        <th>名前</th>
        <td>
            <jsp:getProperty name="user" property="name"/>
        </td>
    </tr>
    <tr>
        <th>フリガナ</th>
        <td>
            <jsp:getProperty name="user" property="phonetic"/>
        </td>
    </tr>
    <tr>
        <th>性別</th>
        <td>
            <%
                if(user.getGender() == 0){
            %>
                男性
            <%
                }else{
            %>
                女性
            <%
                }
            %>
        </td>
    </tr>
    <tr>
        <th>生年月日</th>
        <td>
            <jsp:getProperty name="user" property="birthday"/>
        </td>
    </tr>
    <tr>
        <th>郵便番号</th>
        <td>
            <jsp:getProperty name="user" property="postalCode"/>
        </td>
    </tr>
    <tr>
        <th>住所</th>
        <td>
            <jsp:getProperty name="user" property="address"/>
        </td>
    </tr>
    <tr>
        <th>電話番号</th>
        <td>
            <jsp:getProperty name="user" property="tel"/>
        </td>
    </tr>
    <tr>
        <th>ユーザー分類</th>
        <td>
            <jsp:getProperty name="user" property="userClassification"/>
        </td>
    </tr>


</table>
</body>
</html>
