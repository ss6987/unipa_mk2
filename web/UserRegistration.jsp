<%--
  Created by IntelliJ IDEA.
  User: satone
  Date: 2018/07/13
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="Entity.User" scope="session"/>
<jsp:useBean id="targetUser" class="Entity.User" scope="request"/>
<jsp:useBean id="targetUserId" class="java.lang.String" scope="session"/>
<jsp:useBean id="errorString" class="java.lang.String" scope="request"/>
<html lang="ja">
<head>
    <title>ユーザー登録</title>
</head>
<body>

<form action="/Top" method="get">
    <button type="submit" name="action" value="Top">トップ</button>
</form>
<br>
<%
    if (!targetUserId.equals("")) {
%>
<h1>ユーザー更新</h1>
<%
} else {
%>
<h1>ユーザー登録</h1>
<%
    }
%>
<br>

以下のフォームに登録する学生の情報を入力してくださいなんでもしますから

<br>
<%
    if (!errorString.equals("")) {
%>
<%=errorString%>
<%
    }
%>

<form action="/UserUpdate" method="post">
    <table BORDER="1" align="center">
        <tr align="center">
            <th>学籍番号</th>
            <td>
                <%
                    if (targetUserId.equals("")) {
                %>
                <input type="text" name="targetUserId" value="<jsp:getProperty name="targetUser" property="userId"/>">
                <%
                } else {
                %>
                <jsp:getProperty name="targetUser" property="userId"/>
                <%
                    }
                %>
            </td>
        </tr>

        <tr align="center">
            <th>氏名</th>
            <td>
                <input type="text" name="name" value="<jsp:getProperty name="targetUser" property="name"/>"/>
            </td>
        </tr>

        <tr align="center">
            <th>フリガナ</th>
            <td>
                <input type="text" name="phonetic" value="<jsp:getProperty name="targetUser" property="phonetic"/>"/>
            </td>
        </tr>

        <tr align="center">
            <th>ユーザー分類</th>
            <td>
                <select name="user_classification" size="1">
                    <option value="学生" <%=targetUser.getUserClassificationSelected("学生")%>>学生</option>
                    <option value="教職員" <%=targetUser.getUserClassificationSelected("教職員")%>>教職員</option>
                    <option value="管理者" <%=targetUser.getUserClassificationSelected("管理者")%>>管理者</option>
                </select>
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
            <th>性別</th>
            <td>
                <input type="radio" name="gender" value="0" <%=targetUser.getGenderSelected(0)%>>男性
                <input type="radio" name="gender" value="1" <%=targetUser.getGenderSelected(1)%>>女性
            </td>
        </tr>

        <tr align="center">
            <th>生年月日</th>
            <td>
                西暦<input type="text" name="year" value="<jsp:getProperty name="targetUser" property="birthYear"/>"/>年
                <input type="text" name="month" value="<jsp:getProperty name="targetUser" property="birthMonth"/>"/>月
                <input type="text" name="day" value="<jsp:getProperty name="targetUser" property="birthDay"/>"/>日
            </td>
        </tr>

        <tr align="center">
            <th>郵便番号</th>
            <td>
                <input type="text" name="postal_code"
                       value="<jsp:getProperty name="targetUser" property="postalCode"/>"/><br>※ハイフンなしで入力
            </td>
        </tr>

        <tr align="center">
            <th>住所</th>
            <td>
                <input type="text" name="address" value="<jsp:getProperty name="targetUser" property="address"/>"/>
            </td>
        </tr>

        <tr align="center">
            <th>電話番号</th>
            <td>
                <input type="text" name="tel" value="<jsp:getProperty name="targetUser" property="tel"/>"/><br>※ハイフンなしで入力
            </td>
        </tr>
    </table>
    <%
        if (!targetUserId.equals("")) {
    %>
    <button type="submit" name="action" value="update" align="center"> 更新</button>
    <button type="submit" name="action" value="delete" align="center"> 削除</button>
    <%
    } else {
    %>
    <button type="submit" name="action" value="insert" align="center"> 登録</button>
    <%
        }
    %>
</form>
</body>
</html>
