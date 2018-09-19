<%@ page import="Entity.User" %><%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/13
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="Entity.User" scope="session"/>
<jsp:useBean id="targetUser" class="Entity.User" scope="request"/>

<html>
<head>
    <title>ユーザー管理</title>
</head>
<body>

<div>
    <form action="/Top" method="get">
        <button type="submit">トップ</button>
    </form>
</div>

<h1 align="center">ユーザー管理</h1>
<%
    if (user.getUserClassification().equals("学生")) {
%>
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
            <jsp:getProperty name="targetUser" property="gender"/>
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
<h2 align="center">パスワード設定</h2>
<table BORDER="1" align="center">
    <td>パスワード<br>確認用パスワード</td>
    <td><input type="password" name="pass"><br><input type="password" name="kakuninpass"></td>
    </tr>
</table>

<br>

<h2 align="center">保護者用パスワード設定</h2>
<table BORDER="1" align="center">
    <td>パスワード<br>確認用パスワード</td>
    <td><input type="password" name="pass"><br><input type="password" name="kakuninpass"></td>
    </tr>
</table>
<%
} else if (user.getUserClassification().equals("教職員")){
%>
<h2 align="center">更新情報を入力してください</h2>

<table BORDER="1" align="center">
    <tr align="center">
        <td>学籍番号</td>
        <td><input type="text" name="gakusekibangou"/></td>
    </tr>

    <tr align="center">
        <td>氏名<br>フリガナ</td>
        <td>姓<input type="text" name="say"/>名<input type="text" name="may"/>
            <br>セイ<input type="text" name="saykana"/>メイ<input type="text" name="maykana"/></td>
    </tr>

    <tr align="center">
        <td>ユーザー分類</td>
        <td><select name="bunrui" size="1">
            <option value="student">学生</option>
            <option value="staff">教職員</option>
            <option value="admin">管理者</option>
        </select></td>
    </tr>

    <tr align="center">
        <td>学部・学科</td>
        <td><select name="bunrui2" size="1">
            <option value="nc">工学部第二部情報通信工学科</option>
            <option value="nm">工学部第二部機械工学科</option>
            <option value="ne">工学部第二部電気電子工学科</option>
        </select></td>
    </tr>

    <tr align="center">
        <td>性別</td>
        <td><input type=radio name="sex" value="man">男性<input type=radio name="sex" value="woman">女性</td>
    </tr>

    <tr align="center">
        <td>生年月日</td>
        <td>西暦<input type="text" name="nen"/>年<input type="text" name="tsuki"/>月<input type="text" name="hi"/>日</td>
    </tr>

    <tr align="center">
        <td>郵便番号</td>
        <td><input type="text" name="yuubin"/><br>※ハイフンなしで入力</td>
    </tr>

    <tr align="center">
        <td>住所</td>
        <td><input type="text" name="jusho"/></td>
    </tr>

    <tr align="center">
        <td>電話番号</td>
        <td><input type="text" name="denwa"/><br>※ハイフンなしで入力</td>
    </tr>
</table>
<button type="submit" name="update" align="center"> 更新</button>
<button type="submit" name="userdel" align="center"> 削除</button>
<%
    }
%>

</body>
</html>
