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
    <title>TOP</title>
</head>
<body>
<%
    String period = (String) request.getAttribute("period");
    String classification = user.getUserClassification();
    boolean registrationPeriodFlag = (boolean) session.getAttribute("registrationPeriodFlag");
%>

<h1>UNIPAへようこそ！</h1>
<span style="background-color:#ffcc99"> <jsp:getProperty name="user" property="name"/></span>
<%
    if (registrationPeriodFlag) {
%>
<h2>
    履修登録期間<%=period%>
</h2>

<%
    }
%>

<div>
    <form action="<%= request.getContextPath()%>/Top" method="post" accept-charset="UTF-8">
        <span><button type="submit" name="action" value="MyUser">ユーザー情報確認</button></span>
        <span><button type="submit" name="action" value="SyllabusSearch">シラバス検索</button></span>
        <%
            if (classification.equals("学生")) {
        %>

        <span><button type="submit" name="action" value="CheckTimeTable">時間割表確認</button> </span>
        <span><button type="submit" name="action" value="Achieve">成績参照</button></span>
        <%
            if (registrationPeriodFlag) {
        %>
        <span><button type="submit" name="action" value="CourseRegistration">履修登録</button> </span>
        <%
            }
        %>
        <%
        } else if (classification.equals("教職員")) {
        %>
        <span><button type="submit" name="action" value="CourseCheck">担当科目確認</button></span>
        <%
        } else if (classification.equals("管理者")) {
        %>
        <span><button type="submit" name="action" value="UserSearch">ユーザー検索</button></span>
        <span><button type="submit" name="action" value="AchieveRegistration">成績登録</button></span>
        <span><button type="submit" name="action" value="UserRegistration">ユーザー登録</button></span>
        <span><button type="submit" name="action" value="SyllabusRegistration">シラバス登録</button></span>
        <span><button type="submit" name="action" value="AchieveCheck">履修登録確認</button></span>
        <%
        } else {
        %>
        <span><button type="submit" name="action" value="CheckTimeTable">時間割表確認</button> </span>
        <span><button type="submit" name="action" value="Achieve">成績参照</button></span>
        <%
            }
        %>
    </form>
</div>
</body>
</html>