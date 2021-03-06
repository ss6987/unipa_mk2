<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="session" class="Entity.User"/>
<!doctype html>
<html lang="jp">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>トップページ</title>
    <link rel="stylesheet" type="text/css" href="Design.css">
</head>
<body>
<%
    String period = (String) request.getAttribute("period");
    String classification = user.getUserClassification();
    Boolean periodFlag = (Boolean) request.getAttribute("periodFlag");
%>
<table id="window">
    <tr>
        <td class="c-box">
            <form action="/Main" method="post">
                <button name="action" value="Logout">ログアウト</button>
            </form>
            <h1>UNIPAへようこそ！</h1>
            <h2><span style="background-color:#ffcc99">
            <%
                if (user.getUserClassification().equals("保護者")) {
            %>
            <jsp:getProperty name="user" property="name"/>(保護者)さん
            <%
            } else {
            %>
            <jsp:getProperty name="user" property="name"/>さん
            <%
                }
            %>
            </span><h2></h2>
            <h2>履修登録期間<%=period%>
            </h2>
            <form action="<%= request.getContextPath()%>/Main" method="post" accept-charset="UTF-8">
                <span><button type="submit" name="action" class="btn_2" value="myUserDetail">ユーザー情報確認</button></span>
                <span><button type="submit" name="action" class="btn_2" value="SyllabusSearch">シラバス検索</button></span>
                <%
                    if (classification.equals("学生")) {
                %>

                <span><button type="submit" name="action" class="btn_2" value="TimeTableCheck">時間割表確認</button> </span>
                <span><button type="submit" name="action" class="btn_2" value="AchieveCheck">成績参照</button></span>
                <%
                    if (periodFlag) {
                %>
                <span><button type="submit" name="action" class="btn_2" value="CourseRegistration">履修登録</button></span>
                <%
                    }
                %>
                <%
                } else if (classification.equals("教職員")) {
                %>
                <span><button type="submit" name="action" class="btn_2" value="TimeTableCheck">担当科目確認</button></span>
                <%
                } else if (classification.equals("管理者")) {
                %>
                <span><button type="submit" name="action" class="btn_2" value="UserSearch">ユーザー検索</button></span>
                <span><button type="submit" name="action" class="btn_2" value="UserRegistration">ユーザー登録</button></span>
                <span><button type="submit" name="action" class="btn_2"
                              value="SyllabusRegistration">シラバス登録</button></span>
                <span><button type="submit" name="action" class="btn_2"
                              value="PeriodRegistrationCheck">履修登録期間設定</button></span>
                <%
                } else {
                %>
                <span><button type="submit" name="action" class="btn_2" value="TimeTableCheck">時間割表確認</button> </span>
                <span><button type="submit" name="action" class="btn_2" value="AchieveCheck">成績参照</button></span>
                <%
                    }
                %>
            </form>
        </td>
    </tr>
</table>
</body>
</html>