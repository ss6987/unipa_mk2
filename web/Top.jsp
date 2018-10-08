
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
    <link rel="stylesheet" type="text/css" href="Design.css">
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
        <form action="<%= request.getContextPath()%>/Top" method="post" accept-charset="UTF-8">
            <span><button type="submit" name="action" class="btn_2" value="MyUser">ユーザー情報確認</button></span>
            <span><button type="submit" name="action" class="btn_2" value="SyllabusSearch">シラバス検索</button></span>
            <%
                if (classification.equals("学生")) {
            %>

            <span><button type="submit" name="action" class="btn_2" value="CheckTimeTable">時間割表確認</button> </span>
            <span><button type="submit" name="action" class="btn_2" value="Achieve">成績参照</button></span>
            <span><button type="submit" name="action" class="btn_2" value="CourseRegistration">履修登録</button> </span>
            <%
            } else if (classification.equals("教職員")) {
            %>
            <span><button type="submit" name="action" class="btn_2" value="CourseCheck">担当科目確認</button></span>
            <%
            } else if (classification.equals("管理者")) {
            %>
            <span><button type="submit" name="action" class="btn_2" value="UserSearch">ユーザー検索</button></span>
            <span><button type="submit" name="action" class="btn_2" value="AchieveRegistration">成績登録</button></span>
            <span><button type="submit" name="action" class="btn_2" value="UserRegistration">ユーザー登録</button></span>
            <span><button type="submit" name="action" class="btn_2" value="SyllabusRegistration">シラバス登録</button></span>
            <span><button type="submit" name="action" class="btn_2" value="AchieveCheck">履修登録期間設定</button></span>
            <%
            } else {
            %>
            <span><button type="submit" name="action" class="btn_2" value="CheckTimeTable">時間割表確認</button> </span>
            <span><button type="submit" name="action" class="btn_2" value="Achieve">成績参照</button></span>
            <%
                }
            %>
        </form>
    </div>
</body>
</html>