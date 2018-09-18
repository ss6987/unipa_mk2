<%--
  Created by IntelliJ IDEA.
  User: ASAMI
  Date: 2018/07/13
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="session" class="Entity.User"/>
<!doctype html>
<html lang="jp">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>

</body>
</html>
<html>
<head>
    <title>TOP</title>
</head>
<body>
<%
    String period = (String) request.getAttribute("period");
    String classification = user.getUserClassification();
%>

<h1>UNIPAへようこそ！</h1>
<span style="background-color:#ffcc99"> <jsp:getProperty name="user" property="name"/></span>
<h2>履修登録期間<%=period%>
</h2>
<div>
    <form action="/Top" method="post">
        <span><button type="submit" name="action" value="MyUser">ユーザー情報確認</button></span>
        <span><button type="submit" name="action" value="SyllabusSearch">シラバス検索</button></span>
        <%
            if (classification.equals("学生")) {
        %>

        <span><button type="submit" name="action" value="CheckTimeTable">時間割表確認</button> </span>
        <span>成績参照</span>
        <span>履修登録</span>

        <%
        } else if (classification.equals("教職員")) {
        %>
        <span>担当科目確認</span>
        <%
        } else if (classification.equals("管理者")) {
        %>
        <span>ユーザー検索</span>
        <span>成績情報登録</span>
        <span>ユーザー登録</span>
        <span>シラバス登録</span>
        <span>成績情報参照</span>
        <%
        } else {
        %>
        <span>時間割表確認</span>
        <span>成績参照</span>
        <%
            }
        %>
    </form>
</div>

</body>
</html>