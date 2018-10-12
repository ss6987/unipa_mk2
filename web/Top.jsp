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
    <form action="<%= request.getContextPath()%>/Main" method="post" accept-charset="UTF-8">
        <span><button type="submit" name="action" class="btn_2" value="myUserDetail">ユーザー情報確認</button></span>
        <span><button type="submit" name="action" class="btn_2" value="syllabusSearch">シラバス検索</button></span>
        <%
            if (classification.equals("学生")) {
        %>

        <span><button type="submit" name="action" class="btn_2" value="timeTableCheck">時間割表確認</button> </span>
        <span><button type="submit" name="action" class="btn_2" value="achieveCheck">成績参照</button></span>
        <span><button type="submit" name="action" class="btn_2" value="courseRegistration">履修登録</button> </span>
        <%
        } else if (classification.equals("教職員")) {
        %>
        <span><button type="submit" name="action" class="btn_2" value="courseCheck">担当科目確認</button></span>
        <%
        } else if (classification.equals("管理者")) {
        %>
        <span><button type="submit" name="action" class="btn_2" value="userSearch">ユーザー検索</button></span>
        <span><button type="submit" name="action" class="btn_2" value="userRegistration">ユーザー登録</button></span>
        <span><button type="submit" name="action" class="btn_2" value="syllabusRegistration">シラバス登録</button></span>
        <span><button type="submit" name="action" class="btn_2" value="periodRegistrationCheck">履修登録期間設定</button></span>
        <%
        } else {
        %>
        <span><button type="submit" name="action" class="btn_2" value="timeTableCheck">時間割表確認</button> </span>
        <span><button type="submit" name="action" class="btn_2" value="achieveCheck">成績参照</button></span>
        <%
            }
        %>
    </form>
</div>
</body>
</html>