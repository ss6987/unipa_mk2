<%@ page import="Entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.User" %>
<%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/20
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="paging" class="etc.Paging" scope="request"/>
<%
    List<User> userList = (List<User>) request.getAttribute("userList");
%>

<html lang="ja">
<head>
    <title>ユーザー検索結果</title>
</head>
<body>

<form action="/Top" method="get">
    <button type="submit" name="check"> トップ</button>
</form>
<form action="/Top" method="post">
    <button type="submit" name="action" value="UserSearch">もどる</button>
</form>
<br>

<h1>検索結果</h1>
<jsp:getProperty name="paging" property="count"/>
<br>

<table BORDER="1" align="center">


    <tr>
        <th>学籍番号</th>
        <th>名前</th>
        <th>フリガナ</th>
        <th>性別</th>
        <th>生年月日</th>
        <th>郵便番号</th>
        <th>住所</th>
        <th>電話番号</th>
        <th>ユーザー分類</th>
    </tr>
    <%
        for (int i = 0;i < userList.size();i++) {
            User user = userList.get(i);
    %>
    <tr>
        <td>
            <%=user.getUserId()%>
        </td>
        <td>
            <form action="/Top" method="post" name="form<%=i%>">
                <input type="hidden" name="action" value="UserDetail"/>
                <input type="hidden" name="targetUserId" value="<%=user.getUserId()%>"/>
                <a href="javascript:form<%=i%>.submit()"><%=user.getName()%></a>
            </form>
        </td>
        <td>
            <%=user.getPhonetic()%>
        </td>
        <td>
            <%=user.getGenderString()%>
        </td>
        <td><%=user.getBirthYear()%>年
            <%=user.getBirthMonth()%>月
            <%=user.getBirthDay()%>日
        </td>
        <td>
            <%=user.getPostalCode()%>
        </td>
        <td>
            <%=user.getAddress()%>
        </td>
        <td>
            <%=user.getTel()%>
        </td>
        <td>
            <%=user.getUserClassification()%>
        </td>
    </tr>
    <%
        }
    %>
</table>


</body>
</html>
